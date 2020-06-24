/**
 * @author: Basappa Hunsikatti
 * @Created Date :17/02/2016
 * @Updated Date :
 * @Comments This automation class will update Guest Chef Bio information.
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

public class ChefBioInformationUpdatePage 
{
	 private String loginURL;
	 private String loginEmail;
	 private String loginPassword;
	 private String currentEmployment;
	 private String workHistory;
	 private String education;
	 private String homeTown;
	 private String FiveFavoriteRestaurants;
	 private String FiveFavoriteBars;
	 private String howDidYouHearAboutUs;
	 private String twentyOneYearsOld;
	 boolean present;
	 public int rowCount;
	 String isTestPassed="FAIL";
	 public static boolean bioInfoUpdateSuccess;
	 private static Logger log = Logger.getLogger(ChefBioInformationUpdatePage.class);
	 //MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	
	 //AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
	 
	 /**
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void chefBioInfoUpdateInitialPage(WebDriver driver)
	 {
		try
		{
			ExcelLib xllib = new ExcelLib();
			 
			int rowCount = xllib.getRowCount("ChefBioInfoUpdate");
			System.out.println("rowCount:"+rowCount);
			log.info("*********************Chef Bio Info Edit Logger Initialized******************************* ");
			for(int i=1;i<= rowCount;i++)
			{
				loginURL = xllib.getExcelData("ChefBioInfoUpdate", i, 0);
				loginEmail = xllib.getExcelData("ChefBioInfoUpdate", i, 1);
				loginPassword = xllib.getExcelData("ChefBioInfoUpdate", i, 2);
				currentEmployment = xllib.getExcelData("ChefBioInfoUpdate", i,3);
				workHistory = xllib.getExcelData("ChefBioInfoUpdate", i,4);
				education = xllib.getExcelData("ChefBioInfoUpdate", i,5);
				homeTown = xllib.getExcelData("ChefBioInfoUpdate", i,6);
				FiveFavoriteRestaurants = xllib.getExcelData("ChefBioInfoUpdate", i,7);
				FiveFavoriteBars = xllib.getExcelData("ChefBioInfoUpdate", i,8);
				howDidYouHearAboutUs = xllib.getExcelData("ChefBioInfoUpdate", i,9);
				twentyOneYearsOld = xllib.getExcelData("ChefBioInfoUpdate", i,10);
				
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				//Calling Login URL method
				loginLogout.loginURLActions(driver,loginURL);
				
				//Calling Member Login Method
				loginLogout.loginPageActions(driver,loginEmail,loginPassword);
						
				
				//Checking whether login credentials valid or not
				if(MemberLoginLogoutPage.loggedInStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("ChefBioInfoUpdate", i, 11, isTestPassed);
					
					//Calling chef information info edit actions method
					chefBioInfoUpdateActions(driver);
					
					if(rowCount !=1)
					{
						//Calling Logout method
						loginLogout.logoutActions(driver);
					}
				}else if(!MemberLoginLogoutPage.loggedInStatus)
			    {
			    	isTestPassed = "FAIL";
					xllib.writeToExcel("ChefBioInfoUpdate", i, 11, isTestPassed);
				}
				if(bioInfoUpdateSuccess)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("ChefBioInfoUpdate", i, 12, isTestPassed);
					log.info("Your account "+loginEmail+" has been updated successfully.");	
				}else if(!bioInfoUpdateSuccess)
				{
					isTestPassed = "FAIL";
					xllib.writeToExcel("ChefBioInfoUpdate", i, 12, isTestPassed);
				}
			}
		}
		catch(Exception e)
		{
			log.info("Test Execution Failed");
		}
	 }
	 /**
	 * Test Case for updating chef bio information.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void chefBioInfoUpdateActions(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	 {
		 try
		 {
			log.info("Clicking on Account Info Edit");	
			AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Calling Account Overview method
			creditCard.clickingOnBioInformation(driver);
			
			//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.id("bio-currentemployment")).clear();
			driver.findElement(By.id("bio-currentemployment")).sendKeys(currentEmployment);
			
			driver.findElement(By.id("bio-laststaged")).clear();
			driver.findElement(By.id("bio-laststaged")).sendKeys(workHistory);
			
			driver.findElement(By.id("bio-education")).clear();
			driver.findElement(By.id("bio-education")).sendKeys(education);
			
			driver.findElement(By.id("bio-hometown")).clear();
			driver.findElement(By.id("bio-hometown")).sendKeys(homeTown);
			
			driver.findElement(By.id("bio-favoriterestaurant")).clear();
			driver.findElement(By.id("bio-favoriterestaurant")).sendKeys(FiveFavoriteRestaurants);
			
			driver.findElement(By.id("bio-favoritebar")).clear();
			driver.findElement(By.id("bio-favoritebar")).sendKeys(FiveFavoriteBars);
			
			WebElement referrerText = driver.findElement(By.id("referrer"));
			Select st1 = new Select(referrerText);
			st1.selectByVisibleText(howDidYouHearAboutUs);
			
			if(twentyOneYearsOld.equalsIgnoreCase(TestConstants.STATUS_FALSE))
        	{
				driver.findElement(By.id("travelno")).click();
        	}else if(twentyOneYearsOld.equalsIgnoreCase(TestConstants.STATUS_TRUE))
        	{
        		log.info("You are 21 years old");	
        	}
			
			//Clicking on Update Button
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.id("edit-bio-submit")).click();
			
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(3000);
			bioInfoUpdateSuccess = driver.findElement(By.xpath("//span[contains(text(),'Bio Information Updated.')]")).isDisplayed();
			System.out.println("bioInfoUpdateSuccess:"+bioInfoUpdateSuccess);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Your bio Information has not updated");
		}		
	}
}