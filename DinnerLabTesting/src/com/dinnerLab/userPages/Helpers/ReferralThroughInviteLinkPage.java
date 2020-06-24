/**
 * @author: Basappa Hunsikatti
 * @Created Date :24/09/2015
 * @Updated Date :
 * @Comments:This automation class will serve the Subscriber Sign Up  using a friend's referral link. 
 * Once you upgrade to a member account, you'll both get $20 credit in your account..
 */

package com.dinnerLab.userPages.Helpers;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;
import com.pack.ScriptHandler;

public class ReferralThroughInviteLinkPage 
{
	//private static String signUpUrl = TestConstants.SignUp_Url;
	private static String firstName;
	private static String lastName;
	private static String email;
	private static String password;
	private static String useOtherCity;
	private static String city;
	private static String otherCity;
	private static String doYouHavePromoCode;
	private static String promoCode;
	private static String inviteLinkURL;
	public static boolean signUpSuccess;
	/*private static String updateMyCitiesStatus;
	private static String referLink;
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
	public boolean homeCity;
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
	private String phoneNumber;
	private String phoneNumberStatus;*/
	String isTestPassed="FAIL";
	int i=0;
	private static Logger log = Logger.getLogger(ReferralThroughInviteLinkPage.class);
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
	public void subscriberSignUpInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			int rowCount = xllib.getRowCount("ReferralThroughInviteLink");
			//For loop:Fetch data from excel input configuration file(excel sheet)
			for(i=1;i<= rowCount;i++)
			{
			 	inviteLinkURL = xllib.getExcelData("ReferralThroughInviteLink", i, 0);
			 	firstName = xllib.getExcelData("ReferralThroughInviteLink", i, 1);
			 	lastName = xllib.getExcelData("ReferralThroughInviteLink", i, 2);
				email = xllib.getExcelData("ReferralThroughInviteLink", i, 3);
				password = xllib.getExcelData("ReferralThroughInviteLink", i, 4);
				city = xllib.getExcelData("ReferralThroughInviteLink", i, 5);
				useOtherCity = xllib.getExcelData("ReferralThroughInviteLink", i, 6);
				otherCity = xllib.getExcelData("ReferralThroughInviteLink", i, 7);
				doYouHavePromoCode = xllib.getExcelData("ReferralThroughInviteLink", i, 8);
				promoCode = xllib.getExcelData("ReferralThroughInviteLink", i, 9);
				
				/*updateMyCitiesStatus = xllib.getExcelData("ReferralThroughInviteLink", i, 10);
				selectAll = xllib.getExcelData("ReferralThroughInviteLink", i, 11);
				Atlanta	= xllib.getExcelData("ReferralThroughInviteLink", i, 12);	
				Austin	= xllib.getExcelData("ReferralThroughInviteLink", i, 13);	
				Baltimore = xllib.getExcelData("ReferralThroughInviteLink", i, 14);
			 	BatonRouge = xllib.getExcelData("ReferralThroughInviteLink", i, 15);
			 	Birmingham = xllib.getExcelData("ReferralThroughInviteLink", i, 16);
			 	Boston = xllib.getExcelData("ReferralThroughInviteLink", i, 17);
				Charlotte = xllib.getExcelData("ReferralThroughInviteLink", i, 18);
				Chicago = xllib.getExcelData("ReferralThroughInviteLink", i, 19);
				Cleveland = xllib.getExcelData("ReferralThroughInviteLink", i, 20);
				Columbus = xllib.getExcelData("ReferralThroughInviteLink", i, 21);
				DC = xllib.getExcelData("ReferralThroughInviteLink", i, 22);
				Dallas = xllib.getExcelData("ReferralThroughInviteLink", i, 23);
				Denver	= xllib.getExcelData("ReferralThroughInviteLink", i, 24);	
				Houston	= xllib.getExcelData("ReferralThroughInviteLink", i, 25);	
				//Indianapolis = xllib.getExcelData("ReferralThroughInviteLink", i, 26);
				KansasCity = xllib.getExcelData("ReferralThroughInviteLink", i, 26);
				LosAngeles = xllib.getExcelData("ReferralThroughInviteLink", i, 27);
				Miami = xllib.getExcelData("ReferralThroughInviteLink", i, 28);
				Milwaukee = xllib.getExcelData("ReferralThroughInviteLink", i, 29);
				Minneapolis = xllib.getExcelData("ReferralThroughInviteLink", i,30);
				Nashville = xllib.getExcelData("ReferralThroughInviteLink", i, 31);
				NewOrleans = xllib.getExcelData("ReferralThroughInviteLink", i, 32);
				NewYork = xllib.getExcelData("ReferralThroughInviteLink", i, 33);
				Philadelphia = xllib.getExcelData("ReferralThroughInviteLink", i, 34);	
				Phoenix	= xllib.getExcelData("ReferralThroughInviteLink", i, 35);	
				Pittsburgh = xllib.getExcelData("ReferralThroughInviteLink", i, 36);
				Portland = xllib.getExcelData("ReferralThroughInviteLink", i, 37);
				//Raleigh = xllib.getExcelData("ReferralThroughInviteLink", i, 39);
				SanAntonio = xllib.getExcelData("ReferralThroughInviteLink", i, 38);
				SanDiego = xllib.getExcelData("ReferralThroughInviteLink", i, 39);
				SanFrancisco = xllib.getExcelData("ReferralThroughInviteLink", i, 40);
				Seattle = xllib.getExcelData("ReferralThroughInviteLink", i, 41);
				StLouis = xllib.getExcelData("ReferralThroughInviteLink", i, 42);
				
				dietaryPreferences = xllib.getExcelData("ReferralThroughInviteLink", i, 10);
				dietaryRestrictions = xllib.getExcelData("ReferralThroughInviteLink", i, 11);
				peanuts = xllib.getExcelData("ReferralThroughInviteLink", i,12);
				shellfish = xllib.getExcelData("ReferralThroughInviteLink", i, 13);
				treeNuts = xllib.getExcelData("ReferralThroughInviteLink", i, 14);
				lactose = xllib.getExcelData("ReferralThroughInviteLink", i, 15);
				gluten = xllib.getExcelData("ReferralThroughInviteLink", i,16);
				noneOfThese = xllib.getExcelData("ReferralThroughInviteLink", i,17);
		 		otherDietaryRestrictOrAllergies = xllib.getExcelData("ReferralThroughInviteLink", i,18);
		 		OtherAllergiesTextArea = xllib.getExcelData("ReferralThroughInviteLink", i,19);
				
		 		phoneNumberStatus = xllib.getExcelData("ReferralThroughInviteLink", i, 20);
				phoneNumber = xllib.getExcelData("ReferralThroughInviteLink", i, 21);*/
				
		 		//Calling Invite Link URL Method.
		 		inviteLinkURLActions(driver,inviteLinkURL);
		 		
		 		FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
		 		
		 		//Calling Sign Up Method.
		 		freeMember.signUpPageActions(driver,doYouHavePromoCode,promoCode,firstName,lastName,email,password,city,useOtherCity,otherCity);
				
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
				}*/
		 		
