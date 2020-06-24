/**
 * @author: Basappa Hunsikatti
 * @Created Date :23/11/2015
 * @Updated Date :
 * @Comments:This automation class Create Custom Event Links 
*/
package com.dinnerLab.adminPages.Helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.MemberLoginLogoutPage;
import com.dinnerLab.util.ExcelLib;
import com.pack.AutomateLoader;

public class CreateCustomEventLinksPage 
{
	private String AdminEmail;
	private String AdminPassword;
	private String loginURL;
	private String eventTitle;
	private String city;
	private String seating;
	private String ticketPrice;
	private String linkExpirationDate;
	private String linkGraphicsStatus;
	private String linkGraphics;
	private String linkCapacityType;
	private String linkCapacity;
	private String initialDisplayQuantity;
	private String maximumDisplayQuantity;
	private String linkIdentifier;
	private String noteStatus;
	private String note;
	public static boolean createCustomEventLinkStatus;
	public static String customEventLinkURL;
	public int rowCount;
	public int rowsCount;
	int i=0;
	private static Logger log = Logger.getLogger(CreateCustomEventLinksPage.class);
	public static String password1;
	boolean status = false;
	public int CurrentRow = 0;
	Connection con = null;
	ResultSet rs = null;
	Connection newcon = null; //for Local DB
	String userCreatedStatus = null;
	String JobNumber="";
	String ErrorMessage="";
	String errMsg ="";
	String userName ="";
	//long endTime   = System.currentTimeMillis();
	long endTime   = 0;
	String isTestPassed="FAIL";
	public int ProcessRecords = 0;
	//FerrariMPHandler obj = new FerrariMPHandler();
	//MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
	//CreateNewEventPage newEvent = new CreateNewEventPage();
	static final String DB_URL = AutomateLoader.config.getProperty("DL_DEVELOPMENT_DB_URL");
	//static final String DB_URL = AutomateLoader.config.getProperty("FERRARI_DBURL");
	
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
				System.out.println("loginStatus:"+MemberLoginLogoutPage.loggedInStatus);
				
				CreateNewEventPage newEvent = new CreateNewEventPage();
				
				//Calling Super Admin Link method 
				newEvent.clickingOnSuperAdminLink(driver,loginURL);
				
