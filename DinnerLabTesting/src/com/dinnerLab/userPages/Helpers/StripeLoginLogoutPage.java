/**
 * @author: Basappa Hunsikatti


 * @Created Date :14/08/2015
 * @Updated Date :
 * @Comments This automation class will serve the Stripe Login use cases
 */
package com.dinnerLab.userPages.Helpers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;
import com.dinnerLab.util.OutputReport;
import com.pack.Database;
import com.pack.ScriptHandler;

public class StripeLoginLogoutPage 
{
	
	//private String loginUrl=TestConstants.LogIn_Url;
	private String stripeLoginEmail;
	private String stripeLoginPassword;
	private String stripeLoginURL = TestConstants.STRIPE_LOGIN_URL;
	boolean present;
	Connection con = Database.getConnection();
	Statement stm=null;
	ResultSet rs=null;
	String isTestPassed="FAIL";
	public static String result;
	public static String comment;
	public String description;
	public String memberEmail;
	public String createdDate;
	public String id;
	public String amount;
	public String fee1;
	public String date;
	public String status;
	public String description1;
	public String planID;
	public String itemName;
	public String city;
	public String type;
	public String fCode;
	public String ticketAmount;
	public String ticketFee;
	public String ticketDate;
	public String ticketStatus;
	public String ticketDescription;
	public String ticketItemName;
	public String ticketCity;
	public String ticketType;
	public String ticketFinance;
	public String ticketDiscountAmount;
	public String ticketQuantity;
	public String ticketDay;
	public String ticketDate1;
	public String ticketTotalTax;
	public boolean stripeLoginStatus;
	public boolean memberFinanceCodeStatus;
	public boolean ticketPurchaseTotalTaxStatus;
	public boolean searchTextFieldStatus;
	ExcelLib xllib = new ExcelLib();
	int i = 0;
	private static Logger log = Logger.getLogger(StripeLoginLogoutPage.class);
	OutputReport report = new OutputReport();
		        
	 /**
	 * Test Case for Reading the excel data and login into the Stripe Page
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 * @throws IOException 
	 * @throws AWTException 
	 * @throws InterruptedException 
	 */
	 public void stripeLoginInitialPage(WebDriver driver,String email) throws InvalidFormatException, IOException, AWTException, InterruptedException
	 {
		 
		try
		{
			int rowCount = xllib.getRowCount("Stripe");
			//For loop:Fetch data from excel input configuration file(excel sheet)
			for(i=1;i<= rowCount;i++)
			{  
				stripeLoginEmail = xllib.getExcelData("Stripe", i, 0);
				stripeLoginPassword = xllib.getExcelData("Stripe", i, 1);
				//stripeEmail = xllib.getExcelData("Stripe", i, 2);
				System.out.println("Member Email:"+email);		
				stripeLoginStatus = stripeLoginPageActions(driver,stripeLoginEmail,stripeLoginPassword,email);
				
				//Check whether login credentials valid or not
				if(stripeLoginStatus)
				{
					log.info("Stripe Login sucessful");
					isTestPassed="PASS";
					ScriptHandler.result=xllib.writeToExcel("Stripe", i, 2, isTestPassed);
					System.out.println("In Stripe Login: Result Value:"+ScriptHandler.result);
				}else
				{
					isTestPassed = "FAIL";
					ScriptHandler.result=xllib.writeToExcel("Stripe", i, 2, isTestPassed);
					System.out.println("In Stripe Login: Result Value1:"+ScriptHandler.result);
					//ScriptHandler.comment.add("Script Login, Test Data "+i+": Failed in Login page.");							
				}
			}
		}catch(StaleElementReferenceException e)
		{
			log.info("Stripe Login Unsucessful");
			e.toString();
		}
	}
	
