package com.stos.usersPage.helpers;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;


public class OpportunityTestPage 
{
	private String OpportunityName;
	private String AccountName;
	private String Type;
	private String Amount;
	private String CloseDate;
	private String Stage;
	private String CreateOpp;
	private String ModifyOpp;
	private String SearchOppor;
	private String PrimaryCampaignSource;
	private String Description;
	private String Result;
	
	String isTestPassed="FAIL";
	public String text="Home";
	
	public static boolean oppStatus; 
	public static boolean oppConditionStatus;
	
	public int rowCount;
	int i=0;
	ExcelLib xllib = new ExcelLib();
	
	private static Logger log = Logger.getLogger(OpportunityTestPage.class);
	public WebDriver driver;

	public void opportunityTestInitialPage(WebDriver driver)
	{
		
		try
		{
			rowCount= xllib.getRowCount("STOSopp");
		 	for (i = 1; i <= rowCount; i++) 
			{
		 		OpportunityName = xllib.getExcelData("STOSopp", i, 0);
		 		AccountName = xllib.getExcelData("STOSopp", i, 1);
		 		Type = xllib.getExcelData("STOSopp", i, 2);
		 		Amount = xllib.getExcelData("STOSopp", i, 3);
		 		CloseDate = xllib.getExcelData("STOSopp", i, 4);
		 		Stage = xllib.getExcelData("STOSopp", i, 5);
		 		CreateOpp = xllib.getExcelData("STOSopp", i, 6);
		 		ModifyOpp = xllib.getExcelData("STOSopp", i, 7);
		 		SearchOppor = xllib.getExcelData("STOSopp", i, 8);
		 		Result = xllib.getExcelData("STOSopp", i, 9);
		 		PrimaryCampaignSource = xllib.getExcelData("STOSopp", i, 10);
		 		Description = xllib.getExcelData("STOSopp", i, 11);
		 		
		 		oppConditionStatus = opportunityTestConditionPage(driver,CreateOpp,ModifyOpp,SearchOppor);
		 		
			}
		}
		catch(Exception e)
		{
			log.info("Opportunity was Unsuccessfully created");
			e.printStackTrace();
		}
	}

	private boolean opportunityTestConditionPage(WebDriver driver,
			String createOpp, String modifyOpp,String searchOppor) 
	{

		try
		{
			log.info("Opportunity Flow:");
			log.info("---------------------------");
			
			Thread.sleep(10000);
			if(driver.findElement(By.xpath("//li[@id='home_Tab']/a")).getText().contains(text))
			{
				log.info("Logged into the Application");
				
				Thread.sleep(8000);
				driver.get("https://stosdev-dev-ed.my.salesforce.com/006/o");//Opportunity tab URL
				if(createOpp.equalsIgnoreCase(TestConstants.CREATEOPP))
				{
					log.info("Creating New Opportunity");
					log.info("************************");
					
					Thread.sleep(8000);
					driver.findElement(By.xpath("//input[@title='New']")).click();//New Button
					//calling OpportunityAction method
					oppStatus=opportunityTestActionPage(driver,OpportunityName,AccountName,Type,Amount,CloseDate,Stage,PrimaryCampaignSource,Description);
					System.out.println("Opportunity Status"+oppStatus);
			 			if(oppStatus)
			 			{
			 				log.info("Opportunity was successfully created");
			 				isTestPassed="PASS";
							xllib.writeToExcel("STOSopp", i, 9, isTestPassed);
			 			}
			 			else if(!oppStatus)
			 			{
			 				log.info("Opportunity was Unsuccessfully created");
			 				isTestPassed="FAIL";
							xllib.writeToExcel("STOSopp", i, 9, isTestPassed);
			 				
			 			}
				}//end of create if
				else if(modifyOpp.equalsIgnoreCase(TestConstants.MODIFYOPP))
				{
					log.info("Modifying the Opportunity");
					log.info("**************************");
					
					Thread.sleep(8000);
					driver.findElement(By.xpath("//a[contains(text(),'"+searchOppor+"')]")).click(); //Search opp link
					Thread.sleep(10000);
					driver.findElement(By.xpath("//td[@id='topButtonRow']/input[@title='Edit']")).click(); // Edit button
					
					oppStatus=opportunityTestActionPage(driver,OpportunityName,AccountName,Type,Amount,CloseDate,Stage,PrimaryCampaignSource,Description);
						if(oppStatus)
						{
							log.info("Opportunity was updated successfully");
							isTestPassed="PASS";
							xllib.writeToExcel("STOSopp", i, 9, isTestPassed);
						}
						else if(!oppStatus)
						{
							log.info("Opportunity was updated Unsuccessfully");
							isTestPassed="FAIL";
							xllib.writeToExcel("STOSopp", i, 9, isTestPassed);
						}
				}//end of elseif
			}
			else
			{
				System.out.println("Not logged into the application");
			}
			return true;
		}
		catch(Exception e)
		{
			return false;
		}


	}

