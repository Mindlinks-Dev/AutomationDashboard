/**
 * 
 */
package com.pack;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;

import sun.misc.BASE64Encoder;
//import org.myorg.SystemUnavailableException;
public final class PasswordService
{

	private static PasswordService instance;
	String UserName=null;
	static String UserPassword=null;
	String hash=null;
	Connection con=null;
	Statement stmt=null;
	
	 public static void getPassword(String Password)
	 {
		//UserPassword=Password;
		//System.out.println("UserName in password service class " +Password);
		//  public synchronized String encrypt(UserPassword);throws Exception
		
		
	 }
	
	  public PasswordService()
	  {
	   }

	  public synchronized String encrypt(String UserPassword) throws Exception
	  {
		 
	    MessageDigest md = null;
	    try
	    {
	      md = MessageDigest.getInstance("SHA"); //step 2
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	      throw new Exception("Inside first catch"+e.getMessage());
	    }
	    try
	    {
	      md.update(UserPassword.getBytes("UTF-8")); //step 3
	    }
	    catch(UnsupportedEncodingException e)
	    {
	      throw new Exception("Insdie second catch"+e.getMessage());
	    }
          
	    byte raw[] = md.digest(); //step 4
	    hash = (new BASE64Encoder()).encode(raw); //step 5
	 
	    return hash; //step 6
	  }
	  
	  
	  public static synchronized PasswordService getInstance() //step 1
	  {
	    if(instance == null)
	    {
	       instance = new PasswordService(); 
	    } 
	    return instance;
	  }
	  
	  
	  
	
}
