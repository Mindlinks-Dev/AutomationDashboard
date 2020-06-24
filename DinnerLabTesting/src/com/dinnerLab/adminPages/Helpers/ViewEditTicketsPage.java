/**
 * @author: Basappa Hunsikatti
 * @Created Date :15/12/2015
 * @Updated Date :
 * @Comments:This automation class add tickets and modify reservation
*/
package com.dinnerLab.adminPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.MemberLoginLogoutPage;
import com.dinnerLab.util.ExcelLib;

public class ViewEditTicketsPage 
{
	private String AdminEmail;
	private String AdminPassword;
	private String loginURL;
	private String addTicketStatus;
	private String firstName;
	private String lastName;
	private String usersEmail;
	private String quantity;
	private String contactEmail;
	private String eventCode;
	private String ticketType;
	private String amountPaid;
	private String notesStatus;
	private String notes;
	private String modifyReservation;
	private String modifyTicketAction;
	private String changeReservation;
	private String newContactEmail;
	private String receiveConfirmationEmail;
	private String newEventCode;
	private String currentStatus;
	private String action;
	private String newContactEmailStatus;
	private String newEventCodeStatus;
	public boolean addedTicketStatus;
	public boolean changeReservationStatus;
	public int rowCount;
	public int rowsCount;
	int i=0;
	private static Logger log = Logger.getLogger(ViewEditTicketsPage.class);
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
								
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				//Calling login url method
				loginLogout.loginURLActions(driver, loginURL);
								
				//Calling Login method
				loginLogout.loginPageActions(driver,AdminEmail,AdminPassword);
								
				CreateNewEventPage newEvent = new CreateNewEventPage();
				
				//Calling Super Admin Link method 
				newEvent.clickingOnSuperAdminLink(driver,loginURL);
				
