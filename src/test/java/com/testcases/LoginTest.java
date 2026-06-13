package com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageobjects.HomePage;
import com.pageobjects.LoginPage;
import com.testbase.TestBase;

public class LoginTest extends TestBase {

	LoginPage lp;
	HomePage hm;

	@Test(priority = 1)
	public void verifyLoginPageTitleTC() {
		lp = new LoginPage(driver);
		String actualTitle = driver.getTitle();
		String expectedTitle = "OrangeHRM11";
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@Test(priority = 2)
	public void verifylogoTC() {
		Assert.assertTrue(lp.verifyLogo());
	}

//	@Test(priority = 3)
	public void loginTC() {
		hm =lp.login(configDataProvider.getUserName(), configDataProvider.getUserPassword());
		Assert.assertTrue(hm.dashboardDisplayed());	
	}

}
