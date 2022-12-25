package com.testng.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;

public class ParallelTest extends Thread {

	String browserName;
	
	public ParallelTest(String browserName){
		this.browserName = browserName;
	}
	
	public void run() {
		System.out.println("Thread Running....");
		Playwright playwright = Playwright.create();
		Browser browser = getBrowser(playwright, browserName).launch(new BrowserType.LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		page.locator("//input[@name='username']").fill("Admin");
		page.locator("//input[@name='password']").fill("admin123");
		page.locator("//button[@type=\"submit\"]").click();
		page.locator("//span[@class='oxd-userdropdown-tab']").click();
		page.locator("//a[text()='Logout']").click();
		page.close();
		browser.close();
		playwright.close();		
	}
	
	
	public static void main(String[] args) {
		Thread th1 = new ParallelTest("Chrome");
		Thread th2 = new ParallelTest("Firefox");
		Thread th3 = new ParallelTest("WebKit");
		th1.start();
		th2.start();
		th3.start();
	}

	
	public static BrowserType getBrowser(Playwright playwright, String browserName) {
		BrowserType browserType;
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
			throw new IllegalArgumentException("Please provide valid browser name");
		}
		return browserType;
	}
}
