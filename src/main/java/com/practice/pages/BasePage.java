package com.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.practice.driver.DriverManager;
import com.practice.listeners.ExtentListeners;

public abstract class BasePage<T> {
	protected WebDriver driver;
	Select select;
	public BasePage() {
		this.driver = DriverManager.getDriver();
	}

	public T openPage(Class<T> cls) {
		T page = null;
		AjaxElementLocatorFactory ajaxElementFactory = new AjaxElementLocatorFactory(driver, 10);
		driver = DriverManager.getDriver();
		page = PageFactory.initElements(driver, cls);
		PageFactory.initElements(ajaxElementFactory, page);
		ExpectedCondition pageLoadCondition = ((BasePage) page).getPageLoadCondition();
		waitForPageToLoad(pageLoadCondition);
		return page;
	}

	private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(pageLoadCondition);
	}

	protected abstract ExpectedCondition getPageLoadCondition();
	
	public void click(WebElement element, String elementName) {
		element.click();
		ExtentListeners.testReport.get().info("Clicking on : " + elementName);
	}
	
	public void type(WebElement element, String text, String elementName) {
		element.sendKeys(text);
		ExtentListeners.testReport.get().info("Typing in : " + elementName + " value as : " + text);
	}
	
	public void selectByValue(WebElement element, String value) {
		select = new Select(element);
		select.selectByValue(value);
	}
	
	public void selectByVisibleText(WebElement element, String text) {
		select = new Select(element);
		select.selectByVisibleText(text);
	}

	
	public boolean isElementSelected(WebElement element) {
		boolean flag = false;
		try {
			flag = element.isSelected();
		}catch(NoSuchElementException e) {
			return flag;
		}
		return flag;
	}

	public boolean isElementPresent(WebElement element) {
		boolean flag = false;
		try {
			flag = element.isDisplayed();
		}catch(NoSuchElementException e) {
			return flag;
		}
		return flag;
	}

}
