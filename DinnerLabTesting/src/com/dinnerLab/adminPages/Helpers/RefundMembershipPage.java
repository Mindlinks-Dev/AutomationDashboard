/**
 * @author: Anitha.M.
 * @Created Date :10/12/2015
 * @Updated Date :
 * @Comments:This automation class will refund membership amount
*/
package com.dinnerLab.adminPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.MemberLoginLogoutPage;
import com.dinnerLab.util.ExcelLib;

public class RefundMembershipPage
{
	private String AdminEmail;
	private String AdminPassword;
	private String email;
	private String loginURL;
	public String changerById;
	public String changerByDate;
	public String id;
	public String chargeID;
	public String refundAmount;
	public String creditStatus;
	public String adjustCredits;
	public String descStatus;
	public String desc;
	public String userTypeStatus;
	public String usertype;
	public String mailingList;
	public String currentUserType;
	public String userID;
	public String amountRefunded;
	public String creditAdded;
	public String creditAddedDesc;
	public String changedUserType;
	public String changedMailinglist;
	boolean refundStatus = false;
	boolean partialRefundStatus = false;
	public static boolean operationSuccess=false;
	public int rowCount;
	public int rowsCount;
	String isTestPassed="FAIL";
	int i=0;
	String amt;
	float amount;
	String refundAmt;
	float refundAmountValue;
	float enteredAmountToRefund;
	private static Logger log = Logger.getLogger(RefundMembershipPage.class);
	
