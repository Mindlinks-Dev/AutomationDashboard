/**
 * @author: Basappa Hunsikatti
 * @Created Date :06/10/2015
 * @Updated Date :
 * @Comments This automation class will purchase Free Member Event Tickets.
 */

package com.dinnerLab.userPages.Helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;

public class ChefTicketPurchasePage 
{
	public static String loginEmail;
	 private String loginPassword;
	 private String loginURL;
	 boolean present;
	 public static String eventCity;
	 public static String eventName;
	 public static String seatingDate;
	 public static String seatingTime;
	 public static String purchaseQuantity;
	 String isTestPassed="FAIL";
	 public static int rowCount;
	 public boolean eventTicketPurchaseSuccess;
	 public static Boolean ticketPurchaseStatus;
	 public static String memberCityName;
	 
	 private String defaultCardStatus;
	 private String nameOnCard;
	 private String cardNumber;
	 private String expiryMonth;
	 private String expiryYear;
	 private static String phoneNumber;
	 private String cvv;
	 private static String saveCard;
	 private String billingStreet;
	 private String billingCity;
	 private String billingState;
	 private String billingZipCode;
	 public static boolean upgradeAccountStatus;
	
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
	 public static boolean purchaseButtonStatus;
	 //String isTicketSoldOut="Event Ticket Available";
	 String isTicketSoldOut="FAIL";
	 int i=0;
	 private static Logger log = Logger.getLogger(PaidMemberTicketPurchasePage.class);
	 //MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	
	 //MemberEventTicketPurchasePage ticket = new MemberEventTicketPurchasePage();
	 //AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
	 //PaidMemberTicketPurchasePage memberTicket = new PaidMemberTicketPurchasePage();
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
			
