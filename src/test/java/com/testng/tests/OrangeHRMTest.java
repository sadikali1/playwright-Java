package com.testng.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class OrangeHRMTest {
	
	Browser browser;
	Page page;
	Playwright playwright;
	
	@BeforeMethod
	@Parameters({"Browser"})
	public void setup(@Optional("Chrome") String browserName) {
		BrowserType browserType;
		
		playwright = Playwright.create();
		switch(browserName) {
		case "Chrome":
			browserType = playwright.chromium();
			break;
		case "Firefox":
			browserType = playwright.firefox();
			break;
		case "WebKit":
			browserType = playwright.webkit();
			break;
		default:
			throw new IllegalArgumentException("Please provide valid browsertype");	
		}
		
		browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(true));
		page = browser.newPage();
	}
	
	@Test()	
	public void testLogin() {
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");		
		page.locator("//input[@name='username']").fill("Admin");
		
		page.locator("//input[@name='password']").fill("admin123");
		page.locator("//button[@type=\"submit\"]").click();
	
		page.locator("//span[@class='oxd-userdropdown-tab']").click();
		page.locator("//a[text()='Logout']").click();
	}
	
	@AfterMethod
	public void tearDown() {
		browser.close();
		page.close();
		playwright.close();
	}

}
