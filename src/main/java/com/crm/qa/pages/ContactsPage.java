package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBaseClass;

public class ContactsPage extends TestBaseClass{
	
	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement ContactsLable;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="surname")
	WebElement lastName;
	
	@FindBy(name="client_lookup")
	WebElement company;
	
	@FindBy(xpath="//input[@type='submit' and @value='Save']")
	WebElement saveBtn;
	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactlable() {
		
		return ContactsLable.isDisplayed();
	}
	
	
	public void verifyContactsSelectbyUsername(String name) {
		
		driver.findElement(By.xpath("//a[(text()='"+name+"')]//parent::td[@class='datalistrow']//"
				+ "preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
	}

	
	public void createNewContact(String title,String FN, String LN, String companyName) {
		Select select=new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);
		firstName.sendKeys(FN);
		lastName.sendKeys(LN);
		company.sendKeys(companyName);
		saveBtn.click();		
		
	}
}
