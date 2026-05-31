package com.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageobjects.LoginPage;
import com.testbase.TestBase;
import com.utility.ExcelDataProvider;

public class LoginTC02 extends TestBase{
	
	
	
	
	
	@Test(dataProvider = "createData")
	public void verifyLogin(String username, String password)
	{
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);
		
		System.out.println(driver.getTitle()+"  "+driver.getCurrentUrl());
	}
	
	@DataProvider
	public String[][] createData()
	{
		return ExcelDataProvider.getTestData("login");
	}

}
