package com.QuartzSchedulerClasses;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.Arrays;
import org.joda.time.DateTime;

import com.pack.ScriptEntity;

public class schedule 
{
	public void scheduleThis(ArrayList<ScriptEntity> scriptlist,int execId,Date scheduledt,Date enddate,String runstatus,
			String scheudleDateTime, String scheudleEndDate) 
    {
		
		
		System.out.println("execidscheduledexecId::::"+execId);
		System.out.println("execidscheduledscriptlist::::"+scriptlist);
		System.out.println("scheduledt::::"+scheduledt);
		System.out.println("runstatus::::"+runstatus);
		System.out.println("enddate::::"+enddate);
		System.out.println("scheudleDateTime::::"+scheudleDateTime);
		System.out.println("scheudleEndDate::::"+scheudleEndDate);
		Timer timer = new Timer();
	
		
		if(new String(runstatus).equals("once"))
		{
			timer.schedule(new ScheduleClass(scriptlist,execId), scheduledt);
		}else
		{
			System.out.println("Timecompare::=");
			//System.out.println("Timer:::"+timer);
			
			
			  Date date = new Date();
		      //if(date.compareTo(enddate) < 0 || date.compareTo(enddate) == 0 )
				
			//{
			// Instantiate a Date object
			  
			  String outputStart = scheudleDateTime.substring(0, 10);
			  String outputEnd = scheudleEndDate.substring(0, 10);
			  String[] start=outputStart.split("-");
			
			  System.out.println("Test start:::"+Arrays.toString(start));
			  String[] end=outputEnd.split("-");
			  System.out.println("ending:::"+Integer.parseInt(end[2].toString()));
			  System.out.println("startIng:::"+Integer.parseInt(start[2].toString()));
			  System.out.println("diff:::"+(enddate.getTime()-scheduledt.getTime())/86400000);
			  long difference=(enddate.getTime()-scheduledt.getTime())/86400000;
			  System.out.println("Difference:::"+difference);
			  
			  System.out.println("Today date:::"+scheduledt);
			  
			  Date tomorrow=scheduledt;
		    System.out.println("Tomorrow date is:::"+tomorrow);
			for(int i=1; i<=difference; i++)	
			{
				
				 
				 if(i==1)
				 {
					 System.out.println("Entered if for today's date:::"+scheduledt);
					 timer.schedule(new ScheduleClass(scriptlist,execId), scheduledt,1000*60*60*1);
					 //timer.schedule(new ScheduleClass(scriptlist,execId), scheduledt,1000*60*60*2);
					 //timer.schedule(new ScheduleClass(scriptlist,execId), scheduledt,1000*60*60*3);
						System.out.println(scriptlist);
						System.out.println(execId); 
					 
				 }
				 else
				 {
					 tomorrow = new Date(tomorrow.getTime() + (1000 * 60 * 60 * 24));
					 System.out.println("Entered else for tomorrow date:::"+tomorrow);
					 //timer.schedule(new ScheduleClass(scriptlist,execId), scheduledt,1000*60*60*2);
					 timer.schedule(new ScheduleClass(scriptlist,execId), tomorrow,1000*60*60*24);
						System.out.println(scriptlist);
						System.out.println(execId);
				 }
				
			}
			
			//}
		      
		      
		      
			
			 
		}
		
		
		
		
    }
}

