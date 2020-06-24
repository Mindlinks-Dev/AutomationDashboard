/**
 * @author: Basappa Hunsikatti
 * @Created Date :17/02/2016
 * @Updated Date :
 * @Comments This automation class will update Guest Chef Bio information.
 */
package com.dinnerLab.userTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.ChefBioInformationUpdatePage;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class ChefBioInformationUpdateTest
{
	private static Logger log = Logger.getLogger(ChefBioInformationUpdateTest.class);
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
	 public void chefBioInfoUpdateTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
		driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		log.info("-------Loaded the Driver--------");
		
		ChefBioInformationUpdatePage bioInfo = new ChefBioInformationUpdatePage();
		System.out.println("before calling method");
		bioInfo.chefBioInfoUpdateInitialPage(driver);
		System.out.println("after calling method");
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.CHEF_BIO_INFO_UPDATE)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
