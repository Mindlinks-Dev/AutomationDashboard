/**
 * @author: Basappa Hunsikatti
 * @Created Date :24/11/2015
 * @Updated Date :
 * @Comments This automation class will update Member/Chef Account information.
 */
package com.dinnerLab.userPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;
import com.pack.ScriptHandler;

public class UserAccountInfoUpdatePage 
{
	 private String loginURL;
	 private String loginEmail;
	 private String loginPassword;
	 private String firstName;
	 private String lastName;
	 private String email;
	 private String address1;
	 private String address2;
	 private String zipCode;
	 private String city;
	 private String state;
	 private String phoneNumber;
	 private String aboutUs;
	 private String areYouTwentyOneYearsOld;
	 boolean present;
	 public int rowCount;
	 String isTestPassed="FAIL";
	 public static boolean accountUpdateSuccess;
	 private static Logger log = Logger.getLogger(UserAccountInfoUpdatePage.class);
	 //MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	
	 //AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
	 
	 /**
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void userAccountInfoUpdateInitialPage(WebDriver driver)
	 {
		try
		{
			ExcelLib xllib = new ExcelLib();
			 
			int rowCount = xllib.getRowCount("UserAccountInfoUpdate");
			log.info("*********************User Account Info Edit Logger Initialized******************************* ");
			for(int i=1;i<= rowCount;i++)
			{
				loginURL = xllib.getExcelData("UserAccountInfoUpdate", i, 0);
				loginEmail = xllib.getExcelData("UserAccountInfoUpdate", i, 1);
				loginPassword = xllib.getExcelData("UserAccountInfoUpdate", i, 2);
				firstName = xllib.getExcelData("UserAccountInfoUpdate", i,3);
				lastName = xllib.getExcelData("UserAccountInfoUpdate", i,4);
				email = xllib.getExcelData("UserAccountInfoUpdate", i,5);
				address1 = xllib.getExcelData("UserAccountInfoUpdate", i,6);
				address2 = xllib.getExcelData("UserAccountInfoUpdate", i,7);
				city = xllib.getExcelData("UserAccountInfoUpdate", i,8);
				state = xllib.getExcelData("UserAccountInfoUpdate", i,9);
				zipCode = xllib.getExcelData("UserAccountInfoUpdate", i,10);
				phoneNumber = xllib.getExcelData("UserAccountInfoUpdate", i,11);
				aboutUs = xllib.getExcelData("UserAccountInfoUpdate", i,12);
				areYouTwentyOneYearsOld = xllib.getExcelData("UserAccountInfoUpdate", i,13);
				
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				//Calling Login URL method
				loginLogout.loginURLActions(driver,loginURL);
				
				//Calling Member Login Method
				loginLogout.loginPageActions(driver,loginEmail,loginPassword);
						
				
				//Checking whether login credentials valid or not
				if(MemberLoginLogoutPage.loggedInStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("UserAccountInfoUpdate", i, 14, isTestPassed);
					
					//Calling user account info edit actions method
					userAccountInfoUpdateActions(driver);
					
					if(rowCount !=1)
					{
						//Calling Logout method
						loginLogout.logoutActions(driver);
					}
				}else if(!MemberLoginLogoutPage.loggedInStatus)
			    {
			    	isTestPassed = "FAIL";
					xllib.writeToExcel("UserAccountInfoUpdate", i, 14, isTestPassed);
				}
				if(accountUpdateSuccess)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("UserAccountInfoUpdate", i, 15, isTestPassed);
					log.info("Your account "+loginEmail+" has been updated successfully.");	
				}else if(!accountUpdateSuccess)
				{
					isTestPassed = "FAIL";
					xllib.writeToExcel("UserAccountInfoUpdate", i, 15, isTestPassed);
				}
			}
		}
		catch(Exception e)
		{
			log.info("Test Execution Failed");
		}
	 }
	 /**
	 * Test Case for updating Member/Chef Account information.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void userAccountInfoUpdateActions(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	 {
		 try
		 {
			AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
			 
			//Calling Account Overview Link method
			//creditCard.clickingOnAccountOverviewLink(driver);
			
			//Calling Account Overview method
			creditCard.clickingOnAccountOverview(driver);
			 
			log.info("Clicking on Account Info Edit");
			Thread.sleep(3000);
			//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//span[text()='edit']")).click();
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(3000);
			System.out.println("before info-first-name");
			driver.findElement(By.id("info-first-name")).clear();
			driver.findElement(By.id("info-first-name")).sendKeys(firstName);
			System.out.println("after info-first-name");
			driver.findElement(By.id("info-last-name")).clear();
			driver.findElement(By.id("info-last-name")).sendKeys(lastName);
			driver.findElement(By.id("info-email")).clear();
			driver.findElement(By.id("info-email")).sendKeys(email);
			driver.findElement(By.id("info-address")).clear();
			//driver.findElement(By.id("info-street")).clear();
			//driver.findElement(By.id("info-street")).sendKeys(street);
			driver.findElement(By.id("info-address")).clear();
			driver.findElement(By.id("info-address")).sendKeys(address1);
			driver.findElement(By.id("info-address2")).clear();
			driver.findElement(By.id("info-address2")).sendKeys(address2);
			driver.findElement(By.id("info-city")).clear();
			driver.findElement(By.id("info-city")).sendKeys(city);
			driver.findElement(By.id("info-state")).sendKeys(state);
			driver.findElement(By.id("info-zip")).clear();
			driver.findElement(By.id("info-zip")).sendKeys(zipCode);
			driver.findElement(By.id("telephone")).clear();
			driver.findElement(By.id("telephone")).sendKeys(phoneNumber);
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement aboutUsText = driver.findElement(By.id("referrer"));
			Select st1 = new Select(aboutUsText);
			st1.selectByVisibleText(aboutUs);
			
			//Condition for checking are you twenty one years old
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if(areYouTwentyOneYearsOld.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.findElement(By.id("travelyes")).click();
			}else if(areYouTwentyOneYearsOld.equalsIgnoreCase(TestConstants.STATUS_FALSE))
			{
				driver.findElement(By.id("travelno")).click();
			}
			
			driver.findElement(By.xpath("//input[@value='Update']")).click();
				
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			accountUpdateSuccess = driver.findElement(By.xpath("//span[contains(text(),'Successfully updated profile.')]")).isDisplayed();
			//System.out.println("accountUpdateSuccess:"+accountUpdateSuccess);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Your account has not updated");
		}		
	}
}