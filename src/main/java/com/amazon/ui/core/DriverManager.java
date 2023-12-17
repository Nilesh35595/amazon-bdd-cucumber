package com.amazon.ui.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

	public static WebDriver driver;
	
	public static WebDriver createInstance(String browser) {
		
		if(browser.contains("chrome")) {
			driver = new ChromeDriver();	
		} else if (browser.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		return driver;
	}
	
	public static void get(String url) {
		getDriver().get(url);
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static void exitBrowser() {
		getDriver().quit();
	}
	
}
