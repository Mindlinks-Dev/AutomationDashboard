/**
 * @author: Basappa Hunsikatti
 * @Created Date :30/11/2015
 * @Updated Date :
 * @Comments This automation class will manage Member/Chef Cities.
 */
package com.dinnerLab.userPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.dinnerLab.util.ExcelLib;

public class ManageCitiesPage 
{
	private String loginURL;
	private String loginEmail;
	private String loginPassword;
	public String selectAll;
	public String Atlanta;
	public String Austin;
	public String Baltimore;
	public String BatonRouge;
	public String Birmingham;
	public String Boston;
	public String Charlotte;
	public String Chicago;
	public String Cleveland;
	public String Columbus;
	public String DC;
	public String Dallas;
	public String Denver;
	public String Houston;
	public String Indianapolis;
	public String KansasCity;
	public String LosAngeles;
	public String Miami;
	public String Milwaukee;
	public String Minneapolis;
	public String Nashville;
	public String NewOrleans;
	public String NewYork;
	public String Philadelphia;
	public String Phoenix;
	public String Pittsburgh;
	public String Portland;
	public String Raleigh;
	public String SanAntonio;
	public String SanDiego;
	public String SanFrancisco;
	public String Seattle;
	public String StLouis;
	boolean present;
	public int rowCount;
	String isTestPassed="FAIL";
	public static boolean selectedCitiesStatus;
	private static Logger log = Logger.getLogger(ManageCitiesPage.class);
	 
	 /**
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void manageCitiesInitialPage(WebDriver driver)
	 {
		try
		{
			ExcelLib xllib = new ExcelLib();
			 
			int rowCount = xllib.getRowCount("ManageCities");
			log.info("********************* Manage Cities Logger Initialized******************************* ");
			for(int i=1;i<= rowCount;i++)
			{
				loginURL = xllib.getExcelData("ManageCities", i, 0);
				loginEmail = xllib.getExcelData("ManageCities", i, 1);
				loginPassword = xllib.getExcelData("ManageCities", i, 2);

				selectAll = xllib.getExcelData("ManageCities", i, 3);
				Atlanta	= xllib.getExcelData("ManageCities", i, 4);	
				Austin	= xllib.getExcelData("ManageCities", i, 5);	
				Baltimore = xllib.getExcelData("ManageCities", i, 6);
			 	BatonRouge = xllib.getExcelData("ManageCities", i, 7);
			 	Birmingham = xllib.getExcelData("ManageCities", i, 8);
			 	Boston = xllib.getExcelData("ManageCities", i, 9);
				Charlotte = xllib.getExcelData("ManageCities", i, 10);
				Chicago = xllib.getExcelData("ManageCities", i, 11);
				Cleveland = xllib.getExcelData("ManageCities", i, 12);
				Columbus = xllib.getExcelData("ManageCities", i, 13);
				DC = xllib.getExcelData("ManageCities", i, 14);
				Dallas = xllib.getExcelData("ManageCities", i, 15);
				Denver	= xllib.getExcelData("ManageCities", i, 16);	
				Houston	= xllib.getExcelData("ManageCities", i, 17);	
				KansasCity = xllib.getExcelData("ManageCities", i, 18);
				LosAngeles = xllib.getExcelData("ManageCities", i, 19);
				Miami = xllib.getExcelData("ManageCities", i, 20);
				Milwaukee = xllib.getExcelData("ManageCities", i, 21);
				Minneapolis = xllib.getExcelData("ManageCities", i, 22);
				Nashville = xllib.getExcelData("ManageCities", i, 23);
				NewOrleans = xllib.getExcelData("ManageCities", i, 24);
				NewYork = xllib.getExcelData("ManageCities", i, 25);
				Philadelphia = xllib.getExcelData("ManageCities", i, 26);	
				Phoenix	= xllib.getExcelData("ManageCities", i, 27);	
				Pittsburgh = xllib.getExcelData("ManageCities", i, 28);
				Portland = xllib.getExcelData("ManageCities", i, 29);
				SanAntonio = xllib.getExcelData("ManageCities", i, 30);
				SanDiego = xllib.getExcelData("ManageCities", i, 31);
				SanFrancisco = xllib.getExcelData("ManageCities", i, 32);
				Seattle = xllib.getExcelData("ManageCities", i, 33);
				StLouis = xllib.getExcelData("ManageCities", i, 34);
				
				
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				//Calling Login URL method
				loginLogout.loginURLActions(driver,loginURL);
				
				//Calling Member Login Method
				loginLogout.loginPageActions(driver,loginEmail,loginPassword);
						
				FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
				
				//Checking whether login credentials valid or not
				if(MemberLoginLogoutPage.loggedInStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("ManageCities", i, 35, isTestPassed);
					
					//Calling Manage Cities method
					clickingOnManageCities(driver);
					
					//Calling select cities actions method
					freeMember.selectCitiesActions(driver,selectAll,Atlanta,Austin,Baltimore,BatonRouge,Birmingham,Boston,Charlotte,Chicago,Cleveland,Columbus,DC,Dallas,Denver,Houston,KansasCity,LosAngeles,Miami,Milwaukee,Minneapolis,Nashville,NewOrleans,NewYork,Philadelphia,Phoenix,Pittsburgh,Portland,SanAntonio,SanDiego,SanFrancisco,Seattle,StLouis);
					
					//Calling verify Selected Cities method
					verifySelectedCities(driver);
					
					if(rowCount !=1)
					{
						//Calling Logout method
						loginLogout.logoutActions(driver);
					}
				}else if(!MemberLoginLogoutPage.loggedInStatus)
			    {
			    	isTestPassed = "FAIL";
					xllib.writeToExcel("ManageCities", i, 35, isTestPassed);
				}
				if(selectedCitiesStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("ManageCities", i, 36, isTestPassed);
				}else if(!selectedCitiesStatus)
				{
					isTestPassed = "FAIL";
					xllib.writeToExcel("ManageCities", i, 36, isTestPassed);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Test Execution Failed");
		}
	 }
	 /**
		 * Test Case for clicking on Manage Cities
		 * Input: WebDriver
		 * Output: Void
	*/
	 public void clickingOnManageCities(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	 {
		 try
		 {	
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 Actions actions = new Actions(driver);
				
			 WebElement parent = driver.findElement(By.xpath("//li[@class='parent']")); // member drop down
			 actions.moveToElement(parent).build().perform();

			 driver.findElement(By.xpath("//a[contains(text(),'Manage Cities')]")).click();
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
	 /**
		 * Test Case for verifying updated cities
		 * Input: WebDriver
		 * Output: Boolean
		 * @return 
		 * @throws InvalidFormatException 
	*/
	public  void verifySelectedCities(WebDriver driver)
	{
		try
		{
			 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			 Thread.sleep(3000);
			 selectedCitiesStatus = driver.findElement(By.xpath("//form[input[@id='submitManageCities']]/div[1]")).isDisplayed();
			 String selectedCitiesMessage = driver.findElement(By.xpath("//form[input[@id='submitManageCities']]/div[1]")).getText();
			 log.info(selectedCitiesMessage);
			 System.out.println("selectedCitiesStatus:"+selectedCitiesStatus);
			 System.out.println("selectedCitiesMessage:"+selectedCitiesMessage);
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 }catch(Exception e)
		 {
			e.printStackTrace();
		 }
	}
}