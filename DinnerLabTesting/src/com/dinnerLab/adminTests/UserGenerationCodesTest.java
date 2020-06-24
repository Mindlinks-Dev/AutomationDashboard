/**
 * @author: Basappa Hunsikatti
 * @Created Date :28/10/2015
 * @Updated Date :
 * @Comments This automation class will serve the creating Sign Up Codes
 */
package com.dinnerLab.adminTests;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.dinnerLab.adminPages.Helpers.UserGenerationCodesPage;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.MemberLoginLogoutPage;
import com.dinnerLab.util.DriverManager;
import com.dinnerLab.util.OutputReport;
import com.pack.ScriptHandler;

public class UserGenerationCodesTest
{
	private static Logger log = Logger.getLogger(UserGenerationCodesTest.class);
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
	 public void userGenerationCodesTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
		driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		log.info("-------Loaded the Driver--------");
		
		UserGenerationCodesPage signUpCode = new UserGenerationCodesPage();
		signUpCode.userGenerationCodesLogin(driver);
		signUpCode.userGenerationCodesInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.USER_GENERATION_CODES)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}