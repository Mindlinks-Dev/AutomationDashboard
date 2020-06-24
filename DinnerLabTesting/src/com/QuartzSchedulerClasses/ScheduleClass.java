package com.QuartzSchedulerClasses;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;
import com.dinnerLab.util.HTMLToExcel;
import com.pack.ReportSync;
import com.pack.ScriptEntity;
import com.pack.ScriptHandler;



public class ScheduleClass extends TimerTask
{
	private String name;
	public int execId;
	public String emailId;
	public String UserName;
	public ArrayList<String> ExecutedScriptNames;
	public ArrayList<ScriptEntity> scriptlist;
    public ScheduleClass(ArrayList<ScriptEntity> scriptlist,int execId) 
    {
        this.execId = execId;
        this.scriptlist = scriptlist;
        
    }
    public void run() 
    {
    	ScriptHandler handler = new ScriptHandler();
    	try {
			handler.execute(scriptlist,execId);
			System.out.println("ran :");
    	
    	   //For Synchronization of ATU Reports
    	    System.out.println("Before calling syncobject.start:");
			ReportSync syncobject=new ReportSync();
			syncobject.start();
			System.out.println("After calling syncobject.start:");
			//step5 to call  HTMLToExcel class's copyHTMLToExcelReportActions
			
			
			
			Thread.sleep(10000);			
			System.out.println("Before calling copyHTMLToExcelReportActions:");
			HTMLToExcel report = new HTMLToExcel();
			report.copyHTMLToExcelReportActions();
			System.out.println("After calling copyHTMLToExcelReportActions:");

				Thread.sleep(5000);
          
				
		//Calling Send Email method from Scripthandler Class	
			try
			{
 			handler.sendEmail(ExecutedScriptNames,emailId,UserName);//calling ScriptHandler's sendEmail(ArrayList<String> ExecutedScriptNames) method=
			}
			catch(Exception ex)
			{
				//System.out.println("Exception occured in executescript jsp while calling ScriptHandler's sendEmail()"+ex.getMessage());
			} 
			
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
			
    }
}
