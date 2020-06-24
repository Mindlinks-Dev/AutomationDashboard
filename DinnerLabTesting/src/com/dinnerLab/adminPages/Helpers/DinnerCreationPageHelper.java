/**
 * @author: Anitha M
 * @Created Date :10/5/2016
 * @Updated Date :
 * @Comments This automation class will serve script for Dinner Creation helper page.
 */
package com.dinnerLab.adminPages.Helpers;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


import com.dinnerLab.util.ExcelLib;

public class DinnerCreationPageHelper
{
	private static Logger log = Logger.getLogger(DinnerCreationPageHelper.class);
	private String dname;
	private String dlregion;
	private String startdate;
	private String status;
	private String ticketprice;
	private String displayorder;
	private String seatingpublishtype;
	private String websiteblurb;
	private String graphic;
	private String allergy;
	private String chef;
	public static int rowCount;
	ExcelLib xllib = new ExcelLib();
	int i;
	
	String isTestPassed="FAIL";
	/**
	 * This Method serves as reading the excel data for creating dinner
	 * Input: WebDriver driver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void dinnerCreationInitialPage(WebDriver driver) throws InvalidFormatException
	{
		
		  try
		   {
			 
		     rowCount = xllib.getRowCount("dcreation");
		     for(i=1;i<=rowCount;i++)
			
		     	{
		    	 
		    	 	log.info("--------------------------------------------");
		    	 	log.info("Reading the dinner data for creating");
		    	 	log.info("--------------------------------------------");
		    	 	dname = xllib.getExcelData("dcreation", i, 0);
		    	 	startdate = xllib.getExcelData("dcreation", i, 1);
		    	 	dlregion = xllib.getExcelData("dcreation", i, 2);
		    	 	status = xllib.getExcelData("dcreation", i, 3);
		    	 	ticketprice = xllib.getExcelData("dcreation", i, 4);
		    	 	displayorder = xllib.getExcelData("dcreation", i, 5);
		    	 	seatingpublishtype  = xllib.getExcelData("dcreation", i, 6);
		    	 	websiteblurb = xllib.getExcelData("dcreation", i, 7);
		    	 	allergy= xllib.getExcelData("dcreation", i, 8);
		    	 	graphic= xllib.getExcelData("dcreation", i, 9);
		    	 	chef= xllib.getExcelData("dcreation", i, 10);
		    	 	DinnerCreationPageHelper dc = new DinnerCreationPageHelper();
		    	 	int val=dc.dinnercreationActionPage(driver, dname, startdate,dlregion, status, ticketprice, displayorder, seatingpublishtype, websiteblurb, allergy, graphic,chef);
		    	 	
		    	 	if(val==1)
	    	 		{
	    	 			log.info("Dinner created successfully");
	    	 			isTestPassed="PASS";
	    	 			System.out.println("Pass Result :"+isTestPassed);
	    			    xllib.writeToExcel("dcreation", i, 11, isTestPassed);
	    	 		}
		    	 	
		     	}//for loop end
		   }//try end
		
		catch(Exception e)
		{
			log.info("Dinner created was unsuccessful");
	 		isTestPassed="FAIL";
	 		System.out.println("Failed Result :"+isTestPassed);
	 		xllib.writeToExcel("dcreation", i, 11, isTestPassed);
			e.printStackTrace();
		}
	}//me
	
	/**
	 * This Method serves Actions for creating Dinner
	 * Input: WebDriver driver,DinnerName,StartDate,DLRegion,Status,TicketPrice,DisplayOrder,SeatingPublishtype,
	 * WebsiteBlurb,Allergy,Graphics,Chef
	 * Output: int
	 */

