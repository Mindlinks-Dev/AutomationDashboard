package com.alignsandbox.java.com.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.alignsandbox.java.com.utilities.ExcelReading;


public class AlignTestbase {

	public static WebDriver driver;
	public static int row;
	public static FileInputStream fis;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static Logger log = Logger.getLogger(AlignTestbase.class);
	public static ExcelReading excel = new ExcelReading("D://DinnerLabWorkSpace//DinnerLabTesting//src//Data.xlsx");
	public static String Browser = "chrome";

	// public static ExcelReading excel = new
	// ExcelReading("D:/DinnerLabWorkSpace/DinnerLabTesting/src/Data.xls");

	@BeforeSuite
	public static void setup() throws IOException, InterruptedException {

		System.out.println(System.getProperty("user.dir"));
		
		 if (driver == null) {
		  
		  fis = new FileInputStream(
		  "D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\com\\alignsandbox\\java\\com\\Base\\Config");
		  
		  config.load(fis); log.debug("loading config file");
		  
		  
		  
/*		  fis = new FileInputStream(
		  "D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\com\\src\\test\\resources\\properties\\OR"
		  );
		  
		  OR.load(fis);
		  
		log.debug("loading or file");*/

		if (Browser.equals("chrome")) {

			System.setProperty(
					"webdriver.chrome.driver",
					"D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\chromedriver_win32(2.36)\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		//Redirect to login page
		driver.navigate().to(config.getProperty("Testurl"));
         Thread.sleep(12000);
		
		//Enter the user name text field
		driver.findElement(By.xpath("//input[@name='username']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(config.getProperty("UN"));
		
		 //Enter password
	     driver.findElement(By.xpath("(//input[@name='password'])[1]")).click();
	     driver.findElement(By.xpath("(//input[@name='password'])[1]")).sendKeys(config.getProperty("PWD"));

	     
	     //Click login button
	     driver.findElement(By.xpath("//button[@class='button green']")).click();
		 log.debug("login successfull");
		 Thread.sleep(8000);
	/*	   // click on oh no- ok button
	     try
	     {
		 //driver.findElement(By.xpath("//div[@class='k-widget k-window radoloWindow']/div[2]/div/div[2]/descendant::button[text()='OK']")).click();
	    	 driver.findElement(By.xpath("//button[text()='OK']")).click();
	     }
	     catch(Exception e)
	     { 
	    	 e.printStackTrace();
	     }
	           */
			}
		}
	

	@DataProvider(name = "dp")
	public static Object[][] getdata(Method m) throws InterruptedException,
			InvalidFormatException {
		String sheetname = m.getName();
		int rowno = excel.getRowCount(sheetname);
		int colno = excel.getColumnCount(sheetname);
		Object[][] data = new Object[rowno - 1][colno];
		for (row = 2; row <= rowno; row++) {
			for (int col = 0; col < colno; col++) {
				System.out.println(rowno + "," + colno);
				data[row - 2][col] = excel.getCellData(sheetname, col, row);
				System.out.println(data[row - 2][col]);

			}

		}
		return data;

	}

	@AfterSuite
	public void close() {
		// if(driver!=null)
		// driver.quit();
	}

}
