package com.testng.tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Videos {

	private Browser browser;
	private Page page;
	private BrowserContext context;
	

	@BeforeMethod
	public void setup() {
		Playwright playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));	
		
		context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos/")));
		page = context.newPage();
	}

	@Test
	public void testExample() {
		// Go to https://playwright.dev/
		page.navigate("https://playwright.dev/");

		// Click text=Get started
		page.locator("text=Get started").click();
		assertThat(page).hasURL("https://playwright.dev/docs/intro");

		// Click aside >> text=Writing Tests
		page.locator("aside >> text=Writing Tests").click();
		assertThat(page).hasURL("https://playwright.dev/docs/writing-tests");

		// Click aside >> text=Running Tests
		page.locator("aside >> text=Running Tests").click();
		assertThat(page).hasURL("https://playwright.dev/docs/running-tests");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		String pathProject = System.getProperty("user.dir");
		String testName = result.getMethod().getMethodName();
		Path videoName = page.video().path().getFileName();
		
		System.out.println(videoName);
		browser.close();
		context.close();
		page.close();
		
		File file1 = new File(pathProject + File.separator + "videos"+File.separator + videoName);
		File file2 = new File(pathProject + File.separator + "videos"+File.separator + testName + ".webm");
		boolean status = file1.renameTo(file2);
		System.out.println(status);
	}
}
