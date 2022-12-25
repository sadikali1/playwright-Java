package com.testng.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserExample {
	
	public static void main(String[] args) {
		
		Playwright playwright = Playwright.create();
		//BrowserType browserType = playwright.chromium();
		//BrowserType browserType = playwright.firefox();
		BrowserType browserType = playwright.webkit();
		Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		
	}
}


// Chromium -- Chrome, Edge
// Firefox
// Webkit -- Safari

// Chromium -- N+1th then Chrome and Edge -- nth