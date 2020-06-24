/**
 * @author: Basappa Hunsikatti
 * @Created Date :22/02/2016
 * @Updated Date :
 * @Comments:This automation class will view Member Statistics
*/
package com.dinnerLab.adminPages.Helpers;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.MemberLoginLogoutPage;
import com.dinnerLab.util.ExcelLib;

public class MemberStatisticsPage 
{
	private String AdminEmail;
	private String AdminPassword;
	private String loginURL;
	private String city;
	private String fromDate;
	private String toDate;
	private String totalMembers;
	private String organicSignupsSinceLaunch;
	private String organicSelectMembersSinceLaunch;
	private String unpaidSelectMembersSinceLaunch;
	private String fromCodeSinceLaunch;
	private String totalSignups;
	private String organicSignups;
	private String organicSelectMembers;
	private String unpaidSelectMembers;
	private String fromCode;
	public int rowCount;
	public int rowsCount;
	int i=0;
	private static Logger log = Logger.getLogger(MemberStatisticsPage.class);
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
								
				//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				//driver.get("http://release1.dinnerlab.com/superadmin/kpi/members/");
				
				CreateNewEventPage newEvent = new CreateNewEventPage();
				
				//Calling Super Admin Link method 
				newEvent.clickingOnSuperAdminLink(driver,loginURL);
				
				//Calling clicking on Member Statistics Link method 
				clickingOnMemberStatisticsLink(driver);
				
