package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//h6[text()='Dashboard']")
	@CacheLookup
	WebElement dashboard;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span/img")
	@CacheLookup
	WebElement profilePic;

	@FindBy(xpath = "//a[text()='Logout']")
	@CacheLookup
	WebElement logoutLink;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public boolean dashboardDisplayed() {
		return dashboard.isDisplayed();
	}

	public void logout() {
		try {
			profilePic.click();
			logoutLink.click();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
