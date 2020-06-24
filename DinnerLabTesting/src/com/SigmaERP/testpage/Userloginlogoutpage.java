package com.SigmaERP.testpage;

import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dinnerLab.util.ExcelLib;

public class Userloginlogoutpage 
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
		 private static Logger log = Logger.getLogger(Userloginlogoutpage.class);
			        
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
				 
				int loginRowCount = xllib.getRowCount("SigmaERPLogin");
				System.out.println("loginRowCount:"+loginRowCount);
				//For loop:Fetch data from excel input configuration file(excel sheet)
				for(i=1;i<= loginRowCount;i++)
				{  
					loginURL = xllib.getExcelData("SigmaERPLogin", i, 0);
					loginUsername = xllib.getExcelData("SigmaERPLogin", i, 1);
					loginPassword = xllib.getExcelData("SigmaERPLogin", i, 2);
					
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
				System.out.println("Login sucessful");
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
				 	log.info("loginURL::"+loginURL);
				 	//driver.get(TestConstants.LOGIN_URL);
				 	driver.get(loginURL);
				 	System.out.println("url inspected");
				 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.id("username")).clear();
					driver.findElement(By.id("username")).sendKeys(loginUsername);
					driver.findElement(By.id("password")).clear();
					driver.findElement(By.id("password")).sendKeys(loginPassword);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.id("Login")).submit();
					
					
					log.info("Waiting for Login process to complete...");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					
					return true;
				}
				catch(Exception e)
				{
					log.info("Unsuccessful Logged into an application"+e.getLocalizedMessage());
					log.info("Unsuccessful Logged into an"+e.getMessage());
					return false;
					
				}
			}

}
