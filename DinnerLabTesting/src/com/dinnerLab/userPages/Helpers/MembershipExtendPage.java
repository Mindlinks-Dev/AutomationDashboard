/**
 * @author: Basappa Hunsikatti
 * @Created Date :16/10/2015
 * @Updated Date :
 * @Comments This automation class will serve the Membership Extend.
 */
package com.dinnerLab.userPages.Helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;
import com.pack.FerrariMPHandler;

public class MembershipExtendPage 
{
	private String membershipURL;
	private String userID;
	private String months;
	public int rowCount;
	int i=0;
	Connection con =null;
	ResultSet rs = null;
	private static Logger log = Logger.getLogger(MembershipExtendPage.class);
	
	
	public void getFerrariDbConnection()
	{
		
		System.out.println("Inside getFerrariDbConnection() ::::");
		
		FerrariMPHandler obj = new FerrariMPHandler();
		//con=obj.getConnection();
		
		if(membershipURL.equalsIgnoreCase(TestConstants.RELEASE1_URL))
		{
			System.out.println("Before release1");
			con=obj.getConnection();
			System.out.println("After release1");
		}else
		{
			System.out.println("Before production");
			con=obj.getDLProductionDBConnection();
			System.out.println("After production");
		}
		System.out.println("Inside getFerrariDbConnection() after creating connection object");
		
	}
		
	public void getUserDetails(String userId)
	{
		
		//String UserId = null;
		String dblastBillingDate = null;
		String dbnextBillingDate = null;
		String dbrenewOnNextBillingDate = null;
		//String query="SELECT * FROM `users_emails` WHERE emailID=(SELECT emailID FROM `emails` WHERE EMAIL LIKE'%aaadltestingrope@gmail.com%')";
		System.out.println("UserId inside getUserDetails():"+userId);
		int userID = Integer.parseInt(userId);
		
	    String sqlQuery ="SELECT lastBillingDate,nextBillingDate,renewOnNextBillingDate FROM usersBillingSubscriptions WHERE userID ="+userID+" ";
	    System.out.println("Printing query inside getUserDetails() :: "+sqlQuery);
		try 
		{
			 rs = con.createStatement().executeQuery(sqlQuery);
		    while(rs.next())
	        {
		    	dblastBillingDate = rs.getString(1);
		    	dbnextBillingDate = rs.getString(2);
		    	dbrenewOnNextBillingDate =rs.getString(3);
	    	  
	        }
		    System.out.println("dblastBillingDate from db is 1:"+dblastBillingDate);
		    System.out.println("dbnextBillingDate from db is 2:"+dbnextBillingDate);
		    System.out.println("dbrenewOnNextBillingDate from db is 3:"+dbrenewOnNextBillingDate);
		    
			} catch (SQLException e) 
		    {
				e.printStackTrace();
		    }
		
	} 
	
	public void membershipExtendURLActions(WebDriver driver)
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowCount= xllib.getRowCount("AdminLogin");
		 	for (i = 1; i <= rowCount; i++) 
			{
		 		membershipURL = xllib.getExcelData("AdminLogin", i, 0);
		 		System.out.println("membershipURL:"+membershipURL);
		 		
		 		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		 		//String membershipExtendURL = membershipURL + TestConstants.MEMBERSHIP_EXTEND_URL;
		 		String membershipExtendURL = TestConstants.HTTP+membershipURL+TestConstants.MEMBERSHIP_EXTEND_URL;
				System.out.println("membershipExtendURL:"+membershipExtendURL);
				driver.get(membershipExtendURL);
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			}
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}
	public void membershipExtendActions(WebDriver driver)
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowCount= xllib.getRowCount("MembershipExtend");
			System.out.println("rowCount:"+rowCount);
			log.info("*********************Logger Initialized for Membership Extend Page******************************* ");
			log.info("UserID " +"||" + " Email " +"||" + "Subscription " +"||" +"Status" +" || " +"LastBillingDate" +"||" +"NextBillingDate"+"||" +"RenewonNextbilling");
		 	for (i = 1; i <= rowCount; i++) 
			{
		 		userID = xllib.getExcelData("MembershipExtend", i, 0);
				months = xllib.getExcelData("MembershipExtend", i, 1);
				
				System.out.println("User ID ::"+userID);
				System.out.println("months ::"+months);
				
				/*if(userID.equals("27490"))
				{
					System.out.println("Found userID with 27490 and continuing");
					continue;
					
				}*/
				
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
				driver.findElement(By.name("userID")).clear();
				driver.findElement(By.name("userID")).sendKeys(userID);
				driver.findElement(By.name("months")).clear();
				driver.findElement(By.name("months")).sendKeys(months);
				driver.findElement(By.id("submitManageCities")).click();
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				
				//getUserDetails(userID);
				//driver.navigate().refresh();
				//driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				String data = driver.findElement(By.xpath("//html/body")).getText();
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				System.out.println("Data123:"+data);
				System.out.println("************************************");
				String[] splitData =data.split("Who to extend:");
				//System.out.println("splitData :"+splitData[0]);	
				//String splitData1 = splitData[0];
				//System.out.println("splitdata1 is :"+splitData1);
			    String arr ="";
				for(int i= 0; i<splitData.length-1;i++)
				{
					//System.out.println("inside for loop i value is:"+i);
					arr=arr+splitData[i];
				}
				//System.out.println("arr array is :"+arr);
				String[] newArra= arr.split(userID);
				//System.out.println("newArray Is :"+newArra);
				if(newArra.length > 1)
				{
					String userDetails = newArra[1].toString();
					String[] afterSplittingData = userDetails.split(":");
				
					String[] email = afterSplittingData[1].split("\n");
					String[] subscription = afterSplittingData[2].split("\n");

					String[] status = afterSplittingData[3].split("\n");
					String[] lastBillingDate = afterSplittingData[4].split("\n");

					String[] nextBillingDate = afterSplittingData[5].split("\n");
			
					System.out.println("value of email ::"+email[0]);
					System.out.println("value of subscription ::"+subscription[0]);
					System.out.println("value of status ::"+status[0]);
					System.out.println("value of last billing date ::"+lastBillingDate[0]);
					System.out.println("value of next billing date ::"+nextBillingDate[0]);
					System.out.println("value of renewonNextbilling date ::"+afterSplittingData[6]);
					log.info(","+userID +","+email[0] +","+ subscription[0]+"," + status[0]+","+lastBillingDate[0]+","+nextBillingDate[0]+","+afterSplittingData[6]);
				
					xllib.writeToExcel("MembershipExtend", i, 2,email[0]);
					xllib.writeToExcel("MembershipExtend", i, 3,subscription[0]);
					xllib.writeToExcel("MembershipExtend", i, 4,status[0]);
					xllib.writeToExcel("MembershipExtend", i, 5,lastBillingDate[0]);
					xllib.writeToExcel("MembershipExtend", i, 6,nextBillingDate[0]);
					xllib.writeToExcel("MembershipExtend", i, 7,afterSplittingData[6]);
					//Thread.sleep(5000);
				}
			}
		 
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			
		 }
		finally
		{
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}


