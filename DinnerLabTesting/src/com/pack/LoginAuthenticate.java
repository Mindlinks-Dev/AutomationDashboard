
package com.pack;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginAuthenticate 
{

	public static String email=new String("");
	public static String name=new String("");
	public static int userSignupId = 0;
	
	  public static boolean validateUser(String name,String email,String password) 
	     {
		  Connection con = null;
		   PreparedStatement ps = null;
		   Statement stmt =null;
		   
		  //RequestDispatcher rd;
	      boolean status =false;
	      try{
	    	  
	    	 System.out.println("user Name is in Authenticate class" +name +"And PASSWORD IS:"+password);
	    	  con=Database.getConnection();
	    	   System.out.println("con object Authenticate class :"+con);
	    	   

	    	   
		         //ps =con.prepareStatement("select * from signup where name=?");
		         //ps =con.prepareStatement("select * from signup");
		         stmt = con.createStatement();
		         System.out.println("running inside authenticate.java ps : "+ ps );
		         //ps.setString(1,name);
		         //ps.setString(2,userpass);
		         String a="";
		         String b="";
		         //ResultSet rs =ps.executeQuery();
		         String q="select * from signup";
		         ResultSet rs =stmt.executeQuery(q);
		         //System.out.println("running inside authenticate.java a : "+rs.getString(2));
		         
		         //st = rs.next();
		       
		         while (rs.next())
		         {
		        
		        	System.out.println("Validating password with DB password");
		        	 a=rs.getString(1);
			         b=rs.getString(2);
			         System.out.println("running inside class LoginAuthenticate.java a : "+ a);
			         System.out.println("running inside class LoginAuthenticate.java b : "+ b);
			         System.out.println("running inside class LoginAuthenticate.java c : "+rs.getString(3));
			         //System.out.println("first Name from DB:"+rs.getString(3));
			         
			         
			         if(rs.getString(2).equalsIgnoreCase(name) && (rs.getString(4).equals(password)))
			         {
			        	 LoginAuthenticate.email=rs.getString(3);
			        	 LoginAuthenticate.name=rs.getString(2);
			        	 LoginAuthenticate.userSignupId=rs.getInt("SignupID");
			        	 //fetching Email Id from Database
			        		status = true;
			        		return status;
			        		 
			         }
			 		//UserLoginAction.FirstName=rs.getString(3);
			 		//UserLoginAction.LastName=rs.getString(4);

			        

		         }
		         
		 		/*if(a.equalsIgnoreCase(name) && (b.equals(password)))
		 		{    
		 			status=true;
		 			
		 			return status;
		 			
		 		}*/
		         
		         //rs.close();
		      }
		      catch(Exception e)
		      {
		          e.printStackTrace();
		          System.out.println("inside catch::"+ e.getMessage());
		      }
	      finally
	      {
		     	
	    	  Database.closeConnection(con);
	      }
	         return status;                 
	  }   
	
	
	
	
}

