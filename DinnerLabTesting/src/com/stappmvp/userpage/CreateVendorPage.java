package com.stappmvp.userpage;

import java.beans.Statement;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dinnerLab.util.ExcelLib;

public class CreateVendorPage 
{
WebDriver driver;
	
	public String AccountName;
	public String MinQty;
	public String MinimumOrderQty;
	public int rowCount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean Vendorstatus;
    int i =0;
    
private static Logger log = Logger.getLogger (CreateVendorPage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean VendorInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int VendorRowCount = xllib.getRowCount("Vendor");
			System.out.println("VendorRowCount:"+VendorRowCount);
			log.info("VendorRowCount log initialized ");
			for (i = 1; i <= VendorRowCount; i++) 
			{
				AccountName = xllib.getExcelData("Vendor", i, 0);
				MinQty = xllib.getExcelData("Vendor", i, 1);				
				MinimumOrderQty = xllib.getExcelData("Vendor", i, 2);
							
				Vendorstatus = CreateVendor(driver,AccountName,MinQty,MinimumOrderQty);
				System.out.println("Login Count:"+i);
			}
		}
		catch(Exception e)
		{
			System.out.println("Vendor Unsucessful");
			e.printStackTrace();
		}
		return Vendorstatus;
	}

	private boolean CreateVendor(WebDriver driver, String accountName,
			String minQty,String MinimumOrderQty)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			//Inspect for Product tab			
			driver.findElement(By.cssSelector("a[title='Products Tab']")).click();
			log.info("Product Tab");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			
			
			//Inspecting for Product 
			driver.findElement(By.xpath("//td[@id='bodyCell']/div[6]/div[1]/div/div[2]/table/tbody/tr[2]/th/a")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			log.info("Product");
			
			
			//Inspecting for CreateNewVendor button
			driver.findElement(By.xpath("//input[@title='New Vendor Product']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			log.info("CreateNewVendor button");
			
			
			
			//Inspect for AccountName lookup field
			Thread.sleep(4000);
			driver.findElement(By.xpath("//img[@title='Account Name Lookup (New Window)']")).click(); 
			String parentWindow = driver.getWindowHandle();
			Set<String> handles =  driver.getWindowHandles();
			for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(parentWindow))
		          {
		          driver.switchTo().window(windowHandle);
		          driver.switchTo().frame(0);//frame1
		          driver.findElement(By.id("lksrch")).clear();
		          driver.findElement(By.id("lksrch")).sendKeys(AccountName);//Search input
		          Thread.sleep(2000);
		          driver.findElement(By.xpath("//input[@type='submit']")).click();//Go button
		          driver.switchTo().defaultContent();
		          driver.switchTo().frame(1);//frame2
		          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); 
		          Thread.sleep(2000);		    
		          driver.switchTo().window(parentWindow); //cntrl to parent wind
		          }//if loop
		       }//for loop		   
			log.info("Select Product lookup field");
			
			
			
			//Inspecting for minqty text field
			driver.findElement(By.id("00N0Y00000KFyje")).sendKeys(MinQty);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("MinQty text field ");
			
			
			//Inspecting for MinimumOrderQty text field
			driver.findElement(By.id("00N0Y00000KFyjh")).sendKeys(MinimumOrderQty);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("MinimumOrderQty text field ");
			
			
			//Inspecting for ExpiryDate text field
			driver.findElement(By.xpath(".//*[@id='ep']/div[2]/div[3]/table/tbody/tr[3]/td[4]/span/span/a")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("ExpiryDate text field ");
			
			
			//Inspect for save button
			driver.findElement(By.xpath("//td[@id='bottomButtonRow']/input[1]")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("Select save button");
			
			
			return true;					
		}
		
		catch(Exception e)
		{
			System.out.println("vendor Unsuccessful");
			return false;
			
		}
	}

}
			
			
			
			
			
			
			
			
			
			
			
	