	private boolean opportunityTestActionPage(WebDriver driver,
			String opportunityName, String accountName, String type,
			String amount, String closeDate, String stage,String primaryCampaignSource,String description)
	{
		try
		{
				Thread.sleep(3000);
				driver.findElement(By.id("opp3")).clear();
				driver.findElement(By.id("opp3")).sendKeys(opportunityName); //opportunityName
			
				log.info("Account lookup");
				Thread.sleep(6000);
				driver.findElement(By.xpath("//a[@title='Account Name Lookup (New Window)']")).click(); // Account lookup icon
				String parentWindow = driver.getWindowHandle();
				Set<String> handles =  driver.getWindowHandles();
				for(String windowHandle  : handles)
			       {
			       if(!windowHandle.equals(parentWindow))
			          {
			          driver.switchTo().window(windowHandle);
			          driver.switchTo().frame(0);//frame1
			          driver.findElement(By.id("lksrch")).clear();
			          driver.findElement(By.id("lksrch")).sendKeys(accountName);//Search input
			          
			          driver.findElement(By.xpath("//input[@title='Go!']")).click();//Go button
			          
			          driver.switchTo().defaultContent();
			          driver.switchTo().frame(1);//frame2
			          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); // Account Name
			          
			          Thread.sleep(2000);
			          //driver.close(); //closing child window
			          driver.switchTo().window(parentWindow); //cntrl to parent wind
			          }//if loop
			       }//for loop
			   
				Thread.sleep(2000);
				WebElement ele = driver.findElement(By.id("opp5"));
				Select st= new Select(ele);
				st.selectByVisibleText(type); //Type
			   
				driver.findElement(By.id("opp7")).clear();
				driver.findElement(By.id("opp7")).sendKeys(amount); //amount
			   
				driver.findElement(By.id("opp9")).clear();
				driver.findElement(By.id("opp9")).sendKeys(closeDate); //closeDate
			   
				Thread.sleep(2000);
				WebElement ele1 = driver.findElement(By.id("opp11"));
				Select st1 = new Select(ele1);
				st1.selectByVisibleText(stage); //stage
			   
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td[@id='bottomButtonRow']/input[1]")).click(); //save button
				
				/*log.info("Product Campaign Source lookup");
				Thread.sleep(6000);
				driver.findElement(By.xpath("//img[@title='Primary Campaign Source Lookup (New Window)']")).click(); // Product Campaign Source lookup icon
				String parentWindow1 = driver.getWindowHandle();
				Set<String> handles1 =  driver.getWindowHandles();
				for(String windowHandle1  : handles1)
			       {
			       if(!windowHandle1.equals(parentWindow1))
			          {
			          driver.switchTo().window(windowHandle1);
			          driver.switchTo().frame(0);//frame1
			          driver.findElement(By.id("lksrch")).clear();
			          driver.findElement(By.id("lksrch")).sendKeys(primaryCampaignSource);//Search input
			          
			          driver.findElement(By.xpath("//input[@title='Go!']")).click();//Go button
			          
			          driver.switchTo().defaultContent();
			          driver.switchTo().frame(1);//frame2
			         // driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); // Product Campaign Source Name link
			          
			          Thread.sleep(2000);
			          //driver.close(); //closing child window
			          driver.switchTo().window(parentWindow1); //cntrl to parent wind
			          }//if loop
			       }//for loop
				
				Thread.sleep(3000);
				driver.findElement(By.id("opp14")).clear();
				driver.findElement(By.id("opp14")).sendKeys(description);*/
				return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

}
