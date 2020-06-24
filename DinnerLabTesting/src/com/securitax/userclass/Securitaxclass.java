package com.securitax.userclass;

import java.awt.AWTException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Securitaxclass
{	
	@Test
	public void launchBrowser () throws InterruptedException, AWTException, InvalidFormatException
	{
		//Launching Browser
		System.setProperty("webdriver.chrome.driver","D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\com\\src\\test\\resources\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
	/*	//Login to DSp
		loginpage login = new loginpage();
		login.LoginInitialPage(driver);
		Thread.sleep(8000);
		
		//Initial Registration
		initialReg registration = new initialReg();
		registration.initialRegistration(driver);		
		registration.selfRegistration(driver);
		
		//Driver Login
		DiverLog Dlog = new DiverLog();
		Dlog.DriverLoginStatus(driver);
		Dlog.driverContracts(driver);
		Dlog.driverMessage(driver);
		Dlog.driverInvoice(driver);
		Dlog.driverUpload(driver);
		
		//Paymentrun Flow
		PaymentRunFlow pay = new PaymentRunFlow();
		pay.Securitaxlogin(driver);
		pay.getPaymentrunRecords(driver);
		pay.enterPaymentdata(driver);*/
		
		//Gmail login and Self Registration
		loginandSelfReg.gMaillogin(driver);
		loginandSelfReg.selfRegistration(driver);
	}	
}
