/**
 * @author: Basappa Hunsikatti
 * @Created Date :31/07/2015
 * @Updated Date :
 * @Comments This automation class will purchase Member/Chef Event Tickets.
 */
package com.dinnerLab.userPages.Helpers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;
import com.dinnerLab.util.ExcelReport;
import com.dinnerLab.util.OutputReport;
import com.pack.ScriptHandler;

public  class MemberEventTicketPurchasePage 
{
	 private String city;
	 public static String eventName;
	 public static String seatingDate;
	 public static String seatingTime;
	 public static String purchaseQuantity;
	 public static String eventNameText;
	 public static String cityText;
	 public static String dateText;
	 public static String timeText;
	 public static String ticketQuantityText;
	 public static String subTotalText;
	 public static String taxText;
	 public static String creditsText;
	 public static String orderTotalText;
	 public static String eventNameAfterPayment;
	 public static String cityAfterPayment;
	 public static String dateAfterPayment;
	 public static String timeAfterPayment;
	 public static String quantityAfterPayment;
	 public static Boolean ticketClosedStatus;
	 public static String multipleEventNames = "";
	 public String tempEventNames;
	 //private String useDifferentCard;
	 boolean present;
	 public int rowCount;
	 String isTestPassed="FAIL";
	 //String isTicketSoldOut="Event Ticket Available";
	 String isTicketSoldOut="FAIL";
	 public static String result;
	 public static boolean eventTicketPurchaseSuccess;
	 public static boolean purchaseButtonStatus;
	 //ExcelLib xllib = new ExcelLib();
	 public static Boolean soldOutStatus = false;
	 private static Logger log = Logger.getLogger(MemberEventTicketPurchasePage.class);
	
	 
	 /**
		 * Test Case for purchasing Member/Chef Event Tickets.
		 * Input: WebDriver
		 * Output: boolean
	*/
	public boolean memberEventTicketPurchase(WebDriver driver,String city,String eventName,String seatingDate,String seatingTime,String purchaseQuantity) 
	{
		try
		{
			log.info("Clicking on Events");
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//a[@href='/events/']")).click();
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
			Thread.sleep(5000);
			System.out.println("before city select1");
			//WebElement changeCity= driver.findElement(By.xpath("//div[@class='form-item']/form[1]/select[1]"));
			WebElement changeCity= driver.findElement(By.xpath("//form[@id='change-city']/select"));
			//WebElement changeCity= driver.findElement(By.name("change-city"));
			Select st=new Select(changeCity);
			st.selectByVisibleText(city);
			//driver.findElement(By.name("change-city")).sendKeys(city);
			
			System.out.println("after city select1");
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			//Listing no.of Events in City
			List<WebElement> eventNameList = driver.findElements(By.xpath("//div[@class='event-title']"));
			
			int i=0;
			
	        for (WebElement events : eventNameList) 
	        {
	        	tempEventNames = events.getText();
	        	if(i==0)
	        	{
	        		multipleEventNames = tempEventNames;
	        	}
	        	else
	        	{
		        	multipleEventNames = multipleEventNames+","+tempEventNames;
	        	}
	        	i++;
	        }
	        
	        System.out.println("Event Names List ::"+multipleEventNames);
	        
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[contains(text(),'"+eventName+"')]")).click();
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			//System.out.println("Clicked on event");
			try
			{
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				driver.findElement(By.id("seatingDate")).sendKeys(seatingDate);
				driver.findElement(By.id("seatingTime")).sendKeys(seatingTime);
				driver.findElement(By.id("purchaseQuantity")).sendKeys(purchaseQuantity);
				//Thread.sleep(5000);
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				
				eventTicketSoldOutActions(driver);
				Thread.sleep(3000);
				purchaseButtonStatus = driver.findElement(By.id("continueToPurchase")).isDisplayed();
				System.out.println("Ticket status:"+purchaseButtonStatus);
				driver.findElement(By.id("continueToPurchase")).click();
				//driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				Thread.sleep(5000);
			}
			catch(Exception e)
			{
			}
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Test Case for clicking on Continue Button
	 * Input: WebDriver
	 * Output: Void
	 */
	public void clickingOnContinueButton(WebDriver driver) 
	{
		boolean headsUpStatus=false;
		WebElement element=null;
		try
		{	
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			try
			{
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				//headsUpStatus = driver.findElement(By.xpath("//h1[contains(text(),'Heads up!')]")).isDisplayed();
				headsUpStatus = driver.findElement(By.id("disregardUnmetAllergies")).isDisplayed();
				System.out.println("headsUpStatus:"+headsUpStatus);
			}
			catch(Exception ex)
			{
				System.out.println("headsUpStatus1:"+headsUpStatus);
				ex.printStackTrace();
			}
			try
			{
				if(headsUpStatus)
				{
					driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
					//element = driver.findElement(By.xpath("//h1[contains(text(),'Heads up!')]"));
					element = driver.findElement(By.id("disregardUnmetAllergies"));
					System.out.println("element:"+element);
					
					//Condition to check Pop up displayed.
					if(element !=null)
					{
						driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
						driver.findElement(By.id("disregardUnmetAllergies")).click();
					}
				}
			}
			catch(Exception ex)
			{
				System.out.println("element1:"+element);
				ex.printStackTrace();
			}
			
			/*Thread.sleep(3000);
			System.out.println("before eventNameText");							
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			eventNameText = driver.findElement(By.xpath("//div[div[contains(text(),'Event')]]/div[2]")).getText();
			System.out.println("eventNameText3:"+eventNameText);
			System.out.println("after eventNameText");
			
			cityText = driver.findElement(By.xpath("//div[div[contains(text(),'City')]]/div[2]")).getText();
			System.out.println("cityText3:"+cityText);
			System.out.println("after cityText");
			
			dateText = driver.findElement(By.xpath("//div[div[contains(text(),'Date')]]/div[2]")).getText();
			System.out.println("dateText3:"+dateText);
			
			timeText = driver.findElement(By.xpath("//div[div[contains(text(),'Time')]]/div[2]")).getText();
			System.out.println("timeText3:"+timeText);*/
			
			Thread.sleep(3000);
			ticketQuantityText = driver.findElement(By.xpath("//div[div[contains(text(),'Tickets')]]/div[2]")).getText();
			System.out.println("ticketQuantityText:"+ticketQuantityText);
			
			subTotalText = driver.findElement(By.xpath("//div[div[contains(text(),'Subtotal')]]/div[2]")).getText();
			System.out.println("subTotalText3:"+subTotalText);
			
			taxText = driver.findElement(By.xpath("//div[div[contains(text(),'Tax')]]/div[2]")).getText();
			System.out.println("taxText3:"+taxText);
			
			creditsText = driver.findElement(By.xpath("//div[div[contains(text(),'Credits')]]/div[2]")).getText();
			System.out.println("creditsText3:"+creditsText);
			
			orderTotalText = driver.findElement(By.xpath("//div[div[strong[contains(text(),'Order Total')]]]/div[2]/strong")).getText();
			System.out.println("orderTotalText3:"+orderTotalText);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}catch(Exception e)
		{
			System.out.println("inside catch clickingOnContinueButton");
			e.printStackTrace();
		}
	}
	
	/**
	 * Test Case for clicking on Purchase Button
	 * Input: WebDriver
	 * Output: Void
	 */
	public boolean memberEventTicketPurchaseFinal(WebDriver driver) 
	{
		try
		{
			System.out.println("before purchase");
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//input[@value='Purchase']")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			System.out.println("after purchase");

			/*eventNameAfterPayment = driver.findElement(By.xpath("//div[div[contains(text(),'Event')]]/div[2]")).getText();
			System.out.println("eventNameText:"+eventNameAfterPayment);
			
			cityAfterPayment = driver.findElement(By.xpath("//div[div[contains(text(),'City')]]/div[2]")).getText();
			System.out.println("cityText:"+cityAfterPayment);
			
			dateAfterPayment = driver.findElement(By.xpath("//div[div[contains(text(),'Date')]]/div[2]")).getText();
			System.out.println("dateText:"+dateAfterPayment);
			
			timeAfterPayment = driver.findElement(By.xpath("//div[div[contains(text(),'Time')]]/div[2]")).getText();
			System.out.println("timeText:"+timeAfterPayment);
			
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			quantityAfterPayment = driver.findElement(By.xpath("//div[div[contains(text(),'Quantity')]]/div[2]")).getText();
			System.out.println("Quantity Text:"+quantityAfterPayment);*/
			
			//eventTicketPurchaseSuccess = driver.findElement(By.xpath("//h1[contains(text(),'You did it!')]")).isDisplayed();
			Thread.sleep(2000);
			eventTicketPurchaseSuccess = driver.findElement(By.xpath("//h1[contains(text(),'You are all set!')]")).isDisplayed();
			System.out.println("eventTicketPurchaseSuccess:"+eventTicketPurchaseSuccess);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
		
	/**
	 * Test Case for checking Event Ticket Sold Out.
	 * Input: WebDriver
	 * Output: Void
	 */
	public void eventTicketSoldOutActions(WebDriver driver) 
	{
		try
		{
			soldOutStatus = driver.findElement(By.xpath("//button[contains(text(),'Sold Out')]")).isDisplayed();
			if (soldOutStatus)
			{
				soldOutStatus = true;
			}
			else
			{
				soldOutStatus = false;
			}
		}catch(Exception e)
		{
			
		}
	}
}