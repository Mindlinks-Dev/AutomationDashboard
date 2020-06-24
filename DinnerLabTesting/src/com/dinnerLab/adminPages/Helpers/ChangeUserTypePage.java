/**
 * @author: Basappa Hunsikatti
 * @Created Date :02/12/2015
 * @Updated Date :
 * @Comments:This automation class will change the User Type of a user by a Superdmin.
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

public class ChangeUserTypePage 
{
	private String AdminEmail;
	private String AdminPassword;
	private String email;
	private String loginURL;
	private String newUserType;
	private String reason;
	private String originUserType;
	private String newUserTypeId;
	public String changerById;
	public String changerByDate;
	public String id;
	public String nextBillingDate;
	public boolean operationSuccess;
	public int rowCount;
	public int rowsCount;
	String isTestPassed="FAIL";
	int i=0;
	private static Logger log = Logger.getLogger(ChangeUserTypePage.class);
	
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
				System.out.println("loginStatus:"+MemberLoginLogoutPage.loggedInStatus);
				
				CreateNewEventPage newEvent = new CreateNewEventPage();
				
				//Calling Super Admin Link method 
				newEvent.clickingOnSuperAdminLink(driver,loginURL);
				
				//Calling View Edit Users Link method 
				clickingOnViewEditUsersLink(driver);
				
				//Calling child handle window method
				//newEvent.childWindowHandle(driver);
			}
		 	return true;
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 return false;
		 }
	}
	/**
	 * Test Case for Reading the excel Change User Type data.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void changeUserTypeInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowCount= xllib.getRowCount("ChangeUserType");
			log.info("*********************Change User Type Logger Initialized******************************* ");
		 	for (i = 1; i <= rowCount; i++) 
			{
		 		//Reading creating New Event values
				email = xllib.getExcelData("ChangeUserType", i, 0);
				newUserType = xllib.getExcelData("ChangeUserType", i, 1);
		 		reason = xllib.getExcelData("ChangeUserType", i, 2);
		 	
		 		//Search criteria
				searchCriteria(driver);
				
				//Writing Origin User Type into Excel file
				xllib.writeToExcel("ChangeUserType", i, 3, originUserType);
				
				//Calling Change User Type Actions method
				changeUserTypeActions(driver);
				
				//calling Changed User Type Operation Success method
				changeUserTypeOperationSuccess(driver);
				
				CreateNewEventPage newEvent = new CreateNewEventPage();
				
				//Calling clicking on go back link method
				newEvent.clickingOnGoBackLink(driver);
				
				//Calling clicking on go back link method
				newEvent.clickingOnGoBackLink(driver);
				
				if(operationSuccess)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("ChangeUserType", i, 4, newUserTypeId);
					xllib.writeToExcel("ChangeUserType", i, 5, changerById);
					xllib.writeToExcel("ChangeUserType", i, 6, changerByDate);
					xllib.writeToExcel("ChangeUserType", i, 7, id);
				}
				else if(!operationSuccess)
				{
					isTestPassed="FAIL";
					//xllib.writeToExcel("ChangeUserType", i, 3, isTestPassed);
					xllib.writeToExcel("ChangeUserType", i, 4, isTestPassed);
					xllib.writeToExcel("ChangeUserType", i, 5, isTestPassed);
					xllib.writeToExcel("ChangeUserType", i, 6, isTestPassed);
					xllib.writeToExcel("ChangeUserType", i, 7, isTestPassed);
				}
				if(originUserType.equalsIgnoreCase(TestConstants.FREE_USER))
		        {
					xllib.writeToExcel("ChangeUserType", i, 8, nextBillingDate);
		        }
			}
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}
	/**
	 * Test Case for searching user to change User Type
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void searchCriteria(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         driver.findElement(By.id("filteremail")).clear();
	         driver.findElement(By.id("filteremail")).sendKeys(email);
	         driver.findElement(By.id("search")).click();
	         driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         
	         originUserType = driver.findElement(By.xpath("//tr[td[contains(text(),'"+email+"')]]/td[7]")).getText();
	         System.out.println("originUserType:"+originUserType);
	         //boolean paidMemberStatus = driver.findElement(By.xpath("//td[contains(text(),'PAID MEMBER')]")).isDisplayed();
	         //System.out.println("paidMemberStatus:"+paidMemberStatus);
	         
	         //Condition to check user type is Paid Member 
	         if(!originUserType.equalsIgnoreCase(TestConstants.PAID_MEMBER))
	         {
	        	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        	 driver.findElement(By.xpath("//a[contains(text(),'View User Detail')]")).click();
	        	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         }
	         
		 }catch(NoSuchElementException e)
		{
			e.printStackTrace();
		}
	 }
	/**
		 * Test Case for clicking on View Edit Users Link
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	*/
	 public  void clickingOnViewEditUsersLink(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         driver.findElement(By.xpath("//a[contains(text(),'View/edit Users')]")).click();
			 /*WebElement viewEditEvents = driver.findElement(By.xpath("//a[contains(text(),'View/edit Events')]"));
			 Actions action= new Actions(driver);
			 action.contextClick(viewEditEvents).sendKeys(Keys.ARROW_DOWN).click().build().perform();*/
	         log.info("Clicked on View Edit Users Link");
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	/**
		* Test Case for Changing User Type
		* Input: WebDriver
		* Output: Void
		* @return 
		* @throws InvalidFormatException 
	*/ 
	public void changeUserTypeActions(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS); 
			//Clicking on Change User Type button
			driver.findElement(By.xpath("//a[contains(text(),'Change Usertype')]")).click();
			
			WebElement userType =  driver.findElement(By.id("newUserType"));
			Select st = new Select(userType);
			st.selectByVisibleText(newUserType);
			
			if(originUserType.equalsIgnoreCase(TestConstants.FREE_USER))
	        {
	        	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        	 //Fetching Next Billing Date
	        	 //nextBillingDate = driver.findElement(By.id("nextBillingDate")).getText();
	        	 nextBillingDate = driver.findElement(By.id("nextBillingDate")).getAttribute("value");
	        	 System.out.println("nextBillingDate:"+nextBillingDate);
	        	 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	        }
			
			//Entering Reason for Change User type
			driver.findElement(By.id("reason")).clear();
			driver.findElement(By.id("reason")).sendKeys(reason);
			//Clicking on Submit Button
			driver.findElement(By.xpath("//button[@type='submit']")).click();	
			
		}
		catch(WebDriverException wde)
		{
			wde.printStackTrace();
		}
	}
	
	/**
	 * Test Case for verifying Changed User Type
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void changeUserTypeOperationSuccess(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 operationSuccess = driver.findElement(By.xpath("//span[contains(text(),'Operation Success:')]")).isDisplayed();
			 System.out.println("operationSuccess:"+operationSuccess);
			 
			 if(operationSuccess)
			 {
				 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
				 newUserTypeId = driver.findElement(By.xpath("//li[contains(text(),'newUserType:')]")).getText();
				 changerById = driver.findElement(By.xpath("//li[contains(text(),'changerByID:')]")).getText();
				 changerByDate =driver.findElement(By.xpath("//li[contains(text(),'changerByDate:')]")).getText();
				 id = driver.findElement(By.xpath("//li[contains(text(),'id:')]")).getText();
				 
				 System.out.println("newUserTypeId:"+newUserTypeId);
				 System.out.println("changerById:"+changerById);
				 System.out.println("changerByDate:"+changerByDate);
				 System.out.println("Id:"+id);
				 
				 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			 }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
}


