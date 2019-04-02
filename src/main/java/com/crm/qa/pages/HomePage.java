package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBaseClass; 

public class HomePage extends TestBaseClass{
	
	
	//Page Factory  
	
	 // User: Nakshtra Singh 
	
	
	@FindBy(xpath="//td[contains(text(),'User: Nakshtra Singh')]")
	WebElement UsernameLable;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement ContactsLink;
	
	@FindBy(xpath="//a[text()='New Contact']")
	WebElement NewContactLink;

	@FindBy(xpath="//a[@title='Deals']")
	WebElement DealsLink;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement TasksLink;
	
	public HomePage() {
		
		PageFactory.initElements(driver, this);
		
	}
	
	public String verifyHomePageTitle() {
		return  driver.getTitle();
		
	}
	
	public boolean verifyCorrectUsername() {
		
		return UsernameLable.isDisplayed();
	}
	
	public ContactsPage clickContactsLink() {
		ContactsLink.click();
		return new ContactsPage();
		
	}
	
	public ContactsPage ClickNewConatctslink() {
		
		Actions action= new Actions(driver);
		action.moveToElement(ContactsLink).build().perform();
		NewContactLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickDealsLink() {
		DealsLink.click();
		return new DealsPage();
	}
		public TasksPage clickTasksLink() {
			TasksLink.click();
			return new TasksPage();
	
	}
		
		
}


