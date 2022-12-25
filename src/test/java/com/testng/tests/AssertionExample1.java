package com.testng.tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class AssertionExample1 {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext();
		Page page = context.newPage();
		/*
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		page.locator("[name='username']").fill("admin");
		page.locator("[name='password']").fill("admin123");
		
		assertThat(page.locator("[class='orangehrm-login-error'] > div > p:nth-child(1)")).hasText("Username : Admin");
		assertThat(page.locator("[class='orangehrm-login-error'] > div > p:nth-child(1)")).containsText("Admin");
		assertThat(page.locator("[class='orangehrm-login-error'] > div > p:nth-child(1)")).not().containsText("Admin");
		assertThat(page.locator("[class='orangehrm-login-error'] > div > p:nth-child(1)")).not().hasText("Admin");
		*/
		//page.navigate("https://the-internet.herokuapp.com/checkboxes");
		//assertThat(page.locator("[id='checkboxes'] input:nth-of-type(2)")).isChecked();
		//assertThat(page.locator("[id='checkboxes'] input:nth-of-type(1)")).isChecked();
		
		//page.navigate("https://the-internet.herokuapp.com/context_menu");
		//assertThat(page.locator("#hot-spot")).isEmpty();
		//assertThat(page.locator("//p[1]")).isEmpty();
		//assertThat(page.locator("#hot-spot")).not().isEmpty();
		
		
		playwright.close();
	}
}
