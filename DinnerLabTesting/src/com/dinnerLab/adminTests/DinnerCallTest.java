/**
 * @author: Anitha M
 * @Created Date :10/5/2016
 * @Updated Date :
 * @Comments This automation class will serve script for Dinner Creation,Adding seating and menu.
 */
package com.dinnerLab.adminTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.adminPages.Helpers.DinnerCreationPageHelper;
import com.dinnerLab.adminPages.Helpers.DinnerMenuPageHelper;
import com.dinnerLab.adminPages.Helpers.DinnerSeatingPageHelper;
import com.dinnerLab.adminPages.Helpers.SFAdminLoginPageHelper;
import com.dinnerLab.adminPages.Helpers.SFAdminLogoutPageHelper;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class DinnerCallTest
{
	private static Logger log = Logger.getLogger(DinnerCallTest.class);
	public WebDriver driver;
	/**
	 * This Method serves the Test method for running the dinner creation+seating+menu.
	 * depending on login condition
	 * Input: 
	 * Output: Void
	 */
	@Test 
	public void AccountPageTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
	 	driver = loadBrowser.getDriverForBrowser(TestConstants.BROWSER_IE);
		driver.manage().window().maximize();
		
		//calling Admin Login methods 
		SFAdminLoginPageHelper sfa = new SFAdminLoginPageHelper();
		sfa.sfAdminLoginInitialPage(driver);
		
		//calling Dinner creation methods 
		DinnerCreationPageHelper dc = new DinnerCreationPageHelper();
		dc.dinnerCreationInitialPage(driver);
		
		//calling add seating methods 
		DinnerSeatingPageHelper ds=new DinnerSeatingPageHelper();
		ds.dinnerSeatingInitialPage(driver);
		
		//calling add menu methods 
		DinnerMenuPageHelper dm=new DinnerMenuPageHelper();
		dm.dinnerMenuInitialPage(driver);
		
		//calling Admin Logout methods 
		SFAdminLogoutPageHelper logout= new SFAdminLogoutPageHelper(); // Admin logout page
		logout.sfAdminLogoutInitialPage(driver);
		
		////Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.SFDC_DINNER_CREATION)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
