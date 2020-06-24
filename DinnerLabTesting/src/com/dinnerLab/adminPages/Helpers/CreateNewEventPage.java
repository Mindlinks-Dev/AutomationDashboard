/**
 * @author: Basappa Hunsikatti
 * @Created Date :09/11/2015
 * @Updated Date :
 * @Comments:This automation class create new event.
*/
package com.dinnerLab.adminPages.Helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.FreeMemberSignUpPage;
import com.dinnerLab.userPages.Helpers.MemberLoginLogoutPage;
import com.dinnerLab.util.ExcelLib;
import com.pack.AutomateLoader;
import com.pack.ScriptHandler;

public class CreateNewEventPage 
{
	private String AdminEmail;
	private String AdminPassword;
	private String Fname;
	private String Lname;
	private String email;
	private String loginURL;
	private String GenarateRandomPassword;
	private String passwordForUser;
	private String City;
	private String userType;
	private String SendEmail;
	private String linkToPromoCode;
	private String PromoCode;
	private String RenewalDateConfigure;
	private String RenewalDate;
	private String superAdminURL;
	private String eventTitle;
	private String eventSubtitleStatus;
	private String eventSubtitle;
	private String dlRegion;
	private String eventDate;
	private String ticketPrice;
	private String day;
	private String premium;
	private String closeDate;
	private String webStatus;
	private String dayPublishFormat;
	private String seatingPublishFormat;
	private String locationStatus;
	private String location;
	private String selectMemberReleaseDate;
	private String displayOrder;
	private String tourAllergyLanguageStaus;
	private String tourAllergyLanguage;
	private String eventIdentifier;
	private String memberReleaseDate;
	private String selectMembersOnly;
	private String foodAllergiesStatus;
	private String vegetarian;
	private String vegan;
	private String treenuts;
	private String shellfish;
	private String pescetarian;
	private String peanuts;
	private String lactose;
	private String gluten;
	private String graphicHeadingStaus;
	private String graphicHeading;
	private String graphicStaus;
	private String graphic;
	private String startTime;
	private String seatingDateStatus;
	private String seatingDate;
	private String seatingDayStatus;
	private String seatingDay;
	private String dlRegrionStatus;
	private String region;
	private String ticketType;
	private String webCapacity;
	private String dispQuantity;
	private String nonMemDispQuantity;
	private String dateOrder;
	private String seatingOrder;
	private String seatingStatus;
	private String seatingEventIdentifier;
	private String partnerSeatsStatus;
	private String partnerSeats;
	private String courseName;
	private String courseOrder;
	private String courseIngredientsStatus;
	private String courseIngredients;
	private String courseStatusCheck;
	private String courseStatus;
	public static String addSeatingValue;
	private String amuse;
	public static boolean errorMessageStatus;
	public static boolean createNewEventStatus;
	public static boolean addSeatingStatus;
	public static boolean singleEventViewUrlStatus;
	public static boolean editEventsStatus;
	public static String verifyCourseStatus;
	public static String eventCode;
	public static String ticketsLink;
	public static String nonEventCode;
	public static String singleEventViewURL;
	public static String doYouWantToCreateEvent;
	public static String doYouWantToAddSeating;
	public static String doYouWantToAddCourse;
	public String Currenthandle;
	public int rowCount;
	public int rowsCount;
	int i=0;
	private static Logger log = Logger.getLogger(CreateNewEventPage.class);
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
								
				System.out.println("Admin email:"+AdminEmail);
				System.out.println("Admin password:"+AdminPassword);
				System.out.println("loginURL:"+loginURL);
				
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				//Calling login url method
				loginLogout.loginURLActions(driver, loginURL);
								
				//Calling Login method
				loginLogout.loginPageActions(driver,AdminEmail,AdminPassword);
				System.out.println("loginStatus:"+MemberLoginLogoutPage.loggedInStatus);
				
