package com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageobjects.HomePage;
import com.pageobjects.LoginPage;
import com.testbase.TestBase;
import com.utility.ExcelDataProvider;

public class LoginTC01 extends TestBase{

	
	@Test
	public void login()
	{
		LoginPage lp = new LoginPage(driver);
		HomePage hm = lp.login(ExcelDataProvider.getCellData("login", 1, 0),ExcelDataProvider.getCellData("login", 1, 1));
		
		Assert.assertTrue(hm.dashboardDisplayed());
		
		hm.logout();
		
//		ExcelDataProvider.setTestResut("login", "orangehrm", 0, 2, "Test Result");
//		ExcelDataProvider.setTestResut("login", "orangehrm", 1, 2, "Pass");
		
	}
}
