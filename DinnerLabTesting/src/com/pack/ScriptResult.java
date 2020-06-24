/**
 * 
 */
package com.pack;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author win
 *
 */
public class ScriptResult 
{
	public int ScriptId=0;
	public String ScriptName=null;
	public String ScriptInputFile=null;
	public String ScriptScenario=null;
	public int ScriptSequenceNo=0;
	public String ScriptStatus=null;
	public int ScriptCategoryId=0;
	public String ScriptCategoryName=null;
	Connection con=null;
	ResultSet rs1 =null;
	int numero=0;
	int execId=0;
	
	public int insertExecutionRequest(ArrayList<ScriptEntity> scripts, String Username)
	{
		//Insert into Execution table - 1 record
		//StartDateTime, Status="OPEN"
		//System.out.println("Inside insertExecutionRequest method () ");
		//Insert into Execution Details table - equals record selected on UI 
		//ScriptID, ScriptName and SeqNo - ScriptEntity, Link execid of execution table
		con = Database.getConnection();
		int totalScenarios=scripts.size();
		//System.out.println("total scenarios :"+totalScenarios);
		//System.out.println("script size is :"+totalScenarios);
		
	      try 
	      {
	    	  
	    	 // String InsertQuery = "insert into execution (StartDateTime,status,executedBy,total_scenarios)  values(now(),'OPEN','"+Username+"','"+totalScenarios+"')";
		     String InsertQuery = "insert into execution (StartDateTime,status,executedBy,total_scenarios)  values(now(),'OPEN','"+LoginAuthenticate.name+"','"+totalScenarios+"')";
 	
	    	  //  int execId=ps.executeUpdate("SELECT LAST_INSERT_ID()");

	    	 // boolean false;
	    	 /*Statement stmt = con.createStatement();
	    	 System.out.println("reference " +Statement.RETURN_GENERATED_KEYS);
	    	 InsertQuery numero = stmt.executeUpdate(InsertQuery, Statement.RETURN_GENERATED_KEYS);
	    	 System.out.println("numero    "+numero);*/
	    	  Statement stmt = con.createStatement();
	    	  //con.prepareStatement(InsertQuery, Statement.RETURN_GENERATED_KEYS);
	    	  numero = stmt.executeUpdate(InsertQuery, Statement.RETURN_GENERATED_KEYS);
              // System.out.println("printing numero value :"+numero);
	    	  rs1 = stmt.getGeneratedKeys();
	    	  if (rs1.next())
	    	  {
	    		  //System.out.println("Inside if condition of rs1.next insertExecutionRequest()");
	    	 		  execId=rs1.getInt(1);
	    	  }
	    	  //System.out.println("Execution ID inside insertExecutionRequest :"+execId);
	    	  
	    		//boolean success=false; 
	    		//= stmt.execute(InsertQuery,Statement.RETURN_GENERATED_KEYS);
	    		
	    		
	    		//ResultSet rs1=stmt.executeQuery("SELECT LAST_INSERT_ID()");
	    		//int id=rs1.getInt(1);
	    		   		
	    		    		
	    		//System.out.println("success:"+success);
				 
			/*	     if(success)
				     {
					  ResultSet rs= stmt.getGeneratedKeys();
					  System.out.println("Result set:"+rs);
					  
					  int execId=rs.getInt(1);
					  System.out.println("Execute ID:"+execId);
					  
			   
	    	  
	    	  }
	    	  */
	    	  
	    	  
	    	   for(int i=0;i<scripts.size();i++)
		              {
					   //System.out.println("script id is  printing in my own class " +scripts.get(i).ScriptId);
	    		   			ScriptEntity scriptentity= scripts.get(i);
			        	    ScriptId=scriptentity.ScriptId;
			        		ScriptName=scriptentity.ScriptName;
			        	    ScriptSequenceNo=Integer.parseInt(scriptentity.ScriptSequenceNo);
			        	    
			           	     insertintoexecutiondetails(execId,ScriptId,ScriptName,ScriptSequenceNo,scriptentity.containsFlow,scriptentity.FlowID);
			           	     //System.out.println("Flow Id inside ScriptREsult  is :"+scriptentity.FlowID);
			    
			         }

	
		    }    
	          catch (SQLException e) {
		       // TODO Auto-generated catch block
		        e.printStackTrace();
	       }
		
	   
	       finally 
           {
             try
	         {
					con.close();
	         } catch (SQLException e)
	         {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
       }
	      return execId;
	}

	
	public void insertintoexecutiondetails(int execId,int ScriptId,String ScriptName,int ScriptSequenceNo, boolean containsFlow, String FlowId)
	{
		Connection con1 = Database.getConnection();
		
		//System.out.println("inside script result .java :FLow Id are:"+FlowId+"and Contains Flow "+containsFlow);
		try
		{
			String InsertQuery1 = "insert into executiondetails(execid,scriptid,scriptname,seqno,FlowId,ContainsFlow) values('"+execId+"','"+ScriptId+"','"+ScriptName+"','"+ScriptSequenceNo+"','"+FlowId+"','"+containsFlow+"')";
			con1.createStatement().executeUpdate(InsertQuery1);
			
		}
			catch(Exception ex)
		{
			//System.out.println("Exception Occured inside ScriptResult :"+ex.getMessage());
		}
		finally
		{
			try {
				con1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	 
}