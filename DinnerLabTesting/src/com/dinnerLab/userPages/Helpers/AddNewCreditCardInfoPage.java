/**
 * @author: Basappa Hunsikatti
 * @Created Date :14/07/2015
 * @Updated Date :
 * @Comments This automation class will add Member/Chef new Credit Card information.
 */
package com.dinnerLab.userPages.Helpers;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;

public class AddNewCreditCardInfoPage 
{
	 private String loginEmail;
	 private String loginPassword;
	 private String loginURL;
	 private String nameOnCard;
	 private String cardNumber;
	 private String expiryMonth;
	 private String expiryYear;
	 private static String phoneNumber;
	 private static String upgradeAccount;
	 private static String saveCard;
	 private String cvv;
	 private String billingStreet;
	 private String billingCity;
	 private String billingState;
	 private String billingZipCode;
	 public static boolean accountUpgradedStatus;
	 public static boolean verifyUpgradedButton;
	 public static boolean upgradeAccountStatus;
	 public static boolean UpgradeAccountButtonStatus;
	 boolean present;
	 String isTestPassed="FAIL";
	 public int rowCount;
	 int i=0;
	 private static Logger log = Logger.getLogger(AddNewCreditCardInfoPage.class);
	 //MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	 
	 //FreeMemberTicketPurchasePage freeMember = new FreeMemberTicketPurchasePage();
	 
