package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBaseClass;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBaseClass {

	
	HomePage homepage;
	LoginPage loginpage;
	TestUtil testutil1;
	ContactsPage contactsPageObj;
	
	public HomePageTest() {
		super();
	}
	// Test case should be as independent with each other as possible.
	// execute @Test test case
	//before each test case launch the browser and login and after each test case, close the browser.

	
	@BeforeMethod
	public void setUp() {
	 initialization();
	 loginpage= new LoginPage();
	 testutil1= new TestUtil();
	 contactsPageObj = new ContactsPage();
	 homepage= loginpage.login3("Nakshu", "Icanhack00");
	
	}
	
	@Test(priority=1)
	public void verifyCorrectUsernameTest() {
		testutil1.switchToFrame("mainpanel");
		boolean UNflag=homepage.verifyCorrectUsername();
		Assert.assertTrue(UNflag);
	}
	
	@Test(priority=3)
	public void verifyContactlinkTest() {
		testutil1.switchToFrame("mainpanel");
		contactsPageObj = homepage.clickContactsLink();
	}
	
	@Test(priority=2)
	public void verifyHomePageTitle() {
		
		String titleHP= homepage.verifyHomePageTitle();
		Assert.assertEquals(titleHP, "CRMPRO","HomePageTitle Not Matched");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
