package com.stappmvp.userpage;

import java.beans.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.util.ExcelLib;

public class CreateProductPage
{
WebDriver driver;
	
	public String RecordTypeOfNewRecord;
	public String ProductName;
	public String AttributeType;
	public String Source;
	public String ProductPrice;
	public String SellingPrice;
	public String ReorderQty;
	public String ThresholdQty;
	public String MinimumQty;
	public String BomType;
	public String AverageBuyingPrice;
    public int rowCount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean Productstatus;
    int i =0;
    
    
private static Logger log = Logger.getLogger (CreateProductPage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean ProductInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int createStappmvpProductRowCount = xllib.getRowCount("StappmvpProduct");
			System.out.println("createStappmvpProductRowCount:"+createStappmvpProductRowCount);
			log.info("createStappmvpProductRowCount log initialized ");
			for (i = 1; i <= createStappmvpProductRowCount; i++) 
			{
				RecordTypeOfNewRecord = xllib.getExcelData("StappmvpProduct", i, 0);
				ProductName = xllib.getExcelData("StappmvpProduct", i, 1);
				AttributeType = xllib.getExcelData("StappmvpProduct", i, 2);
				Source = xllib.getExcelData("StappmvpProduct", i, 3);
				ProductPrice = xllib.getExcelData("StappmvpProduct", i, 4);
				SellingPrice = xllib.getExcelData("StappmvpProduct", i, 5);
				ReorderQty = xllib.getExcelData("StappmvpProduct", i, 6);
				ThresholdQty = xllib.getExcelData("StappmvpProduct", i, 7);
				MinimumQty = xllib.getExcelData("StappmvpProduct", i, 8);
				BomType = xllib.getExcelData("StappmvpProduct", i, 9);
				AverageBuyingPrice = xllib.getExcelData("StappmvpProduct", i, 10);
				
				
				Productstatus = CreateProduct(driver,RecordTypeOfNewRecord,ProductName,AttributeType,Source,ProductPrice,SellingPrice,ReorderQty,ThresholdQty,MinimumQty,BomType,AverageBuyingPrice);
				System.out.println("Login Count:"+i);
												
			}
		}
		catch(Exception e)
		{
			System.out.println("CreateProduct Unsucessful");
			e.printStackTrace();
		}
		return Productstatus;
	}

	private boolean CreateProduct(WebDriver driver,
			String recordTypeOfNewRecord, String productName,
			String attributeType, String source, String productPrice,
			String sellingPrice, String reorderQty, String thresholdQty,
			String minimumQty,String BomType,String AverageBuyingPrice)
	{
		
		try
		{
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			Thread.sleep(10000);
			//Inspect for Product tab
			log.info("Product Tab");
			driver.findElement(By.cssSelector("a[title='Products Tab']")).click();
			log.info("Product Tab");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);			
			
			
			//Inspecting for New button
			driver.findElement(By.xpath("//input[@value=' New ']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("New button ");
			
			//driver.findElement(By.id("p3")).click();
		/*	//Inspecting for recordTypeOfNewRecord  drop down
			Select cluster = new Select(driver.findElement(By.id("p3")));
			cluster.selectByVisibleText(RecordTypeOfNewRecord);						
			System.out.println("RecordTypeOfNewRecordStatus");
			Thread.sleep(8000);*/
			
			//Inspecting for Continue button
			driver.findElement(By.xpath("//td[@id='bottomButtonRow']/input[1]")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Continue button ");
			
			
			//Inspect for ProductName text field
			driver.findElement(By.id("Name")).sendKeys(ProductName);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("Select ProductName text field");
	
			
			//Inspecting for Active CheckBox
			driver.findElement(By.id("IsActive")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Active CheckBox");
			
			
			//Inspecting for BomType  drop down
			Select cluster2 = new Select(driver.findElement(By.id("00N0Y00000LEDF7")));
			cluster2.selectByVisibleText(BomType);						
			System.out.println("BomType");
			Thread.sleep(5000);
			
						
			//Inspecting for AttributeType  drop down
			Select cluster = new Select(driver.findElement(By.id("00N0Y00000KFyfT")));
			cluster.selectByVisibleText(AttributeType);						
			System.out.println("AttributeType");
			Thread.sleep(5000);
			
			
			//Inspecting for Source  drop down
			Select cluster1 = new Select(driver.findElement(By.id("00N0Y00000KFyfi")));
			cluster1.selectByVisibleText(Source);						
			System.out.println("Source");
			Thread.sleep(5000);

			
			//Inspect for ProductPrice text field
			driver.findElement(By.id("00N0Y00000KFyfa")).sendKeys(ProductPrice);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("Select ProductPrice text field");
			
			
			//Inspect for SellingPrice text field
			driver.findElement(By.id("00N0Y00000KFyfh")).sendKeys(SellingPrice);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("Select SellingPrice text field");
			
			
			//Inspect for ReorderQty text field
			driver.findElement(By.id("00N0Y00000KFyff")).sendKeys(ReorderQty);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("Select ReorderQty text field");
			
			
			//Inspect for ThresholdQty text field
			driver.findElement(By.id("00N0Y00000KFyfm")).sendKeys(ThresholdQty);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("Select ThresholdQty text field");
			
			
			//Inspect for MinimumQty text field
			driver.findElement(By.id("00N0Y00000KFyfe")).sendKeys(MinimumQty);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("Select MinimumQty text field");
			
			
			//Inspect for AverageBuyingPrice text field
			driver.findElement(By.id("00N0Y00000KFyfU")).sendKeys(AverageBuyingPrice);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("Select AverageBuyingPrice text field");
			
						
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
	