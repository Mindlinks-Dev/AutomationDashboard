package com.cohbaines.testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.cohbaines.testpages.LoginLogoutTestpage;
import com.cohbaines.testpages.ProductTestPage;
import com.cohbaines.testpages.StockReceiving;








public class ProductTestClass1 {
	private static Logger log = Logger.getLogger(ProductTestClass1.class);
	public WebDriver driver;
	 
	@Test 
	public void ProductTestClass() throws Exception
	 {
		System.setProperty("webdriver.chrome.driver","D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\com\\src\\test\\resources\\Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
	    driver.manage().window().maximize();
	     log.info("open");
	     LoginLogoutTestpage login = new LoginLogoutTestpage();
	     login.loginInitialPage(driver);
		
	      Thread.sleep(2000);
	      ProductTestPage Product = new ProductTestPage();
		  Product.ProductInitialPage1(driver);
	     
	    /*  VendorProduct vendor=  new VendorProduct();
	      vendor.VendorProductInitialPage(driver);
	      TransactionTestpage Transaction= new TransactionTestpage();
	      Transaction.TransactionInitialPage(driver);
	      
	      RecipeManagement Recipe = new RecipeManagement();
	      Recipe.RecipeInitialPage(driver);
	      
	      StockReceiving Receiving =new StockReceiving();
	      Receiving.StockReceivingInitialPage(driver);
	      */
	     
		}



}
