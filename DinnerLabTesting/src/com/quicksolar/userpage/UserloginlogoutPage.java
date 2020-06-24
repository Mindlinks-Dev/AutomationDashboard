package com.quicksolar.userpage;

import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.dinnerLab.util.ExcelLib;

public class UserloginlogoutPage 
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
			 private static Logger log = Logger.getLogger(UserloginlogoutPage.class);
				        
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
					 
					int QuicksolarloginRowCount = xllib.getRowCount("Quicksolarlogin");
					System.out.println("QuicksolarloginRowCount:"+QuicksolarloginRowCount);
					//For loop:Fetch data from excel input configuration file(excel sheet)
					for(i=0;i<= QuicksolarloginRowCount;i++)
					{  
						loginURL = xllib.getExcelData("Quicksolarlogin", i, 0);
						loginUsername = xllib.getExcelData("Quicksolarlogin", i, 1);
						loginPassword = xllib.getExcelData("Quicksolarlogin", i, 2);
						
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
					System.out.println("Login successful");
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
						driver.findElement(By.xpath("//input[@style='text-align:center;margin-bottom:14px; margin-top:-22px;background-color:#f2f2f3 ;color:#5e6060;']")).clear();
						driver.findElement(By.xpath("//input[@style='text-align:center;margin-bottom:14px; margin-top:-22px;background-color:#f2f2f3 ;color:#5e6060;']")).sendKeys(loginUsername);
						driver.findElement(By.id("Password")).clear();
						driver.findElement(By.id("Password")).sendKeys(loginPassword);
						driver.findElement(By.xpath("//input[@value=' Log in']")).click();
						
						
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
             }


