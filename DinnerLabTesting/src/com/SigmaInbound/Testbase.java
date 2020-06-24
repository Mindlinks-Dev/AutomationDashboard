 /**
 * @author: Meghana .J
 * @Created Date :02/03/2018
 * @Updated Date :
 * @Comments:This automation class  is the base class where we send excel, given path of chrome driver and excel sheet
*/
package com.SigmaInbound;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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



public class Testbase {

	public static WebDriver driver;
	public static int row;
	public static FileInputStream fis;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static Logger log = Logger.getLogger(Testbase.class);
	//public static ExcelReading excel = new ExcelReading("C:\\Users\\Chaithra\\workspace\\Project-2\\ExcelData\\Testdata.xlsx");
	//public static ExcelReading excel = new ExcelReading("D:/DinnerLabWorkSpace/DinnerLabTesting/src/Data.xls");
	//public static ExcelReading excel = new ExcelReading("D:\\AutomationDashboard\\DinnerLabTesting\\src\\com\\SigmaInbound\\SigmaTestData.xlsx");
	public static ExcelReading excel = new ExcelReading("D:\\AutomationDashboard\\DinnerLabTesting\\src\\com\\SigmaInbound\\ManufacturingVerticalTC.xlsx");
			
			
	@BeforeSuite
	public static void setup() throws IOException, InterruptedException  {
		
		System.out.println(System.getProperty("user.dir"));
		if (driver == null) {
			
			fis = new FileInputStream(
						"D:\\AutomationDashboard\\DinnerLabTesting\\src\\com\\SigmaInbound\\Config");
		 
			config.load(fis);
           log.debug("loading config file");
			
			
			
				fis = new FileInputStream("D:\\AutomationDashboard\\DinnerLabTesting\\src\\com\\SigmaInbound\\OR");
			
			OR.load(fis);
			log.debug("loading or file");
		
			if(config.getProperty("Browser").equals("chrome")) {

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");

				System.setProperty("webdriver.chrome.driver",
					"D:\\AutomationDashboard\\DinnerLabTesting\\src\\chromedriver.exe");
					driver = new ChromeDriver(options);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				

			}
			else if(config.getProperty("Browser").equals("firefox")) {

				System.setProperty("webdriver.gecko.driver",
						"E:\\Softwares\\Drivers\\geckodriver-v0.26.0-win64\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS);
				log.info("opens browser");
				log.debug("opens browser");
			}
			driver.navigate().to(config.getProperty("URL1"));

			//Enter username text field
			//driver.findElement(By.id("username")).sendKeys("sigmamvp1.0@mindlinks.com");
			driver.findElement(By.id("username")).sendKeys("sigma_manufacturingverticle_pe@mindlinks.com");
			//driver.findElement(By.id("username")).sendKeys("sigma_functionaltesting@mindlinks.com");
			//driver.findElement(By.id("username")).sendKeys("sigma_new_qa3@mindlinkssolution.com");

			Thread.sleep(3000);
			//driver.findElement(By.id("password")).sendKeys("sigmadev123");
			driver.findElement(By.id("password")).sendKeys("password-1");
			Thread.sleep(3000);
			driver.findElement(By.id("Login")).click();
			
			/*//Enter username text field
			driver.findElement(By.id("username")).sendKeys(config.getProperty("userName"));

			WebElement ws= driver.findElement(By.id("userName"));
			//Enter password text field and click login button 
			driver.findElement(By.id("password")).sendKeys(config.getProperty("Pword"));
		    ws.sendKeys(Keys.TAB , config.getProperty("Pword"),Keys.TAB.ENTER );    
			Thread.sleep(8000);*/
			
			//------Added by shridhar to switch from classic to lightning----- 
			try
		    {
		    Thread.sleep(5000);
		    //driver.findElement(By.xpath("//nav[@class='appLauncher slds-context-bar__icon-action']//button[1]")).isDisplayed(); //lightning element
		    {
		    Thread.sleep(8000);
		    System.out.println("Switching Lightntning Page Button");
		    }
		    }
		    catch(Exception e)
		    {
		    e.printStackTrace();
		    //driver.findElement(By.xpath("//div/a[text()='Switch to Lightning Experience']")).isDisplayed(); //classic element
		      {    
		    Thread.sleep(8000);
		    System.out.println("before click");
		    driver.findElement(By.xpath("(//div/a[text()='Switch to Lightning Experience'])[1]")).click();  
		    System.out.println("clicked");
		    Thread.sleep(8000);
		   
		    }
		    }
			
			//------Ends here------
			
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
