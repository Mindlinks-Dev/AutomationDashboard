package com.pack;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/CreateProject")
public class CreateProject extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	  public CreateProject() {
	        super();
	
	  }
	
	
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
		  System.out.println("Before starting the execution :");
		  
		  String ProjectName= request.getParameter("ProjectName");
		  String Description= request.getParameter("Description");
		  String Status = "Active";
		  String Type = "Automation";
		  String StartDate= request.getParameter("StartDate");
		  //String EndDate= request.getParameter("EndDate");
		  
		  Connection connection = null;
			//Statement statement = null;
			PreparedStatement statement = null;

			try{
			connection = Database.getConnection();
			//statement = connection.createStatement();

			/*String sqlQuery = "insert into script(script_id,script_name,script_inputfile,script_scenario,script_sequenceno,script_status,script_category_id,script_category_name,Test_Type) "
			   		+ "values('" +script_id + "','" +script_name + "','" + script_inputfile + "','" + script_scenerio + "','" + script_sequenceno + "','" + script_status + "','" + script_category_id + "','" + script_category_name + "','" + Test_Type + "')";
			   		
			   		*/
			
			String sqlQuery = "insert into project(ProjectName,Description,Status,Type,StartDate) "
			   		+ "values('" +ProjectName + "','" + Description + "','" + Status + "','" + Type + "','" + StartDate + "')";
		

			System.out.println("Before Insertion project sqlQuery is:"+sqlQuery);
			
				Statement stmt = connection.createStatement();
				int result = stmt.executeUpdate(sqlQuery);
				System.out.println("Result of project DML is:"+result);
			}
			catch (Exception e) 
			{
				System.out.println("Before starting the execution :"+e.getMessage());
				System.out.println("Inside Exception Before starting the execution :"+e.getStackTrace());
			}
		  
			response.getWriter().append("Served at: ").append(request.getContextPath());
			
			response.sendRedirect("ProjectSetup.jsp"); 
		  
		}
	
	
}
