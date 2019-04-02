package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBaseClass;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBaseClass{
	 LoginPage loginpage;
	 HomePage homepage;
	
	public LoginPageTest() {
		super(); // it calls the constructor of super class for iniitialising 'properties' file
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		 loginpage = new LoginPage();
		}
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		
		String title= loginpage.validateLoginPageTitle();
		Assert.assertEquals(title,"#1 Free CRM software in the cloud for sales and service")  ;    
	}
	
	@Test(priority=2)
	public void CRMlogoImageTest() {
		boolean imageFlag=loginpage.CRMimage();
		Assert.assertTrue(imageFlag);
		
	}
	
	@Test(priority=3)
	public void LoginFuncTest() {
		
		String Usrnm= prop.getProperty("username");
		String Pswrd=prop.getProperty("password");
  	 	homepage=loginpage.login3(Usrnm,Pswrd);
		//homepage=loginpage.login("Nakshu","Icanhack00");
		//homepage=new HomePage();
		

		}
	
	
	@AfterMethod
	
	public void tearDOwn() {
		driver.quit();
	}
}
