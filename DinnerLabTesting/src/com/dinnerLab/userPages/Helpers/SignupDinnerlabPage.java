package com.dinnerLab.userPages.Helpers;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.util.ExcelLib;

public class SignupDinnerlabPage
{
	private static String signupURL;
	private static String firstName;
	private static String lastName;
	private static String email;
	private static String confirmemail;
	private static String password;
	private static String confirmpassword;
	private static String phone;
	private static String city;
	private static String street;
	private static String billingcity;
	private static String state;
	private static String zipcode;
	private static String cardholdersName;
	private static String cardNumber;
	private static String cardexpirymonth;
	private static String cardexpiryyear;
	private static String cvv;
	
	public static Boolean signupstatus; 
	public static Boolean paymentstatus;
	public static String isTestPassed="FAIL";
	int i=0;
	
	public WebDriver driver;
	private static Logger log = Logger.getLogger(SignupDinnerlabPage.class);
	
	/**
	 * Test Case for Reading the excel data for signing up as a Member
	 * portal on successful registration to the application.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	*/
	public void signupInitialPage(WebDriver driver)
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			int rowCount = xllib.getRowCount("SignupDinnerlab");
			//For loop:Fetch data from excel input configuration file(excel sheet)
			for(i=1;i<= rowCount;i++)
			{
				signupURL = xllib.getExcelData("SignupDinnerlab", i, 0);
				firstName = xllib.getExcelData("SignupDinnerlab", i, 1);
				lastName = xllib.getExcelData("SignupDinnerlab", i, 2);
				email = xllib.getExcelData("SignupDinnerlab", i, 3);
				confirmemail = xllib.getExcelData("SignupDinnerlab", i, 4);
				password = xllib.getExcelData("SignupDinnerlab", i, 5);
				confirmpassword = xllib.getExcelData("SignupDinnerlab", i, 6);
				phone = xllib.getExcelData("SignupDinnerlab",i, 7);
				city = xllib.getExcelData("SignupDinnerlab",i, 8);
				street = xllib.getExcelData("SignupDinnerlab",i, 9);
				billingcity = xllib.getExcelData("SignupDinnerlab",i, 10);
				state = xllib.getExcelData("SignupDinnerlab",i, 11);
				zipcode = xllib.getExcelData("SignupDinnerlab",i, 12);
				cardholdersName = xllib.getExcelData("SignupDinnerlab",i, 13);
				cardNumber = xllib.getExcelData("SignupDinnerlab",i, 14);
				cardexpirymonth = xllib.getExcelData("SignupDinnerlab",i, 15);
				cardexpiryyear = xllib.getExcelData("SignupDinnerlab",i, 16);
				cvv = xllib.getExcelData("SignupDinnerlab",i, 17);
				
				
				signupstatus=signupActionPage(driver,signupURL,firstName,lastName,email,confirmemail,password,confirmpassword,phone,city);
				System.out.println("Status is :"+signupstatus);
				
				
				if(signupstatus)
				{
					
					log.info("Signup page was successfull...");
					System.out.println("Signup page was successfull");
					log.info("--------------------------------------");
					
					paymentstatus=paymentActionPage(driver,street,billingcity,state,zipcode,cardholdersName,cardNumber,cardexpirymonth,cardexpiryyear,cvv);
					
					if(paymentstatus)
					{
						isTestPassed="PASS";
						xllib.writeToExcel("SignupDinnerlab", i, 18, isTestPassed);
					}
					else if(!paymentstatus)
					{
						isTestPassed = "FAIL";
						xllib.writeToExcel("SignupDinnerlab", i, 18, isTestPassed);
					}
				}
				else if(!signupstatus)
				{
					log.info("Signup page was Unsuccessfull...");
					System.out.println("Signup page was Unsuccessfull");
					log.info("--------------------------------------");
				}
				
				
			}//end of for loop
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	private Boolean signupActionPage(WebDriver driver,String SignupURL,String Firstname,String Lastname,String Email,String Confirmemail
			,String Password , String Confirmpassword,String Phone,String City)
	{
		try
		{
			log.info("Signup Action method");
			log.info("Navigating to Dinnerlab website");
			driver.navigate().to(SignupURL);
			driver.findElement(By.partialLinkText("JOIN US")).click();// Join us button
			
			Thread.sleep(2000);
			WebElement identifier = driver.findElement(By.id("Group_ID"));//City Name
			Select sel = new Select(identifier);
			sel.selectByVisibleText(City);
			
			//driver.findElement(By.id("Group_ID")).sendKeys(City);//City Name
			Thread.sleep(2000);
			driver.findElement(By.id("payment-url")).click();//Get Membership button
			
			Thread.sleep(2000);
			driver.findElement(By.id("firstName")).sendKeys(Firstname);//FirstName
			driver.findElement(By.id("lastName")).sendKeys(Lastname);//LastName
			driver.findElement(By.id("email")).sendKeys(Email);//Email
			driver.findElement(By.id("confemail")).sendKeys(Confirmemail);//Confirmemail
			driver.findElement(By.id("password")).sendKeys(Password);//Password
			driver.findElement(By.id("cpassword")).sendKeys(Confirmpassword);//Confirmpassword
			driver.findElement(By.id("phone")).sendKeys(Phone);//phone
			
			driver.findElement(By.xpath("//input[@value='Submit']")).click(); // Submit button
			
			
			/*if(isAlertPresent())
			{
				Alert alert = driver.switchTo().alert();
				// check if alert exists
				// TODO find better way
				alert.getText();

	        	// alert handling
	        	log.info("Alert detected: {}" + alert.getText());
	        	alert.accept();
			}
			else if(!isAlertPresent())
			{
				log.info("Submitted Successfully:");
			}*/
			
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}//end of signupactionpage method
	
	
	public boolean paymentActionPage(WebDriver driver,String Street,String Billingcity,String State,String Zipcode
			,String Cardholdersname,String Cardnumber,String Cardexpirymonth,String Cardexpiryyear,String cvv) throws InterruptedException
	{
		try
		{
		//PAYMENT PAGE
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='street']")).clear();
		driver.findElement(By.xpath("//input[@name='street']")).sendKeys(Street);//street
		
		driver.findElement(By.xpath("//input[@name='city']")).clear();
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(Billingcity);//Billingcity
		
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By.xpath("//select[@name='state']"));//State
		Select sel = new Select(ele);
		sel.selectByVisibleText(State);
		
		driver.findElement(By.xpath("//input[@name='zip']")).clear();
		driver.findElement(By.xpath("//input[@name='zip']")).sendKeys(Zipcode);//zipcode
		
		driver.findElement(By.xpath("//input[@name='cardholdername']")).clear();
		driver.findElement(By.xpath("//input[@name='cardholdername']")).sendKeys(Cardholdersname);//Cardholdersname
		
		driver.findElement(By.id("cardnumber")).clear();
		driver.findElement(By.id("cardnumber")).sendKeys(Cardnumber);//Cardnumber
		
		Thread.sleep(2000);
		WebElement ele1 = driver.findElement(By.xpath("//select[@class='card-expiry-month stripe-sensitive required form-control']"));//Cardexpirymonth
		Select sel1 = new Select(ele1);
		sel1.selectByVisibleText(Cardexpirymonth);
		
		Thread.sleep(2000);
		WebElement ele2 = driver.findElement(By.xpath("//select[@class='card-expiry-year stripe-sensitive required form-control']"));//Cardexpiryyear
		Select sel2 = new Select(ele2);
		sel2.selectByVisibleText(Cardexpiryyear);
		
		driver.findElement(By.id("cvv")).clear();
		driver.findElement(By.id("cvv")).sendKeys(cvv);//cvv
		
		driver.findElement(By.id("diseablebtn")).click();//Get Your Membership button
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}// end of paymentActionPage method
	
	public boolean isAlertPresent()
	{
		try
		{
			//driver.switchTo().alert();
			return true;

		}
		catch(NoAlertPresentException ex)
		{
			return false;
		}
	
	}//end of isAlertPresent() method
}
