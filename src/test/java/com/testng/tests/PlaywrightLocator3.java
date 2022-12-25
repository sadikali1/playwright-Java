package com.testng.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightLocator3 {
	
	public static void main(String[] args) throws InterruptedException {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext();
		Page page = context.newPage();
		
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		//page.locator("//input[@name='username']").fill("admin");
		//page.locator("//*[@name='username'] | //*[@placeholder='Username']").fill("admin");
		//page.locator("//input[@name='password']").fill("admin123");
		//page.locator("//button[@type=\"submit\"]").click();
	
		page.locator("input >> nth=-2").fill("admin");
		page.locator("input >> nth=-1").fill("admin123");
		page.locator("button >> nth=0").click();
		page.locator("[class=\"oxd-main-menu-item\"] >> nth=0").click();
		Thread.sleep(5000);
		Locator userColumn = page.locator("//div[@class='oxd-table-body']/div/div/div[2]/div");
		System.out.println(userColumn.count());
		/*
		for(int i=0; i<userColumn.count(); i++) {
			String userName = userColumn.nth(i).textContent();
			System.out.println(userName);
		}*/
		
		Object listUser = userColumn.evaluateAll("list => list.map(element => element.textContent)");
		System.out.println(listUser);
		
	}
}
