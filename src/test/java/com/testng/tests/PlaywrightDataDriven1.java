package com.testng.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PlaywrightDataDriven1 {
		
	private Playwright playwright;
	private Page page;
	
	@BeforeClass
	public void setup() {
		playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext();
		page  = context.newPage();
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = MyDataProvider.class)
	public void testSearchCountry(String country, String result) throws InterruptedException {	
		
		page.navigate("https://en.wikipedia.org/wiki/Main_Page");
		page.locator("[name='search']").fill(country);
		page.locator("[id='searchButton']").click();
		Thread.sleep(2000);
		assertThat(page.locator("[class='mw-page-title-main']")).containsText(result);				
	}
	
	@AfterClass
	public void tearDown() {
		playwright.close();	
	}
	
}
