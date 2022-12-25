package com.testng.tests;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CaptureSession {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext();
		Page page = context.newPage();
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		page.locator("[name='username']").fill("admin");
		page.locator("[name='password']").fill("admin123");
		
		assertThat(page.locator("[class='orangehrm-login-error'] > div > p:nth-child(1)")).hasText("Username : Admin");
		assertThat(page.locator("[class='orangehrm-login-error'] > div > p:nth-child(1)")).containsText("Admin");
		assertThat(page.locator("[class='orangehrm-login-error'] > div > p:nth-child(1)")).not().containsText("Admin");
		assertThat(page.locator("[class='orangehrm-login-error'] > div > p:nth-child(1)")).not().hasText("Admin");
		
		
		page.locator("[type='submit']").click();
		context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("auth.json")));
		
		playwright.close();
	}
}
