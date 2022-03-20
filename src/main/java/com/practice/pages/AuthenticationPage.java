package com.practice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.practice.setup.WaitHandler;

public class AuthenticationPage extends BasePage {

	@FindBy(id = "email_create")
	public WebElement emailIdCreate;

	@FindBy(id = "SubmitCreate")
	public WebElement createAnAccount;

	@FindBy(css = "#create_account_error li")
	public WebElement createAccountError;

	@FindBy(name = "email")
	public WebElement loginEmail;

	@FindBy(name = "passwd")
	public WebElement loginPassword;

	@FindBy(id = "SubmitLogin")
	public WebElement signInButton;

	@FindBy(linkText = "Forgot your password?")
	public WebElement forgotPassword;

	@FindBy(xpath = "//div[@id='center_column']/div[@class='alert alert-danger']//li")
	public WebElement signInError;

	public BasePage createAccount(String username) {
		type(emailIdCreate, username, "Email");
		click(createAnAccount, "Next Button");

		if (!isElementPresent(createAccountError)) {
			return (SignupPage) openPage(SignupPage.class);
		} else {
			return this;
		}

	}

	public BasePage login(String username, String pwd) {
		type(loginEmail, username, "Login Email");
		type(loginPassword, pwd, "Login Password");
		click(signInButton, "Sign In Button");

		if (!isElementPresent(signInError)) {
			return (MyAccountsPage) openPage(MyAccountsPage.class);
		} else {
			return this;
		}

	}

	public String getSignupError() {
		return createAccountError.getText();
	}

	public String getLoginError() {
		return signInError.getText();
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(emailIdCreate);
	}
}
