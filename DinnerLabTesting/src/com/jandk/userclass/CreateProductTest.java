package com.jandk.userclass;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.dinnerLab.util.DriverManager;
import com.jandk.userpage.CreateProductPage;
import com.jandk.userpage.LoginLogoutPage;

public class CreateProductTest
{
	private static Logger log = Logger.getLogger(CreateProductTest.class);
	public WebDriver driver;
	
	@Test 
	  public void CreateproductTest() throws Exception
	 {
		  DriverManager Launch = new DriverManager();
			driver = new ChromeDriver();
			driver.manage().window().maximize();

			//Calling the loginmethod
			LoginLogoutPage login = new LoginLogoutPage();
			login.loginInitialPage(driver);
			Thread.sleep(8000);
			
			CreateProductPage A2 = new CreateProductPage();
			A2.ProductInitialPage(driver);
			
	 }
}
