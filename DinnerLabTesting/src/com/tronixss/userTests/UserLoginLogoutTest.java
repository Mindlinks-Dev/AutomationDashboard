/**
 * @author: Basappa Hunsikatti
 * @Created Date : 07/06/2016
 * @Updated Date :
 * @Comments This automation class will serve the Login use cases
 */
package com.tronixss.userTests;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.tronixss.userPages.Helpers.UserLoginLogoutPage;


public class UserLoginLogoutTest
{
	private static Logger log = Logger.getLogger(UserLoginLogoutTest.class);
	public WebDriver driver;
	/**
	 * Test Case for submitting the Login page depending on the test data passed
	 * valid email and password should allow the user to
	 * login in to the application.
	 * Input: No parameter
	 * Output: Void
	 * @throws Exception 
	 * @throws InvalidFormatException 
	 */	 

	@Test
	 public void loginPageTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
		driver = loadBrowser.getDriverForBrowser(TestConstants.BROWSER_CHROME);
		driver.manage().window().maximize();
		log.info("-------Loaded the Driver--------");
		
		UserLoginLogoutPage login = new UserLoginLogoutPage();
		login.loginInitialPage(driver);
		
		//Condition to Close Browser
		/*if(ScriptHandler.scriptIdFlag!=TestConstants.LOGIN)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}*/
	}
}