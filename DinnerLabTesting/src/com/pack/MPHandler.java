/**
 * 
 */
package com.pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

/**
 * @author win
 *
 */
public class MPHandler
{
	static final String USER = AutomateLoader.config.getProperty("DLDevelopmentUserName");
	static final String PASS = AutomateLoader.config.getProperty("DLDevelopmentPassword");
	static final String DB_URL = AutomateLoader.config.getProperty("DLDevelopmentURL");
	StripeHandler stripe = new StripeHandler();
	public static String merchantFee;
	public static String amount;
	public static String planId;
	public static String transactionDate;
	public String stripeTransactionId = null;
	public String stripeCustomerId = null;
	public WebDriver driver;
	
	public Connection getConnection()
	//private Connection getConnection()
	{
		Connection conn = null;
		//Statement stmt = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} 
		catch(Exception ex)
		{
		
		}
		return conn;
	}
	
	public Map<String, String> getDetails(String email)
	{
		Map<String,String> getRecordsnew =  new HashMap<String,String>();
		//System.out.println("Email ID inside MpHandler java is :"+email);
		//System.out.println("Connecting to database...");
	    Connection  newCon = getConnection();
	    Statement stmt = null;
	    ResultSet rs = null;
	    String userId= null;
	    //System.out.println("Creating statement...");
	    //int i = 0;
	    try
	    {
	       stmt = newCon.createStatement();
	       String stripeCustomerIdQuery;
	    
	       stripeCustomerIdQuery = "SELECT User_ID,stripe_customeridTest,Plan_id from dev_Users where Email = '"+email+"' ";
	       //System.out.println("Db Query to fetch stripe user is :"+stripeCustomerIdQuery);
	       rs = stmt.executeQuery(stripeCustomerIdQuery);
	       while(rs.next())
	       {
	    	   getRecordsnew.put("User_ID",rs.getString(1));
	    	   getRecordsnew.put("stripe_customeridTest",rs.getString(2));
	    	   userId=(rs.getString(1));
	    	   stripeCustomerId = rs.getString(2);
	    	   planId = rs.getString(3);
	      }
	           
	      //Second time querying Database
	      String stripeTransactionIdQuery = null;
	      stripeTransactionIdQuery= "select transaction_id,merchant_fee,amount,transaction_date from payment where payer_email = '"+email+"' AND user_id = '"+userId+"' ORDER BY lastmodified_date desc limit 1";
	      //System.out.println("Printing 2nd DB query inside MPHandler java :"+stripeTransactionIdQuery);
	      rs = stmt.executeQuery(stripeTransactionIdQuery);
	      String transactionID =null;
	      while(rs.next())
	      {
	    	  getRecordsnew.put("transaction_id",rs.getString(1));
	    	  //getRecordsnew.put("Mechant_Fee",rs.getString(2));
	    	  //System.out.println("printing merchant Fee :"+rs.getString(2));
	          //System.out.println("printing Amount:"+rs.getString(3));
	    	  //System.out.println("printing Transaction_date:"+rs.getString(4));
	    	  getRecordsnew.put("Mechant_Fee",rs.getString(2));
	    	  getRecordsnew.put("Amount ",rs.getString(3));
	    	  getRecordsnew.put("Transaction_Date ",rs.getString(4));
	    	  
	    	  stripeTransactionId = rs.getString(1);
	    	  merchantFee = rs.getString(2);
	    	  amount = rs.getString(3);
	    	  transactionDate = rs.getString(4);
	      }
	      

      	//Calling Stripe set Up method
      	stripe.setUp();
      	
      	//Calling Stripe Customer Retrieve method
      	stripe.testCustomerRetrieve(driver,stripeCustomerId);
      
      	//Calling Stripe Charge Retrieve method
      	stripe.testChargeRetrieve(driver,stripeTransactionId);
       }
	   catch(SQLException e)
	   {
	      e.getMessage();
	   }
	   catch(Exception e)
	   {
		   //System.out.println("Exception occured in MPHandler's getDetails() :"+e.getMessage());
	   }
	   finally
	   {    
		   try {
			   rs.close();
		   } 
		   catch (SQLException e1)
		   {
			   e1.printStackTrace();
		   }
		   try {
			   stmt.close();
		   }
		   catch (SQLException e) 
		   {
			   e.printStackTrace();
		   }
	       try {
	    	   newCon.close();
	       }
	       catch (SQLException e) 
	       {
	    	   e.printStackTrace();
	       }
	    }
	  return getRecordsnew;
	}
}
	
	

