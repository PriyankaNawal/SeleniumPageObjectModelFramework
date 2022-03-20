package com.practice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignupPage extends BasePage {

	@FindBy(css = ".radio:nth-child(1)")
	public WebElement mrRadio;

	@FindBy(css = "#id_gender2")
	public WebElement mrsRadio;

	@FindBy(name = "customer_firstname")
	public WebElement customerFirstname;

	@FindBy(id = "customer_lastname")
	public WebElement customerLastname;

	@FindBy(id = "email")
	public WebElement emailId;

	@FindBy(css = "#passwd")
	public WebElement password;

	@FindBy(id = "days")
	public WebElement daysDropdown;

	@FindBy(id = "months")
	public WebElement monthsDropdown;

	@FindBy(id = "years")
	public WebElement yearsDropdown;

	@FindBy(id = "newsletter")
	public WebElement newsletterCheckbox;

	@FindBy(name = "optin")
	public WebElement specialOffersCheckbox;

	@FindBy(id = "firstname")
	public WebElement firstname;

	@FindBy(id = "lastname")
	public WebElement lastname;

	@FindBy(id = "company")
	public WebElement company;

	@FindBy(name = "address1")
	public WebElement address1;

	@FindBy(name = "address2")
	public WebElement address2;

	@FindBy(name = "city")
	public WebElement city;

	@FindBy(css = "#id_state")
	public WebElement stateDropdown;

	@FindBy(id = "postcode")
	public WebElement postcode;

	@FindBy(css = "#id_country")
	public WebElement countryDropdown;

	@FindBy(id = "other")
	public WebElement additionalInfo;

	@FindBy(id = "phone")
	public WebElement homephone;

	@FindBy(name = "phone_mobile")
	public WebElement mobilephone;

	@FindBy(xpath = "//button[@id='submitAccount']//span[text()='Register']")
	public WebElement registerButton;

	
	public void selectNewsLetterCheckbox(WebElement element, String isSelect) {
		if (isSelect.equalsIgnoreCase("true") && !isElementSelected(element)) {
			click(element, "News Letter checkbox");
		}
	}
	
	public void selectSpecialOffersCheckbox(WebElement element, String isSelect) {
		if (isSelect.equalsIgnoreCase("true") && !isElementSelected(element)) {
			click(element, "SpecialOffers checkbox");
		}
	}
	
	public MyAccountsPage signUp(String title, String firstName, String lastName, String email, String password, String dob,
			String newsletter, String specialOffers, String company, String address1, String address2, String city,
			String state, String postal, String country, String additionalInfo, String homePhone, String mobile,
			String addressAlias) {
		
		if(title.equalsIgnoreCase("mr"))
			click(mrRadio, "Mr Radio Button");
		else 
			click(mrsRadio, "Mrs Radio Button");
		
		type(customerFirstname, firstName, "Customer First Name");
		type(customerLastname, lastName, "Customer Last Name");
		type(this.password, password, "Password" );
		selectByValue(daysDropdown, dob.split(" ")[0]);
		selectByValue(monthsDropdown, dob.split(" ")[1]);
		selectByValue(yearsDropdown, dob.split(" ")[2]);		
		selectNewsLetterCheckbox(newsletterCheckbox, newsletter);
		selectSpecialOffersCheckbox(specialOffersCheckbox, specialOffers);
		type(this.company, company , "Company");
		type(this.address1, address1, "Address1");
		type(this.address2, address2, "Address2");
		type(this.city, city, "City");
		selectByVisibleText(stateDropdown, state);
		type(postcode, postal, "Postal Code");
		selectByVisibleText(countryDropdown, country);
		type(mobilephone, mobile, "Mobile");
		click(registerButton, "Register button");
		return (MyAccountsPage) openPage(MyAccountsPage.class);
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(customerFirstname);
	}

}
