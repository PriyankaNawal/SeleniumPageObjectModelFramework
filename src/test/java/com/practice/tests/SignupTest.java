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

public class SignupTest extends BaseTest {
	SignupPage signupPage;
	AuthenticationPage authPage;
	MyAccountsPage myaccountPage;

	@Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
	public void ValidSignupTest(Hashtable<String, String> data) {
		ExcelUtil excel = new ExcelUtil(ExcelConstants.SUITE1_XL_PATH);
		TestDataUtil.checkExecution("master", "ValidSignupTest", data.get("Runmode"), excel);
		openBrowser(data.get("browser"));
		logInfo("Launched browser.." + data.get("browser"));
		HomePage homePage = new HomePage().open(url);
		authPage = homePage.goToLoginPage();
		BasePage page = authPage.createAccount(data.get("email"));
		if (page instanceof SignupPage) {
			signupPage = (SignupPage) page;
			myaccountPage =	signupPage.signUp(data.get("title"), data.get("firstName"), data.get("lastName"), data.get("email"),
					data.get("password"), data.get("dob"), data.get("newsletter"), data.get("specialoffers"),
					data.get("company"), data.get("address1"), data.get("address2"), data.get("city"),
					data.get("state"), data.get("postal"), data.get("country"), null, null, data.get("mobile"), null);

			Assert.assertEquals(myaccountPage.getAccountUserName(), data.get("firstName") + " " + data.get("lastName"));	
		}else {
			Assert.fail("Signup failed with an error:"+authPage.getSignupError());
		}
	}

	@Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
	public void InvalidSignupTest(Hashtable<String, String> data) {
		ExcelUtil excel = new ExcelUtil(ExcelConstants.SUITE1_XL_PATH);
		TestDataUtil.checkExecution("master", "InvalidSignupTest", data.get("Runmode"), excel);
		openBrowser(data.get("browser"));
		logInfo("Launched browser.." + data.get("browser"));
		HomePage homePage = new HomePage().open(url);
		authPage = homePage.goToLoginPage();
		BasePage page = authPage.createAccount(data.get("email"));
		if (page instanceof AuthenticationPage) {
			authPage = (AuthenticationPage) page;
			Assert.assertEquals(authPage.getSignupError(), "An account using this email address has already been registered. Please enter a valid password or request a new one.");
		}
	}
	
	@AfterMethod
	public void tearDown() {
		logInfo("Closing browser");
		quit();
	}
}
