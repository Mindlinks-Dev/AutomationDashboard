/**
 * @author: Basappa Hunsikatti
 * @Created Date :23/11/2015
 * @Updated Date :
 * @Comments:This automation class Create Custom Event Links 
*/
package com.dinnerLab.adminTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.adminPages.Helpers.CreateCustomEventLinksPage;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class CreateCustomEventLinksTest 
{
	private static Logger log = Logger.getLogger(CreateCustomEventLinksTest.class);
	public WebDriver driver;
	 
	@Test 
	public void CreateCustomEventLinksTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
	 	driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		
		CreateCustomEventLinksPage eventLink = new CreateCustomEventLinksPage();
		eventLink.AdminLogin(driver);
		eventLink.createCustomEventLinksInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.CREATE_CUSTOM_EVENT_LINKS)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
