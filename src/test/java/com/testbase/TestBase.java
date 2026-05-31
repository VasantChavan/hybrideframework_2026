package com.testbase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

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

	@BeforeMethod
	@Parameters("browser")
	public void setUp(@Optional("chrome") String browserType) {

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

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
