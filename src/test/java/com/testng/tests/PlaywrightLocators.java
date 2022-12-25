package com.testng.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightLocators {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext();
		Page page = context.newPage();
		
		//page.navigate("https://www.facebook.com/");
		//page.locator("text=Create New Account").click();
		//page.locator("text=Forgotten password?").click();
		//page.locator("text=Search").last().click();		
		//page.getByText("Create New Account").click();
		
		//page.getByTestId("open-registration-form-button").click();
		
		//page.getByPlaceholder("Email address or phone number").fill("Testing");
		//page.getByPlaceholder("Password").fill("Testing");
		
		//page.getByTitle("Sign up for Facebook").click();
		//page.getByTitle("Log in to Facebook").click();
		
		//String src = page.getByAltText("Facebook").getAttribute("src");
		//System.out.println(src);
		page.navigate("https://the-internet.herokuapp.com/login");
		page.getByLabel("Username").fill("Testing");
		page.getByLabel("Password").fill("Testing");
		
	}

}
