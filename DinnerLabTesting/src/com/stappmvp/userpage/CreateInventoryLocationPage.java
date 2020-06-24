package com.stappmvp.userpage;

import java.beans.Statement;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.dinnerLab.util.ExcelLib;

public class CreateInventoryLocationPage
{
WebDriver driver;
	
	public String Location;
    public int rowCount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean InventoryLocationstatus;
    int i =0;
	    
	    
	    private static Logger log = Logger.getLogger (CreateInventoryLocationPage.class);
	    
	    //Test Case for Reading the excel Quotation data.
	    public boolean InventoryLocationInitialPage(WebDriver driver) throws InvalidFormatException
		{
			try
			{
				ExcelLib xllib = new ExcelLib();
				int InventoryLocationRowCount = xllib.getRowCount("InventoryLocation");
				System.out.println("InventoryLocationRowCount:"+InventoryLocationRowCount);
				log.info("InventoryLocationRowCount log initialized ");
				for (i = 1; i <= InventoryLocationRowCount; i++) 
				{
					Location = xllib.getExcelData("InventoryLocation", i, 0);
					
					InventoryLocationstatus = CreateInventoryLocation(driver,Location);
					System.out.println("Login Count:"+i);					
				}
			}
			catch(Exception e)
			{
				System.out.println("CreateInventoryLocation Unsucessful");
				e.printStackTrace();
			}
			return InventoryLocationstatus;
		}

		private boolean CreateInventoryLocation(WebDriver driver,
				String location) 
		{							
				try
				{
					driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
					Thread.sleep(10000);
					//Inspect for InventoryLocation tab					
					driver.findElement(By.cssSelector("a[title='Inventory Locations Tab']")).click();
					log.info("InventoryLocation Tab");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);			
					
					
					//Inspecting for New button
					driver.findElement(By.xpath("//input[@value=' New ']")).click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					System.out.println("New button ");
					
					
					//Inspect for Location lookup field
					Thread.sleep(4000);
					driver.findElement(By.xpath("//img[@title='Location Lookup (New Window)']")).click(); //lookup icon
					String parentWindow = driver.getWindowHandle();
					Set<String> handles =  driver.getWindowHandles();
					for(String windowHandle  : handles)
				       {
				       if(!windowHandle.equals(parentWindow))
				          {
				          driver.switchTo().window(windowHandle);
				          driver.switchTo().frame(0);//frame1
				          driver.findElement(By.id("lksrch")).clear();
				          driver.findElement(By.id("lksrch")).sendKeys(Location);//Search input
				          Thread.sleep(2000);
				          driver.findElement(By.xpath("//input[@name='go']")).click();//Go button
				          driver.switchTo().defaultContent();
				          driver.switchTo().frame(1);//frame2
				          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); 
				          Thread.sleep(2000);
				          //driver.close(); //closing child window
				          driver.switchTo().window(parentWindow); //cntrl to parent wind
				          }//if loop
				       }//for loop
				   	Thread.sleep(2000);
					log.info("Select Location lookup field");
					
					
					//Inspect for save button
					driver.findElement(By.xpath("//td[@id='bottomButtonRow']/input[1]")).click();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					log.info("Select save button");	
					
					
					return true;
					
				}
				catch(Exception e)
				{
					System.out.println("createProduct Unsuccessful");
					return false;
					
				}
			}
		}
		
						
					
					
					
					
					
					
			