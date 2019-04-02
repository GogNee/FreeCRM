package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBaseClass;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBaseClass {
	
	
	LoginPage loginpage;
	HomePage homepage;
	ContactsPage contactspage;
	
	public ContactsPageTest() {
		super(); // it calls the constructor of super class for iniitialising 'properties' file
	}
	
	@BeforeMethod
	
	public void setUp() {
		initialization();
		loginpage= new LoginPage();
		contactspage = new ContactsPage();
		homepage=loginpage.login3(prop.getProperty("username"),prop.getProperty("password"));
		TestUtil testutil1=new TestUtil();
		testutil1.switchToFrame("mainpanel");
		contactspage=homepage.clickContactsLink();
		
	}
	
	@Test(priority=1)
	public void verifyContactsLabelTest() {
		Boolean ContactLabelFlag= contactspage.verifyContactlable();
		Assert.assertTrue(ContactLabelFlag);
		
	}
	
	@Test(priority=2)
	public void verifyContactNamelistTest() {
		contactspage.verifyContactsSelectbyUsername("Mamta Kulkarni");
		contactspage.verifyContactsSelectbyUsername("Ketan shah");
	}
	
	@DataProvider(name = "getCRMTestData")
	public Object[][] getCRMTestData() {
		Object[][] data = TestUtil.getTestData("contacts");
		return data;
	}
	
	
	@Test(priority=4, dataProvider= "getCRMTestData")
	public void createNewContactTest(String title, String FN, String LN, String Company) {
		homepage.ClickNewConatctslink();
		contactspage.createNewContact(title, FN,LN, Company);
	}
	@AfterMethod
	
	public void tearDOwn() {
		driver.quit();
	}
	
	
	
	
	
	
	
	

}
