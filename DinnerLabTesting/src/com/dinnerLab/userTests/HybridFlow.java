package com.dinnerLab.userTests;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.HybridFlowHelper;
import com.dinnerLab.util.DriverManager;
import com.dinnerLab.util.OutputReport;
import com.pack.ScriptHandler;

public class HybridFlow
{

	private static Logger log = Logger.getLogger(HybridFlow.class);
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
	 public void hybridFlowTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
		
		driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		log.info("-------Loaded the Driver--------");
		
		HybridFlowHelper hybrid = new HybridFlowHelper();
		hybrid.hybridFlowInitialPage(driver);	
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.HYBRID_FLOW)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
