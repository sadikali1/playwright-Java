package com.test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlayWrightTest {
	
	public static void main(String[] args) {

		  String dir = System.getProperty("user.dir");
		  
	    try (Playwright playwright = Playwright.create()) {
	      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	      BrowserContext context = browser.newContext(new Browser.NewContextOptions()
	    		  .setRecordVideoDir(Paths.get("videos/")).setRecordVideoSize(640, 480));
			
	      Page page = context.newPage();
	     
	      page.navigate("http://playwright.dev");
	      System.out.println(page.title());
	      
	      Path path = page.video().path();
	      page.close();
	      context.close();
	      System.out.println(path.getFileName());
	      System.out.println(dir+ File.separator + "videos" +File.separator + path.getFileName());
	      System.out.println(dir+ File.separator + "videos" +File.separator +  "mytest.webm");
	      
	      File file = new File(dir+ File.separator + "videos" +File.separator + path.getFileName());
	      File file2 = new File(dir+ File.separator + "videos" +File.separator +  "mytest.webm");
	      boolean success = file.renameTo(file2);
	      System.out.println(success);
	    }
	   
	
	}
}
