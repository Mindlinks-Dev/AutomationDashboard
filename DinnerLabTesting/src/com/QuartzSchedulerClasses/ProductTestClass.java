package com.QuartzSchedulerClasses;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.QuartzSchedulerPages.LoginLogoutTestpage;
import com.QuartzSchedulerPages.ProductPricingTestpage;
import com.QuartzSchedulerPages.ProductTestPage;
import com.QuartzSchedulerPages.StockReceiving;
import com.QuartzSchedulerPages.TransactionTestpage;
import com.QuartzSchedulerPages.VendorProduct;


public class ProductTestClass {
	private static Logger log = Logger.getLogger(ProductTestClass.class);
	public WebDriver driver;
	 
	@Test 
	public   ProductTestClass() throws Exception
	
	 {

		
		System.setProperty("webdriver.chrome.driver","D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\com\\src\\test\\resources\\Drivers\\chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		
	    driver.manage().window().maximize();
	     log.info("open");
	     LoginLogoutTestpage login = new LoginLogoutTestpage();
	     login.loginInitialPage(driver);
		
	      Thread.sleep(2000);
	      
	     

	  ProductTestPage Product = new ProductTestPage();
	  Product.ProductInitialPage1(driver);
	   

	   VendorProduct vendor=  new VendorProduct();
	  vendor.VendorProductInitialPage(driver);
	   
	  
	  ProductPricingTestpage pricing = new ProductPricingTestpage();
	  pricing.ProductPricingInitialPage(driver);
	 
		
	 TransactionTestpage Transaction= new TransactionTestpage();
	 Transaction.TransactionInitialPage(driver);
		
	 StockReceiving Receiving =new StockReceiving();
	 Receiving.StockReceivingInitialPage(driver);   
	     
	
		   
	      

	      
	     
	      
		}



}