	public int dinnercreationActionPage(WebDriver driver,String DinnerName, String StartDate,
			String DLRegion, String Status, String TicketPrice,
			String DisplayOrder, String SeatingPublishType,
			String WebsiteBlurb, String Allergy, String Graphic,String Chef) throws InterruptedException 
	{
		try
		{

			   	log.info("--------------------------------------------");
				log.info("Dinner Creation in SFDC");
				log.info("--------------------------------------------");
				//Thread.sleep(2000);
				driver.findElement(By.xpath("//a[@href='/701/o']")).click();
				

				
				log.info("New dinner button");
				driver.findElement(By.xpath("//input[@name='new']")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//input[@id='cpn1']")).sendKeys(DinnerName); // dinner name
				driver.findElement(By.xpath("//select[@id='00N3600000D8L1d']")).sendKeys(DLRegion);// dlregion
				
				driver.findElement(By.xpath("//input[@id='cpn5']")).sendKeys(StartDate); // date picker text box
				
				
				driver.findElement(By.id("cpn3")).sendKeys(Status); // status
				Thread.sleep(2000);
				
				driver.findElement(By.id("00N3600000D8L74")).sendKeys(TicketPrice); // ticket price
				driver.findElement(By.id("00N3600000D8L25")).sendKeys(DisplayOrder);
				driver.findElement(By.id("00N3600000D8L6C")).sendKeys(SeatingPublishType);
				driver.findElement(By.id("00N3600000D8L7r")).sendKeys(WebsiteBlurb);
				
				Thread.sleep(2000);
				String[] allergyspilt=Allergy.split(",");
				 for (String valueToBeSelected : allergyspilt) 
				 {

					 //new Select(driver.findElement(By.id("00N3600000D8KzZ_unselected"))).selectByVisibleText(valueToBeSelected);
					 //driver.findElement(By.id("00N3600000D8KzZ_selected")).sendKeys(Keys.CONTROL);// Allergy
					 
					 WebElement ele1=driver.findElement(By.id("00N3600000D8KzZ_unselected")); // Allergy
					 Select st1 = new Select(ele1);
					 st1.selectByVisibleText(valueToBeSelected);
					 driver.findElement(By.id("00N3600000D8KzZ_right_arrow")).click(); //right arrow
				 }
				/*WebElement ele1=driver.findElement(By.id("00N3600000D8KzZ_unselected")); // Allergy
				Select st1 = new Select(ele1);
				st1.selectByVisibleText(Allergy);
				
				driver.findElement(By.id("00N3600000D8KzZ_selected")).click(); // allergy right shift*/
				 
				driver.findElement(By.id("00N3600000D8L3e")).sendKeys(Graphic);//graphics
				
				log.info("Chef Look up");//10-5-2016
				driver.findElement(By.xpath("//a[@id='CF00N3600000D8L1N_lkwgt']/img[@title='Chef Lookup (New Window)']")).click(); //Chef lookup
				String parentWindow = driver.getWindowHandle();
				Set<String> handles =  driver.getWindowHandles();
				   for(String windowHandle  : handles)
				       {
				       if(!windowHandle.equals(parentWindow))
				          {
				          driver.switchTo().window(windowHandle);
				          driver.switchTo().frame(0);//frame1
				          driver.findElement(By.id("lksrch")).clear();
				          driver.findElement(By.id("lksrch")).sendKeys(Chef);//Search input
				          
				          driver.findElement(By.xpath(".//*[@id='theForm']/div/div[2]/input[2]")).click();//Go button
				          
				          driver.switchTo().defaultContent();
				          driver.switchTo().frame(1);
				          driver.findElement(By.xpath(".//*[@id='msgBox']/table/tbody/tr/td[2]/div/div/a")).click(); //show result link
				          driver.findElement(By.xpath(".//*[@id='new']/div/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/th/a")).click(); // Chef name 
				          Thread.sleep(2000);
				          //driver.close(); //closing child window
				          driver.switchTo().window(parentWindow); //cntrl to parent window
				          }
				       }//for loop end
			
				
				Thread.sleep(2000);
				log.info("Save the dinner");
				driver.findElement(By.xpath("//td[@id='topButtonRow']/input[1]")).click(); // save button
				
				/*log.info("Mandatory fields error message");
				Boolean b1=driver.findElement(By.id("errorDiv_ep")).isDisplayed();
				if(b1)
				{
					log.info("mandatory fields are not filled");
					driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[3]")).click(); // cancel button
					
				}*/
				
				return 1;
		   }
	
		   catch(Exception e)
		   {
			   e.printStackTrace();
			   return 0;
		   }

		
	}


}
