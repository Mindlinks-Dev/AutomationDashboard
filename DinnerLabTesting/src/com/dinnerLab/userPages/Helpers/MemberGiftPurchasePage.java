/**
 * @author: Basappa Hunsikatti
 * @Created Date :26/10/2015
 * @Updated Date :
 * @Comments This automation class will purchase Member Gift.
 */
package com.dinnerLab.userPages.Helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;
import com.pack.ScriptHandler;

public class MemberGiftPurchasePage 
{
	 private String dinnerLabURL;
	 private String giftMembershipCity;
	 private String quantity;
	 private String DinnerCreditsToMembership;
	 private String cardName;
	 private String cardNumber;
	 private String expiryMonth;
	 private String expiryYear;
	 private String cvv;
	 private String billingStreet;
	 private String billingCity;
	 private String billingState;
	 private String billingZipCode;
	 private String emailAddress;
	 private String loginPassword;
	 public static boolean loginStatus;
	 String isTestPassed="FAIL";
	 public static boolean giftPurchaseSuccess;
	 private String defaultCard;
	 boolean present;
	 public int rowCount;
	 private static Logger log = Logger.getLogger(MemberGiftPurchasePage.class);
	 //MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	 
	 //AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
	 //GiftPurchasePage gift = new GiftPurchasePage();
	
	 /**
	 * Test Case for purchasing Member Gift.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void memberGiftPurchasePageActions(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	 {
		 try
		 {
			 ExcelLib xllib = new ExcelLib();
			 
			 rowCount= xllib.getRowCount("MemberGiftPurchase");
		 	for (int i = 1; i <= rowCount; i++) 
			{
		 		dinnerLabURL = xllib.getExcelData("MemberGiftPurchase", i, 0);
		 		giftMembershipCity = xllib.getExcelData("MemberGiftPurchase", i,1);
		 		quantity = xllib.getExcelData("MemberGiftPurchase", i,2);
		 		DinnerCreditsToMembership = xllib.getExcelData("MemberGiftPurchase", i,3);
		 		emailAddress = xllib.getExcelData("MemberGiftPurchase", i,4);
		 		loginPassword = xllib.getExcelData("MemberGiftPurchase", i, 5);
		 		defaultCard = xllib.getExcelData("MemberGiftPurchase", i,6);
		 		cardName = xllib.getExcelData("MemberGiftPurchase", i,7);
		 		cardNumber = xllib.getExcelData("MemberGiftPurchase", i,8);
		 		expiryMonth = xllib.getExcelData("MemberGiftPurchase", i,9);
		 		expiryYear = xllib.getExcelData("MemberGiftPurchase", i,10);
		 		cvv = xllib.getExcelData("MemberGiftPurchase", i,11);
		 		billingStreet = xllib.getExcelData("MemberGiftPurchase", i,12);
		 		billingCity = xllib.getExcelData("MemberGiftPurchase", i,13);
		 		billingState = xllib.getExcelData("MemberGiftPurchase", i,14);
		 		billingZipCode = xllib.getExcelData("MemberGiftPurchase", i,15);
		 		
		 		MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
		 		
		 		//Calling Login URL method
				loginLogout.loginURLActions(driver,dinnerLabURL);
				
		 		//Calling Login method
		 		loginStatus = loginLogout.loginPageActions(driver,emailAddress, loginPassword);
		 		System.out.println("loginStatus:"+loginStatus);
		 		
		 		GiftPurchasePage gift = new GiftPurchasePage();
		 		
		 		if(loginStatus)
		 		{
		 			isTestPassed="PASS";
					xllib.writeToExcel("MemberGiftPurchase", i, 16, isTestPassed);
									
		 			//Clicking on Gifts Link
			 		//gift.GiftPurchaseActions(driver,giftMembershipCity,quantity,DinnerCreditsToMembership);
			 		
			 		//Calling Order Details Data method
			 		gift.gettingOrderDetailsData(driver);
					
					//Calling Email Address method
			 		gift.emailTextField(driver);
				
			 		AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
			 		
					if(defaultCard.equalsIgnoreCase(TestConstants.STATUS_TRUE))
					{
						//clicking on Purchase Button
						creditCard.clickingOnBecomeAMemberButton(driver);
					}
					else if(defaultCard.equalsIgnoreCase(TestConstants.STATUS_FALSE))
					{
						//Calling use different card method
						creditCard.useDifferentCardAction(driver);
						
						//Calling Add New Credit Card Info Method
						creditCard.addNewCreditCardInfoActions(driver,cardName,cardNumber,expiryMonth,expiryYear,cvv,billingStreet,billingCity,billingState,billingZipCode);
				 	  
						//clicking on Purchase Button
						creditCard.clickingOnBecomeAMemberButton(driver);
					}
					
					//Verifying Gift Purchase
					gift.verifyGiftPurchaseActions(driver);
					
					//Calling Logout Method
					loginLogout.logoutActions(driver);
		 		}
		 		else
				{
					isTestPassed = "FAIL";
					xllib.writeToExcel("MemberGiftPurchase", i, 16, isTestPassed);
				}
		 		
		 		System.out.println("gift status:"+GiftPurchasePage.giftPurchaseSuccess);
		 		if(GiftPurchasePage.giftPurchaseSuccess)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("MemberGiftPurchase", i, 17, isTestPassed);
					xllib.writeToExcel("MemberGiftPurchase", i, 18, GiftPurchasePage.giftCity);
					xllib.writeToExcel("MemberGiftPurchase", i, 19, GiftPurchasePage.membershipFee);
					xllib.writeToExcel("MemberGiftPurchase", i, 20, GiftPurchasePage.numberOfMembership);
					xllib.writeToExcel("MemberGiftPurchase", i, 21, GiftPurchasePage.orderCredits);
					xllib.writeToExcel("MemberGiftPurchase", i, 22, GiftPurchasePage.orderTotal);
				}
				else
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("MemberGiftPurchase", i, 17, isTestPassed);
				}
			}
		}
		catch(Exception e)
		{
			log.info("Gift has not purchased.");	
		}		
	}
}