				//Calling Create Customer Event Links method 
				clickingOnCreateCustomEventLinks(driver);
				
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
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 * @throws InterruptedException 
	 */
	public void createCustomEventLinksInitialPage(WebDriver driver) throws InvalidFormatException, InterruptedException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowCount= xllib.getRowCount("CreateCustomEventLinks");
			log.info("*********************Create New Event Logger Initialized******************************* ");
		 	for (i = 1; i <= rowCount; i++) 
			{				
				//Reading creating Customer Event Links values
				eventTitle = xllib.getExcelData("CreateCustomEventLinks", i, 0);
		 		city = xllib.getExcelData("CreateCustomEventLinks", i, 1);
		 		seating = xllib.getExcelData("CreateCustomEventLinks", i, 2);
		 		ticketPrice = xllib.getExcelData("CreateCustomEventLinks", i, 3);
		 		linkExpirationDate = xllib.getExcelData("CreateCustomEventLinks", i, 4);
		 		linkGraphicsStatus = xllib.getExcelData("CreateCustomEventLinks", i, 5);
		 		linkGraphics = xllib.getExcelData("CreateCustomEventLinks", i, 6);
		 		linkCapacityType = xllib.getExcelData("CreateCustomEventLinks", i, 7);
		 		linkCapacity = xllib.getExcelData("CreateCustomEventLinks", i, 8);
		 		initialDisplayQuantity = xllib.getExcelData("CreateCustomEventLinks", i, 9);
		 		maximumDisplayQuantity = xllib.getExcelData("CreateCustomEventLinks", i, 10);
		 		linkIdentifier = xllib.getExcelData("CreateCustomEventLinks", i, 11);
		 		noteStatus = xllib.getExcelData("CreateCustomEventLinks", i, 12);
		 		note = xllib.getExcelData("CreateCustomEventLinks", i, 13);
		 		
		 		//Calling Search Event Name method
				searchEventName(driver);
				
				//Calling Create Custom Event Link method
				createCustomEventLinksActions(driver);
				
				//Calling Fetching and verifying Custom Event Link method
				fetchAndVerifyCustomEventLink(driver);
						
				//Calling clicking on go back link method
				//newEvent.clickingOnGoBackLink(driver);
						
				if(createCustomEventLinkStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("CreateCustomEventLinks", i, 14, isTestPassed);
					xllib.writeToExcel("CreateCustomEventLinks", i, 15, customEventLinkURL);
				}
				else
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("CreateCustomEventLinks", i, 14, isTestPassed);
					xllib.writeToExcel("CreateCustomEventLinks", i, 15, isTestPassed);
				}
			}//End of FOR LOOP
		 }catch(NullPointerException e)
		 {
			 e.printStackTrace();
		 }
	}
	/**
		 * Test Case for clicking on Create Custom Event Links
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	*/
	 public  void clickingOnCreateCustomEventLinks(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         driver.findElement(By.xpath("//a[contains(text(),'Create Custom Event Links')]")).click();
			 /*WebElement viewEditEvents = driver.findElement(By.xpath("//a[contains(text(),'View/edit Events')]"));
			 Actions action= new Actions(driver);
			 action.contextClick(viewEditEvents).sendKeys(Keys.ARROW_DOWN).click().build().perform();*/
	         log.info("Clicked on Create Customer Event Links");
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }

	 /**
		 * Test Case for creating custom event links
		 * Input: WebDriver
		 * Output: Void
		 * @throws InvalidFormatException 
	 */
	public void createCustomEventLinksActions(WebDriver driver) 
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			driver.findElement(By.xpath("//div[div[h2[contains(text(),'Custom Event Link')]]]/div[3]/div/div/div/h4/a")).click();
			
			WebElement seat = driver.findElement(By.id("seating"));
			Select st1 = new Select(seat);
			st1.selectByVisibleText(seating);
			 
			driver.findElement(By.id("ticket_price")).clear();		
			driver.findElement(By.id("ticket_price")).sendKeys(ticketPrice);
			
			//WebElement ticket = driver.findElement(By.id("ticket_price"));
			//Select st2 = new Select(ticket);
			//st2.selectByVisibleText(ticketPrice); 
			 
			driver.findElement(By.id("link_expiration_date")).clear();		
			driver.findElement(By.id("link_expiration_date")).sendKeys(linkExpirationDate);
			
			if(linkGraphicsStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.findElement(By.id("link_graphic")).clear();		
				driver.findElement(By.id("link_graphic")).sendKeys(linkGraphics);
			}
			
			WebElement capacity = driver.findElement(By.id("link_capacity_type"));
			Select st3 = new Select(capacity);
			st3.selectByVisibleText(linkCapacityType); 
			
			if(linkCapacityType.equalsIgnoreCase(TestConstants.MATCH_SEATING_CAPACITY_WITH_LIMIT)||linkCapacityType.equalsIgnoreCase(TestConstants.SUPERSEDE_SEATING_CAPACITY))
			{
				driver.findElement(By.id("link_capacity")).clear();		
				driver.findElement(By.id("link_capacity")).sendKeys(linkCapacity);
			}
			driver.findElement(By.id("initial_display_quantity")).clear();		
			driver.findElement(By.id("initial_display_quantity")).sendKeys(initialDisplayQuantity);
			
			driver.findElement(By.name("maximum_display_quantity")).clear();		
			driver.findElement(By.name("maximum_display_quantity")).sendKeys(maximumDisplayQuantity);
			
			driver.findElement(By.id("link_identifier")).clear();		
			driver.findElement(By.id("link_identifier")).sendKeys(linkIdentifier);
			
			if(noteStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.findElement(By.id("notes")).clear();		
				driver.findElement(By.id("notes")).sendKeys(note);
			}
						
			driver.findElement(By.xpath("//button[@type='submit']")).click();	
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		}
		catch(WebDriverException wde)
		{
			wde.printStackTrace();
		}
	}
	
  /**
	 * Test Case for search Event name
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public  void searchEventName(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElement(By.name("searcheventname")).clear();
			driver.findElement(By.name("searcheventname")).sendKeys(eventTitle);
			
			WebElement searchEventCity = driver.findElement(By.name("city"));
			Select st13 = new Select(searchEventCity);
			st13.selectByVisibleText(city);
			
			driver.findElement(By.id("search")).click();
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			driver.findElement(By.xpath("//input[@value='Custom Event Links']")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Test Case for fetch and verify created custom event link
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void fetchAndVerifyCustomEventLink(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 createCustomEventLinkStatus = driver.findElement(By.xpath("//a[contains(text(),'"+linkIdentifier+"')]")).isDisplayed();
			 System.out.println("createCustomEventLinkStatus:"+createCustomEventLinkStatus);
			 if(createCustomEventLinkStatus)
			 {
				 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
				 customEventLinkURL = driver.findElement(By.xpath("//a[contains(text(),'"+linkIdentifier+"')]")).getText();
				 System.out.println("customEventLinkURL:"+customEventLinkURL);
				 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			 }
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
}


