/**
 * @author: Basappa Hunsikatti
 * @Created Date :14/07/2015
 * @Updated Date :
 * @Comments This automation class will serve the Login use cases
 */
package com.dinnerLab.userTests;

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

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.MemberLoginLogoutPage;
import com.dinnerLab.util.DriverManager;
import com.dinnerLab.util.OutputReport;
import com.pack.ScriptHandler;

public class MemberLoginLogoutTest
{
	private static Logger log = Logger.getLogger(MemberLoginLogoutTest.class);
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
		driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		log.info("-------Loaded the Driver--------");
		
		//Calling method to delete PaidMemberReport sheet rows data
		//report.deleteRowsData("PaidMemberReport");
		//Calling method to delete IndividualTicketPurchaseReport sheet rows data
		//report.deleteRowsData("IndividualTicketPurchaseReport");
		//Calling method to delete CompleteTicketPurchaseReport sheet rows data
		//report.deleteRowsData("CompleteTicketPurchaseReport");
		
		MemberLoginLogoutPage login = new MemberLoginLogoutPage();
		login.loginInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.LOGIN)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}