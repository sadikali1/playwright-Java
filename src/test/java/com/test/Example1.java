package com.test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class Example1 {

	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			context.tracing().start(new Tracing.StartOptions()
					.setScreenshots(true)
					.setSnapshots(true).setSources(true));
			
			// Open new page
			Page page = context.newPage();
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
			
			context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("Trace.zip")));

		}
	}
}
