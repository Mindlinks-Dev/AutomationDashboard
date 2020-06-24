package com.SalesForce.TestClasses;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.SalesForce.TestPages.LoginLogoutTestpage;




@Test
public class CreateLead {
	private static Logger log = Logger.getLogger(CreateLead.class);
	public WebDriver driver;
	@Test 
	 public void StappLoginLogout() throws Exception
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver","D:\\Ranjitha\\chromedriver.exe");
		WebDriver driver=new ChromeDriver(options);
	     driver.manage().window().maximize();
	     log.info("open");

	LoginLogoutTestpage login = new LoginLogoutTestpage();
	login.loginInitialPage(driver);
	}
	

}
