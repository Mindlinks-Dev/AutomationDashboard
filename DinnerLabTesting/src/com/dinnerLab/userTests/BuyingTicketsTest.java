/**
 * @author: Basappa Hunsikatti
 * @Created Date :9/11/2015
 * @Updated Date :
 * @Comments This automation class will purchase Member Tickets.
 */
package com.dinnerLab.userTests;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.BuyingTicketsPage;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class BuyingTicketsTest 
{
	private static Logger log = Logger.getLogger(AddNewCreditCardInfoTest.class);
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
	 public void BuyingTicketsTest() throws Exception
	 {
		try
		{
			log.info("-------Opening the browser--------");
			DriverManager loadBrowser = new DriverManager();
		   	//Calling WebDriver to launch Browser.
			driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
			driver.manage().window().maximize();
			
			BuyingTicketsPage ticketPurchase = new BuyingTicketsPage();
			ticketPurchase.buyingTicketsInitialPage(driver);
			
			//Condition to Close Browser
			if(ScriptHandler.scriptIdFlag!=TestConstants.BUYING_TICKETS)
			{
				log.info("-------Closing the browser--------");
				//Close opened browser
				driver.close();
				//Quit all the opened browsers
				driver.quit();
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}

