package com.practice.tests;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.pages.AuthenticationPage;
import com.practice.pages.BasePage;
import com.practice.pages.HomePage;
import com.practice.pages.MyAccountsPage;
import com.practice.pages.SignupPage;
import com.practice.utils.ExcelConstants;
import com.practice.utils.DataProviders;
import com.practice.utils.TestDataUtil;
import com.practice.utils.ExcelUtil;

public class LoginTest extends BaseTest {
	AuthenticationPage authPage;
	MyAccountsPage myaccountPage;

	@Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
	public void ValidLoginTest(Hashtable<String, String> data) {
		ExcelUtil excel = new ExcelUtil(ExcelConstants.SUITE1_XL_PATH);
		TestDataUtil.checkExecution("master", "ValidLoginTest", data.get("Runmode"), excel);
		openBrowser(data.get("browser"));
		logInfo("Launched browser.." + data.get("browser"));
		HomePage homePage = new HomePage().open(url);
		authPage = homePage.goToLoginPage();
		BasePage page = authPage.login(data.get("email"), data.get("password"));
		
		if (page instanceof MyAccountsPage) {
			myaccountPage = (MyAccountsPage) page;
			Assert.assertEquals(myaccountPage.getAccountUserName(), data.get("fullName"));	
		}
	}

	@Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
	public void InvalidLoginTest(Hashtable<String, String> data) {
		ExcelUtil excel = new ExcelUtil(ExcelConstants.SUITE1_XL_PATH);
		TestDataUtil.checkExecution("master", "InvalidLoginTest", data.get("Runmode"), excel);
		openBrowser(data.get("browser"));
		logInfo("Launched browser.." + data.get("browser"));
		HomePage homePage = new HomePage().open(url);
		authPage = homePage.goToLoginPage();
		BasePage page = authPage.login(data.get("email"), data.get("password"));
		if (page instanceof AuthenticationPage) {
			authPage = (AuthenticationPage) page;
			Assert.assertTrue(authPage.getLoginError().contains("Invalid password."));
		}
	}
	
	@AfterMethod
	public void tearDown() {
		logInfo("Closing browser");
		quit();
	}
}
