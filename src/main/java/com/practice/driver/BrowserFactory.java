package com.practice.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public enum BrowserFactory {

	CHROME {
		@Override
		public WebDriver createDriver() {
			System.setProperty("webdriver.chrome.driver", DriverBean.getChromeDriverExePath());
			return new ChromeDriver(getOptions());
		}

		@Override
		public ChromeOptions getOptions() {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments(START_MAXIMIZED);
			chromeOptions.addArguments("--disable-infobars");
			chromeOptions.addArguments("--disable-notifications");
			return chromeOptions;
		}
	},
	FIREFOX {
		@Override
		public WebDriver createDriver() {
			System.setProperty("webdriver.gecko.driver", DriverBean.getGeckoDriverExePath());

			return new FirefoxDriver(getOptions());
		}

		@Override
		public FirefoxOptions getOptions() {
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments(START_MAXIMIZED);
			return firefoxOptions;
		}
	},
	EDGE {
		@Override
		public WebDriver createDriver() {
			System.setProperty("webdriver.edge.driver", DriverBean.getEdgeDriverExePath());
			return new EdgeDriver(getOptions());
		}

		@Override
		public EdgeOptions getOptions() {
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments(START_MAXIMIZED);
			return edgeOptions;
		}
	};

	private static final String START_MAXIMIZED = "--start-maximized";

	public abstract WebDriver createDriver();

	public abstract AbstractDriverOptions<?> getOptions();
}
