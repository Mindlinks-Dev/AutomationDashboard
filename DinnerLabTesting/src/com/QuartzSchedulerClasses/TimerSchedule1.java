package com.QuartzSchedulerClasses;



import java.io.IOException;









import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pack.Database;


/**
 * Servlet implementation class TimerSchedule1
 */
//@WebServlet("/TimerSchedule1")
public class TimerSchedule1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public static String Time;
    public TimerSchedule1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @return 
	 * @return 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	

    		
    		
    	}
    	
	   
	
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String seconds= request.getParameter("t1");
		String minutes= request.getParameter("t2");
		String hours= request.getParameter("t3");
	    
		System.out.println("Inside TimeScheduleclass do get::"+seconds);
		System.out.println("Inside TimeScheduleclass do get::"+minutes);
		System.out.println("Inside TimeScheduleclass do get::"+hours);
		
		   String sql = "insert into scheduletime(Seconds,Minutes,Hours) values('" +seconds + "','" + minutes + "','" + hours + "')";
           
           
           Database x = new Database();
            x.QueryExecuter(sql);
            
            response.sendRedirect("TimerSuccessMsg.jsp");

	}

}
