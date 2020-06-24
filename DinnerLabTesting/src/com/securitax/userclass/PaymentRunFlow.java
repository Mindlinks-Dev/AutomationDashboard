package com.securitax.userclass;

import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentRunFlow {
	
	WebDriver driver;
	String URL;
	String USERNAME;
	String PASSWORD;
	//int RowCount;
	boolean LoginStatusSecuritax;
	int i=0;
	
	//DSP And Depot
	public String  DSP;
	public String Depot;
	public boolean getrecords;
	
	//Paymentrun data
	public String WeekNumber;
	public String Routescompleted;
	public String Hoursworked;
	public String HourlyRate;
	public String AdditionalRoutes;
	public String IncentivePay;
	public String Miles;
	public String RatePerMile;
	public String BYOD;
	public String OtherPay;
	public String VanrentalExVAT;
	public String VandamagesExVAT;
	public String CompanyFuelUsed;
	public String OtherDeductions;
	public String VanInsurance;
	public String VanFines;
	public String LoanRepayments;
	public String name;
	boolean loadPaymentRundata;

	
	public boolean Securitaxlogin(WebDriver driver) throws InterruptedException{
		try{
			ExcelLib Ex = new ExcelLib();
			int loginRowCount = Ex.getRowCount("SecuritaxLogin");
            System.out.println("loginRowCount:"+loginRowCount);
            System.out.println("loginRowCount log initialized ");
            
            for(int i=1; i<= loginRowCount; i++){
            	URL=Ex.getExcelData("SecuritaxLogin", i, 0);
            	USERNAME=Ex.getExcelData("SecuritaxLogin", i, 1);
            	PASSWORD=Ex.getExcelData("SecuritaxLogin", i, 2);
            	
            	LoginStatusSecuritax = CreateLogin(driver, URL, USERNAME, PASSWORD);
            	System.out.println("Login Count:"+i);
            	

            }
		}catch(Exception e){
			System.out.println("login failed ");
			e.printStackTrace();

		}
		
		

		return LoginStatusSecuritax;
	
	}

	public boolean CreateLogin(WebDriver driver,String URL, String USERNAME, String PASSWORD) throws InterruptedException {
		
		Thread.sleep(6000);
		driver.get(URL);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='username']")).click();
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(USERNAME);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='password']")).click();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(PASSWORD);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		Thread.sleep(8000);
		// SWITCHING page from classic to lightning or vice versa if logged into lightning/classic
		try
		{
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@title='Payment Run']")).isDisplayed(); //lightning element
			 { 
					Thread.sleep(8000);
					System.out.println("Switching Lightntning Page Button");
					//driver.findElement(By.xpath("//a[@title='Payment Run']")).click(); 
					System.out.println("clicked in else if");
			 }			 
		}	
		catch(Exception e)
		{
			e.printStackTrace();
			driver.findElement(By.xpath("(//a[text()='Switch to Lightning Experience'])[1]")).isDisplayed(); //classic element
			   {    
					Thread.sleep(8000);
					System.out.println("before click");
					driver.findElement(By.xpath("(//a[text()='Switch to Lightning Experience'])[1]")).click();   
					System.out.println("clicked"); 
					Thread.sleep(8000);
					//driver.findElement(By.xpath("//a[@title='Payment Run']")).click(); 
				}			
		}


	
		return false;
	}
	
	public boolean getPaymentrunRecords(WebDriver driver) throws InterruptedException{
		try
		{
			ExcelLib Ex1 = new ExcelLib();
			int RowCount = Ex1.getRowCount("DSPDepot");
			System.out.println("loginRowCount:"+RowCount);
			
			for (int i=1; i<=RowCount; i++){
		
				DSP=Ex1.getExcelData("DSPDepot", i, 0);
				Depot=Ex1.getExcelData("DSPDepot", i, 1);
				
				getrecords=paymentrunRecords(driver,DSP,Depot);
			}

		}
catch(Exception e){
	
	
}
		return LoginStatusSecuritax;
		
		
	}

	private boolean paymentrunRecords(WebDriver driver, String DSP, String Depot) throws InterruptedException {
		
		Thread.sleep(8000);
		System.out.println("clicked on App Builder"); 
		driver.findElement(By.xpath("//one-app-launcher-header[@class='slds-icon-waffle_container']")).click();
		Thread.sleep(8000);
		System.out.println("clicked on Securitax App"); 
		driver.findElement(By.xpath("//div[@title='Securitax']")).click();
		Thread.sleep(8000);
		System.out.println("clicked on PaymentRun Tab"); 
		driver.findElement(By.xpath("//a[@title='Payment Run']")).click();
		Thread.sleep(8000);
		System.out.println("clicked on DSP DropDown"); 
		driver.findElement(By.xpath("(//select[@class='slds-select'])[1]")).click();
		driver.findElement(By.xpath("(//select[@class='slds-select'])[1]")).sendKeys(DSP);
		Thread.sleep(8000);
		System.out.println("clicked on Depot DropDown");
		driver.findElement(By.xpath("(//select[@class='slds-select'])[2]")).click();
		driver.findElement(By.xpath("(//select[@class='slds-select'])[2]")).sendKeys(Depot);
		Thread.sleep(8000);
		driver.findElement(By.xpath("//button[@title='Get Drivers']")).click();
		
		
		return false;
	}
	public boolean enterPaymentdata(WebDriver driver){
		try{
			ExcelLib Ex2 = new ExcelLib();
			int datacount = Ex2.getRowCount("PaymentRun");
			System.out.println("Paymentrun Rows :"+datacount);
			
			for (i=1; i<=datacount; i++){
				WeekNumber=Ex2.getExcelData("PaymentRun", i, 0);
				Routescompleted=Ex2.getExcelData("PaymentRun", i, 1);
				Hoursworked=Ex2.getExcelData("PaymentRun", i, 2);
				HourlyRate=Ex2.getExcelData("PaymentRun", i, 3);
				AdditionalRoutes=Ex2.getExcelData("PaymentRun", i, 4);
				IncentivePay=Ex2.getExcelData("PaymentRun", i, 5);
				Miles=Ex2.getExcelData("PaymentRun", i, 6);
				RatePerMile=Ex2.getExcelData("PaymentRun", i, 7);
				BYOD=Ex2.getExcelData("PaymentRun", i, 8);
				OtherPay=Ex2.getExcelData("PaymentRun", i, 9);
				VanrentalExVAT=Ex2.getExcelData("PaymentRun", i, 10);
				VandamagesExVAT=Ex2.getExcelData("PaymentRun", i, 11);
				CompanyFuelUsed=Ex2.getExcelData("PaymentRun", i, 12);
				OtherDeductions=Ex2.getExcelData("PaymentRun", i, 13);
				VanInsurance=Ex2.getExcelData("PaymentRun", i, 14);
				VanFines=Ex2.getExcelData("PaymentRun", i, 15);
				LoanRepayments=Ex2.getExcelData("PaymentRun", i, 16);
				name=Ex2.getExcelData("PaymentRun", i, 17);
								
				System.out.println("WeekNumber::"+WeekNumber);
				System.out.println("Routescompleted::"+Routescompleted);
				System.out.println("Hoursworked::"+Hoursworked);
				System.out.println("HourlyRate::"+HourlyRate);
				System.out.println("AdditionalRoutes::"+AdditionalRoutes);
				System.out.println("IncentivePay::"+IncentivePay);
				System.out.println("Miles::"+Miles);
				System.out.println("RatePerMile::"+RatePerMile);
				System.out.println("BYOD::"+BYOD);
				System.out.println("OtherPay::"+OtherPay);
				System.out.println("VanrentalExVAT::"+VanrentalExVAT);
				System.out.println("VandamagesExVAT::"+VandamagesExVAT);
				System.out.println("CompanyFuelUsed::"+CompanyFuelUsed);
				System.out.println("OtherDeductions::"+OtherDeductions);
				System.out.println("VanInsurance::"+VanInsurance);
				System.out.println("VanFines::"+VanFines);
				System.out.println("LoanRepayments::"+LoanRepayments);
				System.out.println("name::"+name);
				
				loadPaymentRundata=loadData(driver,WeekNumber,Routescompleted,Hoursworked,HourlyRate,
						AdditionalRoutes,IncentivePay,Miles,RatePerMile,BYOD,OtherPay,VanrentalExVAT,
						VandamagesExVAT,CompanyFuelUsed,OtherDeductions,VanInsurance,VanFines,LoanRepayments,name);
			}
			
		}catch(Exception e){
			System.out.println("Invalid data");
		}
		return LoginStatusSecuritax;
	}

	private boolean loadData(WebDriver driver, String WeekNumber, String Routescompleted, String Hoursworked,
			String HourlyRate, String AdditionalRoutes, String IncentivePay, String Miles, String RatePerMile,
			String BYOD, String OtherPay, String VanrentalExVAT, String VandamagesExVAT, String CompanyFuelUsed,
			String OtherDeductions,String VanInsurance, String VanFines, String LoanRepayments, String name) throws InterruptedException {
		// TODO Auto-generated method stub
		//Thread.sleep(5000);
		//driver.findElement(By.xpath("(//a[@class='slds-tabs_scoped__link'])[1]")).click();
		
		
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//a[@class='slds-tabs_scoped__link'])[2]")).click();
		System.out.println("Clicked on Active Tab");
		Thread.sleep(10000);
		
		//select the driver checkbox
		System.out.println("name::"+ name);
		//WebDriverWait wait = new WebDriverWait(driver, 5000);
		WebElement select1=driver.findElement(By.xpath("(//table/descendant::tbody/tr/descendant::div[text()='"+name+"']/ancestor::tr/descendant::span)[1]"));
		System.out.println("Clicked on Neil :: "+select1);
		select1.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//button[@class='slds-button slds-button_neutral slds-button'])[2]")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("(//a[@class='slds-tabs_scoped__link'])[1]")).click();
		Thread.sleep(5000);
		
		//edit the selected driver
		Thread.sleep(5000);
		//WebDriverWait wait1 = new WebDriverWait(driver, 5000);
		WebElement select=driver.findElement(By.xpath("(//table/descendant::tbody/tr/descendant::div[text()='"+name+"']/ancestor::tr/descendant::button)"));
		System.out.println("Clicked on Neil :: "+select);
		select.click();
		Thread.sleep(8000);
		
         //Entering Paymentrun values 
		
		//Passing Week Number Value
		WebElement WeekNumber1 = (driver.findElement(By.xpath("(//input[@class='input uiInput uiInputNumber uiInput--default uiInput--input'])[1]")));		
		WeekNumber1.clear();
		Thread.sleep(5000);
		WeekNumber1.sendKeys(WeekNumber);		
		System.out.println("WeekNumber");
		
		//Passing Route Completed value
		//WebDriverWait wait11 = new WebDriverWait(driver, 8000);
		Thread.sleep(5000);
		WebElement Routescompleted1=driver.findElement(By.xpath("(//td[@data-label=\"Routes Completed\"])[2]/descendant::input"));
		Thread.sleep(8000);		
		Routescompleted1.clear();
		Thread.sleep(8000);
		Routescompleted1.sendKeys(Routescompleted);		
		System.out.println("routescompleted");
		
		
		//Passing Hours Worked Value
		//WebDriverWait wait12 = new WebDriverWait(driver, 8000);
		Thread.sleep(5000);
		WebElement Hoursworked1=driver.findElement(By.xpath("(//td[@data-label=\"Hours Worked\"])[2]/descendant::input"));
		Thread.sleep(8000);		
		Hoursworked1.clear();
		Thread.sleep(8000);
		Hoursworked1.sendKeys(Hoursworked);		
		System.out.println("hoursworked");
		Thread.sleep(5000);
		
		//Passing Hourly Rate value
		//WebDriverWait wait13 = new WebDriverWait(driver, 8000);
		Thread.sleep(5000);
		WebElement HourlyRate1=driver.findElement(By.xpath("(//td[@data-label=\"Confidence\"])[1]/descendant::input"));
		Thread.sleep(8000);		
		HourlyRate1.clear();
		Thread.sleep(8000);
		HourlyRate1.sendKeys(HourlyRate);		
		System.out.println("HourlyRate");
		Thread.sleep(5000);
		
		//Passing Additional Route value
		//WebDriverWait wait14 = new WebDriverWait(driver, 8000);
		Thread.sleep(5000);
		WebElement AdditionalRoutes1=driver.findElement(By.xpath("(//td[@data-label=\"Confidence\"])[2]/descendant::input"));
		Thread.sleep(8000);		
		AdditionalRoutes1.clear();
		Thread.sleep(8000);
		AdditionalRoutes1.sendKeys(AdditionalRoutes);		
		System.out.println("AdditionalRoutes");
		Thread.sleep(5000);
		
		//Passing the IncentivePay value 
		//WebDriverWait wait15 = new WebDriverWait(driver, 8000);
		Thread.sleep(5000);
		WebElement IncentivePay1=driver.findElement(By.xpath("(//td[@data-label=\"Amount\"])[3]/descendant::input"));
		Thread.sleep(8000);		
		IncentivePay1.clear();
		Thread.sleep(8000);
		IncentivePay1.sendKeys(IncentivePay);		
		System.out.println("IncentivePay");
		Thread.sleep(5000);
		
		//PAssing Miles value
		Thread.sleep(5000);
		//WebDriverWait wait16 = new WebDriverWait(driver, 8000);
		WebElement Miles1=driver.findElement(By.xpath("(//td[@data-label=\"Confidence\"])[3]/descendant::input"));
		Thread.sleep(8000);		
		Miles1.clear();
		Thread.sleep(8000);
		Miles1.sendKeys(Miles);		
		System.out.println("Miles");
		Thread.sleep(5000);
		
		//Passing Rate per Miles Value
		//WebDriverWait wait17 = new WebDriverWait(driver, 8000);
		Thread.sleep(5000);
		WebElement RatePerMile1=driver.findElement(By.xpath("(//td[@data-label=\"Confidence\"])[4]/descendant::input"));
		Thread.sleep(8000);		
		RatePerMile1.clear();
		Thread.sleep(8000);
		RatePerMile1.sendKeys(RatePerMile);		
		System.out.println("RatePerMile");
		Thread.sleep(5000);
		
		//Passing BYOD Value 
		//WebDriverWait wait18 = new WebDriverWait(driver, 8000);
		Thread.sleep(5000);
		WebElement BYOD1=driver.findElement(By.xpath("(//td[@data-label=\"Amount\"])[4]/descendant::input"));
		Thread.sleep(8000);		
		BYOD1.clear();
		Thread.sleep(8000);
		BYOD1.sendKeys(BYOD);		
		System.out.println("BYOD");
		Thread.sleep(5000);
		
		
		//Passing Others Pay Value 
		//WebDriverWait wait19 = new WebDriverWait(driver, 8000);
		Thread.sleep(5000);
		WebElement OtherPay1=driver.findElement(By.xpath("(//td[@data-label=\"Amount\"])[5]/descendant::input"));
		Thread.sleep(8000);		
		OtherPay1.clear();
		Thread.sleep(8000);
		OtherPay1.sendKeys(BYOD);		
		System.out.println("Others Pay");
		Thread.sleep(5000);
		
		//Passing VanrentalExVAT Value 
		//WebDriverWait wait20 = new WebDriverWait(driver, 8000);
		Thread.sleep(5000);
		WebElement VanrentalExVAT1=driver.findElement(By.xpath("(//td[@data-label=\"Amount\"])[6]/descendant::input"));
		Thread.sleep(8000);		
		VanrentalExVAT1.clear();
		Thread.sleep(8000);
		VanrentalExVAT1.sendKeys(VanrentalExVAT);		
		System.out.println("VanrentalExVAT");
		Thread.sleep(5000);
		
		//Passing VandamagesExVAT Value 
		//WebDriverWait wait21 = new WebDriverWait(driver, 8000);
		Thread.sleep(5000);
		WebElement VandamagesExVAT1=driver.findElement(By.xpath("(//td[@data-label=\"Amount\"])[7]/descendant::input"));
		Thread.sleep(8000);		
		VandamagesExVAT1.clear();
		Thread.sleep(8000);
		VandamagesExVAT1.sendKeys(VandamagesExVAT);		
		System.out.println("VandamagesExVAT");
		Thread.sleep(5000);
		
		//Passing CompanyFuelUsed Value 
		//WebDriverWait wait22 = new WebDriverWait(driver, 8000);
		Thread.sleep(5000);
		WebElement CompanyFuelUsed1=driver.findElement(By.xpath("(//td[@data-label=\"Amount\"])[8]/descendant::input"));
		Thread.sleep(8000);		
		CompanyFuelUsed1.clear();
		Thread.sleep(8000);
		CompanyFuelUsed1.sendKeys(CompanyFuelUsed);		
		System.out.println("CompanyFuelUsed");
		Thread.sleep(5000);
		
		//Passing OtherDeductions Value 
		//WebDriverWait wait23 = new WebDriverWait(driver, 8000);
		Thread.sleep(5000);
		WebElement OtherDeductions1=driver.findElement(By.xpath("(//td[@data-label=\"Amount\"])[9]/descendant::input"));
		Thread.sleep(8000);		
		OtherDeductions1.clear();
		Thread.sleep(8000);
		OtherDeductions1.sendKeys(OtherDeductions);		
		System.out.println("OtherDeductions");
		Thread.sleep(5000);
		
		//Passing VanInsurance Value 
		//WebDriverWait wait24 = new WebDriverWait(driver, 8000);
		Thread.sleep(5000);
		WebElement VanInsurance1=driver.findElement(By.xpath("(//td[@data-label=\"Amount\"])[11]/descendant::input"));
		Thread.sleep(8000);		
		VanInsurance1.clear();
		Thread.sleep(8000);
		VanInsurance1.sendKeys(VanInsurance);		
		System.out.println("VanInsurance");
		Thread.sleep(5000);
		
		//Passing VanFines Value 
		//WebDriverWait wait25 = new WebDriverWait(driver, 8000);
		Thread.sleep(5000);
		WebElement VanFines1=driver.findElement(By.xpath("(//td[@data-label=\"Amount\"])[13]/descendant::input"));
		Thread.sleep(8000);		
		VanFines1.clear();
		Thread.sleep(8000);
		VanFines1.sendKeys(VanFines);		
		System.out.println("VanFines");
		Thread.sleep(5000);
		
		//Passing LoanRepayments Value 
		//WebDriverWait wait26 = new WebDriverWait(driver, 8000);
		WebElement LoanRepayments1=driver.findElement(By.xpath("(//td[@data-label=\"Amount\"])[14]/descendant::input"));
		Thread.sleep(8000);		
		LoanRepayments1.clear();
		Thread.sleep(8000);
		LoanRepayments1.sendKeys(LoanRepayments);		
		System.out.println("LoanRepayments");
		Thread.sleep(5000);
		
		//Saving the record 
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//button[@class='slds-button slds-button_brand'])[2]")).click();
		System.out.println("clicked on Save Button");
		Thread.sleep(8000);
		
		//Creating invoice 
		driver.findElement(By.xpath(".//*[@id='brandBand_1']/div/div[1]/div/div/div/div/div/div/div/div[3]/article/div[2]/div/button")).click();
		System.out.println("clicked on Create invoice Button");
		Thread.sleep(12000);
		
		//Clicking on Transaction tab
		System.out.println("clicked on Transactions Tab"); 
		driver.findElement(By.xpath("//a[@title='Transactions']")).click();
		
		/*//Selecting list View
		Thread.sleep(5000);
		Select sel = new Select(driver.findElement(By.xpath("//select[@class=\"slds-select select uiInput uiInputSelect uiInput--default uiInput--select\"]")));
		sel.selectByVisibleText("Verified");
		Thread.sleep(9000);*/
		
		
		return false;
	}
	
	
	

}
