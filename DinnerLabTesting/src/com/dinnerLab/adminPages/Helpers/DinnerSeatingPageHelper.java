/**
 * @author: Anitha M
 * @Created Date :10/5/2016
 * @Updated Date :
 * @Comments This automation class will serve the seating helper page.
 */
package com.dinnerLab.adminPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;


public class DinnerSeatingPageHelper 
{
	private static Logger log = Logger.getLogger(DinnerSeatingPageHelper.class);
	
	private String starttime;
	private String status;
	private String maxcapacity;
	private String displayquantity;
	private String nondisplayquantity;
	private String seatingorder;
	private String dateorder;
	private String newseating;
	private String massadd;
	private String searchdinner;
	private String dlregion;
	private String masscount;
	String isTestPassed="FAIL";
	int i;
	ExcelLib xllib=new ExcelLib();
	
	/**
	 * This Method serves as reading the excel data for Adding seating for Dinner.
	 * Input: WebDriver driver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void dinnerSeatingInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			
			int rowCount=xllib.getRowCount("dseating");
			for(i=1;i<=rowCount;i++)
			{
				starttime=xllib.getExcelData("dseating", i, 0);
				status=xllib.getExcelData("dseating", i, 1);
				maxcapacity=xllib.getExcelData("dseating", i, 2);
				displayquantity=xllib.getExcelData("dseating", i, 3);
				nondisplayquantity=xllib.getExcelData("dseating", i, 4);
				seatingorder=xllib.getExcelData("dseating", i, 5);
				dateorder=xllib.getExcelData("dseating", i, 6);
				newseating=xllib.getExcelData("dseating", i, 7);
				massadd=xllib.getExcelData("dseating", i, 8);
				searchdinner=xllib.getExcelData("dseating", i, 9);
				dlregion=xllib.getExcelData("dseating", i, 10);
				masscount=xllib.getExcelData("dseating", i, 11);
				DinnerSeatingPageHelper ds=new DinnerSeatingPageHelper();
				int s1=ds.dinnerSeatingActionPage(driver,starttime,status,maxcapacity,displayquantity,nondisplayquantity,
						seatingorder,dateorder,newseating,massadd,searchdinner,dlregion,masscount);
				if(s1==1)
				{
					log.info("seating created successfully");
					isTestPassed="PASS";
    	 			System.out.println("Pass Result :"+isTestPassed);
    			    xllib.writeToExcel("dseating", i, 11, isTestPassed);
				}
			}
		}
		catch(Exception e)
		{
			log.info("failed in creating seating");
			isTestPassed="FAIL";
 			System.out.println("Pass Result :"+isTestPassed);
		    xllib.writeToExcel("dseating", i, 11, isTestPassed);
			e.printStackTrace();
		}
	}
	
	/**
	 * This Method serves Action for Adding Seating for created Dinner.
	 * Input: WebDriver driver,StartTime,Status,MaxCapacity,DisplayQuantity,NonDisplayQuantity,
	 * SeatingOrder,DateOrder,NewSeating,MassAdd,SearhDinner,MassCount.
	 * Output: Void
	 */
	
