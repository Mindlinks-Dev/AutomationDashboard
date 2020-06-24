/**
 * @author: Basappa Hunsikatti
 * @Created Date :17/12/2015
 * @Updated Date :
 * @Comments:This automation class will change the Home City of a user by a Superdmin.
*/
package com.tronixss.userPages.Helpers;

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

public class CreateNewEntityPage 
{
	private String loginUsername;
	private String loginPassword;
	public String loginURL;
	private String OrgName;
	private String entityTitle;
	private String division;
	private String cluster;
	private String entityUnitType;
	private String AUTeam;
	private String regionalEntityDirector;
	private String AULead;
	private String clusterLead;
	private String countries;
	private String region;
	private String activeAuditableUnit;
	private String uploadFileStatus;
	private String gmailUsername;
	private String gmailPassword;
	private String googleDriveFolderName;
	
	public boolean newHomeCityStatus;
	public boolean newEventIdStatus;
	public String newEventId;
	public boolean existingHomeCityStatus;
	public int rowCount;
	public int rowsCount;
	String isTestPassed="FAIL";
	int i=0;
	private static Logger log = Logger.getLogger(CreateNewEntityPage.class);
	
	/**
	 * Test Case for Reading the excel Entity data.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void createNewEntityInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowCount= xllib.getRowCount("SFDCTronixss");
			System.out.println("rowCount:"+rowCount);
			log.info("*********************Entity Logger Initialized*****************************************");
			//log.info("Email  ||"+" Existing Home City ||" + "New Home City ||"+ "User Type||" + "User ID||"+ "Staus");
	 		//log.info("**************************************************************************************************");
		 	for (i = 1; i <= rowCount; i++) 
			{
		 		//Reading creating New Entity data
		 		//OrgName = xllib.getExcelData("SFDCTronixss", i, 0);
				//loginUsername = xllib.getExcelData("SFDCTronixss", i, 1);
				//loginPassword = xllib.getExcelData("SFDCTronixss", i, 2);
		 		entityTitle = xllib.getExcelData("SFDCTronixss", i, 0);
				division = xllib.getExcelData("SFDCTronixss", i, 1);
				cluster = xllib.getExcelData("SFDCTronixss", i, 2);
				entityUnitType = xllib.getExcelData("SFDCTronixss", i, 3);
				AUTeam = xllib.getExcelData("SFDCTronixss", i, 4);
				regionalEntityDirector = xllib.getExcelData("SFDCTronixss", i, 5);
				AULead = xllib.getExcelData("SFDCTronixss", i, 6);
				clusterLead = xllib.getExcelData("SFDCTronixss", i, 7);
				countries = xllib.getExcelData("SFDCTronixss", i, 8);
				region = xllib.getExcelData("SFDCTronixss", i, 9);
				activeAuditableUnit = xllib.getExcelData("SFDCTronixss", i, 10);
				/*uploadFileStatus = xllib.getExcelData("Entity", i, 11);
				gmailUsername = xllib.getExcelData("Entity", i, 12);
				gmailPassword = xllib.getExcelData("Entity", i, 13);
				googleDriveFolderName = xllib.getExcelData("Entity", i, 14);*/
				
				//Reading creating New Observation data
				/*uploadFileStatus = xllib.getExcelData("Entity", i, 14);
				gmailUsername = xllib.getExcelData("Entity", i, 15);
				gmailPassword = xllib.getExcelData("Entity", i, 16);
				googleDriveFolderName = xllib.getExcelData("Entity", i, 17);*/
				
