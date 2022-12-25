package com.testng.tests;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FrameHandle {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext();
		Page page = context.newPage();
		page.navigate("https://the-internet.herokuapp.com/nested_frames");
		
		Frame frame = page.frame("frame-top");
		List<Frame> frames = frame.childFrames();
		String text1 = frames.get(0).locator("body").innerText();
		System.out.println(text1);
		
		String text2 = frames.get(1).locator("body").innerText();
		System.out.println(text2);
		
		String text3 = frames.get(2).locator("body").innerText();
		System.out.println(text3);
		/*
		String  text = page.locator("h3").innerText();
		System.out.println(text);
		
		Frame frame = page.frame("mce_0_ifr");
		
		String  text1 = frame.locator("[id='tinymce'] p").innerText();
		System.out.println(text1);  */
		
	}

}
