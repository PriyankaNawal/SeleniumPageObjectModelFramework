package com.practice.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.practice.driver.DriverManager;

public class ExtentManager {

	private static ExtentReports extent;
	public static String screenshotPath;
	public static String screenshotName;

	public static ExtentReports buildInstance(String fileName) {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
		
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setDocumentTitle(fileName);
		sparkReporter.config().setEncoding("utf-8");
		sparkReporter.config().setReportName(fileName);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Automation Tester", "Priyanka Nawal");
		extent.setSystemInfo("Organization", "Demo");

		return extent;
	}

	public static void takeScreenshot() {

		File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
