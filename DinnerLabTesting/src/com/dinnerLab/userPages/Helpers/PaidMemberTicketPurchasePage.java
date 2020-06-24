/**
 * @author: Basappa Hunsikatti
 * @Created Date :31/07/2015
 * @Updated Date :24/09/2015
 * @Comments This automation class will purchase Member/Chef Event Tickets for Individual.
 */

package com.dinnerLab.userPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;
import com.dinnerLab.util.ExcelReport;
import com.dinnerLab.util.OutputReport;
import com.pack.ScriptHandler;

public class PaidMemberTicketPurchasePage {
	
	 public static String loginEmail;
	 private String loginPassword;
	 private String loginURL;
	 boolean present;
	 public static String city;
	 public static String eventName;
	 public static String seatingDate;
	 public static String seatingTime;
	 public static String purchaseQuantity;
	 String isTestPassed="FAIL";
	 public static int rowCount;
	 public boolean eventTicketPurchaseSuccess;
	 public static String memberCityName;
	 public boolean ticketPurchaseStatus;
	 private String defaultCardStatus;
	 private String nameOnCard;
	 private String cardNumber;
	 private String expiryMonth;
	 private String expiryYear;
	 private String cvv;
	 private String billingStreet;
	 private String billingCity;
	 private String billingState;
	 private String billingZipCode;
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
	 //MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	
	 //MemberEventTicketPurchasePage ticket = new MemberEventTicketPurchasePage();
	 //AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
	 /**
		 * Test Case for Reading the excel data and login into the Login Page
		 * portal on successful login to the application.
		 * Input: WebDriver
		 * Output: Void
	 */
	public void individualTicketPurchaseInitialPage(WebDriver driver)
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			 
			int rowsCount = xllib.getRowCount("PaidMemberTicketPurchase");
		    //For loop:Fetch data from excel input configuration file(excel sheet)
			for(int i=1;i<= rowsCount;i++)
			{
				loginURL = xllib.getExcelData("PaidMemberTicketPurchase", i, 0);
				loginEmail = xllib.getExcelData("PaidMemberTicketPurchase", i, 1);
				loginPassword = xllib.getExcelData("PaidMemberTicketPurchase", i, 2);
				city = xllib.getExcelData("PaidMemberTicketPurchase", i,3);
				eventName = xllib.getExcelData("PaidMemberTicketPurchase", i,4);
				seatingDate = xllib.getExcelData("PaidMemberTicketPurchase", i,5);
				seatingTime = xllib.getExcelData("PaidMemberTicketPurchase", i,6);
				purchaseQuantity = xllib.getExcelData("PaidMemberTicketPurchase", i,7);
				
				defaultCardStatus = xllib.getExcelData("PaidMemberTicketPurchase", i, 8);
				nameOnCard = xllib.getExcelData("PaidMemberTicketPurchase", i, 9);
				cardNumber = xllib.getExcelData("PaidMemberTicketPurchase", i,10);
			 	expiryMonth = xllib.getExcelData("PaidMemberTicketPurchase", i,11);
			 	expiryYear = xllib.getExcelData("PaidMemberTicketPurchase", i,12);
			 	cvv = xllib.getExcelData("PaidMemberTicketPurchase", i,13);
			 	billingStreet = xllib.getExcelData("PaidMemberTicketPurchase", i,14);
			 	billingCity = xllib.getExcelData("PaidMemberTicketPurchase", i,15);
			 	billingState = xllib.getExcelData("PaidMemberTicketPurchase", i,16);
			 	billingZipCode = xllib.getExcelData("PaidMemberTicketPurchase", i,17);
			 	
			 	dietaryPreferences = xllib.getExcelData("PaidMemberTicketPurchase", i, 18);
				dietaryRestrictions = xllib.getExcelData("PaidMemberTicketPurchase", i, 19);
				peanuts = xllib.getExcelData("PaidMemberTicketPurchase", i,20);
				shellfish = xllib.getExcelData("PaidMemberTicketPurchase", i, 21);
				treeNuts = xllib.getExcelData("PaidMemberTicketPurchase", i, 22);
				lactose = xllib.getExcelData("PaidMemberTicketPurchase", i, 23);
				gluten = xllib.getExcelData("PaidMemberTicketPurchase", i,24);
				noneOfThese = xllib.getExcelData("PaidMemberTicketPurchase", i,25);
				otherDietaryRestrictOrAllergies = xllib.getExcelData("PaidMemberTicketPurchase", i,26);
		 		OtherAllergiesTextArea = xllib.getExcelData("PaidMemberTicketPurchase", i,27);
				
			 	MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
			 	
			 	//Calling Login URL method
				loginLogout.loginURLActions(driver,loginURL);
				
				//Calling Member Login Method
				loginLogout.loginPageActions(driver,loginEmail,loginPassword);
						
				
				//Checking whether login credentials valid or not
				if(MemberLoginLogoutPage.loggedInStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 28, isTestPassed);
					//System.out.println("In MemberLogin: Result Value:"+ScriptHandler.result);
					//ScriptHandler.comment.add("Script PaidMemberTicketPurchase, Test Data "+i+": Success in Login page.");
					
					//output.outputReport("IndividualTicketPurchaseReport", 1, 3,TestConstants.RESULT_PASS);
					
					//memberCityActions(driver);
					
					MemberEventTicketPurchasePage ticket = new MemberEventTicketPurchasePage();
					
					//FerrariMPHandler obj = new FerrariMPHandler();
			        //Map<String,String> myMap = new HashMap<String,String>();
			        try
			        {
			        	//Calling Ticket Purchase Actions Method
				        //individualTicketPurchaseActions(driver);
			        	
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
				            dietary.clickingOnSaveButton(driver);
						}
						
			        	//Clicking on Continue Button
			        	ticket.clickingOnContinueButton(driver);
			        	
			        	if(!MemberEventTicketPurchasePage.orderTotalText.equalsIgnoreCase(TestConstants.DOLLAR_ZERO))
			        	{
			        		if(defaultCardStatus.equalsIgnoreCase(TestConstants.STATUS_FALSE))
				        	{
				        		System.out.println("before credit card method");
				        		
				        		AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
				        		
				        		//Calling use different card action method
				        		creditCard.useDifferentCardAction(driver);
				        		
				        		//Calling Credit Card Info Method
					        	creditCard.addNewCreditCardInfoActions(driver,nameOnCard, cardNumber, expiryMonth, expiryYear, cvv, billingStreet, billingCity, billingState, billingZipCode);
					        	System.out.println("after credit card method");
				        	}
			        	}
			        				        	
			        	//Clicking on Purchase Button
			        	ticket.memberEventTicketPurchaseFinal(driver);
			        	
			        	//Calling Log Out Method
						loginLogout.logoutActions(driver);
			            			        	
			        	System.out.println("ticketPurchaseStatus1122:"+ticketPurchaseStatus);
			        	
			        	//StripeEmailId = PaidMemberTicketPurchasePage.loginEmail;
			        	//System.out.println("StripeEmailId:"+StripeEmailId);
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
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 28, isTestPassed);
					//ScriptHandler.comment.add("Script PaidMemberTicketPurchase, Test Data "+i+": Failed in Login page.");	
			    	//output.outputReport("IndividualTicketPurchaseReport", 1, 3,TestConstants.RESULT_FAIL);
				}
				
				if(MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
				{
					isTestPassed="PASS";
					log.info("Event Ticket has been purchased successfully.");
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 29,TestConstants.TICKET_AVAILABLE);
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 30, isTestPassed);
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 31,MemberEventTicketPurchasePage.subTotalText);
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 32,MemberEventTicketPurchasePage.taxText);
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 33,MemberEventTicketPurchasePage.creditsText);
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 34,MemberEventTicketPurchasePage.orderTotalText);
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 35,MemberEventTicketPurchasePage.ticketQuantityText);
					//ScriptHandler.comment.add("Script PaidMember Ticket Purchase , Test Data "+i+": Success in Script PaidMember Ticket Purchase Page.");
				}
				else
				{
					isTestPassed="FAIL";
					log.info("Event Ticket has not been purchased.");
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 29,TestConstants.TICKET_SOLD_OUT);
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 30, isTestPassed);
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 31,isTestPassed);
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 32,isTestPassed);
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 33,isTestPassed);
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 34,isTestPassed);
					xllib.writeToExcel("PaidMemberTicketPurchase", i, 35,isTestPassed);
					//ScriptHandler.comment.add("Script PaidMember Ticket Purchase "+i+": Failed in Script PaidMember Ticket Purchase  Page.");
				}
				
				if(rowsCount !=1)
				{
	            	MemberLoginLogoutPage logout = new MemberLoginLogoutPage();
	            	
	            	//Calling Logout method
					logout.logoutActions(driver);
				}
			}//End of For Loop
		}//End of try block
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Test Execution Failed");
		}
	}//End of individualTicketPurchaseInitialPage method
	
	/**
	  * Test Case for fetching Member City
	  * Input: WebDriver
	  * Output: Void
	 */
	 public void memberCityActions(WebDriver driver) {
		 try
		 {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Actions actions = new Actions(driver);
					
			WebElement parent = driver.findElement(By.xpath("//li[@class='parent']")); // member drop down
			actions.moveToElement(parent).build().perform();

			driver.findElement(By.xpath("//a[contains(text(),'Account Overview')]")).click(); 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			memberCityName = driver.findElement(By.xpath("//div[contains(text(),'Home City:')]")).getText();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			System.out.println("memberCityName:"+memberCityName);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}

	/**
	  * Test Case for reading Excel Data and calling Member/Chef Event Ticket Purchase Method.
	  * Input: WebDriver
	  * Output: Void
	 */
	public void individualTicketPurchaseActions(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			//Calling Event Ticket Purchase Method
			//ticket.memberEventTicketPurchase(driver,city,eventName,seatingDate,seatingTime,purchaseQuantity);
			//Verify event ticket Purchased or not.
			//System.out.println("Sold Out Status:"+MemberEventTicketPurchasePage.soldOutStatus);
			if(MemberEventTicketPurchasePage.soldOutStatus||!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
			{
				log.info("Event Ticket has been Sold Out.");
				//report.excelReport("IndividualTicketPurchase", i, 8,TestConstants.TICKET_SOLD_OUT);
				//ScriptHandler.result=xllib.writeToExcel("IndividualTicketPurchase", i, 9, TestConstants.TICKET_SOLD_OUT);
				//output.outputReport("IndividualTicketPurchaseReport", 26, 2,TestConstants.BUTTON_STATE_SOLD_OUT);
				//output.outputReport("IndividualTicketPurchaseReport", 37, 3,TestConstants.RESULT_FAIL);
				//isTicketSoldOut="Event Ticket Sold Out";
				//isTicketSoldOut="PASS";
				//ScriptHandler.result=xllib.writeToExcel("MemberEventTicket", i, 5, isTicketSoldOut);
			}else if(!MemberEventTicketPurchasePage.soldOutStatus||MemberEventTicketPurchasePage.eventTicketPurchaseSuccess){
				//isTicketSoldOut="Event Ticket is Available";
				//isTicketSoldOut="FAIL";
				//ScriptHandler.result=xllib.writeToExcel("IndividualTicketPurchase", i, 9, isTicketSoldOut);
				log.info("Event Ticket is Available.");
				//report.excelReport("IndividualTicketPurchase", i, 8,TestConstants.TICKET_AVAILABLE);
				//ScriptHandler.result=xllib.writeToExcel("IndividualTicketPurchase", i, 9, TestConstants.TICKET_AVAILABLE);
				//output.outputReport("IndividualTicketPurchaseReport", 26, 2,TestConstants.BUTTON_STATE_PURCHASE);
				//output.outputReport("IndividualTicketPurchaseReport", 37, 3,TestConstants.RESULT_PASS);
			}
			
			//Verify event ticket Purchased or not.
			/*if(eventTicketPurchaseSuccess)
			{
				log.info("Event Ticket has been purchased successfully.");
				isTestPassed="PASS";
				ScriptHandler.result=xllib.writeToExcel("IndividualTicketPurchase", i, 6, isTestPassed);
				ScriptHandler.comment.add("Script Ticket Purchase(Individual), Test Data "+i+": Success in Ticket Purchase Page.");
			}else{
				isTestPassed="FAIL";
				ScriptHandler.result=xllib.writeToExcel("IndividualTicketPurchase", i, 6, isTestPassed);
				ScriptHandler.comment.add("Script Ticket Purchase(Individual), Test Data "+i+": Failed in Ticket Purchase Page.");
			}*/
			
			System.out.println("eventTicketPurchaseSuccess1:"+eventTicketPurchaseSuccess);
			if(MemberEventTicketPurchasePage.eventTicketPurchaseSuccess){
				
				log.info("Event Ticket has been purchased successfully.");
				isTestPassed="PASS";
				//ScriptHandler.result=xllib.writeToExcel("IndividualTicketPurchase", i, 10, isTestPassed);
				//ScriptHandler.comment.add("Script Ticket Purchase(Individual), Test Data "+i+": Success in Ticket Purchase Page.");
				//output.outputReport("IndividualTicketPurchaseReport", 37, 3,TestConstants.RESULT_PASS);
			}else if(!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess){
				
				isTestPassed="FAIL";
				//ScriptHandler.result=xllib.writeToExcel("IndividualTicketPurchase", i, 10, isTestPassed);
				//ScriptHandler.comment.add("Script Ticket Purchase(Individual), Test Data "+i+": Failed in Ticket Purchase Page.");
				//output.outputReport("IndividualTicketPurchaseReport", 37, 3,TestConstants.RESULT_FAIL);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			//log.info("Event Ticket has not purchased");
		}		
	}
}
