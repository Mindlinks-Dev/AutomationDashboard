package com.autoparts.userpage;

import java.beans.Statement;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dinnerLab.util.ExcelLib;


public class CreateOrderPartsPage
{
	WebDriver driver;
	//private String loginUrl=TestConstants.LogIn_Url;
	private String loginUsername;
	private String loginPassword;
	private String loginURL;
	//Connection con = Database.getConnection();
	// boolean isTestPass=false;
	public String Order;
	public String Partname;
	public String Parttype;
	public String Partmake;
	public String Partmodel;
	public String Partyear;
	public String Partquantity;
	public String Netamount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static String result;
	public static String comment;
	public static boolean neworderpartsstatus;
	private static String newQuotationPartsNumber;
	int i=0;
	
private static Logger log = Logger.getLogger (UserLoginLogoutPage.class);
	
	//Test Case for Reading the excel QuotationParts data.
	public boolean OrderPartsInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			int CreateOrderPartsRowCount = xllib.getRowCount("OrderParts");
			System.out.println("CreateOrderPartsRowCount:"+CreateOrderPartsRowCount);
			System.out.println("*********************OrderParts Logger Initialized*****************************************");
			for (i = 1; i <= CreateOrderPartsRowCount; i++) 
			{
				System.out.println("excel reading");
		 		//Reading creating Account excel data
				Order = xllib.getExcelData("OrderParts", i, 0);
				Partname = xllib.getExcelData("OrderParts", i, 1);
				Parttype = xllib.getExcelData("OrderParts", i, 2);
				Partmake = xllib.getExcelData("OrderParts", i, 3);
				Partmodel = xllib.getExcelData("OrderParts", i, 4);
				Partyear = xllib.getExcelData("OrderParts", i, 5);
				Partquantity = xllib.getExcelData("OrderParts", i, 6);
				Netamount = xllib.getExcelData("OrderParts", i, 7);
				
				//Calling Login URL method
				neworderpartsstatus = Createorderpartspage(driver,loginURL,loginUsername,loginPassword,Order,Partname,Parttype,Partmake,Partmodel,Partyear,Partquantity,Netamount);
				System.out.println("Login Count3:"+i);	
				}
		    }
		
		catch(Exception e)
		{
			System.out.println("Login Unsucessful");
			e.printStackTrace();
		}
		return neworderpartsstatus;
	}

	private boolean Createorderpartspage(WebDriver driver,String loginURL,String loginUsername, String loginPassword, String order,String partname, String parttype, String partmake,
	String partmodel, String partyear, String partquantity,String netamount) throws InvalidFormatException
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Inspecting for OrderParts Tab
			driver.findElement(By.cssSelector("a[href='/a0N/o']")).click();
			//Inspecting for New button
			driver.findElement(By.xpath("//input[@value=' New ']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Creating new orderparts");
			
			//Select order lookup
			/*driver.findElement(By.xpath("//input[@id='CF00N9000000EoEG3']")).sendKeys(Order);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
			log.info("order lookup");
			Thread.sleep(6000);
			driver.findElement(By.xpath("//img[@title='Order Lookup (New Window)']")).click(); // order lookup icon
			String parentWindow = driver.getWindowHandle();
			Set<String> handles =  driver.getWindowHandles();
			for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(parentWindow))
		          {
		          driver.switchTo().window(windowHandle);
		          driver.switchTo().frame(0);//frame1
		          driver.findElement(By.id("lksrch")).clear();
		          driver.findElement(By.id("lksrch")).sendKeys(Order);//Search input
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
			System.out.println("Order lookup");
			//Select Partname textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEGO']")).sendKeys(Partname);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Partname textfield");
			//Select Parttype textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEGQ']")).sendKeys(Parttype);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Parttype textfield");
			//Select Partmake textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEGM']")).sendKeys(Partmake);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Partmake textfield");
			//Select Partmodel textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEGN']")).sendKeys(Partmodel);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Partmodel textfield");
			//Select Partyear textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEGR']")).sendKeys(Partyear);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Partyear textfield");
			//Select Partquantity textfield
			//driver.findElement(By.xpath("//input[@id='00N9000000EoEGP']")).clear();
			driver.findElement(By.xpath("//input[@id='00N9000000EoEGP']")).sendKeys(Partquantity);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Partquantity textfield");
			//Select Netamount textfield
			driver.findElement(By.xpath("//input[@id='00N9000000EoEGK']")).clear();
			driver.findElement(By.xpath("//input[@id='00N9000000EoEGK']")).sendKeys(Netamount);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Netamount textfield");
			//Inspecting for Save button
			driver.findElement(By.xpath("//input[@tabindex='11']")).click();
			System.out.println("save");	
			//QuotationInitialPage(driver);
			
			if(!neworderpartsstatus)
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				System.out.println("Createorderparts process is complete...");
				return true;
			}
			catch(Exception e)
			{
				System.out.println("Createorderparts Unsuccessful");
				return false;
					}
				}
			}
	

