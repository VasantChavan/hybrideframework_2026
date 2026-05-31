package com.testcases;

import org.testng.annotations.Test;

import com.pageobjects.HomePage;
import com.pageobjects.LoginPage;
import com.testbase.TestBase;

public class HomePageTC extends TestBase
{
	
	HomePage hm;
	
	@Test
	public void logoutTest()
	{
		hm =new LoginPage(driver).login(configDataProvider.getUserName(), configDataProvider.getUserPassword());
		hm.logout();
	}

}
