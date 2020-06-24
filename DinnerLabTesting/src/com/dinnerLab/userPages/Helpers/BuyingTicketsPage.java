/**
 * @author: Basappa Hunsikatti
 * @Created Date :9/11/2015
 * @Updated Date :
 * @Comments This automation class will purchase Member Tickets.
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
import com.dinnerLab.util.ExcelReport;
import com.dinnerLab.util.OutputReport;
import com.pack.ScriptHandler;

public class BuyingTicketsPage 
{
	public static String loginEmail;
	private String singleEventViewURL;
	private static String firstName;
	private static String lastName;
	private static String email;
	private static String password;
	public static String useOtherCity;
	private static String city;
	private static String otherCity;
	private static String doYouHavePromoCode;
	private static String promoCode;
	public static boolean signUpSuccess;
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
	private static String StLouis;*/
	public static String dietaryRestrictions;
	private String dietaryPreferences;
	private String peanuts;
	private String shellfish;
	private String treeNuts;
	private String lactose;
	private String gluten;
	private String noneOfThese;
	private String otherDietaryRestrictOrAllergies;
	private String OtherAllergiesTextArea;
	public static Boolean paidMemberSignUpStatus;
	public static Boolean upgradeAccountStatus;
	private String nameOnCard;
	private String cardNumber;
	private String expiryMonth;
	private String expiryYear;
	private static String phoneNumber;
	private String cvv;
	private String billingStreet;
	private String billingCity;
	private String billingState;
	private String billingZipCode;	 
	boolean present;
	public static String ticketPurchaseCity;
	public static String eventName;
	public static String seatingDate;
	public static String seatingTime;
	public static String purchaseQuantity;
	public static String readyToJoinDinnerLab;
	public static String doYouWantToLogin;
	public static String membershipStatus;
	private String phoneNumberStatus;
	public static String changeHomeCity;
	public static String newHomeCity;
	public static String applyPromoCode;
	public String accountPromoCode;
	String isTestPassed="FAIL";
	public static int rowCount;
	public boolean eventTicketPurchaseSuccess;
	public static String memberCityName;
	public boolean ticketPurchaseStatus;
	//String isTicketSoldOut="Event Ticket Available";
	String isTicketSoldOut="FAIL";
	int i=0;
	private static Logger log = Logger.getLogger(BuyingTicketsPage.class);
	//MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	
	//MemberEventTicketPurchasePage ticket = new MemberEventTicketPurchasePage();
	ExcelReport report = new ExcelReport();
	OutputReport output = new OutputReport();
	//WritingStripeDetailsToExcelReport stripeData = new WritingStripeDetailsToExcelReport();
	//FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
	//DietryRestrictionPage dietary = new DietryRestrictionPage();
	//AddNewCreditCardInfoPage credit = new AddNewCreditCardInfoPage();
	//public static String StripeEmailId;
	 	 
	 /**
		 * Test Case for Reading the excel data and login into the Login Page
		 * portal on successful login to the application.
		 * Input: WebDriver
		 * Output: Void
	 */
	public void buyingTicketsInitialPage(WebDriver driver)
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			int rowCount = xllib.getRowCount("BuyingTickets");
		    //For loop:Fetch data from excel input configuration file(excel sheet)
			log.info("*********************Buying Tickets Logger Initialized******************************* ");
			for(int i=1;i<= rowCount;i++)
			{
				singleEventViewURL = xllib.getExcelData("BuyingTickets", i, 0);
				readyToJoinDinnerLab = xllib.getExcelData("BuyingTickets", i,1);
				doYouWantToLogin = xllib.getExcelData("BuyingTickets", i,2);
				
				//Reading Login credentials from Excel File
				email = xllib.getExcelData("BuyingTickets", i, 3);
				password = xllib.getExcelData("BuyingTickets", i, 4);
				
				//Reading Signup values from Excel File
				firstName = xllib.getExcelData("BuyingTickets", i, 5);
			 	lastName = xllib.getExcelData("BuyingTickets", i, 6);
			 	city = xllib.getExcelData("BuyingTickets", i, 7);
			 	useOtherCity = xllib.getExcelData("BuyingTickets", i, 8);
				otherCity = xllib.getExcelData("BuyingTickets", i, 9);
				doYouHavePromoCode = xllib.getExcelData("BuyingTickets", i, 10);
				promoCode = xllib.getExcelData("BuyingTickets", i, 11);
				
				//Reading different cities from Excel File
				/*updateMyCitiesStatus = xllib.getExcelData("BuyingTickets", i, 12);
				selectAll = xllib.getExcelData("BuyingTickets", i, 13);
				Atlanta	= xllib.getExcelData("BuyingTickets", i, 14);	
				Austin	= xllib.getExcelData("BuyingTickets", i, 15);	
				Baltimore = xllib.getExcelData("BuyingTickets", i, 16);
			 	BatonRouge = xllib.getExcelData("BuyingTickets", i, 17);
			 	Birmingham = xllib.getExcelData("BuyingTickets", i, 18);
			 	Boston = xllib.getExcelData("BuyingTickets", i, 19);
				Charlotte = xllib.getExcelData("BuyingTickets", i, 20);
				Chicago = xllib.getExcelData("BuyingTickets", i, 21);
				Cleveland = xllib.getExcelData("BuyingTickets", i, 22);
				Columbus = xllib.getExcelData("BuyingTickets", i, 23);
				DC = xllib.getExcelData("BuyingTickets", i, 24);
				Dallas = xllib.getExcelData("BuyingTickets", i, 25);
				Denver	= xllib.getExcelData("BuyingTickets", i, 26);	
				Houston	= xllib.getExcelData("BuyingTickets", i, 27);	
				//Indianapolis = xllib.getExcelData("BuyingTickets", i, 28);
				KansasCity = xllib.getExcelData("BuyingTickets", i, 28);
				LosAngeles = xllib.getExcelData("BuyingTickets", i, 29);
				Miami = xllib.getExcelData("BuyingTickets", i, 30);
				Milwaukee = xllib.getExcelData("BuyingTickets", i, 31);
				Minneapolis = xllib.getExcelData("BuyingTickets", i, 32);
				Nashville = xllib.getExcelData("BuyingTickets", i, 33);
				NewOrleans = xllib.getExcelData("BuyingTickets", i, 34);
				NewYork = xllib.getExcelData("BuyingTickets", i, 35);
				Philadelphia = xllib.getExcelData("BuyingTickets", i, 36);	
				Phoenix	= xllib.getExcelData("BuyingTickets", i, 37);	
				Pittsburgh = xllib.getExcelData("BuyingTickets", i, 38);
				Portland = xllib.getExcelData("BuyingTickets", i, 39);
				//Raleigh = xllib.getExcelData("BuyingTickets", i, 40);
				SanAntonio = xllib.getExcelData("BuyingTickets", i, 40);
				SanDiego = xllib.getExcelData("BuyingTickets", i, 41);
				SanFrancisco = xllib.getExcelData("BuyingTickets", i, 42);
				Seattle = xllib.getExcelData("BuyingTickets", i, 43);
				StLouis = xllib.getExcelData("BuyingTickets", i, 44);*/
				
				//Reading Dietary values from Excel File
				dietaryPreferences = xllib.getExcelData("BuyingTickets", i, 12);
				dietaryRestrictions = xllib.getExcelData("BuyingTickets", i, 13);
				peanuts = xllib.getExcelData("BuyingTickets", i,14);
				shellfish = xllib.getExcelData("BuyingTickets", i, 15);
				treeNuts = xllib.getExcelData("BuyingTickets", i, 16);
				lactose = xllib.getExcelData("BuyingTickets", i, 17);
				gluten = xllib.getExcelData("BuyingTickets", i,18);
				noneOfThese = xllib.getExcelData("BuyingTickets", i,19);
		 		otherDietaryRestrictOrAllergies = xllib.getExcelData("BuyingTickets", i,20);
		 		OtherAllergiesTextArea = xllib.getExcelData("BuyingTickets", i,21);
		 		phoneNumberStatus = xllib.getExcelData("BuyingTickets", i, 22);
				phoneNumber = xllib.getExcelData("BuyingTickets", i, 23);
				
		 		//Reading Credit Card values from Excel File
				changeHomeCity = xllib.getExcelData("BuyingTickets", i,24);
				newHomeCity = xllib.getExcelData("BuyingTickets", i,25);
				applyPromoCode = xllib.getExcelData("BuyingTickets", i,26);
				accountPromoCode = xllib.getExcelData("BuyingTickets", i,27);
				nameOnCard = xllib.getExcelData("BuyingTickets", i, 28);
				cardNumber = xllib.getExcelData("BuyingTickets", i,29);
			 	expiryMonth = xllib.getExcelData("BuyingTickets", i,30);
			 	expiryYear = xllib.getExcelData("BuyingTickets", i,31);
			 	cvv = xllib.getExcelData("BuyingTickets", i,32);
			 	billingStreet = xllib.getExcelData("BuyingTickets", i,33);
			 	billingCity = xllib.getExcelData("BuyingTickets", i,34);
			 	billingState = xllib.getExcelData("BuyingTickets", i,35);
			 	billingZipCode = xllib.getExcelData("BuyingTickets", i,36);
			 	
			 	//Reading Ticket Purchase values from Excel File
			 	ticketPurchaseCity = xllib.getExcelData("BuyingTickets", i,37);
				eventName = xllib.getExcelData("BuyingTickets", i,38);
				seatingDate = xllib.getExcelData("BuyingTickets", i,39);
				seatingTime = xllib.getExcelData("BuyingTickets", i,40);
				purchaseQuantity = xllib.getExcelData("BuyingTickets", i,41);
				
				//System.out.println("doYouWantToLogin:"+doYouWantToLogin);
				driver.manage().window().maximize();
				driver.get(singleEventViewURL);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
				
				DietryRestrictionPage dietary = new DietryRestrictionPage();
				
				AddNewCreditCardInfoPage credit = new AddNewCreditCardInfoPage();
				
				log.info("Single Event View Url:"+singleEventViewURL);
				if(readyToJoinDinnerLab.equalsIgnoreCase(TestConstants.STATUS_TRUE)) //Join Now FLow
				{
					 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					 //Clicking on Join Now Button
					 driver.findElement(By.id("join-city-submit")).click();
					 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					 
					 //Selecting User Type as Member
					 //freeMember.selectUserTypeMember(driver);
				 		
				 	 //Calling Sign Up Method.
					 freeMember.signUpPageActions(driver,doYouHavePromoCode,promoCode,firstName,lastName,email,password,city,useOtherCity,otherCity);
					
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
					 }	
					 
					 //Clicking on Next Control
					 freeMember.clickingOnNextControl(driver);
					 
					 AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
					 
					 if(phoneNumberStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
					 {
							//Adding Phone Number
							creditCard.addingPhoneNumberActions(driver, phoneNumber);
							
							//Clicking on Submit Button
							freeMember.clickingOnSubmitButton(driver);
					 }
					 
					 //Clicking on Next Control
					 freeMember.clickingOnNextControl(driver);
					 
					 //Fetching Refer/Invite Link
					 freeMember.referLinkGetText(driver);
					 
					 //Clicking on Submit Button
					 creditCard.clickingOnSubmitButtonActions(driver);
			          
			         //Verifying Sign Up
			         freeMember.verifySignUp(driver);	
			         
					 //Condition to Select other cities to receive email for events.
					 //freeMember.selectOtherCitiesToReceiveMailForEvents(driver,useOtherCity,city,otherCity,updateMyCitiesStatus,selectAll,Atlanta,Austin,Baltimore,BatonRouge,Birmingham,Boston,Charlotte,Chicago,Cleveland,Columbus,DC,Dallas, Denver,Houston,KansasCity,LosAngeles,Miami,Milwaukee,Minneapolis,Nashville,NewOrleans,NewYork,Philadelphia,Phoenix,Pittsburgh,Portland,SanAntonio,SanDiego,SanFrancisco,Seattle,StLouis);
						
			         //checking the status of Sign up page and updating the result in excel	
					 if(FreeMemberSignUpPage.signUpSuccess)
					 {
						 log.info("Dinner Lab Subscriber Account is created successfully.");
						 log.info("You are Paid Member of Dinner Lab.Thank you.");
						 isTestPassed="PASS";
						 xllib.writeToExcel("BuyingTickets", i, 43, isTestPassed);
													
						 //Clicking on Upgrade Account Button
						 credit.clickingOnUpgradeAccountButton(driver);
						 	  
						 //Adding Phone Number into Payment Page
						 //credit.addingPhoneNumberActions(driver,phoneNumber);
						 
						 if(changeHomeCity.equalsIgnoreCase(TestConstants.STATUS_TRUE))
						 {
							//Calling Change Home City method
				        	credit.changeHomeCity(driver,newHomeCity);
						 }
							
						 if(applyPromoCode.equalsIgnoreCase(TestConstants.STATUS_TRUE) && useOtherCity.equalsIgnoreCase(TestConstants.STATUS_FALSE))
						 {
							//Calling applying Promo Code method
				        	credit.applyingPromoCode(driver,accountPromoCode);
						 }
						 
						 //Calling Add New Credit Card Info Method
						 credit.addNewCreditCardInfoActions(driver,nameOnCard,cardNumber,expiryMonth,expiryYear,cvv,billingStreet,billingCity,billingState,billingZipCode);
						 	  
						 //clicking on Become A Member Button
						 credit.clickingOnBecomeAMemberButton(driver);
						 	
						 //verifying Payment success
						 upgradeAccountStatus = credit.verifyPaymentPageAction(driver);
						 System.out.println("upgradeAccountStatus100:"+upgradeAccountStatus);
					}
					else
					{
						isTestPassed = "FAIL";
						xllib.writeToExcel("BuyingTickets", i, 43, isTestPassed);
					}
					 MemberEventTicketPurchasePage ticket = new MemberEventTicketPurchasePage();
					 
					if(AddNewCreditCardInfoPage.accountUpgradedStatus)
					{
						isTestPassed="PASS";
						xllib.writeToExcel("BuyingTickets", i, 44, isTestPassed);
						
						//Calling Ticket Purchase Actions Method
			        	ticket.memberEventTicketPurchase(driver,ticketPurchaseCity,eventName,seatingDate,seatingTime,purchaseQuantity);
			        	
			        	//Clicking on Continue Button
			        	ticket.clickingOnContinueButton(driver);
			        	
			        	//Clicking on Purchase Button
			        	ticketPurchaseStatus = ticket.memberEventTicketPurchaseFinal(driver);
					}
					else
					{
						isTestPassed="FAIL";
						xllib.writeToExcel("BuyingTickets", i, 44, isTestPassed);
					}
					 
				}else if(doYouWantToLogin.equalsIgnoreCase(TestConstants.STATUS_TRUE)) //Login Flow
				{
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					//Calling Login Link method
					loginLogout.clickingOnLoginLink(driver);
					
				    //Calling Member Login Method
					loginLogout.loginPageActions(driver,email,password);
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					
					fetchMembershipStatus(driver);
					
					MemberEventTicketPurchasePage ticket = new MemberEventTicketPurchasePage();
					
					//Calling Ticket Purchase Actions Method
		        	ticket.memberEventTicketPurchase(driver,ticketPurchaseCity,eventName,seatingDate,seatingTime,purchaseQuantity);
		        	
		        	//Clicking on Continue Button
		        	ticket.clickingOnContinueButton(driver);
		        	
		        	//Adding credit card information for Free Member
		        	if(membershipStatus.equalsIgnoreCase(TestConstants.USER_TYPE_MEMBER))
		        	{
		        		//Calling Add New Credit Card Info Method
						credit.addNewCreditCardInfoActions(driver,nameOnCard,cardNumber,expiryMonth,expiryYear,cvv,billingStreet,billingCity,billingState,billingZipCode);
		        	}
		        	
		        	//Clicking on Purchase Button
		        	ticketPurchaseStatus = ticket.memberEventTicketPurchaseFinal(driver);				
					
					//Writing Login Status to Output file
					if(MemberLoginLogoutPage.loggedInStatus)
					{
						isTestPassed="PASS";
						xllib.writeToExcel("BuyingTickets", i, 42, isTestPassed);
					}else if(!MemberLoginLogoutPage.loggedInStatus)
				    {
				    	isTestPassed = "FAIL";
						xllib.writeToExcel("BuyingTickets", i, 42, isTestPassed);
					}
				}else //Signup FLow
				{
					 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					 //Clicking on Signup Link
					 driver.findElement(By.xpath("//a[@class='sign-up']")).click();
					 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					 
					 //Selecting User Type as Member
					 //freeMember.selectUserTypeMember(driver);
				 		
				 	 //Calling Sign Up Method.
					 freeMember.signUpPageActions(driver,doYouHavePromoCode,promoCode,firstName,lastName,email,password,city,useOtherCity,otherCity);
					
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
					 }	
					 
					 //Clicking on Next Control
					 freeMember.clickingOnNextControl(driver);
					 
					 AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
					 
					 if(phoneNumberStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
					 {
							//Adding Phone Number
							creditCard.addingPhoneNumberActions(driver, phoneNumber);
							
							//Clicking on Submit Button
							freeMember.clickingOnSubmitButton(driver);
					 }
					 
					 //Clicking on Next Control
					 freeMember.clickingOnNextControl(driver);
						
					 //Fetching Refer/Invite Link
					 freeMember.referLinkGetText(driver);
					
					 //Clicking on Submit Button
					 creditCard.clickingOnSubmitButtonActions(driver);
			          
			         //Verifying Sign Up
			         freeMember.verifySignUp(driver);	 
											
					 //Condition to Select other cities to receive email for events.
					 //freeMember.selectOtherCitiesToReceiveMailForEvents(driver,useOtherCity,city,otherCity,updateMyCitiesStatus,selectAll,Atlanta,Austin,Baltimore,BatonRouge,Birmingham,Boston,Charlotte,Chicago,Cleveland,Columbus,DC,Dallas, Denver,Houston,KansasCity,LosAngeles,Miami,Milwaukee,Minneapolis,Nashville,NewOrleans,NewYork,Philadelphia,Phoenix,Pittsburgh,Portland,SanAntonio,SanDiego,SanFrancisco,Seattle,StLouis);
					
			         //checking the status of Sign up page and updating the result in excel	
					 if(FreeMemberSignUpPage.signUpSuccess)
					 {
						 log.info("Dinner Lab Subscriber Account is created successfully.");
						 log.info("You are Paid Member of Dinner Lab.Thank you.");
						 isTestPassed="PASS";
						 xllib.writeToExcel("BuyingTickets", i, 43, isTestPassed);
													
						 //Clicking on Upgrade Account Button
						 credit.clickingOnUpgradeAccountButton(driver);
						 	  
						 //Adding Phone Number into Payment Page
						 //credit.addingPhoneNumberActions(driver,phoneNumber);
						 
						 if(changeHomeCity.equalsIgnoreCase(TestConstants.STATUS_TRUE))
						 {
							//Calling Change Home City method
				        	credit.changeHomeCity(driver,newHomeCity);
						 }
							
						 if(applyPromoCode.equalsIgnoreCase(TestConstants.STATUS_TRUE) && useOtherCity.equalsIgnoreCase(TestConstants.STATUS_FALSE))
						 {
							//Calling applying Promo Code method
				        	credit.applyingPromoCode(driver,accountPromoCode);
						 }
						 	  
						 //Calling Add New Credit Card Info Method
						 credit.addNewCreditCardInfoActions(driver,nameOnCard,cardNumber,expiryMonth,expiryYear,cvv,billingStreet,billingCity,billingState,billingZipCode);
						 	  
						 //clicking on Become A Member Button
						 credit.clickingOnBecomeAMemberButton(driver);
						 	
						 //verifying Payment success
						 credit.verifyPaymentPageAction(driver);
						 //System.out.println("upgradeAccountStatus100:"+upgradeAccountStatus);
					}
					else
					{
						isTestPassed = "FAIL";
						xllib.writeToExcel("BuyingTickets", i, 43, isTestPassed);
					}
					 
					MemberEventTicketPurchasePage ticket = new MemberEventTicketPurchasePage();
					
					if(AddNewCreditCardInfoPage.accountUpgradedStatus)
					{
						isTestPassed="PASS";
						xllib.writeToExcel("BuyingTickets", i, 44, isTestPassed);
						
						//Calling Ticket Purchase Actions Method
			        	ticket.memberEventTicketPurchase(driver,ticketPurchaseCity,eventName,seatingDate,seatingTime,purchaseQuantity);
			        	
			        	//Clicking on Continue Button
			        	ticket.clickingOnContinueButton(driver);
			        	
			        	//Clicking on Purchase Button
			        	ticketPurchaseStatus = ticket.memberEventTicketPurchaseFinal(driver);
					}
					else
					{
						isTestPassed="FAIL";
						xllib.writeToExcel("BuyingTickets", i, 44, isTestPassed);
					}
				}
				
				//Condition for checking single test case
	        	if(rowCount !=1)
	        	{
	        		
	        		//Calling Log Out Method
					loginLogout.logoutActions(driver);
	        	}
	        	            			        	
	        	System.out.println("ticketPurchaseStatus1122:"+ticketPurchaseStatus);
						
				if(ticketPurchaseStatus)
				{
					isTestPassed="PASS";
					log.info("Event Ticket has been purchased successfully.");
					xllib.writeToExcel("BuyingTickets", i, 45,TestConstants.TICKET_AVAILABLE);
					xllib.writeToExcel("BuyingTickets", i, 46, isTestPassed);
					xllib.writeToExcel("BuyingTickets", i, 47,MemberEventTicketPurchasePage.subTotalText);
					xllib.writeToExcel("BuyingTickets", i, 48,MemberEventTicketPurchasePage.taxText);
					xllib.writeToExcel("BuyingTickets", i, 49,MemberEventTicketPurchasePage.creditsText);
					xllib.writeToExcel("BuyingTickets", i, 50,MemberEventTicketPurchasePage.orderTotalText);
					xllib.writeToExcel("BuyingTickets", i, 51,MemberEventTicketPurchasePage.quantityAfterPayment);
				}
				else
				{
					isTestPassed="FAIL";
					log.info("Event Ticket has not been purchased.");
					xllib.writeToExcel("BuyingTickets", i, 45,TestConstants.TICKET_SOLD_OUT);
					xllib.writeToExcel("BuyingTickets", i, 46, isTestPassed);
					xllib.writeToExcel("BuyingTickets", i, 47,isTestPassed);
					xllib.writeToExcel("BuyingTickets", i, 48,isTestPassed);
					xllib.writeToExcel("BuyingTickets", i, 49,isTestPassed);
					xllib.writeToExcel("BuyingTickets", i, 50,isTestPassed);
					xllib.writeToExcel("BuyingTickets", i, 51,isTestPassed);
				}
			}//End of For Loop
		}//End of try block
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Test Execution Failed");
		}
	}//End of Buying Tickets method
	/**
	 * Test Case for fetching Membership Status
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public void fetchMembershipStatus(WebDriver driver) 
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Actions actions = new Actions(driver);
			
			WebElement parent = driver.findElement(By.xpath("//li[@class='parent']")); // member drop down
			actions.moveToElement(parent).build().perform();
			//Clicking on Account Overview Link
			driver.findElement(By.xpath("//a[contains(text(),'Account Overview')]")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			membershipStatus = driver.findElement(By.xpath("//div[contains(text(),'Membership Status:')]/span")).getText();
			System.out.println("membershipStatus:"+membershipStatus);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}//End of try block
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Test Execution Failed");
		}
	}
}
