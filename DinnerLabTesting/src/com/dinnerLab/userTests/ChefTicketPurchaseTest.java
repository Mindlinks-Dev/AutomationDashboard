/**
 * @author: Basappa Hunsikatti
 * @Created Date :06/10/2015
 * @Updated Date :
 * @Comments This automation class will purchase Free Member Event Tickets.
 */
package com.dinnerLab.userTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.ChefTicketPurchasePage;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class ChefTicketPurchaseTest
{
	private static Logger log = Logger.getLogger(ChefTicketPurchaseTest.class);
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
		
		//Calling Free Member Sign Up Page Method.
		ChefTicketPurchasePage ticketPurchase = new ChefTicketPurchasePage();
		ticketPurchase.freeMemberTicketPurchaseInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.CHEF_TICKET_PURCHASE)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}

