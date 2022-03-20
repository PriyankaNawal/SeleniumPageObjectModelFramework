package com.practice.utils;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	
	@DataProvider(name="masterDP")
	public static Object[][] getDataSuite1(Method m) {

		System.out.println(m.getName());
		
		ExcelUtil excel = new ExcelUtil(ExcelConstants.SUITE1_XL_PATH);
		String testcase = m.getName();
		return TestDataUtil.getData(testcase, excel);
	
	}
	


}
