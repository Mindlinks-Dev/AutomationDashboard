/**
 * @author: Basappa Hunsikatti
 * @Created Date :11/12/2015
 * @Updated Date :
 * @Comments This automation class will server Gift Credit
 */
package com.dinnerLab.userPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.dinnerLab.util.ExcelLib;

public class GiftCreditPage 
{
	 private String loginURL;
	 private String loginEmail;
	 private String loginPassword;
	 private String giftCode;
	 private String creditsReceived;
	 private String totalCreditsAvailable;
	 boolean present;
	 public int rowCount;
	 String isTestPassed="FAIL";
	 public static boolean giftCreditedStatus;
	 private static Logger log = Logger.getLogger(GiftCreditPage.class);
		 
	 /**
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void giftCreditInitialPage(WebDriver driver)
	 {
		try
		{
			ExcelLib xllib = new ExcelLib();
			 
			int rowCount = xllib.getRowCount("GiftCredit");
			log.info("*********************Gift Credit Logger Initialized*********************************************************************");
			log.info("Gift Code ||"+" Gift Credited Status ||" + "   Credits Received ||" + "  Total Credits Available ||");
	 		log.info("************************************************************************************************************************");
			for(int i=1;i<= rowCount;i++)
			{
				loginURL = xllib.getExcelData("GiftCredit", i, 0);
				loginEmail = xllib.getExcelData("GiftCredit", i, 1);
				loginPassword = xllib.getExcelData("GiftCredit", i, 2);
				giftCode = xllib.getExcelData("GiftCredit", i,3);
								
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				//Calling Login URL method
				loginLogout.loginURLActions(driver,loginURL);
				
				//Calling Member Login Method
				loginLogout.loginPageActions(driver,loginEmail,loginPassword);
						
				
				//Checking whether login credentials valid or not
				if(MemberLoginLogoutPage.loggedInStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("GiftCredit", i, 4, isTestPassed);
					
					//Calling Gift Credit method
					clickingOnGiftCreditLink(driver);
					
					//Calling user account change password actions method
					giftCreditActions(driver);
					
					if(rowCount !=1)
					{
						//Calling Logout method
						loginLogout.logoutActions(driver);
					}
				}else if(!MemberLoginLogoutPage.loggedInStatus)
			    {
			    	isTestPassed = "FAIL";
					xllib.writeToExcel("GiftCredit", i, 4, isTestPassed);
				}
				if(giftCreditedStatus)
				{
					isTestPassed="PASS";
					log.info(","+giftCode +"," +isTestPassed +"," + creditsReceived + "," + totalCreditsAvailable);
					xllib.writeToExcel("GiftCredit", i, 5, isTestPassed);
					xllib.writeToExcel("GiftCredit", i, 6, creditsReceived);
					xllib.writeToExcel("GiftCredit", i, 7, totalCreditsAvailable);
					log.info("Gift Credited successfully.");	
				}else if(!giftCreditedStatus)
				{
					isTestPassed = "FAIL";
					log.info(","+giftCode +"," +isTestPassed +"," + isTestPassed + "," + isTestPassed);
					xllib.writeToExcel("GiftCredit", i, 5, isTestPassed);
					xllib.writeToExcel("GiftCredit", i, 6, isTestPassed);
					xllib.writeToExcel("GiftCredit", i, 7, isTestPassed);
					log.info("Gift is not credited.");
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
		 * Test Case for gift credit
		 * Input: WebDriver
		 * Output: Void
	*/
	 public void giftCreditActions(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	 {
		 String creditsReceivedTemp = null;
		 try
		 {	
			 
			 driver.findElement(By.name("giftCode")).clear();
			 driver.findElement(By.name("giftCode")).sendKeys(giftCode);
			 
			 //Clicking on Redeem Code button
			 driver.findElement(By.xpath("//input[@value='redeem code']")).click();
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 
			 giftCreditedStatus = driver.findElement(By.xpath("//div[contains(text(),'Your code has been successfully redeemed')]")).isDisplayed();
			 System.out.println("giftCreditedStatus:"+giftCreditedStatus);
			 creditsReceivedTemp = driver.findElement(By.xpath("//div[contains(text(),'Your code has been successfully redeemed')]")).getText();
			 System.out.println("creditsReceivedTemp:"+creditsReceivedTemp);
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 totalCreditsAvailable = driver.findElement(By.xpath("//span[@id='userCreditSum']")).getText();
			 System.out.println("totalCreditsAvailable:"+totalCreditsAvailable);
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			 
			 String [] temp1 = creditsReceivedTemp.split(" ");
			 System.out.println("Temp String is :"+temp1[7]);
			 creditsReceived = temp1[7];
			 System.out.println("creditsReceived:"+creditsReceived);
		}
		catch(Exception e)
		{
			log.info("Password changed unsuccessfully.");
		}		
	}
	/**
		 * Test Case for clicking on My Events Link
		 * Input: WebDriver
		 * Output: Void
		 * @return 
	*/
	public void clickingOnGiftCreditLink(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Actions actions = new Actions(driver);
				
			WebElement parent = driver.findElement(By.xpath("//li[@class='parent']")); // member drop down
			actions.moveToElement(parent).build().perform();

			driver.findElement(By.xpath("//a[contains(text(),'Gift Credit')]")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}