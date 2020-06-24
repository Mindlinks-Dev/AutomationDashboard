/**
 * @author: Basappa Hunsikatti
 * @Created Date :07/09/2015
 * @Updated Date :
 * @Comments This automation class will write Stripe details to Excel Report file.
 */
package com.dinnerLab.userPages.Helpers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;
import com.dinnerLab.util.ExcelReportStaticData;
import com.dinnerLab.util.OutputReport;
import com.pack.AutomateLoader;
import com.pack.Database;
import com.pack.MPHandler;
import com.pack.ScriptHandler;
import com.pack.StripeHandler;

public class WritingStripeDetailsToExcelReport 
{
	boolean present;
	Connection con = Database.getConnection();
	Statement stm=null;
	ResultSet rs=null;
	String isTestPassed="FAIL";
	public static String result;
	public static String comment;
	public boolean searchTextFieldStatus;
	//Row data
	public int i = 0;
	//1st column
	public int k=0;
	// 2nd column
	public int l=1;
	//Parameters(3rd) column
	public int j=2;
	// 4th column
	public int m=3;
		
	private static Logger log = Logger.getLogger(WritingStripeDetailsToExcelReport.class);
	//MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	
	//OutputReport report = new OutputReport();
	ExcelReportStaticData staticData = new ExcelReportStaticData();
	
	/*public boolean isTestElementPresent(WebDriver driver)
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
	}*/

