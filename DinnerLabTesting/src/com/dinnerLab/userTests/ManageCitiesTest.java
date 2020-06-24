/**
 * @author: Basappa Hunsikatti
 * @Created Date :30/11/2015
 * @Updated Date :
 * @Comments This automation class will manage Member/Chef Cities.
 */
package com.dinnerLab.userTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.ManageCitiesPage;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class ManageCitiesTest
{
	private static Logger log = Logger.getLogger(ManageCitiesTest.class);
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
	 public void manageCitiesTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
		driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		log.info("-------Loaded the Driver--------");
		
		ManageCitiesPage cities = new ManageCitiesPage();
		cities.manageCitiesInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.MANAGE_CITIES)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
