package com.SigmaERP.testclass;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.SigmaERP.testpage.CreateStockReceivingPage;
import com.SigmaERP.testpage.Userloginlogoutpage;
import com.dinnerLab.util.DriverManager;

public class CreateStockReceivingClass
{
	private static Logger log = Logger.getLogger(Userloginlogoutclass.class);
	public WebDriver driver;
	
	
	 //Test Case for submitting the Login page depending on the test data passed
	

	 @Test
	 public void loginPageTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		
		DriverManager Launch = new DriverManager();
		driver = new ChromeDriver();
		//System.setProperty("webdriver.chrome.driver" , "E://spoo project//Testing Tool Softwares//New Chrome driver//chromedriver.exe");
		System.out.println("Chrome browser has been successfully loaded");
		driver.manage().window().maximize();
		System.out.println("-------Loaded the Driver--------");
		//driver.get("http:google.com");
		
		
		Userloginlogoutpage login = new Userloginlogoutpage();
		login.loginInitialPage(driver);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

		
		CreateStockReceivingPage  A1 = new CreateStockReceivingPage();
		A1.StockReceivingInitialPage(driver);
	 }
}