	 /**
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void addNewCreditCardInfoInitialPage(WebDriver driver)
	 {
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			int rowCount = xllib.getRowCount("AddNewCreditCard");
			log.info("*********************User Add New Card Info Logger Initialized******************************* ");
			for(int i=1;i<= rowCount;i++)
			{
				loginURL = xllib.getExcelData("AddNewCreditCard", i, 0);
				loginEmail = xllib.getExcelData("AddNewCreditCard", i, 1);
				loginPassword = xllib.getExcelData("AddNewCreditCard", i, 2);
				upgradeAccount = xllib.getExcelData("AddNewCreditCard", i, 3);
				phoneNumber = xllib.getExcelData("AddNewCreditCard", i, 4);
				nameOnCard = xllib.getExcelData("AddNewCreditCard", i, 5);
				cardNumber = xllib.getExcelData("AddNewCreditCard", i,6);
			 	expiryMonth = xllib.getExcelData("AddNewCreditCard", i,7);
			 	expiryYear = xllib.getExcelData("AddNewCreditCard", i,8);
			 	cvv = xllib.getExcelData("AddNewCreditCard", i,9);
			 	billingStreet = xllib.getExcelData("AddNewCreditCard", i,10);
			 	billingCity = xllib.getExcelData("AddNewCreditCard", i,11);
			 	billingState = xllib.getExcelData("AddNewCreditCard", i,12);
			 	billingZipCode = xllib.getExcelData("AddNewCreditCard", i,13);
				
			 	MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
			 	
			 	//Calling Login URL method
				loginLogout.loginURLActions(driver,loginURL);
				
				//Calling Member Login Method
				loginLogout.loginPageActions(driver,loginEmail,loginPassword);
						
				
				//Checking whether login credentials valid or not
				if(MemberLoginLogoutPage.loggedInStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("AddNewCreditCard", i, 14, isTestPassed);
					
					if(upgradeAccount.equalsIgnoreCase(TestConstants.STATUS_TRUE))
					{
						//Clicking on Upgrade Account Button
					 	clickingOnUpgradeAccountButton(driver);
					 	  
					 	//Adding Phone Number into Payment Page
					 	addingPhoneNumberActions(driver,phoneNumber);
					 	  
					 	//Calling Add New Credit Card Info Method
					 	addNewCreditCardInfoActions(driver,nameOnCard,cardNumber,expiryMonth,expiryYear,cvv,billingStreet,billingCity,billingState,billingZipCode);
					 	  
					 	//clicking on Become A Member Button
					 	clickingOnBecomeAMemberButton(driver);
					 	
					 	//verifying Payment success
					 	verifyPaymentPageAction(driver);
					 	
					 	if(accountUpgradedStatus)
						{
							isTestPassed="PASS";
							xllib.writeToExcel("AddNewCreditCard", i, 15, isTestPassed);
						}else if(!accountUpgradedStatus)
						{
							isTestPassed = "FAIL";
							xllib.writeToExcel("AddNewCreditCard", i, 15, isTestPassed);
						}
					 	
					}else if(upgradeAccount.equalsIgnoreCase(TestConstants.STATUS_FALSE))
					{
						//Calling Account Overview Link method
						//clickingOnAccountOverviewLink(driver);
						
						//Calling Account Overview method
						clickingOnAccountOverview(driver);
						
						FreeMemberTicketPurchasePage freeMember = new FreeMemberTicketPurchasePage();
						
						//Condition to check credit card exists or not
						freeMember.checkingDefaultCreditCard(driver);
			        	
						//if(!FreeMemberTicketPurchasePage.creditCardExistStatus)
						//{
							//Clicking on add new card method
							clickingOnAddNewCard(driver);
							
							//Calling Add New Credit Card Info Method
						 	addNewCreditCardInfoActions(driver,nameOnCard,cardNumber,expiryMonth,expiryYear,cvv,billingStreet,billingCity,billingState,billingZipCode);
							
						 	//Calling Submit method
						 	clickingOnSubmitButton(driver);
						//}
					}
					
					if(rowCount !=1)
					{
						//Calling Logout method
						loginLogout.logoutActions(driver);
					}
				}else if(!MemberLoginLogoutPage.loggedInStatus)
			    {
			    	isTestPassed = "FAIL";
					xllib.writeToExcel("AddNewCreditCard", i, 14, isTestPassed);
				}
				
			}
		}
		catch(Exception e)
		{
			log.info("Test Execution Failed");
		}
	 }
		
	/**
	 * Test Case for clicking on Upgrade Account Button
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void clickingOnUpgradeAccountButton(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 UpgradeAccountButtonStatus = driver.findElement(By.xpath("//a[contains(text(),'Upgrade Account')]")).isDisplayed();
	         driver.findElement(By.xpath("//a[contains(text(),'Upgrade Account')]")).click();
	         log.info("Clicked on Upgrade Account Button");
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 /**
		 * Test Case for Verifying Upgrade Account Button
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
		*/
		 public  void verifyUpgradeAccountButton(WebDriver driver)
		 {
			 try
			 {
				 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				 verifyUpgradedButton = driver.findElement(By.xpath("//a[contains(text(),'Upgrade Account')]")).isDisplayed();
		         System.out.println("verifyUpgradedButton:"+verifyUpgradedButton);
				 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			 }catch(Exception e)
			{
				e.printStackTrace();
			}
		 }
	 /**
	 * Test Case for adding Phone Number into Payment Page.
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void addingPhoneNumberActions(WebDriver driver,String phoneNumber)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 driver.findElement(By.id("telephone")).clear();
	         driver.findElement(By.id("telephone")).sendKeys(phoneNumber);
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
		
	 /**
	  * Test Case for adding Member/Chef new Credit Card information.
	  * Input: WebDriver
	  * Output: Void
	  * @return 
	  * @throws InvalidFormatException 
	*/
	public  void addNewCreditCardInfoActions(WebDriver driver,
			String nameOnCard, String cardNumber,
			String expiryMonth, String expiryYear, String cvv,
			String billingStreet, String billingCity, String billingState,
			String billingZipCode) throws InvalidFormatException 
	{
		try
		{
			//Adding card details into Payment Page
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            log.info("Navigating to Payment Page");
        	log.info("Entering Credit Card information.");
        	driver.findElement(By.id("cardName")).clear();
        	driver.findElement(By.id("cardName")).sendKeys(nameOnCard);
        	driver.findElement(By.id("cardNumber")).clear();
        	
        	String[] cardNumberArray = cardNumber.split("");
        	for (String c : cardNumberArray) 
        	{
        		//System.out.println("value of C ::"+c);
            	driver.findElement(By.id("cardNumber")).sendKeys(c);
				
			}
        	/*
			driver.findElement(By.id("cardNumber")).sendKeys(cardNumber);
			driver.findElement(By.id("cardNumber")).sendKeys(cardNumber);
			driver.findElement(By.id("cardNumber")).sendKeys(cardNumber);
			*/
			driver.findElement(By.id("cardMonth")).sendKeys(expiryMonth);
			driver.findElement(By.id("cardYear")).sendKeys(expiryYear);
			driver.findElement(By.id("cardCVV")).clear();
			driver.findElement(By.id("cardCVV")).sendKeys(cvv);
			
			//Checking the condition from input configuration file
			/*if(saveCard.equalsIgnoreCase(TestConstants.saveCardInformation))
			{
				driver.findElement(By.id("saveCard")).click();		 
			}*/
			
			driver.findElement(By.id("billingStreet")).clear();
			driver.findElement(By.id("billingStreet")).sendKeys(billingStreet);
			driver.findElement(By.id("billingCity")).clear();
			driver.findElement(By.id("billingCity")).sendKeys(billingCity);
			driver.findElement(By.id("billingState")).sendKeys(billingState);
			driver.findElement(By.id("billingZip")).clear();
			driver.findElement(By.id("billingZip")).sendKeys(billingZipCode);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Your new Credit Card information has not added");
		}
	}
		 
