package Securitax;

import java.lang.reflect.Method;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class initialregistration {
	
	WebDriver driver;
	
static ExcelReading excel = new ExcelReading("D:\\DinnerLabWorkspace - Test\\DinnerLabTesting\\src\\Securitax\\TestData.xlsx");
@Test(dataProviderClass= initialregistration.class, dataProvider ="dp", priority = 1)

public void Login(String URL,String USERNAME,String PASSWORD) throws InterruptedException {
	
	System.setProperty("webdriver.chrome.driver", "E:\\Shridhar Workspace\\Securitax\\Drivers\\chromedriver.exe");
	driver = new ChromeDriver();

	driver.manage().window().maximize();

	driver.get(URL);
	Thread.sleep(2000);
	driver.findElement(By.xpath("//input[@id='username']")).click();
	System.out.println("User Name is :"+USERNAME);
	driver.findElement(By.xpath("//input[@id='username']")).sendKeys(USERNAME);
	Thread.sleep(2000);
	driver.findElement(By.xpath("//input[@id='password']")).click();
	System.out.println("Password is :"+PASSWORD);
	driver.findElement(By.xpath("//input[@id='password']")).sendKeys(PASSWORD);
	Thread.sleep(2000);
	driver.findElement(By.xpath("//input[@id='Login']")).click();
	System.out.println("Login Success");
	Thread.sleep(8000);
	
}
@Test(dataProviderClass= initialregistration.class, dataProvider ="dp", priority=2)
public void InitialReg(String Title,String Email,String FirstName,String Surname,String locationlist,
		String Address,String AddressLine2,String LandlineNumber,String Town,String MobileNumber,
		String County,String DateofBirth,String Country,String NationalInsuranceNumber,String Postcode,
		String DrivingLicense,String Customer,String PreferableLanguage,String RTW,String ExtDriverID,String CompanyName) throws InterruptedException{
	
	Thread.sleep(2000);
	System.out.println("Driver Register Tab");
	driver.findElement(By.xpath("//a[@title='Driver Registration']")).click();
	System.out.println("Driver Registred Tab clicked");
	Thread.sleep(2000);
	
		
	System.out.println("switching to frame");
	WebElement we = driver.findElement(By.xpath("//iframe[@title=\"Visualforce Page component container\"]"));
	driver.switchTo().frame(we);
	System.out.println("frame switched");
	
	driver.findElement(By.xpath("//span[@id='DSPRegisteration:theForm:j_id5']")).click();
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("(//select[@class='slds-select'])[1]")).click();
	driver.findElement(By.xpath("(//select[@class='slds-select'])[1]")).sendKeys(Title);
	System.out.println("Title :"+Title);
	Thread.sleep(3000);
				
	driver.findElement(By.xpath("//label[text()='Email Address']/following-sibling::input")).click();
	driver.findElement(By.xpath("//label[text()='Email Address']/following-sibling::input")).sendKeys(Email);
	System.out.println("Email Id is :"+Email);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//label[text()='First Name']/following-sibling::div/input")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//label[text()='First Name']/following-sibling::div/input")).sendKeys(FirstName);
	System.out.println("First Name :"+FirstName);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//label[text()='Surname']/following-sibling::div/input")).click();
	driver.findElement(By.xpath("//label[text()='Surname']/following-sibling::div/input")).sendKeys(Surname);
	System.out.println("Surname Name :"+Surname);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//table/descendant::input")).click();
	driver.findElement(By.xpath("//table/descendant::input")).sendKeys(locationlist);
	System.out.println("locationlist :"+locationlist);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("(//input[@class='slds-input'])[5]")).click();
	driver.findElement(By.xpath("(//input[@class='slds-input'])[5]")).sendKeys(Address);
	System.out.println("Address :"+Address);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("(//input[@class='slds-input'])[6]")).click();
	driver.findElement(By.xpath("(//input[@class='slds-input'])[6]")).sendKeys(AddressLine2);
	System.out.println("AddressLine2 :"+AddressLine2);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//span[@id='DSPRegisteration:theForm:j_id5']")).click();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Thread.sleep(2000);
	js.executeScript("window.scrollBy(0,70)");
	Thread.sleep(5000);
	
	driver.findElement(By.xpath("(//input[@class='slds-input'])[7]")).click();
	driver.findElement(By.xpath("(//input[@class='slds-input'])[7]")).sendKeys(LandlineNumber);
	System.out.println("LandlineNumber :"+LandlineNumber);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("(//input[@class='slds-input'])[8]")).click();
	driver.findElement(By.xpath("(//input[@class='slds-input'])[8]")).sendKeys(Town);
	System.out.println("Town :"+Town);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("(//input[@class='slds-input'])[9]")).click();
	driver.findElement(By.xpath("(//input[@class='slds-input'])[9]")).sendKeys(MobileNumber);
	System.out.println("MobileNumber :"+MobileNumber);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("(//input[@class='slds-input'])[10]")).click();
	driver.findElement(By.xpath("(//input[@class='slds-input'])[10]")).sendKeys(County);
	System.out.println("County :"+County);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("(//input[@class='slds-input'])[11]")).click();
	driver.findElement(By.xpath("(//input[@class='slds-input'])[11]")).sendKeys(DateofBirth);
	System.out.println("DateofBirth :"+DateofBirth);
	Thread.sleep(3000);
	
	Thread.sleep(2000);
	js.executeScript("window.scrollBy(0,70)");
	Thread.sleep(5000);
	
	driver.findElement(By.xpath("(//select[@class='slds-select'])[2]")).click();
	driver.findElement(By.xpath("(//select[@class='slds-select'])[2]")).sendKeys(Country);
	System.out.println("Country :"+Country);
	Thread.sleep(3000);	
		
	driver.findElement(By.xpath("(//input[@class='slds-input'])[12]")).click();
	driver.findElement(By.xpath("(//input[@class='slds-input'])[12]")).sendKeys(NationalInsuranceNumber);
	System.out.println("NationalInsuranceNumber :"+NationalInsuranceNumber);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("(//input[@class='slds-input'])[13]")).click();
	driver.findElement(By.xpath("(//input[@class='slds-input'])[13]")).sendKeys(Postcode);
	System.out.println("Postcode :"+Postcode);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("(//input[@class='slds-input'])[14]")).click();
	driver.findElement(By.xpath("(//input[@class='slds-input'])[14]")).sendKeys(DrivingLicense);
	System.out.println("DrivingLicense :"+DrivingLicense);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("(//select[@class='slds-select'])[3]")).click();
	driver.findElement(By.xpath("(//select[@class='slds-select'])[3]")).sendKeys(Customer);
	System.out.println("Customer :"+Customer);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("(//select[@class='slds-select'])[4]")).click();
	driver.findElement(By.xpath("(//select[@class='slds-select'])[4]")).sendKeys(PreferableLanguage);
	System.out.println("PreferableLanguage :"+PreferableLanguage);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("(//input[@class='slds-input'])[15]")).click();
	driver.findElement(By.xpath("(//input[@class='slds-input'])[15]")).sendKeys(RTW);
	System.out.println("RTW :"+RTW);
	Thread.sleep(3000);
	
	Thread.sleep(2000);
	js.executeScript("window.scrollBy(0,100)");
	Thread.sleep(5000);
	
	driver.findElement(By.xpath("(//input[@class='slds-input'])[16]")).click();
	driver.findElement(By.xpath("(//input[@class='slds-input'])[16]")).sendKeys(ExtDriverID);
	System.out.println("ExtDriverID :"+ExtDriverID);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("(//input[@class='slds-input'])[17]")).click();
	driver.findElement(By.xpath("(//input[@class='slds-input'])[17]")).sendKeys(CompanyName);
	System.out.println("CompanyName :"+CompanyName);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//input[@value='Initiate Registration']")).click();
	
	Alert alert = driver.switchTo().alert();
	
	alert.dismiss();
	
	
}
		@DataProvider(name = "dp")
		public static Object[][] getdata(Method m) {
		String sheetname = m.getName();
		int	rowno = excel.getRowCount(sheetname);
		int colno = excel.getColumnCount(sheetname);
		Object[][] data = new Object[rowno - 1][colno];
		for ( int row = 2; row <= rowno; row++) {
		for (int col = 0; col < colno; col++) {
		System.out.println(rowno + "," + colno);
		data[row - 2][col] = excel.getCellData(sheetname, col, row);
		System.out.println(data[row - 2][col]);
		
		}
		
		}
return data;

}
}

