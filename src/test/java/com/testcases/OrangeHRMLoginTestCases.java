package com.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(com.utility.MyListeners.class)
@Listeners(com.utility.ExtentReportManager.class)
public class OrangeHRMLoginTestCases {

	WebDriver driver;

	@BeforeClass
	public void setUp() throws InterruptedException {
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		Thread.sleep(3000);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void verifyOrangeHRMLogoTest() {
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='company-branding']")).isDisplayed());
	}

	@Test(dependsOnMethods = "verifyOrangeHRMLogoTest")
	public void verifyOrangeAppUrlTest() {
		Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com");
	}

	@Test(dependsOnMethods = "verifyOrangeAppUrlTest")
	public void verifyOrangeHRMTitleTest() {
		Assert.assertEquals(driver.getTitle(), "OrangeHRM");
	}

}
