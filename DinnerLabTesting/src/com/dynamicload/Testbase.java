
package com.dynamicload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import com.src.test.java.com.utilities.ExcelReading;


public class Testbase {

	public static WebDriver driver;
	public static int row;
	public static FileInputStream fis;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static Logger log = Logger.getLogger(Testbase.class);
	//public static ExcelReading excel = new ExcelReading("D:\\AutomationDashboard\\SigmaAutomation\\src\\com\\src\\test\\resources\\Excel\\Testdata.xlsx"); 
	public static ExcelReading excel = new ExcelReading("D:\\AutomationDashboard\\DinnerLabTesting\\src\\com\\src\\test\\resources\\Excel\\Testdata.xlsx");
			
			
			
	@BeforeSuite
	public static void setup() throws IOException, InterruptedException  {
		
		System.out.println(System.getProperty("user.dir"));
		if (driver == null) {
			
				fis = new FileInputStream(
						"D:\\AutomationDashboard\\DinnerLabTesting\\src\\com\\dynamicload\\Config");
		 
			config.load(fis);
           log.debug("loading config file");
			
			
			
				fis = new FileInputStream("D:\\AutomationDashboard\\DinnerLabTesting\\src\\com\\dynamicload\\OR");
			
			OR.load(fis);
			log.debug("loading or file");
		
			if(config.getProperty("Browser").equals("chrome")) {
            Thread.sleep(8000);

               
			ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				
			
			System.setProperty("webdriver.chrome.driver",
				"D:\\AutomationDashboard\\DinnerLabTesting\\src\\com\\src\\test\\resources\\Drivers\\chromedriver.exe");
				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS);
				

			}
			else if(config.getProperty("Browser").equals("firfox")) {

				System.setProperty("webdriver.gecko.driver",
						"D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\com\\src\\test\\resources\\Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS);
				log.info("opens browser");
				log.debug("opens browser");
			}
			driver.navigate().to(config.getProperty("Testurl"));
			driver.findElement(By.xpath(OR.getProperty("Username"))).sendKeys(config.getProperty("UN"));

			driver.findElement(By.xpath(OR.getProperty("Password"))).sendKeys(config.getProperty("PWD"));

			driver.findElement(By.xpath(OR.getProperty("Login"))).submit();
			 log.debug("login successfull");
		}
	}

	public static void Click(String Locator) {

		driver.findElement(By.xpath(Locator)).click();

	}

	public static void sendkeys(String Locator, String name) {

		driver.findElement(By.xpath(Locator)).sendKeys(name);

	}

	public static void select(String list, String TypeofAccount) {
		Select sel = new Select(driver.findElement(By.xpath(list)));
		sel.selectByVisibleText(TypeofAccount);
	}

	public static void click(String TD) throws InterruptedException {
		List<WebElement> list = driver.findElements(By.xpath("//ul[@class=\"slds-lookup__list\"]/descendant::a"));
		for (WebElement we : list) {
			if (we.getText().contentEquals(TD)) {
				Thread.sleep(8000l);

				we.click();

			}
		}
	}

	
	public static void Headersearch(String TD) throws InterruptedException {
		List<WebElement> list = driver.findElements(By.xpath("//div[@class=\"listContent\"]/descendant::span"));
		for (WebElement we : list) {
		if (we.getText().contentEquals(TD)) {
		Thread.sleep(8000l);

		we.click();


		}
		}}
	
	
	@DataProvider(name = "dp")
	public static Object[][] getdata(Method m) {
		String sheetname = m.getName();
	int	 rowno = excel.getRowCount(sheetname);
		int colno = excel.getColumnCount(sheetname);
		Object[][] data = new Object[rowno - 1][colno];
		for ( row = 2; row <= rowno; row++) {
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
		//if(driver!=null)
		//driver.quit();
	}

}