				/*System.out.println("OrgName:"+OrgName);
				System.out.println("loginUsername:"+loginUsername);
				System.out.println("loginPassword:"+loginPassword);
								 
				if(OrgName.equalsIgnoreCase(TestConstants.TEST_ORG))
				{
					driver.get(TestConstants.TRONIXSS_TEST_ORG);
					driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
					System.out.println("TestConstants.TRONIXSS_TEST_ORG:"+TestConstants.TRONIXSS_TEST_ORG);
				}else if(OrgName.equalsIgnoreCase(TestConstants.DEV_ORG))
				{
					driver.get(TestConstants.TRONIXSS_DEV_ORG);
					driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
					System.out.println("TestConstants.TRONIXSS_DEV_ORG:"+TestConstants.TRONIXSS_DEV_ORG);
				}else if(OrgName.equalsIgnoreCase(TestConstants.NEW_DEV_ORG))
				{
					driver.get(TestConstants.TRONIXSS_TEST_ORG);
					driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
					System.out.println("TestConstants.NEW_DEV_ORG:"+TestConstants.TRONIXSS_TEST_ORG);
				}*/
				
				//UserLoginLogoutPage login = new UserLoginLogoutPage();
				
				//login.loginPageActions(driver,loginUsername,loginPassword);
				
				//Check whether login credentials valid or not
				//if(UserLoginLogoutPage.loggedInStatus)
				//{
					//Calling click on Entity object method
					clickOnEntityObject(driver);
					
					//Calling click on new button method
					clickOnNewButton(driver);
			 		
					//Calling create New Entity Actions method
					createNewEntityActions(driver);
					
					//Calling verify New Event Created method
					verifyNewEventCreated(driver);
					
					//Calling logout method
					//login.logoutActions(driver);
				//}
				
