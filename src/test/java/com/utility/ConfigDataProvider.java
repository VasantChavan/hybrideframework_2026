package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {

	Properties prop;

	public ConfigDataProvider(String filename) {
		String currWorDic = System.getProperty("user.dir") + "\\Config\\";

		try {
			File fs = new File(currWorDic + filename + ".properties");
			FileInputStream fins = new FileInputStream(fs);
			prop = new Properties();
			prop.load(fins);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public String getValue(String key) {
		return prop.getProperty(key);
	}

	public String getUserName() {
		return prop.getProperty("Username");
	}

	public String getUserPassword() {
		return prop.getProperty("Password");
	}

	public String getAppUrl() {
		return prop.getProperty("URL");
	}
	
	
	public static void main(String[] args) {
		
		ConfigDataProvider configDataProvider=new ConfigDataProvider("config");
		
		System.out.println(configDataProvider.getUserName());
		System.out.println(configDataProvider.getUserPassword());
		System.out.println(configDataProvider.getAppUrl());
		
		System.out.println(configDataProvider.getValue("Username"));
		System.out.println(configDataProvider.getValue("password"));
		System.out.println(configDataProvider.getValue("URL"));
	}

}
