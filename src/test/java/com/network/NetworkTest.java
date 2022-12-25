package com.network;

import java.util.HashMap;
import java.util.Map;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Route;

public class NetworkTest {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext();

		// Open new page
		Page page = context.newPage();

		// Delete header
		page.route("**/*", route -> {
			Map<String, String> headers = new HashMap<>(route.request().headers());
			headers.putIfAbsent("MyHeader", "Sadik Header name");		
			route.resume(new Route.ResumeOptions().setHeaders(headers));
		});
		// Continue requests as POST.
		//page.route("**/*", route -> route.resume(new Route.ResumeOptions().setMethod("Post")));
		
		page.navigate("https://www.toyota.com/");

	}
}
