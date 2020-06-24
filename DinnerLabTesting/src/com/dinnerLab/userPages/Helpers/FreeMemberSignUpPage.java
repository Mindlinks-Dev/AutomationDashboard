/**
 * @author: Basappa Hunsikatti
 * @Created Date :23/09/2015
 * @Updated Date :09/02/2016
 * @Comments:This automation class will serve the Free Member Sign Up.
*/

package com.dinnerLab.userPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;
//import com.pack.ScriptHandler;

public class FreeMemberSignUpPage 
{
	//private static String signUpUrl = TestConstants.SignUp_Url;
	private  String firstName;
	private  String lastName;
	private  String email;
	private  String password;
	//private String phoneNumber;
	//private String phoneNumberStatus;
	private  String useOtherCity;
	private  String city;
	private  String otherCity;
	private  String doYouHavePromoCode;
	private  String promoCode;
	private  String signUpURL;
	public static boolean signUpSuccess;
	public static String referLink;
	/*private static String updateMyCitiesStatus;
	private static String selectAll;
	private static String Atlanta;
	private static String Austin;
	private static String Baltimore;
	private static String BatonRouge;
	private static String Birmingham;
	private static String Boston;
	private static String Charlotte;
	private static String Chicago;
	private static String Cleveland;
	private static String Columbus;
	private static String DC;
	private static String Dallas;
	private static String Denver;
	private static String Houston;
	private static String KansasCity;
	private static String LosAngeles;
	private static String Miami;
	private static String Milwaukee;
	private static String Minneapolis;
	private static String Nashville;
	private static String NewOrleans;
	private static String NewYork;
	private static String Philadelphia;
	private static String Phoenix;
	private static String Pittsburgh;
	private static String Portland;
	private static String SanAntonio;
	private static String SanDiego;
	private static String SanFrancisco;
	private static String Seattle;
	private static String StLouis;
	public static String allergies;*/
	public boolean homeCity;
	public static boolean viewEventsStatus;
	/*public static String dietaryRestrictions;
	private String dietaryPreferences;
	private String peanuts;
	private String shellfish;
	private String treeNuts;
	private String lactose;
	private String gluten;
	private String noneOfThese;
	private String otherDietaryRestrictOrAllergies;
	private String OtherAllergiesTextArea;*/
	String isTestPassed="FAIL";
	int i=0;
	private static Logger log = Logger.getLogger(FreeMemberSignUpPage.class);
			
