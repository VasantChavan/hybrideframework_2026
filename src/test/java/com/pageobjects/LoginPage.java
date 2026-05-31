package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	// Object Repository Login

	@FindBy(name = "username")
	@CacheLookup
	WebElement txt_username;

	@FindBy(name = "password")
	@CacheLookup
	WebElement txt_password;

	@FindBy(xpath = "//button[@type='submit']")
	@CacheLookup
	WebElement loginBtn;

	@FindBy(xpath = "//img[@alt='company-branding']")
	@CacheLookup
	WebElement logo;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean verifyLogo() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logo.isDisplayed();
	}

	public HomePage login(String username, String password) {

		try {
			txt_username.clear();
			txt_username.sendKeys(username);

			txt_password.clear();
			txt_password.sendKeys(password);

			loginBtn.click();
			return new HomePage(driver);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
