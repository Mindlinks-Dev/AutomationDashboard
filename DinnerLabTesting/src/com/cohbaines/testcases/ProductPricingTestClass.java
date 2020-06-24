package com.cohbaines.testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.cohbaines.testpages.LoginLogoutTestpage;
import com.cohbaines.testpages.ProductPricingTestpage;





public class ProductPricingTestClass {
	
	
		private static Logger log = Logger.getLogger(ProductPricingTestClass.class);
		public WebDriver driver;
		 
		@Test 
		public void AccountTestClass() throws Exception
		 {
			System.setProperty("webdriver.chrome.driver","E:/DinnerLab_WorkSpace/DinnerLabAutomationDashboard/DinnerLabTesting/src/chromedriver.exe");
			WebDriver driver=new ChromeDriver();
		    driver.manage().window().maximize();
		     log.info("open");
		     LoginLogoutTestpage login = new LoginLogoutTestpage();
		     login.loginInitialPage(driver);
			
		      Thread.sleep(2000);
		     ProductPricingTestpage productpricing = new ProductPricingTestpage();
				productpricing.ProductPricingInitialPage(driver);
			}

}
