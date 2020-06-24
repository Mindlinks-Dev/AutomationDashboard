/**
 * @author: Basappa Hunsikatti
 * @Created Date :28/09/2015
 * @Updated Date :
 * @Comments:This automation class will serve the Chef Sign Up.
 */
package com.dinnerLab.userTests;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.ChefSignUpPage;
import com.dinnerLab.userPages.Helpers.FreeMemberSignUpPage;
import com.dinnerLab.util.DriverManager;
import com.dinnerLab.util.Manager;
import com.pack.ScriptHandler;

public class ChefSignUpTest
{
	private static Logger log = Logger.getLogger(ChefSignUpTest.class);
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
    	 //Calling WebDriver to launch Browser.
    	 driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
    	 driver.manage().window().maximize();
		
		 //Calling Chef Sign Up Page Method.
		 ChefSignUpPage signUp = new ChefSignUpPage();
		 signUp.chefSignUpInitialPage(driver);
		
		 //Condition to Close Browser
		 if(ScriptHandler.scriptIdFlag!=TestConstants.CHEF_SIGN_UP)
		 {
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		 }
	}
}

