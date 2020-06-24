package com.autoparts.userpage;

import java.beans.Statement;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dinnerLab.util.ExcelLib;


public class CreateQuotationPartsPage 
{
	WebDriver driver;
	//private String loginUrl=TestConstants.LogIn_Url;
	private String loginUsername;
	private String loginPassword;
	private String loginURL;
	//Connection con = Database.getConnection();
	// boolean isTestPass=false;
	public String Quotation;
	public String Partname;
	public String Parttype;
	public String Partmake;
	public String Partmodel;
	public String Partyear;
	public String Partquantity;
	public String Netamount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static String result;
	public static String comment;
	public static boolean newQuotationsPartstatus;
	int i=0;
	
	private static Logger log = Logger.getLogger (UserLoginLogoutPage.class);
	
	//Test Case for Reading the excel QuotationParts data.
	public boolean QuotationPartsInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			int createquotationpartsRowCount = xllib.getRowCount("QUOTATION PARTS");
			System.out.println("createquotationpartsRowCount:"+createquotationpartsRowCount);
			System.out.println("*********************QuotationPARTS Logger Initialized*****************************************");
			for (i = 1; i <= createquotationpartsRowCount; i++) 
			{
				System.out.println("excel reading");
		 		//Reading creating Account excel data
				Quotation = xllib.getExcelData("QUOTATION PARTS", i, 0);
				Partname = xllib.getExcelData("QUOTATION PARTS", i, 1);
				Parttype = xllib.getExcelData("QUOTATION PARTS", i, 2);
				Partmake = xllib.getExcelData("QUOTATION PARTS", i, 3);
				Partmodel = xllib.getExcelData("QUOTATION PARTS", i, 4);
				Partyear = xllib.getExcelData("QUOTATION PARTS", i, 5);
				Partquantity = xllib.getExcelData("QUOTATION PARTS", i, 6);
				Netamount = xllib.getExcelData("QUOTATION PARTS", i, 7);
				
				//Calling Login URL method
				newQuotationsPartstatus = CreateQuotationpartspage(driver,loginURL,loginUsername,loginPassword,Quotation,Partname,Parttype,Partmake,Partmodel,Partyear,Partquantity,Netamount);
				System.out.println("Login Count1:"+i);	
				}
		    }
		
		catch(Exception e)
		{
			System.out.println("Login Unsucessful");
			e.printStackTrace();
		}
		return newQuotationsPartstatus;
	}
	
	public boolean CreateQuotationpartspage(WebDriver driver,String loginURL,
			String loginUsername, String loginPassword,String Quotation,
			String Partname, String Parttype,
			String Partmake,String Partmodel,String Partyear,String Partquantity,String Netamount) throws InvalidFormatException
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Inspecting for QuotationParts Tab
			driver.findElement(By.cssSelector("a[href='/a0U/o']")).click();
			//Inspecting for New button
			driver.findElement(By.xpath("//input[@value=' New ']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Creating new quoparts");
			
			//Select quotation lookup
			/*driver.findElement(By.xpath("//input[@id='CF00N9000000EoEG7']")).sendKeys(Quotation);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
			Thread.sleep(6000);
			driver.findElement(By.xpath("//img[@title='Quotation Lookup (New Window)']")).click(); // quotation lookup icon
			String parentWindow = driver.getWindowHandle();
			Set<String> handles =  driver.getWindowHandles();
			for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(parentWindow))
		          {
		          driver.switchTo().window(windowHandle);
		          driver.switchTo().frame(0);//frame1
		          driver.findElement(By.id("lksrch")).clear();
		          driver.findElement(By.id("lksrch")).sendKeys(Quotation);//Search input
		          driver.findElement(By.xpath("//input[@title='Go!']")).click();//Go button
		          
		          driver.switchTo().defaultContent();
		          driver.switchTo().frame(1);//frame2
		          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); // quotation Name
		          Thread.sleep(2000);
		          //driver.close(); //closing child window
		          driver.switchTo().window(parentWindow); //cntrl to parent wind
		          }//if loop
		       }//for loop
		   	Thread.sleep(2000);
			System.out.println("quotation lookup");
			
			//Select Partname textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEHM']")).sendKeys(Partname);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Partname textfield");
			//Select Parttype textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEHO']")).sendKeys(Parttype);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Parttype textfield");
			//Select Partmake textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEHK']")).sendKeys(Partmake);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Partmake textfield");
			//Select Partmodel textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEHL']")).sendKeys(Partmodel);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Partmodel textfield");
			//Select Partyear textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEHP']")).sendKeys(Partyear);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Partyear textfield");
			//Select Partquantity textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEHN']")).clear();
			driver.findElement(By.xpath("//input[@id='00N9000000EoEHN']")).sendKeys(Partquantity);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Partquantity textfield");
			//Select Netamount textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEHN']")).clear();
			driver.findElement(By.xpath("//input[@id='00N9000000EoEHN']")).sendKeys(Netamount);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Netamount textfield");
			//Inspecting for Save button
			driver.findElement(By.xpath("//input[@tabindex='11']")).click();
			System.out.println("save");	
			//QuotationInitialPage(driver);
			
			/*if(!newQuotationsPartstatus)
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				System.out.println("CreatequotationParts process is complete...");*/
				return true;
			}
			catch(Exception e)
			{
				System.out.println("CreatequotationParts Unsuccessful");
				return false;
							}
		                }
					}