	/**
		 * Test Case for Reading the excel data and login into the Stripe Page
		 * portal on successful login to the application.
		 * Input: WebDriver,loginEmail,loginPassword
		 * Output: Boolean
		 * @throws IOException 
	 * @throws AWTException 
	 */
	 public Boolean stripeLoginPageActions(WebDriver driver,String stripeLoginEmail,String stripeLoginPassword,String email) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException, AWTException
	 {
		 try
		{
			Robot r = new Robot();                          
			r.keyPress(KeyEvent.VK_CONTROL); 
			r.keyPress(KeyEvent.VK_T); 
			r.keyRelease(KeyEvent.VK_CONTROL); 
			r.keyRelease(KeyEvent.VK_T);
		    driver.navigate().to(stripeLoginURL);
			log.info("navigating to "+stripeLoginURL);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.id("email")).clear();		
			driver.findElement(By.id("email")).sendKeys(stripeLoginEmail);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(stripeLoginPassword);
			driver.findElement(By.name("remember")).click();
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			log.info("Waiting for Login process to complete...");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//Condition to Verify Login into an application.
			/*boolean flag = driver.findElement(By.xpath("//span[@class='account-name']")).isDisplayed();
			if(flag)
			{
				log.info("Successfully logged in into Stripe Page.");
			}*/
			return true;
		}catch(StaleElementReferenceException e)
		{
			log.info("Unsuccessful Logged in into Stripe Page.");
			return false;
		}
	}
		
	 /**
		 * Test Case for Reading Membership details in Stripe Page
		 * Input: WebDriver,loginEmail,loginPassword
		 * Output: Void
	     * @throws InvalidFormatException 
	 * @throws InterruptedException 
		 * @throws IOException 
	 */	
	public void stripeMembershipDetails(WebDriver driver,String email) throws InvalidFormatException, InterruptedException 
	{
		try
		    {
		      	//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				
		      	/*By fooID = By.xpath("//div[@id='quicksearch-view']/div[1]/form[1]/div[1]/input");
		      	WebElement foo = driver.findElement(fooID);
		      	foo.click();*/
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				isTestElementPresent(driver);
				
				if(searchTextFieldStatus)
				{
					System.out.println("working inside search text field status");
					//driver.findElement(By.xpath("//div[@id='quicksearch-view']/div[1]/form[1]/div[1]/input")).click();
					Thread.sleep(5000);
			        driver.findElement(By.xpath("//div[@id='quicksearch-view']/div[1]/form[1]/div[1]/input")).clear();
			        System.out.println("after clear");
			        driver.findElement(By.xpath("//div[@id='quicksearch-view']/div[1]/form[1]/div[1]/input")).sendKeys(email);
			        System.out.println("after email sent");
			        driver.findElement(By.xpath("//div[@id='quicksearch-view']/div[1]/form[1]/div[1]/input")).sendKeys(Keys.ENTER);
			        System.out.println("after enter");
			        Thread.sleep(5000);
			        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			        // customer section
			        driver.findElement(By.xpath("//a[@class='customer']")).click();
			        System.out.println("before getting 1st value");
			        
			        Thread.sleep(5000);
			        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		  
			        //Customer Details
			        //getting description
			        description = driver.findElement(By.xpath("//div[@class='container']/dl/dd[4]")).getText();
			        System.out.println("description is :"+description);
			       	        
			        //getting email
			        memberEmail=driver.findElement(By.xpath("//div[@class='container']/dl/dd[3]")).getText();
			        System.out.println("email is:"+memberEmail);
			  
			        //getting created date
			        createdDate=driver.findElement(By.xpath("//div[@class='container']/dl/dd[2]")).getText();
			        System.out.println("Created Date is:"+createdDate);
			    
			        //getting ID
			        id=driver.findElement(By.xpath("//dd[contains(text(), 'cus_')]")).getText();
			        System.out.println("ID is:"+id);
			  
			        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			         
			         Thread.sleep(5000);
			         driver.findElement(By.xpath("//div[@id='quicksearch-view']/div[1]/form[1]/div[1]/input")).click();
				     driver.findElement(By.xpath("//div[@id='quicksearch-view']/div[1]/form[1]/div[1]/input")).clear();
				     System.out.println("after 2nd time clear");
			         driver.findElement(By.xpath("//div[@id='quicksearch-view']/div[1]/form[1]/div[1]/input")).sendKeys(email);
			         System.out.println("after 2nd time email id");
			         driver.findElement(By.xpath("//div[@id='quicksearch-view']/div[1]/form[1]/div[1]/input")).sendKeys(Keys.ENTER);
			         System.out.println("after 2nd time enter");
			         Thread.sleep(5000);
			         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			        driver.findElement(By.xpath("//p[contains(text(), 'membership')]")).click();
			        System.out.println("before getting 2nd value");
			        Thread.sleep(5000);
			        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			         // payment details
			 
			         //getting amount
			         amount=driver.findElement(By.xpath("//span[@class='amount']")).getText();
			         System.out.println("Amount  is :"+amount);
			   
			         //getting fee
			         fee1=driver.findElement(By.xpath("//span[@class='fees']")).getText();
			         System.out.println("Fees  is :"+fee1);
			    
			         //getting date
			         date=driver.findElement(By.xpath("//dl[dt[contains(text(),'Date:')]]/dd[3]")).getText();
			         System.out.println("Date  is :"+date);
			   
			         //getting status
			         status=driver.findElement(By.xpath("//span[@class='status success']")).getText();
			         System.out.println("Status  is :"+status);
			   
			         //getting description
			         description1=driver.findElement(By.xpath("//dd[@id='description']")).getText();
			         System.out.println("Description is :"+description1);
			    
			        //Meta data
			        planID=driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[1]/td[2]")).getText();
			        System.out.println("Plan ID is :"+planID);
			    
			        //item name
			        itemName=driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[2]/td[2]")).getText();
			        System.out.println("ItemName is :"+itemName);
			 
			        //city
			        city=driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[3]/td[2]")).getText();
			        System.out.println("City name is :"+city);
			 
			        //Type
			        type=driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[4]/td[2]")).getText();
			        System.out.println("Type is :"+type);
			 
			        //finance code
			        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			        memberFinanceCodeStatus = driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[5]/td[2]")).isDisplayed();
			        fCode=driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[5]/td[2]")).getText();
			        System.out.println("Finance code is 1 :"+fCode);
			        Thread.sleep(6000);
			        System.out.println("---------------------------------------------------------------------------------");
		        }
		    }
		  catch (Exception e)
		    {
		    	e.printStackTrace();
		        report.outputReport("CompleteTicketPurchaseReport", 42, 3,TestConstants.RESULT_FAIL);
		        report.outputReport("IndividualTicketPurchaseReport", 42, 3,TestConstants.RESULT_FAIL);
		    }
		}
	
		
	public boolean isTestElementPresent(WebDriver driver)
	{
	    try
	    {
	    	Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        searchTextFieldStatus = driver.findElement(By.xpath("//div[@id='quicksearch-view']/div[1]/form[1]/div[1]/input")).isDisplayed();
	        System.out.println("searchTextFieldStatus:"+searchTextFieldStatus);
	        return true;
	    }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
	}

	/**
	  	* Test Case for Writing Complete Ticket Purchase Membership details in Stripe Page into Excel Report
	  	* Input: WebDriver
	  	* Output: Void
	    * @throws InvalidFormatException 
	  	* @throws IOException 
	 */	
	public void completeTicketPurchaseStripeMembershipDetails(WebDriver driver) throws InvalidFormatException 
	{
		try
	    {
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	               
	        report.outputReport("CompleteTicketPurchaseReport", 0, 2, TestConstants.USER_TYPE_MEMBER);
	        report.outputReport("CompleteTicketPurchaseReport", 1, 2, HybridFlowHelper.city);
	        report.outputReport("CompleteTicketPurchaseReport", 6, 2, "Description: "+description);
	        report.outputReport("CompleteTicketPurchaseReport", 5, 2, "Email: "+memberEmail);
	        report.outputReport("CompleteTicketPurchaseReport", 4, 2, "Created Date: "+createdDate);
	        report.outputReport("CompleteTicketPurchaseReport", 3, 2, "ID: "+id);
	        report.outputReport("CompleteTicketPurchaseReport", 9, 2, "Amount: "+amount);
	        report.outputReport("CompleteTicketPurchaseReport", 10, 2, "Fee: "+fee1);
	        report.outputReport("CompleteTicketPurchaseReport", 11, 2, "Date: "+date);
	        report.outputReport("CompleteTicketPurchaseReport", 12, 2,"Status:"+status);
	        report.outputReport("CompleteTicketPurchaseReport", 13, 2, "Description: "+description1);
	        report.outputReport("CompleteTicketPurchaseReport", 15, 2, "PlanID: "+planID);
	        report.outputReport("CompleteTicketPurchaseReport", 16, 2, "Item_Name1: "+itemName);
	        report.outputReport("CompleteTicketPurchaseReport", 17, 2, "City: "+city);
	        report.outputReport("CompleteTicketPurchaseReport", 18, 2, "Type: "+type);
	        report.outputReport("CompleteTicketPurchaseReport", 19, 2, "Finance Code: "+fCode);
	        report.outputReport("CompleteTicketPurchaseReport", 22, 2, DietryRestrictionPage.referLink);
	        
	        if(stripeLoginStatus)
			{
	        	report.outputReport("CompleteTicketPurchaseReport", 2, 3,TestConstants.RESULT_PASS);
			}else if(!stripeLoginStatus)
			{
				report.outputReport("CompleteTicketPurchaseReport", 2, 3,TestConstants.RESULT_FAIL);
			}
	        if(!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
			{
				report.outputReport("CompleteTicketPurchaseReport", 42, 3,TestConstants.RESULT_FAIL);
			}
	        if(memberFinanceCodeStatus)
			{
	        	report.outputReport("CompleteTicketPurchaseReport", 8, 3,TestConstants.RESULT_PASS);
			}else if(!memberFinanceCodeStatus)
			{
				report.outputReport("CompleteTicketPurchaseReport", 8, 3,TestConstants.RESULT_FAIL);
			}
	        
	        if(MemberEventTicketPurchasePage.soldOutStatus||!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess){
	 			for(i=28;i<58;i++)
	 			{
	 			report.outputReport("CompleteTicketPurchaseReport", i, 2, "Ticket Sold Out");
	 			}
	 		}
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	        report.outputReport("CompleteTicketPurchaseReport", 8, 3,TestConstants.RESULT_FAIL);
	    }
	}
	/**
	  	* Test Case for Writing Paid Member Membership details into Excel Report
	  	* Input: WebDriver
	  	* Output: Void
	    * @throws InvalidFormatException 
	  	* @throws IOException 
	 */	
	public void paidMemberStripeMembershipDetails(WebDriver driver) throws InvalidFormatException 
	{
		try
	    {
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        report.outputReport("PaidMemberReport", 0, 2, TestConstants.USER_TYPE_MEMBER);
	        //report.outputReport("PaidMemberReport", 1, 2, PaidMemberSignUpPage1.city);
	        report.outputReport("PaidMemberReport", 6, 2, "Description: "+description);
	        report.outputReport("PaidMemberReport", 5, 2, "Email: "+memberEmail);
	        report.outputReport("PaidMemberReport", 4, 2, "Created Date: "+createdDate);
	        report.outputReport("PaidMemberReport", 3, 2, "ID: "+id);
	        report.outputReport("PaidMemberReport", 9, 2, "Amount: "+amount);
	        report.outputReport("PaidMemberReport", 10, 2, "Fee: "+fee1);
	        report.outputReport("PaidMemberReport", 11, 2, "Date: "+date);
	        report.outputReport("PaidMemberReport", 12, 2,"Status:"+status);
	        report.outputReport("PaidMemberReport", 13, 2, "Description: "+description1);
	        report.outputReport("PaidMemberReport", 15, 2, "PlanID: "+planID);
	        report.outputReport("PaidMemberReport", 16, 2, "Item_Name1: "+itemName);
	        report.outputReport("PaidMemberReport", 17, 2, "City: "+city);
	        report.outputReport("PaidMemberReport", 18, 2, "Type: "+type);
	        report.outputReport("PaidMemberReport", 19, 2, "Finance Code: "+fCode);
	        //report.outputReport("PaidMemberReport", 22, 2, PaidMemberSignUpPage1.referLink);
	        
	        if(stripeLoginStatus)
			{
	        	report.outputReport("PaidMemberReport", 2, 3,TestConstants.RESULT_PASS);
			}else if(!stripeLoginStatus)
			{
				report.outputReport("PaidMemberReport", 2, 3,TestConstants.RESULT_FAIL);
			}
	        
	        if(memberFinanceCodeStatus)
			{
	        	report.outputReport("PaidMemberReport", 8, 3,TestConstants.RESULT_PASS);
			}else if(!memberFinanceCodeStatus)
			{
				report.outputReport("PaidMemberReport", 8, 3,TestConstants.RESULT_FAIL);
			}
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	        report.outputReport("PaidMemberReport", 8, 3,TestConstants.RESULT_FAIL);
	    }
	}
	
	 
	 /**
	  	* Test Case for Writing Individual Ticket Purchase Membership details into Excel Report
	  	* Input: WebDriver
	  	* Output: Void
	    * @throws InvalidFormatException 
	  	* @throws IOException 
	 */	
	public void individualTicketPurchaseStripeMembershipDetails(WebDriver driver) throws InvalidFormatException 
	{
		try
	    {
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        report.outputReport("IndividualTicketPurchaseReport", 0, 2, TestConstants.USER_TYPE_MEMBER);
	        report.outputReport("IndividualTicketPurchaseReport", 1, 2, PaidMemberTicketPurchasePage.memberCityName);
	        report.outputReport("IndividualTicketPurchaseReport", 6, 2, "Description: "+description);
	        report.outputReport("IndividualTicketPurchaseReport", 5, 2, "Email: "+memberEmail);
	        report.outputReport("IndividualTicketPurchaseReport", 4, 2, "Created Date: "+createdDate);
	        report.outputReport("IndividualTicketPurchaseReport", 3, 2, "ID: "+id);
	        report.outputReport("IndividualTicketPurchaseReport", 9, 2, "Amount: "+amount);
	        report.outputReport("IndividualTicketPurchaseReport", 10, 2, "Fee: "+fee1);
	        report.outputReport("IndividualTicketPurchaseReport", 11, 2, "Date: "+date);
	        report.outputReport("IndividualTicketPurchaseReport", 12, 2,"Status:"+status);
	        report.outputReport("IndividualTicketPurchaseReport", 13, 2, "Description: "+description1);
	        report.outputReport("IndividualTicketPurchaseReport", 15, 2, "PlanID: "+planID);
	        report.outputReport("IndividualTicketPurchaseReport", 16, 2, "Item_Name1: "+itemName);
	        report.outputReport("IndividualTicketPurchaseReport", 17, 2, "City: "+city);
	        report.outputReport("IndividualTicketPurchaseReport", 18, 2, "Type: "+type);
	        report.outputReport("IndividualTicketPurchaseReport", 19, 2, "Finance Code: "+fCode);
	        
	        if(stripeLoginStatus)
			{
	        	report.outputReport("IndividualTicketPurchaseReport", 2, 3,TestConstants.RESULT_PASS);
			}else if(!stripeLoginStatus)
			{
				report.outputReport("IndividualTicketPurchaseReport", 2, 3,TestConstants.RESULT_FAIL);
			}
	        if(!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
			{
				report.outputReport("IndividualTicketPurchaseReport", 42, 3,TestConstants.RESULT_FAIL);
			}
	        if(memberFinanceCodeStatus)
			{
	        	report.outputReport("IndividualTicketPurchaseReport", 8, 3,TestConstants.RESULT_PASS);
			}else if(!memberFinanceCodeStatus)
			{
				report.outputReport("IndividualTicketPurchaseReport", 8, 3,TestConstants.RESULT_FAIL);
			}
	        
	        if(MemberEventTicketPurchasePage.soldOutStatus||!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess){
	 			for(i=28;i<58;i++)
	 			{
	 			report.outputReport("IndividualTicketPurchaseReport", i, 2, "Ticket Sold Out");
	 			}
	 		}
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	        report.outputReport("IndividualTicketPurchaseReport", 8, 3,TestConstants.RESULT_FAIL);
	        report.outputReport("IndividualTicketPurchaseReport", 42, 3,TestConstants.RESULT_FAIL);
	    }
	}
	/**
  	* Test Case for Writing Complete Ticket Purchase Stripe Dietary and allergies details into Excel Report
  	* Input: WebDriver,Email
  	* Output: Void
  	* @throws IOException 
 */	
	public void completeTicketPurchaseStripeDietaryDetails(WebDriver driver) 
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			report.outputReport("CompleteTicketPurchaseReport", 20, 2, "DIETARY: "+DietryRestrictionPage.dietaryRestrictions);
			report.outputReport("CompleteTicketPurchaseReport", 21, 2, "ALLERGIES: "+DietryRestrictionPage.allergies);
		 }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	}	
	/**
  	* Test Case for Writing Complete Ticket Purchase Stripe Ticket details into Excel Report
  		* Input: WebDriver
  		* Output: Void
	    * @throws InvalidFormatException 
  		* @throws IOException 
    */	
	public void completeTicketPurchaseStripeTicketDetails(WebDriver driver) throws InvalidFormatException 
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			report.outputReport("CompleteTicketPurchaseReport", 23, 2, "EVENTS: "+MemberEventTicketPurchasePage.multipleEventNames);
			report.outputReport("CompleteTicketPurchaseReport", 24, 2, "DATE: "+MemberEventTicketPurchasePage.seatingDate);
	 		report.outputReport("CompleteTicketPurchaseReport", 25, 2, "TIME: "+MemberEventTicketPurchasePage.seatingTime);
	 		report.outputReport("CompleteTicketPurchaseReport", 27, 2, "QUANTITY: "+MemberEventTicketPurchasePage.purchaseQuantity);
			report.outputReport("CompleteTicketPurchaseReport", 28, 2, "EVENT: "+MemberEventTicketPurchasePage.eventNameText);
	 		report.outputReport("CompleteTicketPurchaseReport", 29, 2, "CITY: "+MemberEventTicketPurchasePage.cityText);
	 		report.outputReport("CompleteTicketPurchaseReport", 30, 2, "DATE: "+MemberEventTicketPurchasePage.dateText);
	 		report.outputReport("CompleteTicketPurchaseReport", 31, 2, "TIME: "+MemberEventTicketPurchasePage.timeText);
	 		report.outputReport("CompleteTicketPurchaseReport", 32, 2, "TICKETS: "+MemberEventTicketPurchasePage.ticketQuantityText);
	 		report.outputReport("CompleteTicketPurchaseReport", 33, 2, "SUBTOTAL: "+MemberEventTicketPurchasePage.subTotalText);
	 		report.outputReport("CompleteTicketPurchaseReport", 34, 2, "TAX: "+MemberEventTicketPurchasePage.taxText);
	 		report.outputReport("CompleteTicketPurchaseReport", 35, 2, "CREDITS: "+MemberEventTicketPurchasePage.creditsText);
	 		report.outputReport("CompleteTicketPurchaseReport", 36, 2, "ORDERTOTAL: "+MemberEventTicketPurchasePage.orderTotalText);
	 		report.outputReport("CompleteTicketPurchaseReport", 37, 2, "EVENT: "+MemberEventTicketPurchasePage.eventNameAfterPayment);
	 		report.outputReport("CompleteTicketPurchaseReport", 38, 2, "CITY: "+MemberEventTicketPurchasePage.cityAfterPayment);
	 		report.outputReport("CompleteTicketPurchaseReport", 39, 2, "DATE: "+MemberEventTicketPurchasePage.dateAfterPayment);
	 		report.outputReport("CompleteTicketPurchaseReport", 40, 2, "TIME: "+MemberEventTicketPurchasePage.timeAfterPayment);
	 		report.outputReport("CompleteTicketPurchaseReport", 41, 2, "TICKETS: "+MemberEventTicketPurchasePage.quantityAfterPayment);
	 		report.outputReport("CompleteTicketPurchaseReport", 43, 2, "Amount: "+ticketAmount);
	 		report.outputReport("CompleteTicketPurchaseReport", 44, 2, "Fee: "+ticketFee);
	 		report.outputReport("CompleteTicketPurchaseReport", 45, 2, "Date: "+ticketDate);
	 		report.outputReport("CompleteTicketPurchaseReport", 46, 2, "Status: "+ticketStatus);
	 		report.outputReport("CompleteTicketPurchaseReport", 47, 2, "Description:"+ticketDescription);
	 		report.outputReport("CompleteTicketPurchaseReport", 49, 2, "Item_Name1: "+ticketItemName);
	 		report.outputReport("CompleteTicketPurchaseReport", 50, 2, "City: "+ticketCity);
	 		report.outputReport("CompleteTicketPurchaseReport", 51, 2, "Type: "+ticketType);
	 		report.outputReport("CompleteTicketPurchaseReport", 52, 2, "Finance Code: "+ticketFinance);
	 		report.outputReport("CompleteTicketPurchaseReport", 53, 2, "Discount Amount: "+ticketDiscountAmount);
	 		report.outputReport("CompleteTicketPurchaseReport", 54, 2, "Quantity: "+ticketQuantity);
	 		report.outputReport("CompleteTicketPurchaseReport", 55, 2, "Day: "+ticketDay);
	 		report.outputReport("CompleteTicketPurchaseReport", 56, 2, "Date: "+ticketDate1);
	 		report.outputReport("CompleteTicketPurchaseReport", 57, 2, "Total_Tax: "+ticketTotalTax);
	 		
	 		if(ticketPurchaseTotalTaxStatus)
			{
		       	report.outputReport("CompleteTicketPurchaseReport", 42, 3,TestConstants.RESULT_PASS);
			}else if(!ticketPurchaseTotalTaxStatus||!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
			{
				report.outputReport("CompleteTicketPurchaseReport", 42, 3,TestConstants.RESULT_FAIL);
			}
	 		if(MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
			{
				report.outputReport("CompleteTicketPurchaseReport", 37, 3,TestConstants.RESULT_PASS);
			}else if(!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
			{
				report.outputReport("CompleteTicketPurchaseReport", 37, 3,TestConstants.RESULT_FAIL);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			report.outputReport("CompleteTicketPurchaseReport", 42, 3,TestConstants.RESULT_FAIL);
		}
	}
	
	/**
  	* Test Case for Writing Individual Ticket Purchase Stripe Ticket details into Excel Report
  		* Input: WebDriver
  		* Output: Void
	 * @throws InvalidFormatException 
  		* @throws IOException 
    */	
	public void individualTicketPurchaseStripeTicketDetails(WebDriver driver) throws InvalidFormatException 
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			report.outputReport("IndividualTicketPurchaseReport", 23, 2, "EVENTS: "+MemberEventTicketPurchasePage.multipleEventNames);
			report.outputReport("IndividualTicketPurchaseReport", 24, 2, "DATE: "+PaidMemberTicketPurchasePage.seatingDate);
	 		report.outputReport("IndividualTicketPurchaseReport", 25, 2, "TIME: "+PaidMemberTicketPurchasePage.seatingTime);
	 		report.outputReport("IndividualTicketPurchaseReport", 27, 2, "QUANTITY: "+PaidMemberTicketPurchasePage.purchaseQuantity);
			report.outputReport("IndividualTicketPurchaseReport", 28, 2, "EVENT: "+MemberEventTicketPurchasePage.eventNameText);
	 		report.outputReport("IndividualTicketPurchaseReport", 29, 2, "CITY: "+MemberEventTicketPurchasePage.cityText);
	 		report.outputReport("IndividualTicketPurchaseReport", 30, 2, "DATE: "+MemberEventTicketPurchasePage.dateText);
	 		report.outputReport("IndividualTicketPurchaseReport", 31, 2, "TIME: "+MemberEventTicketPurchasePage.timeText);
	 		report.outputReport("IndividualTicketPurchaseReport", 32, 2, "TICKETS: "+MemberEventTicketPurchasePage.ticketQuantityText);
	 		report.outputReport("IndividualTicketPurchaseReport", 33, 2, "SUBTOTAL: "+MemberEventTicketPurchasePage.subTotalText);
	 		report.outputReport("IndividualTicketPurchaseReport", 34, 2, "TAX: "+MemberEventTicketPurchasePage.taxText);
	 		report.outputReport("IndividualTicketPurchaseReport", 35, 2, "CREDITS: "+MemberEventTicketPurchasePage.creditsText);
	 		report.outputReport("IndividualTicketPurchaseReport", 36, 2, "ORDERTOTAL: "+MemberEventTicketPurchasePage.orderTotalText);
	 		report.outputReport("IndividualTicketPurchaseReport", 37, 2, "EVENT: "+MemberEventTicketPurchasePage.eventNameAfterPayment);
	 		report.outputReport("IndividualTicketPurchaseReport", 38, 2, "CITY: "+MemberEventTicketPurchasePage.cityAfterPayment);
	 		report.outputReport("IndividualTicketPurchaseReport", 39, 2, "DATE: "+MemberEventTicketPurchasePage.dateAfterPayment);
	 		report.outputReport("IndividualTicketPurchaseReport", 40, 2, "TIME: "+MemberEventTicketPurchasePage.timeAfterPayment);
	 		report.outputReport("IndividualTicketPurchaseReport", 41, 2, "TICKETS: "+MemberEventTicketPurchasePage.quantityAfterPayment);
	 		report.outputReport("IndividualTicketPurchaseReport", 43, 2, "Amount: "+ticketAmount);
	 		report.outputReport("IndividualTicketPurchaseReport", 44, 2, "Fee: "+ticketFee);
	 		report.outputReport("IndividualTicketPurchaseReport", 45, 2, "Date: "+ticketDate);
	 		report.outputReport("IndividualTicketPurchaseReport", 46, 2, "Status: "+ticketStatus);
	 		report.outputReport("IndividualTicketPurchaseReport", 47, 2, "Description:"+ticketDescription);
	 		report.outputReport("IndividualTicketPurchaseReport", 49, 2, "Item_Name1: "+ticketItemName);
	 		report.outputReport("IndividualTicketPurchaseReport", 50, 2, "City: "+ticketCity);
	 		report.outputReport("IndividualTicketPurchaseReport", 51, 2, "Type: "+ticketType);
	 		report.outputReport("IndividualTicketPurchaseReport", 52, 2, "Finance Code: "+ticketFinance);
	 		report.outputReport("IndividualTicketPurchaseReport", 53, 2, "Discount Amount: "+ticketDiscountAmount);
	 		report.outputReport("IndividualTicketPurchaseReport", 54, 2, "Quantity: "+ticketQuantity);
	 		report.outputReport("IndividualTicketPurchaseReport", 55, 2, "Day: "+ticketDay);
	 		report.outputReport("IndividualTicketPurchaseReport", 56, 2, "Date: "+ticketDate1);
	 		report.outputReport("IndividualTicketPurchaseReport", 57, 2, "Total_Tax: "+ticketTotalTax);
	 		
	 		if(ticketPurchaseTotalTaxStatus)
			{
		       	report.outputReport("IndividualTicketPurchaseReport", 42, 3,TestConstants.RESULT_PASS);
			}else if(!ticketPurchaseTotalTaxStatus||!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
			{
				report.outputReport("IndividualTicketPurchaseReport", 42, 3,TestConstants.RESULT_FAIL);
			}
	 		if(MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
			{
				report.outputReport("IndividualTicketPurchaseReport", 37, 3,TestConstants.RESULT_PASS);
			}else if(!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
			{
				report.outputReport("IndividualTicketPurchaseReport", 37, 3,TestConstants.RESULT_FAIL);
			}
		}catch(Exception e)
		{
			report.outputReport("IndividualTicketPurchaseReport", 42, 3,TestConstants.RESULT_FAIL);
			e.printStackTrace();
		}
	}
	
	/**
		 * Test Case for Reading Ticket Purchase details in Stripe Page
		 * Input: WebDriver,loginEmail,loginPassword
		 * Output: Void
		 * @throws InterruptedException 
		 * @throws IOException 
	 */	
		
	 public Boolean stripeTicketPurchaseDetails(WebDriver driver,String email) throws InterruptedException {
	 {
	 	try
	 	{
	 		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 		//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	 		//driver.findElement(By.xpath("//div[@id='quicksearch-view']/div[1]/form[1]/div[1]/input")).click();
	 		
	 		if(searchTextFieldStatus)
	 		{
	 			Thread.sleep(5000);
	 			driver.findElement(By.xpath("//div[@id='quicksearch-view']/div[1]/form[1]/div[1]/input")).clear();
	 			System.out.println("after 3rd time clear");
		 		driver.findElement(By.xpath("//div[@id='quicksearch-view']/div[1]/form[1]/div[1]/input")).sendKeys(email);
		 		System.out.println("after 3rd time email");
		 		driver.findElement(By.xpath("//div[@id='quicksearch-view']/div[1]/form[1]/div[1]/input")).sendKeys(Keys.ENTER);
		 		System.out.println("after 3rd time enter");
		 		Thread.sleep(5000);
		 		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 		driver.findElement(By.xpath("//span[p[contains(text(),'baltimore event')]]/strong")).click();
		 		Thread.sleep(5000);
		        System.out.println("before getting 3rd value");
		     
		 		//Writing Membership Parameters into Excel File
		 		ticketAmount = driver.findElement(By.xpath("//span[@class='amount']")).getText();
		 		
		 		System.out.println("Amount is :"+ticketAmount); 
		     
		 		//fees
		 		ticketFee=driver.findElement(By.xpath("//span[@class='fees']")).getText();
		 		//report.outputReport("OutputReport", 43, 2, ticketFee);
		 		System.out.println("fees is :"+ticketFee); 
		     
		 		//date
		 		ticketDate=driver.findElement(By.xpath("//div[@class='container']/dl/dd[3]")).getText();
		 		//report.outputReport("OutputReport", 44, 2, ticketDate);
		 		System.out.println("date is :"+ticketDate); 
		     
		 		//Status
		 		ticketStatus=driver.findElement(By.xpath("//span[@class='status success']")).getText();
		 		//report.outputReport("OutputReport", 45, 2, ticketStatus);
		 		System.out.println("Status is :"+ticketStatus); 
		     	     
		 		//description
		 		ticketDescription=driver.findElement(By.xpath("//div[@class='container']/dl/dd[5]")).getText();
		 		//report.outputReport("OutputReport", 46, 2, ticketDescription);
		 		System.out.println("description is :"+ticketDescription);
		     
		 		//item_name1
		 		ticketItemName=driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[1]/td[2]")).getText();
		 		//report.outputReport("OutputReport", 48, 2, ticketItemName);
		 		System.out.println("Amount is :"+ticketItemName);
		     
		 		//City:
		 		ticketCity=driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[2]/td[2]")).getText();
		 		//report.outputReport("OutputReport", 49, 2, ticketCity);
		 		System.out.println("City: is :"+ticketCity);
		     
		 		//Type:
		 		ticketType=driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[3]/td[2]")).getText();
		 		//report.outputReport("OutputReport", 50, 2, ticketType);
		 		System.out.println("Type is :"+ticketType);
		     
		 		//Finance code:
		 		ticketFinance=driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[4]/td[2]")).getText();
		 		//report.outputReport("OutputReport", 51, 2, ticketFinance);
		 		System.out.println("Finance code is :"+ticketFinance);
		   
		 		//discount_amount:
		 		ticketDiscountAmount=driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[5]/td[2]")).getText();
		 		//report.outputReport("OutputReport", 52, 2, ticketDiscountAmount);
		 		System.out.println("discount_amount is :"+ticketDiscountAmount);
		 		
		 		//quantity:
		 		ticketQuantity=driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[6]/td[2]")).getText();
		 		//report.outputReport("OutputReport", 53, 2, ticketQuantity);
		 		System.out.println("quantity is :"+ticketQuantity);
		 		
		 		//day:
		 		ticketDay=driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[7]/td[2]")).getText();
		 		//report.outputReport("OutputReport", 54, 2, ticketDay);
		 		System.out.println("day is :"+ticketDay);
		     
		 		//date:
		 		ticketDate1=driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[8]/td[2]")).getText();
		 		//report.outputReport("OutputReport", 55, 2, ticketDate1);
		 		System.out.println("date is :"+ticketDate1);
		 		
		 		ticketPurchaseTotalTaxStatus = driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[9]/td[2]")).isDisplayed();
		 		
		 		//total_tax:
		 		ticketTotalTax=driver.findElement(By.xpath("//tbody[//td[@class='key']]/tr[9]/td[2]")).getText();
		 		//report.outputReport("OutputReport", 56, 2, ticketTotalTax);
		 		System.out.println("total_tax is :"+ticketTotalTax);
		 		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 		//driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		 		Thread.sleep(4000);
	 		}
	        return true;
	 	}
	 	  catch (Exception e)
	 	{
	 		e.printStackTrace();
	 		return false;
	 	}
	 }	
  }
	 
	/**
	 	* Test Case for log out from the Stripe Page
		* Input: WebDriver
		* Output: Void
		* @return 
	*/ 
	 public void stripeLogoutActions(WebDriver driver) 
	 {
		 try
		 {
		 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 	driver.findElement(By.xpath("//span[@class='account-name']")).click(); 
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//a[@class='logout']")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//Condition to Verify Logout from an application.
			boolean flag = driver.findElement(By.xpath("//h2[contains(text(),'Welcome Back!')]")).isDisplayed();
			if(flag)
			{
				log.info("Successfully logged out from Stripe Page.");
			}
		 }catch(Exception e)
		 {
		 	e.printStackTrace();
		 }
	}
}
	
	


