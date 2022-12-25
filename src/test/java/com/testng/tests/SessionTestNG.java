package com.testng.tests;

import java.nio.file.Paths;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SessionTestNG {
	
	Playwright playwright ;
	BrowserContext context ;
	Page page;
	
	@BeforeSuite
	public void sessionCapture() {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext();
		Page page = context.newPage();
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		page.locator("[name='username']").fill("admin");
		page.locator("[name='password']").fill("admin123");
		page.locator("[type='submit']").click();
		context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("auth.json")));
		
		playwright.close();
	}
	
	@BeforeMethod
	public void method() {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("auth.json")));
		page = context.newPage();		
	}
	
	@Test()
	public void test1() {
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		page.locator("//span[text()='Leave']").click();
	}
	
	@Test()
	public void test2() {
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		page.locator("[href='/web/index.php/admin/viewAdminModule']").click();
	}

}
