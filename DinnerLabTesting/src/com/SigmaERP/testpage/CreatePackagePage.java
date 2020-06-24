package com.SigmaERP.testpage;

import java.beans.Statement;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.util.ExcelLib;

public class CreatePackagePage 
{
WebDriver driver;
	
	public String Customer;
	public String PackagedLocation;
	public String PackageStatus;
	public String PackagedDate;
	public String Order;
	public String ProductCheckBox;
	public String PackageDate;
	public String PackageProductStatus;
	public int rowCount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean Packagestatus;
    int i =0;
    
private static Logger log = Logger.getLogger (CreatePackagePage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean PackageInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int createPackagesRowCount = xllib.getRowCount("Packages");
			System.out.println("createPackagesRowCount:"+createPackagesRowCount);
			log.info("createPackagesRowCount log initialized ");
			for (i = 1; i <= createPackagesRowCount; i++) 
			{
				Customer = xllib.getExcelData("Packages", i, 0);
				PackagedLocation = xllib.getExcelData("Packages", i, 1);
				PackageStatus = xllib.getExcelData("Packages", i, 2);
				Order  = xllib.getExcelData("Packages", i, 3);
				PackageProductStatus  = xllib.getExcelData("Packages", i, 4);
				
				Packagestatus = CreatePackage(driver,Customer,PackagedLocation,PackageStatus,PackagedDate,Order,ProductCheckBox,PackageDate,PackageProductStatus);
				System.out.println("Login Count:"+i);
			}
		}
		catch(Exception e)
		{
			System.out.println("Package Unsucessful");
			e.printStackTrace();
		}
		return Packagestatus;
	}

	private boolean CreatePackage(WebDriver driver, String customer,String packagedLocation, 
			String packageStatus,String packagedDate, String order, String productCheckBox,String packageDate,
			String packageProductStatus)
	{

		try
		{
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			Thread.sleep(8000);
			//Inspect for Package tab
			driver.findElement(By.xpath("//li[@id='01r28000000tS2u_Tab']/a")).click();
			log.info("Package Tab");
			Thread.sleep(4000);
			
			
			
			//Inspecting for New button
			driver.findElement(By.xpath("//input[@value=' New ']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("New button ");
			
		
			//Inspecting for Customer lookup field
			Thread.sleep(4000);
			driver.findElement(By.xpath("//img[@title='Customer Lookup (New Window)']")).click(); 
			String parentWindow = driver.getWindowHandle();
			Set<String> handles =  driver.getWindowHandles();
			for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(parentWindow))
		          {
		          driver.switchTo().window(windowHandle);
		          driver.switchTo().frame(0);//frame1
		          driver.findElement(By.id("lksrch")).clear();
		          driver.findElement(By.id("lksrch")).sendKeys(Customer);//Search input
		          Thread.sleep(2000);
		          driver.findElement(By.xpath("//input[@value=' Go! ']")).click();//Go button
		          driver.switchTo().defaultContent();
		          driver.switchTo().frame(1);//frame2
		          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); 
		          Thread.sleep(2000);		    
		          driver.switchTo().window(parentWindow); //cntrl to parent wind
		          }//if loop
		       }//for loop		   
			log.info("Select Customer lookup field");
			
			
			
			//Inspecting for PackagedLocation lookup field
			Thread.sleep(4000);
			driver.findElement(By.xpath("//img[@title='Packaged Location Lookup (New Window)']")).click(); 
			String parentWindow1 = driver.getWindowHandle();
			Set<String> handles1 =  driver.getWindowHandles();
			for(String windowHandle  : handles1)
		       {
		       if(!windowHandle.equals(parentWindow1))
		          {
		          driver.switchTo().window(windowHandle);
		          driver.switchTo().frame(0);//frame1
		          driver.findElement(By.id("lksrch")).clear();
		          driver.findElement(By.id("lksrch")).sendKeys(PackagedLocation);//Search input
		          Thread.sleep(2000);
		          driver.findElement(By.xpath("//input[@value=' Go! ']")).click();//Go button
		          driver.switchTo().defaultContent();
		          driver.switchTo().frame(1);//frame2
		          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); 
		          Thread.sleep(2000);		    
		          driver.switchTo().window(parentWindow1); //cntrl to parent wind
		          }//if loop
		       }//for loop		   
			log.info("Select PackagedLocation lookup field");
			
			
			
			//Inspect for PackagedDate text field
			driver.findElement(By.xpath(".//*[@id='packPage:frmNew:newBlock:pbid']/div[2]/table/tbody/tr[2]/td[2]/span/span/a")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("PackagedDate text field");
			
			
			
			//Inspecting for PackageStatus drop down
			WebElement clusterText =  driver.findElement(By.id("packPage:frmNew:newBlock:pbid:j_id36"));
			Select st2 = new Select(clusterText);
			Thread.sleep(6000);
			st2.selectByVisibleText(PackageStatus);
			System.out.println("PackageStatus");
			
			
			
			//Inspect for Order lookup field
			driver.findElement(By.xpath("//span[@id='packPage:frmNew:newBlock:newSection:newTable:0:opPanel']/img")).click();
			Thread.sleep(3000);
			String parentWindow2 = driver.getWindowHandle();
			Set<String> handles2 =  driver.getWindowHandles();
			for(String windowHandle  : handles2)
		    {
		       if(!windowHandle.equals(parentWindow2))
		          {
		    	   	driver.switchTo().window(windowHandle);
		    	   	//driver.switchTo().frame(0);//frame1
		    	   	driver.findElement(By.xpath("//input[@type='text']")).clear();
		    	   	driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Order);//Search input
		            driver.findElement(By.xpath("//input[@value='Go']")).click();//Go button
		          
		            //driver.switchTo().defaultContent();
		            // driver.switchTo().frame(1);
		            driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		            driver.findElement(By.xpath("//a[contains(text(),'"+Order+"')]")).click(); // 
		            Thread.sleep(2000);
			        driver.switchTo().window(parentWindow2); //cntrl to parent wind
			      }//if loop
			 }//for loop
			log.info("Select Order lookup field");
			
			
			
			
			//Inspecting for ProductCheckBox button
			 Thread.sleep(4000);
			driver.findElement(By.xpath("//td[@id='packPage:frmNew:newBlock:newSection:newTable:0:j_id46']/input[2]")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("ProductCheckBox button ");
			
			
			
			
			//Inspect for PackageDate text field
			driver.findElement(By.xpath(".//*[@id='packPage:frmNew:newBlock:newSection:newTable:0:j_id68']/span/span/a")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("PackageDate text field");
			
			
			
			//Inspecting for PackageProductStatus drop down
			WebElement cluster =  driver.findElement(By.id("packPage:frmNew:newBlock:newSection:newTable:0:j_id71"));
			Select st1 = new Select(cluster);
			Thread.sleep(6000);
			st1.selectByVisibleText(PackageProductStatus);
			System.out.println("PackageProductStatus");
			
			
			//Inspect for save button
			driver.findElement(By.xpath("//input[@id='packPage:frmNew:newBlock:j_id74:Save']")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("Select save button");
			
			return true;					
		}
		
		catch(Exception e)
		{
			System.out.println("CreatePackage Unsuccessful");
			return false;
			
		}
	}

}