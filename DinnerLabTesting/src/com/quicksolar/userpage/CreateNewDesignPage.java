package com.quicksolar.userpage;

import java.beans.Statement;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateNewDesignPage
{
	WebDriver driver;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean NewDesignStatus;
    int i =0;
    
    private static Logger log = Logger.getLogger (CreateNewDesignPage.class);
    
    public boolean NewDesignInitialPage(WebDriver driver) throws InvalidFormatException
   	{
   		try
   		{
   			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			//Inspect for Start new Design 
   			Thread.sleep(10000);
			driver.findElement(By.id("design")).click();
			log.info("Start new Design");
			Thread.sleep(8000);
			
			
			//Inspect for Next button 
			driver.findElement(By.id("btnnext")).click();
			log.info("Next button");
			Thread.sleep(4000);
			
			
			//Inspect for Next button 
			driver.findElement(By.id("btnnext")).click();
			log.info("Next button");
			Thread.sleep(4000);
			
			
			//Inspect for Design button 
			driver.findElement(By.id("btnnext")).click();
			log.info("Design button");
			Thread.sleep(8000);
			
			
			//Inspect for Done button 
			driver.findElement(By.xpath("//a[@id='ddone']/img")).click();
			log.info("Done button");
			Thread.sleep(4000);
			
			//Inspect for ok button 
			driver.findElement(By.xpath(".//*[@id='bg']/div[24]/div[3]/div/button[2]")).click();
			log.info("ok button");
			Thread.sleep(4000);
			
			
        return true;
						
			
		}
		catch(Exception e)
		{
			System.out.println("CreateNewDesign Unsuccessful");
			return false;
			
		}
	}
}