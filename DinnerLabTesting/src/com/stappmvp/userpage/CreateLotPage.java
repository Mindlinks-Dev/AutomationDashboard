package com.stappmvp.userpage;

import java.beans.Statement;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dinnerLab.util.ExcelLib;

public class CreateLotPage
{
WebDriver driver;
	
	public String Lotname;
	public String Product;
	public String CreateDate;
	public String ExpiryDate;
	public int rowCount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean Lotstatus;
    int i =0;
    
private static Logger log = Logger.getLogger (CreateLotPage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean LotInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int createLotRowCount = xllib.getRowCount("StappMVPLot");
			System.out.println("createLotRowCount:"+createLotRowCount);
			log.info("createLotRowCount log initialized ");
			for (i = 1; i <= createLotRowCount; i++) 
			{
				Lotname = xllib.getExcelData("StappMVPLot", i, 0);
				Product = xllib.getExcelData("StappMVPLot", i, 1);
				ExpiryDate = xllib.getExcelData("StappMVPLot", i, 2);
				
				
				System.out.println("ExpiryDate" + ExpiryDate);
			
				Lotstatus = CreateLot(driver,Lotname,Product,ExpiryDate);
				System.out.println("Login Count:"+i);
			}
		}
		catch(Exception e)
		{
			System.out.println("StappMVPLot Unsucessful");
			e.printStackTrace();
		}
		return Lotstatus;
	}

	private boolean CreateLot(WebDriver driver, String lotname,
			String product, String createDate)
	{
		
		try
		{
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			Thread.sleep(8000);
			//Inspect for lot tab
			driver.findElement(By.xpath("//li[@id='01r0Y0000012cLb_Tab']/a")).click();
			log.info("lot Tab");
			Thread.sleep(4000);
			
			
			
			//Inspecting for New button
			driver.findElement(By.xpath("//input[@value=' New ']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("New button ");
			
		
			//Inspecting for Lotname text field
			driver.findElement(By.id("00N0Y00000KFydB")).sendKeys(Lotname);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Lotname text field ");
		
		
			//Inspect for Product lookup field
			Thread.sleep(4000);
			driver.findElement(By.xpath("//img[@title='Product Lookup (New Window)']")).click(); 
			String parentWindow = driver.getWindowHandle();
			Set<String> handles =  driver.getWindowHandles();
			for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(parentWindow))
		          {
		          driver.switchTo().window(windowHandle);
		          driver.switchTo().frame(0);//frame1
		          driver.findElement(By.id("lksrch")).clear();
		          driver.findElement(By.id("lksrch")).sendKeys(Product);//Search input
		          Thread.sleep(2000);
		          driver.findElement(By.xpath("//input[@name='go']")).click();//Go button
		          driver.switchTo().defaultContent();
		          driver.switchTo().frame(1);//frame2
		          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); 
		          Thread.sleep(2000);		    
		          driver.switchTo().window(parentWindow); //cntrl to parent wind
		          }//if loop
		       }//for loop		   
			log.info("Select Product lookup field");
		
		
			//Inspect for CreateDate text field
			driver.findElement(By.xpath(".//*[@id='ep']/div[2]/div[3]/table/tbody/tr[2]/td[4]/span/span/a")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("CreateDate text field");
							
		
			//Inspecting for ExpiryDate text field
			driver.findElement(By.xpath(".//*[@id='ep']/div[2]/div[3]/table/tbody/tr[3]/td[4]/div/span/span/a")).click();
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
			System.out.println("Lot Unsuccessful");
			return false;
			
		}
	}

}


