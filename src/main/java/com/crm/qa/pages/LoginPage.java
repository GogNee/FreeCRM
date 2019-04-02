package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBaseClass;


public class LoginPage extends TestBaseClass {

	
	// Page Factory - object Repo
	
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement Loginbtn;
	
	@FindBy(linkText="Sign Up")
	WebElement SignUp;
	
	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	WebElement CRMlogo;
	
	//Initialising my page objects
	public LoginPage() {
		
		PageFactory.initElements(driver, this);// this is current class object i.e LoginPage.class
		//PageFactory.initElements(driver, LoginPage.class);
	}
	
	
	//Actions
	public String validateLoginPageTitle() {
		return driver.getTitle(); 
	}
	
	public boolean CRMimage() {
		return CRMlogo.isDisplayed();
		
	}
	
	public HomePage login(String UN, String pw) {
		username.sendKeys(UN);
		password.sendKeys(pw);
		Loginbtn.click();
		return new HomePage();
//		
	
	}
	
	public HomePage login1(String UN, String pw) {
		try {
		username.sendKeys(UN);
		password.sendKeys(pw);
 
		Loginbtn.sendKeys(" ");// to avoid element overlay , the below commented code is not working
		Loginbtn.click();
		} catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		catch(NullPointerException e) {
		e.printStackTrace();
		}
		return new HomePage();
	
	}
	
	public HomePage login2(String UN, String pw)  {
		
	try {
	username.sendKeys(UN);
	password.sendKeys(pw);
 
	new WebDriverWait(driver, 20).ignoring(org.openqa.selenium.StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(Loginbtn));
//	Thread.sleep(2000);
//	myWaitVar.until(ExpectedConditions.elementToBeClickable (Loginbtn));
//	Loginbtn.sendKeys(" ");
	Actions actions = new Actions(driver);
 	actions.moveToElement(Loginbtn).click().build().perform();
 	Loginbtn.click();
	
	} catch(NoSuchElementException e) {
	e.printStackTrace();
	}
	catch(NullPointerException e) {
	e.printStackTrace();
	}

		return new HomePage();
	}

	public HomePage login3(String UN, String pw)  {
		
	
		username.sendKeys(UN);
		password.sendKeys(pw);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", Loginbtn);
		
		return new HomePage();
		
}
	
	
}