package com.jandk.userclass;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import com.dinnerLab.util.DriverManager;
import com.jandk.userpage.CreateAccountPage;
import com.jandk.userpage.LoginLogoutPage;

public class CreateAccountTest
{
	private static Logger log = Logger.getLogger(CreateAccountTest.class);
	public WebDriver driver;
	
	@Test 
	  public void CreateaccountTest() throws Exception
	 {
		  DriverManager Launch = new DriverManager();
			driver = new ChromeDriver();
			driver.manage().window().maximize();

			//Calling the loginmethod
			LoginLogoutPage login = new LoginLogoutPage();
			login.loginInitialPage(driver);
			
			CreateAccountPage A1 = new CreateAccountPage();
			A1.AccountInitialPage(driver);
	 }

}
