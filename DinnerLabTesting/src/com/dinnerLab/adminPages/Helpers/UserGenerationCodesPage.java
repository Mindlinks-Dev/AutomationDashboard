/**
 * @author: Basappa Hunsikatti
 * @Created Date :28/10/2015
 * @Updated Date :
 * @Comments This automation class will serve the creating Sign Up Codes
 */
package com.dinnerLab.adminPages.Helpers;

import java.sql.Connection;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.MemberLoginLogoutPage;
import com.dinnerLab.util.ExcelLib;
import com.pack.Database;
import com.pack.ScriptHandler;
import com.pack.StripeHandler;

public class UserGenerationCodesPage 
{
	private String loginEmail;
	private String loginPassword;
	private String createSignUpCodeURL;
	boolean present;
	//Connection con = Database.getConnection();
	Statement stm=null;
	String isTestPassed="FAIL";
	public static String result;
	public static String comment;
	public static boolean loginStatus;
	private static String selectAll;
	private static String name;
	private static String description;
	private static String userType;
	private static String useDefaultLink;
	private static String linkCopy;
	private static String signUpCreditStatus;
	private static String signUpCredit;
	private static String expirationDateStatus;
	private static String expirationDate;
	private static String monthsToBilling;
	private static String codeStatus;
	private static String code;
	private static String limitCitiesStatus;
	private static String ticketDiscountStatus;
	private static String ticketDiscount;
	private static String signUpLimitStatus;
	private static String signUpLimit;
	private static String Atlanta;
	private static String Austin;
	private static String Baltimore;
	private static String BatonRouge;
	private static String Birmingham;
	private static String Boston;
	private static String Charlotte;
	private static String Chicago;
	private static String Cleveland;
	private static String Columbus;
	private static String DC;
	private static String Dallas;
	private static String Denver;
	private static String Houston;
	private static String Indianapolis;
	private static String KansasCity;
	private static String LosAngeles;
	private static String Miami;
	private static String Milwaukee;
	private static String Minneapolis;
	private static String Nashville;
	private static String NewOrleans;
	private static String NewYork;
	private static String Philadelphia;
	private static String Phoenix;
	private static String Pittsburgh;
	private static String Portland;
	private static String Raleigh;
	private static String SanAntonio;
	private static String SanDiego;
	private static String SanFrancisco;
	private static String Seattle;
	private static String StLouis;
	public static String signUpCode;
	public static boolean createSignUpCodeStatus;
	public String finalSignUpCode = " ";
	int i = 0;
	private static Logger log = Logger.getLogger(UserGenerationCodesPage.class);
	//MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	
	//StripeHandler stripe = new StripeHandler();
	        
