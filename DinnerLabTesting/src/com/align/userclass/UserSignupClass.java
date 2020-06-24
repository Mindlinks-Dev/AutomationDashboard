package com.align.userclass;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.alignsandbox.java.com.Base.IntegrationTestbase;


public class UserSignupClass extends IntegrationTestbase 
{

	@Test(dataProviderClass = IntegrationTestbase.class, dataProvider = "dp")
	public static void AlignSignup(String SignupURL,String CompanyName, String Email, String FirstName,String LastName, String PhoneNumber,String CreatePassword,String PromoCode, String CoachsName,String PhoneArea, String Ext)throws InterruptedException
	{
		driver.manage().window().maximize();
		Thread.sleep(5000l);
	  	{

	     			//Redirect to signup page
	     			driver.get(SignupURL);
	     			Thread.sleep(8000);
	     			Thread.sleep(8000);
	     			
	     			//Enter the company name text field
	     			//driver.findElement(By.xpath("//input[@data-bind='value: newAccount.Company.Name']")).sendKeys(CompanyName);
	     			driver.findElement(By.xpath("//ul[@class='form full no-style form-columns']/li[1]/label[1]/input[1]")).click();
	     			driver.findElement(By.xpath("//ul[@class='form full no-style form-columns']/li[1]/label[1]/input[1]")).sendKeys(CompanyName);
	     			Thread.sleep(5000);
	     			
	     			//Enter the Email id text field
	     			//driver.findElement(By.xpath("//input[@data-bind='value: newAccount.Email, events: { change: validateEmail }'']")).click();
	     			driver.findElement(By.xpath("//input[@type='email']")).click();
	     			driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Email);
	     			Thread.sleep(5000);
	     			
	     			 //Enter the first name
	     		     driver.findElement(By.xpath("//ul[@class='form full no-style form-columns']/li[2]/label[1]/input[1]")).click();
	     		     driver.findElement(By.xpath("//ul[@class='form full no-style form-columns']/li[2]/label[1]/input[1]")).sendKeys(FirstName);
	     		     Thread.sleep(5000);
	     		
	     		    //Enter the last name
	     		     driver.findElement(By.xpath("//input[@data-bind='value: newAccount.LastName']")).click();
	     		     driver.findElement(By.xpath("//input[@data-bind='value: newAccount.LastName']")).sendKeys(LastName);
	     		     Thread.sleep(5000);
	     		     
	     		     //Enter the phone area
	     		     driver.findElement(By.xpath("//input[@placeholder='(Area)']")).click();
	     		     driver.findElement(By.xpath("//input[@placeholder='(Area)']")).sendKeys(PhoneArea);
	     		     Thread.sleep(5000);
	     		     
	     		     //Enter the phone number
	     		     driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).click();
	     		     driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).sendKeys(PhoneNumber);
	     		     Thread.sleep(5000);
	     		     
	     		     //Enter the EXT.
	     		     driver.findElement(By.xpath("//input[@placeholder='Ext.']")).click();
	     		     driver.findElement(By.xpath("//input[@placeholder='Ext.']")).sendKeys(Ext);
	     		     Thread.sleep(5000);
	     		  
	     		     
	     		     //Enter the password
	     		     driver.findElement(By.xpath("(//input[@placeholder='Password'])[1]")).click();
	     		     driver.findElement(By.xpath("(//input[@placeholder='Password'])[1]")).sendKeys(CreatePassword);
	     		     Thread.sleep(5000);
	     		
	     		    //enable the show password check box
	                  WebElement oCheckBox = driver.findElement(By.xpath("//div[@class='checker']/span")); 
	                  oCheckBox.click();
	     		     //driver.findElement(By.xpath("(//div[@class='checker']/span/input")).click();
	     		     Thread.sleep(5000);
	     		     
	     		     //Enter the promo code
	     		     driver.findElement(By.xpath("//input[@data-bind='value: promoCode']")).click();
	     		     driver.findElement(By.xpath("//input[@data-bind='value: promoCode']")).sendKeys(PromoCode);
	     		     Thread.sleep(5000);
	     		     
	     		     //Enter the coach name
	     		     driver.findElement(By.xpath("//input[@data-bind='value: coachNameAtSignUp']")).click();
	     		     driver.findElement(By.xpath("//input[@data-bind='value: coachNameAtSignUp']")).sendKeys(CoachsName);
	     		     Thread.sleep(5000);
	     		     
	     		     // click on recaptcha checkbox
	     		     WebElement frame = driver.findElement(By.xpath("(//iframe[contains(@src, 'recaptcha')])[1]")); 
	     		     driver.switchTo().frame(frame);
	     		     WebElement checkbox = driver.findElement(By.xpath(".//*[@id='recaptcha-anchor']/div[5]"));
	     		     checkbox.click();
	     		     
	     		    Thread.sleep(18000);
	     		}
	}
}
