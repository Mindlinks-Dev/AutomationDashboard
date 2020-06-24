/**
 * @author: Basappa Hunsikatti
 * @Created Date :24/11/2015
 * @Updated Date :
 * @Comments This automation class will change Member/Chef Account Password.
 */
package com.dinnerLab.userPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dinnerLab.util.ExcelLib;
import com.pack.ScriptHandler;

public class UserAccountChangePasswordPage 
{
	 private String loginURL;
	 private String loginEmail;
	 private String loginPassword;
	 private String newPassword;
	 private String confirmPassword;
	 boolean present;
	 public int rowCount;
	 String isTestPassed="FAIL";
	 public static boolean changePasswordStatus;
	 private static Logger log = Logger.getLogger(UserAccountChangePasswordPage.class);
	 //MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	
	 //AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
	 
	 /**
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void userAccountChangePasswordInitialPage(WebDriver driver)
	 {
		try
		{
			ExcelLib xllib = new ExcelLib();
			 
			int rowCount = xllib.getRowCount("UserAccountChangePassword");
			log.info("*********************User Account Change Password Logger Initialized******************************* ");
			for(int i=1;i<= rowCount;i++)
			{
				loginURL = xllib.getExcelData("UserAccountChangePassword", i, 0);
				loginEmail = xllib.getExcelData("UserAccountChangePassword", i, 1);
				loginPassword = xllib.getExcelData("UserAccountChangePassword", i, 2);
				newPassword = xllib.getExcelData("UserAccountChangePassword", i,3);
				confirmPassword = xllib.getExcelData("UserAccountChangePassword", i,4);
				
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				//Calling Login URL method
				loginLogout.loginURLActions(driver,loginURL);
				
				//Calling Member Login Method
				loginLogout.loginPageActions(driver,loginEmail,loginPassword);
						
				
				//Checking whether login credentials valid or not
				if(MemberLoginLogoutPage.loggedInStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("UserAccountChangePassword", i, 5, isTestPassed);
					
					//Calling user account change password actions method
					userAccountChangePasswordActions(driver);
					
					if(rowCount !=1)
					{
						//Calling Logout method
						loginLogout.logoutActions(driver);
					}
				}else if(!MemberLoginLogoutPage.loggedInStatus)
			    {
			    	isTestPassed = "FAIL";
					xllib.writeToExcel("UserAccountChangePassword", i, 5, isTestPassed);
				}
				if(changePasswordStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("UserAccountChangePassword", i, 6, isTestPassed);
					log.info("Your Account Email Id:"+loginEmail+" and New Password:"+newPassword);	
					log.info("Password changed successfully.");	
				}else if(!changePasswordStatus)
				{
					isTestPassed = "FAIL";
					xllib.writeToExcel("UserAccountChangePassword", i, 6, isTestPassed);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Test Execution Failed");
		}
	 }
	 /**
		 * Test Case for changing Password of Member/Chef.
		 * Input: WebDriver
		 * Output: Void
	*/
	 public void userAccountChangePasswordActions(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	 {
		 try
		 {	
			 AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
			 
			 //Calling Account Overview Link method
			 //creditCard.clickingOnAccountOverviewLink(driver);
			 
			 //Calling Account Overview method
		     creditCard.clickingOnAccountOverview(driver);
			
			 driver.findElement(By.id("password")).clear();
			 driver.findElement(By.id("password")).sendKeys(newPassword);
			 driver.findElement(By.id("password2")).clear();
			 driver.findElement(By.id("password2")).sendKeys(confirmPassword);
			 driver.findElement(By.xpath("//input[@value='Update Password']")).click();
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 changePasswordStatus = driver.findElement(By.xpath("//span[text()='Password successfully changed.']")).isDisplayed();
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 System.out.println("changePasswordStatus:"+changePasswordStatus);
			 //Verify Change Password success.
			 /*if(changePasswordStatus)
			 {
				driver.findElement(By.xpath("//span[text()='Password successfully changed.']")).click();
				log.info("Password changed successfully.");	
			}*/
		}
		catch(Exception e)
		{
			log.info("Password changed unsuccessfully.");
		}		
	}
}