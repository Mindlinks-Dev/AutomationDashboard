/**
 * @author: Basappa Hunsikatti
 * @Created Date :28/12/2015
 * @Updated Date :
 * @Comments:This automation class Add/Substract credits.
*/
package com.dinnerLab.adminPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.MemberLoginLogoutPage;
import com.dinnerLab.util.ExcelLib;

public class ViewEditCreditsPage 
{
	private String AdminEmail;
	private String AdminPassword;
	private String loginURL;
	private String email;
	private String amount;
	private String reason;
	private String addCredits;
	private String subtractCredits;
	private String firstName;
	private String lastName;
	private String creditType;
	private String amountCreditedRedeem;
	private String creditedTime;
	private String source;
	private String subCategory;
	public boolean creditStatus;
	public int rowCount;
	public int rowsCount;
	int i=0;
	private static Logger log = Logger.getLogger(ViewEditCreditsPage.class);
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
				
				//Calling clicking on View Edit Credits Link method 
				clickingViewEditCreditsLink(driver);
				
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
	 * Test Case for Reading the excel data and calling add tickets and modify reservation methods
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 * @throws InterruptedException 
	 */
	public void viewEditCreditsInitialPage(WebDriver driver) throws InvalidFormatException, InterruptedException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowCount= xllib.getRowCount("ViewEditCredits");
			log.info("*********************View/Edit Credits Logger Initialized***********************************************************************************************************************************************************************");
			log.info("Email  ||"+" Amount ||" + "   Reason ||" + "  Add Credits || " + " Subtract Credits  || " + " Credit Status ||" + " First Name || " + " Last Name || " + " Credit Type || " + " Amount(Credited/Redeem) || " + " Sub Category || " + " Credit Time ||" + " Source");
	 		log.info("********************************************************************************************************************************************************************************************************************************");
			for (i = 1; i <= rowCount; i++) 
			{				
				//Reading View Edit Tickets values
		 		email = xllib.getExcelData("ViewEditCredits", i, 0);
		 		amount = xllib.getExcelData("ViewEditCredits", i, 1);
		 		reason = xllib.getExcelData("ViewEditCredits", i, 2);
		 		addCredits = xllib.getExcelData("ViewEditCredits", i, 3);
		 		subtractCredits = xllib.getExcelData("ViewEditCredits", i, 4);
		 		
		 		//Calling change credit method
		 		changeCreditActions(driver);
		 			
		 		//Calling verify credit status
				verifyCreditStatus(driver);
				
				if(creditStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("ViewEditCredits", i, 5, isTestPassed);
					xllib.writeToExcel("ViewEditCredits", i, 6, firstName);
					xllib.writeToExcel("ViewEditCredits", i, 7, lastName);
					xllib.writeToExcel("ViewEditCredits", i, 8, creditType);
					xllib.writeToExcel("ViewEditCredits", i, 9, amountCreditedRedeem);
					xllib.writeToExcel("ViewEditCredits", i, 10, subCategory);
					xllib.writeToExcel("ViewEditCredits", i, 11, creditedTime);
					xllib.writeToExcel("ViewEditCredits", i, 12, source);
					log.info(","+email +"," +amount +"," + reason + "," + addCredits+"," + subtractCredits +","+isTestPassed+","+firstName+","+lastName+","+creditType+","+amountCreditedRedeem+","+subCategory+","+creditedTime+","+source);
				}else
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("ViewEditCredits", i, 5, isTestPassed);
					xllib.writeToExcel("ViewEditCredits", i, 6, isTestPassed);
					xllib.writeToExcel("ViewEditCredits", i, 7, isTestPassed);
					xllib.writeToExcel("ViewEditCredits", i, 8, isTestPassed);
					xllib.writeToExcel("ViewEditCredits", i, 9, isTestPassed);
					xllib.writeToExcel("ViewEditCredits", i, 10, isTestPassed);
					xllib.writeToExcel("ViewEditCredits", i, 11, isTestPassed);
					xllib.writeToExcel("ViewEditCredits", i, 12, isTestPassed);
					log.info(","+email +"," +amount +"," + reason + "," + addCredits+"," + subtractCredits +","+isTestPassed+","+isTestPassed+","+isTestPassed+","+isTestPassed+","+isTestPassed+","+isTestPassed+","+isTestPassed+","+isTestPassed);
				}
			 }//End of FOR LOOP
		 }catch(NullPointerException e)
		 {
			 e.printStackTrace();
		 }
	}
	/**
		 * Test Case for clicking on view edit credits link
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	*/
	 public  void clickingViewEditCreditsLink(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         driver.findElement(By.xpath("//a[@href='/superadmin/credits']")).click();
			 log.info("Clicked on View/Edit Credits Link");
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }

	 /**
		 * Test Case for change credit
		 * Input: WebDriver
		 * Output: Void
	 * @throws InterruptedException 
		 * @throws InvalidFormatException 
	 */
	public void changeCreditActions(WebDriver driver) throws InterruptedException 
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			//Clicking on Change Credit Button
			driver.findElement(By.xpath("//a[contains(text(),'Change Credit')]")).click();			
			
			driver.findElement(By.name("email")).clear();		
			driver.findElement(By.name("email")).sendKeys(email);
			
			driver.findElement(By.name("amount")).clear();		
			driver.findElement(By.name("amount")).sendKeys(amount);
			
			driver.findElement(By.name("reason")).clear();		
			driver.findElement(By.name("reason")).sendKeys(reason);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
			if(addCredits.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				driver.findElement(By.xpath("//input[@value='Add']")).click();
				//Calling Alert pop up method
				handlingAlertPopup(driver);
				
			}else if(subtractCredits.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				driver.findElement(By.xpath("//input[@value='Subtract']")).click();
				//Calling Alert pop up method
				handlingAlertPopup(driver);
			}
		}
		catch(WebDriverException wde)
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
			Thread.sleep(5000);
			Alert alert = driver.switchTo().alert();
			alert.accept();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Test Case for verify credit status
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void verifyCreditStatus(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 creditStatus = driver.findElement(By.xpath("//div[contains(text(),'Successfully added credits to member')]")).isDisplayed();
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 System.out.println("creditStatus:"+creditStatus);
			 
			 firstName  = driver.findElement(By.xpath("//tr[1]/td[2]")).getText();
			 lastName  = driver.findElement(By.xpath("//tr[1]/td[3]")).getText();
			 creditType  = driver.findElement(By.xpath("//tr[1]/td[5]")).getText();
			 amountCreditedRedeem  = driver.findElement(By.xpath("//tr[1]/td[6]")).getText();
			 subCategory  = driver.findElement(By.xpath("//tr[1]/td[7]")).getText();
			 creditedTime  = driver.findElement(By.xpath("//tr[1]/td[8]")).getText();
			 source  = driver.findElement(By.xpath("//tr[1]/td[9]")).getText();
			 
			 System.out.println("firstName:"+firstName);
			 System.out.println("lastName:"+lastName);
			 System.out.println("creditType:"+creditType);
			 System.out.println("amountCreditedRedeem:"+amountCreditedRedeem);
			 System.out.println("creditedTime:"+creditedTime);
			 System.out.println("subCategory:"+subCategory);
			 System.out.println("source:"+source);
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
}


