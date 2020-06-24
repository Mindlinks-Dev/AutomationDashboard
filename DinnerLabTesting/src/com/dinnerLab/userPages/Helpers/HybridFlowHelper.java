/**
 * @author: Basappa Hunsikatti
 * @Created Date :31/07/2015
 * @Updated Date :
 * @Comments This automation for Hybrid FLow.
 */
package com.dinnerLab.userPages.Helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;
import com.pack.Database;
import com.pack.ScriptHandler;

public class HybridFlowHelper
{
	private static String firstName;
	private static String lastName;
	private static String email;
	private static String password;
	public static String useOtherCity;
	public static String city;
	private static String otherCity;
	private static String doYouHavePromoCode;
	private static String promoCode;
	private static String signUpCode;
	private static String signUpURL;
	public static boolean signUpSuccess;
	private static String referLink;
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
	private static String Indianapolis;
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
	private static String Raleigh;
	private static String SanAntonio;
	private static String SanDiego;
	private static String SanFrancisco;
	private static String Seattle;
	private static String StLouis;*/
	private String dietaryPreferences;
	private String peanuts;
	private String shellfish;
	private String treeNuts;
	private String lactose;
	private String gluten;
	private String noneOfThese;
	public static String dietaryRestrictions;
	private String otherDietaryRestrictOrAllergies;
	private String OtherAllergiesTextArea;
	public static Boolean paidMemberSignUpStatus;
	public static Boolean upgradeAccountStatus;
	private String phoneNumberStatus;
	public String phoneNumber;
	public static String changeHomeCity;
	public static String newHomeCity;
	public static String applyPromoCode;
	public String accountPromoCode;
	private String nameOnCard;
	private String cardNumber;
	private String expiryMonth;
	private String expiryYear;
	private String cvv;
	private String billingStreet;
	private String billingCity;
	private String billingState;
	private String billingZipCode;
	public static String cityName;
	public static String eventName;
	public static String seatingDate;
	public static String seatingTime;
	public static String purchaseQuantity;
	private static Logger log = Logger.getLogger(HybridFlowHelper.class);
	public static int rowCount;
	public static int outputRowCount;
	int i=0;
	int j=0;
	Connection con = Database.getConnection();
    Statement stm=null;
    ResultSet rs=null;
    public static int flowint=0;
    public static String flowId =null;
   	Boolean paymentSuccess;
	public static Boolean dietarySuccess;
	public static Boolean ticketPurchaseStatus;
	public static Boolean dietaryStatus;
	public static Boolean dietaryRestrictionStatus;
	String isTestPassed="FAIL";
	public String stripeCustomerId = null;
	public String stripeTransactionId = null;
	//StripeHandler stripe = new StripeHandler();
	//FerrariMPHandler obj = new FerrariMPHandler();
    //Map<String,String> myMap = new HashMap<String,String>();
    //MemberLoginLogoutPage logout = new MemberLoginLogoutPage();
  	//DietryRestrictionPage dietary = new DietryRestrictionPage();
  	//FreeMemberSignUpPage subscribe = new FreeMemberSignUpPage();
  	//AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
  	//FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
  	//MemberEventTicketPurchasePage ticketPurchase = new MemberEventTicketPurchasePage();
  	