				/* Set<String> handles= driver.getWindowHandles();
				 Iterator it = handles.iterator();
				 
				 String parent = handles.iterator().next();
				 String child1 = handles.iterator().next();
				 System.out.println("parent:"+parent);
				 System.out.println("child1:"+child1);
				 driver.switchTo().window(child1);*/
				
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
	 * Test Case for Reading the excel data and calling methods
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 * @throws InterruptedException 
	 */
	public void memberStatisticsInitialPage(WebDriver driver) throws InvalidFormatException, InterruptedException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowCount= xllib.getRowCount("MemberStatistics");
			log.info("*********************Member Statistics Logger Initialized***********************************************************************************************************************************************************************");
			log.info("Email  ||"+" Amount ||" + "   Reason ||" + "  Add Credits || " + " Subtract Credits  || " + " Credit Status ||" + " First Name || " + " Last Name || " + " Credit Type || " + " Amount(Credited/Redeem) || " + " Sub Category || " + " Credit Time ||" + " Source");
	 		log.info("********************************************************************************************************************************************************************************************************************************");
			for (i = 1; i <= rowCount; i++) 
			{				
				//Reading View Edit Tickets values
		 		city = xllib.getExcelData("MemberStatistics", i, 0);
		 		fromDate = xllib.getExcelData("MemberStatistics", i, 1);
		 		toDate = xllib.getExcelData("MemberStatistics", i, 2);
		 		
		 		//Calling Member Statistics method
		 		memberStatisticsActions(driver);
		 			
		 		//Calling get Member Statistics method
				getMemberStatistics(driver);
				
				//if(creditStatus)
				//{
					isTestPassed="PASS";
					xllib.writeToExcel("MemberStatistics", i, 3, totalMembers);
					xllib.writeToExcel("MemberStatistics", i, 4, organicSignupsSinceLaunch);
					xllib.writeToExcel("MemberStatistics", i, 5, organicSelectMembersSinceLaunch);
					xllib.writeToExcel("MemberStatistics", i, 6, unpaidSelectMembersSinceLaunch);
					xllib.writeToExcel("MemberStatistics", i, 7, fromCodeSinceLaunch);
					xllib.writeToExcel("MemberStatistics", i, 8, totalSignups);
					xllib.writeToExcel("MemberStatistics", i, 9, organicSignups);
					xllib.writeToExcel("MemberStatistics", i, 10, organicSelectMembers);
					xllib.writeToExcel("MemberStatistics", i, 11, unpaidSelectMembers);
					xllib.writeToExcel("MemberStatistics", i, 12, fromCode);
					//log.info(","+email +"," +amount +"," + reason + "," + addCredits+"," + subtractCredits +","+isTestPassed+","+firstName+","+lastName+","+creditType+","+amountCreditedRedeem+","+subCategory+","+creditedTime+","+source);
				/*}else
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("MemberStatistics", i, 3, isTestPassed);
					xllib.writeToExcel("MemberStatistics", i, 4, isTestPassed);
					xllib.writeToExcel("MemberStatistics", i, 5, isTestPassed);
					xllib.writeToExcel("MemberStatistics", i, 6, isTestPassed);
					xllib.writeToExcel("MemberStatistics", i, 7, isTestPassed);
					xllib.writeToExcel("MemberStatistics", i, 8, isTestPassed);
					xllib.writeToExcel("MemberStatistics", i, 9, isTestPassed);
					xllib.writeToExcel("MemberStatistics", i, 10, isTestPassed);
					xllib.writeToExcel("MemberStatistics", i, 11, isTestPassed);
					xllib.writeToExcel("MemberStatistics", i, 12, isTestPassed);
					//log.info(","+email +"," +amount +"," + reason + "," + addCredits+"," + subtractCredits +","+isTestPassed+","+isTestPassed+","+isTestPassed+","+isTestPassed+","+isTestPassed+","+isTestPassed+","+isTestPassed+","+isTestPassed);
				}*/
			 }//End of FOR LOOP
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}
	/**
		 * Test Case for clicking on Member Statistics link
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
	*/
	 public  void clickingOnMemberStatisticsLink(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	         driver.findElement(By.xpath("//a[@href='/superadmin/kpi/members']")).click();
			 log.info("Clicked on Member Statistics Link");
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
	public void memberStatisticsActions(WebDriver driver) throws InterruptedException 
	{
		try
		{
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			Thread.sleep(5000);
			System.out.println("before city");
			WebElement cityName = driver.findElement(By.name("cityID"));
			Select st = new Select(cityName);
			st.selectByVisibleText(city);
			System.out.println("after city");
			
			driver.findElement(By.id("members_from")).clear();		
			driver.findElement(By.id("members_from")).sendKeys(fromDate);
			
			driver.findElement(By.id("members_to")).clear();		
			driver.findElement(By.id("members_to")).sendKeys(toDate);
			
			driver.findElement(By.xpath("//input[@value='Search']")).click();
			
			System.out.println("after search");
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}
		catch(Exception wde)
		{
			wde.printStackTrace();
		}
	}
	/**
	 * Test Case for getting Member Statistics
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void getMemberStatistics(WebDriver driver)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			 
			 //Total Since Launch
			 totalMembers  = driver.findElement(By.xpath("//div[div[h3[contains(text(),'Total Members:')]]]/div[2]/h3")).getText();
			 organicSignupsSinceLaunch  = driver.findElement(By.xpath("//div[div[h2[contains(text(),'Total Since Launch')]]]/div[3]/div[2]/h3")).getText();
			 organicSelectMembersSinceLaunch  = driver.findElement(By.xpath("//div[div[h2[contains(text(),'Total Since Launch')]]]/div[4]/div[2]/h3")).getText();
			 unpaidSelectMembersSinceLaunch  = driver.findElement(By.xpath("//div[div[h2[contains(text(),'Total Since Launch')]]]/div[5]/div[2]/h3")).getText();
			 fromCodeSinceLaunch = driver.findElement(By.xpath("//div[div[h2[contains(text(),'Total Since Launch')]]]/div[6]/div[2]/h3")).getText();
			 
			 //From Date - To Date
			 totalSignups  = driver.findElement(By.xpath("//div[div[h3[contains(text(),'Total Signups:')]]]/div[2]/h3")).getText();
			 organicSignups = driver.findElement(By.xpath("//div[div[div[h3[contains(text(),'Total Signups:')]]]]/div[3]/div[2]/h3")).getText();
			 organicSelectMembers = driver.findElement(By.xpath("//div[div[div[h3[contains(text(),'Total Signups:')]]]]/div[4]/div[2]/h3")).getText();
			 unpaidSelectMembers  = driver.findElement(By.xpath("//div[div[div[h3[contains(text(),'Total Signups:')]]]]/div[5]/div[2]/h3")).getText();
			 fromCode = driver.findElement(By.xpath("//div[div[div[h3[contains(text(),'Total Signups:')]]]]/div[6]/div[2]/h3")).getText();
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 
			 System.out.println("totalMembers:"+totalMembers);
			 System.out.println("organicSignupsSinceLaunch:"+organicSignupsSinceLaunch);
			 System.out.println("organicSelectMembersSinceLaunch:"+organicSelectMembersSinceLaunch);
			 System.out.println("unpaidSelectMembersSinceLaunch:"+unpaidSelectMembersSinceLaunch);
			 System.out.println("fromCodeSinceLaunch:"+fromCodeSinceLaunch);
			 System.out.println("totalSignups:"+totalSignups);
			 System.out.println("organicSignups:"+organicSignups);
			 System.out.println("organicSelectMembers:"+organicSelectMembers);
			 System.out.println("unpaidSelectMembers:"+unpaidSelectMembers);
			 System.out.println("fromCode:"+fromCode);
			 
		
		 }catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
}


