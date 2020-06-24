package com.SigmaERP.testpage;

import java.beans.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.util.ExcelLib;

public class CreateStockmovementoutPage
{
WebDriver driver;
	
	public String Quantityreceived;
	public String Reasonforstockmismatch;
	public String newStockMovementNumberLink;
	public int rowCount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean newstockmovementinStatus;
    int i =0;
    
private static Logger log = Logger.getLogger (CreateStockmovementoutPage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean StockmovementoutPageInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int StockINRowCount = xllib.getRowCount("StockIN");
			System.out.println("StockINRowCount:"+StockINRowCount);
			log.info("StockINRowCount log initialized ");
			for (i = 1; i <= StockINRowCount; i++) 
			{
				Quantityreceived = xllib.getExcelData("StockIN", i, 0);
				Reasonforstockmismatch = xllib.getExcelData("StockIN", i, 1);
				
				
				
				newstockmovementinStatus = CreateStockmovementin(driver,Quantityreceived,Reasonforstockmismatch);
				System.out.println("Login Count:"+i);
			}
		}
		catch(Exception e)
		{
			System.out.println("StockMovementin Unsucessful");
			e.printStackTrace();
		}
		return newstockmovementinStatus;
	}

	private boolean CreateStockmovementin(WebDriver driver,
			String quantityreceived, String reasonforstockmismatch)
	{
		try
		{
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		Thread.sleep(8000);
		//Inspect for stockmovement tab
		driver.findElement(By.xpath("//li[@id='01r28000000tS3E_Tab']/a")).click();
		log.info("stockmovement Tab");
		Thread.sleep(4000);
		
		
		
		//Inspect for stockmovementout tab
				driver.findElement(By.xpath("//tbody[tr[th[contains(text(),'StockMovement Number')]]]/tr[2]/th/a")).click();
				log.info("stockmovement Tab");
				Thread.sleep(4000);
				
				
				//Inspect for stockin tab
				driver.findElement(By.xpath("//input[@name='sigmaerp__stock_in']")).click();
				log.info("stockin Tab");
				Thread.sleep(10000);
				
				
				//Inspect for Quantityreceived textfield
				Thread.sleep(10000);
				System.out.println("Quantityreceived"+Quantityreceived);
				//driver.findElement(By.xpath("//td[@id='mainPage:myform:pageBlockprd:pageSectionprd:pageTable:0:j_id38']/input")).clear();
				driver.findElement(By.cssSelector("//tbody[@id='mainPage:myform:pageBlockprd:pageSectionprd:pageTable:tb']/tr/td[4]/input")).sendKeys(Quantityreceived);
				log.info("Quantityreceived textfield");
				Thread.sleep(4000);
				
				
				//Inspecting for Reasonforstockmismatch status drop down
				WebElement clusterText =  driver.findElement(By.id("mainPage:myform:pageBlockprd:pageSectionprd:pageTable:0:j_id41"));
				Select st2 = new Select(clusterText);
				Thread.sleep(6000);
				st2.selectByVisibleText(Reasonforstockmismatch);
				System.out.println("stockReceivingProductStatus");

				return true;		
						
			}
			catch(Exception e)
			{
				System.out.println("createstockmovementin Unsuccessful");
				return false;
				
			}
		}

	}