	 /**
	 * Test Case for clicking on Become A Member Button
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public void clickingOnBecomeAMemberButton(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 driver.findElement(By.xpath("//input[@ class='btn']")).click();
	         driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 /**
		 * Test Case for clicking on Submit Button
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	 */
	 public void clickingOnSubmitButton(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 driver.findElement(By.xpath("//input[@class='btn-card-modal']")).click();
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	/**
	 * Test Case for verifying Payment success
	 * Input: WebDriver
	 * Output: Boolean
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  boolean verifyPaymentPageAction(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			 accountUpgradedStatus = driver.findElement(By.xpath("//h1[contains(text(),'Your account has been upgraded.')]")).isDisplayed();
			 log.info("Your new Credit Card information has been added successfully.");
			 System.out.println("accountUpgradedStatus:"+accountUpgradedStatus);
			 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	         return true;
		 }catch(Exception e)
		 {
			e.printStackTrace();
			return false;
		 }
	}
	
	/**
	 * Test Case for verifying upgrade account
	 * Input: WebDriver
	 * Output: Boolean
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  boolean verifyUpgradeAccount(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			 upgradeAccountStatus = driver.findElement(By.xpath("//h1[contains(text(),'YOUR ACCOUNT HAS BEEN UPGRADED')]")).isDisplayed();
			 System.out.println("upgradeAccountStatus:"+upgradeAccountStatus);
			 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	         return true;
		 }catch(Exception e)
		 {
			e.printStackTrace();
			return false;
		 }
	}
	 /**
	 * Test Case for clicking on use this Payment Method link
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public void useThisPaymentMethodAction(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 driver.findElement(By.xpath("//div[@class='default-card']")).click();
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 
	 /**
		 * Test Case for clicking on use different card 
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	 */
	public void useDifferentCardAction(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[@class='add-card new-card']")).click();
		    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Test Case for don't Save Card information
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public void dontSaveCardInformation(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 driver.findElement(By.id("saveCard")).click();
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
		 
	/**
	 * Test Case for clicking on add new card 
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	 */
	public void clickingOnAddNewCard(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[@class='add-card card modal-trigger']")).click();
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Test Case for clicking on Account Overview link
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void clickingOnAccountOverviewLink(WebDriver driver)
	 {
		 try
		 {
			 log.info("Clicking on Account Overview");	
			 driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
			 driver.findElement(By.xpath("//a[@href='#account-overview']")).click();
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 /**
		 * Test Case for changing Home City
		 * Input: WebDriver
		 * Output: Void
		 * @return 
	*/
	public void changeHomeCity(WebDriver driver,String newHomeCity) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement cityText = driver.findElement(By.id("cityID"));
			Select st1 = new Select(cityText);
			st1.selectByVisibleText(newHomeCity);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Test Case for applying Promo Code
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 */
	public void applyingPromoCode(WebDriver driver,String promoCode) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("showCode")).click(); 
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.id("alt-code")).clear();
			driver.findElement(By.id("alt-code")).sendKeys(promoCode);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.id("apply-code")).click(); 
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	 /**
		 * Test Case for clicking on Account Overview
		 * Input: WebDriver
		 * Output: Void
		 * @return 
	*/
	public void clickingOnAccountOverview(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Actions actions = new Actions(driver);
				
			WebElement parent = driver.findElement(By.xpath("//li[@class='parent']")); // member drop down
			actions.moveToElement(parent).build().perform();

			driver.findElement(By.xpath("//a[contains(text(),'Account Overview')]")).click(); 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	 /**
	 * Test Case for clicking on Bio Information
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 */
	public void clickingOnBioInformation(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Actions actions = new Actions(driver);
			
			WebElement parent = driver.findElement(By.xpath("//li[@class='parent']")); // member drop down
			actions.moveToElement(parent).build().perform();

			driver.findElement(By.xpath("//a[contains(text(),'Bio Information')]")).click(); 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 	* Test Case for clicking on Availability
	 	* Input: WebDriver
	 	* Output: Void
	 	* @return 
	 	*/
	public void clickingOnAvailability(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Actions actions = new Actions(driver);
			
			WebElement parent = driver.findElement(By.xpath("//li[@class='parent']")); // member drop down
			actions.moveToElement(parent).build().perform();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//a[contains(text(),'Availability')]")).click(); 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Test Case for clicking on Submit Button
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	 */
	public void clickingOnSubmitButtonActions(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}