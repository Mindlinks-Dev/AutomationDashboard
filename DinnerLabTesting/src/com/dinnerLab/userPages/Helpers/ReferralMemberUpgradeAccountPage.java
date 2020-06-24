/**
 * @author: Basappa Hunsikatti
 * @Created Date :24/09/2015
 * @Updated Date :
 * @Comments:This automation class will serve the upgrade account for Referral Member. 
 * When you used code on PAYMENT PAGE when upgrading your account, you have $20 in credit added on your account automatically.
*/

package com.dinnerLab.userPages.Helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;

public class ReferralMemberUpgradeAccountPage 
{
	 public static String loginEmail;
	 private String loginPassword;
	 private String loginURL;
	 boolean present;
	 String isTestPassed="FAIL";
	 public static int rowCount;
	 public static String changeHomeCity;
	 public static String newHomeCity;
	 public static String applyPromoCode;
	 public String accountPromoCode;
	 public String dontSaveCard;
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
	 public static boolean referralUpgradeAccountStatus;
	 String isTicketSoldOut="FAIL";
	 int i=0;
	 public static String StripeEmailId;
	 private static Logger log = Logger.getLogger(ReferralMemberUpgradeAccountPage.class);
	 //MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	
	 //AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
	 
	 
	 /**
		 * Test Case for Reading the excel data and login into the Login Page
		 * portal on successful login to the application.
		 * Input: WebDriver
		 * Output: Void
	 */
	public void referralMemberUpgradeAccountInitialPage(WebDriver driver)
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			 
			int rowsCount = xllib.getRowCount("ReferralMemberUpgradeAccount");
		    //For loop:Fetch data from excel input configuration file(excel sheet)
			for(int i=1;i<= rowsCount;i++)
			{
				loginURL = xllib.getExcelData("ReferralMemberUpgradeAccount", i, 0);
				loginEmail = xllib.getExcelData("ReferralMemberUpgradeAccount", i, 1);
				loginPassword = xllib.getExcelData("ReferralMemberUpgradeAccount", i, 2);
				
				changeHomeCity = xllib.getExcelData("ReferralMemberUpgradeAccount", i,3);
				newHomeCity = xllib.getExcelData("ReferralMemberUpgradeAccount", i,4);
				//applyPromoCode = xllib.getExcelData("ReferralMemberUpgradeAccount", i,5);
				//accountPromoCode = xllib.getExcelData("ReferralMemberUpgradeAccount", i,6);
				dontSaveCard = xllib.getExcelData("ReferralMemberUpgradeAccount", i,5);
				nameOnCard = xllib.getExcelData("ReferralMemberUpgradeAccount", i, 6);
				cardNumber = xllib.getExcelData("ReferralMemberUpgradeAccount", i,7);
			 	expiryMonth = xllib.getExcelData("ReferralMemberUpgradeAccount", i,8);
			 	expiryYear = xllib.getExcelData("ReferralMemberUpgradeAccount", i,9);
			 	cvv = xllib.getExcelData("ReferralMemberUpgradeAccount", i,10);
			 	billingStreet = xllib.getExcelData("ReferralMemberUpgradeAccount", i,11);
			 	billingCity = xllib.getExcelData("ReferralMemberUpgradeAccount", i,12);
			 	billingState = xllib.getExcelData("ReferralMemberUpgradeAccount", i,13);
			 	billingZipCode = xllib.getExcelData("ReferralMemberUpgradeAccount", i,14);
				
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
						xllib.writeToExcel("ReferralMemberUpgradeAccount", i, 15, isTestPassed);
						//System.out.println("In MemberLogin: Result Value:"+ScriptHandler.result);
						//ScriptHandler.comment.add("Script Referral Member Upgrad eAccount, Test Data "+i+": Success in Login page.");
						
						AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
						
						//Clicking on Upgrade Account Button
			        	creditCard.clickingOnUpgradeAccountButton(driver);
					 	  
					 	//Adding Phone Number into Payment Page
			        	//creditCard.addingPhoneNumberActions(driver,phoneNumber);
			        	
			        	if(changeHomeCity.equalsIgnoreCase(TestConstants.STATUS_TRUE))
						{
							//Calling Change Home City method
		        			creditCard.changeHomeCity(driver,newHomeCity);
						}
						
						/*if(applyPromoCode.equalsIgnoreCase(TestConstants.STATUS_TRUE))
						{
							//Calling applying Promo Code method
							creditCard.applyingPromoCode(driver,accountPromoCode);
						}*/
						
			        	if(dontSaveCard.equalsIgnoreCase(TestConstants.STATUS_TRUE))
						{
				 			//Calling don't Save Card information method
			        		creditCard.dontSaveCardInformation(driver);
						}
				 		
					 	//Calling Add New Credit Card Info Method
			        	creditCard.addNewCreditCardInfoActions(driver,nameOnCard,cardNumber,expiryMonth,expiryYear,cvv,billingStreet,billingCity,billingState,billingZipCode);
					 	  
					 	//clicking on Become A Member Button
			        	creditCard.clickingOnBecomeAMemberButton(driver);
					 	
					 	//verifying Payment success
			        	creditCard.verifyUpgradeAccount(driver);
			        	
			        	if(rowsCount !=1)
						{
				            //Calling Logout method
							loginLogout.logoutActions(driver);
						}
			        	
			        	System.out.println("referralUpgradeAccountStatus:"+referralUpgradeAccountStatus);
			        	
			        	//StripeEmailId = ReferralMemberUpgradeAccountPage.loginEmail;
			        	//System.out.println("StripeEmailId111:"+StripeEmailId);
			        	
			        	//checking the status of Sign up page and updating the result in excel	
						if(AddNewCreditCardInfoPage.upgradeAccountStatus)
						{				
							//myMap = obj.getDetails(StripeEmailId);
    			        	
				        	//Calling write Stripe details to Paid Member Excel Report file method.
				        	//stripeData.individualTicketPurchaseStripeDetails(driver);
							
							log.info("Dinner Lab Referral Account is created successfully through Invite Link.");
							log.info("You are Free Member of Dinner Lab.Thank you.");
							isTestPassed="PASS";
							xllib.writeToExcel("ReferralMemberUpgradeAccount", i, 16, isTestPassed);
							//ScriptHandler.comment.add("Script Referral Member Upgrade Account, Test Data "+i+": Success in Referral Member Upgrade Account page.");
						}
						else
						{
							isTestPassed = "FAIL";
							xllib.writeToExcel("ReferralMemberUpgradeAccount", i, 16, isTestPassed);
							//ScriptHandler.comment.add("Script Referral Member Upgrade Account, Test Data "+i+": Failed in Referral Member Upgrade Account Page.");	
							//update table here
						}
			        }
			        catch(Exception ex)
			        {
			        	ex.printStackTrace();
			        	isTestPassed = "FAIL";
						xllib.writeToExcel("ReferralMemberUpgradeAccount", i, 15, isTestPassed);
						//ScriptHandler.comment.add("Script Referral Member Upgrade Account, Test Data"+i+": Failed in Login page.");	
			        }

			    }else if(!MemberLoginLogoutPage.loggedInStatus){
					//output.outputReport("IndividualTicketPurchaseReport", 1, 3,TestConstants.RESULT_FAIL);
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