	/**
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public boolean AdminLogin(WebDriver driver)
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowsCount= xllib.getRowCount("AdminLogin");
		 	for (i = 1; i <= rowsCount; i++) 
			{
		 		loginURL = xllib.getExcelData("AdminLogin", i, 0);
		 		AdminEmail = xllib.getExcelData("AdminLogin", i, 1);
				AdminPassword = xllib.getExcelData("AdminLogin", i, 2);
				
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				//Calling login url method
				loginLogout.loginURLActions(driver, loginURL);
								
				//Calling Login method
				loginLogout.loginPageActions(driver,AdminEmail,AdminPassword);
								
				CreateNewEventPage newEvent = new CreateNewEventPage();
				
				//Calling Super Admin Link method 
				newEvent.clickingOnSuperAdminLink(driver,loginURL);
				
				//Calling Refund membership method
				clickingOnRefundMembershipLink(driver);
				
				//Calling child handle window method
				newEvent.childWindowHandle(driver);
			}
		 	return true;
		 }
			catch(Exception e)
			{
				//e.printStackTrace();
				return false;
			}
	}
	/**
	 * Test Case for Reading the excel refund membership data
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void refundMembershipInitialPage(WebDriver driver)throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowCount= xllib.getRowCount("RefundMembership");
			log.info("*********************Refund Membership Logger Initialized******************************************************************************************************************************************************************");
			log.info("Charger ID  ||"+" Email ||" + "   Current User Type ||" + "  Refund Status || " + " User ID  || " + "  Amount Refunded || " + " Credit Added || "+ " Credit Added Desc || "+ "Changed User Type || "+ "Changed Mailing List");
	 		log.info("***************************************************************************************************************************************************************************************************************************");
		 	for (i = 1; i <= rowCount; i++) 
			{
		 		//Reading creating New Event values
		 		chargeID = xllib.getExcelData("RefundMembership", i, 0);
				email = xllib.getExcelData("RefundMembership", i, 1);
				refundAmount = xllib.getExcelData("RefundMembership", i, 2);
				creditStatus = xllib.getExcelData("RefundMembership", i, 3);
				adjustCredits = xllib.getExcelData("RefundMembership", i, 4);
				descStatus = xllib.getExcelData("RefundMembership", i, 5);
				desc = xllib.getExcelData("RefundMembership", i, 6);
				userTypeStatus = xllib.getExcelData("RefundMembership", i, 7);
				usertype = xllib.getExcelData("RefundMembership", i, 8);
				mailingList = xllib.getExcelData("RefundMembership", i, 9);
				
		 		//Search criteria
				searchCriteria(driver);
				
				//Writing Origin User Type into Excel file
				//xllib.writeToExcel("RefundMembership", i, 10, currentUserType);
				
				System.out.println("refundStatus123:"+refundStatus);
				
				//if(refundStatus || !operationSuccess)
				if(refundStatus)
				{
					isTestPassed="FAIL";
					log.info(","+chargeID +"," +email +"," + isTestPassed + "," + isTestPassed+"," + isTestPassed+"," + isTestPassed+"," + isTestPassed+"," + isTestPassed+"," + isTestPassed+"," + isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 10, isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 11, isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 12, isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 13, isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 14, isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 15, isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 16, isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 17, isTestPassed);
				}
				//else if(!refundStatus && operationSuccess)
				else if(!refundStatus && amount>=enteredAmountToRefund)
				{
					isTestPassed="PASS";
					log.info(","+chargeID +"," +email +"," + currentUserType + "," + isTestPassed+"," + userID+"," + amountRefunded+"," + creditAdded+"," + creditAddedDesc+"," + changedUserType+"," + changedMailinglist);
					xllib.writeToExcel("RefundMembership", i, 10, currentUserType);
					xllib.writeToExcel("RefundMembership", i, 11, isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 12, userID);
					xllib.writeToExcel("RefundMembership", i, 13, amountRefunded);
					xllib.writeToExcel("RefundMembership", i, 14, creditAdded);
					xllib.writeToExcel("RefundMembership", i, 15, creditAddedDesc);
					xllib.writeToExcel("RefundMembership", i, 16, changedUserType);
					xllib.writeToExcel("RefundMembership", i, 17, changedMailinglist);
				}else
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("RefundMembership", i, 10, isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 11, isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 12, isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 13, isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 14, isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 15, isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 16, isTestPassed);
					xllib.writeToExcel("RefundMembership", i, 17, isTestPassed);
				}
			}
		 }
		catch(Exception e)
		 {
			// e.printStackTrace();
		 }
	}
	/**
	 * Test Case for Refund tool.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	 public  void clickingOnRefundMembershipLink(WebDriver driver)
	 {
		 try
		 {
			 
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 driver.findElement(By.xpath("//a[@href='/superadmin/user-transactions/refund-search']")).click();
			 log.info("Clicked on Refund Membeship Link");
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }
		 catch(Exception e)
		{
			//e.printStackTrace();
		}
	 }
	 /**
		 * Test Case for searching user to Refund
		 * Input: WebDriver
		 * Output: Void
		 * @return 
	 * @throws InterruptedException 
		 * @throws InvalidFormatException 
		*/
		 public  void searchCriteria(WebDriver driver) throws InterruptedException, InvalidFormatException
		 {
			try
			 {
				 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				 driver.findElement(By.id("filter_chargeID")).clear();
		         driver.findElement(By.id("filter_chargeID")).sendKeys(chargeID);
		         driver.findElement(By.id("filter_email")).clear();
		         driver.findElement(By.id("filter_email")).sendKeys(email);
		         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		         driver.findElement(By.id("search")).click();              // Search button
		         try
		         {
		        	 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		        	 refundStatus =driver.findElement(By.xpath("//tr[td[a[contains(text(),'"+email+"')]]]/td[10]/span[contains(text(),'Yes')]")).isDisplayed();
		        	 System.out.println("refundStatus:"+refundStatus);
		        	 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		         }
		         catch(Exception ex)
		         {
		        	 //ex.printStackTrace();
		         }
		         System.out.println("refundStatus111:"+refundStatus);	
			     driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		         	          
		         amt = driver.findElement(By.xpath("//tr[td[a[contains(text(),'"+email+"')]]]/td[9]/b")).getText();
		         //System.out.println("amt:"+amt);
		         
		         amount = Float.parseFloat(amt);
		         System.out.println("amount:"+amount);
		         
		         refundAmt =  driver.findElement(By.xpath("//tr[td[a[contains(text(),'"+email+"')]]]/td[11]/span")).getText();
		         //System.out.println("refundamt:"+refundAmt);
		         
		         driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		         
		         refundAmountValue = Float.parseFloat(refundAmt);
		         System.out.println("refundAmountValueBefore:"+refundAmountValue);
		         
		         amount = amount-refundAmountValue;
		         System.out.println("Balance Amount:"+amount);
		         
		         enteredAmountToRefund = Float.parseFloat(refundAmount);
		         System.out.println("Entered Amount to refund:"+enteredAmountToRefund);
		         
		         driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		         try
		         {
		        	 if(!refundStatus)
			         {
			        	 try
			        	 {
			        		 if(amount>=enteredAmountToRefund)
				        	 {
				        		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				        		 driver.findElement(By.xpath("//a[@class='btn btn-info ']")).click();  //Refund Button
				        		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				        		 
				        		 //Calling Refund Actions method
				 				 refundMembershipActions(driver);
				 				 
				 				 //calling Changed User Type Operation Success method
				 				 refundMembershipOperationSuccess(driver);
				        	 } 
			        	 }catch(NoSuchElementException e)
				 			{
				 				System.out.println("Entered Refunding Amount is greater than balance amount");
				 			}
			         }
		         }catch(NoSuchElementException e)
		 		 {
		 			//e.printStackTrace();
		 		}
		    }catch(NoSuchElementException e)
			{
				//e.printStackTrace();
			}
		 }
			/**
			* Test Case for Refunding Membership Action method.
			* Input: WebDriver
			* Output: Void
			* @return 
			* @throws InvalidFormatException 
		    */ 
		public void refundMembershipActions(WebDriver driver)
		{
			try
			{
				 //Current userType
				 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		         currentUserType=driver.findElement(By.xpath("//div[label[contains(text(),'Current User Type ID')]]/div[1]/span")).getText();
		         System.out.println("Current userType 123:"+currentUserType);
		         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		         
		         // Refund button
		         driver.findElement(By.id("amount")).clear();
		         driver.findElement(By.id("amount")).sendKeys(refundAmount); // Amount for Refund
				
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				// Adding credits for Refund its optional.
				if(creditStatus.equalsIgnoreCase(TestConstants.REFUND_STATUS))
				{
					driver.findElement(By.id("credit")).clear();
					driver.findElement(By.id("credit")).sendKeys(adjustCredits);
				}
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				//Credits description
				if(descStatus.equalsIgnoreCase(TestConstants.REFUND_STATUS))  // credits descriptions
				{
					driver.findElement(By.id("creditDescription")).clear();
					driver.findElement(By.id("creditDescription")).sendKeys(desc);
				}
				
			  if(userTypeStatus.equalsIgnoreCase(TestConstants.REFUND_STATUS))   // userType
				{
					WebElement userType =  driver.findElement(By.id("userTypeID"));
					Select st = new Select(userType);
					st.selectByVisibleText(usertype);
				}
				WebElement mailing =  driver.findElement(By.id("keepMailingList")); // Mailing list
				Select st = new Select(mailing);
				st.selectByVisibleText(mailingList);
				
				driver.findElement(By.xpath("//button[@type='submit']")).click(); //refund button
				
			}
			catch(WebDriverException wde)
			{
				//wde.printStackTrace();
			}
		}
		
