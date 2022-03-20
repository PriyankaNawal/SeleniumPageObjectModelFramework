package com.practice.driver;

public class DriverBean {

	private static String chromeDriverExePath;
	private static String geckoDriverExePath;
	private static String edgeDriverExePath;
	private static String configPropertyFilePath;
	private static String gridPath;
	private static Target target;

	public static String getGridPath() {
		return gridPath;
	}

	public static void setGridPath(String gridPath) {
		DriverBean.gridPath = gridPath;
	}

	public static String getConfigPropertyFilePath() {
		return configPropertyFilePath;
	}

	public static void setConfigPropertyFilePath(String configPropertyFilePath) {
		DriverBean.configPropertyFilePath = configPropertyFilePath;
	}

	public static Target getTarget() {
		return target;
	}

	public static void setTarget(Target target) {
		DriverBean.target = target;
	}

	public static String getChromeDriverExePath() {
		return chromeDriverExePath;
	}

	public static void setChromeDriverExePath(String chromeDriverExePath) {
		DriverBean.chromeDriverExePath = chromeDriverExePath;
	}

	public static String getGeckoDriverExePath() {
		return geckoDriverExePath;
	}

	public static void setGeckoDriverExePath(String geckoDriverExePath) {
		DriverBean.geckoDriverExePath = geckoDriverExePath;
	}

	public static String getEdgeDriverExePath() {
		return edgeDriverExePath;
	}

	public static void setEdgeDriverExePath(String edgeDriverExePath) {
		DriverBean.edgeDriverExePath = edgeDriverExePath;
	}

	

}

