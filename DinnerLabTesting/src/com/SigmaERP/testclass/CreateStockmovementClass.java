package com.SigmaERP.testclass;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.SigmaERP.testpage.CreateStockmovementPage;
import com.SigmaERP.testpage.CreateStockmovementoutPage;
import com.SigmaERP.testpage.Userloginlogoutpage;
import com.dinnerLab.util.DriverManager;

public class CreateStockmovementClass 
{
	private static Logger log = Logger.getLogger(CreateStockmovementClass.class);
	public WebDriver driver;
	
	
	 //Test Case for submitting the Login page depending on the test data passed
	

	 @Test
	 public void loginPageTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		
		DriverManager Launch = new DriverManager();
		driver = new ChromeDriver();
		System.out.println("Chrome browser has been successfully loaded");
		driver.manage().window().maximize();
		System.out.println("-------Loaded the Driver--------");
		
		
		Userloginlogoutpage login = new Userloginlogoutpage();
		login.loginInitialPage(driver);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

		
		CreateStockmovementPage  C1 = new CreateStockmovementPage();
		C1.StockmovementPageInitialPage(driver);
		
		
		//CreateStockmovementoutPage  D1 = new CreateStockmovementoutPage();
		//D1.StockmovementoutPageInitialPage(driver);
	 }
}