	public int dinnerSeatingActionPage(WebDriver driver, String Starttime,
			String Status, String Maxcapacity, String Displayquantity,
			String Nondisplayquantity, String Seatingorder, String Dateorder,
			String Newseating, String Massadd,String Searchdinner,String DlRegion,String Masscount) 
	{
		try
		{
			log.info("Adding the seatings");
			Thread.sleep(2000);
			driver.findElement(By.id("phSearchInput")).clear();
			driver.findElement(By.id("phSearchInput")).sendKeys(Searchdinner);// Search dinner
			driver.findElement(By.id("phSearchButton")).click(); //Search button
			Thread.sleep(4000);
			
			if((driver.findElement(By.xpath("//a[contains(text(),'"+Searchdinner+"')]")).isDisplayed()) && (driver.findElement(By.xpath("//td[contains(text(),'"+DlRegion+"')]")).isDisplayed()))
			{
			driver.findElement(By.xpath("//a[contains(text(),'"+Searchdinner+"')]")).click(); // click action on dinner link
			}
			Thread.sleep(5000);
			driver.findElement(By.xpath("//td[@id='topButtonRow']/input[@title='SEATING']")).click(); // Seating button
			
			if(Massadd.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				log.info("Mass Add functionality");
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id55:j_id58")).click();//Mass Add button
				driver.findElement(By.xpath("//input[@name='j_id0:j_id2:j_id239:j_id246']")).clear();
				driver.findElement(By.xpath("//input[@name='j_id0:j_id2:j_id239:j_id246']")).sendKeys(Masscount);//Mass count
				driver.findElement(By.id("j_id0:j_id2:j_id239:j_id249")).click();//Continue button
				
				String[] starttimesplit =Starttime.split(",");
				for(int i =0; i < starttimesplit.length ; i++)
				System.out.println("Start time split:"+starttimesplit[i]);
				 
				String[] statussplit=Status.split(",");
				String[] maxcapsplit=Maxcapacity.split(",");
				String[] displayquantitysplit=Displayquantity.split(",");
				for(int i =0; i < displayquantitysplit.length ; i++)
					System.out.println("DisplayQuantity split:"+displayquantitysplit[i]);
				
				String[] nondisplayquantitysplit=Nondisplayquantity.split(",");
				for(int i =0; i < nondisplayquantitysplit.length ; i++)
					System.out.println("DisplayQuantity split:"+nondisplayquantitysplit[i]);
				
				String[] seatingordersplit=Seatingorder.split(",");
				for(int i =0; i < seatingordersplit.length ; i++)
					System.out.println("DisplayQuantity split:"+seatingordersplit[i]);
				
				String[] dateordersplit=Dateorder.split(",");
				int n = Integer.parseInt(Masscount);
				
				for(int i=0;i<n;i++)
				{
					Thread.sleep(2000);
					WebElement ele1=driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id209:"+i+":j_id212:j_id214")); // status
					Select st1 = new Select(ele1);
					st1.selectByVisibleText(starttimesplit[i]);
					//driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id209:"+i+":j_id212:j_id214")).sendKeys(starttimesplit[i]); //start time
					Thread.sleep(2000);
					WebElement ele2=driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id209:"+i+":j_id212:j_id218")); // status
					Select st2 = new Select(ele2);
					st2.selectByVisibleText(statussplit[i]);
					//driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id209:"+i+":j_id212:j_id218")).sendKeys(statussplit[i]); //status
					driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id209:"+i+":j_id212:j_id219")).sendKeys(maxcapsplit[i]);//max capacity
					driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id209:"+i+":j_id222:j_id223")).sendKeys(displayquantitysplit[i]);//displayquantity
					driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id209:"+i+":j_id222:j_id224")).sendKeys(nondisplayquantitysplit[i]);//nondisplayquantitysplit
					driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id209:"+i+":j_id222:j_id225")).sendKeys(seatingordersplit[i]);//seatingordersplit
					driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id209:"+i+":j_id222:j_id226")).sendKeys(dateordersplit[i]);//dateordersplit
					
				}
				Thread.sleep(2000);
				driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id236")).click(); //save button
			}//end if
			else
			{
				
				log.info("New Seating functionality");
				driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id55:j_id56")).click(); // New button
				driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id97:j_id98:j_id100")).sendKeys(Starttime); //starttime
				driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id97:j_id98:j_id104")).sendKeys(Status);//status
				
				driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id97:j_id98:j_id105")).clear();
				driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id97:j_id98:j_id105")).sendKeys(Maxcapacity); //maxcapacity
				driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id97:j_id110:j_id111")).sendKeys(Displayquantity);//Displayquantity
				driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id97:j_id110:j_id112")).sendKeys(Nondisplayquantity);//Nondisplayquantity
				driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id97:j_id110:j_id113")).sendKeys(Seatingorder);//Seatingorder
				driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id97:j_id110:j_id114")).sendKeys(Dateorder);//Dateorder
				
				driver.findElement(By.id("j_id0:j_id2:EventSeating:j_id97:j_id121:bottom:j_id122")).click();//save button
				
			}//else end
			return 1;
		}//try end
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
			
		}
		
		
	}
	
}
