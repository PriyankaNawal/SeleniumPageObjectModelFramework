package com.practice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.practice.driver.DriverManager;

public class HomePage extends BasePage{
	
	
	@FindBy(className = "login")
	public WebElement signIn;

	@FindBy(id = "contact-link")
	public WebElement contactUs;
	
	@FindBy(id = "search_query_top")
	public WebElement searchBox;
	
	@FindBy(name = "submit_search")
	public WebElement searchButton;

	public HomePage open(String url) {
		DriverManager.getDriver().navigate().to(url);
		return (HomePage) openPage(HomePage.class);
	}
	
	public AuthenticationPage goToLoginPage() {
		click(signIn, "Sign In Link");
		return (AuthenticationPage) openPage(AuthenticationPage.class);
	}
	
	public ContactUsPage goToContactUsPage() {
		click(signIn, "Contact Us Link");
		return (ContactUsPage) openPage(ContactUsPage.class);
	}
	
	public void search(String value) {
		type(searchBox, value, "Search box");
		click(searchButton, "Search button");
	}
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(signIn);
	}

}
