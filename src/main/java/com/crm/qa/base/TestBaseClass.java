package com.crm.qa.base;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;


public class TestBaseClass {

	// define all the properties here like browser launch, window settings etc
	// TestBase class is the parent class of all the page classes, they can inherit properties from the base class
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public  TestBaseClass(){
		
		try {
			
			prop= new Properties();
			FileInputStream ip=new FileInputStream("C:\\Eclipse-JEE\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);	
			
		}catch(FileNotFoundException e) {
			e.printStackTrace(); 
			
		}catch(IOException e) {
		
			e.printStackTrace();
		}
	}
	
	public static void initialization() {
		
		String browserName=prop.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\Eclipse-JEE\\webdrivers\\chromedriver.exe" );
			driver= new ChromeDriver();
		}else if(browserName.equals("FF"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\Eclipse-JEE\\webdrivers\\geckodriver.exe");
			driver=new FirefoxDriver();
	    }else if(browserName.equals("IE"))
		{
	    	System.setProperty("webdriver.edge.driver","C:\\Eclipse-JEE\\webdrivers\\MicrosoftWebDriver.exe"); //put actual location
        	driver = new EdgeDriver();
	    }else if(browserName.equals("Opera"))
		{
	    	System.setProperty("webdriver.opera.driver","C:\\Eclipse-JEE\\webdrivers\\operadriver.exe"); //put actual location
        	driver = new OperaDriver();
		
		}
		
		
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		//dynamic wait
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		String urlnew=prop.getProperty("url");
		driver.get(urlnew);
		
		}
	
}

