package com.jandk.userclass;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import com.dinnerLab.util.DriverManager;
import com.jandk.userpage.CreateDimensionTagPage;
import com.jandk.userpage.LoginLogoutPage;

public class CreateDimensionTagTest
{
	
		private static Logger log = Logger.getLogger(CreateDimensionTagTest.class);
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
				
				CreateDimensionTagPage A3 = new CreateDimensionTagPage();
			    A3.dimensionTagInitialPage(driver);
				
				
		 }		

}
