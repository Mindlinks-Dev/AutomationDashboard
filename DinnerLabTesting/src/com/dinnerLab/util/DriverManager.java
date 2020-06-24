/**
 * @author: Basappa Hunsikatti
 * @Created Date :28/04/2015
 * @Updated Date :
 * @Comments This util class is used to load the browsers and drivers need to be executed
 */
package com.dinnerLab.util;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.dinnerLab.constants.TestConstants;


public class DriverManager 
{
	public static WebDriver driver;
	private static Logger log = Logger.getLogger(DriverManager.class);
	public static String downloadPath = "C:\\Users\\admin\\Desktop\\temp";
	
	/**
	 * Method to select the browsers which are required to be loaded
	 * Input: Browser Name
	 * Output: Static
	 */
	public static WebDriver getDriverForBrowser(String browserName) throws Exception 
	{
		//Condition to load the Chrome Browser
		if (browserName.equalsIgnoreCase(com.dinnerLab.constants.TestConstants.BROWSER_CHROME))
		{
			log.info("Chrome browser has been selected");
			ChromeOptions choption = new ChromeOptions();	
			choption.addArguments("--disable-default-apps");
			choption.addArguments("ignore-certifcate-errors","test-type","start-maximized","no-default-browser-check");
			choption.addArguments("disable-infobars");
			
			DesiredCapabilities descap= DesiredCapabilities.chrome();
			descap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true );
			descap.setCapability(ChromeOptions.CAPABILITY, descap);
			//choption.addArguments("chrome.switches","--disable-extensions");
			System.setProperty("webdriver.chrome.driver", "D:/DinnerLabWorkspace/DinnerLabTesting/src/chromedriver.exe");
			log.info("Chrome browser has been successfully loaded");
			return new ChromeDriver();
		}		

		//Condition to load the Firefox Browser
		else if (browserName.equalsIgnoreCase(TestConstants.BROWSER_FIREFOX))
		{		
			log.info("Fire Fox browser has been selected");
			DesiredCapabilities descap= DesiredCapabilities.firefox();
			descap.setCapability(FirefoxDriver.BINARY, true);
			descap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,true);
			//choption.addArguments("chrome.switches","--disable-extensions");
			System.setProperty("webdriver.gecko.driver", "D:/DinnerLabWorkspace/DinnerLabTesting/src/geckodriver.exe");
			log.info("Fire Fox browser has been successfully loaded");
			return new FirefoxDriver();
		}

		//Condition to load the Internet Explorer Browser
		else 
		{
			log.info("Internet Explorer browser has been selected");
			DesiredCapabilities descap= DesiredCapabilities.internetExplorer();
			descap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			descap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,true);
			System.setProperty("webdriver.ie.driver", "D:/DinnerLabWorkspace/DinnerLabTesting/src/IEDriverServer_32.exe");
			log.info("Internet Explorer browser has been successfully loaded");
			return new InternetExplorerDriver();
		}
	}
	
	/**
	 * Method to download a file from the fire fox application.
	 * Input: No Parameter
	 * Output: static
	 */
	public static FirefoxProfile FirefoxDriverProfile() throws Exception 
	{
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.dir", downloadPath);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv, application/pdf, application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel, application/vnd.ms- excel,application/x-excel,application/x-msexcel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/excel,text/x-c");
		profile.setPreference("browser.helperApps.neverAsk.openFile","text/csv, application/csv, application/pdf, application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/excel");
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.closeWhenDone", false);
		return profile;
	}
}