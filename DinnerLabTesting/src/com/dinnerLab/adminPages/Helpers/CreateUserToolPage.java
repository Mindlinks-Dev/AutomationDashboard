/**
 * @author: Basappa Hunsikatti
 * @Created Date :15/10/2015
 * @Updated Date :
 * @Comments This automation class will serve the creating users in admin page.
 */
package com.dinnerLab.adminPages.Helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.MemberLoginLogoutPage;
import com.dinnerLab.util.ExcelLib;
import com.pack.AutomateLoader;
import com.pack.Database;
import com.pack.FerrariMPHandler;

public class CreateUserToolPage 
{
	private String AdminEmail;
	private String AdminPassword;
	private String Fname;
	private String Lname;
	private String email;
	private String loginURL;
	private String GenarateRandomPassword;
	private String passwordForUser;
	private String City;
	private String userType;
	private String SendEmail;
	private String linkToPromoCode;
	private String PromoCode;
	private String RenewalDateConfigure;
	private String RenewalDate;
	public static boolean errorMessageStatus;
	public int rowCount;
	int i=0;
	private static Logger log = Logger.getLogger(CreateUserToolPage.class);
	public static String password1;
	boolean status = false;
	public int CurrentRow = 0;
	Connection con = null;
	ResultSet rs = null;
	Connection newcon = null; //for Local DB
	String userCreatedStatus = null;
	String JobNumber="";
	String ErrorMessage="";
	String errMsg ="";
	String userName ="";
	String userTypeID=null;
	String getUserTypeId [];
	//long startTime = System.currentTimeMillis();
	long startTime = 0;
	//long endTime   = System.currentTimeMillis();
	long endTime   = 0;
	public int ProcessRecords = 0;
	//FerrariMPHandler obj = new FerrariMPHandler();
	//MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
	static final String DB_URL = AutomateLoader.config.getProperty("DL_DEVELOPMENT_DB_URL");
	//static final String DB_URL = AutomateLoader.config.getProperty("FERRARI_DBURL");
			
	public String getUserDetails(String email)
	{
		//MPHandler obj = new MPHandler();
		//Connection con = obj.getConnection();
		//ResultSet rs = null;
		String UserId = null;
		//String query="SELECT * FROM `users_emails` WHERE emailID=(SELECT emailID FROM `emails` WHERE EMAIL LIKE'%aaadltestingrope@gmail.com%')";
		try 
		{
			rs = con.createStatement().executeQuery("SELECT * FROM users_emails WHERE emailID=(SELECT emailID FROM emails WHERE email ='"+email+"')");
		    while(rs.next())
	        {
		    	UserId = rs.getString(1);
	    	  
	        }
			} catch (SQLException e) 
		    {
				e.printStackTrace();
		    }
		/*finally
		{
			try 
			{
				rs.close();
			}catch (SQLException e) 
			{
				e.printStackTrace();
			}
			try 
			{
				con.close();
			}catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		*/
		//System.out.println("User Id is getConnection() method ::" +UserId);
		return UserId;
	}
	
	
	
	//****************************Modification on 15-0ctober**************************
	
	public void getFerrariDbConnection()
	{
		log.info("*********************Create User Tool Logger Initialized****************************************************************");
		log.info("First Name  ||"+" Last Name ||" + "   Email ||" + "  Password || " + " UserID  || " + "  Status || " + " UserTypeId");
 		log.info("************************************************************************************************************************");
		FerrariMPHandler obj = new FerrariMPHandler();
		
		if(loginURL.equalsIgnoreCase(TestConstants.RELEASE1_URL))
		{
			con=obj.getConnection();
		}else
		{
			con=obj.getDLProductionDBConnection();
		}
		
		//newcon=Database.getConnection();//for our Local DB Connection
	}
	
	public void writeIntoNewUserTable(List<String> listObject,Map<String,String> mapObject)
	{
		
		
		//System.out.println("Inside writeIntoNewUserTable() :::");
		//System.out.println("Printing lis listObject Contents : "+listObject);
		//System.out.println("Printing lis listObject Size : "+listObject.size());
		//System.out.println("Map contents are ::: "+mapObject);
		
		
	}
	
	
	//****************************Modification on 15-0ctober ends here **************************
	
