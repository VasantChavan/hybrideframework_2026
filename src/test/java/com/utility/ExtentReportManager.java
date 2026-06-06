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

public class ExtentReportManager implements ITestListener {

	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;

	public void onStart(ITestContext context) {

		
		String currentWorkingDirectory = System.getProperty("user.dir");
		String customformat = new SimpleDateFormat("YYYY-mm-dd-HH-mm-ss").format(new Date());
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
	}

	public void onTestSkipped(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.SKIP, "Test case skipped is : "+result.getName());
	}
	
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}
	
	
	public static void main(String[] args) {
		
		Date currentDate = new Date();
		System.out.println(currentDate);
		
		String requiredFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").format(currentDate);
		System.out.println(requiredFormat);
	}

}