			int rowsCount = xllib.getRowCount("ChefTicketPurchase");
		    //For loop:Fetch data from excel input configuration file(excel sheet)
			for(int i=1;i<= rowsCount;i++)
			{
				loginURL = xllib.getExcelData("ChefTicketPurchase", i, 0);
				loginEmail = xllib.getExcelData("ChefTicketPurchase", i, 1);
				loginPassword = xllib.getExcelData("ChefTicketPurchase", i, 2);
				eventCity = xllib.getExcelData("ChefTicketPurchase", i,3);
				eventName = xllib.getExcelData("ChefTicketPurchase", i,4);
				seatingDate = xllib.getExcelData("ChefTicketPurchase", i,5);
				seatingTime = xllib.getExcelData("ChefTicketPurchase", i,6);
				purchaseQuantity = xllib.getExcelData("ChefTicketPurchase", i,7);
				
				defaultCardStatus = xllib.getExcelData("ChefTicketPurchase", i, 8);
				nameOnCard = xllib.getExcelData("ChefTicketPurchase", i, 9);
				cardNumber = xllib.getExcelData("ChefTicketPurchase", i,10);
			 	expiryMonth = xllib.getExcelData("ChefTicketPurchase", i,11);
			 	expiryYear = xllib.getExcelData("ChefTicketPurchase", i,12);
			 	cvv = xllib.getExcelData("ChefTicketPurchase", i,13);
			 	billingStreet = xllib.getExcelData("ChefTicketPurchase", i,14);
			 	billingCity = xllib.getExcelData("ChefTicketPurchase", i,15);
			 	billingState = xllib.getExcelData("ChefTicketPurchase", i,16);
			 	billingZipCode = xllib.getExcelData("ChefTicketPurchase", i,17);
				
			 	MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
			 	
			 	//Calling Login URL method
				loginLogout.loginURLActions(driver,loginURL);
				
			 	//Calling Member Login Method
				loginLogout.loginPageActions(driver,loginEmail,loginPassword);
						
				
				//Checking whether login credentials valid or not
				if(MemberLoginLogoutPage.loggedInStatus)
				{

					//FerrariMPHandler obj = new FerrariMPHandler();
			        //Map<String,String> myMap = new HashMap<String,String>();
			        try
			        {
			        	isTestPassed="PASS";
			        	xllib.writeToExcel("ChefTicketPurchase", i, 18, isTestPassed);
			        	//System.out.println("In MemberLogin: Result Value:"+ScriptHandler.result);
			        	//ScriptHandler.comment.add("Script Chef TicketPurchase, Test Data "+i+": Success in Login page.");
			        	//output.outputReport("IndividualTicketPurchaseReport", 1, 3,TestConstants.RESULT_PASS);
			        	
			        	PaidMemberTicketPurchasePage memberTicket = new PaidMemberTicketPurchasePage();
			        	
			        	//Fetching Member City
			        	memberTicket.memberCityActions(driver);
			        	
			        	MemberEventTicketPurchasePage ticket = new MemberEventTicketPurchasePage();
			        	
			        	//Calling Ticket Purchase Actions Method
			        	ticket.memberEventTicketPurchase(driver,eventCity,eventName,seatingDate,seatingTime,purchaseQuantity);
			        	
			        	System.out.println("before click on continue button");
			        	//try{
			        		//Clicking on Heads up pop up Continue Button
				        	ticket.clickingOnContinueButton(driver);
				        	System.out.println("after click on continue button");	
			        	//} catch(Exception ex)
				        //{
			        		//System.out.println("inside catch clickingOnContinueButton");
			        		//ex.printStackTrace();
				        	
				       // }
			        	
				        FreeMemberTicketPurchasePage freeTicket = new FreeMemberTicketPurchasePage();
				        	
			        	//Condition to check credit card exists or not
			        	freeTicket.checkingDefaultCreditCard(driver);
			        	
			        	AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
			        	
			        	if(!FreeMemberTicketPurchasePage.creditCardExistStatus)
			        	{
			        		//Calling Credit Card Info Method
			        		creditCard.addNewCreditCardInfoActions(driver,nameOnCard, cardNumber, expiryMonth, expiryYear, cvv, billingStreet, billingCity, billingState, billingZipCode);
			        	}
			        	
			        	System.out.println("order:"+MemberEventTicketPurchasePage.orderTotalText);
			        	System.out.println("defaultCardStatus:"+defaultCardStatus);
			        	
			        	
			        	
			        	if(!MemberEventTicketPurchasePage.orderTotalText.equalsIgnoreCase(TestConstants.DOLLAR_ZERO))
			        	{
			        		if(defaultCardStatus.equalsIgnoreCase(TestConstants.STATUS_FALSE))
				        	{
				        		System.out.println("before credit card method2");
				        		
				        		//Calling use different card action method
				        		creditCard.useDifferentCardAction(driver);
				        		
				        		//Calling Credit Card Info Method
					        	creditCard.addNewCreditCardInfoActions(driver,nameOnCard, cardNumber, expiryMonth, expiryYear, cvv, billingStreet, billingCity, billingState, billingZipCode);
					        	System.out.println("after credit card method2");
				        	}
			        	}
				        
			        	//Clicking on Purchase Button
			        	ticketPurchaseStatus = ticket.memberEventTicketPurchaseFinal(driver);
			        	
			        	if(rowsCount !=1)
						{
							//Calling Logout method
							loginLogout.logoutActions(driver);
						}
			            
			        	//StripeEmailId = ChefTicketPurchasePage.loginEmail;
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
					xllib.writeToExcel("ChefTicketPurchase", i, 18, isTestPassed);
					//ScriptHandler.comment.add("Script Chef TicketPurchase, Test Data "+i+": Failed in Login page.");	
			    	//output.outputReport("IndividualTicketPurchaseReport", 1, 3,TestConstants.RESULT_FAIL);
				}
				
				System.out.println("Soldout Status:"+MemberEventTicketPurchasePage.soldOutStatus);
				System.out.println("Ticket Purchase Status:"+MemberEventTicketPurchasePage.eventTicketPurchaseSuccess);
				//Verify event ticket Purchased or not.
				if(ticketPurchaseStatus)
				{
					isTestPassed="PASS";
					log.info("Event Ticket has been purchased successfully.");
					xllib.writeToExcel("ChefTicketPurchase", i, 19,TestConstants.TICKET_AVAILABLE);
					xllib.writeToExcel("ChefTicketPurchase", i, 20, isTestPassed);
					xllib.writeToExcel("ChefTicketPurchase", i, 21,MemberEventTicketPurchasePage.subTotalText);
					xllib.writeToExcel("ChefTicketPurchase", i, 22,MemberEventTicketPurchasePage.taxText);
					xllib.writeToExcel("ChefTicketPurchase", i, 23,MemberEventTicketPurchasePage.creditsText);
					xllib.writeToExcel("ChefTicketPurchase", i, 24,MemberEventTicketPurchasePage.orderTotalText);
					xllib.writeToExcel("ChefTicketPurchase", i, 25,MemberEventTicketPurchasePage.quantityAfterPayment);
					//ScriptHandler.comment.add("Script Chef Ticket Purchase, Test Data "+i+": Success in Script Chef Ticket Purchase Page.");
				}
				else
				{
					isTestPassed="FAIL";
					log.info("Event Ticket has not been purchased.");
					xllib.writeToExcel("ChefTicketPurchase", i, 19,TestConstants.TICKET_SOLD_OUT);
					xllib.writeToExcel("ChefTicketPurchase", i, 20, isTestPassed);
					xllib.writeToExcel("ChefTicketPurchase", i, 21,isTestPassed);
					xllib.writeToExcel("ChefTicketPurchase", i, 22,isTestPassed);
					xllib.writeToExcel("ChefTicketPurchase", i, 23,isTestPassed);
					xllib.writeToExcel("ChefTicketPurchase", i, 24,isTestPassed);
					xllib.writeToExcel("ChefTicketPurchase", i, 25,isTestPassed);
					//ScriptHandler.comment.add("Script Chef Ticket Purchase"+i+": Failed in Script Chef Ticket Purchase Page.");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Test Execution Failed");
		}
	}
}