	public boolean AdminLogin(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowCount= xllib.getRowCount("AdminLogin");
		 	for (i = 1; i <= rowCount; i++) 
			{
		 		loginURL = xllib.getExcelData("AdminLogin", i, 0);
		 		AdminEmail = xllib.getExcelData("AdminLogin", i, 1);
				AdminPassword = xllib.getExcelData("AdminLogin", i, 2);
				//JobNumber=xllib.getExcelData("AdminLogin", i, 3);
				
				//System.out.println("Admin email:"+AdminEmail);
				//System.out.println("Admin password:"+AdminPassword);
				//System.out.println("loginURL:"+loginURL);
				//System.out.println("Job No:"+JobNumber);
				
				//Calling user generation codes url method
				createUserToolURLActions(driver,loginURL);
				
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				//Calling Login method
				loginLogout.loginPageActions(driver,AdminEmail,AdminPassword);
				
				//driver.get(TestConstants.CreatUserTool);
				
				//driver.findElement(By.id("email")).sendKeys(AdminEmail);
				//driver.findElement(By.id("password")).sendKeys(AdminPassword);
				//driver.findElement(By.id("login-submit")).click();
			}
		 	return true;
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 return false;
		 }
	}
	
	/**
	 * Test Case for passing Create User Tool Page url
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public  void createUserToolURLActions(WebDriver driver,String createUserToolURL)
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 String userToolURL = TestConstants.HTTP+createUserToolURL+TestConstants.CREATE_USER_TOOL_URL;
			 //System.out.println("userToolURL:"+userToolURL);
			 driver.manage().window().maximize();
			 driver.get(userToolURL);
			 log.info("navigating to "+userToolURL);
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}catch(Exception e)
		{
			e.printStackTrace();
		 }
	}	
	 
	public void loadUser(WebDriver driver) throws InvalidFormatException, InterruptedException
	{
		ExcelLib xllib = new ExcelLib();
		
		rowCount= xllib.getRowCount("CreateUserTool");
		//System.out.println("original row count is :"+rowCount);
		
		 
		//Insert into joblog table
		/*if(!JobNumber.isEmpty())
		{
			
		insertIntoJobLog();
		}*/
		
		try{	
		addUser(driver,xllib);
		}
		finally
		{
			try 
			{
				rs.close();
			}catch (SQLException e) 
			{
				e.printStackTrace();
			}
			try 
			{
				con.close();
			}catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		
		//Update Job log after finishing
		/*if(!JobNumber.isEmpty())
		{
		UpdateJobLog();
		}*/
		
		 /*
		 
		while(CurrentRow <= rowCount)
		{
			CurrentRow++;
			System.out.println("current row Number :"+CurrentRow);
			
			try
			{
				
			}
			catch(Exception ex){
				System.out.println("Exception occured when processing row:"+CurrentRow+":Exception Message:"+ex.getMessage());
				
			}
		}
		*/
	}
	
	public void addUser(WebDriver driver,ExcelLib obj) throws InvalidFormatException
	{
		
		List<String> listObject = new ArrayList<String>();
		Map<String,String> mapObject = new HashMap<String,String>();
		
		
		try
		{
		//for (i = CurrentRow ; i <= rowCount; i++) 
			ExcelLib xllib = new ExcelLib();
			
		 	for (i = 1; i <= rowCount; i++) 
			{
				 try
				 {
				 	//rowCount= xllib.getRowCount("CreateUserToolSheet");

			 		//System.out.println("count is"+rowCount);
			 		CurrentRow = i;
			 		
			 		Fname= xllib.getExcelData("CreateUserTool", i, 0);
			 		Lname = xllib.getExcelData("CreateUserTool", i, 1);
			 		email= xllib.getExcelData("CreateUserTool", i, 2);
			 		GenarateRandomPassword= xllib.getExcelData("CreateUserTool", i, 3);
			 	    passwordForUser= xllib.getExcelData("CreateUserTool", i, 4);
			 	    City= xllib.getExcelData("CreateUserTool", i, 5);
			 		userType= xllib.getExcelData("CreateUserTool", i, 6);
			 		RenewalDateConfigure= xllib.getExcelData("CreateUserTool", i, 7);
			 		RenewalDate= xllib.getExcelData("CreateUserTool", i, 8);
			 		SendEmail= xllib.getExcelData("CreateUserTool", i, 9);
			 		linkToPromoCode= xllib.getExcelData("CreateUserTool", i, 10);
			 		PromoCode= xllib.getExcelData("CreateUserTool", i, 11);
			 		
			 		//System.out.println("fanmes:"+Fname);
			 		//System.out.println("lname:"+Lname); 
			 		//System.out.println("emails:"+email);
			 		//System.out.println("GenarateRandomPassword:"+GenarateRandomPassword);
			 	    //System.out.println("password:"+passwordForUser);
			 		//System.out.println("city:"+City);
			 		//System.out.println("userType:"+userType);
			 		//System.out.println("RenewalDateConfigure:"+RenewalDateConfigure);
			 		//System.out.println("RenewalDate:"+RenewalDate);
			 		//System.out.println("send email opltion:"+SendEmail);
			 		//System.out.println("linkToPromoCode:"+linkToPromoCode);
			 		//System.out.println("PromoCode:"+PromoCode);
			 		
			 		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			 		driver.findElement(By.id("first_name")).clear();
			 		driver.findElement(By.id("first_name")).sendKeys(Fname);
			 		driver.findElement(By.id("last_name")).clear();
			 		driver.findElement(By.id("last_name")).sendKeys(Lname);
			 		driver.findElement(By.id("email")).clear();
			 		driver.findElement(By.id("email")).sendKeys(email);
			 		if(GenarateRandomPassword.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 		{
			 			driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS); 
			 			driver.findElement(By.xpath("//a[@onclick='autoGeneratePassword()']")).click();
			 			//System.out.println("Random password is genearted ");
			 			//listObject.add("PasswordSetForUser");
			 		}
			 		else
			 		{
			 			driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS); 
			 			driver.findElement(By.id("password")).clear();
			 			driver.findElement(By.id("password")).sendKeys(passwordForUser);
			 			//System.out.println("admin has set the password"+passwordForUser);
			 			//listObject.add("PasswordSetForUser");
			 		}
				
			 		String password=driver.findElement(By.id("password")).getAttribute("value");
			 		mapObject.put("password",password);
			 		listObject.add(password);
			 		password1=password;
			 		//System.out.println("passord for the user "+email+ " is " +password1);
			 		//String kl=obj. writeToExcel("CreateUserToolSheet", i, 9,password );
		
			 		driver.findElement(By.id("city")).sendKeys(City);
			 		
			 		//Select user Type
			 		//driver.findElement(By.id("user_type")).sendKeys(userType);
			 		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			 		WebElement user= driver.findElement(By.id("user_type"));
					Select st = new Select(user);
					st.selectByVisibleText(userType);
					
			 		//driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS); 
			 		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
			 		
			 		//Condition to Renewal Date
			 		if(userType.equalsIgnoreCase(TestConstants.SelectMember)||userType.equalsIgnoreCase(TestConstants.DONATED)||userType.equalsIgnoreCase(TestConstants.GIFTED))
			 		{
			 			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			 			try
			 			{
			 				if(RenewalDateConfigure.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 				{
			 					driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			 					driver.findElement(By.id("renewal_date")).clear();
			 					driver.findElement(By.id("renewal_date")).sendKeys(RenewalDate);
			 				}
			 			}
			 			catch(ElementNotVisibleException ex)
			 			{
			 				ex.printStackTrace();
			 			}
			 		}
			 		//Condition to Send Email
			 		if(SendEmail.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 		{
			 			driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS); 
			 			driver.findElement(By.id("send_email")).click();
			 		}
			 		//Condition to Link PromoCode
			 		if(linkToPromoCode.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 		{
			 			driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS); 
			 			driver.findElement(By.id("link_to_promocode")).click();
			 			driver.findElement(By.id("promocode")).clear();
			 			driver.findElement(By.id("promocode")).sendKeys(PromoCode);
			 		}
			 		
			 		driver.findElement(By.xpath("//button[contains(text(), 'Submit')]")).click();
				    String UserId = getUserDetails(email);
				    
				    /* listObject.add(UserId);
				    
				    listObject.add(Fname);
			 		listObject.add(Lname);
			 		listObject.add(email);
			 		listObject.add(GenarateRandomPassword);
			 		listObject.add(passwordForUser);
			 		listObject.add(City);
			 		listObject.add(userType);
			 		listObject.add(SendEmail);
			 		listObject.add(City);
			 		
			 		//for MAP Details
			 		mapObject.put("Fname",Fname);
			 		mapObject.put("Lname",Lname);
			 		mapObject.put("email",email);
			 		mapObject.put("GenarateRandomPassword",GenarateRandomPassword);
			 		mapObject.put("passwordForUser",passwordForUser);
			 		mapObject.put("City",City);
			 		mapObject.put("userType",userType);
			 		mapObject.put("SendEmail",SendEmail);
			 		mapObject.put("City",City);
			 		//mapObject.put("GenarateRandomPassword",GenarateRandomPassword);
			 		
				    
					    
					mapObject.put("UserId",UserId);
					*/
				    
				    
				    //System.out.println("Email id before Sending to getConnection () method  is " +email);
			 		//String userId = getConnection(email);
			 		//System.out.println("inside addUser() after getting email from getConnection() userId is  :"+UserId);
			 		//mapObject.put("UserId",userId);
			 		//System.out.println("UserId inside addUser() :::::" +UserId);
			 		//driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			 		boolean createUserStatus = false;
			 		
			 		try
			 		{
			 			createUserStatus = driver.findElement(By.xpath("//span[contains(text(), 'Operation Success')]")).isDisplayed();
			 			System.out.println("createUserStatus :"+createUserStatus);
			 			if(createUserStatus)
			 			{
			 				userTypeID = driver.findElement(By.xpath("//li[contains(text(),'usertypeID:')]")).getText();
			 				System.out.println("userTypeID :"+userTypeID);
			 				getUserTypeId = userTypeID.split(":");
			 				System.out.println("getUserTypeId:"+getUserTypeId[1]);
			 				
			 			}
			 			
			 		}
			 		catch(Exception e)
					{
			 			log.info(","+Fname +"," +Lname +"," + email + "," + password +",Fail" +","+"Fail"+","+"Fail");
			 			e.printStackTrace();
					}
			 		//listObject.add(password);
			 		if(createUserStatus)
					{
			 			userCreatedStatus="Pass";
			 			log.info(","+Fname +","+Lname+"," + email + "," + password +"," + UserId +","+userCreatedStatus +","+getUserTypeId[1]);
			 			//listObject.add(userCreatedStatus);
			 			mapObject.put("userCreatedStatus",userCreatedStatus);
						obj. writeToExcel("CreateUserTool", i, 12,"PASS" );
						obj. writeToExcel("CreateUserTool", i, 13,password1);
						obj. writeToExcel("CreateUserTool", i, 14,UserId);
						obj. writeToExcel("CreateUserTool", i, 15,getUserTypeId[1]);
						//ProcessRecords++;
					}else if(!createUserStatus)
					{
						 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);  
						 userCreatedStatus ="FAIL";
						 userName = Fname;
						 //listObject.add(userCreatedStatus);
						 mapObject.put("userCreatedStatus",userCreatedStatus);
						 obj. writeToExcel("CreateUserTool", i, 12,"FAIL" );
					 	 obj. writeToExcel("CreateUserTool", i, 13," ");
					 	 obj. writeToExcel("CreateUserTool", i, 14,"FAIL" );
					 	 obj. writeToExcel("CreateUserTool", i, 15,"FAIL" );
					 	 driver.navigate().refresh();
					 	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
					 }
			 	}
				 catch(Exception e)
				 {
					 e.printStackTrace();
					 /* System.out.println("Inside catch block after if condition before writing into excel ");
					 e.printStackTrace();
					  errMsg =errMsg+driver.findElement(By.xpath(".//*[@id='wrapper']/div[3]/div/ul/li")).getText()+userName+" , ";
					  
					  System.out.println("new1111111 Error msg is :"+errMsg);
					 
					 ErrorMessage=e.getMessage();
					 System.out.println("error msg 1 is ::::::"+ErrorMessage);
					 obj. writeToExcel("CreateUserTool", i, 12,"fail" );
					 obj. writeToExcel("CreateUserTool", i, 13," ");*/
					 //log.info(","+Fname +"," + email + "," + passwordForUser +",Fail");
					 //System.out.println("Inside catch block after if condition after writing into excel");*/
				 }
				 
				 //calling writeIntoNewUserTable() to write into new table
				 
				// writeIntoNewUserTable(listObject,mapObject);
			}//End of FOR LOOP
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ErrorMessage=e.getMessage();
			//System.out.println("error message is :"+ErrorMessage);
			
		}
	}
	
	
	//********************Modifications on October 16 *********************************************
	
	
	public void insertIntoJobLog()
	{
		//Insert into 1 record into new table - usercreatejobs
		//System.out.println("Inside insertIntoJobLog() ::::");
		
		//System.out.println("JobNumber Inside insertIntoJobLog is :"+JobNumber);
		//System.out.println("Total Records Inside insertIntoJobLog() are :"+rowCount);
		startTime = System.currentTimeMillis();
		String insertSQL = "INSERT INTO usercreatejobs (JobNumber,StartDate,TotalRecords,Status) VALUES ("+JobNumber+",now(),"+rowCount+",'InProgress')";
		//System.out.println("Printing insert query :"+insertSQL);
		try {
			 int i = newcon.createStatement().executeUpdate(insertSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	public void UpdateJobLog()
	{
		//Insert into 1 record into new table - usercreatejobs
		//System.out.println("Inside UpdateJobLog() ::::");
		String Status ="";
		if(ErrorMessage.isEmpty())
		{
			Status ="Completed";
		}
		else
		{
			Status ="Failure";
		}
		endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		//System.out.println("totalTime taken to process records in milliseconds :::"+totalTime);
		
		long newtotalTime = (endTime - startTime)/1000;
		//System.out.println("totalTime taken to process records in Seconds :::"+newtotalTime);
		
		
		//*******************total time calculation**********************
		
		int day = (int)TimeUnit.SECONDS.toDays(newtotalTime);        
		long hours = TimeUnit.SECONDS.toHours(newtotalTime) - (day *24);
		long minute = TimeUnit.SECONDS.toMinutes(newtotalTime) - (TimeUnit.SECONDS.toHours(newtotalTime)* 60);
		long second = TimeUnit.SECONDS.toSeconds(newtotalTime) - (TimeUnit.SECONDS.toMinutes(newtotalTime) *60);
		//System.out.println("Day " + day + " Hour " + hours + " Minute " + minute + " Seconds " + second);
		String newTime = hours+":"+ minute+":"+second;
		//System.out.println("New Time in correct format is :"+newTime);
		//System.out.println("New Time Is: "+newTime);​
		
				
		//*********************total time calculation ends ****************
		//String updateSQL = "UPDATE usercreatejobs SET ProcessedRecords='"+ProcessRecords+"',Status='"+Status+"',ErrorMessage='"+errMsg+"',EndTime=now(),TotalTimeTaken='"+newTime+"' WHERE JobNumber="+JobNumber+"";
		String updateSQL = "UPDATE usercreatejobs SET ProcessedRecords='"+ProcessRecords+"',Status='"+Status+"',EndTime=now(),TotalTimeTaken='"+newTime+"' WHERE JobNumber="+JobNumber+"";
		//System.out.println("Printing Upadte Query :"+updateSQL);
		try {
			int i =newcon.createStatement().executeUpdate(updateSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				newcon.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	
	//**************************Modifications on October 16 Ends here*************************************

}