	/**
	  	* Test Case for Writing Complete Ticket Purchase Membership details in Stripe Page into Excel Report
	  	* Input: WebDriver
	  	* Output: Void
	    * @throws InvalidFormatException 
	  	* @throws IOException 
	 */	
	public void completeTicketPurchaseStripeDetails(WebDriver driver) throws InvalidFormatException 
	{
		try
	    {
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        OutputReport report = new OutputReport();
	        
	        if(HybridFlowHelper.rowCount > 1)
			{
				System.out.println("running inside greater than 1");
				//i = report.getRowCount("PaidMemberReport");
				i = OutputReport.lastRowNumber;
				System.out.println("first condition123:"+i);
			}
			else
			{
				System.out.println("running inside less than one");
				i = 0;
				System.out.println("second condition123:"+i);
			}
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        report.outputReport("CompleteTicketPurchaseReport",i, k,"Sign Up");
	        report.outputReport("CompleteTicketPurchaseReport",i, l,"User Type");
	        report.outputReport("CompleteTicketPurchaseReport", i, j,TestConstants.USER_TYPE_MEMBER);
	        report.outputReport("CompleteTicketPurchaseReport", i, m,"Status (Pass/Fail)");
	        ++i;    
	        if(FreeMemberSignUpPage.signUpSuccess){
	        	
	        	report.outputReport("CompleteTicketPurchaseReport", i, m,TestConstants.RESULT_PASS);	
	        }
	        
	        if(!FreeMemberSignUpPage.signUpSuccess){
	        	
	        	report.outputReport("CompleteTicketPurchaseReport", i, m,TestConstants.RESULT_FAIL);	
	        }
	    	
	        report.outputReport("CompleteTicketPurchaseReport",i, l,"City");
	        report.outputReport("CompleteTicketPurchaseReport",i,j,HybridFlowHelper.city);
	        ++i;
	        report.outputReport("CompleteTicketPurchaseReport",i, k,"Purchase");
	        report.outputReport("CompleteTicketPurchaseReport",i, l,"Stripe Customer");
	        report.outputReport("CompleteTicketPurchaseReport",i,j,"Customer Details");
	        i++;
	        report.outputReport("CompleteTicketPurchaseReport",i,j,"ID: "+StripeHandler.id);
	        i++;
	        report.outputReport("CompleteTicketPurchaseReport",i,j, "Created Date: "+StripeHandler.createdDate);
	        i++;
	        report.outputReport("CompleteTicketPurchaseReport",i,j, "Email: "+StripeHandler.email);
	        i++;
	        report.outputReport("CompleteTicketPurchaseReport",i,j, "Description:"+StripeHandler.memberDescription);
	        ++i;
	        report.outputReport("CompleteTicketPurchaseReport",i, k,"Stripe");
	        report.outputReport("CompleteTicketPurchaseReport",i, l,"Membership Parameters");
	        report.outputReport("CompleteTicketPurchaseReport",i, j, "Payment Details");
	        i++;
	        report.outputReport("CompleteTicketPurchaseReport",i, j, "Amount: "+MPHandler.amount);
	        i++;
	        report.outputReport("CompleteTicketPurchaseReport",i, j, "Fee: "+MPHandler.merchantFee);
	        i++;
	        report.outputReport("CompleteTicketPurchaseReport",i, j, "Transaction Date: "+MPHandler.transactionDate);
	        i++;
	        report.outputReport("CompleteTicketPurchaseReport",i, j,"Status:"+StripeHandler.status);
	        i++;
	        report.outputReport("CompleteTicketPurchaseReport",i, j, "Description: "+StripeHandler.description);
	        i++;
	        report.outputReport("CompleteTicketPurchaseReport",i, j, "Metadata");
	        i++;
	        report.outputReport("CompleteTicketPurchaseReport",i, j, "PlanID: "+MPHandler.planId);
	        i++;
	        report.outputReport("CompleteTicketPurchaseReport",i, j, "Item_Name1: "+StripeHandler.item_Name1);
	        i++;
	        report.outputReport("CompleteTicketPurchaseReport",i, j, "City: "+StripeHandler.city);
	        i++;
	        report.outputReport("CompleteTicketPurchaseReport",i, j, "Type: "+StripeHandler.type);
	        i++;
	        report.outputReport("CompleteTicketPurchaseReport",i, j, "Finance Code: "+StripeHandler.financeCode);
	        
	        System.out.println("Dietary:"+DietryRestrictionPage.dietaryRestrictions);
	        System.out.println("Allergies:"+DietryRestrictionPage.allergies);
	        if(DietryRestrictionPage.allergiesStatus)
			{
        		++i;
        		report.outputReport("CompleteTicketPurchaseReport",i, k,"Welcome");
    	        report.outputReport("CompleteTicketPurchaseReport",i, l,"Dietary submitted");
        		report.outputReport("CompleteTicketPurchaseReport", i, j, "DIETARY: "+DietryRestrictionPage.dietaryRestrictions);
        		++i;
        		report.outputReport("CompleteTicketPurchaseReport",i, l,"Allergies submitted");
        		report.outputReport("CompleteTicketPurchaseReport", i, j, "ALLERGIES: "+DietryRestrictionPage.allergies);
			}
	        
	        ++i;
	        System.out.println("Invite Link:"+DietryRestrictionPage.referLink);
	        report.outputReport("CompleteTicketPurchaseReport",i, l,"Invite Link");
	        report.outputReport("CompleteTicketPurchaseReport",i, j, "Invite Link: "+DietryRestrictionPage.referLink);
	       	        
	        System.out.println("Complete soldout status:"+!MemberEventTicketPurchasePage.soldOutStatus);
	        System.out.println("Complete ticket purchase status:"+!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess);
	        
	        //Condition to check ticket purchase success
	        if(HybridFlowHelper.ticketPurchaseStatus)
			{
	        	++i;
	        	report.outputReport("CompleteTicketPurchaseReport",i, k,"MultiEventView");
	        	report.outputReport("CompleteTicketPurchaseReport",i, l,"City");
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "EVENTS: "+MemberEventTicketPurchasePage.multipleEventNames);
	        	++i;
	        	report.outputReport("CompleteTicketPurchaseReport",i, k,"SingleEventView");
	        	report.outputReport("CompleteTicketPurchaseReport",i, l,"Date displayed");
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "DATE: "+MemberEventTicketPurchasePage.seatingDate);
	        	++i;
	        	report.outputReport("CompleteTicketPurchaseReport",i, l,"Time displayed");
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "TIME: "+MemberEventTicketPurchasePage.seatingTime);
	        	++i;
	        	report.outputReport("CompleteTicketPurchaseReport",i, l,"Button State");
	        	report.outputReport("CompleteTicketPurchaseReport", i, j,TestConstants.BUTTON_STATE_PURCHASE);
	   	        ++i;
	   	        report.outputReport("CompleteTicketPurchaseReport",i, l,"Quantity");
	   	        report.outputReport("CompleteTicketPurchaseReport", i, j, "QUANTITY: "+MemberEventTicketPurchasePage.purchaseQuantity);
	        	++i;
	        	report.outputReport("CompleteTicketPurchaseReport",i, k,"Order Summary");
	        	report.outputReport("CompleteTicketPurchaseReport",i, l,"All fields shown");
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "EVENT: "+MemberEventTicketPurchasePage.eventNameText);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "CITY: "+MemberEventTicketPurchasePage.cityText);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "DATE: "+MemberEventTicketPurchasePage.dateText);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "TIME: "+MemberEventTicketPurchasePage.timeText);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "TICKETS: "+MemberEventTicketPurchasePage.ticketQuantityText);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "SUBTOTAL: "+MemberEventTicketPurchasePage.subTotalText);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "TAX: "+MemberEventTicketPurchasePage.taxText);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "CREDITS: "+MemberEventTicketPurchasePage.creditsText);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "ORDERTOTAL: "+MemberEventTicketPurchasePage.orderTotalText);
	        	++i;
	        	report.outputReport("CompleteTicketPurchaseReport",i, k,"Success");
	        	report.outputReport("CompleteTicketPurchaseReport",i, l,"All fields shown");
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "EVENT: "+MemberEventTicketPurchasePage.eventNameAfterPayment);
	        	
	        	if(MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
				{
					report.outputReport("CompleteTicketPurchaseReport", i, m,TestConstants.RESULT_PASS);
				}else if(!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
				{
					report.outputReport("CompleteTicketPurchaseReport", i, m,TestConstants.RESULT_FAIL);
				}
	        	
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "CITY: "+MemberEventTicketPurchasePage.cityAfterPayment);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "DATE: "+MemberEventTicketPurchasePage.dateAfterPayment);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "TIME: "+MemberEventTicketPurchasePage.timeAfterPayment);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "TICKETS: "+MemberEventTicketPurchasePage.quantityAfterPayment);
	        	++i;
	        	report.outputReport("CompleteTicketPurchaseReport",i, k,"Stripe");
	        	report.outputReport("CompleteTicketPurchaseReport",i, l,"Ticket Parameters");
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "Payment Details");
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "Amount: "+MPHandler.amount);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j,"Fee: "+MPHandler.merchantFee);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "Transaction Date: "+MPHandler.transactionDate);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "Status: "+StripeHandler.status);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "Description:"+StripeHandler.description);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "Metadata");
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "Item_Name1: "+StripeHandler.item_Name1);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "City: "+StripeHandler.city);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "Type: "+StripeHandler.type);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "Finance Code: "+StripeHandler.financeCode);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "Discount Amount: "+StripeHandler.discountAmount);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "Quantity: "+StripeHandler.quantity);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "Day: "+StripeHandler.day);
	        	i++;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "Event Date: "+StripeHandler.date);
	        	++i;
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "Total_Tax: "+StripeHandler.totalTax);
			}
	        
	        if(!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess && !HybridFlowHelper.dietarySuccess)
			{
	        	++i;
         		report.outputReport("CompleteTicketPurchaseReport",i, l,"Button State");
	        	report.outputReport("CompleteTicketPurchaseReport", i, j, "BUTTON STATE:"+TestConstants.BUTTON_STATE_SOLD_OUT);
			}
	         
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	}
	/**
	  	* Test Case for Writing Paid Member Membership details into Excel Report
	  	* Input: WebDriver
	  	* Output: Void
	    * @throws InvalidFormatException 
	  	* @throws IOException 
	*/	
	public void paidMemberStripeDetails(WebDriver driver) throws InvalidFormatException 
	{
		try
	    {
	        //int i = report.getRowCount("PaidMemberReport");
			
			System.out.println("inside paidMemberStripeDetails ");
			/*if(PaidMemberSignUpPage.rowCount  > 1)
			{
				System.out.println("running inside greater than 1");
				//i = report.getRowCount("PaidMemberReport");
				i = OutputReport.lastRowNumber;
				System.out.println("first condition:"+i);
			}
			else
			{
				System.out.println("running inside less than one");
				i = 0;
				System.out.println("second condition:"+i);
			}
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			report.outputReport("PaidMemberReport",i, k,"Sign Up");
			report.outputReport("PaidMemberReport",i, l,"User Type");
			report.outputReport("PaidMemberReport", i, j,TestConstants.USER_TYPE_MEMBER);
			report.outputReport("PaidMemberReport", i, m,"Status (Pass/Fail)");
	    	++i;
	        report.outputReport("PaidMemberReport",i, l,"City");
	        report.outputReport("PaidMemberReport",i, j,PaidMemberSignUpPage.city);
	        if(PaidMemberSignUpPage.signUpStatus)
			{
				report.outputReport("PaidMemberReport", i, m,TestConstants.RESULT_PASS);	
			}
	        
			/*if(!PaidMemberSignUpPage.signUpStatus)
			{
	         	report.outputReport("PaidMemberReport", i, m,TestConstants.RESULT_FAIL);	
			}*/
			OutputReport report = new OutputReport();
			
			System.out.println("i value before purchase Link123:"+i);
	        ++i;
	        report.outputReport("PaidMemberReport",i, k,"Purchase");
	        report.outputReport("PaidMemberReport",i, l,"Stripe Customer");
	        report.outputReport("PaidMemberReport",i, j,"Customer Details");
	        System.out.println("i value after purchase Link123:"+i);
	        ++i;
	        report.outputReport("PaidMemberReport",i, j,"ID: "+StripeHandler.id);
	        ++i;
	        report.outputReport("PaidMemberReport",i, j, "Created Date: "+StripeHandler.createdDate);
	        ++i;
	        report.outputReport("PaidMemberReport",i, j, "Email: "+StripeHandler.email);
	        ++i;
	        report.outputReport("PaidMemberReport",i, j, "Description:"+StripeHandler.memberDescription);
	        ++i;
	        report.outputReport("PaidMemberReport",i, j, "Payment Details");
	        report.outputReport("PaidMemberReport",i, k,"Stripe");
	        report.outputReport("PaidMemberReport",i, l,"Membership Parameters");
	        ++i;
	        report.outputReport("PaidMemberReport",i, j, "Amount: "+MPHandler.amount);
	        ++i;
	        report.outputReport("PaidMemberReport",i, j, "Fee: "+MPHandler.merchantFee);
	        ++i;
	        report.outputReport("PaidMemberReport",i, j, "Transaction Date: "+MPHandler.transactionDate);
	        ++i;
	        report.outputReport("PaidMemberReport",i, j,"Status:"+StripeHandler.status);
	        ++i;
	        report.outputReport("PaidMemberReport",i, j, "Description: "+StripeHandler.description);
	        ++i;
	        report.outputReport("PaidMemberReport",i, j, "Metadata");
	        ++i;
	        report.outputReport("PaidMemberReport",i, j, "PlanID: "+MPHandler.planId);
	        ++i;
	        report.outputReport("PaidMemberReport",i, j, "Item_Name1: "+StripeHandler.item_Name1);
	        ++i;
	        report.outputReport("PaidMemberReport",i, j, "City: "+StripeHandler.city);
	        ++i;
	        report.outputReport("PaidMemberReport",i, j, "Type: "+StripeHandler.type);
	        ++i;
	        report.outputReport("PaidMemberReport",i, j, "Finance Code: "+StripeHandler.financeCode);
	        System.out.println("i value before Invite Link123:"+i);
	        ++i;
	        report.outputReport("PaidMemberReport",i, l,"Invite Link");
	        //report.outputReport("PaidMemberReport",i, j,PaidMemberSignUpPage1.referLink);
	        ++i;
	        System.out.println("i value after Invite Link123:"+i);
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	}
	
	public void excelReportStaticDataActions(WebDriver driver) throws InvalidFormatException 
	{
		try
		{
			/*if(PaidMemberSignUpPage1.rowCount  > 1)
			{
				System.out.println("running inside greater than 1");
				//i = report.getRowCount("PaidMemberReport");
				i = OutputReport.lastRowNumber;
				System.out.println("first condition:"+i);
			}
			else
			{
				System.out.println("running inside less than one");
				i = 0;
				System.out.println("second condition:"+i);
			}
				System.out.println("K value:"+k);
				System.out.println("L value:"+l);
				report.outputReport("PaidMemberReport",i, k,"Sign Up");
				report.outputReport("PaidMemberReport",i, l,"User Type");
	   
				/*if(PaidMemberSignUpPage.signUpStatus)
				{
        	  		report.outputReport("PaidMemberReport", i, m,TestConstants.RESULT_PASS);	
        		}
        	
        	if(!PaidMemberSignUpPage.signUpStatus)
        	{
        	     report.outputReport("PaidMemberReport", i, m,TestConstants.RESULT_FAIL);	
        	}*/
				
		 }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	}
	/**
	  	* Test Case for Writing Individual Ticket Purchase Membership details into Excel Report
	  	* Input: WebDriver
	  	* Output: Void
	    * @throws InvalidFormatException 
	  	* @throws IOException 
	 */	
	public void individualTicketPurchaseStripeDetails(WebDriver driver) throws InvalidFormatException 
	{
		try
	    {
			OutputReport report = new OutputReport();
			
			if(PaidMemberTicketPurchasePage.rowCount > 1)
			{
				System.out.println("running inside greater than 1");
				//i = report.getRowCount("PaidMemberReport");
				i = OutputReport.lastRowNumber;
				System.out.println("first condition123:"+i);
			}
			else
			{
				System.out.println("running inside less than one");
				i = 0;
				System.out.println("second condition123:"+i);
			}
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        
	        report.outputReport("IndividualTicketPurchaseReport",i, k,"Log In");
	        report.outputReport("IndividualTicketPurchaseReport",i, l,"User Type");
	        report.outputReport("IndividualTicketPurchaseReport", i, j,TestConstants.USER_TYPE_MEMBER);
	        report.outputReport("IndividualTicketPurchaseReport", i, m,"Status (Pass/Fail)");
	        i++;    
	        report.outputReport("IndividualTicketPurchaseReport",i, l,"City");
	        report.outputReport("IndividualTicketPurchaseReport",i, j,PaidMemberTicketPurchasePage.memberCityName);
	        
	        MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
	        
	        if(loginLogout.loggedInStatus){
	        	
	        	report.outputReport("IndividualTicketPurchaseReport", i, m,TestConstants.RESULT_PASS);	
	        }
	        
	        if(!loginLogout.loggedInStatus){
	        	
	        	report.outputReport("IndividualTicketPurchaseReport", i, m,TestConstants.RESULT_FAIL);	
	        }
	        
	        ++i;
	        report.outputReport("IndividualTicketPurchaseReport",i, k,"Purchase");
	        report.outputReport("IndividualTicketPurchaseReport",i, l,"Stripe Customer");
	        report.outputReport("IndividualTicketPurchaseReport",i, j,"Customer Details");
	        i++;
	        report.outputReport("IndividualTicketPurchaseReport",i, j,"ID: "+StripeHandler.id);
	        i++;
	        report.outputReport("IndividualTicketPurchaseReport",i, j, "Created Date: "+StripeHandler.createdDate);
	        i++;
	        report.outputReport("IndividualTicketPurchaseReport",i, j, "Email: "+StripeHandler.email);
	        i++;
	        report.outputReport("IndividualTicketPurchaseReport",i, j, "Description:"+StripeHandler.memberDescription);
	        ++i;
	        report.outputReport("IndividualTicketPurchaseReport",i, k,"Stripe");
	        report.outputReport("IndividualTicketPurchaseReport",i, l,"Membership Parameters");
	        report.outputReport("IndividualTicketPurchaseReport",i, j, "Payment Details");
	        i++;
	        report.outputReport("IndividualTicketPurchaseReport",i, j, "Amount: "+MPHandler.amount);
	        i++;
	        report.outputReport("IndividualTicketPurchaseReport",i, j, "Fee: "+MPHandler.merchantFee);
	        i++;
	        report.outputReport("IndividualTicketPurchaseReport",i, j, "Transaction Date: "+MPHandler.transactionDate);
	        i++;
	        report.outputReport("IndividualTicketPurchaseReport",i, j,"Status:"+StripeHandler.status);
	        i++;
	        report.outputReport("IndividualTicketPurchaseReport",i, j, "Description: "+StripeHandler.description);
	        i++;
	        report.outputReport("IndividualTicketPurchaseReport",i, j, "Metadata");
	        i++;
	        report.outputReport("IndividualTicketPurchaseReport",i, j, "PlanID: "+MPHandler.planId);
	        i++;
	        report.outputReport("IndividualTicketPurchaseReport",i, j, "Item_Name1: "+StripeHandler.item_Name1);
	        i++;
	        report.outputReport("IndividualTicketPurchaseReport",i, j, "City: "+StripeHandler.city);
	        i++;
	        report.outputReport("IndividualTicketPurchaseReport",i, j, "Type: "+StripeHandler.type);
	        i++;
	        report.outputReport("IndividualTicketPurchaseReport",i, j, "Finance Code: "+StripeHandler.financeCode);
	       	        
	        System.out.println("soldout status:"+!MemberEventTicketPurchasePage.soldOutStatus);
	        System.out.println("ticket purchase status:"+!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess);
	        
	        //Condition to check ticket purchase success
	        if(MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
			{
	        	++i;
	        	report.outputReport("IndividualTicketPurchaseReport",i, k,"MultiEventView");
	        	report.outputReport("IndividualTicketPurchaseReport",i, l,"City");
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "EVENTS: "+MemberEventTicketPurchasePage.multipleEventNames);
	        	++i;
	        	report.outputReport("IndividualTicketPurchaseReport",i, k,"SingleEventView");
	        	report.outputReport("IndividualTicketPurchaseReport",i, l,"Date displayed");
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "DATE: "+PaidMemberTicketPurchasePage.seatingDate);
	        	++i;
	        	report.outputReport("IndividualTicketPurchaseReport",i, l,"Time displayed");
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "TIME: "+PaidMemberTicketPurchasePage.seatingTime);
	        	++i;
	        	report.outputReport("IndividualTicketPurchaseReport",i, l,"Button State");
	        	report.outputReport("IndividualTicketPurchaseReport", i, j,TestConstants.BUTTON_STATE_PURCHASE);
	 			++i;
	   	        report.outputReport("IndividualTicketPurchaseReport",i, l,"Quantity");
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "QUANTITY: "+PaidMemberTicketPurchasePage.purchaseQuantity);
	        	++i;
	        	report.outputReport("IndividualTicketPurchaseReport",i, k,"Order Summary");
	        	report.outputReport("IndividualTicketPurchaseReport",i, l,"All fields shown");
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "EVENT: "+MemberEventTicketPurchasePage.eventNameText);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "CITY: "+MemberEventTicketPurchasePage.cityText);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "DATE: "+MemberEventTicketPurchasePage.dateText);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "TIME: "+MemberEventTicketPurchasePage.timeText);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "TICKETS: "+MemberEventTicketPurchasePage.ticketQuantityText);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "SUBTOTAL: "+MemberEventTicketPurchasePage.subTotalText);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "TAX: "+MemberEventTicketPurchasePage.taxText);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "CREDITS: "+MemberEventTicketPurchasePage.creditsText);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "ORDERTOTAL: "+MemberEventTicketPurchasePage.orderTotalText);
	        	++i;
	        	report.outputReport("IndividualTicketPurchaseReport",i, k,"Success");
	        	report.outputReport("IndividualTicketPurchaseReport",i, l,"All fields shown");
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "EVENT: "+MemberEventTicketPurchasePage.eventNameAfterPayment);
	        	
	        	if(MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
				{
					report.outputReport("IndividualTicketPurchaseReport", i, m,TestConstants.RESULT_PASS);
				}else if(!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
				{
					report.outputReport("IndividualTicketPurchaseReport", i, m,TestConstants.RESULT_FAIL);
				}
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "CITY: "+MemberEventTicketPurchasePage.cityAfterPayment);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "DATE: "+MemberEventTicketPurchasePage.dateAfterPayment);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "TIME: "+MemberEventTicketPurchasePage.timeAfterPayment);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "TICKETS: "+MemberEventTicketPurchasePage.quantityAfterPayment);
	        	++i;
	        	report.outputReport("IndividualTicketPurchaseReport",i, k,"Stripe");
	        	report.outputReport("IndividualTicketPurchaseReport",i, l,"Ticket Parameters");
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "Payment Details");
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "Amount: "+MPHandler.amount);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j,"Fee: "+MPHandler.merchantFee);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "Transaction Date: "+MPHandler.transactionDate);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "Status: "+StripeHandler.status);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "Description:"+StripeHandler.description);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "Metadata");
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "Item_Name1: "+StripeHandler.item_Name1);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "City: "+StripeHandler.city);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "Type: "+StripeHandler.type);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "Finance Code: "+StripeHandler.financeCode);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "Discount Amount: "+StripeHandler.discountAmount);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "Quantity: "+StripeHandler.quantity);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "Day: "+StripeHandler.day);
	        	i++;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "Event Date: "+StripeHandler.date);
	        	++i;
	        	report.outputReport("IndividualTicketPurchaseReport", i, j, "Total_Tax: "+StripeHandler.totalTax);
	        }
	        
	        if(!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
 			{
         		++i;
         		report.outputReport("IndividualTicketPurchaseReport",i, l,"Button State");
	        	report.outputReport("IndividualTicketPurchaseReport", i, j,TestConstants.BUTTON_STATE_SOLD_OUT);
 			}
	         
	        /*			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        report.outputReport("IndividualTicketPurchaseReport", 0, 2, TestConstants.USER_TYPE_MEMBER);
	        report.outputReport("IndividualTicketPurchaseReport", 1, 2, IndividualTicketPurchasePage.memberCityName);
	        report.outputReport("IndividualTicketPurchaseReport", 6, 2, "Description: "+StripeHandler.memberDescription);
	        report.outputReport("IndividualTicketPurchaseReport", 5, 2, "Email: "+StripeHandler.email);
	        report.outputReport("IndividualTicketPurchaseReport", 4, 2, "Created Date: "+StripeHandler.createdDate);
	        report.outputReport("IndividualTicketPurchaseReport", 3, 2, "ID: "+StripeHandler.id);
	        report.outputReport("IndividualTicketPurchaseReport", 9, 2, "Amount: "+MPHandler.amount);
	        report.outputReport("IndividualTicketPurchaseReport", 10, 2, "Fee: "+MPHandler.merchantFee);
	        report.outputReport("IndividualTicketPurchaseReport", 11, 2, "Transaction Date: "+MPHandler.transactionDate);
	        report.outputReport("IndividualTicketPurchaseReport", 12, 2,"Status:"+StripeHandler.status);
	        report.outputReport("IndividualTicketPurchaseReport", 13, 2, "Description: "+StripeHandler.description);
	        report.outputReport("IndividualTicketPurchaseReport", 15, 2, "PlanID: "+MPHandler.planId);
	        report.outputReport("IndividualTicketPurchaseReport", 16, 2, "Item_Name1: "+StripeHandler.item_Name1);
	        report.outputReport("IndividualTicketPurchaseReport", 17, 2, "City: "+StripeHandler.city);
	        report.outputReport("IndividualTicketPurchaseReport", 18, 2, "Type: "+StripeHandler.type);
	        report.outputReport("IndividualTicketPurchaseReport", 19, 2, "Finance Code: "+StripeHandler.financeCode);*/
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
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
			OutputReport report = new OutputReport();
			
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
			OutputReport report = new OutputReport();
			
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
	 		report.outputReport("CompleteTicketPurchaseReport", 42, 2, "Payment Details");
	 		report.outputReport("CompleteTicketPurchaseReport", 43, 2, "Amount: "+MPHandler.amount);
	 		report.outputReport("CompleteTicketPurchaseReport", 44, 2, "Fee: "+MPHandler.merchantFee);
	 		report.outputReport("CompleteTicketPurchaseReport", 45, 2, "Transaction Date: "+MPHandler.transactionDate);
	 		report.outputReport("CompleteTicketPurchaseReport", 46, 2, "Status: "+StripeHandler.status);
	 		report.outputReport("CompleteTicketPurchaseReport", 47, 2, "Description:"+StripeHandler.description);
	 		report.outputReport("CompleteTicketPurchaseReport", 48, 2, "Metadata");
	 		report.outputReport("CompleteTicketPurchaseReport", 49, 2, "Item_Name1: "+StripeHandler.item_Name1);
	 		report.outputReport("CompleteTicketPurchaseReport", 50, 2, "City: "+StripeHandler.city);
	 		report.outputReport("CompleteTicketPurchaseReport", 51, 2, "Type: "+StripeHandler.type);
	 		report.outputReport("CompleteTicketPurchaseReport", 52, 2, "Finance Code: "+StripeHandler.financeCode);
	 		report.outputReport("CompleteTicketPurchaseReport", 53, 2, "Discount Amount: "+StripeHandler.discountAmount);
	 		report.outputReport("CompleteTicketPurchaseReport", 54, 2, "Quantity: "+StripeHandler.quantity);
	 		report.outputReport("CompleteTicketPurchaseReport", 55, 2, "Day: "+StripeHandler.day);
	 		report.outputReport("CompleteTicketPurchaseReport", 56, 2, "Event Date: "+StripeHandler.date);
	 		report.outputReport("CompleteTicketPurchaseReport", 57, 2, "Total_Tax: "+StripeHandler.totalTax);
	 		
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
			//report.outputReport("CompleteTicketPurchaseReport", 42, 3,TestConstants.RESULT_FAIL);
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
			OutputReport report = new OutputReport();
			
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
	 		report.outputReport("IndividualTicketPurchaseReport", 42, 2, "Payment Details");
	 		report.outputReport("IndividualTicketPurchaseReport", 43, 2, "Amount: "+MPHandler.amount);
	 		report.outputReport("IndividualTicketPurchaseReport", 44, 2, "Fee: "+MPHandler.merchantFee);
	 		report.outputReport("IndividualTicketPurchaseReport", 45, 2, "Transaction Date: "+MPHandler.transactionDate);
	 		report.outputReport("IndividualTicketPurchaseReport", 46, 2, "Status: "+StripeHandler.status);
	 		report.outputReport("IndividualTicketPurchaseReport", 47, 2, "Description:"+StripeHandler.description);
	 		report.outputReport("IndividualTicketPurchaseReport", 48, 2, "Metadata");
	 		report.outputReport("IndividualTicketPurchaseReport", 49, 2, "Item_Name1: "+StripeHandler.item_Name1);
	 		report.outputReport("IndividualTicketPurchaseReport", 50, 2, "City: "+StripeHandler.city);
	 		report.outputReport("IndividualTicketPurchaseReport", 51, 2, "Type: "+StripeHandler.type);
	 		report.outputReport("IndividualTicketPurchaseReport", 52, 2, "Finance Code: "+StripeHandler.financeCode);
	 		report.outputReport("IndividualTicketPurchaseReport", 53, 2, "Discount Amount: "+StripeHandler.discountAmount);
	 		report.outputReport("IndividualTicketPurchaseReport", 54, 2, "Quantity: "+StripeHandler.quantity);
	 		report.outputReport("IndividualTicketPurchaseReport", 55, 2, "Day: "+StripeHandler.day);
	 		report.outputReport("IndividualTicketPurchaseReport", 56, 2, "Event Date: "+StripeHandler.date);
	 		report.outputReport("IndividualTicketPurchaseReport", 57, 2, "Total_Tax: "+StripeHandler.totalTax);
	 		
	 		if(MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
			{
				report.outputReport("IndividualTicketPurchaseReport", 37, 3,TestConstants.RESULT_PASS);
			}else if(!MemberEventTicketPurchasePage.eventTicketPurchaseSuccess)
			{
				report.outputReport("IndividualTicketPurchaseReport", 37, 3,TestConstants.RESULT_FAIL);
			}
		}catch(Exception e)
		{
			//report.outputReport("IndividualTicketPurchaseReport", 42, 3,TestConstants.RESULT_FAIL);
			e.printStackTrace();
		}
	}
}//Class is closed
	
	


