/**
 * @author: Basappa Hunsikatti
 * @Created Date : 07/06/2016
 * @Updated Date :
 * @Comments This automation class will serve the Login use cases
 */
package com.tronixss.userPages.Helpers;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;

public class UserLoginLogoutPage 
{
	private String loginUsername;
	private String loginPassword;
	private String loginURL;
	String isTestPassed="FAIL";
	public static String result;
	public static String comment;
	public static boolean loggedInStatus;
	int i = 0;
	private static Logger log = Logger.getLogger(UserLoginLogoutPage.class);
		        
	 /**
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	 public void loginInitialPage(WebDriver driver) throws InvalidFormatException
	 {
		 
		try
		{
			 ExcelLib xllib = new ExcelLib();
			 
			int rowCount = xllib.getRowCount("SFDCLogin");
			//For loop:Fetch data from excel input configuration file(excel sheet)
			for(i=1;i<= rowCount;i++)
			{  
				loginURL = xllib.getExcelData("SFDCLogin", i, 0);
				loginUsername = xllib.getExcelData("SFDCLogin", i, 1);
				loginPassword = xllib.getExcelData("SFDCLogin", i, 2);
				
				//System.out.println("loginURL:"+loginURL);
				//System.out.println("loginUsername:"+loginUsername);
				//System.out.println("loginPassword:"+loginPassword);
				
				//Calling Login URL method
				//loginURLActions(driver,loginURL);
				
				loginPageActions(driver,loginURL,loginUsername,loginPassword);
				
				//Check whether login credentials valid or not
				/*if(loggedInStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("SFDCLogin", i, 3, isTestPassed);
					
					logoutActions(driver);
				}
				else
				{
					isTestPassed = "FAIL";
					xllib.writeToExcel("SFDCLogin", i, 3, isTestPassed);
				}*/
			}
		}
		catch(Exception e)
		{
			log.info("Login Unsucessful");
			e.printStackTrace();
			
		}
		//Assert.assertTrue(isTestPassed);
	 }
	 
	 /**
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver,loginEmail,loginPassword
	 * Output: Void
	 * @throws Exception 
	 */
	 public void loginPageActions(WebDriver driver,String loginURL,String loginUsername,String loginPassword) throws Exception
	 {
		 try
		 {
		 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 	//Navigating to specified URL.
		 	driver.get(loginURL);
		 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 	
		 	driver.findElement(By.id("username")).clear();		
			driver.findElement(By.id("username")).sendKeys(loginUsername);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(loginPassword);
			//driver.findElement(By.id("rememberUn")).click();
			driver.findElement(By.id("Login")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//log.info("Waiting for Login process to complete...");
			/*Thread.sleep(5000);
			loggedInStatus = driver.findElement(By.xpath("//a[contains(text(),'Home')]")).isDisplayed();
			System.out.println("loggedInStatus:"+loggedInStatus);
			
			if(loggedInStatus)
			{
				log.info("Successfully logged into Salesforce application.");
			}*/
			}
			catch(NoSuchElementException nse)
			{
				//getScreenshot(driver);
				nse.printStackTrace();
			}
		 	catch(Exception e)
			{
				//getScreenshot(driver);
				e.printStackTrace();
				log.info("Unsuccessful Logged into Salesforce application");
			}
		}
	
	  /**
		 * Test Case for passing Login URL
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
		*/
		 public  void loginURLActions(WebDriver driver,String loginURL)
		 {
			 try
			 {
				 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				 //String stagingURL=AutomateLoader.config.getProperty("STAGING_LOGIN_URL");
				 //String stagingLoginURL = stagingURL+"?verify=boop";
				 String dinnerLabLoginURL = TestConstants.HTTP+loginURL+TestConstants.LOG_IN;
				 //System.out.println("Login URL:"+dinnerLabLoginURL);
				 driver.manage().window().maximize();
				 driver.get(dinnerLabLoginURL);
				log.info("navigating to "+dinnerLabLoginURL);
				 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 }catch(Exception e)
			 {
				e.printStackTrace();
			 }
		 }
	 	 
	/**
	 * Test Case for log out from the page
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws Exception 
	 */
	public void logoutActions(WebDriver driver) throws Exception
	{
		try
		{
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(3000);
			driver.findElement(By.id("userNavLabel")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//a[text()='Logout']")).click(); // logout option
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			log.info("Unsuccessfully logged out from Application");
			e.printStackTrace();
			//getScreenshot(driver);
		}
	}
	 /**
	 * Test Case for clicking on Login Link
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws Exception 
	 * @throws InvalidFormatException 
	*/
	 public  void clickingOnLoginLink(WebDriver driver) throws Exception
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 driver.findElement(By.xpath("//a[@href='/login/']")).click();	
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 }catch(Exception e)
		 {
			e.printStackTrace();
			//getScreenshot(driver);
		 }
	 }
	 
	 public void getScreenshot(WebDriver driver) throws Exception 
     {
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in E drive with name "LoginFailed1.png"
         FileUtils.copyFile(scrFile, new File("E:\\DLFailedTCScreenShots\\LoginFailed1.png"));
     }
}



