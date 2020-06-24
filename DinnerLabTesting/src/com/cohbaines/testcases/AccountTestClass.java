package com.cohbaines.testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.cohbaines.testpages.AccountTestpage;
import com.cohbaines.testpages.LoginLogoutTestpage;





public class AccountTestClass {
	private static Logger log = Logger.getLogger(AccountTestClass.class);
	public WebDriver driver;
	 
	@Test 
	public void AccountTestClass() throws Exception
	 {
		System.setProperty("webdriver.chrome.driver","D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\com\\src\\test\\resources\\Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
	    driver.manage().window().maximize();
	     log.info("open");
	     LoginLogoutTestpage login = new LoginLogoutTestpage();
	     login.loginInitialPage(driver);
		
	      Thread.sleep(2000);
	      AccountTestpage account = new AccountTestpage();
			account.accountsInitialPage1(driver);
		}


}
