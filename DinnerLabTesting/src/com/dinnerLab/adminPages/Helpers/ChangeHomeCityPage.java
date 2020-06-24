/**
 * @author: Basappa Hunsikatti
 * @Created Date :17/12/2015
 * @Updated Date :
 * @Comments:This automation class will change the Home City of a user by a Superdmin.
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
import com.dinnerLab.userPages.Helpers.MemberLoginLogoutPage;
import com.dinnerLab.util.ExcelLib;

public class ChangeHomeCityPage 
{
	private String AdminEmail;
	private String AdminPassword;
	private String email;
	private String loginURL;
	private String newHomeCity;
	private String existingHomeCity;
	private String userType;
	private String userId;
	private String currentURL[];
	public boolean newHomeCityStatus;
	public boolean existingHomeCityStatus;
	public int rowCount;
	public int rowsCount;
	String isTestPassed="FAIL";
	int i=0;
	private static Logger log = Logger.getLogger(ChangeHomeCityPage.class);
	
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
				
				ChangeUserTypePage user = new ChangeUserTypePage();
				
				//Calling View Edit Users Link method 
				user.clickingOnViewEditUsersLink(driver);
				
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
	 * Test Case for Reading the excel Change Home City data.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void changeHomeCityInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowCount= xllib.getRowCount("ChangeHomeCity");
			log.info("*********************Change Home City Logger Initialized*****************************************");
			log.info("Email  ||"+" Existing Home City ||" + "New Home City ||"+ "User Type||" + "User ID||"+ "Staus");
	 		log.info("**************************************************************************************************");
		 	for (i = 1; i <= rowCount; i++) 
			{
		 		//Reading creating New Event values
				email = xllib.getExcelData("ChangeHomeCity", i, 0);
				newHomeCity = xllib.getExcelData("ChangeHomeCity", i, 1);
		 				 	
		 		//Search criteria
				searchCriteria(driver);
				
				if(existingHomeCityStatus)
				{
					xllib.writeToExcel("ChangeHomeCity", i, 3, existingHomeCity);
					xllib.writeToExcel("ChangeHomeCity", i, 4, userType);
					xllib.writeToExcel("ChangeHomeCity", i, 5, userId);
				}else if(!existingHomeCityStatus)
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("ChangeHomeCity", i, 3, isTestPassed);
					xllib.writeToExcel("ChangeHomeCity", i, 4, isTestPassed);
					xllib.writeToExcel("ChangeHomeCity", i, 5, isTestPassed);
				}
				
				//Calling Change User Type Actions method
				changeHomeCityActions(driver);
				
				//calling verify New Home City Status method
				verifyNewHomeCityStatus(driver);
				
				CreateNewEventPage newEvent = new CreateNewEventPage();
				
				//Calling clicking on go back link method
				newEvent.clickingOnGoBackLink(driver);
				
				//Calling clicking on go back link method
				newEvent.clickingOnGoBackLink(driver);
				
				if(newHomeCityStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("ChangeHomeCity", i, 2, isTestPassed);
					log.info(","+email +"," +existingHomeCity+"," +newHomeCity+","+userType+","+userId+isTestPassed);
				}
				else if(!newHomeCityStatus)
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("ChangeHomeCity", i, 2, isTestPassed);
					log.info(","+email +"," +existingHomeCity+"," +newHomeCity+","+userType+","+userId+isTestPassed);
				}
			}
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}
	/**
	 * Test Case for searching user to change Home City
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
	         
	         existingHomeCityStatus = driver.findElement(By.xpath("//tr[td[contains(text(),'"+email+"')]]/td[5]")).isDisplayed();
	         System.out.println("existingHomeCityStatus:"+existingHomeCityStatus);
	         
	         existingHomeCity = driver.findElement(By.xpath("//tr[td[contains(text(),'"+email+"')]]/td[5]")).getText();
	         System.out.println("existingHomeCity:"+existingHomeCity);
	         
	         userType = driver.findElement(By.xpath("//tr[td[contains(text(),'"+email+"')]]/td[6]")).getText();
	         System.out.println("userType:"+userType);
	         
	         driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         driver.findElement(By.xpath("//a[contains(text(),'View User Detail')]")).click();
	         driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         currentURL = driver.getCurrentUrl().split("=");
	         System.out.println("currentURL:"+currentURL);
	         userId= currentURL[1];
	         System.out.println("userId:"+userId);
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	     }catch(NoSuchElementException e)
		 {
			e.printStackTrace();
		 }
	 }
	/**
		* Test Case for Changing Home City
		* Input: WebDriver
		* Output: Void
		* @return 
		* @throws InvalidFormatException 
	*/ 
	public void changeHomeCityActions(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS); 
			//Clicking on Change User Type button
			driver.findElement(By.xpath("//a[contains(text(),'Manage Cities')]")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			
			WebElement newCity =  driver.findElement(By.id("cityID"));
			Select st = new Select(newCity);
			st.selectByVisibleText(newHomeCity);
			
			//Clicking on Submit Button
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			driver.findElement(By.name("Update")).click();	
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		}
		catch(WebDriverException wde)
		{
			wde.printStackTrace();
		}
	}
	
	/**
	 * Test Case for verifying Changed Home City
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void verifyNewHomeCityStatus(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 if(existingHomeCity.equalsIgnoreCase(newHomeCity))
			 {
				 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				 newHomeCityStatus = driver.findElement(By.xpath("//li[contains(text(),'current home city and new home city are the same.')]")).isDisplayed();
				 System.out.println("newHomeCityStatus111:"+newHomeCityStatus);
			 }else
			 {
				 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				 newHomeCityStatus = driver.findElement(By.xpath("//li[contains(text(),'Successfully changed home city.')]")).isDisplayed();
				 System.out.println("newHomeCityStatus222:"+newHomeCityStatus);
			 }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
}


