package com.stos.usersPage;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.stos.usersPage.helpers.LoginTestPage;
import com.stos.usersPage.helpers.OpportunityTestPage;

public class LoginTest
{
	private static Logger log = Logger.getLogger(LoginTest.class);
	public WebDriver driver;
	
	@Test 
	public void Logintest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
	 	driver = loadBrowser.getDriverForBrowser(TestConstants.BROWSER_CHROME);
		driver.manage().window().maximize();
		
		LoginTestPage lg = new LoginTestPage();
		lg.loginTestInitialPage(driver);
		
		OpportunityTestPage opt = new OpportunityTestPage();
		opt.opportunityTestInitialPage(driver);
		
	}
}
