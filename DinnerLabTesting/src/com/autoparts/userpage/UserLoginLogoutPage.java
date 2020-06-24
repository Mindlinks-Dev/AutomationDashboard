package com.autoparts.userpage;

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

import com.autoparts.userclass.CreateEditQuotationTest;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;

public class UserLoginLogoutPage 

{
	//private String loginUrl=TestConstants.LogIn_Url;
	private String loginUsername;
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
	 public static boolean loginStatus;
	 public static boolean logoutStatus;

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
			 
			int loginRowCount = xllib.getRowCount("AutopartsSFDCLogin");
			System.out.println("loginRowCount:"+loginRowCount);
			//For loop:Fetch data from excel input configuration file(excel sheet)
			for(i=0;i<= loginRowCount;i++)
			{  
				loginURL = xllib.getExcelData("AutopartsSFDCLogin", i, 0);
				loginUsername = xllib.getExcelData("AutopartsSFDCLogin", i, 1);
				loginPassword = xllib.getExcelData("AutopartsSFDCLogin", i, 2);
				
				//Calling Login URL method
				System.out.println("loginURL:"+loginURL);
				System.out.println("loginUsername:"+loginUsername);
				System.out.println("loginPassword:"+loginPassword);
				
				
				loginStatus = loginPageActions(driver,loginURL,loginUsername,loginPassword);
				//logoutStatus = logoutActions(driver);
				System.out.println("Login Count:"+i);
								
			}
		}
		catch(Exception e)
		{
			System.out.println("Login Unsucessful");
			e.printStackTrace();
			
		}
	 }
	 
	 /**
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver,loginEmail,loginPassword
	 * Output: Void
	 * @throws Exception 
	 */
	 public Boolean loginPageActions(WebDriver driver,String loginURL,String loginUsername,String loginPassword) throws Exception
	 {
		 try
			{
			 	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			 	//driver.get(TestConstants.LOGIN_URL);
			 	driver.get(loginURL);
			 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.id("username")).clear();
				driver.findElement(By.id("username")).sendKeys(loginUsername);
				driver.findElement(By.id("password")).clear();
				driver.findElement(By.id("password")).sendKeys(loginPassword);
				driver.findElement(By.id("Login")).click();
				
				
				log.info("Waiting for Login process to complete...");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				return true;
			}
			catch(Exception e)
			{
				log.info("Unsuccessful Logged into an application");
				return false;
				
			}
		}
	/**
		 * Test Case for log out from the page
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws Exception 
		 */
		/*public static Boolean logoutActions(WebDriver driver) throws Exception
		{
			try
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				log.info("Logout processing");
				driver.findElement(By.xpath("//span[@id='userNavLabel']"));// Support High flow auto parts drop down
				
				driver.findElement(By.xpath("a[@title='Logout']")).click(); // logout option
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				
				if(logoutStatus)
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
		 
		 public void getScreenshot(WebDriver driver) throws Exception 
	     {
			 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	         //The below method will save the screen shot in E drive with name "LoginFailed1.png"
	         FileUtils.copyFile(scrFile, new File("E:\\DLFailedTCScreenShots\\LoginFailed1.png"));
	     }*/
	}




 	 


	
	



