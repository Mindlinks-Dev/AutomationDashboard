package com.quicksolar.userpage;

import java.beans.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.util.ExcelLib;

public class CreateNewProjectPage 
{
WebDriver driver;
	
	public String FirstName;
	public String LastName;
	public String StreetAddress;
	public String City;
	public String State;
	public String PostalCode;
	public String Country;
	public String Email;	
	public int rowCount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean NewProjectStatus;
    int i =0;
    
    
    
 private static Logger log = Logger.getLogger (CreateNewProjectPage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean NewProjectInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int NewProjectRowCount = xllib.getRowCount("CreateNewProject");
			System.out.println("NewProjectRowCount:"+NewProjectRowCount);
			log.info("NewProjectRowCount log initialized ");
			for (i = 1; i <= NewProjectRowCount; i++) 
			{
				FirstName = xllib.getExcelData("CreateNewProject", i, 0);
				LastName = xllib.getExcelData("CreateNewProject", i, 1);
				StreetAddress = xllib.getExcelData("CreateNewProject", i, 2);
				City = xllib.getExcelData("CreateNewProject", i, 3);
				State = xllib.getExcelData("CreateNewProject", i, 4);
				PostalCode = xllib.getExcelData("CreateNewProject", i, 5);
				Country = xllib.getExcelData("CreateNewProject", i, 6);
				Email = xllib.getExcelData("CreateNewProject", i, 7);
				
				
				
				NewProjectStatus = CreateNewProject(driver,FirstName,LastName,StreetAddress,City,State,PostalCode,Country,Email);
				System.out.println("Login Count:"+i);
				
				if(NewProjectStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("CreateNewProject", i, 11, isTestPassed);
					//xllib.writeToExcel("CreateNewProject", i, 12, isTestPassed);
				}
				else if(NewProjectStatus)
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("CreateNewProject", i, 11, isTestPassed);
					//xllib.writeToExcel("CreateNewProject", i, 12, isTestPassed);
				}
			}
		 }
		catch(Exception e)
		{
			System.out.println("CreateNewProject Unsucessful");
			e.printStackTrace();
		}
		return NewProjectStatus;
	}

	private boolean CreateNewProject(WebDriver driver, String firstName,
			String lastName, String streetAddress, String city,
			String state, String postalCode, String country, String email)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Inspect for Start new project drop down
			driver.findElement(By.xpath("//input[@value='Start New Project']")).click();
			log.info("Start new project");
			Thread.sleep(1000);
			
			
			
			//Inspect for FirstName text field
			driver.findElement(By.id("firstname")).sendKeys(FirstName);
			log.info("FirstName text field");
			Thread.sleep(1000);
			
			
			//Inspect for LastName text field
			driver.findElement(By.id("lastname")).sendKeys(LastName);
			log.info("LastName text field");
			Thread.sleep(1000);
			
			
			//Inspect for StreetAddress text field
			driver.findElement(By.id("address")).sendKeys(StreetAddress);
			log.info("StreetAddress text field");
			Thread.sleep(1000);
			
			
			//Inspect for City text field
			driver.findElement(By.id("city")).sendKeys(City);
			log.info("City text field");
			Thread.sleep(1000);
			
			
			//Inspecting for State status drop down
			WebElement clusterText =  driver.findElement(By.id("txtstate"));
			Select st = new Select(clusterText);
			Thread.sleep(2000);
			st.selectByVisibleText(State);
			System.out.println("State");
			
			
			
			//Inspect for PostalCode text field
			driver.findElement(By.id("zipcode")).sendKeys(PostalCode);
			log.info("PostalCode text field");
			Thread.sleep(1000);
			
			
			//Inspecting for Country status drop down
			WebElement Text =  driver.findElement(By.id("txtcountry"));
			Select st1 = new Select(Text);
			Thread.sleep(2000);
			st1.selectByVisibleText(Country);
			System.out.println("Country");
			
			
			//Inspect for Email text field
			driver.findElement(By.id("addemail")).sendKeys(Email);
			log.info("Email text field");
			Thread.sleep(1000);
			
					
			//Inspect for continue button
			driver.findElement(By.id("projcreate")).click();
			log.info("continue button");
			Thread.sleep(1000);
			
			return true;
						
			
		}
		catch(Exception e)
		{
			System.out.println("CreateNewProject Unsuccessful");
			return false;
			
		}
	}
}