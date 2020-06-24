/**
 * @author: Anitha M
 * @Created Date :10/5/2016
 * @Updated Date :
 * @Comments : This automation class will script for Dinner edit helper page.
 */
package com.dinnerLab.adminPages.Helpers;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.dinnerLab.util.ExcelLib;

public class DinnerEditPageHelper
{
  private static Logger log = Logger.getLogger(DinnerEditPageHelper.class);
  
  ExcelLib xllib = new ExcelLib();
	
	private String dname;
	private String searchdname;
	private String dlregion;
	private String startdate;
	private String status;
	private String ticketprice;
	private String displayorder;
	private String seatingpublishtype;
	private String websiteblurb;
	private String graphic;
	private String allergy;
	private String webreleasedate;
	private String freeuserreleasedate;
	private String eventidentifier;
	String isTestPassed="FAIL";
	int i;
	/**
	 * This Method serves as reading the excel data for updating the dinner.
	 * Input: WebDriver driver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	
	public void dinnerEditInitialPage(WebDriver driver) throws InvalidFormatException
	{
		
		try
		{
			int rowCount = xllib.getRowCount("dcreation");
			for(i=1;i<=rowCount;i++)
				{
					log.info("--------------------------------------------");
					log.info("Reading dinner data for updating");
					log.info("--------------------------------------------");
					searchdname = xllib.getExcelData("dedit", i, 0);
					dname = xllib.getExcelData("dedit", i, 1);
					startdate = xllib.getExcelData("dedit", i, 2);
					dlregion = xllib.getExcelData("dedit", i, 3);
					status = xllib.getExcelData("dedit", i, 4);
					ticketprice = xllib.getExcelData("dedit", i, 5);
					displayorder = xllib.getExcelData("dedit", i, 6);
					seatingpublishtype  = xllib.getExcelData("dedit", i, 7);
					websiteblurb = xllib.getExcelData("dedit", i, 8);
					allergy= xllib.getExcelData("dedit", i, 9);
					graphic= xllib.getExcelData("dedit", i, 10);
					eventidentifier = xllib.getExcelData("dedit", i, 11);
					webreleasedate = xllib.getExcelData("dedit", i, 12);
					freeuserreleasedate = xllib.getExcelData("dedit", i, 13);
		
					DinnerEditPageHelper dc = new DinnerEditPageHelper();
					int val=dc.dinnerEditAction1Page(driver,searchdname, dname, startdate, dlregion, status, ticketprice, displayorder, seatingpublishtype, websiteblurb, allergy, graphic,eventidentifier,webreleasedate,freeuserreleasedate);
					if(val==1)
						{
							log.info("Dinner updated successfully");
							isTestPassed="PASS";
		    			    xllib.writeToExcel("dedit", i, 14, isTestPassed);
						}
				}//for loop end
		}//try end
	catch(Exception e)
	{
		log.info("Dinner updated unsuccessfully");
		isTestPassed="FAIL";
	    xllib.writeToExcel("dedit", i, 14, isTestPassed);
		e.printStackTrace();
		
	}
	}
  
  /**
	 * This Method serves the ACtion for updating the dinner.
	 * Input: WebDriver driver,SearchDinner,DinnerName,StartDate,DLRegion,status,TicketPrice,DisplayOrder,SeatingPublishtype,
	 * WebsiteBlurb,allergy,graphic,EventIdentifier,WebReleasedate,FreeuserReleasedate
	 * Output: int
	 */
	public int dinnerEditAction1Page(WebDriver driver,String SearchDinner,String DinnerName,String StartDate,String DLRegion,String Status
,String TicketPrice,String DisplayOrder,String SeatingPublishType,String WebsiteBlurb,String Allergy,String Graphic,String EventIdentifier,String WebReleaseDate,String FreeUserReleaseDate)


	{
	   try 
	   {
		   	log.info("--------------------------------------------");
			log.info("Dinner Updation in SFDC");
			log.info("--------------------------------------------");
			Thread.sleep(5000);
			log.info("Search dinner for edit:");
			log.info("--------------------------------------------");
			driver.findElement(By.id("phSearchInput")).clear();
			driver.findElement(By.id("phSearchInput")).sendKeys(SearchDinner); //search input
			driver.findElement(By.id("phSearchButton")).click(); //search button
			driver.findElement(By.xpath("//div[@id='Campaign_body']/table/tbody/tr[2]/th[1]/a")).click(); // Event name link for edit
			driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[3]")).click(); //edit button
			log.info("----------------------");
			log.info("Dinner Edit Page");
			
			driver.findElement(By.id("cpn1")).clear();
			driver.findElement(By.id("cpn1")).sendKeys(DinnerName); // dinner name
			driver.findElement(By.id("00N3600000D8L1d")).sendKeys(DLRegion); // dlregion
			log.info("----------------------");
			log.info("Start date ");
			driver.findElement(By.id("cpn5")).clear();
			driver.findElement(By.id("cpn5")).sendKeys(StartDate); // start date picker text box
			
			driver.findElement(By.id("cpn3")).sendKeys(Status); // status
			Thread.sleep(2000);
			
			log.info("----------------------");
			log.info("Event close date ");
			
			driver.findElement(By.id("00N3600000D8L74")).clear();
			driver.findElement(By.id("00N3600000D8L74")).sendKeys(TicketPrice); // ticket price
			driver.findElement(By.id("00N3600000D8L25")).sendKeys(DisplayOrder); // display order
			driver.findElement(By.id("00N3600000D8L6C")).sendKeys(SeatingPublishType); // seating publish type
			driver.findElement(By.id("00N3600000D8L7r")).clear();
			driver.findElement(By.id("00N3600000D8L7r")).sendKeys(WebsiteBlurb); // website blurb
			log.info("----------------------");
			log.info("Allergy ");
			WebElement ele1=driver.findElement(By.id("00N3600000D8KzZ_unselected")); // Allergy
			Select st1 = new Select(ele1);
			st1.selectByVisibleText(Allergy);// passing excel allergy data as a parameter
			driver.findElement(By.id("00N3600000D8KzZ_selected")).click(); // allergy right arrow click
			
			driver.findElement(By.id("00N3600000D8L3e")).sendKeys(Graphic); // graphics
			log.info("------------------");
			/*log.info("scroll down");
			WebElement ele3=driver.findElement(By.xpath(".//*[@id='head_4_ep']/h3")); // scroll down
			ele3.sendKeys(Keys.PAGE_DOWN);*/
			log.info("------------------");
			log.info("chef look up");
			
			log.info("------------------");
			log.info("Event Identifier");
			driver.findElement(By.id("00N3600000D8L2j")).clear();
			driver.findElement(By.id("00N3600000D8L2j")).sendKeys(EventIdentifier);
			log.info("------------------");
			log.info("WebRelease date");
			driver.findElement(By.id("00N3600000D8L7g")).clear();
			driver.findElement(By.id("00N3600000D8L7g")).sendKeys(WebReleaseDate); // web release date
			log.info("------------------");
			log.info("FreeUser Release date");
			driver.findElement(By.id("00N3600000D8L3c")).clear();
			driver.findElement(By.id("00N3600000D8L3c")).sendKeys(FreeUserReleaseDate); // free user release date
			//WebElement ele4=driver.findElement(By.xpath(".//*[@id='head_1_ep']/h3")); // scroll up
			//ele4.sendKeys(Keys.PAGE_UP);
			log.info("Save the dinner");
			driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[1]")).click(); // save button
			
			return 1;
			
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
		   return 0;
	   }
	
	}
	
}
