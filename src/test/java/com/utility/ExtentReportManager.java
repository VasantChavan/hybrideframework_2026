package com.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.testbase.TestBase;

public class ExtentReportManager implements ITestListener {

	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;

	public void onStart(ITestContext context) {

		
		String currentWorkingDirectory = System.getProperty("user.dir");
		String customformat = Utils.getCurrentTimeStamp();
		File fs = new File(currentWorkingDirectory + "/reports/extent-report_"+customformat+".html");
		extentSparkReporter = new ExtentSparkReporter(fs);

		extentSparkReporter.config().setDocumentTitle("Automation Test Reports");
		extentSparkReporter.config().setReportName("Regression Testing Report");
//		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setTheme(Theme.STANDARD);

		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);

		extentReports.setSystemInfo("Computer Name", "Local host");
		extentReports.setSystemInfo("Test Name", "Vasant");
		extentReports.setSystemInfo("Evironment", "Dev");
		extentReports.setSystemInfo("OS", "Windows");
		extentReports.setSystemInfo("Browser Name", "Chrome");

	}
	public void onTestSuccess(ITestResult result) {
		
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.PASS, "Test case passed is : "+result.getName());
	}

	public void onTestFailure(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.FAIL, "Test case failed is : "+result.getName());
		extentTest.log(Status.FAIL, "Test case failed is : "+result.getThrowable());
		try {
			String screenshotPath = TestBase.captureScreenshot(result.getName());
			extentTest.addScreenCaptureFromPath(screenshotPath);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.SKIP, "Test case skipped is : "+result.getName());
	}
	
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}
		
	
}
