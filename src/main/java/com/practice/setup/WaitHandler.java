package com.practice.setup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.practice.driver.DriverManager;

public class WaitHandler {
	
	static WebDriverWait wait = null;
	
	public static void waitForElementToBeClickable(WebElement element, long waitTime) {
		wait = new WebDriverWait(DriverManager.getDriver(), waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	public static void waitForVisibilityOfElement(WebElement element, long waitTime) {
		wait = new WebDriverWait(DriverManager.getDriver(), waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitForPresenceOfElement(By locator, long waitTime) {
		wait = new WebDriverWait(DriverManager.getDriver(), waitTime);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	
	
	public static void waitForStalenessOf(WebElement element, long waitTime) {
		wait = new WebDriverWait(DriverManager.getDriver(), waitTime);
		wait.until(ExpectedConditions.stalenessOf(element));

	}
	

}
