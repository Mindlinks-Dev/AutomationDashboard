/**
 * @author: Basappa Hunsikatti
 * @Created Date :06/10/2015
 * @Updated Date :
 * @Comments This automation class will purchase Free Member Event Tickets.
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

public class FreeMemberTicketPurchasePage 
{
	public static String loginEmail;
	 private String loginPassword;
	 private String loginURL;
	 boolean present;
	 public String ticketPurchase;
	 public String city;
	 public String eventName;
	 public String seatingDate;
	 public String seatingTime;
	 public String purchaseQuantity;
	 String isTestPassed="FAIL";
	 public static int rowCount;
	 public boolean eventTicketPurchaseSuccess;
	 public static Boolean ticketPurchaseStatus;
	 public static Boolean upgradeNowStatus;
	 public String memberCityName;
	 
	 private String upgradeAccount;
	 private String nameOnCard;
	 private String cardNumber;
	 private String expiryMonth;
	 private String expiryYear;
	 private String cvv;
	 private String billingStreet;
	 private String billingCity;
	 private String billingState;
	 private String billingZipCode;
	 public static boolean upgradeAccountStatus;
	 private String defaultCardStatus;
	 public static String eventNameText;
	 public static String cityText;
	 public static String dateText;
	 public static String timeText;
	 public static String ticketQuantityText;
	 public static String subTotalText;
	 public static String taxText;
	 public static String creditsText;
	 public static String orderTotalText;
	 public static String eventNameAfterPayment;
	 public static String cityAfterPayment;
	 public static String dateAfterPayment;
	 public static String timeAfterPayment;
	 public static String quantityAfterPayment;
	 public static Boolean ticketClosedStatus;
	 public static String multipleEventNames = "";
	 public String tempEventNames;
	 public static String changeHomeCity;
	 public static String newHomeCity;
	 public static String applyPromoCode;
	 public static String accountPromoCode;
	 public static boolean creditCardExistStatus;
	 public static boolean purchaseButtonStatus;
	 private String dietaryRestrictions;
	 private String dietaryPreferences;
	 private String peanuts;
	 private String shellfish;
	 private String treeNuts;
	 private String lactose;
	 private String gluten;
	 private String noneOfThese;
	 private String otherDietaryRestrictOrAllergies;
	 private String OtherAllergiesTextArea;
	 //String isTicketSoldOut="Event Ticket Available";
	 String isTicketSoldOut="FAIL";
	 int i=0;
	 private static Logger log = Logger.getLogger(PaidMemberTicketPurchasePage.class);
	 // MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	
	 // MemberEventTicketPurchasePage ticket = new MemberEventTicketPurchasePage();
	 // AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
	 // PaidMemberTicketPurchasePage memberTicket = new PaidMemberTicketPurchasePage();
	 public static String StripeEmailId;
	 	 
	 /**
		 * Test Case for Reading the excel data and login into the Login Page
		 * portal on successful login to the application.
		 * Input: WebDriver
		 * Output: Void
	 */
	public void freeMemberTicketPurchaseInitialPage(WebDriver driver)
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			int rowsCount = xllib.getRowCount("FreeMemberTicketPurchase");
		    //For loop:Fetch data from excel input configuration file(excel sheet)
			for(int i=1;i<= rowsCount;i++)
			{
				loginURL = xllib.getExcelData("FreeMemberTicketPurchase", i, 0);
				loginEmail = xllib.getExcelData("FreeMemberTicketPurchase", i, 1);
				loginPassword = xllib.getExcelData("FreeMemberTicketPurchase", i, 2);
				
				upgradeAccount = xllib.getExcelData("FreeMemberTicketPurchase", i, 3);
				changeHomeCity = xllib.getExcelData("FreeMemberTicketPurchase", i,4);
				newHomeCity = xllib.getExcelData("FreeMemberTicketPurchase", i,5);
				applyPromoCode = xllib.getExcelData("FreeMemberTicketPurchase", i,6);
				accountPromoCode = xllib.getExcelData("FreeMemberTicketPurchase", i,7);
				nameOnCard = xllib.getExcelData("FreeMemberTicketPurchase", i, 8);
				cardNumber = xllib.getExcelData("FreeMemberTicketPurchase", i,9);
			 	expiryMonth = xllib.getExcelData("FreeMemberTicketPurchase", i,10);
			 	expiryYear = xllib.getExcelData("FreeMemberTicketPurchase", i,11);
			 	cvv = xllib.getExcelData("FreeMemberTicketPurchase", i,12);
			 	//saveCard = xllib.getExcelData("FreeMemberTicketPurchase", i,13);
			 	billingStreet = xllib.getExcelData("FreeMemberTicketPurchase", i,13);
			 	billingCity = xllib.getExcelData("FreeMemberTicketPurchase", i,14);
			 	billingState = xllib.getExcelData("FreeMemberTicketPurchase", i,15);
			 	billingZipCode = xllib.getExcelData("FreeMemberTicketPurchase", i,16);
				
			 	ticketPurchase = xllib.getExcelData("FreeMemberTicketPurchase", i, 17);
			 	defaultCardStatus = xllib.getExcelData("FreeMemberTicketPurchase", i, 18);
			 	city = xllib.getExcelData("FreeMemberTicketPurchase", i,19);
				eventName = xllib.getExcelData("FreeMemberTicketPurchase", i,20);
				seatingDate = xllib.getExcelData("FreeMemberTicketPurchase", i,21);
				seatingTime = xllib.getExcelData("FreeMemberTicketPurchase", i,22);
				purchaseQuantity = xllib.getExcelData("FreeMemberTicketPurchase", i,23);
				
				dietaryPreferences = xllib.getExcelData("FreeMemberTicketPurchase", i, 24);
				dietaryRestrictions = xllib.getExcelData("FreeMemberTicketPurchase", i, 25);
				peanuts = xllib.getExcelData("FreeMemberTicketPurchase", i,26);
				shellfish = xllib.getExcelData("FreeMemberTicketPurchase", i, 27);
				treeNuts = xllib.getExcelData("FreeMemberTicketPurchase", i, 28);
				lactose = xllib.getExcelData("FreeMemberTicketPurchase", i, 29);
				gluten = xllib.getExcelData("FreeMemberTicketPurchase", i,30);
				noneOfThese = xllib.getExcelData("FreeMemberTicketPurchase", i,31);
				otherDietaryRestrictOrAllergies = xllib.getExcelData("FreeMemberTicketPurchase", i,32);
		 		OtherAllergiesTextArea = xllib.getExcelData("FreeMemberTicketPurchase", i,33);
		 		
			 	//Calling Login URL method
			 	MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	
				loginLogout.loginURLActions(driver,loginURL);
				
			 	//Calling Member Login Method
				Boolean loginStatus = loginLogout.loginPageActions(driver,loginEmail,loginPassword);
						
				
				//Checking whether login credentials valid or not
				//if(MemberLoginLogoutPage.loggedInStatus)
				if(loginStatus)
				{

					//FerrariMPHandler obj = new FerrariMPHandler();
			        //Map<String,String> myMap = new HashMap<String,String>();
			        try
			        {
			        	isTestPassed="PASS";
			        	xllib.writeToExcel("FreeMemberTicketPurchase", i, 34, isTestPassed);
			        	//System.out.println("In MemberLogin: Result Value:"+ScriptHandler.result);
			        	//ScriptHandler.comment.add("Script FreeMemberTicketPurchase, Test Data "+i+": Success in Login page.");
			        	//output.outputReport("IndividualTicketPurchaseReport", 1, 3,TestConstants.RESULT_PASS);
			        	
			        	AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
			        	
			        	if(upgradeAccount.equalsIgnoreCase(TestConstants.STATUS_TRUE) && AddNewCreditCardInfoPage.UpgradeAccountButtonStatus)
			        	{
			        		try
			        		{
			        			//Calling Upgrade Account Button method
			        			creditCard.clickingOnUpgradeAccountButton(driver);
			        			
			        			//Calling Change Home City method
				        		creditCard.changeHomeCity(driver,newHomeCity);
				        		
				        		//Calling applying Promo Code method
				        		creditCard.applyingPromoCode(driver,accountPromoCode);
				        		
				        		//Calling Credit Card Info Method
					        	creditCard.addNewCreditCardInfoActions(driver,nameOnCard, cardNumber, expiryMonth, expiryYear, cvv, billingStreet, billingCity, billingState, billingZipCode);
					        						        	
					        	//clicking on Become A Member Button
					        	creditCard.clickingOnBecomeAMemberButton(driver);
					        	
					        	//Calling verify payment page actions method
					        	creditCard.verifyPaymentPageAction(driver);
					        	
					        	if(AddNewCreditCardInfoPage.accountUpgradedStatus)
								{
									isTestPassed="PASS";
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 35, isTestPassed);
									//ScriptHandler.comment.add("Script Paid Membership, Test Data "+i+": Success in Payment page.");
								}
								else
								{
									isTestPassed="FAIL";
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 35, isTestPassed);
									//ScriptHandler.comment.add("Script Paid Membership"+i+": Failed in Payment page.");
								}
			        		}catch(Exception ex)
					        {
					        	ex.printStackTrace();
					        }
			        	}
			        	
			        	if(ticketPurchase.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			        	{
			        		try
			        		{
			        			MemberEventTicketPurchasePage ticket = new MemberEventTicketPurchasePage();
					        	
					        	//Calling Ticket Purchase Actions Method
					        	ticket.memberEventTicketPurchase(driver,city,eventName,seatingDate,seatingTime,purchaseQuantity);
					        	
					        	DietryRestrictionPage dietary = new DietryRestrictionPage();
								
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
						            
						            //Calling clicking on Save Button method
						            dietary.clickingOnSaveButton(driver);
						            
								}else if(dietaryPreferences.equalsIgnoreCase(TestConstants.STATUS_FALSE))
								{
									log.info("No Dietary Preferences");
									//Calling clicking on Save Button method
						            //dietary.clickingOnSaveButton(driver);
								}
								
					        	//Clicking on Heads up pop up Continue Button
					        	ticket.clickingOnContinueButton(driver);
					        	
					        	//Condition to check credit card exists or not
					        	checkingDefaultCreditCard(driver);
					        	
					        	if(!creditCardExistStatus)
					        	{
					        		//Calling Credit Card Info Method
						        	creditCard.addNewCreditCardInfoActions(driver,nameOnCard, cardNumber, expiryMonth, expiryYear, cvv, billingStreet, billingCity, billingState, billingZipCode);
						        }else if(!MemberEventTicketPurchasePage.orderTotalText.equalsIgnoreCase(TestConstants.DOLLAR_ZERO))
					        	{
					        		if(defaultCardStatus.equalsIgnoreCase(TestConstants.STATUS_FALSE))
						        	{
						        		//Calling use different card action method
						        		creditCard.useDifferentCardAction(driver);
						        		
						        		//Calling Credit Card Info Method
							        	creditCard.addNewCreditCardInfoActions(driver,nameOnCard, cardNumber, expiryMonth, expiryYear, cvv, billingStreet, billingCity, billingState, billingZipCode);
							        }
					        	}
					        	
					        	//Clicking on Purchase Button
					        	ticket.memberEventTicketPurchaseFinal(driver);
					        	System.out.println("Soldout Status:"+MemberEventTicketPurchasePage.soldOutStatus);
								System.out.println("Ticket Purchase Status:"+MemberEventTicketPurchasePage.eventTicketPurchaseSuccess);
								//Verify event ticket Purchased or not.
								if(MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
								{
									isTestPassed="PASS";
									log.info("Event Ticket has been purchased successfully.");
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 36,TestConstants.TICKET_AVAILABLE);
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 37, isTestPassed);
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 38,MemberEventTicketPurchasePage.subTotalText);
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 39,MemberEventTicketPurchasePage.taxText);
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 40,MemberEventTicketPurchasePage.creditsText);
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 41,MemberEventTicketPurchasePage.orderTotalText);
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 42,MemberEventTicketPurchasePage.ticketQuantityText);
								}
								else
								{
									isTestPassed="FAIL";
									log.info("Event Ticket has not been purchased.");
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 36,TestConstants.TICKET_SOLD_OUT);
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 37, isTestPassed);
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 38,isTestPassed);
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 39,isTestPassed);
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 40,isTestPassed);
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 41,isTestPassed);
									xllib.writeToExcel("FreeMemberTicketPurchase", i, 42,isTestPassed);
								}
			        			
			        		}catch(Exception ex)
					        {
					        	ex.printStackTrace();
					        }
			        	}
			        	
			        	if(rowsCount !=1)
						{
			            	MemberLoginLogoutPage logout = new MemberLoginLogoutPage();
			            	
			            	//Calling Logout method
							logout.logoutActions(driver);
						}
			        	
			        	//Calling Log Out Method
						//loginLogout.logoutActions(driver);
			            			        	
			        	//StripeEmailId = FreeMemberTicketPurchasePage.loginEmail;
			        	//System.out.println("StripeEmailId123:"+StripeEmailId);
			        	//myMap = obj.getDetails(StripeEmailId);
			        	
			        	//Calling write Stripe details to Paid Member Excel Report file method.
			        	//stripeData.individualTicketPurchaseStripeDetails(driver);
			        }
			        catch(Exception ex)
			        {
			        	ex.printStackTrace();
			            //System.out.println("Exception inside paidmember while calling getDetails() of MPHandler: "+ex.getMessage());
			        }

			    }else if(!MemberLoginLogoutPage.loggedInStatus)
			    {
			    	isTestPassed = "FAIL";
					xllib.writeToExcel("FreeMemberTicketPurchase", i, 34, isTestPassed);
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
	 * Test Case for Checking Credit Card Information is already added
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void checkingDefaultCreditCard(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 creditCardExistStatus = driver.findElement(By.xpath("//div[@class='default-card']")).isDisplayed();
	         System.out.println("isCardExists123:"+creditCardExistStatus);
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(NoSuchElementException e)
		{
			//e.printStackTrace();
		}
	 }
	 /**
		 * Test Case for verifying upgrade now
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
		 */
	public void verifyUpgradeNow(WebDriver driver,String city,String eventName)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//a[@href='/events/']")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				
			WebElement changeCity= driver.findElement(By.xpath("//div[@class='form-item']/form[1]/select[1]"));
			Select st=new Select(changeCity);
			st.selectByVisibleText(city);
				
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[contains(text(),'"+eventName+"')]")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				
			upgradeNowStatus = driver.findElement(By.xpath("//a[contains(text(),'Upgrade Now')]")).isDisplayed();
			System.out.println("upgradeNowStatus:"+upgradeNowStatus);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
