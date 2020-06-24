/**
 * @author: Basappa Hunsikatti
 * @Created Date :31/07/2015
 * @Updated Date :24/09/2015
 * @Comments This automation class will purchase Member/Chef Event Tickets for Individual.
 */
package com.dinnerLab.userTests;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.PaidMemberTicketPurchasePage;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class PaidMemberTicketPurchaseTest 
{
	private static Logger log = Logger.getLogger(PaidMemberTicketPurchaseTest.class);
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
	 public void individualTicketPurchaseTest() throws Exception
	 {
		try
		{
			log.info("-------Opening the browser--------");
			DriverManager loadBrowser = new DriverManager();
			driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
			driver.manage().window().maximize();
			log.info("-------Loaded the Driver--------");
						
			PaidMemberTicketPurchasePage ticketPurchase = new PaidMemberTicketPurchasePage();
			ticketPurchase.individualTicketPurchaseInitialPage(driver);
			
			//Condition to Close Browser
			if(ScriptHandler.scriptIdFlag!=TestConstants.PAID_MEMBER_TICKET_PURCHASE)
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

