package com.stos.usersPage;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.stos.usersPage.helpers.LoginTestPage;
import com.stos.usersPage.helpers.OpportunityTestPage;

public class OpportunityTest
{
	private static Logger log = Logger.getLogger(OpportunityTest.class);
	public WebDriver driver;
	 
	@Test 
	public void OpportunityTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		
		Thread.sleep(3000);
		DriverManager loadBrowser = new DriverManager();
	 	driver = loadBrowser.getDriverForBrowser(TestConstants.BROWSER_CHROME);
		driver.manage().window().maximize();
		
		LoginTestPage lg = new LoginTestPage();
		lg.loginTestInitialPage(driver);
		
		OpportunityTestPage opt = new OpportunityTestPage();
		opt.opportunityTestInitialPage(driver);
		
		LoginTestPage lg1 = new LoginTestPage();
		lg1.logoutTestActionPage(driver);
		
	}
}
