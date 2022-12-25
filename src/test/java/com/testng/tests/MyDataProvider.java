package com.testng.tests;

import java.io.File;
import java.io.FileReader;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class MyDataProvider {

	@DataProvider(name="TestData")
	public Object[][] getData(){
		Object[][] obj = new Object[][] {
			{"India", "India"},
			{"US", "United States"},
			{"UK", "United Kingdom"},
			{"UAE", "United Arab Emirates"}		
		};
		return obj;
	}
	
	
	public void readJSON() {	
		Gson gson = new Gson();
		//JsonReader reader = new JsonReader(new FileReader("testdata" + File.separator + "testdata.json"));
		//Review data = gson.fromJson(reader, Review.class);
		//data.toScreen(); // prints to screen some values
	}
}