				if(newEventIdStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("SFDCTronixss", i, 11, isTestPassed);
					xllib.writeToExcel("SFDCTronixss", i, 12, newEventId);
				}
				else if(!newEventIdStatus)
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("SFDCTronixss", i, 11, isTestPassed);
					xllib.writeToExcel("SFDCTronixss", i, 12, isTestPassed);
				}
			}
		 }
		catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}
	/**
		* Test Case for create New Entity 
		* Input: WebDriver
		* Output: Void
		* @return 
		* @throws InvalidFormatException 
	*/ 
	public void createNewEntityActions(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS); 
			driver.findElement(By.id("Container:pbMain:frm1:j_id9")).clear();
			driver.findElement(By.id("Container:pbMain:frm1:j_id9")).sendKeys(entityTitle);
			
			driver.findElement(By.id("Container:pbMain:frm1:j_id11")).clear();
			driver.findElement(By.id("Container:pbMain:frm1:j_id11")).sendKeys(division);
			
			driver.findElement(By.id("Container:pbMain:frm1:j_id13")).clear();
			driver.findElement(By.id("Container:pbMain:frm1:j_id13")).sendKeys(cluster);
			
			driver.findElement(By.id("Container:pbMain:frm1:j_id15")).clear();
			driver.findElement(By.id("Container:pbMain:frm1:j_id15")).sendKeys(entityUnitType);
			
			driver.findElement(By.id("Container:pbMain:frm1:j_id17")).clear();
			driver.findElement(By.id("Container:pbMain:frm1:j_id17")).sendKeys(AUTeam);
			
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			
			WebElement regional =  driver.findElement(By.name("Container:pbMain:frm1:j_id19"));
			Select st = new Select(regional);
			st.selectByVisibleText(regionalEntityDirector);
			
			WebElement auLead =  driver.findElement(By.name("Container:pbMain:frm1:j_id22"));
			Select st1 = new Select(auLead);
			st1.selectByVisibleText(AULead);
			
			WebElement clusterText =  driver.findElement(By.name("Container:pbMain:frm1:j_id25"));
			Select st2 = new Select(clusterText);
			st2.selectByVisibleText(clusterLead);
			
			WebElement country =  driver.findElement(By.id("Container:pbMain:frm1:j_id28"));
			Select st3 = new Select(country);
			st3.selectByVisibleText(countries);
			
			WebElement regionText =  driver.findElement(By.name("Container:pbMain:frm1:j_id30"));
			Select st4 = new Select(regionText);
			st4.selectByVisibleText(region);
			
			WebElement auditTable =  driver.findElement(By.id("Container:pbMain:frm1:j_id37"));
			Select st5 = new Select(auditTable);
			st5.selectByVisibleText(activeAuditableUnit);
			
			//Clicking on Save Button
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			driver.findElement(By.name("Container:pbMain:frm1:j_id41")).click();	
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Clicking on Entity button
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public void clickOnEntityObject(WebDriver driver)
	 {
		 try
		 {
			 //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 Thread.sleep(3000);
			 //driver.findElement(By.xpath("//a[@class='brandPrimaryFgr']")).click();
			 driver.findElement(By.xpath("//a[text()='Entity']")).click();
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	/**
	 * Clicking on New button
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public void clickOnNewButton(WebDriver driver)
	 {
		 try
		 {
			 //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 Thread.sleep(3000);
			 driver.findElement(By.xpath("//input[@title='New']")).click();
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 /**
		 * Test Case for verifying new event created
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
		 */
	public void verifyNewEventCreated(WebDriver driver)
	{
		try
		{
			//driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			Thread.sleep(3000);
			newEventIdStatus = driver.findElement(By.xpath("//tbody[tr[th[label[contains(text(),'Entity Id')]]]]/tr[1]/td[1]")).isDisplayed();
			newEventId = driver.findElement(By.xpath("//tbody[tr[th[label[contains(text(),'Entity Id')]]]]/tr[1]/td[1]")).getText();
			System.out.println("newEventId:"+newEventId);
			System.out.println("newEventIdStatus:"+newEventIdStatus);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	* Test Case for create New Observations
	* Input: WebDriver
	* Output: Void
	* @return 
	* @throws InvalidFormatException 
*/ 
public void createNewObservationsActions(WebDriver driver)
{
	try
	{
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS); 
				
		driver.findElement(By.id("Container:pbMain:frm1:j_id9")).clear();
		driver.findElement(By.id("Container:pbMain:frm1:j_id9")).sendKeys(entityTitle);
		
		driver.findElement(By.id("Container:pbMain:frm1:j_id11")).clear();
		driver.findElement(By.id("Container:pbMain:frm1:j_id11")).sendKeys(division);
		
		driver.findElement(By.id("Container:pbMain:frm1:j_id13")).clear();
		driver.findElement(By.id("Container:pbMain:frm1:j_id13")).sendKeys(cluster);
		
		driver.findElement(By.id("Container:pbMain:frm1:j_id15")).clear();
		driver.findElement(By.id("Container:pbMain:frm1:j_id15")).sendKeys(entityUnitType);
		
		driver.findElement(By.id("Container:pbMain:frm1:j_id17")).clear();
		driver.findElement(By.id("Container:pbMain:frm1:j_id17")).sendKeys(AUTeam);
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		
		WebElement regional =  driver.findElement(By.name("Container:pbMain:frm1:j_id19"));
		Select st = new Select(regional);
		st.selectByVisibleText(regionalEntityDirector);
		
		WebElement auLead =  driver.findElement(By.name("Container:pbMain:frm1:j_id22"));
		Select st1 = new Select(auLead);
		st1.selectByVisibleText(AULead);
		
		WebElement clusterText =  driver.findElement(By.name("Container:pbMain:frm1:j_id25"));
		Select st2 = new Select(clusterText);
		st2.selectByVisibleText(clusterLead);
		
		WebElement country =  driver.findElement(By.id("Container:pbMain:frm1:j_id28"));
		Select st3 = new Select(country);
		st3.selectByVisibleText(countries);
		
		WebElement regionText =  driver.findElement(By.name("Container:pbMain:frm1:j_id30"));
		Select st4 = new Select(regionText);
		st4.selectByVisibleText(region);
		
		WebElement auditTable =  driver.findElement(By.id("Container:pbMain:frm1:j_id37"));
		Select st5 = new Select(auditTable);
		st5.selectByVisibleText(activeAuditableUnit);
		
		//Clicking on Save Button
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		driver.findElement(By.name("Container:pbMain:frm1:j_id41")).click();	
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
}