	public void hybridFlowInitialPage(WebDriver driver) throws InvalidFormatException, InterruptedException, SQLException
	{ 
		//System.out.println("Inside the hybridFlowInitialPage method ");
		con=Database.getConnection();
		stm=con.createStatement();
		//rs=stm.executeQuery("SELECT SelectedFlows from executiondetails WHERE scriptid="+ScriptHandler.ScriptID+" and execid="+ScriptHandler.execId);
 		rs=stm.executeQuery("SELECT FlowId from executiondetails WHERE scriptid="+ScriptHandler.ScriptID+" and execid="+ScriptHandler.executionId);	
 		ResultSet printflowid=rs;
 		//System.out.println("Query is:"+printflowid);
 		
 		while(rs.next())
 		{
 			flowId=rs.getString(1);
 			//System.out.println("Selected flows :"+flowId);
 		}
 		
 		try
 		{
 			 ExcelLib xllib = new ExcelLib();
 			 rowCount= xllib.getRowCount("HybridFlow");
 		//outputRowCount= xllib.getRowCount("HybridFLowOutput");
	 	//For loop:Fetch data from excel input configuration file(excel sheet)
	 	for (i = 1; i <= rowCount; i++) 
		{
	 		//for (j = 1; j <= outputRowCount; j++) 
			//{
	 			signUpURL = xllib.getExcelData("HybridFlow", i, 0);
	 			firstName = xllib.getExcelData("HybridFlow", i, 1);
	 			lastName = xllib.getExcelData("HybridFlow", i, 2);
	 			email = xllib.getExcelData("HybridFlow", i, 3);
	 			password = xllib.getExcelData("HybridFlow", i, 4);
	 			city = xllib.getExcelData("HybridFlow", i, 5);
	 			useOtherCity = xllib.getExcelData("HybridFlow", i, 6);
				otherCity = xllib.getExcelData("HybridFlow", i, 7);
				doYouHavePromoCode = xllib.getExcelData("HybridFlow", i, 8);
				promoCode = xllib.getExcelData("HybridFlow", i, 9);
				
				/*updateMyCitiesStatus = xllib.getExcelData("HybridFlow", i, 10);
				selectAll = xllib.getExcelData("HybridFlow", i, 11);
				Atlanta	= xllib.getExcelData("HybridFlow", i, 12);	
				Austin	= xllib.getExcelData("HybridFlow", i, 13);	
				Baltimore = xllib.getExcelData("HybridFlow", i, 14);
			 	BatonRouge = xllib.getExcelData("HybridFlow", i, 15);
			 	Birmingham = xllib.getExcelData("HybridFlow", i, 16);
			 	Boston = xllib.getExcelData("HybridFlow", i, 17);
				Charlotte = xllib.getExcelData("HybridFlow", i, 18);
				Chicago = xllib.getExcelData("HybridFlow", i, 19);
				Cleveland = xllib.getExcelData("HybridFlow", i, 20);
				Columbus = xllib.getExcelData("HybridFlow", i, 21);
				DC = xllib.getExcelData("HybridFlow", i, 22);
				Dallas = xllib.getExcelData("HybridFlow", i, 23);
				Denver	= xllib.getExcelData("HybridFlow", i, 24);	
				Houston	= xllib.getExcelData("HybridFlow", i, 25);	
				//Indianapolis = xllib.getExcelData("HybridFlow", i, 26);
				KansasCity = xllib.getExcelData("HybridFlow", i, 26);
				LosAngeles = xllib.getExcelData("HybridFlow", i, 27);
				Miami = xllib.getExcelData("HybridFlow", i, 28);
				Milwaukee = xllib.getExcelData("HybridFlow", i, 29);
				Minneapolis = xllib.getExcelData("HybridFlow", i, 30);
				Nashville = xllib.getExcelData("HybridFlow", i, 31);
				NewOrleans = xllib.getExcelData("HybridFlow", i, 32);
				NewYork = xllib.getExcelData("HybridFlow", i, 33);
				Philadelphia = xllib.getExcelData("HybridFlow", i, 34);	
				Phoenix	= xllib.getExcelData("HybridFlow", i, 35);	
				Pittsburgh = xllib.getExcelData("HybridFlow", i, 36);
				Portland = xllib.getExcelData("HybridFlow", i, 37);
				//Raleigh = xllib.getExcelData("HybridFlow", i, 39);
				SanAntonio = xllib.getExcelData("HybridFlow", i, 38);
				SanDiego = xllib.getExcelData("HybridFlow", i, 39);
				SanFrancisco = xllib.getExcelData("HybridFlow", i, 40);
				Seattle = xllib.getExcelData("HybridFlow", i, 41);
				StLouis = xllib.getExcelData("HybridFlow", i, 42);*/
				
				dietaryPreferences = xllib.getExcelData("HybridFlow", i, 10);
				dietaryRestrictions = xllib.getExcelData("HybridFlow", i, 11);
				peanuts = xllib.getExcelData("HybridFlow", i,12);
				shellfish = xllib.getExcelData("HybridFlow", i, 13);
				treeNuts = xllib.getExcelData("HybridFlow", i, 14);
				lactose = xllib.getExcelData("HybridFlow", i, 15);
				gluten = xllib.getExcelData("HybridFlow", i,16);
				noneOfThese = xllib.getExcelData("HybridFlow", i,17);
		 		otherDietaryRestrictOrAllergies = xllib.getExcelData("HybridFlow", i,18);
		 		OtherAllergiesTextArea = xllib.getExcelData("HybridFlow", i,19);
		 		phoneNumberStatus = xllib.getExcelData("HybridFlow", i, 20);
				phoneNumber = xllib.getExcelData("HybridFlow", i, 21);
				
				changeHomeCity = xllib.getExcelData("HybridFlow", i,22);
				newHomeCity = xllib.getExcelData("HybridFlow", i,23);
				applyPromoCode = xllib.getExcelData("HybridFlow", i,24);
				accountPromoCode = xllib.getExcelData("HybridFlow", i,25);
				nameOnCard = xllib.getExcelData("HybridFlow", i, 26);
				cardNumber = xllib.getExcelData("HybridFlow", i,27);
			 	expiryMonth = xllib.getExcelData("HybridFlow", i,28);
			 	expiryYear = xllib.getExcelData("HybridFlow", i,29);
			 	cvv = xllib.getExcelData("HybridFlow", i,30);
			 	billingStreet = xllib.getExcelData("HybridFlow", i,31);
			 	billingCity = xllib.getExcelData("HybridFlow", i,32);
			 	billingState = xllib.getExcelData("HybridFlow", i,33);
			 	billingZipCode = xllib.getExcelData("HybridFlow", i,34);
			 	
			 	cityName = xllib.getExcelData("HybridFlow", i,35);
				eventName = xllib.getExcelData("HybridFlow", i,36);
				seatingDate = xllib.getExcelData("HybridFlow", i,37);
				seatingTime = xllib.getExcelData("HybridFlow", i,38);
				purchaseQuantity = xllib.getExcelData("HybridFlow", i,39);
				
	     if(flowId.equalsIgnoreCase(TestConstants.DEFAULT_FLOWS))
	     {
	    	 System.out.println("DEFAULT_FLOWS flowId:"+flowId);
	    	try
	    	{   
	    		FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
	    		
	    		//Calling subscriber Signup URL Method.
			 	freeMember.subscriberSignUpURL(driver,signUpURL);
		 		
		 		//Selecting User Type as Member
			 	//freeMember.selectUserTypeMember(driver);
		 		
		 		//Calling Sign Up Method.
			 	freeMember.signUpPageActions(driver,doYouHavePromoCode,promoCode,firstName,lastName,email,password,city,useOtherCity,otherCity);
				
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
				}
			 	
			 	//Clicking on Next Control
			 	freeMember.clickingOnNextControl(driver);
			 	
			 	if(phoneNumberStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
					
					//Adding Phone Number
					creditCard.addingPhoneNumberActions(driver, phoneNumber);
					
					//Clicking on Submit Button
					freeMember.clickingOnSubmitButton(driver);
				}
			 	
			 	//Clicking on Next Control
			 	freeMember.clickingOnNextControl(driver);
			 	
			 	//Fetching Refer/Invite Link
			 	freeMember.referLinkGetText(driver);
				
			 	//Clicking on View Events Button
	            freeMember.clickingOnViewEventsButton(driver);
	          
	            //Verifying Sign Up
	            freeMember.verifySignUp(driver);
	          
				//Condition to Select other cities to receive email for events.
			 	//freeMember.selectOtherCitiesToReceiveMailForEvents(driver,useOtherCity,city,otherCity,updateMyCitiesStatus,selectAll,Atlanta,Austin,Baltimore,BatonRouge,Birmingham,Boston,Charlotte,Chicago,Cleveland,Columbus,DC,Dallas, Denver,Houston,KansasCity,LosAngeles,Miami,Milwaukee,Minneapolis,Nashville,NewOrleans,NewYork,Philadelphia,Phoenix,Pittsburgh,Portland,SanAntonio,SanDiego,SanFrancisco,Seattle,StLouis);
				
	            System.out.println("signUpSuccess001:"+FreeMemberSignUpPage.signUpSuccess);
			     
	            //checking the status of Sign up page and updating the result in excel	
				if(FreeMemberSignUpPage.signUpSuccess)
				{				
					log.info("Dinner Lab Subscriber Account is created successfully.");
					isTestPassed="PASS";
					xllib.writeToExcel("HybridFlow", i, 40, isTestPassed);
					
					if(useOtherCity.equalsIgnoreCase(TestConstants.STATUS_FALSE))
					{
						AddNewCreditCardInfoPage credit = new AddNewCreditCardInfoPage();
						
						//Clicking on Upgrade Account Button
						credit.clickingOnUpgradeAccountButton(driver);
					 	  
					 	//Adding Phone Number into Payment Page
						//credit.addingPhoneNumberActions(driver,phoneNumber);
					 	
						if(changeHomeCity.equalsIgnoreCase(TestConstants.STATUS_TRUE))
						{
							//Calling Change Home City method
			        		credit.changeHomeCity(driver,newHomeCity);
						}
						
						if(applyPromoCode.equalsIgnoreCase(TestConstants.STATUS_TRUE))
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
						
					}else if(useOtherCity.equalsIgnoreCase(TestConstants.STATUS_TRUE))
					{
						AddNewCreditCardInfoPage.accountUpgradedStatus = false;
					}
				}
				else
				{
					isTestPassed = "FAIL";
					xllib.writeToExcel("HybridFlow", i, 40, isTestPassed);
				}
	    				    			 
				if(AddNewCreditCardInfoPage.accountUpgradedStatus)
				{
					log.info("You are Paid Member of Dinner Lab.Thank you.");
					isTestPassed="PASS";
					xllib.writeToExcel("HybridFlow", i, 41, isTestPassed);
					//ScriptHandler.comment.add("Script Hybrid Flow, Test Data "+i+": Success in Payment page.");
				}
				else
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("HybridFlow", i, 41, isTestPassed);
					//ScriptHandler.comment.add("Script Hybrid Flow"+i+": Failed in Payment page.");
				}
				MemberEventTicketPurchasePage ticketPurchase = new MemberEventTicketPurchasePage();
				
				//Calling Ticket Purchase Actions Method
				ticketPurchase.memberEventTicketPurchase(driver,cityName,eventName,seatingDate,seatingTime,purchaseQuantity);
		        	
		        //Clicking on Continue Button
		        ticketPurchase.clickingOnContinueButton(driver);
		        	
		        //Clicking on Purchase Button
		        ticketPurchaseStatus = ticketPurchase.memberEventTicketPurchaseFinal(driver);
		        	
		        System.out.println("ticketPurchaseStatus1122:"+ticketPurchaseStatus);
		        	
		        if(rowCount !=1)
				{
		        	MemberLoginLogoutPage logout = new MemberLoginLogoutPage();
		        		
		        	//Calling Logout method
					logout.logoutActions(driver);
				}
					  
				System.out.println("ticketPurchaseStatus:"+ticketPurchaseStatus); 
				System.out.println("soldOutStatus:"+MemberEventTicketPurchasePage.soldOutStatus); 
				System.out.println("eventTicketPurchaseSuccess:"+MemberEventTicketPurchasePage.eventTicketPurchaseSuccess); 
				  
				if(ticketPurchaseStatus)
				{
					isTestPassed="PASS";
					log.info("Event Ticket is Available.");
					xllib.writeToExcel("HybridFlow", i, 42,TestConstants.TICKET_AVAILABLE);
					xllib.writeToExcel("HybridFlow", i, 43, isTestPassed);
					xllib.writeToExcel("HybridFlow", i, 44,MemberEventTicketPurchasePage.subTotalText);
					xllib.writeToExcel("HybridFlow", i, 45,MemberEventTicketPurchasePage.taxText);
					xllib.writeToExcel("HybridFlow", i, 46,MemberEventTicketPurchasePage.creditsText);
					xllib.writeToExcel("HybridFlow", i, 47,MemberEventTicketPurchasePage.orderTotalText);
					xllib.writeToExcel("HybridFlow", i, 48,MemberEventTicketPurchasePage.quantityAfterPayment);
				}
				else
				{
					isTestPassed="FAIL";
					log.info("Event Ticket has been Sold Out.");
					xllib.writeToExcel("HybridFlow", i, 42,TestConstants.TICKET_SOLD_OUT);
					xllib.writeToExcel("HybridFlow", i, 43, isTestPassed);
					xllib.writeToExcel("HybridFlow", i, 44,isTestPassed);
					xllib.writeToExcel("HybridFlow", i, 45,isTestPassed);
					xllib.writeToExcel("HybridFlow", i, 46,isTestPassed);
					xllib.writeToExcel("HybridFlow", i, 47,isTestPassed);
					xllib.writeToExcel("HybridFlow", i, 48,isTestPassed);
				}
				   // String StripeEmailId = HybridFlowHelper.email;
					//System.out.println("StripeEmailId1:"+StripeEmailId);
					//Calling MP Handler method
					//myMap = obj.getDetails(StripeEmailId);
					 				   
					//Calling Complete Ticket Purchase Stripe details method
					//stripeData.completeTicketPurchaseStripeDetails(driver);
					 	  
				    			       
	    		  // }else if(!FreeMemberSignUpPage1.signUpSuccess)
	   				//{
	   					//report.outputReport("CompleteTicketPurchaseReport", 1, 3,TestConstants.RESULT_FAIL);
	   				//}
				}catch(Exception e)
	    	 	{
	    	 		//System.out.println("Flow not complete");
	    	 		e.printStackTrace();
	    	 		// report.outputReport("CompleteTicketPurchaseReport", 1, 3,TestConstants.RESULT_FAIL);
	    	 	}
	     	}//end of if(flow_all)
	     	else
	     	{
	     		String[] parts = flowId.split(",");
	     		for(int j=0;j<parts.length;j++)
			{
				String flow=parts[j];
				flowint=Integer.parseInt(flow);
			}	
				if(flowint == TestConstants.MEMBER_REGISTRATION)
				{
					System.out.println("MEMBER_REGISTRATION flowId:"+flowId);
					try
					{   
						FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
						
						//Calling subscriber Signup URL Method.
					 	freeMember.subscriberSignUpURL(driver,signUpURL);
				 		
				 		//Selecting User Type as Member
					 	//freeMember.selectUserTypeMember(driver);
				 		
				 		//Calling Sign Up Method.
					 	freeMember.signUpPageActions(driver,doYouHavePromoCode,promoCode,firstName,lastName,email,password,city,useOtherCity,otherCity);
						
					 	//Verify Signup and Dietary
					 	verifySignUpAndDietary(driver);
					 	
						//Clicking on Next Control
					 	/*freeMember.clickingOnNextControl(driver);
						
					 	//Condition to Select other cities to receive email for events.
					 	//freeMember.selectOtherCitiesToReceiveMailForEvents(driver,useOtherCity,city,otherCity,updateMyCitiesStatus,selectAll,Atlanta,Austin,Baltimore,BatonRouge,Birmingham,Boston,Charlotte,Chicago,Cleveland,Columbus,DC,Dallas, Denver,Houston,KansasCity,LosAngeles,Miami,Milwaukee,Minneapolis,Nashville,NewOrleans,NewYork,Philadelphia,Phoenix,Pittsburgh,Portland,SanAntonio,SanDiego,SanFrancisco,Seattle,StLouis);
						
			            //Clicking on Next Control
					 	freeMember.clickingOnNextControl(driver);
						
					 	//Fetching Refer/Invite Link
					 	//freeMember.referLinkGetText(driver);
						
					 	//Verify View Events Button
			            freeMember.verifyViewEventsButton(driver);
			            
					 	//Clicking on View Events Button
			            freeMember.clickingOnViewEventsButton(driver);
					 	
					 	//Verifying Sign Up
			            //freeMember.verifySignUp(driver);
			            
			            if(rowCount !=1)
						{
			            	MemberLoginLogoutPage logout = new MemberLoginLogoutPage();
			            	
			            	//Calling Logout method
							logout.logoutActions(driver);
						}*/
			            
			            //if(FreeMemberSignUpPage.viewEventsStatus)
			            if(dietaryStatus)
						{
							log.info("You are Member of Dinner Lab.Thank you.");
							isTestPassed="PASS";
							xllib.writeToExcel("HybridFlow", i, 40, isTestPassed);
							//ScriptHandler.comment.add("Script Hybrid Flow, Test Data "+i+": Success in Payment page.");
						}
						else
						{
							isTestPassed="FAIL";
							xllib.writeToExcel("HybridFlow", i, 40, isTestPassed);
							//ScriptHandler.comment.add("Script Hybrid Flow"+i+": Failed in Payment page.");
						}
			            
						//xllib.createNewRow("HybridFLowOutput1", i);
						
						//Calling writing data to excel method
						//writingDataToExcel(driver);
					}
					catch(NullPointerException e)
					{
						e.printStackTrace();
						//report.outputReport("CompleteTicketPurchaseReport", 1, 3,TestConstants.RESULT_FAIL);
					}
					catch(NoSuchElementException e)
					{
						e.printStackTrace();
						//report.outputReport("CompleteTicketPurchaseReport", 1, 3,TestConstants.RESULT_FAIL);
					}
				}//End of Member Registration else if condition
				else if(flowint == TestConstants.DIETARY_RESTRICTION)
				{
					System.out.println("DIETARY_RESTRICTION flowId:"+flowId);
					try
					{   
						FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
						
						//Calling subscriber Signup URL Method.
					 	freeMember.subscriberSignUpURL(driver,signUpURL);
				 		
				 		//Selecting User Type as Member
					 	//freeMember.selectUserTypeMember(driver);
				 		
				 		//Calling Sign Up Method.
					 	freeMember.signUpPageActions(driver,doYouHavePromoCode,promoCode,firstName,lastName,email,password,city,useOtherCity,otherCity);
						
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
						}
					 	
					 	//Clicking on Next Control
					 	freeMember.clickingOnNextControl(driver);
					 	
					 	//Verify Signup and Dietary
					 	verifySignUpAndDietaryRestriction(driver);
					 	
					 	/*if(phoneNumberStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
						{
							AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
							
							//Adding Phone Number
							creditCard.addingPhoneNumberActions(driver, phoneNumber);
							
							//Clicking on Submit Button
							freeMember.clickingOnSubmitButton(driver);
						}
					 	//Clicking on Next Control
					 	freeMember.clickingOnNextControl(driver);
					 	
					 	//Fetching Refer/Invite Link
					 	freeMember.referLinkGetText(driver);
						
						//Condition to Select other cities to receive email for events.
					 	//freeMember.selectOtherCitiesToReceiveMailForEvents(driver,useOtherCity,city,otherCity,updateMyCitiesStatus,selectAll,Atlanta,Austin,Baltimore,BatonRouge,Birmingham,Boston,Charlotte,Chicago,Cleveland,Columbus,DC,Dallas, Denver,Houston,KansasCity,LosAngeles,Miami,Milwaukee,Minneapolis,Nashville,NewOrleans,NewYork,Philadelphia,Phoenix,Pittsburgh,Portland,SanAntonio,SanDiego,SanFrancisco,Seattle,StLouis);
						
			           	//Clicking on View Events Button
			            freeMember.clickingOnViewEventsButton(driver);
			          
			            //Verifying Sign Up
			            freeMember.verifySignUp(driver);
			            
					 	System.out.println("signUpSuccess001:"+FreeMemberSignUpPage.signUpSuccess);
			        	*/		        	        	
						//checking the status of Sign up page and updating the result in excel	
						//if(FreeMemberSignUpPage.signUpSuccess)
						if(dietaryRestrictionStatus)
						{				
							log.info("Dinner Lab Subscriber Account is created successfully.");
							isTestPassed="PASS";
							xllib.writeToExcel("HybridFlow", i, 40, isTestPassed);
							//ScriptHandler.comment.add("Script Hybrid Flow, Test Data "+i+": Success in Hybrid Flow Sign up page.");
						}
						else
						{
							isTestPassed = "FAIL";
							xllib.writeToExcel("HybridFlow", i, 40, isTestPassed);
							//ScriptHandler.comment.add("Script Hybrid Flow, Test Data "+i+": Failed in Hybrid Flow Sign up Page.");	
						}
						
						/*if(rowCount !=1)
						{
							MemberLoginLogoutPage logout = new MemberLoginLogoutPage();
							
							//Calling Logout method
							logout.logoutActions(driver);
						}
												
				        /*if(FreeMemberSignUpPage1.signUpSuccess)
						{
							//report.outputReport("CompleteTicketPurchaseReport", 1, 3,TestConstants.RESULT_PASS);
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							//Calling Payment method
							//paidMember.paidMemberActions(driver);
							driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
							
							//Calling Dietary Restrictions method
							dietarySuccess = dietary.dietaryRestrictionsOrAllergiesActions(driver);
							
							 System.out.println("dietarySuccess2:"+dietarySuccess);
					            
							 if(dietarySuccess)
							 {
								try
								{
									String StripeEmailId = HybridFlowHelper.email;
									System.out.println("StripeEmailId1:"+StripeEmailId);
									
									myMap = obj.getDetails(StripeEmailId);
									
									//Calling Stripe Member details method
									stripeData.completeTicketPurchaseStripeDetails(driver);
								}
								catch(Exception ex)
								{
									ex.printStackTrace();
								}
							}
							driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
						}
						else if(!FreeMemberSignUpPage1.signUpSuccess)
						{
							//report.outputReport("CompleteTicketPurchaseReport", 1, 3,TestConstants.RESULT_FAIL);
						}*/
					}
					catch(Exception e)
					{
						e.printStackTrace();
						//report.outputReport("CompleteTicketPurchaseReport", 1, 3,TestConstants.RESULT_FAIL);
					}
			
				}//End of Dietary Restriction else if condition
				
				else if(flowint == TestConstants.UPGRADE_ACCOUNT)
			     {
					System.out.println("UPGRADE_ACCOUNT flowId:"+flowId);
			    	try
			    	{   
			    		FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
			    		
			    		//Calling subscriber Signup URL Method.
					 	freeMember.subscriberSignUpURL(driver,signUpURL);
				 		
				 		//Selecting User Type as Member
					 	//freeMember.selectUserTypeMember(driver);
				 		
				 		//Calling Sign Up Method.
					 	freeMember.signUpPageActions(driver,doYouHavePromoCode,promoCode,firstName,lastName,email,password,city,useOtherCity,otherCity);
						
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
						}
					 	
					 	//Clicking on Next Control
					 	freeMember.clickingOnNextControl(driver);
					 	
					 	if(phoneNumberStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
						{
							AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
							
							//Adding Phone Number
							creditCard.addingPhoneNumberActions(driver, phoneNumber);
							
							//Clicking on Submit Button
							freeMember.clickingOnSubmitButton(driver);
						}
					 	
					 	//Clicking on Next Control
					 	freeMember.clickingOnNextControl(driver);
					 	
					 	//Fetching Refer/Invite Link
					 	freeMember.referLinkGetText(driver);
						
					 	//Clicking on View Events Button
			            freeMember.clickingOnViewEventsButton(driver);
			          
			            //Verifying Sign Up
			            freeMember.verifySignUp(driver);
			          
						//Condition to Select other cities to receive email for events.
					 	//freeMember.selectOtherCitiesToReceiveMailForEvents(driver,useOtherCity,city,otherCity,updateMyCitiesStatus,selectAll,Atlanta,Austin,Baltimore,BatonRouge,Birmingham,Boston,Charlotte,Chicago,Cleveland,Columbus,DC,Dallas, Denver,Houston,KansasCity,LosAngeles,Miami,Milwaukee,Minneapolis,Nashville,NewOrleans,NewYork,Philadelphia,Phoenix,Pittsburgh,Portland,SanAntonio,SanDiego,SanFrancisco,Seattle,StLouis);
						
			            System.out.println("signUpSuccess001:"+FreeMemberSignUpPage.signUpSuccess);
					     
			            //checking the status of Sign up page and updating the result in excel	
						if(FreeMemberSignUpPage.signUpSuccess)
						{				
							log.info("Dinner Lab Subscriber Account is created successfully.");
							isTestPassed="PASS";
							xllib.writeToExcel("HybridFlow", i, 40, isTestPassed);
							
							if(useOtherCity.equalsIgnoreCase(TestConstants.STATUS_FALSE))
							{
								AddNewCreditCardInfoPage credit = new AddNewCreditCardInfoPage();
								
								//Clicking on Upgrade Account Button
								credit.clickingOnUpgradeAccountButton(driver);
							 	  
							 	//Adding Phone Number into Payment Page
								//credit.addingPhoneNumberActions(driver,phoneNumber);
							 	
								if(changeHomeCity.equalsIgnoreCase(TestConstants.STATUS_TRUE))
								{
									//Calling Change Home City method
					        		credit.changeHomeCity(driver,newHomeCity);
								}
								
								if(applyPromoCode.equalsIgnoreCase(TestConstants.STATUS_TRUE))
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
								
							}else if(useOtherCity.equalsIgnoreCase(TestConstants.STATUS_TRUE))
							{
								AddNewCreditCardInfoPage.accountUpgradedStatus = false;
							}
						}
						else
						{
							isTestPassed = "FAIL";
							xllib.writeToExcel("HybridFlow", i, 40, isTestPassed);
						}
						
						if(rowCount !=1)
						{
							MemberLoginLogoutPage logout = new MemberLoginLogoutPage();
							
							//Calling Logout method
							logout.logoutActions(driver);
						}		    			 
						if(AddNewCreditCardInfoPage.accountUpgradedStatus)
						{
							log.info("You are Paid Member of Dinner Lab.Thank you.");
							isTestPassed="PASS";
							xllib.writeToExcel("HybridFlow", i, 41, isTestPassed);
							//ScriptHandler.comment.add("Script Hybrid Flow, Test Data "+i+": Success in Payment page.");
						}
						else
						{
							isTestPassed="FAIL";
							xllib.writeToExcel("HybridFlow", i, 41, isTestPassed);
							//ScriptHandler.comment.add("Script Hybrid Flow"+i+": Failed in Payment page.");
						}
					}catch(Exception e)
			    	{
			    		//System.out.println("Flow not complete");
			    		e.printStackTrace();
			    	 	// report.outputReport("CompleteTicketPurchaseReport", 1, 3,TestConstants.RESULT_FAIL);
			    	 }
			    }//End of Upgrade Account else if condition
				else if(flowint == TestConstants.MEMBER_TICKET_PURCHASE)
				{ 
					System.out.println("MEMBER_TICKET_PURCHASE flowId:"+flowId);
					try
					{
						FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
			    		
			    		//Calling subscriber Signup URL Method.
					 	freeMember.subscriberSignUpURL(driver,signUpURL);
				 		
				 		//Selecting User Type as Member
					 	//freeMember.selectUserTypeMember(driver);
				 		
				 		//Calling Sign Up Method.
					 	freeMember.signUpPageActions(driver,doYouHavePromoCode,promoCode,firstName,lastName,email,password,city,useOtherCity,otherCity);
						
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
						}
					 	
					 	//Clicking on Next Control
					 	freeMember.clickingOnNextControl(driver);
					 	
					 	if(phoneNumberStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
						{
							AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
							
							//Adding Phone Number
							creditCard.addingPhoneNumberActions(driver, phoneNumber);
							
							//Clicking on Submit Button
							freeMember.clickingOnSubmitButton(driver);
						}
					 	
					 	//Clicking on Next Control
					 	freeMember.clickingOnNextControl(driver);
					 	
					 	//Fetching Refer/Invite Link
					 	freeMember.referLinkGetText(driver);
						
					 	//Clicking on View Events Button
			            freeMember.clickingOnViewEventsButton(driver);
			          
			            //Verifying Sign Up
			            freeMember.verifySignUp(driver);
			          
						//Condition to Select other cities to receive email for events.
					 	//freeMember.selectOtherCitiesToReceiveMailForEvents(driver,useOtherCity,city,otherCity,updateMyCitiesStatus,selectAll,Atlanta,Austin,Baltimore,BatonRouge,Birmingham,Boston,Charlotte,Chicago,Cleveland,Columbus,DC,Dallas, Denver,Houston,KansasCity,LosAngeles,Miami,Milwaukee,Minneapolis,Nashville,NewOrleans,NewYork,Philadelphia,Phoenix,Pittsburgh,Portland,SanAntonio,SanDiego,SanFrancisco,Seattle,StLouis);
						
			            System.out.println("signUpSuccess001:"+FreeMemberSignUpPage.signUpSuccess);
					     
			            //checking the status of Sign up page and updating the result in excel	
						if(FreeMemberSignUpPage.signUpSuccess)
						{				
							log.info("Dinner Lab Subscriber Account is created successfully.");
							isTestPassed="PASS";
							xllib.writeToExcel("HybridFlow", i, 40, isTestPassed);
							
							if(useOtherCity.equalsIgnoreCase(TestConstants.STATUS_FALSE))
							{
								AddNewCreditCardInfoPage credit = new AddNewCreditCardInfoPage();
								
								//Clicking on Upgrade Account Button
								credit.clickingOnUpgradeAccountButton(driver);
							 	  
							 	//Adding Phone Number into Payment Page
								//credit.addingPhoneNumberActions(driver,phoneNumber);
							 	
								if(changeHomeCity.equalsIgnoreCase(TestConstants.STATUS_TRUE))
								{
									//Calling Change Home City method
					        		credit.changeHomeCity(driver,newHomeCity);
								}
								
								if(applyPromoCode.equalsIgnoreCase(TestConstants.STATUS_TRUE))
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
								
							}else if(useOtherCity.equalsIgnoreCase(TestConstants.STATUS_TRUE))
							{
								AddNewCreditCardInfoPage.accountUpgradedStatus = false;
							}
						}
						else
						{
							isTestPassed = "FAIL";
							xllib.writeToExcel("HybridFlow", i, 40, isTestPassed);
						}
			    				    			 
						if(AddNewCreditCardInfoPage.accountUpgradedStatus)
						{
							log.info("You are Paid Member of Dinner Lab.Thank you.");
							isTestPassed="PASS";
							xllib.writeToExcel("HybridFlow", i, 41, isTestPassed);
							//ScriptHandler.comment.add("Script Hybrid Flow, Test Data "+i+": Success in Payment page.");
						}
						else
						{
							isTestPassed="FAIL";
							xllib.writeToExcel("HybridFlow", i, 41, isTestPassed);
							//ScriptHandler.comment.add("Script Hybrid Flow"+i+": Failed in Payment page.");
						}
						MemberEventTicketPurchasePage ticketPurchase = new MemberEventTicketPurchasePage();
						
						//Calling Ticket Purchase Actions Method
						ticketPurchase.memberEventTicketPurchase(driver,cityName,eventName,seatingDate,seatingTime,purchaseQuantity);
				        	
				        //Clicking on Continue Button
				        ticketPurchase.clickingOnContinueButton(driver);
				        	
				        //Clicking on Purchase Button
				        ticketPurchaseStatus = ticketPurchase.memberEventTicketPurchaseFinal(driver);
				        	
				        System.out.println("ticketPurchaseStatus1122:"+ticketPurchaseStatus);
				        	
				        if(rowCount !=1)
						{
				        	MemberLoginLogoutPage logout = new MemberLoginLogoutPage();
				        		
				        	//Calling Logout method
							logout.logoutActions(driver);
						}
							  
						System.out.println("ticketPurchaseStatus:"+ticketPurchaseStatus); 
						System.out.println("soldOutStatus:"+MemberEventTicketPurchasePage.soldOutStatus); 
						System.out.println("eventTicketPurchaseSuccess:"+MemberEventTicketPurchasePage.eventTicketPurchaseSuccess); 
						  
						if(ticketPurchaseStatus)
						{
							isTestPassed="PASS";
							log.info("Event Ticket is Available.");
							xllib.writeToExcel("HybridFlow", i, 42,TestConstants.TICKET_AVAILABLE);
							xllib.writeToExcel("HybridFlow", i, 43, isTestPassed);
							xllib.writeToExcel("HybridFlow", i, 44,MemberEventTicketPurchasePage.subTotalText);
							xllib.writeToExcel("HybridFlow", i, 45,MemberEventTicketPurchasePage.taxText);
							xllib.writeToExcel("HybridFlow", i, 46,MemberEventTicketPurchasePage.creditsText);
							xllib.writeToExcel("HybridFlow", i, 47,MemberEventTicketPurchasePage.orderTotalText);
							xllib.writeToExcel("HybridFlow", i, 48,MemberEventTicketPurchasePage.quantityAfterPayment);
						}
						else
						{
							isTestPassed="FAIL";
							log.info("Event Ticket has been Sold Out.");
							xllib.writeToExcel("HybridFlow", i, 42,TestConstants.TICKET_SOLD_OUT);
							xllib.writeToExcel("HybridFlow", i, 43, isTestPassed);
							xllib.writeToExcel("HybridFlow", i, 44,isTestPassed);
							xllib.writeToExcel("HybridFlow", i, 45,isTestPassed);
							xllib.writeToExcel("HybridFlow", i, 46,isTestPassed);
							xllib.writeToExcel("HybridFlow", i, 47,isTestPassed);
							xllib.writeToExcel("HybridFlow", i, 48,isTestPassed);
						}
						
						// String StripeEmailId = HybridFlowHelper.email;
						//System.out.println("StripeEmailId2:"+StripeEmailId);
						//Calling MP Handler method
						//myMap = obj.getDetails(StripeEmailId);
						 				   
						//Calling Complete Ticket Purchase Stripe details method
						//stripeData.completeTicketPurchaseStripeDetails(driver);
						
						//System.out.println("executing flow: "+flowint);
						//signUpMember.memberSignUpPageActions(driver,signUpURL,firstName,lastName,email,password,city);
						
						/*if(FreeMemberSignUpPage1.signUpSuccess)
						{
							//report.outputReport("CompleteTicketPurchaseReport", 1, 3,TestConstants.RESULT_PASS);
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							//paidMember.paidMemberActions(driver);
							
							driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
							dietarySuccess = dietary.dietaryRestrictionsOrAllergiesActions(driver);
							System.out.println("dietarySuccess3:"+dietarySuccess);
					      
												
							driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
							MemberEventTicketPurchasePage ticketpurch=new MemberEventTicketPurchasePage();
							ticketpurch.memberEventTicketPurchaseActions(driver);
							Thread.sleep(5000);
							
							String StripeEmailId = HybridFlowHelper.email;
						    System.out.println("StripeEmailId2:"+StripeEmailId);
						    myMap = obj.getDetails(StripeEmailId);
						        	
						    //Calling Stripe Ticket Purchase details method  	
						    stripeData.completeTicketPurchaseStripeDetails(driver);
						}
						else if(!FreeMemberSignUpPage1.signUpSuccess)
						{
							//report.outputReport("CompleteTicketPurchaseReport", 1, 3,TestConstants.RESULT_FAIL);
						}*/
					}
					catch(Exception e)
					{
						e.printStackTrace();
						//report.outputReport("CompleteTicketPurchaseReport", 1, 3,TestConstants.RESULT_FAIL);
					}
				}
			//}//End of Member Ticket Purchase else if condition
  		}//end of for loop(flow_int)
	   
		//}//end of inside for loop  
		}//end of excel for loop	
	 	
 		}
 		catch(Exception e)
 		{
 			e.printStackTrace();
 		}
 		con.close();
	}//end of hybridFlowInitialPage
	
	/**
	 * Test Case for verifying Sign Up and Dietary
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	 */
	public void verifySignUpAndDietary(WebDriver driver)
	{
		try
		{
			//Clicking on Next    
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			dietaryStatus =  driver.findElement(By.xpath("//h3[contains(text(),'Have any dietary preferences?')]")).isDisplayed();
			System.out.println("dietaryStatus:"+dietaryStatus);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Test Case for verifying Sign Up and Dietary Restriction
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	 */
	public void verifySignUpAndDietaryRestriction(WebDriver driver)
	{
		try
		{
			//Clicking on Next    
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			dietaryRestrictionStatus =  driver.findElement(By.xpath("//h2[contains(text(),'Please enter your phone number')]")).isDisplayed();
			System.out.println("dietaryRestrictionStatus:"+dietaryRestrictionStatus);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}//HybridFlowHelper

