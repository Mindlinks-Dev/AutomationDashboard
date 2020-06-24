package com.jandk.userpage;

import java.beans.Statement;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dinnerLab.util.ExcelLib;

public class CreateAccountPage
{
	WebDriver driver;
	
	public String AccountName;
	public String AccountNumber;
	public String Description;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean newAccountstatus;
    int i =0;
    
    private static Logger log = Logger.getLogger (LoginLogoutPage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean AccountInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int createaccountRowCount = xllib.getRowCount("Account");
			System.out.println("createaccountRowCount:"+createaccountRowCount);
			log.info("Createaccount log initialized ");
			for (i = 1; i <= createaccountRowCount; i++) 
			{
				AccountName = xllib.getExcelData("Account", i, 0);
				AccountNumber = xllib.getExcelData("Account", i, 1);
				Description = xllib.getExcelData("Account", i, 2);
								
				newAccountstatus = createaccount(driver,AccountName,AccountNumber,Description);
				System.out.println("Login Count:"+i);
			}
		}
		catch(Exception e)
		{
			System.out.println("Account Unsucessful");
			e.printStackTrace();
		}
		return newAccountstatus;
}

	private boolean createaccount(WebDriver driver, String accountName,
			String accountNumber, String description) 
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			System.out.println("AccountName"+AccountName);
			System.out.println("AccountNumber"+AccountNumber);
			System.out.println("Description"+Description);
			
			
			//inspect for Account Tab
			driver.findElement(By.xpath("//a[@title='Accounts']/span[1]")).click();
			log.info("Account Tab");
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			
			//inspect for new button
			driver.findElement(By.xpath("//div[@title='New']")).click();
			log.info("new button");
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			
			//Select the record type radio button
			driver.findElement(By.xpath("//fieldset[@class='slds-form-element slds-no-space firefox-fieldset-bug-fix']/div[2]/div[7]/label/div[2]/span")).click();
			log.info("record type");
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			
			//click on next button
			driver.findElement(By.xpath("//button[@class='slds-button slds-button--neutral uiButton--default uiButton--brand uiButton']")).click();
			log.info("next button");
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			
			//select accountName text field
			//driver.findElement(By.xpath("//input[@id='2589:0']")).sendKeys(AccountName);
			driver.findElement(By.xpath("//input[@class='input uiInput uiInputText uiInput--default uiInput--input']")).sendKeys(AccountName);
			log.info("accountName");
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			
			//Select the accountnumber text field
			/*driver.findElement(By.xpath("//input[@data-aura-rendered-by='3465:0']")).sendKeys(AccountNumber);
			log.info("accountNumber");
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			
			//selectt the description text field
			driver.findElement(By.cssSelector("textarea[data-aura-rendered-by='3535:0']")).sendKeys(Description);
			log.info("Description");
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);*/
			
			//click on save button
			driver.findElement(By.xpath("//button[@title='Save']")).click();
			log.info("save");
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			log.info("Driver Account created");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("createaccount Unsuccessful");
			return false;
		}
	}
}
