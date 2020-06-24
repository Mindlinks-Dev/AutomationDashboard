
package com.pack;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Authenticate 
{


	  public static boolean validateUser(String username,String userpass) 
	     {
		  Connection con = null;
		   PreparedStatement ps = null;
		  //RequestDispatcher rd;
	      boolean status =false;
	      try{
	    	  
	    	 System.out.println("user Name is in Authenticate class" +username +"And PASSWORD IS:"+userpass);
	    	  con=Database.getConnection();
	    	   System.out.println("con object Authenticate class :"+con);
		         ps =con.prepareStatement("select * from user where name=?");
		         ps.setString(1,username);
		         //ps.setString(2,userpass);
		         String a="";
		         String b="";
		         ResultSet rs =ps.executeQuery();
		         
		         //st = rs.next();
		       
		         while (rs.next())
		         {
		        
		        	// System.out.println("Validating password with DB password");
		        	 a=rs.getString(1);
			         b=rs.getString(2);
			         //System.out.println("running inside authenticate.java: ");
			         System.out.println("first Name from DB:"+rs.getString(3));
			         
			         
			         
			 		UserLoginAction.FirstName=rs.getString(3);
			 		UserLoginAction.LastName=rs.getString(4);
			 		UserLoginAction.EmailId=rs.getString(6);//fetching Email Id from Database


		         }
		         
		 		if(a.equalsIgnoreCase(username) && (b.equals(userpass)))
		 		{    
		 			status=true;
		 			
		 			return status;
		 			
		 		}
		         
		         rs.close();
		      }
		      catch(Exception e)
		      {
		          e.printStackTrace();
		      }
	      finally
	      {
		     	
	    	  Database.closeConnection(con);
	      }
	         return status;                 
	  }   
	
	
	
	
}

