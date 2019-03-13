package com.mvc.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mvc.qa.base.TestBase;

public class TodoMVC extends TestBase{
	
	//Page Objects - OR:
	
	@FindBy(xpath="//a[contains(@href,'http://todomvc.com/examples/angular2')]")
	WebElement dlink;
	
	@FindBy(xpath="/html/body/aside/blockquote/p")
	WebElement HtmlText;
	
	@FindBy(xpath="/html/body/aside/ul[1]/li[6]/a")
	WebElement faq;
	
	
	//Initializing the Page Objects:
	public TodoMVC(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	public void ValidateHtmlTextData(){
		String s=HtmlText.getText();
		Assert.assertTrue(s.contains("HTML is great for declaring static"));
	}
	
	public boolean ValidateFAQText(){
		return faq.isDisplayed();
		
	}
	
	public void ClickingonDemoLink(){
		dlink.click();
	}
	
	
	
	
	
	
}