		 		//Clicking on Next Control
		 		//freeMember.clickingOnNextControl(driver);
		 		
		 		//Fetching Refer/Invite Link
		 		//freeMember.referLinkGetText(driver);
				
		 		//Verifying Sign Up
	            freeMember.verifySignUp(driver);
	            
		 		//Clicking on View Events Button
	            freeMember.clickingOnViewEventsButton(driver);
	            
				//Condition to Select other cities to receive email for events.
		 		//freeMember.selectOtherCitiesToReceiveMailForEvents(driver,useOtherCity,city,otherCity,updateMyCitiesStatus,selectAll,Atlanta,Austin,Baltimore,BatonRouge,Birmingham,Boston,Charlotte,Chicago,Cleveland,Columbus,DC,Dallas, Denver,Houston,KansasCity,LosAngeles,Miami,Milwaukee,Minneapolis,Nashville,NewOrleans,NewYork,Philadelphia,Phoenix,Pittsburgh,Portland,SanAntonio,SanDiego,SanFrancisco,Seattle,StLouis);
				
	            if(rowCount !=1)
				{
	            	MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
	            	
	            	//Calling Logout method
					loginLogout.logoutActions(driver);
				}
	        	
				//System.out.println("signUpSuccess123:"+FreeMemberSignUpPage.signUpSuccess);
				
