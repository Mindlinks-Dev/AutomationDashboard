/**
 * @author: Basappa Hunsikatti
 * @Created Date :24/09/2015
 * @Updated Date :09/02/2016
 * @Comments:This automation class will serve the Select Member Sign Up and Add Payment details.
 */

package com.dinnerLab.userPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;
import com.pack.ScriptHandler;

public class PaidMemberSignUpPage 
{
	//public String signUpUrl = TestConstants.SignUp_Url;
	public String firstName;
	public String lastName;
	public String email;
	public String password;
	public String useOtherCity;
	public String city;
	public String otherCity;
	public String doYouHavePromoCode;
	public String promoCode;
	public String skipPayment;
	public String signUpCode;
	public String signUpURL;
	public static boolean signUpSuccess;
	/*public String updateMyCitiesStatus;
	public String referLink;
	public String selectAll;
	public String Atlanta;
	public String Austin;
	public String Baltimore;
	public String BatonRouge;
	public String Birmingham;
	public String Boston;
	public String Charlotte;
	public String Chicago;
	public String Cleveland;
	public String Columbus;
	public String DC;
	public String Dallas;
	public String Denver;
	public String Houston;
	public String Indianapolis;
	public String KansasCity;
	public String LosAngeles;
	public String Miami;
	public String Milwaukee;
	public String Minneapolis;
	public String Nashville;
	public String NewOrleans;
	public String NewYork;
	public String Philadelphia;
	public String Phoenix;
	public String Pittsburgh;
	public String Portland;
	public String Raleigh;
	public String SanAntonio;
	public String SanDiego;
	public String SanFrancisco;
	public String Seattle;
	public String StLouis;*/
	/*public String peanuts;
	public String shellfish;
	public String treeNuts;
	public String lactose;
	public String gluten;
	public String noneOfThese;
	public String dietaryPreferences;
	public String dietaryRestrictions;
	public String otherDietaryRestrictOrAllergies;
	public String OtherAllergiesTextArea;*/
	public static Boolean paidMemberSignUpStatus;
	public static Boolean upgradeAccountStatus;
	private String phoneNumberStatus;
	public String phoneNumber;
	public static String changeHomeCity;
	public static String newHomeCity;
	public static String applyPromoCode;
	public String accountPromoCode;
	public String dontSaveCard;
	private String nameOnCard;
	private String cardNumber;
	private String expiryMonth;
	private String expiryYear;
	private String cvv;
	private String billingStreet;
	private String billingCity;
	private String billingState;
	private String billingZipCode;
	String isTestPassed="FAIL";
	int i=0;
	private static Logger log = Logger.getLogger(PaidMemberSignUpPage.class);
	//AddNewCreditCardInfoPage credit = new AddNewCreditCardInfoPage();
	//MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
	//DietryRestrictionPage dietary = new DietryRestrictionPage();
	//FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
			
