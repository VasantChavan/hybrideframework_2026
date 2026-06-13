package com.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonUtils {

	public static String getCurrentTimeStamp() {
		String currentTimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		return currentTimeStamp;
	}

	/*
	 * switch to the frame based on frame id or name, index and frame element
	 * */
	public static void switchToiFrame(WebDriver driver, int index) {

		try {
			driver.switchTo().frame(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void switchToiFrame(WebDriver driver, String nameOrId) {

		try {
			driver.switchTo().frame(nameOrId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void switchToiFrame(WebDriver driver, WebElement frameElement) {

		try {
			driver.switchTo().frame(frameElement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void switchToDefaultContent(WebDriver driver) {
		try {
			driver.switchTo().parentFrame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * handle browser pop in selenium
	 * */
	
	public static void handleAlertPop(WebDriver driver)
	{
		try {
			if(isAlertPresent(driver)) {
				Alert alert =driver.switchTo().alert();
				System.out.println(alert.getText());
				alert.accept();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean isAlertPresent(WebDriver driver)
	{
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public static void handleMultipleTabOrWindow(WebDriver driver,String windowTitle) {
		
		try {
			
			String parentWindow =driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			
			for(String window: windows) {
				if(parentWindow!=window) {
					if(driver.switchTo().window(window).getTitle().equals(windowTitle)) {
						System.out.println("Window id is : "+window+" and the title is :"+windowTitle);
						break;
					}
				}
			}
			
//			driver.switchTo().window(parentWindow);
//			System.out.println(driver.getTitle());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
