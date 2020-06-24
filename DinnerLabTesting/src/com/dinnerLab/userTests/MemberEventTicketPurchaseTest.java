/**
 * @author: Basappa Hunsikatti
 * @Created Date :31/07/2015
 * @Updated Date :
 * @Comments This automation class will purchase Member/Chef Event Tickets.
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
import com.dinnerLab.userPages.Helpers.MemberEventTicketPurchasePage;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class MemberEventTicketPurchaseTest 
{
	private static Logger log = Logger.getLogger(MemberEventTicketPurchaseTest.class);
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
		log.info("-------Opening the browser --------");
		DriverManager loadBrowser = new DriverManager();
		driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		log.info("-------Loaded the Driver--------");
		
		MemberEventTicketPurchasePage eventTicket = new MemberEventTicketPurchasePage();
		//eventTicket.memberEventTicketPurchaseActions(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.PAID_MEMBER_TICKET_PURCHASE)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
	
	private WebDriver loadDriver(String browserName)
	{
		WebDriver driver = null;
		try{
			//Condition to load the Chrome Browser
			if (browserName.equalsIgnoreCase(TestConstants.Browser_Chrome))
			{
				log.info("Chrome browser has been selected");
				ChromeOptions choption = new ChromeOptions();		    
				choption.addArguments("ignore-certifcate-errors","test-type","start-maximized","no-default-browser-check");
				DesiredCapabilities descap= DesiredCapabilities.chrome();
				descap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true );
				descap.setCapability(ChromeOptions.CAPABILITY, descap);		
				System.setProperty("webdriver.chrome.driver", "DinnerLabAutomationDashboard/DinnerLabTesting/src/chromedriver.exe");
				log.info("Chrome browser has been successfully loaded");
				driver = new ChromeDriver();
			}		
	
			//Condition to load the Firefox Browser
			else if (browserName.equalsIgnoreCase(TestConstants.Browser_Firefox))
			{		
				log.info("Fire Fox browser has been selected");
				log.info("Fire Fox browser has been successfully loaded");
				driver =  new FirefoxDriver();
			}
	
			//Condition to load the Internet Explorer Browser
			else 
			{
				log.info("Internet Explorer browser has been selected");
				DesiredCapabilities descap= DesiredCapabilities.internetExplorer();
				descap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				descap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,true);
				System.setProperty("webdriver.ie.driver", "DinnerLabAutomationDashboard/DinnerLabTesting/src/IEDriverServer_32.exe");
				log.info("Internet Explorer browser has been successfully loaded");
				driver = new InternetExplorerDriver();
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return driver;
	}
	
	/**
	 * Method to download a file from the fire fox application.
	 * Input: No Parameter
	 * Output: static
	 */
	public  FirefoxProfile FirefoxDriverProfile()  
	{
		FirefoxProfile profile = new FirefoxProfile();
		try{
		
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		//profile.setPreference("browser.download.dir", downloadPath);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv, application/pdf, application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel, application/vnd.ms- excel,application/x-excel,application/x-msexcel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/excel,text/x-c");
		profile.setPreference("browser.helperApps.neverAsk.openFile","text/csv, application/csv, application/pdf, application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/excel");
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.closeWhenDone", false);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return profile;
	
	}
}
