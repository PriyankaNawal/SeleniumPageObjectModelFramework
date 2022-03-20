package com.practice.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;

import com.practice.driver.BrowserFactory;
import com.practice.driver.DriverBean;
import com.practice.driver.DriverManager;
import com.practice.driver.Target;
import com.practice.listeners.ExtentListeners;

public class BaseTest {

	public WebDriver driver;
	public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();
	public String currentDir = System.getProperty("user.dir");
	public Properties prop;
	private FileInputStream fis;
	public Logger log = Logger.getLogger(BaseTest.class);
	public static String url;
	public static Target target;
	public static String gridUrl;

	@BeforeSuite
	public void setUp() {
		configureLogging();
		DriverBean.setConfigPropertyFilePath(currentDir + "//src//test//resources//properties//Config.properties");

		try {
			fis = new FileInputStream(DriverBean.getConfigPropertyFilePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop = new Properties();
			prop.load(fis);
			log.info("Config properties file loaded");
			url = prop.getProperty("url");
			target = Target.valueOf(prop.getProperty("target"));
			gridUrl = prop.getProperty("gridUrl");
		} catch (IOException e) {
			e.printStackTrace();
		}

		DriverBean.setGridPath(gridUrl);
		DriverBean.setChromeDriverExePath(currentDir + "//src//test//resources//drivers//chromedriver.exe");
		DriverBean.setGeckoDriverExePath(currentDir + "//src//test//resources//drivers//geckodriver.exe");
		DriverBean.setEdgeDriverExePath(currentDir + "//src//test//resources//drivers//msedgedriver.exe");

	}

	public void logInfo(String message) {
		ExtentListeners.testReport.get().info(message);
	}

	public void configureLogging() {
		String log4jConfigFile = currentDir + "//src//test//resources//properties//log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
	}

	public void openBrowser(String browserName) {
		DriverBean.setTarget(target);

		switch (DriverBean.getTarget()) {
		case LOCAL:
			driver = BrowserFactory.valueOf(browserName.toUpperCase()).createDriver();
			break;
		case REMOTE:
			driver = createRemoteInstance(BrowserFactory.valueOf(browserName.toUpperCase()).getOptions());
			break;
		default:
			log.error("Inside OpenBrowser: Unknow target value. Please provide valid target!");
		}
//		if (DriverBean.isRemote()) {
//			DesiredCapabilities cap = null;
//			if (browserName.equalsIgnoreCase("Chrome")) {
//
//				cap = DesiredCapabilities.chrome();
//				cap.setPlatform(Platform.ANY);
//				cap.setBrowserName(browserName);
//			} else if (browserName.equalsIgnoreCase("Firefox")) {
//
//				cap = DesiredCapabilities.firefox();
//				cap.setPlatform(Platform.ANY);
//				cap.setBrowserName(browserName);
//
//			} else if (browserName.equalsIgnoreCase("Edge")) {
//				cap = DesiredCapabilities.edge();
//				cap.setPlatform(Platform.ANY);
//				cap.setBrowserName(browserName);
//			}
//
//			try {
//				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
//			} catch (MalformedURLException e) {
//
//				e.printStackTrace();
//			}
//
//		} else {
//			if (browserName.equalsIgnoreCase("Chrome")) {
//				System.setProperty("webdriver.chrome.driver", DriverBean.getChromeDriverExePath());
//				driver = new ChromeDriver();
//			} else if (browserName.equalsIgnoreCase("Firefox")) {
//				System.setProperty("webdriver.gecko.driver", DriverBean.getGeckoDriverExePath());
//				driver = new FirefoxDriver();
//
//			} else if (browserName.equalsIgnoreCase("Edge")) {
//				System.setProperty("webdriver.edge.driver", DriverBean.getEdgeDriverExePath());
//				driver = new EdgeDriver();
//			}
//		}

		DriverManager.setWebDriver(driver);
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	private WebDriver createRemoteInstance(MutableCapabilities capability) {
		try {
			driver = new RemoteWebDriver(new URL(gridUrl), capability);
		} catch (java.net.MalformedURLException e) {
			log.error("Grid URL is invalid or Grid is not available");
			log.error(String.format("Browser: %s", capability.getBrowserName()), e);
		} catch (IllegalArgumentException e) {
			log.error(String.format("Browser %s is not valid or recognized", capability.getBrowserName()), e);
		}
		return driver;
	}

	public void quit() {
		DriverManager.getDriver().quit();
	}

}
