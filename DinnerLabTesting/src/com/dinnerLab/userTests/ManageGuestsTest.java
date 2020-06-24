/**
 * @author: Basappa Hunsikatti
 * @Created Date :30/11/2015
 * @Updated Date :
 * @Comments This automation class will serve adding Guests details and their dietary restrictions or allergies.
 */
package com.dinnerLab.userTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.ManageGuestsPage;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class ManageGuestsTest
{
	private static Logger log = Logger.getLogger(ManageGuestsTest.class);
	public WebDriver driver;
		
	/**
	 * Test Case for submitting the Login page depending on the test data passed
	 * valid email and password should allow the user to
	 * login in to the application.
	 * Input: No parameter
	 * Output: Void
	 * @throws Exception 
	 */	 
	@Test
	 public void manageGuestsTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
		driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		log.info("-------Loaded the Driver--------");
		
		ManageGuestsPage guests = new ManageGuestsPage();
		guests.manageGuestsInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.MANAGE_GUESTS)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
