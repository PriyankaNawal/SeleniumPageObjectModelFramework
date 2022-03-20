package com.practice.utils;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;

public class TestDataUtil {

	public static void checkExecution(String testSuiteName, String testCaseName, String dataRunMode,
			ExcelUtil excel) {

		if (!isSuiteRunnable(testSuiteName)) {

			throw new SkipException("Skipping the test : " + testCaseName + " as the Runmode of Test Suite : "
					+ testSuiteName + " is NO");

		}
		
		
		if (!isTestRunnable(testCaseName,excel)) {

			throw new SkipException("Skipping the test : " + testCaseName + " as the Runmode of Test Case : "
					+ testCaseName + " is NO");

		}
		
		
		if(dataRunMode.equalsIgnoreCase(ExcelConstants.RUNMODE_NO)){
			
			
			throw new SkipException("Skipping the test : "+testCaseName+" as the Run mode to Data set is NO");
		}

	}

	public static boolean isSuiteRunnable(String suiteName) {

		ExcelUtil excel = new ExcelUtil(ExcelConstants.SUITE_XL_PATH);
		int rows = excel.getRowCount(ExcelConstants.SUITE_SHEET);

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			String data = excel.getCellData(ExcelConstants.SUITE_SHEET, ExcelConstants.SUITENAME_COL, rowNum);

			if (data.equals(suiteName)) {

				String runmode = excel.getCellData(ExcelConstants.SUITE_SHEET, ExcelConstants.RUNMODE_COL, rowNum);
				if (runmode.equals(ExcelConstants.RUNMODE_YES))
					return true;
				else
					return false;

			}

		}

		return false;

	}

	public static boolean isTestRunnable(String testCaseName, ExcelUtil excel) {

		int rows = excel.getRowCount(ExcelConstants.TESTCASE_SHEET);

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			String data = excel.getCellData(ExcelConstants.TESTCASE_SHEET, ExcelConstants.TESTCASE_COL, rowNum);

			if (data.equals(testCaseName)) {

				String runmode = excel.getCellData(ExcelConstants.TESTCASE_SHEET, ExcelConstants.RUNMODE_COL, rowNum);
				if (runmode.equals(ExcelConstants.RUNMODE_YES))
					return true;
				else
					return false;

			}

		}

		return false;

	}

	@DataProvider
	public static Object[][] getData(String testCase, ExcelUtil excel) {

		int rows = excel.getRowCount(ExcelConstants.DATA_SHEET);
		// System.out.println("Total rows are : " + rows);

		String testName = testCase;

		// Find the test case start row

		int testCaseRowNum = 1;

		for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {

			String testCaseName = excel.getCellData(ExcelConstants.DATA_SHEET, 0, testCaseRowNum);

			if (testCaseName.equalsIgnoreCase(testName))
				break;

		}

		// System.out.println("Test case starts from row num: " + testCaseRowNum);

		// Checking total rows in test case

		int dataStartRowNum = testCaseRowNum + 2;

		int testRows = 0;
		while (!excel.getCellData(ExcelConstants.DATA_SHEET, 0, dataStartRowNum + testRows).equals("")) {

			testRows++;
		}

		// System.out.println("Total rows of data are : " + testRows);

		// Checking total cols in test case

		int colStartColNum = testCaseRowNum + 1;
		int testCols = 0;

		while (!excel.getCellData(ExcelConstants.DATA_SHEET, testCols, colStartColNum).equals("")) {

			testCols++;

		}

		// System.out.println("Total cols are : " + testCols);

		// Printing data

		Object[][] data = new Object[testRows][1];

		int i = 0;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {

			Hashtable<String, String> table = new Hashtable<String, String>();

			for (int cNum = 0; cNum < testCols; cNum++) {

				// System.out.println(excel.getCellData(Constants.DATA_SHEET,
				// cNum, rNum));
				String testData = excel.getCellData(ExcelConstants.DATA_SHEET, cNum, rNum);
				String colName = excel.getCellData(ExcelConstants.DATA_SHEET, cNum, colStartColNum);

				table.put(colName, testData);

			}

			data[i][0] = table;
			i++;

		}

		return data;
	}

}
