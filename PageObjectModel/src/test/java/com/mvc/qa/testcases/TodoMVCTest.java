package com.mvc.qa.testcases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mvc.qa.base.TestBase;
import com.mvc.qa.pages.TodoMVC;

public class TodoMVCTest extends TestBase{
	TodoMVC mvc;
	static JavascriptExecutor js;
	
	public TodoMVCTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		mvc = new TodoMVC();	
	}
	
	/// Test Case 1 --- Clicking on Demo Link *************
	
	
	@Test(priority=1)
	public void ClickingonDemoLink1(){
		mvc.ClickingonDemoLink();
	}
	
	
	//  Test Case 2 --- Validating Page Title and Taking ScreenShot ********************
	
	@Test(priority=2)
	public void mvcTitleTest() throws InterruptedException, IOException {
		String title = driver.getTitle();
		System.out.println("title is: " + title);
		getRunTimeInfoMessage("info", title);

		if (title.equals("AngularJS • TodoMVC")) {
			getRunTimeInfoMessage("info", "title is correct!! YAY!!!");
			takeScreenshot("Title");
			Assert.assertTrue(true);
		} else {
			getRunTimeInfoMessage("error", "title is not correct!! BUG BUG BUG!!!");
			Assert.assertTrue(false);
		}

	}

	public static void getRunTimeInfoMessage(String messageType, String message) throws InterruptedException {
		// Check for jQuery on the page, add it if need be
		js.executeScript("if (!window.jQuery) {"
				+ "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
				+ "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
				+ "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
		Thread.sleep(5000);

		// Use jQuery to add jquery-growl to the page
		js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

		// js.executeScript("$.getScript('/Users/NaveenKhunteta/Documents/workspace/Test/src/testcases/jquery.growl.js')");

		// Use jQuery to add jquery-growl styles to the page
		js.executeScript("$('head').append('<link rel=\"stylesheet\" "
				+ "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
		Thread.sleep(5000);

		// jquery-growl w/ no frills
		js.executeScript("$.growl({ title: 'GET', message: '/' });");
		
		if(messageType.equals("error")){
			js.executeScript("$.growl.error({ title: 'ERROR', message: '"+message+"' });");
		}else if(messageType.equals("info")){
			js.executeScript("$.growl.notice({ title: 'Notice', message: '"+message+"' });");
		}else if(messageType.equals("warning")){
			js.executeScript("$.growl.warning({ title: 'Warning!', message: '"+message+"' });");
		}

		// jquery-growl w/ colorized output
//		js.executeScript("$.growl.error({ title: 'ERROR', message: 'Some exception is coming' });");
//		js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
//		js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
	}
	
	public static void takeScreenshot(String fileName) throws IOException{
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// now copy the screenshot to desired location using copyFile //method
		FileUtils.copyFile(src, 
				new File("C:\\Users\\user\\git\\PageObjectModel\\screenshots" + fileName +".png"));

	}
	
	
	// ******* Test Case 3 -- Validating Specific Text from the Webpage **********
	
	@Test(priority=3)
	public void HtmlDataTest(){
		mvc.ValidateHtmlTextData();
	
	}
	
	// ******* Test Case 4 -- Validating FAQ Text **********
	@Test(priority=4)
	public void FAQTest(){
		boolean flag=mvc.ValidateFAQText();
		Assert.assertTrue(flag);
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	

}
