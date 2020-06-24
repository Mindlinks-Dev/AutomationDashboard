/**
 * @author: Basappa Hunsikatti
 * @Created Date :01/12/2015
 * @Updated Date :
 * @Comments This automation class will modify reservation(Cancel Ticket,Credit Me,Credit Guest)
 */
package com.dinnerLab.userPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;

public class MyEventsPage 
{
	 private String loginURL;
	 private String loginEmail;
	 private String loginPassword;
	 public static String city;
	 public static String eventName;
	 public static String seatingDate;
	 public static String seatingTime;
	 public static boolean eventNameAndCityStatus;
	 public static boolean seatingDateStatus;
	 public static boolean seatingTimeStatus;
	 public static boolean creditMeStatus;
	 public static boolean creditGuestStatus;
	 public static boolean CreditedEventTicketStatus;
	 public static boolean CancelledEventTicketStatus;
	 public static String creditORCancelMe;
	 public static String creditORCancelGuest;
	 public static String guestName;
	 public String quantity;
	 public static String totalCreditsAvailable;
	 boolean present;
	 public int rowCount;
	 String isTestPassed="FAIL";
	 public static boolean accountUpdateSuccess;
	 public static boolean oneTimePassToCancelWithinFiveDays;
	 public static boolean eventFiveDaysAway;
	 private static Logger log = Logger.getLogger(MyEventsPage.class);
	 //MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	 
	 
	 /**
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void myEventsInitialPage(WebDriver driver)
	 {
		try
		{
			ExcelLib xllib = new ExcelLib();
			 
			int rowCount = xllib.getRowCount("MyEvents");
			log.info("*********************My Events Logger Initialized******************************* ");
			for(int i=1;i<= rowCount;i++)
			{
				loginURL = xllib.getExcelData("MyEvents", i, 0);
				loginEmail = xllib.getExcelData("MyEvents", i, 1);
				loginPassword = xllib.getExcelData("MyEvents", i, 2);
				city = xllib.getExcelData("MyEvents", i,3);
				eventName = xllib.getExcelData("MyEvents", i,4);
				seatingDate = xllib.getExcelData("MyEvents", i,5);
				seatingTime = xllib.getExcelData("MyEvents", i,6);
				creditORCancelMe = xllib.getExcelData("MyEvents", i,7);
				creditORCancelGuest = xllib.getExcelData("MyEvents", i, 8);
				guestName = xllib.getExcelData("MyEvents", i, 9);
				
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				//Calling Login URL method
				loginLogout.loginURLActions(driver,loginURL);
				
				//Calling Member Login Method
				loginLogout.loginPageActions(driver,loginEmail,loginPassword);
				
				//Clicking on My Events Link
				clickingOnMyEventsLink(driver);
				
				//Checking whether login credentials valid or not
				if(MemberLoginLogoutPage.loggedInStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("MyEvents", i, 10, isTestPassed);
					
					//Calling user account info edit actions method
					eventModifyReservation(driver,i);
					
					//Calling Total Credits Available method
					viewTotalCreditsAvailable(driver);
					
					xllib.writeToExcel("MyEvents", i, 13, totalCreditsAvailable);
					log.info("Total Credits Available:"+totalCreditsAvailable);	
					
					if(rowCount !=1)
					{
						//Calling Logout method
						loginLogout.logoutActions(driver);
					}
				}else if(!MemberLoginLogoutPage.loggedInStatus)
			    {
			    	isTestPassed = "FAIL";
					xllib.writeToExcel("MyEvents", i, 10, isTestPassed);
				}
			}
		}
		catch(Exception e)
		{
			log.info("Test Execution Failed");
		}
	 }
	 /**
	 * Test Case for modifying reservation
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void eventModifyReservation(WebDriver driver,int i) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	 {
		 try
		 {
			 ExcelLib xllib = new ExcelLib();
			 
			 driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);		 
			 eventNameAndCityStatus = driver.findElement(By.xpath("//div[div[table[thead[tr[th[contains(text(),'"+eventName+"')]]]]]]/div/table/tbody/tr/td[contains(text(),'"+city+"')]")).isDisplayed();
			 System.out.println("eventNameAndCityStatus:"+eventNameAndCityStatus); 
			 
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 seatingDateStatus = driver.findElement(By.xpath("//div[div[table[thead[tr[th[contains(text(),'"+eventName+"')]]]]]]/div/table/tbody/tr/td[contains(text(),'"+seatingDate+"')]")).isDisplayed();
			 System.out.println("seatingDateStatus:"+seatingDateStatus);
			 
			 seatingTimeStatus = driver.findElement(By.xpath("//div[div[table[thead[tr[th[contains(text(),'"+eventName+"')]]]]]]/div/table/tbody/tr/td[contains(text(),'"+seatingTime+"')]")).isDisplayed();
			 System.out.println("seatingTimeStatus:"+seatingTimeStatus);
			 
			 quantity = driver.findElement(By.xpath("//div[div[table[thead[tr[th[contains(text(),'"+eventName+"')]]]]]]/div/table/tbody/tr[4]/td[2]")).getText();
			 System.out.println("quantity:"+quantity);
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 
			 if(eventNameAndCityStatus)
			 {
				 if(seatingDateStatus)
				 {
					 if(seatingTimeStatus)
					 {
						 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						 driver.findElement(By.xpath("//div[div[table[thead[tr[th[contains(text(),'"+eventName+"')]]]]]]/a[contains(text(),'Modify Reservation')]")).click();
						 driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
						 Thread.sleep(1000);
						 try
						 {
							 eventFiveDaysAway = driver.findElement(By.xpath("//div[span[contains(text(),'"+eventName+"')]]/div[1]/span[contains(text(),'if you cancel today you will not receive credit')]")).isDisplayed();
							 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
							 System.out.println("eventFiveDaysAway:"+eventFiveDaysAway);
						 }catch(Exception e)
						 {
							 e.printStackTrace();
						 } 
						 /*if(seatingTimeStatus)
						 {
							 try
							 {
								 oneTimePassToCancelWithinFiveDays = driver.findElement(By.xpath("//div[span[contains(text(),'"+eventName+"')]]/div[1]/span[contains(text(),'use your one time pass to cancel within 5 days and still receive credit')]")).isDisplayed();
								 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
								 System.out.println("oneTimePassToCancelWithinFiveDays:"+oneTimePassToCancelWithinFiveDays);
							 }catch(Exception e)
							 {
								 e.printStackTrace();
									
							 } 
						 }*/
						
