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
	public int Project_Id=0;
	
	public int insertExecutionRequest(ArrayList<ScriptEntity> scripts, String Username)
	{
		
		con = Database.getConnection();
		int totalScenarios=scripts.size();
		
	      try 
	      {
	    	  System.out.println("scripts>>>>>>"+scripts);
	    	  for(int i=0;i<scripts.size();i++)
              {
			  
		   			ScriptEntity scriptentity= scripts.get(i);
	        	    Project_Id=scriptentity.Project_Id;
              }
		     String InsertQuery = "insert into execution (StartDateTime,status,executedBy,total_scenarios,Project_Id)  values(now(),'OPEN','"+LoginAuthenticate.name+"','"+totalScenarios+"','"+Project_Id+"')";

	    	  Statement stmt = con.createStatement();
	    	  numero = stmt.executeUpdate(InsertQuery, Statement.RETURN_GENERATED_KEYS);
	    	  rs1 = stmt.getGeneratedKeys();
	    	  System.out.println("rs1ScriptRESULT>>>>>"+rs1);
	    	  if (rs1.next())
	    	  {
	    	
	    	 		  execId=rs1.getInt(1);
	    	 		 System.out.println("execIdScriptRESULT>>>>>>"+execId);
	    	  }
	    	  
	    	  
	    	   for(int i=0;i<scripts.size();i++)
		              {
					  
	    		   			ScriptEntity scriptentity= scripts.get(i);
			        	    ScriptId=scriptentity.ScriptId;
			        		ScriptName=scriptentity.ScriptName;
			        	    ScriptSequenceNo=Integer.parseInt(scriptentity.ScriptSequenceNo);
			        	    Project_Id=scriptentity.Project_Id;
			        	    
			           	     insertintoexecutiondetails(execId,ScriptId,ScriptName,ScriptSequenceNo,scriptentity.containsFlow,scriptentity.FlowID,Project_Id);
			           	    
			    
			         }

	
		    }    
	          catch (SQLException e) {
		        e.printStackTrace();
	       }
		
	   
	       finally 
           {
             try
	         {
					con.close();
	         } catch (SQLException e)
	         {
				e.printStackTrace();
			}
	         
       }
	      return execId;
	}

	
	public void insertintoexecutiondetails(int execId,int ScriptId,String ScriptName,int ScriptSequenceNo, boolean containsFlow, String FlowId,int Project_Id)
	{
		Connection con1 = Database.getConnection();
		try
		{
			String InsertQuery1 = "insert into executiondetails(execid,scriptid,scriptname,seqno,FlowId,ContainsFlow,Project_Id) values('"+execId+"','"+ScriptId+"','"+ScriptName+"','"+ScriptSequenceNo+"','"+FlowId+"','"+containsFlow+"','"+Project_Id+"')";
			con1.createStatement().executeUpdate(InsertQuery1);
			
		}
			catch(Exception ex)
		{

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