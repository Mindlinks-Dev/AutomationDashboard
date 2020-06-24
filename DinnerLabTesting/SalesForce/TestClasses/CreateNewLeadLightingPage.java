package com.SalesForce.TestClasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.SalesForce.TestPages.CreateNewLeadLightingTestPage;
import com.SalesForce.TestPages.LoginLogoutTestpage;



public class CreateNewLeadLightingPage {

	private static Logger log = Logger.getLogger(CreateNewLeadLightingPage.class);
	public WebDriver driver;
	 
	@Test 
	public void LeadTest() throws Exception
	 {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver","D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\chromedriver.exe");
		WebDriver driver=new ChromeDriver(options);
	    driver.manage().window().maximize();
	     log.info("open");
	     
	     LoginLogoutTestpage login = new LoginLogoutTestpage();
	     login.loginInitialPage(driver);
		
	      Thread.sleep(2000);
	      CreateNewLeadLightingTestPage Lead = new CreateNewLeadLightingTestPage();
			Lead.CreateNewLeadLightingTestPageInitialPage(driver);
		}

}
