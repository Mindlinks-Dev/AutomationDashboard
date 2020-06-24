/**
 * @author: Basappa Hunsikatti
 * @Created Date :26/10/2015
 * @Updated Date :
 * @Comments This automation class will purchase Member/Non Member/Chef Gift.
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

public class GiftPurchasePage 
{
	 private String dinnerLabURL;
	 private String giftMembershipCity;
	 private String selectGiftType;
	 private String quantity;
	 private String DinnerCreditsToMembership;
	 private String doYouWantToLogin;
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
	 public static boolean giftPurchaseSuccess;
	 public static boolean loginStatus;
	 public static String giftCity;
	 public static String membershipFee;
	 public static String numberOfMembership;
	 public static String orderCredits;
	 public static String orderTotal;
	 private String defaultCard;
	 String isTestPassed="FAIL";
	 boolean present;
	 public int rowCount;
	 private static Logger log = Logger.getLogger(GiftPurchasePage.class);
	 //MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	 
	 //AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
		
	 /**
	 * Test Case for purchasing Non Member/Chef Gift.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void giftPurchaseInitialPage(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	 {
		 try
		 {
			 ExcelLib xllib = new ExcelLib();
			 
			 rowCount= xllib.getRowCount("GiftPurchase");
		 	for (int i = 1; i <= rowCount; i++) 
			{
		 		dinnerLabURL = xllib.getExcelData("GiftPurchase", i, 0);
		 		selectGiftType = xllib.getExcelData("GiftPurchase", i,1);
		 		giftMembershipCity = xllib.getExcelData("GiftPurchase", i,2);
		 		quantity = xllib.getExcelData("GiftPurchase", i,3);
		 		DinnerCreditsToMembership = xllib.getExcelData("GiftPurchase", i,4);
		 		doYouWantToLogin = xllib.getExcelData("GiftPurchase", i,5);
		 		emailAddress = xllib.getExcelData("GiftPurchase", i,6);
		 		loginPassword = xllib.getExcelData("GiftPurchase", i, 7);
		 		defaultCard = xllib.getExcelData("GiftPurchase", i, 8);
		 		cardName = xllib.getExcelData("GiftPurchase", i,9);
		 		cardNumber = xllib.getExcelData("GiftPurchase", i,10);
		 		expiryMonth = xllib.getExcelData("GiftPurchase", i,11);
		 		expiryYear = xllib.getExcelData("GiftPurchase", i,12);
		 		cvv = xllib.getExcelData("GiftPurchase", i,13);
		 		billingStreet = xllib.getExcelData("GiftPurchase", i,14);
		 		billingCity = xllib.getExcelData("GiftPurchase", i,15);
		 		billingState = xllib.getExcelData("GiftPurchase", i,16);
		 		billingZipCode = xllib.getExcelData("GiftPurchase", i,17);
		 		
		 		System.out.println("selectGiftType:"+selectGiftType);
		 		System.out.println("cardNumber:"+cardNumber);
		 		System.out.println("quantity:"+quantity);
		 		//Calling Dinner Lab URL Method.
		 		dinnerLabURL(driver,dinnerLabURL);
		 		
		 		//Calling clicking on Gift link and Selecting Gift Type method
		 		clickingOnGiftLinkAndSelectGiftType(driver);
		 		
		 		if(selectGiftType.equalsIgnoreCase(TestConstants.SELECT_GIFT_TYPE_MEMBERSHIP))
		 		{
		 			//Clicking on Gifts Link
			 		//GiftPurchaseActions(driver,giftMembershipCity,quantity);
		 			GiftPurchaseActions(driver,giftMembershipCity);
			 		
			 		//adding Dinner Credits to Membership and clicking on Purchase button
			 		addingDinnerCreditsToMembership(driver,DinnerCreditsToMembership);
			 		
			 		//Condition to check continue as Guest. 
					if(doYouWantToLogin.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 		{
						memberGiftPurchaseActions(driver);
					}else if(doYouWantToLogin.equalsIgnoreCase(TestConstants.STATUS_FALSE))
					{
						guestGiftPurchaseActions(driver);
					}
					if(giftPurchaseSuccess)
					{
						isTestPassed="PASS";
						xllib.writeToExcel("GiftPurchase", i, 18, isTestPassed);
						xllib.writeToExcel("GiftPurchase", i, 19, giftCity);
						xllib.writeToExcel("GiftPurchase", i, 20, membershipFee);
						//xllib.writeToExcel("GiftPurchase", i, 21, numberOfMembership);
						xllib.writeToExcel("GiftPurchase", i, 21, orderCredits);
						xllib.writeToExcel("GiftPurchase", i, 22, orderTotal);
					}
					else
					{
						isTestPassed="FAIL";
						xllib.writeToExcel("GiftPurchase", i, 18, isTestPassed);
					}
		 		}else if(selectGiftType.equalsIgnoreCase(TestConstants.SELECT_GIFT_CREDIT))
		 		{
		 			//adding Dinner Credits to Membership and clicking on Purchase button
			 		addingDinnerCreditsToMembership(driver,DinnerCreditsToMembership);
			 		
			 		//Condition to check continue as Guest. 
					if(doYouWantToLogin.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 		{
						memberGiftPurchaseActions(driver);
					}else if(doYouWantToLogin.equalsIgnoreCase(TestConstants.STATUS_FALSE))
					{
						guestGiftPurchaseActions(driver);
					}
					if(giftPurchaseSuccess)
					{
						isTestPassed="PASS";
						xllib.writeToExcel("GiftPurchase", i, 18, isTestPassed);
						xllib.writeToExcel("GiftPurchase", i, 21, orderCredits);
						xllib.writeToExcel("GiftPurchase", i, 22, orderTotal);
					}
					else
					{
						isTestPassed="FAIL";
						xllib.writeToExcel("GiftPurchase", i, 18, isTestPassed);
					}
		 		}
		 	}
		}
		catch(Exception e)
		{
			log.info("Gift has not purchased.");	
		}		
	}
	
	/**
		 * Test Case for passing Dinner Lab URL 
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	*/
	 public  void dinnerLabURL(WebDriver driver,String dinnerLabURL)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 String dinnerURL = TestConstants.HTTP+dinnerLabURL;
			 driver.get(dinnerURL);
			 log.info("Navigating to "+dinnerURL);
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 }catch(Exception e)
		 {
			e.printStackTrace();
		 }
	 }
	/**
	 * Test Case for purchasing gift for guest.
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public void guestGiftPurchaseActions(WebDriver driver) throws InvalidFormatException 
	{
		try
		{
			driver.findElement(By.id("continueAsGuest")).click();
			log.info("You clicked on Continue as Guest.");
		
			//Calling Order Details Data method
			gettingOrderDetailsData(driver);
			
			//Calling Email Address method
			emailTextField(driver);
			
			AddNewCreditCardInfoPage creditCard = new AddNewCreditCardInfoPage();
			
			//Calling Add New Credit Card Info Method
			creditCard.addNewCreditCardInfoActions(driver,cardName,cardNumber,expiryMonth,expiryYear,cvv,billingStreet,billingCity,billingState,billingZipCode);
	 	  
			//clicking on Purchase Button
			creditCard.clickingOnBecomeAMemberButton(driver);
			
			//Verifying Gift Purchase
			verifyGiftPurchaseActions(driver);
		}
		catch(Exception e)
		{
			log.info("Guest Gift purchase has been failed.");	
		}	
	}

	/**
	* Test Case for entering Email Address
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public void emailTextField(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			 driver.findElement(By.id("emailInputted")).clear();
			 driver.findElement(By.id("emailInputted")).sendKeys(emailAddress);
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	
	 /**
		* Test Case for getting Order Details Data
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
		*/
		 public void gettingOrderDetailsData(WebDriver driver)
		 {
			 try
			 {
				 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				 if(selectGiftType.equalsIgnoreCase(TestConstants.SELECT_GIFT_TYPE_MEMBERSHIP))
			 	 {
					 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
					 giftCity = driver.findElement(By.xpath("//div[h2[contains(text(),'Order Details')]]/div[1]/div[2]")).getText();
					 membershipFee = driver.findElement(By.xpath("//div[h2[contains(text(),'Order Details')]]/div[2]/div[2]")).getText();
					 //numberOfMembership = driver.findElement(By.xpath("//div[h2[contains(text(),'Order Details')]]/div[3]/div[2]")).getText();
					 orderCredits = driver.findElement(By.xpath("//div[h2[contains(text(),'Order Details')]]/div[3]/div[2]")).getText();
					 orderTotal = driver.findElement(By.xpath("//div[h2[contains(text(),'Order Details')]]/div[4]/div[2]")).getText();
					 System.out.println("giftCity:"+giftCity);
					 System.out.println("membershipFee:"+membershipFee);
					 //System.out.println("numberOfMembership:"+numberOfMembership);
					 System.out.println("orderCredits:"+orderCredits);
				 	 System.out.println("orderTotal:"+orderTotal);
			 	 }else if(selectGiftType.equalsIgnoreCase(TestConstants.SELECT_GIFT_CREDIT))
			 	 {
			 		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			 		orderCredits = driver.findElement(By.xpath("//div[h2[contains(text(),'Order Details')]]/div[1]/div[2]")).getText();
					orderTotal = driver.findElement(By.xpath("//div[h2[contains(text(),'Order Details')]]/div[2]/div[2]")).getText();
					System.out.println("orderCredits:"+orderCredits);
				 	System.out.println("orderTotal:"+orderTotal);
			 	 }
			 }catch(Exception e)
			 {
				e.printStackTrace();
			 }
		 }
	/**
	 * Test Case for verifying gift purchase.
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public void verifyGiftPurchaseActions(WebDriver driver) throws InvalidFormatException 
	{
		try
		{
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(2000);
			//giftPurchaseSuccess = driver.findElement(By.xpath("//h1[contains(text(),'Seriously, you just made')]")).isDisplayed();
			giftPurchaseSuccess = driver.findElement(By.xpath("//h1[contains(text(),'SPREADING HAPPINESS')]")).isDisplayed();
			System.out.println("giftPurchaseSuccess:"+giftPurchaseSuccess);
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			//Verify Gift Purchased or not.
			if(giftPurchaseSuccess){
				log.info("Gift has been purchased successfully.");	
			}
		}
		catch(Exception e)
		{
			log.info("Gift purchase has not been verified.");	
		}	
	}
	/**
	 	* Test Case for purchasing gift for Member.
	 	* Input: WebDriver
	 	* Output: Void
	 	* @return 
	 	* @throws InvalidFormatException 
	*/
	public void memberGiftPurchaseActions(WebDriver driver) 
	{
		try
		{
			driver.findElement(By.id("continueAsMember")).click();
			log.info("You clicked on Login button.");
		
			MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
			
			//Calling Login URL method
			loginLogout.loginURLActions(driver,dinnerLabURL);
			
			//Calling Member Login Method
			loginStatus = loginLogout.loginPageActions(driver,emailAddress,loginPassword);
			
			if(loginStatus)
			{
				//Calling Order Details Data method
				gettingOrderDetailsData(driver);
				
				//Calling Email Address method
				emailTextField(driver);
				
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
				verifyGiftPurchaseActions(driver);
				
				//Calling Logout Method
				//loginLogout.logoutActions(driver);
			}
			if(rowCount !=1)
			{
            	//Calling Logout method
				loginLogout.logoutActions(driver);
			}
		}
		catch(Exception e)
		{
			log.info("Member Gift purchase has been failed.");	
		}	
	}
	/**
	* Test Case for clicking on Gifts link
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 //public void GiftPurchaseActions(WebDriver driver,String giftMembershipCity,String quantity)
	public void GiftPurchaseActions(WebDriver driver,String giftMembershipCity)
	 {
		 try
		 {
			 WebElement giftMemCity= driver.findElement(By.id("cityID"));
			 Select st = new Select(giftMemCity);
			 st.selectByVisibleText(giftMembershipCity);
				
			 /*WebElement qty= driver.findElement(By.id("quantity"));
			 Select st1 = new Select(qty);
			 st1.selectByVisibleText(quantity);*/
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 /**
		 * Test Case for clicking on Gift link and Selecting Gift Type Button
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	*/
	 public  void clickingOnGiftLinkAndSelectGiftType(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			 log.info("Clicking on Gifts");	
			 driver.findElement(By.xpath("//a[@href='/gifts/']")).click();
			 driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
			 
			 WebElement giftType= driver.findElement(By.id("giftType"));
			 Select st1 = new Select(giftType);
			 st1.selectByVisibleText(selectGiftType);
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 /**
		 * Test Case for adding Dinner Credits to Membership and clicking on Purchase button
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	*/
	 public  void addingDinnerCreditsToMembership(WebDriver driver,String DinnerCreditsToMembership)
	 {
		 try
		 {
			 //Converting String to Integer			
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			 int credits = Integer.parseInt(DinnerCreditsToMembership);
			 System.out.println("credits:"+credits);
				
			 //Condition to add Dinner Credits to Membership.
			 for(int j=1;j<=credits;j++)
			 {
				driver.findElement(By.xpath("//span[@class='ctrl more']")).click();
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			 }
			 driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
			 driver.findElement(By.id("gift-submit")).click();  
		 }catch(Exception e)
		 {
			e.printStackTrace();
		 }
	 }
}