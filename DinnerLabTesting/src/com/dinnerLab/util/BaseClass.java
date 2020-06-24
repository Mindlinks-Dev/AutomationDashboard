/**
 * @author: Basappa Hunsikatti

 * @Created Date :28/04/2015
 * @Updated Date :
 * @Comments:This automation class will select the browser and launching an application.
 */
package com.dinnerLab.util;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dinnerLab.constants.TestConstants;

import org.testng.annotations.Listeners;

//@Listeners({ ATUReportsListener.class, ConfigurationListener.class,MethodListener.class })
public class BaseClass 
{ 
	private static Logger log = Logger.getLogger(BaseClass.class);
	public WebDriver driver;
	
	/**
	 * Test case to load the necessary driver to run
	 * Input: No parameter
	 * Output: Void
	 * @throws Exception 
	 */
	@Test
	public void launchApplication() throws Exception
	{/*
		log.info("-------Opening the browser--------");
		driver = DriverManager.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 * 
		 */
	}
	
	/**
	 * Test case to close the driver 
	 * Input: No parameter
	 * Output: Void
	 */	  
	@Test
	public void teardown()
	{/*
		log.info("-------Closing the browser--------");
		//Close opened browser
		driver.close();
		//Quit all the opened browsers
		driver.quit();
	}
	*/
	}
}
