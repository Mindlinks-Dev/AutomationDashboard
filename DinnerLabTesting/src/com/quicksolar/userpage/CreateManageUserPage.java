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

public class CreateManageUserPage
{
WebDriver driver;
	
	public String FirstName;
	public String LastName;
	public String Email;
	public String PhoneNumber;	
	public String Welcome;
	public String Status;
	public String loginURL;
	public int rowCount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean NewManageUserStatus;
    int i =0;
    
 private static Logger log = Logger.getLogger (CreateManageUserPage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean ManageUserInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int ManageUserRowCount = xllib.getRowCount("CreateManageUser");
			System.out.println("ManageUserRowCount:"+ManageUserRowCount);
			log.info("ManageUserRowCount log initialized ");
			for (i = 1; i <= ManageUserRowCount; i++) 
			{
				FirstName = xllib.getExcelData("CreateManageUser", i, 0);
				LastName = xllib.getExcelData("CreateManageUser", i, 1);
				Email = xllib.getExcelData("CreateManageUser", i, 2);
				PhoneNumber = xllib.getExcelData("CreateManageUser", i, 3);
				Welcome  = xllib.getExcelData("CreateManageUser", i, 4);
				Status  = xllib.getExcelData("CreateManageUser", i, 5);
				loginURL = xllib.getExcelData("Quicksolarlogin", i, 6);
				
				NewManageUserStatus = CreateManageUser(driver,FirstName,LastName,Email,PhoneNumber,Welcome,Status,loginURL);
				System.out.println("Login Count:"+i);
			}
		}
		catch(Exception e)
		{
			System.out.println("CreateManageUser Unsucessful");
			e.printStackTrace();
		}
		return NewManageUserStatus;
	}

	private boolean CreateManageUser(WebDriver driver, String firstName,
			String lastName, String email, String phoneNumber,String Welcome,String Status,String loginURL) 
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Thread.sleep(8000);
		/*	//Inspect for welcome drop down
			WebElement clusterText =  driver.findElement(By.id("imgsrc"));
			Select st = new Select(clusterText);
			Thread.sleep(2000);
			st.selectByVisibleText(Welcome);
			System.out.println("Welcome");*/
			System.out.println("Welcome");
			driver.get(loginURL);
			
			
			//Inspect for add new user drop down
			driver.findElement(By.xpath("//a[@class='shownw_hidenw newcustinfo']")).click();
			log.info("Start new project");
			Thread.sleep(1000);
			
			
			//Inspect for FirstName text field
			driver.findElement(By.id("txtfirstname")).sendKeys(FirstName);
			log.info("FirstName text field");
			Thread.sleep(1000);
			
			
			//Inspect for LastName text field
			driver.findElement(By.id("txtlastname")).sendKeys(LastName);
			log.info("LastName text field");
			Thread.sleep(1000);
			
			
			
			//Inspect for Email text field
			driver.findElement(By.id("txtuname")).sendKeys(Email);
			log.info("Email text field");
			Thread.sleep(1000);
			
			
			//Inspect for PhoneNumber text field
			driver.findElement(By.id("txtphoneno")).sendKeys(PhoneNumber);
			log.info("PhoneNumber text field");
			Thread.sleep(1000);	
			
	
			//Inspect for Status drop down
			WebElement Text =  driver.findElement(By.id("dpdstatus"));
			Select st1 = new Select(Text);
			Thread.sleep(2000);
			st1.selectByVisibleText(Status);
			System.out.println("Status");
			
			
			//Inspect for continue button
			driver.findElement(By.id("continue-button")).click();
			log.info("continue");
			Thread.sleep(1000);
			
	        return true;
							
				
			}
			catch(Exception e)
			{
				System.out.println("CreateNewDesign Unsuccessful");
				return false;
				
			}
		}
	}