				//Calling clicking on View Edit Tickets Link method 
				clickingViewEditTicketsLink(driver);
				
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
	 * Test Case for Reading the excel data and calling add tickets and modify reservation methods
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 * @throws InterruptedException 
	 */
	public void viewEditTicketsInitialPage(WebDriver driver) throws InvalidFormatException, InterruptedException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowCount= xllib.getRowCount("ViewEditTickets");
			log.info("*********************View/Edit Tickets Logger Initialized*********************************************************");
			//log.info("City  ||"+" Event Name ||" + "   Seating ||" + "  User's Email || " + " Quantity  || " + " Status");
	 		//log.info("*********************************************************************************************************************");
			for (i = 1; i <= rowCount; i++) 
			{				
				//Reading View Edit Tickets values
		 		addTicketStatus = xllib.getExcelData("ViewEditTickets", i, 0);
		 		firstName = xllib.getExcelData("ViewEditTickets", i, 1);
		 		lastName = xllib.getExcelData("ViewEditTickets", i, 2);
		 		contactEmail = xllib.getExcelData("ViewEditTickets", i, 3);
		 		eventCode = xllib.getExcelData("ViewEditTickets", i, 4);
		 		ticketType = xllib.getExcelData("ViewEditTickets", i, 5);
		 		quantity = xllib.getExcelData("ViewEditTickets", i, 6);
		 		amountPaid = xllib.getExcelData("ViewEditTickets", i, 7);
		 		notesStatus = xllib.getExcelData("ViewEditTickets", i, 8);
		 		notes = xllib.getExcelData("ViewEditTickets", i, 9);
		 		modifyReservation = xllib.getExcelData("ViewEditTickets", i, 10);
		 		changeReservation = xllib.getExcelData("ViewEditTickets", i, 11);
		 		newContactEmailStatus = xllib.getExcelData("ViewEditTickets", i, 12);
		 		newContactEmail = xllib.getExcelData("ViewEditTickets", i, 13);
		 		receiveConfirmationEmail = xllib.getExcelData("ViewEditTickets", i, 14);
		 		newEventCodeStatus = xllib.getExcelData("ViewEditTickets", i, 15);
		 		newEventCode = xllib.getExcelData("ViewEditTickets", i, 16);
		 		modifyTicketAction = xllib.getExcelData("ViewEditTickets", i, 17);
		 		action = xllib.getExcelData("ViewEditTickets", i, 18);
		 		
		 		if(addTicketStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
		 			//Calling add tickets method
		 			addTicketsActions(driver);
		 			
					//Calling verify added ticket method
			 		verifyAddedTicket(driver);
			 		
			 		if(addedTicketStatus)
					{
						isTestPassed="PASS";
						xllib.writeToExcel("ViewEditTickets", i, 19, isTestPassed);
						//log.info(","+city +"," +event +"," + seating + "," + usersEmail+"," + quantity +",Pass");
					}
					else if(!addedTicketStatus)
					{
						isTestPassed="FAIL";
						xllib.writeToExcel("ViewEditTickets", i, 19, isTestPassed);
						//log.info(","+city +"," +event +"," + seating + "," + usersEmail+"," + quantity +",Fail");
					}
				}//End of Add Ticket if condition
		 		else if(modifyReservation.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
		 			//Calling modify reservation actions method
		 			modifyReservationActions(driver);
		 			
		 			if(changeReservationStatus)
					{
						isTestPassed="PASS";
						xllib.writeToExcel("ViewEditTickets", i, 20, isTestPassed);
					}
					else if(!changeReservationStatus)
					{
						isTestPassed="FAIL";
						xllib.writeToExcel("ViewEditTickets", i, 20, isTestPassed);
					}
				}
		 	}//End of FOR LOOP
		 }catch(NullPointerException e)
		 {
			 e.printStackTrace();
		 }
	}
	/**
		 * Test Case for clicking on view edit tickets link
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	*/
	 public  void clickingViewEditTicketsLink(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         driver.findElement(By.xpath("//a[@href='/superadmin/tickets']")).click();
			 log.info("Clicked on View/Edit Tickets Link");
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }

	 /**
		 * Test Case for adding tickets
		 * Input: WebDriver
		 * Output: Void
		 * @throws InvalidFormatException 
	 */
	public void addTicketsActions(WebDriver driver) 
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			//Clicking on Add Tickets link
			driver.findElement(By.xpath("//a[@href='add']")).click();			
			
			driver.findElement(By.name("firstName")).clear();		
			driver.findElement(By.name("firstName")).sendKeys(firstName);
			
			driver.findElement(By.name("lastName")).clear();		
			driver.findElement(By.name("lastName")).sendKeys(lastName);
			
			driver.findElement(By.name("email")).clear();		
			driver.findElement(By.name("email")).sendKeys(contactEmail);
			
			driver.findElement(By.name("eventCode")).clear();		
			driver.findElement(By.name("eventCode")).sendKeys(eventCode);
			
			//Selecting Ticket Type
			WebElement typeOfTicket = driver.findElement(By.id("ticketType"));
			Select st1 = new Select(typeOfTicket);
			st1.selectByVisibleText(ticketType);
			
			driver.findElement(By.name("quantity")).clear();		
			driver.findElement(By.name("quantity")).sendKeys(quantity);
			
			driver.findElement(By.name("amountPaid")).clear();		
			driver.findElement(By.name("amountPaid")).sendKeys(amountPaid);
			
			if(notesStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.findElement(By.name("notes")).clear();		
				driver.findElement(By.name("notes")).sendKeys(notes);
			}
			driver.findElement(By.xpath("//input[@value='Add Ticket']")).click();	
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
			ViewEditCreditsPage credits = new ViewEditCreditsPage();
			credits.handlingAlertPopup(driver);
		}
		catch(WebDriverException wde)
		{
			wde.printStackTrace();
		}
	}
	/**
	 * Test Case for verify added ticket
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void verifyAddedTicket(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 addedTicketStatus = driver.findElement(By.xpath("//div[contains(text(),'Successfully added')]")).isDisplayed();
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			 System.out.println("addedTicketStatus:"+addedTicketStatus);
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 /**
		 * Test Case for modify reservation
		 * Input: WebDriver
		 * Output: Void
		 * @throws InvalidFormatException 
	 */
	public void modifyReservationActions(WebDriver driver) 
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			driver.findElement(By.name("eventCode")).clear();		
			driver.findElement(By.name("eventCode")).sendKeys(eventCode);
			driver.findElement(By.name("primaryEmail")).clear();		
			driver.findElement(By.name("primaryEmail")).sendKeys(contactEmail);
			driver.findElement(By.xpath("//input[@value='Search']")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//a[contains(text(),'Modify Reservation')]")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
			if(changeReservation.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
	 			//Calling change reservation actions method
	 			changeReservationActions(driver);
	 			
	 			//Calling verify Change Reservation Actions
	 			verifyChangeReservationActions(driver);
	 		}
			if(modifyTicketAction.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
	 			//Calling change ticket actions method
	 			changeTicketsActions(driver);
			}
			
		}
		catch(WebDriverException wde)
		{
			wde.printStackTrace();
		}
	}
	 /**
	 * Test Case for change reservation
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void changeReservationActions(WebDriver driver) 
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			
			if(newContactEmailStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.findElement(By.name("email")).clear();		
				driver.findElement(By.name("email")).sendKeys(newContactEmail);
			}
			if(receiveConfirmationEmail.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.findElement(By.id("modifyReservationReceiveEmail")).click();
			}
			if(newEventCodeStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.findElement(By.name("eventCode")).clear();		
				driver.findElement(By.name("eventCode")).sendKeys(newEventCode);
			}
			driver.findElement(By.xpath("//input[@value='Edit Ticket']")).click();	
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
			ViewEditCreditsPage credits = new ViewEditCreditsPage();
			credits.handlingAlertPopup(driver);
		}
		catch(WebDriverException wde)
		{
			wde.printStackTrace();
		}
	}
	/**
	 * Test Case for change ticket actions
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void changeTicketsActions(WebDriver driver) 
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			currentStatus = driver.findElement(By.xpath("//td[@class='defaultCell currentStatus']")).getText();
			System.out.println("currentStatus:"+currentStatus);
			
			if(!action.equalsIgnoreCase(currentStatus))
			{
				System.out.println("Before action");
				driver.findElement(By.xpath("//button[contains(text(),'"+action+"')]")).click();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				System.out.println("after action");
				
				Alert alert = driver.switchTo().alert();
				alert.accept();
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			}
			
			if(receiveConfirmationEmail.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				driver.findElement(By.id("modifyReservationReceiveEmail")).click();
			}
			
			if(notesStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				driver.findElement(By.xpath("//textarea[@class='faq-textarea']")).clear();		
				driver.findElement(By.xpath("//textarea[@class='faq-textarea']")).sendKeys(notes);
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			}
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//input[@value='Save']")).click();	
			//driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			
			ViewEditCreditsPage credits = new ViewEditCreditsPage();
			credits.handlingAlertPopup(driver);
		}
		catch(WebDriverException wde)
		{
			wde.printStackTrace();
		}
	}
	/**
	 * Test Case for verify Change Reservation
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void verifyChangeReservationActions(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 changeReservationStatus = driver.findElement(By.xpath("//div[contains(text(),'Successfully updated the reservation.')]")).isDisplayed();
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			 System.out.println("changeReservationStatus:"+changeReservationStatus);
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
}


