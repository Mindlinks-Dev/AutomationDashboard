/**
 * @author: Basappa Hunsikatti
 * @Created Date :24/11/2015
 * @Updated Date :
 * @Comments This automation class will change Member/Chef Account Password.
 */
package com.dinnerLab.userTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.UserAccountChangePasswordPage;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class UserAccountChangePasswordTest
{
	private static Logger log = Logger.getLogger(UserAccountChangePasswordTest.class);
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
	 public void userAccountChangePasswordTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
		driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		log.info("-------Loaded the Driver--------");
		
		UserAccountChangePasswordPage password = new UserAccountChangePasswordPage();
		password.userAccountChangePasswordInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.USER_ACCOUNT_CHANGE_PASSWORD)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
