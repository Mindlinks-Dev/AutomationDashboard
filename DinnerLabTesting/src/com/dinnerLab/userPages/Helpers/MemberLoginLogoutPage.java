/**
 * @author: Basappa Hunsikatti
 * @Created Date :14/07/2015
 * @Updated Date :24/09/2015
 * @Comments This automation class will serve the Login use cases
 */
package com.dinnerLab.userPages.Helpers;

import java.io.File;
import java.io.IOException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;
import com.pack.ScriptHandler;
import com.pack.StripeHandler;

public class MemberLoginLogoutPage 
{
	
	//private String loginUrl=TestConstants.LogIn_Url;
	private String loginEmail;
	private String loginPassword;
	private String loginURL;
	boolean present;
	//Connection con = Database.getConnection();
	Statement stm=null;
	// boolean isTestPass=false;
	 String isTestPassed="FAIL";
	 public static String result;
	 public static String comment;
	 public static boolean loggedInStatus;
	 int i = 0;
	 private static Logger log = Logger.getLogger(MemberLoginLogoutPage.class);
		        
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
			 
			int rowCount = xllib.getRowCount("Login");
			//For loop:Fetch data from excel input configuration file(excel sheet)
			for(i=1;i<= rowCount;i++)
			{  
				loginURL = xllib.getExcelData("Login", i, 0);
				loginEmail = xllib.getExcelData("Login", i, 1);
				loginPassword = xllib.getExcelData("Login", i, 2);
				
				//Calling Login URL method
				loginURLActions(driver,loginURL);
				
				Boolean loginStatus = loginPageActions(driver,loginEmail,loginPassword);
				//Check whether login credentials valid or not
				if(loginStatus==true)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("Login", i, 3, isTestPassed);
					//System.out.println("In MemberLogin: Result Value:"+ScriptHandler.result);
					//ScriptHandler.comment.add("Script Login, Test Data "+i+": Success in Login page.");
					//Calling Logout Method
					//System.out.println("In MemberLogin: comment size:"+ScriptHandler.comment.size());

					logoutActions(driver);
				}
				else
				{
					isTestPassed = "FAIL";
					xllib.writeToExcel("Login", i, 3, isTestPassed);
					//ScriptHandler.comment.add("Script Login, Test Data "+i+": Failed in Login page.");							
				}
								
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
	 public Boolean loginPageActions(WebDriver driver,String loginEmail,String loginPassword) throws Exception
		{
		 try
			{
			 	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			    driver.findElement(By.id("email")).clear();		
				driver.findElement(By.id("email")).sendKeys(loginEmail);
				driver.findElement(By.id("password")).clear();
				driver.findElement(By.id("password")).sendKeys(loginPassword);
				driver.findElement(By.id("remember")).click();
				driver.findElement(By.id("login-submit")).click();

				log.info("Waiting for Login process to complete...");

				//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				Thread.sleep(2000);
				//loggedInStatus = driver.findElement(By.xpath("//a[contains(text(),'About Us')]")).isDisplayed();
				loggedInStatus = driver.findElement(By.xpath("//body[header[a[img[@alt='Dinner Lab']]]]/div[2]/nav/ul/li[1]/a[contains(text(),'About Us')]")).isDisplayed();
				System.out.println("loggedInStatus:"+loggedInStatus);
				if(loggedInStatus)
				{
					log.info("Successfully logged into an application.");
				}
				return true;
			}
			catch(Exception e)
			{
				//getScreenshot(driver);
				log.info("Unsuccessful Logged into an application");
				return false;
				
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
	public Boolean logoutActions(WebDriver driver) throws Exception
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			log.info("Logout processing");
			Actions actions = new Actions(driver);
			
			WebElement parent = driver.findElement(By.xpath("//li[@class='parent']")); // member drop down
			actions.moveToElement(parent).build().perform();

			driver.findElement(By.xpath("//a[text()='Logout']")).click(); // logout option
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//a[text()='Yes']")).click(); // pop up yes option
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			boolean logoutText = driver.findElement(By.xpath("//h1[contains(text(),'You are now logged out')]")).isDisplayed();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			if(logoutText)
			{
				
				log.info("Successfully logged out from Application");
				
			}
			return true;
			
		}
		catch(Exception e)
		{
			log.info("Unsuccessfully logged out from Application");
			//getScreenshot(driver);
			return false;
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



