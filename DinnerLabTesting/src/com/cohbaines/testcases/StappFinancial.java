package com.cohbaines.testcases;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.cohbaines.testpages.LoginLogoutTestpage;



public class StappFinancial {
	private static Logger log= Logger.getLogger(StappFinancial.class);
	public WebDriver driver;
     @Test
	public void StappFinancial() throws InvalidFormatException
	{
    	 System.setProperty("webdriver.chrome.driver", "D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\com\\src\\test\\resources\\Drivers\\chromedriver.exe");
    	 WebDriver driver= new ChromeDriver();
    	 driver.manage().window().maximize();
    	 log.info("Open the Broswer");
    	 
    	 //Calling the LoginClass
    	 LoginLogoutTestpage Loginlogout= new LoginLogoutTestpage();
    	 Loginlogout.loginInitialPage(driver);
    	 
    	 

		// TODO Auto-generated method stub
		
		

	}

}
