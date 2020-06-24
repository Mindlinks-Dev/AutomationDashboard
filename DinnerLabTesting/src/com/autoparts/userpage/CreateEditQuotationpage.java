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



public class CreateEditQuotationpage 
{
	
		WebDriver driver;
		//private String loginUrl=TestConstants.LogIn_Url;
		//Connection con = Database.getConnection();
		// boolean isTestPass=false;
		public String Customername;
		public String Quotationstatus;
		public String Quotationvalidityhrs;
		public String Shippmentamount;
		public String Isordercreated;
		public String Quotationcomments;
		public String Quotationdate;
		boolean present;
		Statement stm=null;
		String isTestPassed="FAIL";
		public static String result;
		public static String comment;
		public static boolean newQuotationstatus;
		int i=0;
		
		private static Logger log = Logger.getLogger (UserLoginLogoutPage.class);
		
		/**
		 * Test Case for Reading the excel Quotation data.
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
		 */
		public boolean QuotationInitialPage(WebDriver driver) throws InvalidFormatException
		{
			try
			{
				ExcelLib xllib = new ExcelLib();
				
				int createquotationRowCount = xllib.getRowCount("Quotation");
				System.out.println("createquotationRowCount:"+createquotationRowCount);
				System.out.println("*********************Quotation Logger Initialized*****************************************");
				for (i = 1; i <= createquotationRowCount; i++) 
				{
					System.out.println("excel reading");
			 		//Reading creating Account excel data
					Customername = xllib.getExcelData("Quotation", i, 0);
					Quotationstatus = xllib.getExcelData("Quotation", i, 1);
					Quotationvalidityhrs = xllib.getExcelData("Quotation", i, 2);
					Shippmentamount = xllib.getExcelData("Quotation", i, 3);
					Quotationcomments = xllib.getExcelData("Quotation", i, 4);
					Quotationdate = xllib.getExcelData("Quotation", i, 5);
					
					//Calling Login URL method
					newQuotationstatus = LoginpageCreateEditQuotationpage(driver,Customername,Quotationstatus,Quotationvalidityhrs,Shippmentamount,Quotationcomments,Quotationdate);
					System.out.println("Login Count:"+i);
					
			/*	if(newQuotationstatus==true)
					{
						isTestPassed="PASS";
						xllib.writeToExcel("Login", i, 6, isTestPassed);
						//UserLoginLogoutPage.logoutActions(driver);
						//.logoutActions(driver);
					}
					else
					{
						isTestPassed = "FAIL";
						xllib.writeToExcel("Login", i, 7, isTestPassed);
						//ScriptHandler.comment.add("Script Login, Test Data "+i+": Failed in Login page.");							
					}*/
				}
			}
			
			catch(Exception e)
			{
				System.out.println("quotation Unsucessful");
				e.printStackTrace();
			}
			return newQuotationstatus;
		}
		
		
		public boolean LoginpageCreateEditQuotationpage(WebDriver driver,String Customername,
				String quotationstatus, String quotationvalidityhrs,
				String shippmentamount,String Quotationcomments,String Quotationdate) throws InvalidFormatException
		{
			try
			{
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			 	/*driver.get(Testconstants.LOGIN_URL);
			 	driver.findElement(By.id("username")).clear();
				driver.findElement(By.id("username")).sendKeys(loginUsername);
				driver.findElement(By.id("password")).clear();
				driver.findElement(By.id("password")).sendKeys(loginPassword);
				driver.findElement(By.id("Login")).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				System.out.println("login successful");*/
				System.out.println("Customername"+Customername);
				System.out.println("Quotationstatus"+Quotationstatus);
				System.out.println("Quotationvalidityhrs"+Quotationvalidityhrs);
				System.out.println("Shippmentamount"+Shippmentamount);
				//Inspecting for Quotation Tab
				driver.findElement(By.cssSelector("a[href='/a0W/o']")).click();
				//Inspecting for New button
				driver.findElement(By.xpath("//input[@value=' New ']")).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				System.out.println("Creating new quo");
				//Select customer name lookup field
				Thread.sleep(6000);
				driver.findElement(By.xpath("//img[@title='Customer Name Lookup (New Window)']")).click(); // customer lookup icon
				String parentWindow = driver.getWindowHandle();
				Set<String> handles =  driver.getWindowHandles();
				for(String windowHandle  : handles)
			       {
			       if(!windowHandle.equals(parentWindow))
			          {
			          driver.switchTo().window(windowHandle);
			          driver.switchTo().frame(0);//frame1
			          driver.findElement(By.id("lksrch")).clear();
			          driver.findElement(By.id("lksrch")).sendKeys(Customername);//Search input
			          driver.findElement(By.xpath("//input[@value=' Go! ']")).click();//Go button
			          driver.switchTo().defaultContent();
			          driver.switchTo().frame(1);//frame2
			          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); 
			          Thread.sleep(2000);
			          //driver.close(); //closing child window
			          driver.switchTo().window(parentWindow); //cntrl to parent wind
			          }//if loop
			       }//for loop
			   	Thread.sleep(2000);
				//driver.findElement(By.cssSelector("#CF00N9000000EoEHT")).sendKeys(Customername);
				System.out.println("Select customer name lookup field");
				//Inspecting for Quotation status drop down
				WebElement clusterText =  driver.findElement(By.id("00N9000000EoEHa"));
				Select st2 = new Select(clusterText);
				Thread.sleep(6000);
				st2.selectByVisibleText(Quotationstatus);
				System.out.println("Quot status drop down Field");
				//Inspecting for Quot Validity Hrs Text Field
				driver.findElement(By.cssSelector("input[id='00N9000000EoEHX']")).clear();
				driver.findElement(By.cssSelector("input[id='00N9000000EoEHX']")).sendKeys(Quotationvalidityhrs);
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				System.out.println("Quot Validity Hrs Text Field");
				//input[@id='00N9000000EoEHX']
				//Inspecting for Shipping Amount Text Field
				driver.findElement(By.xpath("//input[@id='00N9000000EoEHb']")).clear();
				driver.findElement(By.xpath("//input[@id='00N9000000EoEHb']")).sendKeys(Shippmentamount);
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				System.out.println("Shipping Amount Text Field");
				//Inspecting for Quotation comments Text Field
				driver.findElement(By.id("00N9000000EoEHY")).sendKeys(Quotationcomments);
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				//Inspecting for Quotation date Text Field
				driver.findElement(By.xpath("//input[@id='00N9000000EoEHZ']")).sendKeys(Quotationdate);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				//Inspecting for is order created check box
				//driver.findElements(By.xpath("//input[@id='00N9000000EoEHW']")).click();
				driver.findElement(By.xpath("//input[@id='00N9000000EoEHW']")).click();
				//Inspecting for Save button
				driver.findElement(By.xpath("//input[@tabindex='10']")).click();
				System.out.println("save");	
				//QuotationInitialPage(driver);
				
				if(!newQuotationstatus)
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				System.out.println("createquotation process is complete...");
				return true;
			}
			catch(Exception e)
			{
				System.out.println("createquotation Unsuccessful");
				return false;
				
			}
		}
   }
		
  
		/*public void verifyNewQuotationCreated(WebDriver driver)
		{
			try
			{
				Thread.sleep(3000);
				newQuotationstatus = editButton.isEnabled();
				newQuotationNumber = editButton.getText();
				System.out.println("newQuotationstatus:"+newQuotationstatus);
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		/**
		 * Clicking on New button
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
		*/
		/* public void clickOnNewButton(WebDriver driver)
		 {
			 try
			 {
				 Thread.sleep(5000);
				 newButton.click();
				 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		 }*/
	
	