	/**
	 * Test Case for Reading the excel data for signing up as a Free Member
	 * portal on successful registration to the application.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	*/
	public void paidMemberSignUpInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			int rowCount = xllib.getRowCount("PaidMemberSignUp");
			//For loop:Fetch data from excel input configuration file(excel sheet)
			for(i=1;i<= rowCount;i++)
			{
			 	//Sign up Page
				signUpURL = xllib.getExcelData("PaidMemberSignUp", i, 0);
			 	firstName = xllib.getExcelData("PaidMemberSignUp", i, 1);
			 	lastName = xllib.getExcelData("PaidMemberSignUp", i, 2);
				email = xllib.getExcelData("PaidMemberSignUp", i, 3);
				password = xllib.getExcelData("PaidMemberSignUp", i, 4);
				city = xllib.getExcelData("PaidMemberSignUp", i, 5);
				useOtherCity = xllib.getExcelData("PaidMemberSignUp", i, 6);
				otherCity = xllib.getExcelData("PaidMemberSignUp", i, 7);
				doYouHavePromoCode = xllib.getExcelData("PaidMemberSignUp", i, 8);
				promoCode = xllib.getExcelData("PaidMemberSignUp", i, 9);
				
				//Payment Page
				skipPayment = xllib.getExcelData("PaidMemberSignUp", i, 10);
				changeHomeCity = xllib.getExcelData("PaidMemberSignUp", i,11);
				newHomeCity = xllib.getExcelData("PaidMemberSignUp", i,12);
				dontSaveCard = xllib.getExcelData("PaidMemberSignUp", i,13);
				nameOnCard = xllib.getExcelData("PaidMemberSignUp", i, 14);
				cardNumber = xllib.getExcelData("PaidMemberSignUp", i,15);
			 	expiryMonth = xllib.getExcelData("PaidMemberSignUp", i,16);
			 	expiryYear = xllib.getExcelData("PaidMemberSignUp", i,17);
			 	cvv = xllib.getExcelData("PaidMemberSignUp", i,18);
			 	billingStreet = xllib.getExcelData("PaidMemberSignUp", i,19);
			 	billingCity = xllib.getExcelData("PaidMemberSignUp", i,20);
			 	billingState = xllib.getExcelData("PaidMemberSignUp", i,21);
			 	billingZipCode = xllib.getExcelData("PaidMemberSignUp", i,22);
			 	
			 		 	
				/*updateMyCitiesStatus = xllib.getExcelData("PaidMemberSignUp", i, 10);
				selectAll = xllib.getExcelData("PaidMemberSignUp", i, 11);
				Atlanta	= xllib.getExcelData("PaidMemberSignUp", i, 12);	
				Austin	= xllib.getExcelData("PaidMemberSignUp", i, 13);	
				Baltimore = xllib.getExcelData("PaidMemberSignUp", i, 14);
			 	BatonRouge = xllib.getExcelData("PaidMemberSignUp", i, 15);
			 	Birmingham = xllib.getExcelData("PaidMemberSignUp", i, 16);
			 	Boston = xllib.getExcelData("PaidMemberSignUp", i, 17);
				Charlotte = xllib.getExcelData("PaidMemberSignUp", i, 18);
				Chicago = xllib.getExcelData("PaidMemberSignUp", i, 19);
				Cleveland = xllib.getExcelData("PaidMemberSignUp", i, 20);
				Columbus = xllib.getExcelData("PaidMemberSignUp", i, 21);
				DC = xllib.getExcelData("PaidMemberSignUp", i, 22);
				Dallas = xllib.getExcelData("PaidMemberSignUp", i, 23);
				Denver	= xllib.getExcelData("PaidMemberSignUp", i, 24);	
				Houston	= xllib.getExcelData("PaidMemberSignUp", i, 25);	
				//Indianapolis = xllib.getExcelData("PaidMemberSignUp", i, 26);
				KansasCity = xllib.getExcelData("PaidMemberSignUp", i, 26);
				LosAngeles = xllib.getExcelData("PaidMemberSignUp", i, 27);
				Miami = xllib.getExcelData("PaidMemberSignUp", i, 28);
				Milwaukee = xllib.getExcelData("PaidMemberSignUp", i, 29);
				Minneapolis = xllib.getExcelData("PaidMemberSignUp", i, 30);
				Nashville = xllib.getExcelData("PaidMemberSignUp", i, 31);
				NewOrleans = xllib.getExcelData("PaidMemberSignUp", i, 32);
				NewYork = xllib.getExcelData("PaidMemberSignUp", i, 33);
				Philadelphia = xllib.getExcelData("PaidMemberSignUp", i, 34);	
				Phoenix	= xllib.getExcelData("PaidMemberSignUp", i, 35);	
				Pittsburgh = xllib.getExcelData("PaidMemberSignUp", i, 36);
				Portland = xllib.getExcelData("PaidMemberSignUp", i, 37);
				//Raleigh = xllib.getExcelData("PaidMemberSignUp", i, 39);
				SanAntonio = xllib.getExcelData("PaidMemberSignUp", i, 38);
				SanDiego = xllib.getExcelData("PaidMemberSignUp", i, 39);
				SanFrancisco = xllib.getExcelData("PaidMemberSignUp", i, 40);
				Seattle = xllib.getExcelData("PaidMemberSignUp", i, 41);
				StLouis = xllib.getExcelData("PaidMemberSignUp", i, 42);*/
				
				/*dietaryPreferences = xllib.getExcelData("PaidMemberSignUp", i, 10);
				dietaryRestrictions = xllib.getExcelData("PaidMemberSignUp", i, 11);
				peanuts = xllib.getExcelData("PaidMemberSignUp", i,12);
				shellfish = xllib.getExcelData("PaidMemberSignUp", i, 13);
				treeNuts = xllib.getExcelData("PaidMemberSignUp", i, 14);
				lactose = xllib.getExcelData("PaidMemberSignUp", i, 15);
				gluten = xllib.getExcelData("PaidMemberSignUp", i,16);
				noneOfThese = xllib.getExcelData("PaidMemberSignUp", i,17);
		 		otherDietaryRestrictOrAllergies = xllib.getExcelData("PaidMemberSignUp", i,18);
		 		OtherAllergiesTextArea = xllib.getExcelData("PaidMemberSignUp", i,19);
		 		phoneNumberStatus = xllib.getExcelData("PaidMemberSignUp", i, 20);
				phoneNumber = xllib.getExcelData("PaidMemberSignUp", i, 21);*/
				
		 		FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
			 	
			 	//Calling subscriber Signup URL Method.
			 	freeMember.subscriberSignUpURL(driver,signUpURL);
		 		
			 	//Calling click On Select Member Sign Up Button method
			 	clickOnSelectMemberSignUpButton(driver);
			 	
		 		//Selecting User Type as Member
			 	//freeMember.selectUserTypeMember(driver);
		 		
		 		//Calling Sign Up Method.
			 	freeMember.signUpPageActions(driver,doYouHavePromoCode,promoCode,firstName,lastName,email,password,city,useOtherCity,otherCity);
				
			 	if(skipPayment.equalsIgnoreCase(TestConstants.STATUS_FALSE) && useOtherCity.equalsIgnoreCase(TestConstants.STATUS_FALSE))
			 	{
			 		AddNewCreditCardInfoPage credit = new AddNewCreditCardInfoPage();
			 		if(changeHomeCity.equalsIgnoreCase(TestConstants.STATUS_TRUE))
					{
						//Calling Change Home City method
		        		credit.changeHomeCity(driver,newHomeCity);
					}
			 		
			 		if(dontSaveCard.equalsIgnoreCase(TestConstants.STATUS_TRUE))
					{
			 			//Calling don't Save Card information method
				 		credit.dontSaveCardInformation(driver);
					}
			 		
			 		//Calling Add New Credit Card Info Method
					credit.addNewCreditCardInfoActions(driver,nameOnCard,cardNumber,expiryMonth,expiryYear,cvv,billingStreet,billingCity,billingState,billingZipCode);
				 	  
				 	//clicking on Become A Member Button
					credit.clickingOnBecomeAMemberButton(driver);
								         
				 }else if(skipPayment.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				 {
					 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
					 driver.findElement(By.xpath("//a[contains(text(),'Skip payment')]")).click();
				 }
			 	
			 	 //Verifying Sign Up
		         freeMember.verifySignUp(driver);
		         
			 	 //Clicking on View Events Button
				 freeMember.clickingOnViewEventsButton(driver);
				 
			 	 if(FreeMemberSignUpPage.signUpSuccess)
				 {
					log.info("You are Select Member of Dinner Lab.Thank you.");
					isTestPassed="PASS";
					xllib.writeToExcel("PaidMemberSignUp", i, 23, isTestPassed);
				 }else if(!FreeMemberSignUpPage.signUpSuccess)
				 {
					log.info("Unsuccessfull Select Member Signup.");
					isTestPassed = "FAIL";
					xllib.writeToExcel("PaidMemberSignUp", i, 23, isTestPassed);
				 }
				
			 	/*if(dietaryPreferences.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
			 		DietryRestrictionPage dietary = new DietryRestrictionPage();
			 		
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
			 	//freeMember.clickingOnNextControl(driver);
			 	
			 	/*if(phoneNumberStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
					
					//Adding Phone Number
					creditCard.addingPhoneNumberActions(driver, phoneNumber);
					
					//Clicking on Submit Button
					freeMember.clickingOnSubmitButton(driver);
				}*/
				
			 	//Clicking on Next Control
			 	//freeMember.clickingOnNextControl(driver);
			 	
			 	//Fetching Refer/Invite Link
			 	//freeMember.referLinkGetText(driver);
			 	
			 	//Condition to Select other cities to receive email for events.
			 	//freeMember.selectOtherCitiesToReceiveMailForEvents(driver,useOtherCity,city,otherCity,updateMyCitiesStatus,selectAll,Atlanta,Austin,Baltimore,BatonRouge,Birmingham,Boston,Charlotte,Chicago,Cleveland,Columbus,DC,Dallas, Denver,Houston,KansasCity,LosAngeles,Miami,Milwaukee,Minneapolis,Nashville,NewOrleans,NewYork,Philadelphia,Phoenix,Pittsburgh,Portland,SanAntonio,SanDiego,SanFrancisco,Seattle,StLouis);
				
	           /* System.out.println("signUpSuccess:"+FreeMemberSignUpPage.signUpSuccess);
	            //checking the status of Sign up page and updating the result in excel	
				//if(FreeMemberSignUpPage.signUpSuccess)
				//{
					log.info("Dinner Lab Subscriber Account is created successfully.");
					log.info("You are Paid Member of Dinner Lab.Thank you.");
					isTestPassed="PASS";
					//xllib.writeToExcel("PaidMemberSignUp", i, 35, isTestPassed);
					//ScriptHandler.comment.add("Script Paid Membership, Test Data "+i+": Success in Paid Member Sign up page.");
					
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
					
		            if(rowCount !=1)
					{
		            	MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
		            	
		            	//Calling Logout method
						loginLogout.logoutActions(driver);
					}
		        //}
				else if(!FreeMemberSignUpPage.signUpSuccess)
				{
					isTestPassed = "FAIL";
					xllib.writeToExcel("PaidMemberSignUp", i, 35, isTestPassed);
					//ScriptHandler.comment.add("Script Paid Membership, Test Data "+i+": Failed in Paid Member Sign up Page.");	
					//update table here
				}
				
				if(AddNewCreditCardInfoPage.accountUpgradedStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("PaidMemberSignUp", i, 36, isTestPassed);
					//ScriptHandler.comment.add("Script Paid Membership, Test Data "+i+": Success in Payment page.");
				}
				else
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("PaidMemberSignUp", i, 36, isTestPassed);
					//ScriptHandler.comment.add("Script Paid Membership"+i+": Failed in Payment page.");
				}*/
				
			}//End of for loop
		}
		catch(Exception e)
		{
			log.info("Paid Member Account creation unsucessfull");
		}
	}
	 /**
	 * Test Case for clicking on Select Member Sign Up Button
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void clickOnSelectMemberSignUpButton(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			 driver.findElement(By.xpath("//a[@ href='/signup/select']")).click();
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 }catch(Exception e)
		 {
			e.printStackTrace();
		 }
	 }
}//End of Class
		

