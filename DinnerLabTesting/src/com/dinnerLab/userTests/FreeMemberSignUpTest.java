/**
 * @author: Basappa Hunsikatti
 * @Created Date :23/09/2015
 * @Updated Date :09/02/2016
 * @Comments:This automation class will serve the Free Member Sign Up.
 */
package com.dinnerLab.userTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.FreeMemberSignUpPage;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class FreeMemberSignUpTest
{
	private static Logger log = Logger.getLogger(FreeMemberSignUpTest.class);
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
	 public void  subscriberSignUpTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
		driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		log.info("-------Loaded the Driver--------");
		
		//Calling Free Member Sign Up Page Method.
		FreeMemberSignUpPage signUp = new FreeMemberSignUpPage();
		signUp.subscriberSignUpInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.FREE_MEMBERSHIP_SIGN_UP)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}







