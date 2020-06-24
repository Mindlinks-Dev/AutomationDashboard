/**
 * @author: Basappa Hunsikatti
 * @Created Date :09/12/2015
 * @Updated Date :
 * @Comments:This automation class add user to waitlist
*/
package com.dinnerLab.adminPages.Helpers;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.dinnerLab.userPages.Helpers.MemberLoginLogoutPage;
import com.dinnerLab.util.ExcelLib;

public class AddUserToWaitlistPage 
{
	private String AdminEmail;
	private String AdminPassword;
	private String loginURL;
	private String event;
	private String city;
	private String seating;
	private String usersEmail;
	private String quantity;
	public boolean waitListStatus;
	public int rowCount;
	public int rowsCount;
	int i=0;
	private static Logger log = Logger.getLogger(AddUserToWaitlistPage.class);
	public int CurrentRow = 0;
	String isTestPassed="FAIL";
			
	public boolean AdminLogin(WebDriver driver)
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowsCount= xllib.getRowCount("AdminLogin");
		 	for (i = 1; i <= rowsCount; i++) 
			{
		 		loginURL = xllib.getExcelData("AdminLogin", i, 0);
		 		AdminEmail = xllib.getExcelData("AdminLogin", i, 1);
				AdminPassword = xllib.getExcelData("AdminLogin", i, 2);
								
				//System.out.println("Admin email:"+AdminEmail);
				//System.out.println("Admin password:"+AdminPassword);
				//System.out.println("loginURL:"+loginURL);
				
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				//Calling login url method
				loginLogout.loginURLActions(driver, loginURL);
								
				//Calling Login method
				loginLogout.loginPageActions(driver,AdminEmail,AdminPassword);
								
				CreateNewEventPage newEvent = new CreateNewEventPage();
				
				//Calling Super Admin Link method 
				newEvent.clickingOnSuperAdminLink(driver,loginURL);
				
				//Calling clicking on waitlist Link method 
				clickingOnWaitlistLink(driver);
				
				//Calling child handle window method
				newEvent.childWindowHandle(driver);
			}
		 	return true;
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 return false;
		 }
	}
	/**
	 * Test Case for Reading the excel data and calling add user to waitlist methods
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 * @throws InterruptedException 
	 */
	public void addUserToWaitlistInitialPage(WebDriver driver) throws InvalidFormatException, InterruptedException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowCount= xllib.getRowCount("AddUserToWaitlist");
			log.info("*********************Add User To Waitlist Logger Initialized*********************************************************");
			log.info("City  ||"+" Event Name ||" + "   Seating ||" + "  User's Email || " + " Quantity  || " + " Status");
	 		log.info("*********************************************************************************************************************");
			for (i = 1; i <= rowCount; i++) 
			{				
				//Reading creating Customer Event Links values
		 		city = xllib.getExcelData("AddUserToWaitlist", i, 0);
		 		event = xllib.getExcelData("AddUserToWaitlist", i, 1);
		 		seating = xllib.getExcelData("AddUserToWaitlist", i, 2);
		 		usersEmail = xllib.getExcelData("AddUserToWaitlist", i, 3);
		 		quantity = xllib.getExcelData("AddUserToWaitlist", i, 4);
		 		
		 		System.out.println("city:"+city);
				//Calling add user to waitlist method
				addUserToWaitlistActions(driver);
				
				//Calling verify add user to waitlist method
				verifyAddUserToWaitlist(driver);
						
				if(waitListStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("AddUserToWaitlist", i, 5, isTestPassed);
					log.info(","+city +"," +event +"," + seating + "," + usersEmail+"," + quantity +",Pass");
				}
				else if(!waitListStatus)
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("AddUserToWaitlist", i, 5, isTestPassed);
					log.info(","+city +"," +event +"," + seating + "," + usersEmail+"," + quantity +",Fail");
				}
			}//End of FOR LOOP
		 }catch(NullPointerException e)
		 {
			 e.printStackTrace();
		 }
	}
	/**
		 * Test Case for clicking on waitlist Link
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	*/
	 public  void clickingOnWaitlistLink(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         driver.findElement(By.xpath("//a[contains(text(),'Waitlist')]")).click();
			 log.info("Clicked on Waitlist Link");
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }

	 /**
		 * Test Case for adding user to waitlist
		 * Input: WebDriver
		 * Output: Void
		 * @throws InvalidFormatException 
	 */
	public void addUserToWaitlistActions(WebDriver driver) 
	{
		try
		{
			System.out.println("inside add user actions");
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
						
			//Selecting City
			WebElement cityName = driver.findElement(By.name("city"));
			Select st1 = new Select(cityName);
			st1.selectByVisibleText(city);
			
			//Selecting Event
			WebElement eventName = driver.findElement(By.name("event"));
			Select st2 = new Select(eventName);
			st2.selectByVisibleText(event);
			
			//Selecting Seating
			WebElement seatingDetails = driver.findElement(By.name("seating"));
			Select st3 = new Select(seatingDetails);
			st3.selectByVisibleText(seating);
			
			driver.findElement(By.name("email")).clear();		
			driver.findElement(By.name("email")).sendKeys(usersEmail);
			
			driver.findElement(By.name("quantity")).clear();		
			driver.findElement(By.name("quantity")).sendKeys(quantity);
			
			driver.findElement(By.id("submit")).click();	
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		}
		catch(WebDriverException wde)
		{
			wde.printStackTrace();
		}
	}
	/**
	 * Test Case for verify added user to waitlist
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void verifyAddUserToWaitlist(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 waitListStatus = driver.findElement(By.xpath("//a[contains(text(),'"+usersEmail+"')]")).isDisplayed();
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			 System.out.println("waitListStatus:"+waitListStatus);
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
}


