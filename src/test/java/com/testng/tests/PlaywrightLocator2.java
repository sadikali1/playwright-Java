package com.testng.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightLocator2 {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		//BrowserContext context = browser.newContext();
		Page page = browser.newPage();
		
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		page.locator("css=input[name='username']").fill("admin");
		page.locator("css=input[name='password']").fill("admin123");
		page.locator("css=button[class*='orangehrm-login-button']").click();
		playwright.close();
	}
}
