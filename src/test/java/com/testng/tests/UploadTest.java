package com.testng.tests;
import java.io.File;
import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class UploadTest {

	public static void main(String[] args) {
		Playwright playWright = Playwright.create();
		Browser browser = playWright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		
		page.navigate("https://cgi-lib.berkeley.edu/ex/fup.html");
		
		String path =  System.getProperty("user.dir");
		
		//page.locator("[name='upfile']");
		page.setInputFiles("[name='upfile']", Paths.get(path + File.separator + "Dashboard.png"));
		
	}
}