				//checking the status of Sign up page and updating the result in excel	
				if(FreeMemberSignUpPage.signUpSuccess)
				{				
					log.info("You are Free Member of Dinner Lab.Thank you.");
					isTestPassed="PASS";
					xllib.writeToExcel("ReferralThroughInviteLink", i, 10, isTestPassed);
					//ScriptHandler.comment.add("Script Referral Signup through Invite Link, Test Data "+i+": Success in Referral Signup through Invite Link page.");
				}
				else
				{
					isTestPassed = "FAIL";
					xllib.writeToExcel("ReferralThroughInviteLink", i, 10, isTestPassed);
					//ScriptHandler.comment.add("Script Referral Signup through Invite Link, Test Data "+i+": Failed in Referral Signup through Invite Link Page.");	
					//update table here
				}
			}//End of for loop
		}
		catch(Exception e)
		{
			log.info("Referral Account creation unsucessfull");
		}
	}
	
	/**
	 * Test Case for passing Signup URL and selecting Member Type
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void inviteLinkURLActions(WebDriver driver,String inviteLinkURL)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 driver.get(inviteLinkURL);
			 log.info("Navigating to "+inviteLinkURL);
		 	 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 }catch(Exception e)
		 {
			e.printStackTrace();
		 }
	 }
	 
	/**
	 * Test Case for Subscriber Sign Up using a friend's referral link.
	 * Input: driver and parameters
	 * Output: Boolean
     * @throws InterruptedException 
     * @throws InvalidFormatException 
	 * 
	 */	 
	
	public boolean subscriberSignUpPageActions(WebDriver driver,String firstName,String lastName,String email,String password,String useOtherCity,String city,String otherCity,String updateMyCitiesStatus,String selectAll,String Atlanta,String Austin,String Baltimore,String BatonRouge,String Birmingham,String Boston,String Charlotte,String Chicago,String Cleveland,String Columbus,String DC,String Dallas,String Denver,String Houston,String Indianapolis,String KansasCity,String LosAngeles,String Miami,String Milwaukee,String Minneapolis,String Nashville,String NewOrleans,String NewYork,String Philadelphia,String Phoenix,String Pittsburgh,String Portland,String Raleigh,String SanAntonio,String SanDiego,String SanFrancisco,String Seattle,String StLouis) throws InterruptedException, InvalidFormatException
	{
		 try
		 {
			
		 	log.info("Creating Dinner Lab Subscriber Account.");
			
		 	//Sign Up Code
			if(useOtherCity.equalsIgnoreCase(TestConstants.STATUS_YES)){
				
				driver.findElement(By.id("useCityOther")).click();
				driver.findElement(By.id("cityOtherAutocomplete")).sendKeys(otherCity);
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//li[contains(text(),'"+otherCity+"')]")).click();
			}
			else
			{
				WebElement cityText = driver.findElement(By.id("cityID"));
				Select st1 = new Select(cityText);
				st1.selectByVisibleText(city);
				//driver.findElement(By.id("cityID")).sendKeys(city);
			}
			
			/*WebElement member = driver.findElement(By.id("usertypeID"));
			Select st = new Select(member);
			st.selectByIndex(1);
			Thread.sleep(2000);*/
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
				
			/*driver.findElement(By.id("signup-submit")).click();
			Thread.sleep(3000);
            referLink = driver.findElement(By.xpath("//textarea[@class='code']")).getText();
            System.out.println("Your Referral Link is:"+referLink);*/
            
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //Clicking on Next             
            driver.findElement(By.xpath("//span[@class='next ctrl']")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            
            FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
            
            //Condition to Select other cities to receive email for events.
            freeMember.selectOtherCitiesToReceiveMailForEvents(driver,useOtherCity,city,otherCity,updateMyCitiesStatus,selectAll,Atlanta,Austin,Baltimore,BatonRouge,Birmingham,Boston,Charlotte,Chicago,Cleveland,Columbus,DC,Dallas, Denver,Houston,KansasCity,LosAngeles,Miami,Milwaukee,Minneapolis,Nashville,NewOrleans,NewYork,Philadelphia,Phoenix,Pittsburgh,Portland,SanAntonio,SanDiego,SanFrancisco,Seattle,StLouis);
            //Condition to Select other cities to receive email for events.
            /*if(updateMyCitiesStatus.equalsIgnoreCase(TestConstants.STATUS_YES)){
            	
            	FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
            	
            	//Calling Manage your Cities method
            	freeMember.manageCitiesActions(driver,useOtherCity,updateMyCitiesStatus,selectAll,Atlanta,Austin,Baltimore,BatonRouge,Birmingham,Boston,Charlotte,Chicago,Cleveland,Columbus,DC,Dallas,Denver,Houston,KansasCity,LosAngeles,Miami,Milwaukee,Minneapolis,Nashville,NewOrleans,NewYork,Philadelphia,Phoenix,Pittsburgh,Portland,SanAntonio,SanDiego,SanFrancisco,Seattle,StLouis);
            }*/
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//span[@class='next ctrl']")).click();
            return true;
		}
		catch(Exception e)
		{  
			e.printStackTrace();
			 log.info("SignUp unsuccessful.");
			 return false;
		 }
	}//End of subscriberSignUpPageActions method
}//End of Class
		

