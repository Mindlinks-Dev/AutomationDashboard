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
import javax.servlet.http.HttpSession;
@WebServlet("/UpdateProject")
public class UpdateProject extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	  public UpdateProject() {
	        super();
	
	  }
	
	
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
		  System.out.println("Before starting the execution in update");
		  
		  String ProjectName= request.getParameter("ProjectName");
		  String Description= request.getParameter("Description");
		  String Status = request.getParameter("Status");
		  String Type = "Automation";
		  String StartDate= request.getParameter("StartDate");
		  //String EndDate= request.getParameter("EndDate");
		  System.out.println("before entering "+ProjectName+Description+Type+StartDate);
		  Connection connection = null;
			//Statement statement = null;
			PreparedStatement statement = null;

			try{
				HttpSession session=request.getSession();
			String proId = (String) session.getAttribute("ProjectId");
			System.out.println("proId in clienthome2222="+proId);
			connection = Database.getConnection();
			//statement = connection.createStatement();

			/*String sqlQuery = "insert into script(script_id,script_name,script_inputfile,script_scenario,script_sequenceno,script_status,script_category_id,script_category_name,Test_Type) "
			   		+ "values('" +script_id + "','" +script_name + "','" + script_inputfile + "','" + script_scenerio + "','" + script_sequenceno + "','" + script_status + "','" + script_category_id + "','" + script_category_name + "','" + Test_Type + "')";
			   		
			   		*/
			String sqlQuery = "update project set ProjectName=?,Description=?,Status=?,Type=?,StartDate=? where ProjectId=?";
		    boolean rowUpdated = false;

			System.out.println("After entering project sqlQuery is:"+sqlQuery);
			System.out.println("Result of project DML is:"+ProjectName+Description+Status+Type+StartDate);
			PreparedStatement stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, ProjectName);
			stmt.setString(2, Description);
			stmt.setString(3, Status);
			stmt.setString(4, Type);
			stmt.setString(5, StartDate);
			stmt.setString(6,proId);

            rowUpdated = stmt.executeUpdate() > 0;
				
				System.out.println("Result of project DML is:"+stmt);
				System.out.println("statsu:"+rowUpdated);
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
