package com.cohbaines.testpages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



import com.dinnerLab.util.ExcelLib;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class AccountTestpage {
	WebDriver driver;
	private String accountAction;

	private String AccountName;
	private String ShippingStreet;
	private String ShippingCity;
	private String Email;
	private String BillingCountry;
	
	public static boolean newAccountstatus;
	int i = 0;
	private static Logger log = Logger.getLogger(AccountTestpage.class);

	public boolean accountsInitialPage1(WebDriver driver)
			throws InvalidFormatException {

		try {
			ExcelLib xllib = new ExcelLib();

			int rowCount = xllib.getRowCount("AccountPage");
			System.out.println("rowCount:" + rowCount);
			// For loop:Fetch data from excel input configuration file(excel
			// sheet)
			for (i = 1; i <= rowCount; i++) {
				AccountName = xllib.getExcelData("AccountPage", i, 0);
				ShippingStreet = xllib.getExcelData("AccountPage", i, 1);
				ShippingCity = xllib.getExcelData("AccountPage", i, 2);
				Email = xllib.getExcelData("AccountPage", i, 3);
				BillingCountry = xllib.getExcelData("AccountPage", i, 4);
				
				newAccountstatus = createaccount(driver, AccountName,
						ShippingStreet, ShippingCity, Email,
						BillingCountry);			
						System.out.println("Login Count:" + i);
			}
		} catch (Exception e) {
			System.out.println("Account Unsucessful");
			e.printStackTrace();
		}
		return newAccountstatus;
	}

	private boolean createaccount(WebDriver driver, String AccountName,
			String ShippingStreet, String ShippingCity, String Email,
			String BillingCountry) {
		try {
			driver.findElement(By.xpath("//a[@title='Accounts']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@title='New']")).click();
			Thread.sleep(5000);

			driver.findElement(
					By.xpath("html/body/div[5]/div[2]/div[3]/div[2]/div/div[3]/div/button[2]"))
					.click();

			driver.findElement(
					By.xpath("//input[@class='input uiInput uiInputText uiInput--default uiInput--input']"))
					.sendKeys(AccountName);

		/*	driver.findElement(
					By.xpath(" //textarea[@placeholder='Shipping Street']")).sendKeys(ShippingStreet);
			
			driver.findElement(By.xpath("//input[@placeholder='Shipping City']")).sendKeys(ShippingCity);
			System.out.println("entered shipping city");
			
			JavaScriptExecutor js = (JavaScriptExecutor)driver;
			js.executeScript("window.scrollBy(0,250)", "");
			
			Thread.sleep(8000);
			Actions act = new Actions(driver);
			WebElement we=	
			//Thread.sleep(8000);
			//((JavascriptExecutor)driver).executeScript("arguments[0].click;", driver.findElement(By.xpath("(//div[@class='uiPopupTrigger']/descendant::a[1][1])[8]")));
			
			act.click(we);
		
			
			act.moveToElement(we);
			act.click();
			act.build().perform();
			
			
			Thread.sleep(15000);
		     
		     System.out.println("element clickedc");
			//driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Email);
		    //Thread.sleep(4000);
			
		   /* List<WebElement> lst = driver.findElements(By
					.xpath("(//div[@class='uiPopupTrigger']/descendant::a[1])[7]"));
					
					//a[@data-interactive-lib-uid='46']
			for (WebElement we : lst) {
				String str = we.getText();
				if (str.equalsIgnoreCase("UK"))
				{
			
					we.click();
				}
			}

		     
				
		driver.findElement(By.xpath("//button[@title='Save']")).click();
			
		driver.findElement(By.xpath(".//*[@id='5936:0']/div[2]/div/div/div[1]/article/div[1]/div/div/ul/li/a/div")).click();


//div[@class='uiPopupTrigger']/descendant::a)[6]
//div[@title='New']"))
			
   
			/*driver.findElement(
					By.xpath("(//div[@class='uiPopupTrigger']/descendant::a)[2]"))
					.click();
			List<WebElement> lst1 = driver.findElements(By
					.xpath("//ul[@class='scrollable']/li"));
			for (WebElement we : lst1) {
				String str = we.getText();
				if (str.equalsIgnoreCase(Zone))
				{
					we.click();
				}
			}
			
			driver.findElement(
					By.xpath("//div[@class='uiInput uiInputTextArea uiInput--default uiInput--textarea']/descendant::textarea[@class='compoundTLRadius compoundTRRadius compoundBorderBottom textarea']"))
					.sendKeys(billingStreet);
			
			
			driver.findElement(By.xpath("//div[@class='inlineField form-element__control size--2-of-3']/descendant::input[@class='compoundBorderRight compoundBorderBottom input']")).sendKeys(billingCity);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@class='uiInput uiInputText uiInput--default uiInput--input']/descendant::input[@class='compoundBorderBottom input']")).sendKeys(billingState);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@class='uiInput uiInputText uiInput--default uiInput--input']/descendant::input[@class='compoundBRRadius input']")).sendKeys(billingCountry);
			Thread.sleep(5000);*/
			driver.findElement(By.xpath("//div[@class='forceModalActionContainer--footerAction forceModalActionContainer']/descendant::button[@class='slds-button slds-button--neutral uiButton--default uiButton--brand uiButton forceActionButton']")).click();
			Thread.sleep(5000);
			//driver.findElement(By.xpath("//a[@title='Accounts']")).click();
			driver.close();
			
		} catch (Exception e) {
			System.out.println("createaccount Unsuccessful");

		}
		return false;

	}

}
