package com.autoparts.userpage;

import java.beans.Statement;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.util.ExcelLib;

public class CreateOrderPage 
{
	WebDriver driver;
	//private String loginUrl=TestConstants.LogIn_Url;
	private String loginUsername;
	private String loginPassword;
	private String loginURL;
	//Connection con = Database.getConnection();
	// boolean isTestPass=false;
	public String Quotation;
	public String Account;
	public String Ordercomments;
	public String Orderdate;
	public String ordervalidityhrs;
	public String Shippingamount;
	public String orderstatus;
	public String isprocurementcreated;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static String result;
	public static String comment;
	public static boolean newOrderstatus;
	int i=0;
	
private static Logger log = Logger.getLogger (UserLoginLogoutPage.class);
	
	//Test Case for Reading the excel order data.
	public boolean OrderInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			int CreateOrderRowCount = xllib.getRowCount("Orders");
			System.out.println("CreateOrderRowCount:"+CreateOrderRowCount);
			System.out.println("*********************CreateOrder Logger Initialized*****************************************");
			for (i = 1; i <= CreateOrderRowCount; i++) 
			{
				System.out.println("excel reading");
		 		//Reading creating Account excel data
				Quotation = xllib.getExcelData("Orders", i, 0);
				Account = xllib.getExcelData("Orders", i, 1);
				Ordercomments = xllib.getExcelData("Orders", i, 2);
				Orderdate = xllib.getExcelData("Orders", i, 3);
				ordervalidityhrs = xllib.getExcelData("Orders", i, 4);
				Shippingamount = xllib.getExcelData("Orders", i, 5);
				orderstatus = xllib.getExcelData("Orders", i, 6);				
				//Calling Login URL method
				newOrderstatus = Createorderpage(driver,loginURL,loginUsername,loginPassword,Quotation,Account,Ordercomments,Orderdate,ordervalidityhrs,Shippingamount,isprocurementcreated,orderstatus);
				
				System.out.println("Login Count2:"+i);	
				}
		    }
		
		catch(Exception e)
		{
			System.out.println("Login Unsucessful");
			e.printStackTrace();
		}
		return newOrderstatus;
	}

	private boolean Createorderpage(WebDriver driver,String loginURL,String loginUsername,
			String loginPassword, String quotation, String account,
			String ordercomments, String orderdate, String ordervalidityhrs,
			String shippingamount,String isprocurementcreated,String orderstatus) throws InvalidFormatException
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Inspecting for Orders Tab
			driver.findElement(By.cssSelector("a[href='/a0P/o']")).click();
			//Inspecting for New button
			driver.findElement(By.xpath("//input[@value=' New ']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Creating new Orders");
			
			//Select quotation lookup
			/*driver.findElement(By.xpath("//input[@id='CF00N9000000EoEGg']")).sendKeys(Quotation);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
			Thread.sleep(6000);
			driver.findElement(By.xpath("//img[@title='Quotation Lookup (New Window)']")).click(); // Account lookup icon
			String parentWindow = driver.getWindowHandle();
			Set<String> handles =  driver.getWindowHandles();
			for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(parentWindow))
		          {
		          driver.switchTo().window(windowHandle);
		          driver.switchTo().frame(0);//frame1
		          driver.findElement(By.id("lksrch")).clear();
		          driver.findElement(By.id("lksrch")).sendKeys(Quotation);//Search input
		          driver.findElement(By.xpath("//input[@title='Go!']")).click();//Go button
		          
		          driver.switchTo().defaultContent();
		          driver.switchTo().frame(1);//frame2
		          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); // Account Name
		          Thread.sleep(2000);
		          //driver.close(); //closing child window
		          driver.switchTo().window(parentWindow); //cntrl to parent wind
		          }//if loop
		       }//for loop
		   	Thread.sleep(2000);
			System.out.println("quotation lookup");
			
			//Select Account lookup
			/*driver.findElement(By.xpath("//input[@id='CF00N9000000EoEGX']")).sendKeys(Account);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
			log.info("Account lookup");
			Thread.sleep(6000);
			driver.findElement(By.xpath("//img[@title='Account Lookup (New Window)']")).click(); // Account lookup icon
			String parentWindow1 = driver.getWindowHandle();
			Set<String> handles1 =  driver.getWindowHandles();
			for(String windowHandle  : handles1)
		       {
		       if(!windowHandle.equals(parentWindow1))
		          {
		          driver.switchTo().window(windowHandle);
		          driver.switchTo().frame(0);//frame1
		          driver.findElement(By.id("lksrch")).clear();
		          driver.findElement(By.id("lksrch")).sendKeys(Account);//Search input
		          driver.findElement(By.xpath("//input[@title='Go!']")).click();//Go button
		          driver.switchTo().defaultContent();
		          driver.switchTo().frame(1);//frame2
		          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); // Account Name
		          Thread.sleep(2000);
		          //driver.close(); //closing child window
		          driver.switchTo().window(parentWindow1); //cntrl to parent wind
		          }//if loop
		       }//for loop
		   	Thread.sleep(2000);
			System.out.println("Account lookup");
			
			//Select Ordercomments textfield
			driver.findElement(By.xpath("//textarea [@id='00N9000000EoEGb']")).sendKeys(Ordercomments);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Ordercomments textfield");
			//Select Orderdate textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEGc']")).sendKeys(Orderdate);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Orderdate textfield");
			//Select ordervalidityhrs textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEGe']")).sendKeys(ordervalidityhrs);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("ordervalidityhrs textfield");
			//Select Shippingamount textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEGi']")).clear();
			driver.findElement(By.xpath("//input[@id='00N9000000EoEGi']")).sendKeys(Shippingamount);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Shippingamount textfield");
			Thread.sleep(2000);
			//select the is procurement created checkbox
			driver.findElement(By.id("00N9000000EoEGa")).click();
			System.out.println("isprocurementcreated enabled");
			Thread.sleep(2000);
			
			//Select the orderstatus dropdown
			WebElement clusterText =  driver.findElement(By.id("00N9000000EoEGd"));
			Select st2 = new Select(clusterText);
			Thread.sleep(6000);
			st2.selectByVisibleText(orderstatus);
			System.out.println("selected the orderstatus dropdown");
			
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			
			//Inspecting for Save button
			driver.findElement(By.xpath("//input[@tabindex='14']")).click();
			System.out.println("save");	
			//QuotationInitialPage(driver);
			
			if(!newOrderstatus)
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				System.out.println("CreateOrder process is complete...");
				return true;
			}
			catch(Exception e)
			{
				System.out.println("CreateOrder Unsuccessful");
				return false;
			}		
	    }
    }
