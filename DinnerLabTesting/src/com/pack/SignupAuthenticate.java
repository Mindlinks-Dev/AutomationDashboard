
package com.pack;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class SignupAuthenticate 
{


	  public static boolean validateUser(String name,String email,String password) 
	     {
		  Connection con = null;
		   PreparedStatement ps = null;
		  //RequestDispatcher rd;
	      boolean status =false;
	      try{
	    	  
	    	 System.out.println("user Name is in Authenticate class" +name +"And PASSWORD IS:"+password+"And emailid IS:"+email);
	    	  con=Database.getConnection();
	    	   System.out.println("con object Authenticate class :"+con);
	    	   
	    	   
	    	   
	    	   try 
	 	      {
	 	    	  
	 	    	  String InsertQuery = "insert into signup (name,email,password) values('"+name+"','"+email+"','"+password+"')";
	 	    	 //  int execId=ps.executeUpdate("SELECT LAST_INSERT_ID()");
	 	    	 Statement stmt = con.createStatement();
	 	    	stmt.executeUpdate(InsertQuery);
	 	    	 System.out.println("stmt inside signupauthentication :"+stmt);
	 	    	 System.out.println("InsertQuery inside signupauthentication :"+InsertQuery);
	 	    	 
	 	    	 
	 	    /*	// create the mysql insert preparedstatement
	 	    	PreparedStatement preparedStmt = con.prepareStatement(InsertQuery);
		        // ps.s
	 	    	preparedStmt.setString(1,name);
	 	    	preparedStmt.setString(2,email);
	 	    	preparedStmt.setString(3,password);
	 	    	
	 	    	
	 	       // execute the preparedstatement
	 	       preparedStmt.executeQuery();*/
	 	       
	 	       
	 	       
	 	       
	 	       
	 	      //preparedStmt.close();
	 	      }
	    	   catch(Exception e)
	    	   {
	    		   System.err.println(e.getMessage());
	    	   }
	    	   
	    	   
	    	   
	    	   
	    	   
	    	   
	    /*  ps =con.prepareStatement("select * from signup where name=?");
		        // ps.s
		         ps.setString(2,name);
		         ps.setString(3,email);
		         ps.setString(4,password);
		       // ps.setString(2,name);
		         //ps.setString(2,userpass);
		         String a="";
		         String b="";
		         String c="";
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
		         */
		         //ps.close();
		         System.out.println("after ps");
		         //rs.close();
		      }
		      catch(Exception e)
		      {
		    	  
		          e.printStackTrace();
		          System.err.println(e.getMessage());
		      }
	      finally
	      {
		     	
	    	  Database.closeConnection(con);
	    	  System.out.println("inside finally");
	      }
	         return status;                 
	  }   
	
	
	
	
}

