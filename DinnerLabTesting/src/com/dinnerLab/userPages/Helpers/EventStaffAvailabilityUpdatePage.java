/**
 * @author: Basappa Hunsikatti
 * @Created Date :18/02/2016
 * @Updated Date :
 * @Comments This automation class will update Event Staff availability.
 */
package com.dinnerLab.userPages.Helpers;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;

public class EventStaffAvailabilityUpdatePage 
{
	 private String loginURL;
	 private String loginEmail;
	 private String loginPassword;
	 private String Monday;
	 private String Tuesday;
	 private String Wednesday;
	 private String Thursday;
	 private String Friday;
	 private String Saturday;
	 private String Sunday;
	 private String Mornings;
	 private String Afternoons;
	 private String Evenings;
	 private String Graveyard;
	 private boolean MondayStatus;
	 private boolean TuesdayStatus;
	 private boolean WednesdayStatus;
	 private boolean ThursdayStatus;
	 private boolean FridayStatus;
	 private boolean SaturdayStatus;
	 private boolean SundayStatus;
	 private boolean MorningsStatus;
	 private boolean AfternoonsStatus;
	 private boolean EveningsStatus;
	 private boolean GraveyardStatus;
	 boolean present;
	 public int rowCount;
	 String isTestPassed="FAIL";
	 public static boolean eventStaffAvailabilityUpdateSuccess;
	 private static Logger log = Logger.getLogger(EventStaffAvailabilityUpdatePage.class);
		 
