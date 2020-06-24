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
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public static String Time;
    public UploadFile() {
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
	protected void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String script_name= request.getParameter("script_name");
		String script_inputfile= request.getParameter("script_inputfile");
		String script_scenerio= request.getParameter("script_scenerio");
	    
		System.out.println("Inside TimeScheduleclass do get::"+script_name);
		System.out.println("Inside TimeScheduleclass do get::"+script_inputfile);
		System.out.println("Inside TimeScheduleclass do get::"+script_scenerio);
		
		   String sql = "insert into script(script_name,script_inputfile,script_scenerio) values('" +script_name + "','" + script_inputfile + "','" + script_scenerio + "')";
           
           
           Database x = new Database();
            x.QueryExecuter(sql);
            
            response.sendRedirect("TimerSuccessMsg.jsp");

	}

}
