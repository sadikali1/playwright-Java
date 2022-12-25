package com.testng.tests;

import java.nio.file.Paths;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Scenario1 {
	
	Browser browser;
	Page page;
	Playwright playwright;
	
	@BeforeSuite
	public void storeSession() {
	
		playwright = Playwright.create();		
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext(); 
		
		Page page = context.newPage();
		page.navigate("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		page.locator("//input[@id='email']").fill("roadtoautomation@gmail.com");		
		page.locator("//input[@id='passwd']").fill("Testing@123");
		page.locator("//button[@id='SubmitLogin']").click();	
		//page.locator("a[class='logout']").click();
		context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("state.json")));
	
		
	}
	
	
	@Test
	public void testLogin() throws InterruptedException {
		playwright = Playwright.create();		
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext(
				  new Browser.NewContextOptions().setStorageStatePath(Paths.get("state.json")));	
		page = context.newPage();
		page.navigate("http://automationpractice.com");
		Thread.sleep(3000);
		
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {		
		//browser.close();
		//page.close();
		//playwright.close();
	}

	
	public void login(Page page) {
		
	}

}