	public void userGenerationCodesLogin(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			int rowCount = xllib.getRowCount("AdminLogin");
			//For loop:Fetch data from excel input configuration file(excel sheet)
			for(i=1;i<= rowCount;i++)
			{  
				createSignUpCodeURL = xllib.getExcelData("AdminLogin", i, 0);
				loginEmail = xllib.getExcelData("AdminLogin", i, 1);
				loginPassword = xllib.getExcelData("AdminLogin", i, 2);
							
				//Calling user generation codes url method
				userGenerationCodesURLActions(driver,createSignUpCodeURL);
			
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				//Calling Login method
				loginLogout.loginPageActions(driver,loginEmail,loginPassword);
			}
		}
		catch(Exception e)
		{
			log.info("User Generation Code Unsucessful");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	 public void userGenerationCodesInitialPage(WebDriver driver) throws InvalidFormatException
	 {
		 
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			int rowCount = xllib.getRowCount("UserGenerationCodes");
			//For loop:Fetch data from excel input configuration file(excel sheet)
			for(i=1;i<= rowCount;i++)
			{  
				//createSignUpCodeURL = xllib.getExcelData("UserGenerationCodes", i, 0);
				//loginEmail = xllib.getExcelData("UserGenerationCodes", i, 1);
				//loginPassword = xllib.getExcelData("UserGenerationCodes", i, 2);
				
				name = xllib.getExcelData("UserGenerationCodes", i, 0);
				description = xllib.getExcelData("UserGenerationCodes", i, 1);
				userType = xllib.getExcelData("UserGenerationCodes", i, 2);
				useDefaultLink = xllib.getExcelData("UserGenerationCodes", i, 3);
				linkCopy = xllib.getExcelData("UserGenerationCodes", i, 4);
				signUpCreditStatus = xllib.getExcelData("UserGenerationCodes", i, 5);
				signUpCredit = xllib.getExcelData("UserGenerationCodes", i, 6);
				expirationDateStatus = xllib.getExcelData("UserGenerationCodes", i, 7);
				expirationDate = xllib.getExcelData("UserGenerationCodes", i, 8);
				monthsToBilling = xllib.getExcelData("UserGenerationCodes", i, 9);
				codeStatus = xllib.getExcelData("UserGenerationCodes", i, 10);
				code = xllib.getExcelData("UserGenerationCodes", i, 11);
				
				limitCitiesStatus = xllib.getExcelData("UserGenerationCodes", i, 12);
				selectAll = xllib.getExcelData("UserGenerationCodes", i, 13);
				Atlanta	= xllib.getExcelData("UserGenerationCodes", i, 14);	
				Austin	= xllib.getExcelData("UserGenerationCodes", i, 15);	
				Baltimore = xllib.getExcelData("UserGenerationCodes", i, 16);
			 	BatonRouge = xllib.getExcelData("UserGenerationCodes", i, 17);
			 	Birmingham = xllib.getExcelData("UserGenerationCodes", i, 18);
			 	Boston = xllib.getExcelData("UserGenerationCodes", i, 19);
				Charlotte = xllib.getExcelData("UserGenerationCodes", i, 20);
				Chicago = xllib.getExcelData("UserGenerationCodes", i, 21);
				Cleveland = xllib.getExcelData("UserGenerationCodes", i, 22);
				Columbus = xllib.getExcelData("UserGenerationCodes", i, 23);
				DC = xllib.getExcelData("UserGenerationCodes", i, 24);
				Dallas = xllib.getExcelData("UserGenerationCodes", i, 25);
				Denver	= xllib.getExcelData("UserGenerationCodes", i, 26);	
				Houston	= xllib.getExcelData("UserGenerationCodes", i, 27);	
				Indianapolis = xllib.getExcelData("UserGenerationCodes", i, 28);
				KansasCity = xllib.getExcelData("UserGenerationCodes", i, 29);
				LosAngeles = xllib.getExcelData("UserGenerationCodes", i, 30);
				Miami = xllib.getExcelData("UserGenerationCodes", i, 31);
				Milwaukee = xllib.getExcelData("UserGenerationCodes", i, 32);
				Minneapolis = xllib.getExcelData("UserGenerationCodes", i, 33);
				Nashville = xllib.getExcelData("UserGenerationCodes", i, 34);
				NewOrleans = xllib.getExcelData("UserGenerationCodes", i, 35);
				NewYork = xllib.getExcelData("UserGenerationCodes", i, 36);
				Philadelphia = xllib.getExcelData("UserGenerationCodes", i, 37);	
				Phoenix	= xllib.getExcelData("UserGenerationCodes", i, 38);	
				Pittsburgh = xllib.getExcelData("UserGenerationCodes", i, 39);
				Portland = xllib.getExcelData("UserGenerationCodes", i, 40);
				Raleigh = xllib.getExcelData("UserGenerationCodes", i, 41);
				SanAntonio = xllib.getExcelData("UserGenerationCodes", i, 42);
				SanDiego = xllib.getExcelData("UserGenerationCodes", i, 43);
				SanFrancisco = xllib.getExcelData("UserGenerationCodes", i, 44);
				Seattle = xllib.getExcelData("UserGenerationCodes", i, 45);
				StLouis = xllib.getExcelData("UserGenerationCodes", i, 46);
				
				ticketDiscountStatus = xllib.getExcelData("UserGenerationCodes", i, 47);
				ticketDiscount = xllib.getExcelData("UserGenerationCodes", i, 48);
				signUpLimitStatus = xllib.getExcelData("UserGenerationCodes", i, 49);
				signUpLimit = xllib.getExcelData("UserGenerationCodes", i, 50);
				
				System.out.println("expirationDate:"+expirationDate);
				//Calling user generation codes url method
				//userGenerationCodesURLActions(driver,createSignUpCodeURL);
				
				//Calling Login method
				//loginLogout.loginPageActions(driver,loginEmail,loginPassword);
				//System.out.println(MemberLoginLogoutPage.loggedInStatus);
				
				//Check whether login credentials valid or not
				//if(MemberLoginLogoutPage.loggedInStatus)
				//{
					//isTestPassed="PASS";
					//ScriptHandler.result=xllib.writeToExcel("UserGenerationCodes", i, 55, isTestPassed);
					//ScriptHandler.comment.add("Script User Generation Codes, Test Data "+i+": Success in Login page.");
					
					createUserGenerationCodes(driver);
				    log.info("*****************Logger for Class UserGeneration Codes starts here*******************");
			        log.info("||                   Created Sign Up Code                 ||");
			        fetchingSignUpCode(driver);
					log.info("*****************Logger for Class UserGeneration Codes ends  here*******************");
					
					
					//Calling Logout method
					//loginLogout.logoutActions(driver);
				//}
				//else
				//{
					//isTestPassed = "FAIL";
					//ScriptHandler.result=xllib.writeToExcel("UserGenerationCodes", i, 55, isTestPassed);
					//ScriptHandler.comment.add("Script User Generation Codes, Test Data "+i+": Failed in Login page.");							
				//}
				
				//Check whether login credentials valid or not
				if(createSignUpCodeStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("UserGenerationCodes", i, 51, isTestPassed);
					xllib.writeToExcel("UserGenerationCodes", i, 52, finalSignUpCode);
				}
				else
				{
					isTestPassed = "FAIL";
					xllib.writeToExcel("UserGenerationCodes", i, 51, isTestPassed);
					xllib.writeToExcel("UserGenerationCodes", i, 52, isTestPassed);
					ScriptHandler.comment.add("Script User Generation Codes, Test Data "+i+": Failed in User Generation Codes page.");							
				}
			}
		}
		catch(Exception e)
		{
			log.info("User Generation Code Unsucessful");
			e.printStackTrace();
		}
	}
	
