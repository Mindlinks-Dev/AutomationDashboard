package com.dinnerLab.userTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.SignupDinnerlabPage;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class SignupDinnerlabTest 
{
	private static Logger log = Logger.getLogger(SignupDinnerlabTest.class);
	public WebDriver driver;
	//OutputReport report = new OutputReport();
	
	/**
	 * Test Case for signing up as a member depending on the test data passed
	 * Registering as a member in to the application
	 * Input: No parameter
	 * Output: Void
	 * @throws Exception 
	 * 
	 */
     @Test
	 public void  signupDinnerlabTest() throws Exception
	 {
    	 log.info("-------Opening the browser--------");
    	 DriverManager loadBrowser = new DriverManager();
    	 //Calling WebDriver to launch Browser.
    	 driver = loadBrowser.getDriverForBrowser(TestConstants.BROWSER_CHROME);
    	 driver.manage().window().maximize();
		
		 //Calling Chef Sign Up Page Method.
    	 SignupDinnerlabPage signUp = new SignupDinnerlabPage();
		 signUp.signupInitialPage(driver);
		
		 //Condition to Close Browser
		 if(ScriptHandler.scriptIdFlag!=TestConstants.SIGNUP_DINNERLAB)
		 {
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		 }
	}
}
