package com.securitax.userclass;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginandSelfReg {
	WebDriver driver;
    
	
	public static boolean gMaillogin(WebDriver driver)
	{
		String Url;
		String Username;
		String Password;
		boolean loginStatus;
		try{
			
			ExcelLib Ex = new ExcelLib();
			int loginRowCount = Ex.getRowCount("Gmail");
            System.out.println("loginRowCount:"+loginRowCount);
            System.out.println("loginRowCount log initialized ");
            
            for(int i=1; i<=loginRowCount; i++){
            	
            	Url=Ex.getExcelData("Gmail", i, 0);
            	Username=Ex.getExcelData("Gmail", i, 1);
            	Password=Ex.getExcelData("Gmail", i, 2);

            	loginStatus=logintogmail(driver,Url,Username,Password);

            }
            
		}
		catch(Exception e)
		{
			System.out.println("Login Failed");
			e.printStackTrace();
		}
		return false;
		
	}

	private static boolean logintogmail(WebDriver driver, String Url, String Username, String Password) throws InterruptedException {
		
		
		//Navligate to gmail URL		
		driver.get(Url);
		Thread.sleep(5000);

		// gmail login
		driver.findElement(By.xpath("//input[@autocomplete=\"username\"]")).sendKeys(Username);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@autocomplete=\"current-password\"]")).sendKeys(Password);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		return false;
	}
	
	public static boolean selfRegistration (WebDriver driver){

		String Name;
		String ServiceType;
		String BankName;
		String Nameofaccountholders;
		String Accountnumber;
		String Sortcode;
		String BicNumber;
		String BankCode;
		String NationalBankCode;
		String IBANChecksum;
		String VATNumber;
		String CompantyName;
		String SubSearch;
		String UTR;

		boolean selfRegistrtionStatus;
		
		try{

			
			ExcelLib Ex1 = new ExcelLib();
			int RowCount = Ex1.getRowCount("Bank");
            System.out.println("RowCount:"+RowCount);
            System.out.println("RowCount log initialized ");
            
            for(int i=1;i<=RowCount;i++){
            	Name=Ex1.getExcelData("Bank", i, 0);
            	ServiceType=Ex1.getExcelData("Bank", i, 1);
            	BankName=Ex1.getExcelData("Bank", i, 2);
            	Nameofaccountholders=Ex1.getExcelData("Bank", i, 3);
            	Accountnumber=Ex1.getExcelData("Bank", i, 4);
            	Sortcode=Ex1.getExcelData("Bank", i, 5);
            	BicNumber=Ex1.getExcelData("Bank", i, 6);
            	BankCode=Ex1.getExcelData("Bank", i, 7);
            	NationalBankCode=Ex1.getExcelData("Bank", i, 8);
            	IBANChecksum=Ex1.getExcelData("Bank", i, 9);
            	VATNumber=Ex1.getExcelData("Bank", i, 10);
            	CompantyName=Ex1.getExcelData("Bank", i, 11);
            	SubSearch=Ex1.getExcelData("Bank", i, 12);
            	UTR=Ex1.getExcelData("Bank", i, 13);
            	
            	selfRegistrtionStatus= driverSelfreg(driver,Name,ServiceType,BankName,Nameofaccountholders,Accountnumber,Sortcode,
            			BicNumber,BankCode,NationalBankCode,IBANChecksum,VATNumber,CompantyName,SubSearch,UTR);
            	
         	
            }
			
			
		}catch(Exception e){
			System.out.println("Self Registration Failed");
		}
		return false;
		
	}

	private static boolean driverSelfreg(WebDriver driver, String Name, String ServiceType,String BankName, String Nameofaccountholders,
			String Accountnumber, String Sortcode, String BicNumber, String BankCode, String NationalBankCode,
			String IBANChecksum,String VATNumber,String CompantyName,String SubSearch, String UTR) throws InterruptedException 
	{
		System.out.println("Email Subject is :"+SubSearch);
		Thread.sleep(8000);
		
		
		// now talking un-read email form inbox into a list
		List<WebElement> unreademeil = driver.findElements(By.xpath("//*[@class='zF']/following::b"));
		
		Thread.sleep(8000);
		for(WebElement sst : unreademeil){
			String str=sst.getText();
			System.out.println("List of Emails "+str);
		}
		// Mailer name for which i want to check do i have an email in my inbox 
		String MyMailer = SubSearch;
		System.out.println(" MyMailer:"+MyMailer);
		// real logic starts here
		for(int i=0; i<unreademeil.size(); i++){
		    if(unreademeil.get(i).isDisplayed()==true){
		        // now verify if you have got mail form a specific mailer (Note Un-read mails)
		        // for read mails xpath loactor will change but logic will remain same
		        if(unreademeil.get(i).getText().equals(MyMailer)){
		            System.out.println("Yes we have got mail form " + MyMailer);
		            {
		            // also you can perform more actions here
		            	Thread.sleep(8000);
		            	unreademeil.get(i).click();
		            }
		            // like if you want to open email form the mailer
		            break;
		        }else{
		        	Thread.sleep(8000);
		        	driver.findElement(By.xpath("//div/span[contains(text(),'Sandbox: Your new Securitax Registration URL')]")).click();
		        	
		            System.out.println("No mail form " + MyMailer);
		        }
		    }
		}
		Thread.sleep(8000);
		driver.findElement(By.xpath("//a[contains(text(),'https://sagedev-stcommunity')]")).click();
		
	/*	driver.findElement(By.xpath("//div/span[contains(text(),'"+SubSearch+"')]")).click();
		Thread.sleep(20000);
		//System.out.println(driver.findElement(By.xpath("//a[@rel='noreferrer']")).getAttribute("target"));
		driver.findElement(By.xpath("//a[contains(text(),'https://sagedev-stcommunity')]")).click();
*/
		Thread.sleep(6000);
		Set<String> tab =	driver.getWindowHandles();
		Iterator <String> window =tab.iterator();



		String tab1 = window.next();
		String tab2 = window.next();
		//System.out.println(window +" ggh"+tab1+"fghvgh"+tab2);

		driver.switchTo().window(tab2);

		Thread.sleep(6000);
		driver.switchTo().frame(0);
		Thread.sleep(6000);
		driver.findElement(By.xpath("//button[@title='Play']")).click();
		driver.switchTo().defaultContent();

		Thread.sleep(13000);
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();

/*		String serviceType1 ="  I want to be a sole trader and use securitax and use flat rate VAT";
		String serviceType2 ="I want to be a limited company and use securitax";
		String serviceType3 ="I am not sure and would like someone to contact me";*/

		//Selecting the Service Type
		System.out.println("Selected Service Type is :"+ServiceType);
		Thread.sleep(13000);
		System.out.println(driver.findElement(By.xpath("(//td[contains(text(), '"+ServiceType+"')]/following::td/input)[1]")).getAttribute("type"));
		driver.findElement(By.xpath("(//td[contains(text(), '"+ServiceType+"')]/following::td/input)[1]")).click();
		
		
		System.out.println("identified the service type");
		Thread.sleep(8000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='Proceed']")).click();
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='Next']")).click();

		//DSP Contratct Page 
		
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@class='error']")).click();		
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='slds-input'])[6]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='slds-input'])[6]")).sendKeys(Name);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@value='NEXT']")).click();

		// Securitax Contract Page
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@class='error']")).click();
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,850)");
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@value='val1'])[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@class='slds-input'])[15]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@class='slds-input'])[15]")).sendKeys(CompantyName);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@class='slds-input'])[16]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@class='slds-input'])[16]")).sendKeys(VATNumber);
		Thread.sleep(3000);
		Select sub = new Select(driver.findElement(By.xpath("(//select[@class=\"slds-select\"])[4]")));
		sub.selectByVisibleText("Registered");	
		sub.selectByIndex(2);

		//Filling the Bank Account Details 
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@class='slds-input'])[17]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@class='slds-input'])[17]")).sendKeys(BankName);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@class='slds-input'])[18]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@class='slds-input'])[18]")).sendKeys(Nameofaccountholders);
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//label[text()='8 Digit Bank account number']/following::input)[1]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//label[text()='8 Digit Bank account number']/following::input)[1]")).sendKeys(Accountnumber);
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//label[text()='6 Digit Sort code']/following::input)[1]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//label[text()='6 Digit Sort code']/following::input)[1]")).sendKeys(Sortcode);
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//label[text()='Bic Number']/following::input)[1]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//label[text()='Bic Number']/following::input)[1]")).sendKeys(BicNumber);
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//label[text()='Bank Code']/following::input)[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//label[text()='Bank Code']/following::input)[1]")).sendKeys(BankCode);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//label[text()='National Bank Code']/following::input)[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//label[text()='National Bank Code']/following::input)[1]")).sendKeys(NationalBankCode);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//label[text()='IBAN Checksum']/following::input)[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//label[text()='IBAN Checksum']/following::input)[1]")).sendKeys(IBANChecksum);
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,1000)");
		// Checking Agreement Check Box
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='error'])[2]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='error'])[3]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='error'])[4]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='error'])[5]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='error'])[6]")).click();

		//Entering Next Page 
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='NEXT']")).click();

		// Registration Page or HMRC Contract

		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='mainpage:theForm:utrid']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//label[text()='Unique Taxpayer Reference (UTR) If applicable']/following::input)[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//label[text()='Unique Taxpayer Reference (UTR) If applicable']/following::input)[1]")).sendKeys(UTR);
		Thread.sleep(3000);
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(3000);
		//driver.findElement(By.xpath("//input[@value='REGISTER']")).click();
		return false;
	}
	
	

}