						 creditMeStatus = driver.findElement(By.xpath("//div[span[contains(text(),'"+eventName+"')]]/div[2]/div[3]/div[3]/span")).isDisplayed();
						 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
						 System.out.println("creditMeStatus:"+creditMeStatus);
							
						 if(creditORCancelMe.equalsIgnoreCase(TestConstants.STATUS_TRUE))
						 {
							 /*if(oneTimePassToCancelWithinFiveDays)
							 {
								//Calling Credit Me EventTicket method
								 creditMeEventTicket(driver);
								 
								 //Verify Credited Event Ticket
								 verifyCreditedEventTicket(driver);
							 }*/
							 if(eventFiveDaysAway)
							 {
								//Calling Cancel me method
								 cancelMeEventTicket(driver);
								 
								 //Verify Cancelled Event Ticket
								 verifyCancelledEventTicket(driver);
								 
								 if(CancelledEventTicketStatus)
								 {
									 isTestPassed="PASS";
									 xllib.writeToExcel("MyEvents", i, 12, isTestPassed);
								 }else if(!CancelledEventTicketStatus)
								 {
									 isTestPassed = "FAIL";
									 xllib.writeToExcel("MyEvents", i, 12, isTestPassed);
								 }
							 }else if(!eventFiveDaysAway)
							 {
								//Calling Credit Me EventTicket method
								 creditMeEventTicket(driver);
								 
								 //Verify Credited Event Ticket
								 verifyCreditedEventTicket(driver);
								 
								 if(CreditedEventTicketStatus)
									{
										isTestPassed="PASS";
										xllib.writeToExcel("MyEvents", i, 11, isTestPassed);
									}else if(!CreditedEventTicketStatus)
								    {
								    	isTestPassed = "FAIL";
										xllib.writeToExcel("MyEvents", i, 11, isTestPassed);
									}
							 }
						 }else if(creditORCancelGuest.equalsIgnoreCase(TestConstants.STATUS_TRUE))
						 {
							 //Calling Credit/Cancel Guest method
							 creditOrCancelGuest(driver);
						 }
					 }
				 }
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("My Events modify reservation unsuccessful.");
		}		
	}
	/**
	 * Test Case for Credit Me
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public void creditMeEventTicket(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[span[contains(text(),'"+eventName+"')]]/div[2]/div[3]/div[3]/span")).click();
			if(quantity.equalsIgnoreCase(TestConstants.NUMBER_ONE))
			{
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				driver.findElement(By.id("creditSingle")).click();
				System.out.println("Credit clicking single");
			    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			}else
			{
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				driver.findElement(By.id("creditAll")).click();
				System.out.println("Credit clicking all");
			    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Test Case for Cancel Event Ticket
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public void cancelMeEventTicket(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[span[contains(text(),'"+eventName+"')]]/div[2]/div[3]/div[3]/span")).click();
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			
			if(quantity.equalsIgnoreCase(TestConstants.NUMBER_ONE))
			{
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				driver.findElement(By.id("cancelSingle")).click();
				System.out.println("clicking single");
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			}else
			{
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				driver.findElement(By.id("cancelAll")).click();
				System.out.println("clicking all");
			    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("exception while clicking");
		}
	}
	/**
	 * Test Case for Credit/Cancel Guest
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public void creditOrCancelGuest(WebDriver driver)
	{
		try
		{
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			//creditGuestStatus = driver.findElement(By.xpath("//span[contains(text(),'Credit Guest')]")).isDisplayed();
			//System.out.println("creditGuestStatus:"+creditGuestStatus);
			
			//if(creditGuestStatus)
			//{
				System.out.println("guestName:"+guestName);
				if(guestName.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
					 //driver.findElement(By.xpath("//div[div[contains(text(),'"+guest1+"')]]/span[contains(text(),'Credit Guest')]")).click();
					 driver.findElement(By.xpath("//span[contains(text(),'Credit Me')]")).click();
					 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
					 driver.findElement(By.id("creditSingle")).click();
					 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
				}
			//}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Test Case for view Total Credits Available
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public void viewTotalCreditsAvailable(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//a[@data-panel='credits']")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(3000);
			totalCreditsAvailable = driver.findElement(By.xpath("//span[@id='userCreditSum']")).getText();
			System.out.println("totalCreditsAvailable:"+totalCreditsAvailable);
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Test Case for verifying Credited Event Ticket
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public void verifyCreditedEventTicket(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			CreditedEventTicketStatus = driver.findElement(By.xpath("//span[contains(text(),'You have successfully been credited')]")).isDisplayed();
			System.out.println("CreditedEventTicketStatus:"+CreditedEventTicketStatus);
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Test Case for clicking on My Events Link
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 */
	public void clickingOnMyEventsLink(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Actions actions = new Actions(driver);
			
			WebElement parent = driver.findElement(By.xpath("//li[@class='parent']")); // member drop down
			actions.moveToElement(parent).build().perform();

			driver.findElement(By.xpath("//a[contains(text(),'My Events')]")).click(); 
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
					
	}
	/**
	 * Test Case for verifying Cancelled Event Ticket
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public void verifyCancelledEventTicket(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			CancelledEventTicketStatus = driver.findElement(By.xpath("//span[contains(text(),'You have successfully been canceled')]")).isDisplayed();
			System.out.println("CancelledEventTicketStatus:"+CancelledEventTicketStatus);
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}