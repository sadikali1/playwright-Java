package com.network;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class ExampleAPI {

	  private Playwright playwright;
	  private APIRequestContext request;
	  
	  @BeforeClass
	  public void setup() {
		  Map<String, String> headers = new HashMap<>();
		  headers.put("accept", "application/json");
		  headers.put("Content-Type", "application/json");
		  
		  playwright = Playwright.create();
		  request = playwright.request().newContext(new APIRequest.NewContextOptions()
				  .setBaseURL("https://petstore.swagger.io").setExtraHTTPHeaders(headers));
	  }
	  
	  @Test
	  public void getPetByStatus() {
		  APIResponse response = request.get("/v2/pet/findByStatus?status=sold");
		  System.out.println(response.ok());
		  System.out.println(response.statusText());
		  System.out.println(response.text());
		  System.out.println(response.status());	
		  Assert.assertEquals(response.ok(), true);
		  Assert.assertEquals(response.statusText(), "OK");
		  Assert.assertEquals(response.status(), 200);		  
	  }
	  
	  @Test
	  public void addPet() {
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
		  
		 APIResponse response =  request.post("v2/pet",  RequestOptions.create().setData(json));
		 System.out.println(response.text());
	  }
	  
	  @Test
	  public void updatePet() {
		  String json = "{"
		  		+ "  \"id\": 9223372036854251824,"
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
		  
		 APIResponse response =  request.put("v2/pet",  RequestOptions.create().setData(json));
		 System.out.println(response.text());
	  }
	  
	  @Test
	  public void deletePet() {
		  APIResponse response =  request.delete("v2/pet/9223372036854251824");
		  System.out.println(response.text());
		  System.out.println(response.statusText());
	  }
	  
	  
	  @Test
	  public void updatePetsByForm() {
		   Map<String, String> data = new HashMap<>();
		    data.put("name", "Dog");
		    data.put("status", "sold");
		  
		    Map<String, String> headers = new HashMap<>();
			  headers.put("accept", "application/json");
			  headers.put("Content-Type", "application/x-www-form-urlencoded");
			  
			 APIRequestContext request = playwright.request().newContext(new APIRequest.NewContextOptions()
					  .setBaseURL("https://petstore.swagger.io").setExtraHTTPHeaders(headers));
		    
		  APIResponse response =  request.post("v2/pet/9223372036854251921", RequestOptions.create().setData(data));
		  System.out.println(response.text());
		  System.out.println(response.statusText());
	  }
}
