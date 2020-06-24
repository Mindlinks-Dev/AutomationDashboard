package com.jandk.userpage;

import java.beans.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.util.ExcelLib;

public class CreateDimensionTagPage
{
WebDriver driver;
	
	public String Name;
	public String Company;
	public String Dimension;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean newdimensiontagstatus;
    int i =0;
    
    private static Logger log = Logger.getLogger (CreateDimensionTagPage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean dimensionTagInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int createdimensiontagRowCount = xllib.getRowCount("DimensionTag");
			System.out.println("createdimensiontagRowCount:"+createdimensiontagRowCount);
			log.info("Createdimensiontag log initialized ");
			for (i = 1; i <= createdimensiontagRowCount; i++) 
			{
				Name = xllib.getExcelData("DimensionTag", i, 0);
				Dimension = xllib.getExcelData("DimensionTag", i, 1);
				Company = xllib.getExcelData("DimensionTag", i, 2);
								
				newdimensiontagstatus = createdimensiontag(driver,Name,Company,Dimension);
				System.out.println("Login Count:"+i);
			}
		}
		catch(Exception e)
		{
			System.out.println("dimension Unsucessful");
			e.printStackTrace();
		}
		return newdimensiontagstatus;
	}
    
    private boolean createdimensiontag(WebDriver driver, String Name,
			String company, String Dimension) 
	{
		try
		{
			System.out.println("Name"+Name);
			System.out.println("Dimension"+Dimension);
			System.out.println("company"+company);
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			Thread.sleep(10000);
			
			//Inspect for product tab
			driver.findElement(By.xpath("//a[@title='Products']/span[1]")).click();
			log.info("product Tab");
			Thread.sleep(8000);
			
			//Click on Product
			driver.findElement(By.xpath("//a[@data-recordid='01t0Y000004fJrxQAE']")).click();
			log.info("product");
			Thread.sleep(8000);
					
			//Click on relative
			driver.findElement(By.xpath("//a[@title='Related']")).click();
			log.info("relative");
			Thread.sleep(19000);
			
			//click on Dimensiontag new button
			driver.findElement(By.xpath("//li[@data-aura-rendered-by='1644:0']/a[@data-aura-rendered-by='1647:0']/div[@data-aura-rendered-by='1648:0']")).click();
			log.info("Dimensiontag new button");
			Thread.sleep(9000);
			
			//Select the name text field
			driver.findElement(By.xpath("//input[@data-interactive-lib-uid='9']")).sendKeys(Name);
			log.info("name text field");
			Thread.sleep(8000);
			
			//Select the Company text field
			driver.findElement(By.xpath("//div[@data-aura-rendered-by='95:1932;a']")).click();
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[@data-aura-rendered-by='95:1932;a']/div[@data-aura-rendered-by='2307:0']/div[2]/ul[1]/li[1]/a[1]")).click();
			
		/*WebElement clusterText =  driver.findElement(By.xpath("//div[@data-aura-rendered-by='95:1910;a']"));
			Select st2 = new Select(clusterText);
			Thread.sleep(6000);
			st2.selectByVisibleText(Company);
			//driver.findElement(By.xpath("//div[@data-aura-rendered-by='95:1910;a']/div[@data-aura-rendered-by='2341:0']/div[2]/ul[1]/li[1]")).click();
			log.info("Company text field");
			Thread.sleep(8000);/*
			/*WebElement clusterText =  driver.findElement(By.id("00N9000000EoEGd"));
			Select st2 = new Select(clusterText);
			Thread.sleep(6000);
			st2.selectByVisibleText(orderstatus);
			System.out.println("selected the orderstatus dropdown");*/
			
			
			return true;
		}
		catch(Exception e)
		{
			System.out.println("createdimensiontag Unsuccessful");
			return false;
		}
	}
}