				//Calling Super Admin Link method 
				clickingOnSuperAdminLink(driver,loginURL);
				
				//Calling View Edit Link method 
				clickingOnViewEditEventsLink(driver);
				
				//Calling child handle window method
				childWindowHandle(driver);
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
	public void createNewEventInitialPage(WebDriver driver) throws InvalidFormatException, InterruptedException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowCount= xllib.getRowCount("CreateNewEvent");
			log.info("*********************Create New Event Logger Initialized******************************* ");
		 	for (i = 1; i <= rowCount; i++) 
			{
		 		//Reading Login values
		 		//loginURL = xllib.getExcelData("CreateNewEvent", i, 0);
		 		//AdminEmail = xllib.getExcelData("CreateNewEvent", i, 1);
				//AdminPassword = xllib.getExcelData("CreateNewEvent", i, 2);
				
				//Reading creating New Event values
				doYouWantToCreateEvent = xllib.getExcelData("CreateNewEvent", i, 0);
				eventTitle = xllib.getExcelData("CreateNewEvent", i, 1);
		 		eventSubtitleStatus = xllib.getExcelData("CreateNewEvent", i, 2);
		 		eventSubtitle = xllib.getExcelData("CreateNewEvent", i, 3);
				dlRegion = xllib.getExcelData("CreateNewEvent", i, 4);
		 		eventDate = xllib.getExcelData("CreateNewEvent", i, 5);
				ticketPrice = xllib.getExcelData("CreateNewEvent", i, 6);
				day = xllib.getExcelData("CreateNewEvent", i, 7);
		 		premium = xllib.getExcelData("CreateNewEvent", i, 8);
				//closeDate = xllib.getExcelData("CreateNewEvent", i, 9);
				webStatus = xllib.getExcelData("CreateNewEvent", i, 9);
		 		dayPublishFormat = xllib.getExcelData("CreateNewEvent", i, 10);
		 		seatingPublishFormat = xllib.getExcelData("CreateNewEvent", i, 11);
		 		locationStatus = xllib.getExcelData("CreateNewEvent", i, 12);
		 		location = xllib.getExcelData("CreateNewEvent", i, 13);
				selectMemberReleaseDate = xllib.getExcelData("CreateNewEvent", i, 14);
				displayOrder = xllib.getExcelData("CreateNewEvent", i, 15);
		 		tourAllergyLanguageStaus = xllib.getExcelData("CreateNewEvent", i, 16);
		 		tourAllergyLanguage = xllib.getExcelData("CreateNewEvent", i, 17);
				eventIdentifier = xllib.getExcelData("CreateNewEvent", i, 18);
		 		memberReleaseDate = xllib.getExcelData("CreateNewEvent", i, 19);
				selectMembersOnly = xllib.getExcelData("CreateNewEvent", i, 20);
				foodAllergiesStatus = xllib.getExcelData("CreateNewEvent", i, 21);
				vegetarian = xllib.getExcelData("CreateNewEvent", i, 22);
				vegan = xllib.getExcelData("CreateNewEvent", i, 23);
				treenuts = xllib.getExcelData("CreateNewEvent", i, 24);
		 		shellfish = xllib.getExcelData("CreateNewEvent", i, 25);
		 		pescetarian = xllib.getExcelData("CreateNewEvent", i, 26);
		 		peanuts = xllib.getExcelData("CreateNewEvent", i, 27);
		 		lactose = xllib.getExcelData("CreateNewEvent", i, 28);
		 		gluten = xllib.getExcelData("CreateNewEvent", i, 29);
		 		graphicHeadingStaus = xllib.getExcelData("CreateNewEvent", i, 30);
		 		graphicHeading = xllib.getExcelData("CreateNewEvent", i, 31);
		 		graphicStaus = xllib.getExcelData("CreateNewEvent", i, 32);
		 		graphic = xllib.getExcelData("CreateNewEvent", i, 33);
		 		
		 		//Reading Add Seating values
		 		doYouWantToAddSeating = xllib.getExcelData("CreateNewEvent", i, 34);
		 		startTime = xllib.getExcelData("CreateNewEvent", i, 35);
		 		seatingDateStatus = xllib.getExcelData("CreateNewEvent", i, 36);
		 		seatingDate = xllib.getExcelData("CreateNewEvent", i, 37);
		 		seatingDayStatus = xllib.getExcelData("CreateNewEvent", i, 38);
		 		seatingDay = xllib.getExcelData("CreateNewEvent", i, 39);
				dlRegrionStatus = xllib.getExcelData("CreateNewEvent", i, 40);
				region = xllib.getExcelData("CreateNewEvent", i, 41);
		 		ticketType = xllib.getExcelData("CreateNewEvent", i, 42);
				webCapacity = xllib.getExcelData("CreateNewEvent", i, 43);
				dispQuantity = xllib.getExcelData("CreateNewEvent", i, 44);
				nonMemDispQuantity = xllib.getExcelData("CreateNewEvent", i, 45);
		 		dateOrder = xllib.getExcelData("CreateNewEvent", i, 46);
		 		seatingOrder = xllib.getExcelData("CreateNewEvent", i, 47);
		 		seatingStatus = xllib.getExcelData("CreateNewEvent", i, 48);
				//seatingEventIdentifier = xllib.getExcelData("CreateNewEvent", i, 53);
				partnerSeatsStatus = xllib.getExcelData("CreateNewEvent", i, 49);
				partnerSeats = xllib.getExcelData("CreateNewEvent", i, 50);
		 		
				//Reading Add Course values
				doYouWantToAddCourse = xllib.getExcelData("CreateNewEvent", i, 51);
				courseName = xllib.getExcelData("CreateNewEvent", i, 52);
				courseOrder = xllib.getExcelData("CreateNewEvent", i, 53);
				courseIngredientsStatus = xllib.getExcelData("CreateNewEvent", i, 54);
				courseIngredients = xllib.getExcelData("CreateNewEvent", i, 55);
		 		courseStatusCheck = xllib.getExcelData("CreateNewEvent", i, 56);
		 		courseStatus = xllib.getExcelData("CreateNewEvent", i, 57);
				amuse = xllib.getExcelData("CreateNewEvent", i, 58);
						 		
				//System.out.println("Admin email:"+AdminEmail);
				//System.out.println("Admin password:"+AdminPassword);
				//System.out.println("loginURL:"+loginURL);
								
								
					if(doYouWantToCreateEvent.equalsIgnoreCase(TestConstants.STATUS_TRUE))
					{
						//Calling Create New Event method
						createNewEventActions(driver);
						
						if(createNewEventStatus)
						{
							isTestPassed="PASS";
							xllib.writeToExcel("CreateNewEvent", i, 59, isTestPassed);
						}
						else
						{
							isTestPassed="FAIL";
							xllib.writeToExcel("CreateNewEvent", i, 59, isTestPassed);
						}
					
						//if(createNewEventStatus)
						//{
						
						if(doYouWantToAddSeating.equalsIgnoreCase(TestConstants.STATUS_TRUE))
						{
							//Calling Add Seating method
							addSeatingActions(driver);
							
							//Calling verify Add Seating method
							verifyAddSeating(driver);
						}
						
						if(doYouWantToAddCourse.equalsIgnoreCase(TestConstants.STATUS_TRUE))
						{
							//Calling Add Course method
							addCourseActions(driver);
							
							//Calling verify Add Course method
							verifyCourse(driver);
						}
												
						//Calling Fetching Single Event View Url method
						fetchingSingleEventViewUrl(driver);
						
						//Calling clicking on go back link method
						clickingOnGoBackLink(driver);
						
						//Calling Logout method
						//loginLogout.logoutActions(driver);
												
					//}
					}else if(doYouWantToCreateEvent.equalsIgnoreCase(TestConstants.STATUS_FALSE))
					{
						//Calling Search Event Name method
						searchEventName(driver);
						
						if(editEventsStatus)
						{
							if(doYouWantToAddSeating.equalsIgnoreCase(TestConstants.STATUS_TRUE))
							{
								//Calling Add Seating method
								addSeatingActions(driver);
								
								//Calling verify Add Seating method
								verifyAddSeating(driver);
							}
							if(doYouWantToAddCourse.equalsIgnoreCase(TestConstants.STATUS_TRUE))
							{
								//Calling Add Course method
								addCourseActions(driver);
								
								//Calling verify Add Course method
								verifyCourse(driver);
							}
													
							//Calling Fetching Single Event View Url method
							fetchingSingleEventViewUrl(driver);
							
							//Calling clicking on go back link method
							clickingOnGoBackLink(driver);
							
							//Calling Logout method
							//loginLogout.logoutActions(driver);
						}
					}
				//}
					
				if(addSeatingStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("CreateNewEvent", i, 60, isTestPassed);
				}
				else
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("CreateNewEvent", i, 60, isTestPassed);
				}
				if(verifyCourseStatus.equalsIgnoreCase(courseName))
				{
					isTestPassed="PASS";
					xllib.writeToExcel("CreateNewEvent", i, 61, isTestPassed);
				}
				else
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("CreateNewEvent", i, 61, isTestPassed);
				}
				//if(singleEventViewUrlStatus)
				//{
					log.info("Single Event View URL:"+singleEventViewURL);
					xllib.writeToExcel("CreateNewEvent", i, 62, singleEventViewURL);
				//}
				//else
				//{
					//isTestPassed="FAIL";
					//ScriptHandler.result=xllib.writeToExcel("CreateNewEvent", i, 63, isTestPassed);
				//}
			}
		 }catch(NullPointerException e)
		 {
			 e.printStackTrace();
		 }
	}
	
	/**
	 * Test Case for creating new event
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void createNewEventActions(WebDriver driver) 
	{
		//Calling create new event method
		createNewEvent(driver);
		
		//Calling verify create new event method
		verifyNewEvent(driver);
	}
	
	/**
	 * Test Case for clicking on Super Admin Link
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void clickingOnSuperAdminLink(WebDriver driver,String loginURL)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         //driver.findElement(By.xpath("//a[contains(text(),'Superadmin')]")).click();
			 superAdminURL = TestConstants.HTTP+loginURL+TestConstants.SUPER_ADMIN_URL;
			 System.out.println("superAdminURL:"+superAdminURL);
			 driver.get(superAdminURL);
	         log.info("Clicked on Super Admin Link");
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 
	 /**
	 * Test Case for handling child window
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void childWindowHandle(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 System.out.println("before parent id");
			 //getting parent Id
			 Currenthandle = driver.getWindowHandle();
			 System.out.println("parent window id:"+Currenthandle);
			 //handle the child window
			 Set<String> handles= driver.getWindowHandles();
			 driver.close();
			 handles.remove(Currenthandle);
			 //performing action on child window
			 //String parent = handles.iterator().next();
			 String child1 = handles.iterator().next();
			 driver.switchTo().window(child1);
			 System.out.println("after child id");
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 /**
		 * Test Case for clicking on View Edit Event Link
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	*/
	 public  void clickingOnViewEditEventsLink(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         driver.findElement(By.xpath("//a[contains(text(),'View/edit Events')]")).click();
			 /*WebElement viewEditEvents = driver.findElement(By.xpath("//a[contains(text(),'View/edit Events')]"));
			 Actions action= new Actions(driver);
			 action.contextClick(viewEditEvents).sendKeys(Keys.ARROW_DOWN).click().build().perform();*/
	         log.info("Clicked on View Edit Event Link");
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 /**
		* Test Case for creating new event
		* Input: WebDriver
		* Output: Void
		* @return 
		* @throws InvalidFormatException 
	*/ 
	public void createNewEvent(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			driver.findElement(By.id("events")).click();
			
			driver.findElement(By.name("eventname")).clear();		
			driver.findElement(By.name("eventname")).sendKeys(eventTitle);
			
			if(eventSubtitleStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.findElement(By.name("webemailblurb")).clear();		
				driver.findElement(By.name("webemailblurb")).sendKeys(eventSubtitle);
			}
			
			WebElement region = driver.findElement(By.name("dlregion"));
			Select st = new Select(region);
			st.selectByVisibleText(dlRegion);
			
			driver.findElement(By.id("datepicker1")).clear();		
			driver.findElement(By.id("datepicker1")).sendKeys(eventDate);
			
			driver.findElement(By.name("memticketprice")).clear();		
			driver.findElement(By.name("memticketprice")).sendKeys(ticketPrice);
			
			driver.findElement(By.id("day")).clear();		
			driver.findElement(By.id("day")).sendKeys(day);
			
			driver.findElement(By.id("premium")).clear();		
			driver.findElement(By.id("premium")).sendKeys(premium);
			
			//driver.findElement(By.id("closedate")).clear();		
			//driver.findElement(By.id("closedate")).sendKeys(closeDate);
			
			WebElement status = driver.findElement(By.name("stage"));
			Select st1 = new Select(status);
			st1.selectByVisibleText(webStatus);
			
			WebElement dayPublish = driver.findElement(By.name("datesReleaseType"));
			Select st2 = new Select(dayPublish);
			st2.selectByVisibleText(dayPublishFormat);
			
			WebElement seatingPublish = driver.findElement(By.name("stype"));
			Select st3 = new Select(seatingPublish);
			st3.selectByVisibleText(seatingPublishFormat);
			
			if(locationStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.findElement(By.name("location")).clear();		
				driver.findElement(By.name("location")).sendKeys(location);
			}
			
			driver.findElement(By.id("datepicker2")).clear();		
			driver.findElement(By.id("datepicker2")).sendKeys(selectMemberReleaseDate);
			
			WebElement order = driver.findElement(By.name("displayorder"));
			Select st4 = new Select(order);
			st4.selectByValue(displayOrder);
			
			if(tourAllergyLanguageStaus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.findElement(By.id("tourallergylanguage")).clear();		
				driver.findElement(By.id("tourallergylanguage")).sendKeys(tourAllergyLanguage);
			}
			
			driver.findElement(By.id("event_identifier")).clear();		
			driver.findElement(By.id("event_identifier")).sendKeys(eventIdentifier);
			
			driver.findElement(By.id("datepicker4")).clear();		
			driver.findElement(By.id("datepicker4")).sendKeys(memberReleaseDate);
			
			if(selectMembersOnly.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.findElement(By.id("isevent_membersonly")).click();
			}
			
			if(foodAllergiesStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				//Calling select food allergy method
				selectFoodAllergiesActions(driver);
			}
							
			/*if(salesForceAllergyStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.findElement(By.name("sfallergy")).clear();		
				driver.findElement(By.name("sfallergy")).sendKeys(salesForceAllergy);
			}
			*/
			Select st5 = null;
			if(graphicHeadingStaus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				WebElement graphic = driver.findElement(By.id("graphic_heading_selection"));
				st5 = new Select(graphic);
				st5.selectByIndex(0);
				
				//driver.findElement(By.id("graphic_heading")).click();
				driver.findElement(By.id("graphic_heading")).clear();		
				driver.findElement(By.id("graphic_heading")).sendKeys(graphicHeading);
			}else
			{
				st5.selectByIndex(1);
			}
			
			if(graphicStaus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				//driver.findElement(By.name("graphic")).click();
				driver.findElement(By.name("graphic")).clear();		
				driver.findElement(By.name("graphic")).sendKeys(graphic);
			}
			
			//Calling Submit Button method
			clickingOnSubmitButton(driver);
		}
		catch(WebDriverException wde)
		{
			wde.printStackTrace();
		}
	}
	
	 /**
	 * Test Case for selecting food allergies
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	 */
	public  void selectFoodAllergiesActions(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			// Find Select element of "Single selection" using ID locator.
			Select allergySelect = new Select(driver.findElement(By.id("left-allergy")));
			// Get the size of the Select element
			List<WebElement> oSize = allergySelect.getOptions();
			int iListSize = oSize.size();
			System.out.println("iListSize:"+iListSize);
	 
			// Setting up the loop to select allergies
			for(int j =0; j <= iListSize - 1 ; j++)
			{
				
				System.out.println("value at :"+j);
				// Storing the value of the option	
				String sValue = allergySelect.getOptions().get(j).getText();
				// Printing the stored value
				System.out.println("sValue:"+sValue);
				// Putting a check on each option that if any of the option is equal to 'Vegan" then select it 
				
				//allergySelect.selectByIndex(j);
				
				Actions builder = new Actions(driver);
				builder.keyDown(Keys.CONTROL);
				
				if(vegetarian.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					if(sValue.equalsIgnoreCase(TestConstants.DIETARY_VEGETARIAN))
					{
						allergySelect.selectByIndex(j);
						//break;
					}
				}
				
				//driver.findElement(By.id("left-allergy")).sendKeys(Keys.CONTROL);
				if(vegan.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					if(sValue.equalsIgnoreCase(TestConstants.DIETARY_VEGAN))
					{
						allergySelect.selectByIndex(j);
						//break;
					}
				}
				if(treenuts.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					if(sValue.equalsIgnoreCase(TestConstants.ALLERGY_TREENUTS))
					{
						allergySelect.selectByIndex(j);
						//break;
					}
				}
				
				if(shellfish.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					if(sValue.equalsIgnoreCase(TestConstants.ALLERGY_SHELLFISH))
					{
						allergySelect.selectByIndex(j);
						//break;
					}
				}
				if(pescetarian.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					if(sValue.equalsIgnoreCase(TestConstants.ALLERGY_PESCETARIAN))
					{
						allergySelect.selectByIndex(j);
						//break;
					}
				}
				
				if(peanuts.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					if(sValue.equalsIgnoreCase(TestConstants.ALLERGY_PEANUTS))
					{
						allergySelect.selectByIndex(j);
						//break;
					}
				}
				if(lactose.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					if(sValue.equalsIgnoreCase(TestConstants.ALLERGY_LACTOSE))
					{
						allergySelect.selectByIndex(j);
						//break;
					}
				}
				
				if(gluten.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					if(sValue.equalsIgnoreCase(TestConstants.ALLERGY_GLUTEN))
					{
						allergySelect.selectByIndex(j);
						//break;
					}
				}
				builder.build().perform();
				driver.findElement(By.xpath("//input[@ value='>>']")).click();
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			}//End of for loop
	
		}catch(IndexOutOfBoundsException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Test Case for verifying created new event
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void verifyNewEvent(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 createNewEventStatus = driver.findElement(By.xpath("//h3[contains(text(),'Edit events Information')]")).isDisplayed();
			 System.out.println("createNewEventStatus:"+createNewEventStatus);
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 
	 /**
		 * Test Case for add Seating
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	*/
	 public  void addSeatingActions(WebDriver driver) throws NoSuchElementException
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 driver.findElement(By.id("seating")).click();
			 
			 driver.findElement(By.id("seatingstarttime")).clear();
			 driver.findElement(By.id("seatingstarttime")).sendKeys(startTime);
			 
			 System.out.println("seatingDateStatus:"+seatingDateStatus);
			 System.out.println("seatingDate:"+seatingDate);
			 if(seatingDateStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 {
				 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				 driver.findElement(By.id("sdate")).clear();
				 driver.findElement(By.id("sdate")).sendKeys(seatingDate);
			 }
			 
			 if(seatingDayStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 {
				 driver.findElement(By.id("seatingday")).clear();
				 driver.findElement(By.id("seatingday")).sendKeys(seatingDay);
			 }
			 if(dlRegrionStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 {
				 driver.findElement(By.id("dlregion")).clear();
				 driver.findElement(By.id("dlregion")).sendKeys(region);
			 }
			 
			 WebElement ticket = driver.findElement(By.id("tickettype"));
			 Select st6 = new Select(ticket);
			 st6.selectByVisibleText(ticketType);
			 
			 driver.findElement(By.id("maxtickets")).clear();
			 driver.findElement(By.id("maxtickets")).sendKeys(webCapacity);
			 
			 WebElement nonMemticket = driver.findElement(By.name("nonquantity"));
			 Select st7 = new Select(nonMemticket);
			 st7.selectByVisibleText(nonMemDispQuantity);
			 
			 System.out.println("before date order");
			 System.out.println("dateOrder:"+dateOrder);
			 WebElement date = driver.findElement(By.id("dateorder"));
			 Select st8 = new Select(date);
			 st8.selectByVisibleText(dateOrder);
			 System.out.println("after date order");
			 
			 WebElement seatOrder = driver.findElement(By.id("mseat_ord"));
			 Select st9 = new Select(seatOrder);
			 st9.selectByVisibleText(seatingOrder);
			 
			 WebElement seatStatus = driver.findElement(By.id("seatingstatus"));
			 Select st10 = new Select(seatStatus);
			 st10.selectByVisibleText(seatingStatus);
			 
			 //driver.findElement(By.id("eventidty")).clear();
			 //driver.findElement(By.id("eventidty")).sendKeys(seatingEventIdentifier);
			 
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 driver.findElement(By.id("seat_code")).click();
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			 
			 eventCode  = driver.findElement(By.id("eventcode")).getText();
			 ticketsLink = driver.findElement(By.id("ticketslink")).getText();
			 nonEventCode = driver.findElement(By.id("noneventcode")).getText();
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			 
			 System.out.println("eventCode:"+eventCode);
			 System.out.println("ticketsLink:"+ticketsLink);
			 System.out.println("nonEventCode:"+nonEventCode);
			 
			 if(partnerSeatsStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 {
				 driver.findElement(By.name("partnerseats")).clear();
				 driver.findElement(By.name("partnerseats")).sendKeys(partnerSeats);
			 }
			 
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 driver.findElement(By.xpath("//input[@name='Save Seating']")).click();	
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			
		}catch(WebDriverException wde)
		{
			wde.printStackTrace();
		}
	 }
	 
	 /**
		 * Test Case for clicking on Submit Button
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	*/
	 public  void clickingOnSubmitButton(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 driver.findElement(By.xpath("//input[@value='Submit']")).click();	
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 
	 /**
		 * Test Case for verifying add seating
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
		*/
	public  void verifyAddSeating(WebDriver driver)
	{
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 addSeatingStatus = driver.findElement(By.xpath("//a[contains(text(),'"+eventCode+"')]")).isDisplayed();
			 if(addSeatingStatus)
			 {
				 System.out.println("Seating added successfully");
				 log.info("Seating added successfully");
			 }
			 
			 System.out.println("addSeatingStatus:"+addSeatingStatus);
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		 {
			e.printStackTrace();
		 }
	}
	
	/**
	 * Test Case for adding Course
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void addCourseActions(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 driver.findElement(By.id("course")).click();
			 
			 driver.findElement(By.id("coursename")).clear();
			 driver.findElement(By.id("coursename")).sendKeys(courseName);
			 
			 WebElement course = driver.findElement(By.id("courseorder"));
			 Select st11 = new Select(course);
			 st11.selectByVisibleText(courseOrder);
			 
			 if(courseIngredientsStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 {
				 driver.findElement(By.id("courseingredients")).clear();
				 driver.findElement(By.id("courseingredients")).sendKeys(courseIngredients);
			 }
			 
			 if(courseStatusCheck.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 {
				 WebElement status1 = driver.findElement(By.id("coursestatus"));
				 Select st12 = new Select(status1);
				 st12.selectByVisibleText(courseStatus);
			 }
			 
			 if(amuse.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 {
				 driver.findElement(By.name("amuse")).click();
			 }
			 
			 driver.findElement(By.xpath("//input[@value='Save Course']")).click();
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 /**
		 * Test Case for verifying add Course
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
		*/
	public  void verifyCourse(WebDriver driver)
	{
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 verifyCourseStatus = driver.findElement(By.xpath("//div[div[h3[contains(text(),'Edit Course Information')]]]/table[2]/tbody/tr/td[contains(text(),'"+courseName+"')]")).getText();
			 System.out.println("verifyCourseStatus:"+verifyCourseStatus);
			 
			 if(verifyCourseStatus.equalsIgnoreCase(courseName))
			 {
				 System.out.println("Course added successfully");
				 log.info("Course added successfully");
			 }
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		 {
			e.printStackTrace();
		 }
	}
	
	/**
	 * Test Case for fetching Single Event View URL
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public  void fetchingSingleEventViewUrl(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			String DL_Region = dlRegion.toLowerCase();
			String Event_Title = eventTitle.toLowerCase();
			//System.out.println("DL_Region:"+DL_Region);
			//System.out.println("Event_Title:"+Event_Title);
			
			String temp1 = DL_Region;
			String temp2=Event_Title;
			System.out.println("temp1 is :"+temp1.replace(" ","-"));
			System.out.println("temp2 is :"+temp2.replace(" ","-"));
			String DL_Region1 = temp1.replace(" ","-");
			String  Event_Title1 = temp2.replace(" ","-");
			singleEventViewURL = TestConstants.HTTP+loginURL+TestConstants.EVENTS+DL_Region1+TestConstants.FORWARD_SLASH+Event_Title1+TestConstants.FORWARD_SLASH;
			System.out.println("singleEventViewURL:"+singleEventViewURL);
			//singleEventViewURL = TestConstants.HTTP+loginURL+TestConstants.EVENTS+DL_Region+TestConstants.FORWARD_SLASH+Event_Title+TestConstants.FORWARD_SLASH;
			
			//driver.get(singleEventViewURL);
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		 	//singleEventViewUrlStatus = driver.findElement(By.xpath("//div[contains(text(),'"+eventTitle+"')]")).isDisplayed();
		 	//System.out.println("singleEventViewUrlStatus:"+singleEventViewUrlStatus);
		 	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 	
		 	//Clicking on Events Link
		 	/*freeMember.clickingOnEventsLink(driver);
		 	
		 	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		 	WebElement changeCity= driver.findElement(By.xpath("//div[@class='form-item']/form[1]/select[1]"));
			Select st=new Select(changeCity);
			st.selectByVisibleText(dlRegion);
			
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[contains(text(),'"+eventTitle+"')]")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			String singleEventViewURL = driver.getCurrentUrl();
			System.out.println("singleEventViewURL:"+singleEventViewURL);
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);*/
		}catch(Exception e)
		{
			e.printStackTrace();
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
			st13.selectByVisibleText(dlRegion);
			
			driver.findElement(By.id("search")).click();
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			driver.findElement(By.xpath("//input[@value='View/Edit']")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			editEventsStatus = driver.findElement(By.xpath("//h3[contains(text(),'Edit events Information')]")).isDisplayed();
			System.out.println("editEventsStatus:"+editEventsStatus);
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Test Case for clicking on go back link
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public void clickingOnGoBackLink(WebDriver driver) 
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			driver.findElement(By.xpath("//a[contains(text(),'Go Back')]")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}


