/**
 * @author: Basappa Hunsikatti
 * @Created Date :14/12/2015
 * @Updated Date :
 * @Comments This automation class will server For each friend that purchases a 
 * Select Membership off of your link, you’ll both receive $20 in credit.
 */
package com.dinnerLab.userPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.dinnerLab.util.ExcelLib;

public class ReferFriendsPage 
{
	 private String loginURL;
	 private String loginEmail;
	 private String loginPassword;
	 boolean present;
	 public int rowCount;
	 String isTestPassed="FAIL";
	 private static Logger log = Logger.getLogger(ReferFriendsPage.class);
		 
	 /**
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void referFriendsInitialPage(WebDriver driver)
	 {
		try
		{
			ExcelLib xllib = new ExcelLib();
			 
			int rowCount = xllib.getRowCount("ReferFriends");
			log.info("*********************Refer Friends Logger Initialized**");
			log.info("Login Email||"+"Referral Link");
	 		log.info("*******************************************************");
			for(int i=1;i<= rowCount;i++)
			{
				loginURL = xllib.getExcelData("ReferFriends", i, 0);
				loginEmail = xllib.getExcelData("ReferFriends", i, 1);
				loginPassword = xllib.getExcelData("ReferFriends", i, 2);
												
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				//Calling Login URL method
				loginLogout.loginURLActions(driver,loginURL);
				
				//Calling Member Login Method
				loginLogout.loginPageActions(driver,loginEmail,loginPassword);
						
				
				//Checking whether login credentials valid or not
				if(MemberLoginLogoutPage.loggedInStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("ReferFriends", i, 3, isTestPassed);
					
					//Calling Refer Friends method
					clickingOnReferFriendsLink(driver);
					
					FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
					freeMember.referLinkGetText(driver);
					
					log.info(","+loginEmail+","+FreeMemberSignUpPage.referLink);
					xllib.writeToExcel("ReferFriends", i, 4, FreeMemberSignUpPage.referLink);
					
					if(rowCount !=1)
					{
						//Calling Logout method
						loginLogout.logoutActions(driver);
					}
				}else if(!MemberLoginLogoutPage.loggedInStatus)
			    {
			    	isTestPassed = "FAIL";
			    	log.info(","+isTestPassed+","+isTestPassed);
			    	xllib.writeToExcel("ReferFriends", i, 3, isTestPassed);
					xllib.writeToExcel("ReferFriends", i, 4, isTestPassed);
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
		 * Test Case for clicking on Refer Friends Link
		 * Input: WebDriver
		 * Output: Void
		 * @return 
	*/
	public void clickingOnReferFriendsLink(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Actions actions = new Actions(driver);
				
			WebElement parent = driver.findElement(By.xpath("//li[@class='parent']")); // member drop down
			actions.moveToElement(parent).build().perform();

			driver.findElement(By.xpath("//a[contains(text(),'Refer Friends')]")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}