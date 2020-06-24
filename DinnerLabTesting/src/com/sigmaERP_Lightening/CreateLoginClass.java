package com.sigmaERP_Lightening;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CreateLoginClass {

	public static WebDriver driver;
	@Test
	public static void launch() throws InterruptedException
	{

		System.setProperty("webdriver.chrome.driver","D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\com\\src\\test\\resources\\Drivers\\chromedriver.exe");
		 driver = new ChromeDriver();	
		driver.manage().window().maximize();

		
		CreateLoginPage A1 = new CreateLoginPage();
		A1.logincreation(driver);
		Thread.sleep(8000);
		
		/*CreateProductPage A2 = new CreateProductPage();
		A2.productcreation(driver);
		Thread.sleep(3000);*/
		
		/*CreatePurchaseOrderPage A3 = new CreatePurchaseOrderPage();
		A3.PurchaseOrder(driver);
		Thread.sleep(3000);*/
		
		/*CreateStockReceivingPage A4 = new CreateStockReceivingPage();
		A4.stockreceiving(driver);
		Thread.sleep(3000);*/
	
		/*CreateSalesOrder A5 = new CreateSalesOrder();
		A5.salesorder(driver);
		Thread.sleep(3000);*/
	
		/*CreatePackagesPage A6 = new CreatePackagesPage();
		A6.packages(driver);
		Thread.sleep(3000);*/
		
		CreateShipmentPage A7 = new CreateShipmentPage();
		A7.shipment(driver);
		Thread.sleep(3000);
		
	}
}
