package com.jandk.userpage;

import java.beans.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dinnerLab.util.ExcelLib;

public class CreateProductPage
{
WebDriver driver;
	
	public String ProductName;
	public String Stockkeepingunit;
	public String ProductDescription;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean newProductstatus;
    int i =0;
    
    private static Logger log = Logger.getLogger (CreateProductPage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean ProductInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int createproductRowCount = xllib.getRowCount("Product");
			System.out.println("createproductRowCount:"+createproductRowCount);
			log.info("Createproduct log initialized ");
			for (i = 1; i <= createproductRowCount; i++) 
			{
				ProductName = xllib.getExcelData("Product", i, 0);
				Stockkeepingunit = xllib.getExcelData("Product", i, 1);
				ProductDescription = xllib.getExcelData("Product", i, 2);
								
				newProductstatus = createproduct(driver,ProductName,Stockkeepingunit,ProductDescription);
				System.out.println("Login Count:"+i);
			}
		}
		catch(Exception e)
		{
			System.out.println("product Unsucessful");
			e.printStackTrace();
		}
		return newProductstatus;
	}

		private boolean createproduct(WebDriver driver, String ProductName,
				String Stockkeepingunit, String ProductDescription) 
		{
			try
			{
				driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
				Thread.sleep(10000);
				//Inspect for product tab
				driver.findElement(By.xpath("//a[@title='Products']/span[1]")).click();
				log.info("product Tab");
				Thread.sleep(20000);
				
				//inspect for new button
				driver.findElement(By.xpath("//div[@title='New']")).click();
				log.info("new button");
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.SECONDS);
				
				//click on next button
				driver.findElement(By.xpath("//button[@class='slds-button slds-button--neutral uiButton--default uiButton--brand uiButton']")).click();
				log.info("next button");
				Thread.sleep(6000);
				
				//inspect for ProductNametextfield
				driver.findElement(By.xpath("//input[@data-interactive-lib-uid='11']")).sendKeys(ProductName);
				log.info("ProductName");
				driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
				Thread.sleep(6000);
				
				//Inspect for Stock keeping unit text field
				driver.findElement(By.xpath("//input[@data-interactive-lib-uid='13']")).sendKeys(Stockkeepingunit);
				log.info("Stockkeepingunit");
				Thread.sleep(6000);
				
				//Inspect for ProductDescription text field
				driver.findElement(By.xpath("//textarea[@data-interactive-lib-uid='15']")).sendKeys(ProductDescription);
				log.info("ProductDescription");
				Thread.sleep(6000);
				
				//click on save button
				driver.findElement(By.xpath("//button[@title='Save']")).click();
				log.info("save");
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
				log.info("product created");
				
				return true;
			}
			catch(Exception e)
			{
				System.out.println("createproduct Unsuccessful");
				return false;
			}
		}
}
    


