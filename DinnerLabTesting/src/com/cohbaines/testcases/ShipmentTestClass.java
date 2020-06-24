package com.cohbaines.testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.cohbaines.testpages.LoginLogoutTestpage;
import com.cohbaines.testpages.ShipmentTestPage;




public class ShipmentTestClass {
	private static Logger log = Logger.getLogger(ShipmentTestClass.class);
	public WebDriver driver;
	 
	@Test 
	public void ShipmentTestClass() throws Exception
	 {
		System.setProperty("webdriver.chrome.driver","./software/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
	    driver.manage().window().maximize();
	     log.info("open");
	     LoginLogoutTestpage login = new LoginLogoutTestpage();
	     login.loginInitialPage(driver);
	     
	     ShipmentTestPage Shipment=new ShipmentTestPage();
	      Shipment.ShipmentInitialPage(driver);
		
	      Thread.sleep(2000);
	    
	      
	      
	     
		}

}