	/**
	 * Test Case for Reading the excel data for signing up as a Free Member
	 * portal on successful registration to the application.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	*/
	public void subscriberSignUpInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			int rowCount = xllib.getRowCount("FreeMemberSignUp");
			//For loop:Fetch data from excel input configuration file(excel sheet)
			for(i=1;i<= rowCount;i++)
			{
				signUpURL = xllib.getExcelData("FreeMemberSignUp", i, 0);
			 	firstName = xllib.getExcelData("FreeMemberSignUp", i, 1);
			 	lastName = xllib.getExcelData("FreeMemberSignUp", i, 2);
				email = xllib.getExcelData("FreeMemberSignUp", i, 3);
				password = xllib.getExcelData("FreeMemberSignUp", i, 4);
				city = xllib.getExcelData("FreeMemberSignUp", i, 5);
				useOtherCity = xllib.getExcelData("FreeMemberSignUp", i, 6);
				otherCity = xllib.getExcelData("FreeMemberSignUp", i, 7);
				doYouHavePromoCode = xllib.getExcelData("FreeMemberSignUp", i, 8);
				promoCode = xllib.getExcelData("FreeMemberSignUp", i, 9);
				
				//Reading Dietary Restrictions data from excel 
				/*dietaryPreferences = xllib.getExcelData("FreeMemberSignUp", i, 10);
				dietaryRestrictions = xllib.getExcelData("FreeMemberSignUp", i, 11);
				peanuts = xllib.getExcelData("FreeMemberSignUp", i,12);
				shellfish = xllib.getExcelData("FreeMemberSignUp", i, 13);
				treeNuts = xllib.getExcelData("FreeMemberSignUp", i, 14);
				lactose = xllib.getExcelData("FreeMemberSignUp", i, 15);
				gluten = xllib.getExcelData("FreeMemberSignUp", i,16);
				noneOfThese = xllib.getExcelData("FreeMemberSignUp", i,17);
		 		otherDietaryRestrictOrAllergies = xllib.getExcelData("FreeMemberSignUp", i,18);
		 		OtherAllergiesTextArea = xllib.getExcelData("FreeMemberSignUp", i,19);
		 		
		 		phoneNumberStatus = xllib.getExcelData("FreeMemberSignUp", i, 20);
				phoneNumber = xllib.getExcelData("FreeMemberSignUp", i, 21);
				/*updateMyCitiesStatus = xllib.getExcelData("FreeMemberSignUp", i, 10);
				selectAll = xllib.getExcelData("FreeMemberSignUp", i, 11);
				Atlanta	= xllib.getExcelData("FreeMemberSignUp", i, 12);	
				Austin	= xllib.getExcelData("FreeMemberSignUp", i, 13);	
				Baltimore = xllib.getExcelData("FreeMemberSignUp", i, 14);
			 	BatonRouge = xllib.getExcelData("FreeMemberSignUp", i, 15);
			 	Birmingham = xllib.getExcelData("FreeMemberSignUp", i, 16);
			 	Boston = xllib.getExcelData("FreeMemberSignUp", i, 17);
				Charlotte = xllib.getExcelData("FreeMemberSignUp", i, 18);
				Chicago = xllib.getExcelData("FreeMemberSignUp", i, 19);
				Cleveland = xllib.getExcelData("FreeMemberSignUp", i, 20);
				Columbus = xllib.getExcelData("FreeMemberSignUp", i, 21);
				DC = xllib.getExcelData("FreeMemberSignUp", i, 22);
				Dallas = xllib.getExcelData("FreeMemberSignUp", i, 23);
				Denver	= xllib.getExcelData("FreeMemberSignUp", i, 24);	
				Houston	= xllib.getExcelData("FreeMemberSignUp", i, 25);	
				//Indianapolis = xllib.getExcelData("FreeMemberSignUp", i, 26);
				KansasCity = xllib.getExcelData("FreeMemberSignUp", i, 26);
				LosAngeles = xllib.getExcelData("FreeMemberSignUp", i, 27);
				Miami = xllib.getExcelData("FreeMemberSignUp", i, 28);
				Milwaukee = xllib.getExcelData("FreeMemberSignUp", i, 29);
				Minneapolis = xllib.getExcelData("FreeMemberSignUp", i, 30);
				Nashville = xllib.getExcelData("FreeMemberSignUp", i, 31);
				NewOrleans = xllib.getExcelData("FreeMemberSignUp", i, 32);
				NewYork = xllib.getExcelData("FreeMemberSignUp", i, 33);
				Philadelphia = xllib.getExcelData("FreeMemberSignUp", i, 34);	
				Phoenix	= xllib.getExcelData("FreeMemberSignUp", i, 35);	
				Pittsburgh = xllib.getExcelData("FreeMemberSignUp", i, 36);
				Portland = xllib.getExcelData("FreeMemberSignUp", i, 37);
				//Raleigh = xllib.getExcelData("FreeMemberSignUp", i, 38);
				SanAntonio = xllib.getExcelData("FreeMemberSignUp", i, 38);
				SanDiego = xllib.getExcelData("FreeMemberSignUp", i, 39);
				SanFrancisco = xllib.getExcelData("FreeMemberSignUp", i, 40);
				Seattle = xllib.getExcelData("FreeMemberSignUp", i, 41);
				StLouis = xllib.getExcelData("FreeMemberSignUp", i, 42);*/
				
				//Calling subscriber Signup URL Method.
		 		subscriberSignUpURL(driver,signUpURL);
		 		
		 		//Calling click On Free Sign Up Button Method.
		 		clickOnFreeSignUpButton(driver);
		 				
		 		//Selecting User Type as Member
		 		//selectUserTypeMember(driver);
		 		
		 		//Calling Sign Up Method.
				signUpPageActions(driver,doYouHavePromoCode,promoCode,firstName,lastName,email,password,city,useOtherCity,otherCity);
				
				/*DietryRestrictionPage dietary = new DietryRestrictionPage();
				
				if(dietaryPreferences.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					 //Calling Dietary Restrictions method
		            dietary.dietaryRestrictionsActions(driver,dietaryRestrictions);
		            
		            //Calling Allergies method
		            dietary.allergiesActions(driver,peanuts,shellfish,treeNuts,lactose,gluten);
		            
		           //Selecting Signup page allergy none of these
		            dietary.selectingSignUpPageAllergyNoneOfThese(driver,noneOfThese);
		            
		            //Calling other Dietary Restrict Or Allergies method
		            dietary.otherDietaryRestrictOrAllergiesActions(driver,otherDietaryRestrictOrAllergies,OtherAllergiesTextArea);
				}*/
				
				//Clicking on Next Control
				/*clickingOnNextControl(driver);
				
				if(phoneNumberStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
					
					//Adding Phone Number
					creditCard.addingPhoneNumberActions(driver, phoneNumber);
					
					//Clicking on Submit Button
					clickingOnSubmitButton(driver);
				}
				//Condition to Select other cities to receive email for events.
	            //selectOtherCitiesToReceiveMailForEvents(driver,useOtherCity,city,otherCity,updateMyCitiesStatus,selectAll,Atlanta,Austin,Baltimore,BatonRouge,Birmingham,Boston,Charlotte,Chicago,Cleveland,Columbus,DC,Dallas, Denver,Houston,KansasCity,LosAngeles,Miami,Milwaukee,Minneapolis,Nashville,NewOrleans,NewYork,Philadelphia,Phoenix,Pittsburgh,Portland,SanAntonio,SanDiego,SanFrancisco,Seattle,StLouis);
				
	            //Clicking on Next Control
				clickingOnNextControl(driver);
				
				//Fetching Refer/Invite Link
				referLinkGetText(driver);*/
				
				//Verifying Sign Up
	            verifySignUp(driver);
	            
				//Clicking on View Events Button
	            clickingOnViewEventsButton(driver);
	          
	            if(rowCount !=1)
				{
	            	MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
	            	
	            	//Calling Logout method
					loginLogout.logoutActions(driver);
				}
	            
				//checking the status of Sign up page and updating the result in excel	
				if(signUpSuccess)
				{				
					log.info("You are Free Member of Dinner Lab.Thank you.");
					isTestPassed="PASS";
					xllib.writeToExcel("FreeMemberSignUp", i, 10, isTestPassed);
				}
				else
				{
					isTestPassed = "FAIL";
					xllib.writeToExcel("FreeMemberSignUp", i, 10, isTestPassed);
				}
			}//End of for loop
		}
		catch(Exception e)
		{
			log.info("Free Member Account creation unsucessfull");
		}
	}
	
	/**
	 * Test Case for passing Signup URL and selecting Member Type
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void subscriberSignUpURL(WebDriver driver,String signUpURL)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 String dinnerLabSubscriberSignUpURL = TestConstants.HTTP+signUpURL+TestConstants.SIGN_UP;
			 //System.out.println("dinnerLabSubscriberSignUpURL:"+dinnerLabSubscriberSignUpURL);
			 driver.get(dinnerLabSubscriberSignUpURL);
			 log.info("Navigating to "+dinnerLabSubscriberSignUpURL);
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 }catch(Exception e)
		 {
			e.printStackTrace();
		 }
	 }
	 
	 /**
		 * Test Case for clicking on Free Sign Up Button
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
		*/
		 public  void clickOnFreeSignUpButton(WebDriver driver)
		 {
			 try
			 {
				 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				 driver.findElement(By.xpath("//a[@href='/signup/']")).click();
				 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 }catch(Exception e)
			 {
				e.printStackTrace();
			 }
		 }
	 /**
		 * Test Case for selecting User Type as Member
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	 */
	/* public void selectUserTypeMember(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 WebElement member = driver.findElement(By.id("usertypeID"));
			 Select st = new Select(member);
			 st.selectByIndex(1);		
	         driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	     }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }	 */
	 
	/**
	 * Test Case for sign up into the portal
	 * Upon correct user details we can successfully submit the page
	 * Input: driver and parameters
	 * Output: Boolean
     * @throws InterruptedException 
     * @throws InvalidFormatException 
	 * 
	 */	 
	 public boolean signUpPageActions(WebDriver driver,String doYouHavePromoCode,String promoCode,String firstName,String lastName,String email,String password,String city,String useOtherCity,String otherCity) throws InterruptedException, InvalidFormatException
	 {
		 try
		 {
			log.info("Creating Dinner Lab Free Member Account.");
		 	//Sign Up Code
			if(useOtherCity.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.findElement(By.id("useCityOther")).click();
				driver.findElement(By.id("cityOtherAutocomplete")).sendKeys(otherCity);
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//li[contains(text(),'"+otherCity+"')]")).click();
				driver.findElement(By.xpath("//div[@ class='main']")).click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}
			else if(useOtherCity.equalsIgnoreCase(TestConstants.STATUS_FALSE))
			{
				WebElement cityText = driver.findElement(By.id("cityID"));
				Select st1 = new Select(cityText);
				st1.selectByVisibleText(city);
				//driver.findElement(By.id("cityID")).sendKeys(city);
			}
			
			//Condition for checking Promo Code
			if(doYouHavePromoCode.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(By.id("showCode")).click();
				driver.findElement(By.id("alt-code")).clear();
				driver.findElement(By.id("alt-code")).sendKeys(promoCode);
			}
			
			//Email Signup
			driver.findElement(By.id("triggerEmail")).click();
			driver.findElement(By.id("firstname")).clear();
			driver.findElement(By.id("firstname")).sendKeys(firstName);
			driver.findElement(By.id("lastname")).clear();
			driver.findElement(By.id("lastname")).sendKeys(lastName);
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("email")).sendKeys(email);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("signup-submit")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return true;
		}
		catch(NoSuchElementException e)
		{  
			e.printStackTrace();
			 log.info("SignUp unsuccessful.");
			 return false;
		 }
	}
	
	/**
	 * Test Case for fetching Refer/Invite Link
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public void referLinkGetText(WebDriver driver)
	 {
		 try
		 {
			 Thread.sleep(3000);
	         referLink = driver.findElement(By.xpath("//textarea[@class='code']")).getText();
	         System.out.println("Your Referral Link is:"+referLink);		
	         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	     }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	
	 /**
		* Test Case for Clicking on Next Control
		* Input: WebDriver
		* Output: Void
		* @return 
		* @throws InvalidFormatException 
	 */
	 public void clickingOnNextControl(WebDriver driver)
	 {
		try
		{
			//Clicking on Next      
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//span[@class='next ctrl']")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	 /**
		* Test Case for Selecting Other Cities to receive mail
		* Input: WebDriver
		* Output: Void
		* @return 
		* @throws InvalidFormatException 
	 */
	 public void selectOtherCitiesToReceiveMailForEvents(WebDriver driver,String useOtherCity,String city,String otherCity,String updateMyCitiesStatus,String selectAll,String Atlanta,String Austin,String Baltimore,String BatonRouge,String Birmingham,String Boston,String Charlotte,String Chicago,String Cleveland,String Columbus,String DC,String Dallas,String Denver,String Houston,String KansasCity,String LosAngeles,String Miami,String Milwaukee,String Minneapolis,String Nashville,String NewOrleans,String NewYork,String Philadelphia,String Phoenix,String Pittsburgh,String Portland,String SanAntonio,String SanDiego,String SanFrancisco,String Seattle,String StLouis)
	 {
		try
		{
			System.out.println("updateMyCitiesStatus:"+updateMyCitiesStatus);
			//Condition to Select other cities to receive email for events.
            if(updateMyCitiesStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
            {
              	System.out.println("callling cities");
              	
            	//Calling Manage your Cities method
                manageCitiesActions(driver,useOtherCity,updateMyCitiesStatus);
                
                //Calling select cities actions method
				selectCitiesActions(driver,selectAll,Atlanta,Austin,Baltimore,BatonRouge,Birmingham,Boston,Charlotte,Chicago,Cleveland,Columbus,DC,Dallas,Denver,Houston,KansasCity,LosAngeles,Miami,Milwaukee,Minneapolis,Nashville,NewOrleans,NewYork,Philadelphia,Phoenix,Pittsburgh,Portland,SanAntonio,SanDiego,SanFrancisco,Seattle,StLouis);
            }
        }catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	 /**
		* Test Case for Clicking on View Events Button
		* Input: WebDriver
		* Output: Void
		* @return 
		* @throws InvalidFormatException 
	 */
	 public void clickingOnViewEventsButton(WebDriver driver)
	 {
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//driver.findElement(By.xpath("//input[@value='View Events']")).click();
			driver.findElement(By.xpath("//button[contains(text(),'View Events')]")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);		
			
			//WebDriverWait wait = new WebDriverWait(driver,20);
	        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='View Events']"))).click();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	 
	 /**
		* Test Case for verifying View Events Button
		* Input: WebDriver
		* Output: Void
		* @return 
		* @throws InvalidFormatException 
	 */
	 public void verifyViewEventsButton(WebDriver driver)
	 {
		try
		{
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			viewEventsStatus = driver.findElement(By.xpath("//input[@value='View Events']")).isDisplayed();
			System.out.println("viewEventsStatus:"+viewEventsStatus);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);		
		}catch(NoSuchElementException e)
		{
			e.printStackTrace();
		}
	}

	 /**
		* Test Case for verifying Sign Up
		* Input: WebDriver
		* Output: Void
		* @return 
		* @throws InvalidFormatException 
	 */
	 public void verifySignUp(WebDriver driver)
	 {
		try
		{
			//Clicking on Next    
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//signUpSuccess =  driver.findElement(By.xpath("//a[@href='/signup/payment']")).isDisplayed();
			signUpSuccess =  driver.findElement(By.xpath("//h1[contains(text(),'WELCOME TO DINNER LAB')]")).isDisplayed();
			System.out.println("signUpSuccess:"+signUpSuccess);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Method for Select cities to receive email for events that are taking place in other cities as well.
	 * Input: Web driver
	 * Output: Void
	 * @throws InterruptedException 
    */	 
	//public void manageCitiesActions(WebDriver driver,String useOtherCity,String updateMyCitiesStatus,String selectAll,String Atlanta,String Austin,String Baltimore,String BatonRouge,String Birmingham,String Boston,String Charlotte,String Chicago,String Cleveland,String Columbus,String DC,String Dallas,String Denver,String Houston,String KansasCity,String LosAngeles,String Miami,String Milwaukee,String Minneapolis,String Nashville,String NewOrleans,String NewYork,String Philadelphia,String Phoenix,String Pittsburgh,String Portland,String SanAntonio,String SanDiego,String SanFrancisco,String Seattle,String StLouis) throws InterruptedException 
	 public void manageCitiesActions(WebDriver driver,String useOtherCity,String updateMyCitiesStatus)
	 {
		System.out.println("updateMyCitiesStatus:"+updateMyCitiesStatus);
		System.out.println("useOtherCity123:"+useOtherCity);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if(!useOtherCity.equalsIgnoreCase(TestConstants.STATUS_TRUE))
		{
			homeCity = driver.findElement(By.xpath("//span[contains(text(),'(Home City)')]")).isDisplayed();
			System.out.println("homeCity:"+homeCity);
		}
	}
	/**
	 * Method for Select cities to receive email for events that are taking place in other cities as well.
	 * Input: Web driver
	 * Output: Void
	 * @throws InterruptedException 
    */	 	
	public void selectCitiesActions(WebDriver driver,String selectAll,String Atlanta,String Austin,String Baltimore,String BatonRouge,String Birmingham,String Boston,String Charlotte,String Chicago,String Cleveland,String Columbus,String DC,String Dallas,String Denver,String Houston,String KansasCity,String LosAngeles,String Miami,String Milwaukee,String Minneapolis,String Nashville,String NewOrleans,String NewYork,String Philadelphia,String Phoenix,String Pittsburgh,String Portland,String SanAntonio,String SanDiego,String SanFrancisco,String Seattle,String StLouis) throws InterruptedException
	{
		try
		{
			System.out.println("select all:"+selectAll);
			
			//Condition to Select all Cities
			if(selectAll.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(By.id("selectall")).click();
			}
			
			/*driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			boolean selectAllStatus = driver.findElement(By.id("selectall")).isSelected();
			System.out.println("selectAllStatus:"+selectAllStatus);
			
			System.out.println("Atlanta:"+Atlanta);
			//if(!homeCity && !selectAllStatus)
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
			//if(!selectAllStatus)
			//if(selectAll.equalsIgnoreCase(TestConstants.STATUS_NO))
			else if(selectAll.equalsIgnoreCase(TestConstants.STATUS_FALSE))
			{
				System.out.println("inside cities");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				if(Atlanta.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox12")).click();
				}
				if(Austin.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox4")).click();
				}
				if(Baltimore.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox18")).click();
				}
				if(BatonRouge.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox29")).click();
				}
				if(Birmingham.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox30")).click();
				}
				if(Boston.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox26")).click();
				}
				if(Charlotte.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox19")).click();
				}
				if(Chicago.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox8")).click();
				}
				if(Cleveland.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox36")).click();
				}
				if(Columbus.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox28")).click();
				}
				if(DC.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox11")).click();
				}
				if(Dallas.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox24")).click();
				}
				if(Denver.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox21")).click();
				}
				if(Houston.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox22")).click();
				}
				/*if(Indianapolis.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox37")).click();
				}*/
				if(KansasCity.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox34")).click();
				}
				if(LosAngeles.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox7")).click();
				}
				if(Miami.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox10")).click();
				}
				if(Milwaukee.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox17")).click();
				}
				if(Minneapolis.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox27")).click();
				}
				if(Nashville.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox5")).click();
				}
				if(NewOrleans.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox1")).click();
				}
				if(NewYork.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox6")).click();
				}
				if(Philadelphia.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox23")).click();
				}
				if(Phoenix.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox31")).click();
				}
				if(Pittsburgh.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox32")).click();
				}
				if(Portland.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox33")).click();
				}
				/*if(Raleigh.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox35")).click();
				}*/
				if(SanAntonio.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox15")).click();
				}
				if(SanDiego.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox16")).click();
				}
				if(SanFrancisco.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox9")).click();
				}
				if(Seattle.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox25")).click();
				}
				if(StLouis.equalsIgnoreCase(TestConstants.STATUS_TRUE)){
					driver.findElement(By.id("checkbox20")).click();
				}
			}//End of outside else condition 
			
			driver.findElement(By.id("submitManageCities")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}catch(Exception e)
		{
				e.printStackTrace();
		}
	}//End of selectCitiesActions method
	
	 /**
	* Test Case for Clicking on Events Link
	* Input: WebDriver
	* Output: Void
	* @return 
	* @throws InvalidFormatException 
	*/
	public void clickingOnEventsLink(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//a[@href='/events/']")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	 /**
	 * Test Case for clicking on submit button
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	 */
	public void clickingOnSubmitButton(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElement(By.id("phoneNumberValidator")).click();
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}//End of Class
		