	/**
	 * Test Case for passing create sign up url
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void userGenerationCodesURLActions(WebDriver driver,String createSignUpCodeURL)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 String signUpCodeURL = TestConstants.HTTP+createSignUpCodeURL+TestConstants.SUPER_ADMIN_USER_GEN;
			 //System.out.println("Login URL:"+signUpCodeURL);
			 driver.manage().window().maximize();
			 driver.get(signUpCodeURL);
			 log.info("navigating to "+signUpCodeURL);
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}catch(Exception e)
		{
			e.printStackTrace();
		 }
	}	
	/**
	 * Test Case for generating codes
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InterruptedException 
	 * @throws InvalidFormatException 
	*/
	public void createUserGenerationCodes(WebDriver driver) throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@href='add']")).click(); 
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys(name);
			
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys(description);
				
		WebElement user = driver.findElement(By.id("usertype"));
		Select st = new Select(user);
		st.selectByVisibleText(userType);	
		
		if(useDefaultLink.equalsIgnoreCase(TestConstants.STATUS_YES))
		{
			driver.findElement(By.name("signup-copy")).clear();
			driver.findElement(By.name("signup-copy")).sendKeys(linkCopy);
		}
		
		if(signUpCreditStatus.equalsIgnoreCase(TestConstants.STATUS_YES))
		{
			 //Converting String to Integer			
			 //int credits = Integer.parseInt(signUpCredit);
			 //System.out.println("credits:"+credits);
			 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			 driver.findElement(By.xpath("//label[contains(text(),'Sign-up Credit:')]")).click();
			 driver.findElement(By.name("joinerCreditAfterSignup")).sendKeys(signUpCredit);
			 
			 //Condition to add Sign Up Credits
			 /*for(int j=1;j<=credits;j++)
			 {
				 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				 driver.findElement(By.name("joinerCreditAfterSignup")).click();
			 }*/
		}
		
		if(expirationDateStatus.equalsIgnoreCase(TestConstants.STATUS_YES))
		{
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//label[contains(text(),'Expiration Date:')]")).click();
			driver.findElement(By.id("datepicker")).clear();
			driver.findElement(By.id("datepicker")).sendKeys(expirationDate);
			//driver.findElement(By.id("datepicker")).click();
		}
		
		if(userType.equalsIgnoreCase(TestConstants.SELECT_MEMBER))
		{
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			driver.findElement(By.id("billing-check")).click();
			driver.findElement(By.id("months")).sendKeys(monthsToBilling);
			
			//Converting String to Integer			
			//int months = Integer.parseInt(monthsToBilling);
			//System.out.println("months:"+months);
			
			//Condition to add Sign Up Credits
			 /*for(int k=1;k<=months;k++)
			 {
				 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				 driver.findElement(By.id("months")).click();
			 }*/
		}
		
		if(codeStatus.equalsIgnoreCase(TestConstants.STATUS_YES))
		{
			driver.findElement(By.xpath("//label[contains(text(),'Code:')]")).click();
			driver.findElement(By.name("code")).clear();
			driver.findElement(By.name("code")).sendKeys(code);
		}
		
		//Condition for limiting cities
		if(limitCitiesStatus.equalsIgnoreCase(TestConstants.STATUS_YES))
		{
			//Calling limiting cities method
			limitCitiesActions(driver);
		}
		
		if(ticketDiscountStatus.equalsIgnoreCase(TestConstants.STATUS_YES))
		{
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//label[contains(text(),'Ticket Discount:')]")).click();
			
			 driver.findElement(By.name("ticketDiscount")).sendKeys(ticketDiscount);
			 
			//Converting String to Integer			
			//int tickets = Integer.parseInt(ticketDiscount);
			//System.out.println("tickets:"+tickets);
			
			//Condition to add ticket discount
			 /*for(int l=1;l<=tickets;l++)
			 {
				 Thread.sleep(2000);
				 driver.findElement(By.name("ticketDiscount")).click();
			 }*/
		}
		
		if(signUpLimitStatus.equalsIgnoreCase(TestConstants.STATUS_YES))
		{
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//label[contains(text(),'Sign-up Limit:')]")).click();
			
			driver.findElement(By.name("codeJoinerCountLimit")).sendKeys(signUpLimit);
			
			//Converting String to Integer			
			//int signUp = Integer.parseInt(signUpLimit);
			//System.out.println("signUp:"+signUp);
			
			//Condition to add ticket discount
			/* for(int l=1;l<=signUp;l++)
			 {
				 //driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				 Thread.sleep(2000);
				 driver.findElement(By.name("codeJoinerCountLimit")).click();
			 }*/
		}
		
		//Clicking on Create Code button
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}
	/**
	 * Test Case for limiting cities
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public void limitCitiesActions(WebDriver driver) 
	{
		driver.findElement(By.xpath("//label[contains(text(),'Limit Cities:')]")).click();
		//Condition to Select all Cities
		if(selectAll.equalsIgnoreCase(TestConstants.STATUS_YES))
		{
			driver.findElement(By.id("select-all")).click();
		}
		
		boolean selectAllStatus = driver.findElement(By.id("select-all")).isSelected();
		System.out.println("selectAllStatus:"+selectAllStatus);
				
		if(selectAllStatus)
		{
			if(Atlanta.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Atlanta')]")).click();
			}
			if(Austin.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Austin')]")).click();
			}
			if(Baltimore.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Baltimore')]")).click();
			}
			if(BatonRouge.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Baton Rouge')]")).click();
			}
			if(Birmingham.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Birmingham')]")).click();
			}
			if(Boston.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Boston')]")).click();
			}
			if(Charlotte.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Charlotte')]")).click();
			}
			if(Chicago.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Chicago')]")).click();
			}
			if(Cleveland.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Cleveland')]")).click();
			}
			if(Columbus.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Columbus')]")).click();
			}
			if(DC.equalsIgnoreCase(TestConstants.STATUS_YES)){
				driver.findElement(By.xpath("//label[contains(text(),'D.C.')]")).click();
			}
			if(Dallas.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Dallas')]")).click();
			}
			if(Denver.equalsIgnoreCase(TestConstants.STATUS_YES)){
				driver.findElement(By.xpath("//label[contains(text(),'Denver')]")).click();
			}
			if(Houston.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Houston')]")).click();
			}
			if(Indianapolis.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Indianapolis')]")).click();
			}
			if(KansasCity.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Kansas City')]")).click();
			}
			if(LosAngeles.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),' Los Angeles')]")).click();
			}
			if(Miami.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Miami')]")).click();
			}
			if(Milwaukee.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Milwaukee')]")).click();
			}
			if(Minneapolis.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Minneapolis')]")).click();
			}
			if(Nashville.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Nashville')]")).click();
			}
			if(NewOrleans.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'New Orleans')]")).click();
			}
			if(NewYork.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),' New York')]")).click();
			}
			if(Philadelphia.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Philadelphia')]")).click();
			}
			if(Phoenix.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Phoenix')]")).click();
			}
			if(Pittsburgh.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Pittsburgh')]")).click();
			}
			if(Portland.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Portland')]")).click();
			}
			if(Raleigh.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Raleigh')]")).click();
			}
			if(SanAntonio.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'San Antonio')]")).click();
			}
			if(SanDiego.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'San Diego')]")).click();
			}
			if(SanFrancisco.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'San Francisco')]")).click();
			}
			if(Seattle.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'Seattle')]")).click();
			}
			if(StLouis.equalsIgnoreCase(TestConstants.STATUS_YES))
			{
				driver.findElement(By.xpath("//label[contains(text(),'St. Louis')]")).click();
			}
		}//End of outside if condition 
	}
	/**
	 * Test Case for fetching Sign Up Code
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public void fetchingSignUpCode(WebDriver driver) 
	{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		createSignUpCodeStatus = driver.findElement(By.xpath("//div[@class='successPrint']")).isDisplayed();
		System.out.println("createSignUpCodeStatus:"+createSignUpCodeStatus);
		signUpCode = driver.findElement(By.xpath("//div[@class='successPrint']")).getText();
		System.out.println("signUpCode:"+signUpCode);
		
		String[] firstSignUpCode = signUpCode.split("created");
        String[] splitSignUpCode = firstSignUpCode[1].split(" ");
        finalSignUpCode = splitSignUpCode[1];
		log.info(finalSignUpCode);
        //String newfinalSignUpCode = firstSignUpCode[1].split(" ").toString();
        System.out.println("FinalSignUpCode:"+finalSignUpCode);
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}
}



