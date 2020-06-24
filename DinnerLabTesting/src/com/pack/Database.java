package com.pack;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class Database {
	
	
	private static String DatabaseURL="jdbc:mysql://localhost:3306/sample";
	private static String DBUserName="root";
	private static String DBPassword="";
	
	
	/*private static String DatabaseURL="";
	private static String DBUserName="";
	private static String DBPassword="";*/
	
	
	private static void loadProperties()
	{
		
		System.out.println("Inside the loadProperties()");
		
		/*DatabaseURL=(String)AutomateLoader.config.get("DBURL");
		DBUserName=(String)AutomateLoader.config.get("DBUSERNAME");
		DBPassword=(String)AutomateLoader.config.get("DBPASSWORD");*/

		System.out.println("Inside Database the loadProperties():DatabaseURL:"+DatabaseURL+":DBUserName:"+DBUserName+":DBPassword:"+DBPassword);
		
	}
    
    public static Connection getConnection()
    {
    	
    	if(DatabaseURL.equals(""))
    	{
    		loadProperties();
    	}
    	//loading drivers for mysql
    	Connection newcon=null;
    	try
    	{
	        Class.forName("com.mysql.jdbc.Driver");

	        //System.out.println("DatabaseURL:"+DatabaseURL);
	        //System.out.println("DBUserName:"+DBUserName);
	        //System.out.println("DBPassword:"+DBPassword);
	     //creating connection with the database 
	        newcon =(Connection)DriverManager.getConnection
	                       (DatabaseURL,DBUserName,DBPassword);
	        
	        
	        System.out.println("inside  getconnection   "+newcon);
	        
    	}
    	catch(Exception ex)
    	{
    		//System.out.println("Error getting DBConnection");
    		//System.out.println(ex.getStackTrace());
    		
    	}
    	return newcon;
    }
    
    
    
    
    public void QueryExecuter (String sql){
       	try
       	{
       	Connection  conn;
       	conn = Database.getConnection();
       	System.out.println("conn::"+conn);
       	   Statement stat;
       	       stat = conn.createStatement();
       	   stat.execute(sql);
       	   stat.close();
       	   conn.close();


       	}

       	catch(Exception e)
       	{
       	    System.out.println(e);
       	}
       	}

    public static void closeConnection(Connection con)
    {
       	try
    	{  
       		
       		//System.out.println("running inside close connection method"+con); 
	    	if(null != con)
	    			con.close();
    	}
    	catch(Exception ex)
    	{
    		//System.out.println("Error getting DBConnection");
    		//System.out.println(ex.getStackTrace());
    	}
    }

}
