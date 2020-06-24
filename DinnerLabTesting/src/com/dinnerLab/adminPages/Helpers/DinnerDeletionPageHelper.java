/**
 * @author: Anitha M
 * @Created Date :10/5/2016
 * @Updated Date :
 * @Comments : This automation class will script for Dinner deletion helper page.
 */
package com.dinnerLab.adminPages.Helpers;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.dinnerLab.util.ExcelLib;

public class DinnerDeletionPageHelper 
{
	String isTestPassed="FAIL";
	private static Logger log = Logger.getLogger(DinnerDeletionPageHelper.class);
	
	private int rowCount;
	private String searchdname;
	ExcelLib xllib = new ExcelLib();
	
	int i=0;
	;
	/**
	 * Test Case for deleting created dinner
	 * Input: WebDriver driver
	 * Output: Boolean
	 * @throws InvalidFormatException 
	 * 
	 */	 
	public Boolean dinnerdeleteInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			
			rowCount = xllib.getRowCount("ddelete");
		    for(i=1;i<=rowCount;i++)
			
		     	{
		    	 
		    	 	log.info("--------------------------------------------");
		    	 	log.info("Reading the dinner data for deleting");
		    	 	log.info("--------------------------------------------");
		    	 	searchdname = xllib.getExcelData("ddelete", i, 0);
		    	 	
		    	 	DinnerDeletionPageHelper dc = new DinnerDeletionPageHelper();
		    	 	int val=dc.dinnerdeletionActionPage(driver, searchdname);
		    	 	if(val==1)
		    	 		{
		    	 			log.info("Dinner deleted successfully");
		    	 			isTestPassed="PASS";
		    			    xllib.writeToExcel("ddelete", i, 1, isTestPassed);
		    	 		}
		    	 	
		     	}
		}//try end
		    
		catch(Exception e)
		{
			 log.info("Failed in deleting the dinner");
			 isTestPassed="FAIL";
			 xllib.writeToExcel("ddelete", i, 1, isTestPassed);
			 e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This Method serves Actions for deleting the creating dinner
	 * Input: WebDriver driver,SearchDinner
	 * Output: int
	 */
	public int dinnerdeletionActionPage(WebDriver driver, String SearchDinner)
	{
		try
		{
		log.info("------------------------");
		log.info("Deleting the dinner");
		Thread.sleep(5000);
		driver.findElement(By.id("phSearchInput")).clear();
		driver.findElement(By.id("phSearchInput")).sendKeys(SearchDinner); //search input
		driver.findElement(By.id("phSearchButton")).click(); //search button
		driver.findElement(By.xpath("//div[@id='Campaign_body']/table/tbody/tr[2]/th[1]/a")).click(); // Event name link for deleting
		//driver.findElement(By.xpath("//span[contains(text(),'Show Feed')]")).isDisplayed(); // Show feed heading
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[4]")).click(); // delete button
		Thread.sleep(2000);
		Alert alr=driver.switchTo().alert();
		alr.accept();
		
		return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		
	}
	

}
