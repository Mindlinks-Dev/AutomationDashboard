package com.QuartzSchedulerClasses;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.QuartzSchedulerPages.LoginLogoutTestpage;




public class LoginLogout {
	private static Logger log = Logger.getLogger(LoginLogout.class);
	public WebDriver driver;
	@Test 
	 public void StappLoginLogout() throws Exception
	{
		System.setProperty("webdriver.chrome.driver","D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\com\\src\\test\\resources\\Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
	     driver.manage().window().maximize();
	     log.info("open");

	LoginLogoutTestpage login = new LoginLogoutTestpage();
	login.loginInitialPage(driver);
	}
}
