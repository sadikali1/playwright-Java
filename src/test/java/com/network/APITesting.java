package com.network;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class APITesting {

	private Playwright playwright;
	private APIRequestContext request;

	void createPlaywright() {
		playwright = Playwright.create();
	}

	void createRequest() {
		request = playwright.request()
				.newContext(new APIRequest.NewContextOptions().setBaseURL("https://petstore.swagger.io"));

	}

	@BeforeClass
	public void beforeAll() {
		createPlaywright();
		createRequest();
	}

	@Test
	public void getpets() {

		  APIResponse response = request.get("/v2/pet/findByStatus?status=pending");
		  System.out.println(response.ok());
		  System.out.println(response.statusText());
		  System.out.println(response.text());
		  System.out.println(response.url());		
	}

	
	@Test
	public void getXmlResponse() {
		Map<String, String> headers = new HashMap<>();	   
	    headers.put("Accept", "application/xml");
	    request = playwright.request().newContext(new APIRequest.NewContextOptions()	    	      
	    	      .setBaseURL("https://petstore.swagger.io")
	    	      .setExtraHTTPHeaders(headers));
	    
		  APIResponse response = request.get("/v2/pet/findByStatus?status=pending");
		  
		  System.out.println(response.ok());
		  System.out.println(response.statusText());
		  System.out.println(response.text());
		  System.out.println(response.url());		
	}

	@Test
	public void getPost() {
		String json = "{\r\n"
				+ "  \"id\": 0,\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 0,\r\n"
				+ "    \"name\": \"string\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"doggie\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"string\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 0,\r\n"
				+ "      \"name\": \"string\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \"available\"\r\n"
				+ "}";
		Map<String, String> headers = new HashMap<>();	   
	    headers.put("Accept", "application/json");
	    headers.put("Content-Type", "application/json");
	    request = playwright.request().newContext(new APIRequest.NewContextOptions()	    	      
	    	      .setBaseURL("https://petstore.swagger.io")
	    	      .setExtraHTTPHeaders(headers));
	    
		  APIResponse response = request.post("/v2/pet", RequestOptions.create().setData(json));
		  System.out.println(response.ok());
		  System.out.println(response.statusText());
		  System.out.println(response.text());
		  System.out.println(response.url());
		  
		  JsonObject jsonObj = new Gson().fromJson(response.text(), JsonObject.class);
		  System.out.println(jsonObj.get("id"));		   
	}
	
	@Test
	public void testPostWithForm() {
		Map<String, String> data = new HashMap<>();
	    data.put("name", "Test1");
	    data.put("status", "Pending");
	    
		Map<String, String> headers = new HashMap<>();	   
	    headers.put("Accept", "application/json");
	    headers.put("Content-Type", "application/x-www-form-urlencoded");
	    
	    request = playwright.request().newContext(new APIRequest.NewContextOptions()	    	      
	    	      .setBaseURL("https://petstore.swagger.io")
	    	      .setExtraHTTPHeaders(headers));
	    
		  APIResponse response = request.post("/v2/pet/9223372036854245000", RequestOptions.create().setData(data));
		  System.out.println(response.text());
	}
	
	
	@Test
	public void getPut() {
		String json = "{"
				+ "  \"id\": 0,"
				+ "  \"category\": {"
				+ "    \"id\": 0,"
				+ "    \"name\": \"string\""
				+ "  },"
				+ "  \"name\": \"doggie\","
				+ "  \"photoUrls\": ["
				+ "    \"string\""
				+ "  ],"
				+ "  \"tags\": ["
				+ "    {"
				+ "      \"id\": 0,"
				+ "      \"name\": \"string\""
				+ "    }"
				+ "  ],"
				+ "  \"status\": \"available\""
				+ "}";
		
		Map<String, String> headers = new HashMap<>();	   
	    headers.put("Accept", "application/json");
	    headers.put("Content-Type", "application/json");
	    request = playwright.request().newContext(new APIRequest.NewContextOptions()	    	      
	    	      .setBaseURL("https://petstore.swagger.io")
	    	      .setExtraHTTPHeaders(headers));
	    
		  APIResponse response = request.put("/v2/pet", RequestOptions.create().setData(json));
		  System.out.println(response.ok());
		  System.out.println(response.statusText());
		  System.out.println(response.text());
		  System.out.println(response.url());
		  
		  JsonObject jsonObj = new Gson().fromJson(response.text(), JsonObject.class);
		  System.out.println(jsonObj.get("id"));		   
	}
	@AfterClass
	void afterAll() {
		disposeAPIRequestContext();
		closePlaywright();
	}

	void disposeAPIRequestContext() {
		if (request != null) {
			request.dispose();
			request = null;
		}
	}

	void closePlaywright() {
		if (playwright != null) {
			playwright.close();
			playwright = null;
		}
	}

}
