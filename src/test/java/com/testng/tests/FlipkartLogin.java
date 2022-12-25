package com.testng.tests;

import java.nio.file.Paths;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FlipkartLogin {
	Browser browser;
	Page page;
	Playwright playwright;
	BrowserContext context;

	@BeforeMethod
	@Parameters({ "Browser" })
	public void setup(@Optional("Chrome") String browserName) {		
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		context = browser.newContext();
		page = context.newPage();
	}

	@Test
	public void testLogin() {
		page.navigate("https://www.flipkart.com/");
		page.locator("//form[@autocomplete=\"on\"]/div[1]/input").fill("*************");

		page.locator("//form[@autocomplete=\"on\"]/div[2]/input[@type=\"password\"]").fill("*************");
		page.locator("//form[@autocomplete=\"on\"]/div[4]/button").click();
		page.screenshot();
		context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("state.json")));

	}

	@AfterMethod
	public void tearDown() {
		browser.close();
		page.close();
		playwright.close();
	}

}
