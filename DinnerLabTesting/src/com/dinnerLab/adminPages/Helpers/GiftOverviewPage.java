/**
 * @author: Basappa Hunsikatti
 * @Created Date :31/12/2015
 * @Updated Date :
 * @Comments:This automation class will Refund Gift Credits and Membership Amount.
*/
package com.dinnerLab.adminPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.MemberLoginLogoutPage;
import com.dinnerLab.util.ExcelLib;

public class GiftOverviewPage 
{
	private String AdminEmail;
	private String AdminPassword;
	private String loginURL;
	private String purchaserEmail;
	private String code;
	private String redeemerEmail;
	private String creditsOnGifts;
	private String codeActive;
	private String refunded;
	private String totalAmount;
	private String creditsRefundID;
	private String membershipRefundID;
	private String chargeID;
	private String totalCashToRefund;
	public boolean creditStatus;
	public boolean verifyRefundStatus;
	public boolean verifyCreditsRefundID;
	public int rowCount;
	public int rowsCount;
	int i=0;
	private static Logger log = Logger.getLogger(GiftOverviewPage.class);
	public int CurrentRow = 0;
	String isTestPassed="FAIL";
			
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
				
				//Calling clicking on Gift Link method 
				clickingOnGiftLink(driver);
				
				//Calling child handle window method
				newEvent.childWindowHandle(driver);
			}
		 	return true;
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 return false;
		 }
	}
	/**
	 * Test Case for Reading the excel data and calling gift overview methods
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 * @throws InterruptedException 
	 */
	public void giftOverviewInitialPage(WebDriver driver) throws InvalidFormatException, InterruptedException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowCount= xllib.getRowCount("GiftOverview");
			log.info("*********************Gift Overview Logger Initialized***********************************************************************************************************************************************************************");
			log.info("Purchaser Email  ||"+" Amount ||" + "   Reason ||" + "  Add Credits || " + " Subtract Credits  || " + " Credit Status ||" + " First Name || " + " Last Name || " + " Credit Type || " + " Amount(Credited/Redeem) || " + " Sub Category || " + " Credit Time ||" + " Source");
	 		log.info("********************************************************************************************************************************************************************************************************************************");
			for (i = 1; i <= rowCount; i++) 
			{				
				//Reading View Edit Tickets values
				purchaserEmail = xllib.getExcelData("GiftOverview", i, 0);
		 		code = xllib.getExcelData("GiftOverview", i, 1);
		 		
		 		//Search criteria
				searchCriteria(driver);
				
		 		//Calling gift Overview Actions method
				refundGiftActions(driver);
		 		
				//Calling clicking on go back link method
				//clickingOnGoBackLink(driver);
				
		 		if(verifyRefundStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("GiftOverview", i, 2, isTestPassed);
					xllib.writeToExcel("GiftOverview", i, 3, membershipRefundID);
					xllib.writeToExcel("GiftOverview", i, 4, chargeID);
					xllib.writeToExcel("GiftOverview", i, 5, totalCashToRefund);
					
					if(verifyCreditsRefundID)
					{
						xllib.writeToExcel("GiftOverview", i, 6, creditsRefundID);
					}
				}else
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("GiftOverview", i, 2, isTestPassed);
					xllib.writeToExcel("GiftOverview", i, 3, isTestPassed);
					xllib.writeToExcel("GiftOverview", i, 4, isTestPassed);
					xllib.writeToExcel("GiftOverview", i, 5, isTestPassed);
					xllib.writeToExcel("GiftOverview", i, 6, isTestPassed);
				}
			 }//End of FOR LOOP
		 }catch(NullPointerException e)
		 {
			 e.printStackTrace();
		 }
	}
	/**
		 * Test Case for clicking on Gift link
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	*/
	 public  void clickingOnGiftLink(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         driver.findElement(By.xpath("//a[contains(text(),'Gift')]")).click();
			 log.info("Clicked on View/Edit Credits Link");
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 /**
		 * Test Case for searching user to Refund Amount
		 * Input: WebDriver
		 * Output: Void
		 * @return 
	 * @throws InterruptedException 
		 * @throws InvalidFormatException 
	*/
	 public  void searchCriteria(WebDriver driver) throws InterruptedException
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        
			 driver.findElement(By.id("filter_purchaserEmail")).clear();
	         driver.findElement(By.id("filter_purchaserEmail")).sendKeys(purchaserEmail);
	         
	         driver.findElement(By.id("filter_code")).clear();
	         driver.findElement(By.id("filter_code")).sendKeys(code);
	         
	         driver.findElement(By.id("search")).click();
	         driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		         
	         redeemerEmail = driver.findElement(By.xpath("//tr[td[contains(text(),'"+purchaserEmail+"')]]/td[3]")).getText();
	         System.out.println("redeemerEmail:"+redeemerEmail);
	        
	         creditsOnGifts = driver.findElement(By.xpath("//tr[td[contains(text(),'"+purchaserEmail+"')]]/td[6]")).getText();
	         System.out.println("creditsOnGifts:"+creditsOnGifts);
	         
	         codeActive = driver.findElement(By.xpath("//tr[td[contains(text(),'"+purchaserEmail+"')]]/td[7]")).getText();
	         System.out.println("codeActive:"+codeActive);
	         
	         Thread.sleep(10000);
	         //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         refunded = driver.findElement(By.xpath("//tr[td[contains(text(),'"+purchaserEmail+"')]]/td[8]/span")).getText();
	         System.out.println("refunded:"+refunded);
	         driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         
	         //Condition to check view more details
	         if(redeemerEmail.equalsIgnoreCase(TestConstants.NOT_REDEEMED) && codeActive.equalsIgnoreCase(TestConstants.STATUS_YES) && refunded.equalsIgnoreCase(TestConstants.STATUS_NO))
	         {
	        	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        	 System.out.println("Before View More Detail");
	        	 driver.findElement(By.xpath("//a[contains(text(),'View More Detail')]")).click();
	        	 System.out.println("After View More Detail");
	        	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         }
	         
	         System.out.println("Try block");
	       }catch(NoSuchElementException e)
		   {
	    	   e.printStackTrace();
	    	   System.out.println("Catch block");
		   }
	} 
	 /**
		 * Test Case for Refund Amount
		 * Input: WebDriver
		 * Output: Void
		 * @throws InterruptedException 
		 * @throws InvalidFormatException 
	 */
	public void refundGiftActions(WebDriver driver) throws InterruptedException 
	{
		try
		{
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
				
			//Clicking on Change Credit Button
			driver.findElement(By.xpath("//a[contains(text(),'Refund Gift')]")).click();			
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
			
			System.out.println("Before Refund total cash");
			
			try
			{
				driver.findElement(By.xpath("//button[contains(text(),'Refund Total Cash')]")).click();	
				System.out.println("After Refund total cash");
				driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
			}catch(TimeoutException e)
			{
				e.printStackTrace();
			}
			
			Thread.sleep(10000);
			System.out.println("Before pop up");
			verifyRefundStatus = driver.findElement(By.xpath("//div[contains(text(),'Refund was successful')]")).isDisplayed();
			System.out.println("verifyRefundStatus:"+verifyRefundStatus);
			System.out.println("After pop up");
			
			if(verifyRefundStatus)
			{
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				driver.findElement(By.xpath("//button[contains(text(),'Close')]")).click();
				
				Float creditValue = Float.parseFloat(creditsOnGifts); 
				Thread.sleep(5000);
				try
				{
					driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
					verifyCreditsRefundID = driver.findElement(By.xpath("//td[contains(text(),'Credit Modification')]")).isDisplayed();
					System.out.println("verifyCreditsRefundID:"+verifyCreditsRefundID);
				}catch(Exception wde)
				{
					wde.printStackTrace();
				}
				
				if(creditValue == 0)
				{
					driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
					membershipRefundID = driver.findElement(By.xpath("//tr[td[contains(text(),'Membership')]]/td[9]")).getText();
				    System.out.println("membershipRefundID:"+membershipRefundID);
				        
				    chargeID = driver.findElement(By.xpath("//tr[td[contains(text(),'Membership')]]/td[8]")).getText();
				    System.out.println("chargeID:"+chargeID);
				        
				    totalCashToRefund = driver.findElement(By.xpath("//tr[td[strong[contains(text(),'Total cash to refund:')]]]/td[4]")).getText();
				    System.out.println("totalCashToRefund:"+totalCashToRefund); 
				    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				}else if(verifyCreditsRefundID)
				{
					if(verifyCreditsRefundID)
					{
						driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
						creditsRefundID = driver.findElement(By.xpath("//tr[td[contains(text(),'Credit Modification')]]/td[9]")).getText();
					    System.out.println("creditsRefundID:"+creditsRefundID);
					}
					driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
					membershipRefundID = driver.findElement(By.xpath("//tr[td[contains(text(),'Membership')]]/td[9]")).getText();
				    System.out.println("membershipRefundID:"+membershipRefundID);
				        
				    chargeID = driver.findElement(By.xpath("//tr[td[contains(text(),'Membership')]]/td[8]")).getText();
				    System.out.println("chargeID:"+chargeID);
				        
				    totalCashToRefund = driver.findElement(By.xpath("//tr[td[strong[contains(text(),'Total cash to refund:')]]]/td[4]")).getText();
				    System.out.println("totalCashToRefund:"+totalCashToRefund); 
				    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				}
			}
		}
		catch(Exception wde)
		{
			wde.printStackTrace();
		}
	}
	/**
	 * Test Case for clicking on Ok Button on Alert pop up
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	 */
	public  void handlingAlertPopup(WebDriver driver)
	{
		try
		{
			Thread.sleep(10000);
			Alert alert = driver.switchTo().alert();
			String refund = alert.getText();
			System.out.println("refund:"+refund);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}


