package com.securitax.userclass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class initialReg extends Securitaxclass{

	public String Title;
	public String Email;
	public String FirstName;
	public String Surname;
	public String locationlist;
	public String Address;
	public String AddressLine2;
	public String LandlineNumber;
	public String Town;
	public String MobileNumber;
	public String County;
	public String DateofBirth;
	public String Country;	
	public String NationalInsuranceNumber;
	public String Postcode;
	public String DrivingLicense;
	public String Customer;
	public String PreferableLanguage;
	public String RTW;
	public String ExtDriverID;
	public boolean Registrationstatus;
	int i = 0;

	public boolean initialRegistration(WebDriver driver) {
		try {
			ExcelLib xllib1 = new ExcelLib();
			int RowCount1 = xllib1.getRowCount("InitialRegistration");
			System.out.println("RowCount1:" + RowCount1);
			System.out.println("RowCount1 log initialized ");
			for (i = 1; i <= RowCount1; i++) {
				System.out.println("Inside for loop");
				Title = xllib1.getExcelData("InitialRegistration", i, 0);
				Email = xllib1.getExcelData("InitialRegistration", i, 1);
				FirstName = xllib1.getExcelData("InitialRegistration", i, 2);
				Surname = xllib1.getExcelData("InitialRegistration", i, 3);
				locationlist = xllib1.getExcelData("InitialRegistration", i, 4);
				Address = xllib1.getExcelData("InitialRegistration", i, 5);
				AddressLine2 = xllib1.getExcelData("InitialRegistration", i, 6);
				LandlineNumber = xllib1.getExcelData("InitialRegistration", i, 7);
				Town = xllib1.getExcelData("InitialRegistration", i, 8);
				MobileNumber = xllib1.getExcelData("InitialRegistration", i, 9);
				County = xllib1.getExcelData("InitialRegistration", i, 10);
				DateofBirth = xllib1.getExcelData("InitialRegistration", i, 11);
				Country = xllib1.getExcelData("InitialRegistration", i, 12);
				NationalInsuranceNumber = xllib1.getExcelData("InitialRegistration", i, 13);
				Postcode = xllib1.getExcelData("InitialRegistration", i, 14);
				DrivingLicense = xllib1.getExcelData("InitialRegistration", i, 15);
				Customer = xllib1.getExcelData("InitialRegistration", i, 16);
				PreferableLanguage = xllib1.getExcelData("InitialRegistration", i, 17);
				RTW = xllib1.getExcelData("InitialRegistration", i, 18);
				ExtDriverID = xllib1.getExcelData("InitialRegistration", i, 19);

				Registrationstatus = registration(driver, Title, Email, FirstName, Surname, locationlist,Address,
						AddressLine2, LandlineNumber, Town, MobileNumber, County, DateofBirth, Country, NationalInsuranceNumber,
						Postcode, DrivingLicense, Customer, PreferableLanguage, RTW, ExtDriverID);
				System.out.println("InitialRegistration Count:" + i);

			}
		} catch (Exception e) {
			System.out.println("Registration Unsucessful");
			e.printStackTrace();
		}
		return Registrationstatus;
	}

	private boolean registration(WebDriver driver, String Title, String Email, String FirstName,String Surname,String locationlist, 
			 String Address, String AddressLine2, String LandlineNumber, String Town,
			String MobileNumber, String County, String DateofBirth,String Country, String NationalInsuranceNumber, String Postcode,
			String DrivingLicense,String Customer,String PreferableLanguage, String RTW, String ExtDriverID) throws InterruptedException {

		System.out.println("Click on Registration Tab");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()=\"Driver Registration\"]")).click();
		Thread.sleep(15000);

		System.out.println("switching to frame");
		WebElement we1 = driver.findElement(By.xpath("//iframe[@title=\"Visualforce Page component container\"]"));
		driver.switchTo().frame(we1);
		
		System.out.println("frame switched");
		System.out.println("Click on Title Field");
		Thread.sleep(6000);
		driver.findElement(By.xpath("(//select[@class='slds-select'])[1]")).click();
		//click("(//select[@class='slds-select'])[1]");
		driver.findElement(By.xpath("(//select[@class='slds-select'])[1]")).sendKeys(Title);
		Thread.sleep(5000);
		System.out.println("Click on Email Field");
		driver.findElement(By.xpath("(//input[@class='slds-input'])[1]")).click();
		driver.findElement(By.xpath("(//input[@class='slds-input'])[1]")).sendKeys(Email);
		Thread.sleep(5000);
		System.out.println("Click on FirstName Field");
		driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).click();
		driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).sendKeys(FirstName);
		Thread.sleep(8000);
		System.out.println("Click on Surname Field");
		driver.findElement(By.xpath("//input[@id='DSPRegisteration:theForm:j_id39']")).click();
		driver.findElement(By.xpath("//input[@id='DSPRegisteration:theForm:j_id39']")).sendKeys(Surname);
		Thread.sleep(5000);
		System.out.println("Click on Location Field");
		driver.findElement(By.xpath("//table/descendant::input")).click();
		driver.findElement(By.xpath("//table/descendant::input")).sendKeys(locationlist);
		Thread.sleep(5000);
		System.out.println("Click on Address Field");
		driver.findElement(By.xpath("(//input[@class='slds-input'])[5]")).click();
		driver.findElement(By.xpath("(//input[@class='slds-input'])[5]")).sendKeys(Address);
		Thread.sleep(5000);
		System.out.println("Click on Address Line 2 Field");
		driver.findElement(By.xpath("(//input[@class='slds-input'])[6]")).click();
		driver.findElement(By.xpath("(//input[@class='slds-input'])[6]")).sendKeys(AddressLine2);
		Thread.sleep(5000);
		System.out.println("Click on LandlineNumber Field");
		driver.findElement(By.xpath("(//input[@class='slds-input'])[7]")).click();
		driver.findElement(By.xpath("(//input[@class='slds-input'])[7]")).sendKeys(LandlineNumber);
		Thread.sleep(5000);
		System.out.println("Click on Town Field");
		driver.findElement(By.xpath("(//input[@class='slds-input'])[8]")).click();
		driver.findElement(By.xpath("(//input[@class='slds-input'])[8]")).sendKeys(Town);
		Thread.sleep(5000);
		System.out.println("Click on MobileNumber Field");
		driver.findElement(By.xpath("(//input[@class='slds-input'])[9]")).click();
		driver.findElement(By.xpath("(//input[@class='slds-input'])[9]")).sendKeys(MobileNumber);
		Thread.sleep(5000);
		System.out.println("Click on County Field");
		driver.findElement(By.xpath("(//input[@class='slds-input'])[10]")).click();
		driver.findElement(By.xpath("(//input[@class='slds-input'])[10]")).sendKeys(County);
		Thread.sleep(5000);
		System.out.println("Click on Date of Birth Field");				
		Thread.sleep(5000);		
		WebElement dateBox = driver.findElement(By.xpath("//input[@id='DSPRegisteration:theForm:date']"));
		dateBox.sendKeys(DateofBirth);
		Thread.sleep(5000);
		System.out.println("Click on Country Field");
		driver.findElement(By.xpath("//select[@id='DSPRegisteration:theForm:Months']")).click();
		driver.findElement(By.xpath("//select[@id='DSPRegisteration:theForm:Months']")).sendKeys(Country);
		Thread.sleep(5000);
		System.out.println("Click on National insurance number Field");
		driver.findElement(By.xpath("(//input[@class='slds-input'])[12]")).click();
		driver.findElement(By.xpath("(//input[@class='slds-input'])[12]")).sendKeys(NationalInsuranceNumber);
		Thread.sleep(5000);
		System.out.println("Click on Postcode Field");
		driver.findElement(By.xpath("(//input[@class='slds-input'])[13]")).click();
		driver.findElement(By.xpath("(//input[@class='slds-input'])[13]")).sendKeys(Postcode);
		Thread.sleep(5000);
		System.out.println("Click on DrivingLicense Field");
		driver.findElement(By.xpath("(//input[@class='slds-input'])[14]")).click();
		driver.findElement(By.xpath("(//input[@class='slds-input'])[14]")).sendKeys(DrivingLicense);
		Thread.sleep(5000);
		System.out.println("Click on Customer Field");
		driver.findElement(By.xpath("//select[@id='DSPRegisteration:theForm:Months1']")).click();
		driver.findElement(By.xpath("//select[@id='DSPRegisteration:theForm:Months1']")).sendKeys(Customer);
		Thread.sleep(5000);
		System.out.println("Click on PreferableLanguage Field");
		driver.findElement(By.xpath("//select[@id='DSPRegisteration:theForm:Months2']")).click();
		driver.findElement(By.xpath("//select[@id='DSPRegisteration:theForm:Months2']")).sendKeys(PreferableLanguage);
		Thread.sleep(5000);
		System.out.println("Click on RTW Field");
		driver.findElement(By.xpath("(//input[@class='slds-input'])[15]")).click();
		driver.findElement(By.xpath("(//input[@class='slds-input'])[15]")).sendKeys(RTW);
		Thread.sleep(5000);
		System.out.println("Click on Ext Driver ID Field");
		driver.findElement(By.xpath("(//input[@class='slds-input'])[16]")).click();
		driver.findElement(By.xpath("(//input[@class='slds-input'])[16]")).sendKeys(ExtDriverID);
		Thread.sleep(5000);
		System.out.println("Click on Login Button");
		driver.findElement(By.xpath("(//input[@class='slds-button slds-button_brand'])[1]")).click();
		Thread.sleep(5000);
		System.out.println("switching to default page");
		driver.switchTo().defaultContent();
		Thread.sleep(6000);
		
		return false;
	}
	
	public boolean selfRegistration(WebDriver driver) throws InterruptedException{
		System.out.println("clicking on Registered Tab");
		Thread.sleep(6000);
		driver.findElement(By.xpath("//a[@title='Registered Drivers']")).click();
		Thread.sleep(5000);
		System.out.println("Listing Records");
		List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
		int Size = rows.size();
		System.out.println(Size);
		Thread.sleep(8000);
		System.out.println("selecting recent created record dropdown");
		driver.findElement(By.xpath("(//tbody/tr)[" + Size + "]/descendant::a[@aria-haspopup=\"true\"]")).click();
		Thread.sleep(8000);
		System.out.println("Clicking on Edit Option");
		driver.findElement(By.xpath("//a[@title='Edit']")).click();
		Thread.sleep(8000);
		System.out.println("Changing the status");
		driver.findElement(By.xpath("(//a[text()=\"Drivers Registered\"])[1]")).click();
		Thread.sleep(3000);
		String Statusbar = "Induction passed";
		List<WebElement> Status = driver.findElements(By.xpath("//div[@class=\"select-options\"]/descendant::a"));

		System.out.println(Status.size());

		for (WebElement we : Status) {

			if (we.getText().equals(Statusbar)) {
				Thread.sleep(3000);
				we.click();
			}
		}
		Thread.sleep(10000);
		//Clicking save button 
		driver.findElement(By.xpath("html/body/div[8]/div/div[2]/div/div[3]/div/button[3]")).click();
	
		return false;
	}
}
