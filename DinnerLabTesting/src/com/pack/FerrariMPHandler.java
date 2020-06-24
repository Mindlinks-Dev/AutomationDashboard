/**
 * @author: Basappa Hunsikatti
 * @Created Date :10/09/2015
 * @Updated Date :
 * @Comments This automation class will fetch data from Database and send to Stripe API as request 
 *           and Stripe API returns Member and Payment details as Response.
 */

package com.pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;

public class FerrariMPHandler
{
	static final String USER = AutomateLoader.config.getProperty("DLDevelopmentUserName");
	static final String PASS = AutomateLoader.config.getProperty("DLDevelopmentPassword");
	static final String FERRARI_URL = AutomateLoader.config.getProperty("FERRARI_DBURL");
	static final String DEVELOPMENT_URL = AutomateLoader.config.getProperty("DLDevelopmentURL");
	static final String PRODUCTION_URL = AutomateLoader.config.getProperty("DL_PRODUCTIONT_DB_URL");
	StripeHandler stripe = new StripeHandler();
	public static String merchantFee;
	public static String amount;
	public static String planId;
	public static String transactionDate;
	public String stripeTransactionId = null;
	public String stripeCustomerId = null;
	public WebDriver driver;
	
	/**
  	* Test Case for getting Database connection
  	* Input: 
  	* Output: 
    * @throws  
  	*/	
	//private Connection getConnection()
	public Connection getConnection()
	{
	  Connection conn = null;
		 try
		   {
		     Class.forName("com.mysql.jdbc.Driver");
		     conn = DriverManager.getConnection(DEVELOPMENT_URL,USER,PASS);
		   } 
		   catch(Exception ex)
		   {
			   System.out.println("error in getting DB inside FerrariMPHandler   :"+ex.getMessage());
		   }
		return conn;

	}
	/**
  	* Test Case for getting Production Database connection
  	* Input: 
  	* Output: 
    * @throws  
  	*/	
	public Connection getDLProductionDBConnection()
	{
	  Connection conn = null;
		 try
		   {
		     Class.forName("com.mysql.jdbc.Driver");
		     System.out.println("PRODUCTION_URL:"+PRODUCTION_URL);
		     System.out.println("USER:"+USER);
		     System.out.println("PASS:"+PASS);
		     conn = DriverManager.getConnection(PRODUCTION_URL,USER,PASS);
		   } 
		   catch(Exception ex)
		   {
			   System.out.println("error in getting DB inside FerrariMPHandler   :"+ex.getMessage());
		   }
		return conn;

	}
	
	/**
  	* Test Case for fetching data from Database and returns requested data
  	* Input: email
  	* Output: Map
    * @throws InvalidFormatException 
  	* @throws IOException 
  	*/	
	public Map<String, String> getDetails(String email)
	{
		  Map<String,String> getRecordsnew =  new HashMap<String,String>();
		  System.out.println("Email ID inside FerrariMpHandler java is :"+email);
		  System.out.println("Connecting to database...");
	      Connection  newCon = getConnection();
	      Statement stmt = null;
	      ResultSet rs = null;
	      System.out.println("Creating statement ...");
	     
	      //String dbQuery ="SELECT pt.transaction_id,pt.amount,pt.merchant_fee,pt.created_date,pt.transaction_date,ubs.identifier FROM payment pt INNER JOIN usersBillingSubscriptions ubs ON ubs.userID=pt.user_id INNER JOIN users_emails ue ON ue.userID=pt.user_id INNER JOIN emails eml ON ue.emailID=eml.emailID WHERE eml.email = '"+email+"' ORDER BY pt.lastmodified_date DESC LIMIT 1";
	      //String dbQuery ="SELECT ue.userID,ubs.identifier,ub.customerID FROM usersBilling ub INNER JOIN usersBillingSubscriptions ubs ON ub.userID=ubs.userID INNER JOIN users_emails ue ON ue.userID=ub.userID INNER JOIN emails eml ON ue.emailID=eml.emailID WHERE eml.email = '"+email+"' ";
	     
	      String dbQuery = "SELECT ue.userID,ubs.identifier,ub.customerID FROM usersBilling ub INNER JOIN usersBillingSubscriptions ubs ON ub.userID=ubs.userID INNER JOIN users_emails ue ON ue.userID=ub.userID INNER JOIN emails eml ON ue.emailID=eml.emailID WHERE eml.email = '"+email+"'";
	      System.out.println("dbQuery:"+dbQuery);
	      try
	      {
	       stmt = newCon.createStatement();
	       
	       rs = stmt.executeQuery(dbQuery);
	       		while(rs.next())
	       	    {
	    	
	    	   //getRecordsnew.put("Transaction_id", rs.getString("pt.transaction_id"));
	    	  // getRecordsnew.put("Amount", rs.getString("pt.amount"));
	    	   //getRecordsnew.put("Merchant_fee", rs.getString("pt.merchant_fee"));
	    	  // getRecordsnew.put("Created_date", rs.getString("pt.created_date"));
	    	   //getRecordsnew.put("Transaction_date", rs.getString("pt.transaction_date"));
	       			
	       	   System.out.println("userID :"+rs.getString("ue.userID"));		
	       	   //System.out.println("customerID :"+rs.getString("ub.customerID "));	
	       	   System.out.println("customerID :"+rs.getString("customerID"));	
	       	   System.out.println("Identifier :"+rs.getString("ubs.identifier"));	
	    	   //getRecordsnew.put("userID",rs.getString("ue.userID"));
	    	   //getRecordsnew.put("customerID",rs.getString("customerID "));
	    	   //getRecordsnew.put("Identifier",rs.getString("ubs.identifier"));
	    	   stripeCustomerId = rs.getString("customerID").toString();
	       		  System.out.println("stripeCustomerId:"+stripeCustomerId);
	       	    }
	       		
	       	 System.out.println("getRecordsnew:"+ getRecordsnew);
	       	//Calling Stripe set Up method
	       	stripe.setUp();
	       	
	       	//Calling Stripe Customer Retrieve method
	       	stripe.testCustomerRetrieve(driver,stripeCustomerId);
	       
	       	//Calling Stripe Charge Retrieve method
	       	//stripe.testChargeRetrieve(driver,stripeTransactionId);		
	      }
	      catch(Exception ex)
	       {
	    	  System.out.println("exception inside FerrariMPHandler");
	    	  ex.printStackTrace();
	       }
	       finally
	       {
	    	  
	       }
		   return getRecordsnew;
	 }
}
