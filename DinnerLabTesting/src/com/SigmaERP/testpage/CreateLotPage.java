package com.SigmaERP.testpage;

import java.beans.Statement;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;    

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.SigmaERP.testclass.SigmaERPtestbase;
import com.dinnerLab.util.ExcelLib;

public class CreateLotPage extends SigmaERPtestbase
{
WebDriver driver;
	
	public String Lotname;
	public String Product;
	public String CreateDate;
	public int rowCount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	private String newLotNumber;
	public boolean Lotstatus;
    int i =0; 
    private static Logger log = Logger.getLogger (CreateLotPage.class);
    
    
    
  
    //Test Case for Reading the excel Quotation data.
    public boolean LotInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int createLotRowCount = xllib.getRowCount("Lot");
			System.out.println("createLotRowCount:"+createLotRowCount);
			log.info("createLotRowCount log initialized ");
			for (i = 1; i <= createLotRowCount; i++) 
			{
				Lotname = xllib.getExcelData("Lot", i, 0);
				Product = xllib.getExcelData("Lot", i, 1);
				
				
				log.info("product" + Product);
				
				
				Lotstatus = CreateLot(driver,Lotname,Product,CreateDate);
				System.out.println("Login Count:"+i);
					
				
				if(Lotstatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("Lot", i, 2, isTestPassed);
					xllib.writeToExcel("Lot", i, 3, newLotNumber);
				}
				else if(!Lotstatus)
				{
					isTestPassed="FAIL";
					xllib.writeToExcel("Lot", i, 2, isTestPassed);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Lot Unsucessful");
			e.printStackTrace();
		}
		return Lotstatus;
	}

	private boolean CreateLot(WebDriver driver, String lotname,
			String product, String createDate) throws Exception
	{
		
		try
		{
			Thread.sleep(8000);
			/**Inspect for lot tab
			* 
			*/
			driver.findElement(By.xpath("//li[@id='01r28000000tnJ7_Tab']/a")).click();
			log.info("lot Tab");
			Thread.sleep(5000);	
			
			try {
			/**click on "no thanks"button on switching to lightning
			* 
			*/
			driver.findElement(By.xpath("//a[@title=\"Close\"]")).click();			
			System.out.println("no thanks");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			/**Inspecting for New button
			* 
			*/
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@value=' New ']")).click();
			System.out.println("New button ");
			
			
			/**Inspecting for Lotname text field	
			* 		
			*/
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@id='00N2800000HOhdg']")).sendKeys(lotname);
			System.out.println("Lotname text field ");
		
		
			/**Inspect for Product lookup field
			* 
			*/				
			Thread.sleep(5000);
			driver.findElement(By.xpath("//img[@title='Product Lookup (New Window)']")).click();
			
			Set<String> handles =  driver.getWindowHandles();
			System.out.println(handles.size());
			
			Iterator<String> tab =driver.getWindowHandles().iterator();
			
			String  PW =	tab.next();
			String  SW =	tab.next();
			//String CW =tab.next();
				System.out.println("switching to window ");
				try {
				
				driver.switchTo().window(SW);
				System.out.println("sw");
				}
				
				catch(Exception e)
				{
					//driver.switchTo().window(CW);
					System.out.println("cw");
				}
				  driver.switchTo().frame(0);//frame1
		          Thread.sleep(3000);
		          System.out.println("inside child window");
		         // driver.findElement(By.xpath("//input[@name=\"lksrch\"]")).clear();
		          driver.findElement(By.xpath("//input[@name=\"lksrch\"]")).sendKeys(product);//Search input
		          Thread.sleep(3000);
		          System.out.println("click go");
		          driver.findElement(By.xpath(".//*[@id='theForm']/div/div[2]/input[2]")).click();//Go button
		          driver.switchTo().defaultContent();
		          driver.switchTo().frame(1);//frame2
		          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); 
		          Thread.sleep(2000);		    
		          driver.switchTo().window(PW); //cntrl to parent wind
			
		
			/**Inspect for CreateDate text field
			* 
			*/
			Thread.sleep(5000);
			driver.findElement(By.xpath(".//*[@id='ep']/div[2]/div[3]/table/tbody/tr[2]/td[4]/span/span/a")).click();
			System.out.println("CreateDate text field");
			
			
			/**Inspect for save button
			* 
			*/
			Thread.sleep(5000);
			driver.findElement(By.xpath("//td[@id='bottomButtonRow']/input[1]")).click();
			System.out.println("Select save button");			
		}
		
		catch(Exception e)
		{
			System.out.println("Lot Unsuccessful");			
			
		}
		return Lotstatus;
	}

}