package com.QuartzSchedulerClasses;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class JavaMysqlSelectExample {
	
	 public static void main(String args[])
	 {
	 
		 ResultStore();
	 
	 }
	 
	 public static String ResultStore()
		{
		
		 String exp="";
		 try
		    {
		      //create our mysql database connection
		      String myDriver = "com.mysql.jdbc.Driver";
		      String myUrl = "jdbc:mysql://localhost:3306/sample1";
		      Class.forName(myDriver);
		      Connection conn = DriverManager.getConnection(myUrl, "root", "");
			  System.out.println(conn);
			 

		      
		      // our SQL SELECT query. 
		      // if you only need a few columns, specify them by name instead of using "*"
		      String query = "SELECT * FROM scheduletime limit 1";

		      // create the java statement
		      Statement st = conn.createStatement();
		      
		      System.out.println("st::"+st);
		      
		      // execute the query, and get a java resultset
		      ResultSet rs = st.executeQuery(query);
		      //exp = rs;
		      System.out.println(exp);
		      // iterate through the java resultset
		      while (rs.next())
		      {
		    	  
	            String  Seconds = rs.getString(1);
		    	 System.out.println(Seconds);
		   
		    	String Minutes = rs.getString(2);
		    	 System.out.println(Minutes);
		        String Hours = rs.getString(3);
		    	 System.out.println(Hours);
		    	  
		        //print the results
		        System.out.format("%s, %s, %s\n", Seconds, Minutes, Hours);
		      
		        
	   exp= Seconds+" "+Minutes+" "+Hours+" "+"* * ?";
		        System.out.println(exp);
		 
		      }
		      st.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		 
		return  exp;
		 
		
		 }
}
