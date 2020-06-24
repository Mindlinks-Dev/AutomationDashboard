package com.QuartzSchedulerClasses;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronScheduleBuilder;




//import org.quartz.JobBuilder;
import static org.quartz.JobBuilder.*;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

	
	public class QuartzExample extends HttpServlet implements javax.servlet.ServletContextListener  {
		
		String seconds;
		String minutes;
		String hours;
		
		public void contextInitialized(ServletContext context) {
		      
			System.out.println("methos one execute");
		   }

		@Override
		public void contextDestroyed(ServletContextEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("methos one execute 2");
		}

		@Override
		public void contextInitialized(ServletContextEvent arg0) {
			
			// TODO Auto-generated method stub
			//Creating scheduler factory and scheduler
			
			
	
		String	exp1 =JavaMysqlSelectExample.ResultStore();
			
				//    String exp1="0 25 16 * * ?";
				    		
				    		
				    System.out.println(exp1);
 
			System.out.println("inside QuartzExample::"+exp1);
			
			

			
					try
					{
						System.out.println("inside Schedule");
					SchedulerFactory factory = new StdSchedulerFactory();
					Scheduler scheduler = factory.getScheduler();
				    scheduler.start();
				    JobDetail job = JobBuilder.newJob(FirstJob.class).build();
				    Trigger trigger = TriggerBuilder.newTrigger()
				                                    .startNow()
				                                    .withSchedule(
				                                         CronScheduleBuilder.cronSchedule(exp1))
				                                    .build();
				  //  job.getJobDataMap().put(FirstJob.projectName, project);
				    scheduler.scheduleJob(job, trigger);
				    System.out.println("inside Schedule trigger");
					}
					 catch (Exception e) {
					
					System.out.println("inside Schedule trigger");
					}
		}
		
		
		
		
	}
	

	