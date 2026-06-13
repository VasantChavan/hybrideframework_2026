package com.testbase;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.utility.Utils;
import com.utility.ConfigDataProvider;
import com.utility.ExcelDataProvider;

public class TestBase {

	public static WebDriver driver;
	public static ConfigDataProvider configDataProvider;
	public static ExcelDataProvider excelDataProvider;

	@BeforeSuite
	public void init() {
		configDataProvider = new ConfigDataProvider("config");
		excelDataProvider=new ExcelDataProvider("orangehrm");
	}

	@BeforeTest
	@Parameters("browser")
	public void setUp(@Optional("chrome") String browserType) throws InterruptedException {

		switch (browserType.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;

		}

		//driver.manage().window().maximize();

		// driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		driver.get(configDataProvider.getAppUrl());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(3000);

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	public static String captureScreenshot(String name) {
		String screenshotpath = System.getProperty("user.dir") + "/Screenshots/" + name + Utils.getCurrentTimeStamp()+".png";
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File target= new File(screenshotpath);
		
			//FileHandler.copy(scrFile, target);
			scrFile.renameTo(target);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return screenshotpath;

	}
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
	}
}
