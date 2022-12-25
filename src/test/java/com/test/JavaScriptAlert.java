package com.test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class JavaScriptAlert {

	public static void main(String[] args) {
		Playwright playWright = Playwright.create();
		Browser browser = playWright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		page.navigate("https://the-internet.herokuapp.com/javascript_alerts");
		
		page.onDialog(dialog -> {
			dialog.accept("Testing");
			System.out.println(dialog.message());
		});
		
		page.locator("text=Click for JS Alert").click();
		page.locator("text=Click for JS Confirm").click();
		
		page.locator("text=Click for JS Prompt").click();
	}

}
