package com.practice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountsPage extends BasePage{

	@FindBy(css = "ul.myaccount-link-list")
	public WebElement myAccountList;
	
	@FindBy(css = "ul.myaccount-link-list>li:nth-child(1)")
	public WebElement orderHistoryDetails;
	
	@FindBy(css = "ul.myaccount-link-list>li:nth-child(2)")
	public WebElement myCreditSlips;
		
	@FindBy(css = "ul.myaccount-link-list>li:nth-child(3)")
	public WebElement myAddresses;
			
	@FindBy(className = "account")
	public WebElement accountUserName;
	
	public String getAccountUserName() {
		return accountUserName.getText();
	}
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(orderHistoryDetails);
	}

}