		/**
		 * Test Case for Refund success operation page.
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		*/
		 public  void refundMembershipOperationSuccess(WebDriver driver)
		 {
			String userID1[]=null;
			String amountRefunded1[]=null;
			String creditAdded1[]=null;
			String creditAddedDesc1[]=null;
			String changedUserType1[]=null;
			String changedMailinglist1[]=null;
					
			 try
			 {
				 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				 operationSuccess = driver.findElement(By.xpath("//span[contains(text(),'Operation Success:')]")).isDisplayed();
				 System.out.println("operationSuccess:"+operationSuccess);
									
				 if(operationSuccess)
				 {
					 try
					 {
						 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
						 userID1 = driver.findElement(By.xpath("//li[contains(text(),'id:')]")).getText().split(":");
						 amountRefunded1 = driver.findElement(By.xpath("//li[contains(text(),'amount:')]")).getText().split(":");
						 creditAdded1 = driver.findElement(By.xpath("//li[contains(text(),'credit:')]")).getText().split(":");
						 creditAddedDesc1 = driver.findElement(By.xpath("//li[contains(text(),'creditDescription:')]")).getText().split(":");
						 changedUserType1 = driver.findElement(By.xpath("//li[contains(text(),'userTypeID:')]")).getText().split(":");
						 changedMailinglist1 = driver.findElement(By.xpath("//li[contains(text(),'keepMailingList:')]")).getText().split(":");
						 
						 userID = userID1[1];
						 amountRefunded = amountRefunded1[1];
						 creditAdded = creditAdded1[1];
						 creditAddedDesc = creditAddedDesc1[1];
						 changedUserType = changedUserType1[1];
						 changedMailinglist = changedMailinglist1[1];
						
						 System.out.println("userID:"+userID);
						 System.out.println("amountRefunded:"+amountRefunded);
						 System.out.println("creditAdded:"+creditAdded);
						 System.out.println("creditAddedDesc:"+creditAddedDesc);
						 System.out.println("changedUserType:"+changedUserType);
						 System.out.println("changedMailinglist:"+changedMailinglist);
						 
						 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
					 }
					catch(Exception e)
					{
						//e.printStackTrace();
					}
					  
				 }
				  CreateNewEventPage newEvent = new CreateNewEventPage();
					
				  //Calling clicking on go back link method
				  newEvent.clickingOnGoBackLink(driver);
			}
			catch(Exception e)
			{
				//e.printStackTrace();
			}
		 }
}