	 /**
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void eventStaffAvailabilityUpdateInitialPage(WebDriver driver)
	 {
		try
		{
			ExcelLib xllib = new ExcelLib();
			 
			int rowCount = xllib.getRowCount("EventStaffAvailabilityUpdate");
			log.info("*********************Event Staff Availability Update Logger Initialized******************************* ");
			for(int i=1;i<= rowCount;i++)
			{
				loginURL = xllib.getExcelData("EventStaffAvailabilityUpdate", i, 0);
				loginEmail = xllib.getExcelData("EventStaffAvailabilityUpdate", i, 1);
				loginPassword = xllib.getExcelData("EventStaffAvailabilityUpdate", i, 2);
				Monday = xllib.getExcelData("EventStaffAvailabilityUpdate", i,3);
				Tuesday = xllib.getExcelData("EventStaffAvailabilityUpdate", i,4);
				Wednesday = xllib.getExcelData("EventStaffAvailabilityUpdate", i,5);
				Thursday = xllib.getExcelData("EventStaffAvailabilityUpdate", i,6);
				Friday = xllib.getExcelData("EventStaffAvailabilityUpdate", i,7);
				Saturday = xllib.getExcelData("EventStaffAvailabilityUpdate", i,8);
				Sunday = xllib.getExcelData("EventStaffAvailabilityUpdate", i,9);
				Mornings = xllib.getExcelData("EventStaffAvailabilityUpdate", i,10);
				Afternoons = xllib.getExcelData("EventStaffAvailabilityUpdate", i,11);
				Evenings = xllib.getExcelData("EventStaffAvailabilityUpdate", i,12);
				Graveyard = xllib.getExcelData("EventStaffAvailabilityUpdate", i,13);
				
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				//Calling Login URL method
				loginLogout.loginURLActions(driver,loginURL);
				
				//Calling Member Login Method
				loginLogout.loginPageActions(driver,loginEmail,loginPassword);
						
				
				//Checking whether login credentials valid or not
				if(MemberLoginLogoutPage.loggedInStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("EventStaffAvailabilityUpdate", i, 14, isTestPassed);
					
					//Calling chef information info edit actions method
					eventStaffAvailabilityUpdateActions(driver);
					
					if(rowCount !=1)
					{
						//Calling Logout method
						loginLogout.logoutActions(driver);
					}
				}else if(!MemberLoginLogoutPage.loggedInStatus)
			    {
			    	isTestPassed = "FAIL";
					xllib.writeToExcel("EventStaffAvailabilityUpdate", i, 14, isTestPassed);
				}
				/*if(eventStaffAvailabilityUpdateSuccess)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("EventStaffAvailabilityUpdate", i, 15, isTestPassed);
					log.info("Your account "+loginEmail+" has been updated successfully.");	
				}else if(!eventStaffAvailabilityUpdateSuccess)
				{
					isTestPassed = "FAIL";
					xllib.writeToExcel("EventStaffAvailabilityUpdate", i, 15, isTestPassed);
				}*/
			}
		}
		catch(Exception e)
		{
			log.info("Test Execution Failed");
		}
	 }
	 /**
	 * Test Case for updating  Event Staff availability.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void eventStaffAvailabilityUpdateActions(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	 {
		 try
		 {
			log.info("Clicking on Account Info Edit");	
			AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Calling Availability method
			creditCard.clickingOnAvailability(driver);
			
			Thread.sleep(3000);
			
			MondayStatus = driver.findElement(By.id("workdays1")).isSelected();
			TuesdayStatus = driver.findElement(By.id("workdays2")).isSelected();
			WednesdayStatus = driver.findElement(By.id("workdays3")).isSelected();
			ThursdayStatus = driver.findElement(By.id("workdays4")).isSelected();
			FridayStatus = driver.findElement(By.id("workdays5")).isSelected();
			SaturdayStatus = driver.findElement(By.id("workdays6")).isSelected();
			SundayStatus = driver.findElement(By.id("workdays7")).isSelected();
			MorningsStatus = driver.findElement(By.id("worktimes1")).isSelected();
			AfternoonsStatus = driver.findElement(By.id("worktimes2")).isSelected();
			EveningsStatus = driver.findElement(By.id("worktimes3")).isSelected();
			GraveyardStatus = driver.findElement(By.id("worktimes4")).isSelected();
			
			System.out.println("MondayStatus:"+MondayStatus);
			System.out.println("TuesdayStatus:"+TuesdayStatus);
			System.out.println("WednesdayStatus:"+WednesdayStatus);
			System.out.println("ThursdayStatus:"+ThursdayStatus);
			System.out.println("FridayStatus:"+FridayStatus);
			System.out.println("SaturdayStatus:"+SaturdayStatus);
			System.out.println("SundayStatus:"+SundayStatus);
			System.out.println("MorningsStatus:"+MorningsStatus);
			System.out.println("AfternoonsStatus:"+AfternoonsStatus);
			System.out.println("EveningsStatus:"+EveningsStatus);
			System.out.println("GraveyardStatus:"+GraveyardStatus);
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			if(Monday.equalsIgnoreCase(TestConstants.STATUS_TRUE))
        	{
				if(!MondayStatus)
				{
					driver.findElement(By.id("workdays1")).click();
				}
			}	
			
			if(Tuesday.equalsIgnoreCase(TestConstants.STATUS_TRUE))
        	{
				if(!TuesdayStatus)
				{
					driver.findElement(By.id("workdays2")).click();
				}
			}	
			
			if(Wednesday.equalsIgnoreCase(TestConstants.STATUS_TRUE))
        	{
				if(!WednesdayStatus)
				{
					driver.findElement(By.id("workdays3")).click();
				}
			}	
					
			if(Thursday.equalsIgnoreCase(TestConstants.STATUS_TRUE))
        	{
				if(!ThursdayStatus)
				{
					driver.findElement(By.id("workdays4")).click();
				}
			}	
			
			if(Friday.equalsIgnoreCase(TestConstants.STATUS_TRUE))
        	{
				if(!FridayStatus)
				{
					driver.findElement(By.id("workdays5")).click();
				}
			}	
			if(Saturday.equalsIgnoreCase(TestConstants.STATUS_TRUE))
        	{
				if(!SaturdayStatus)
				{
					driver.findElement(By.id("workdays6")).click();
				}
			}	
			if(Sunday.equalsIgnoreCase(TestConstants.STATUS_TRUE))
        	{
				if(!SundayStatus)
				{
					driver.findElement(By.id("workdays7")).click();
				}
			}	
			
			if(Mornings.equalsIgnoreCase(TestConstants.STATUS_TRUE))
        	{
				if(!MorningsStatus)
				{
					driver.findElement(By.id("worktimes1")).click();
				}
			}	
			if(Afternoons.equalsIgnoreCase(TestConstants.STATUS_TRUE))
        	{
				if(!AfternoonsStatus)
				{
					driver.findElement(By.id("worktimes2")).click();
				}
			}	
			if(Evenings.equalsIgnoreCase(TestConstants.STATUS_TRUE))
        	{
				if(!EveningsStatus)
				{
					driver.findElement(By.id("worktimes3")).click();
				}
			}	
			if(Graveyard.equalsIgnoreCase(TestConstants.STATUS_TRUE))
        	{
				if(!GraveyardStatus)
				{
					driver.findElement(By.id("worktimes4")).click();
				}
			}	
			
			//Clicking on Update Button
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.id("edit-bio-submit")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Your bio Information has not updated");
		}		
	}
}