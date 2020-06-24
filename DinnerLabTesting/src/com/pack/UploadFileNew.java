//Code to store the uploaded script details in database --> written by soundarya

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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadFileNew
 */
@WebServlet("/UploadFileNew")
public class UploadFileNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
   
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		 		
		//***********************************************************
		System.out.println("Inside post method of UploadFile servlet:");
		//int script_id = Integer.parseInt(request.getParameter("script_id"));
		String script_name= request.getParameter("script_name");
		String script_inputfile= "Test Data";
		String script_scenerio= request.getParameter("script_scenerio");
		//System.out.println("AS a Text request.getParameter(script_sequenceno):"+request.getParameter("script_sequenceno"));
		//int script_sequenceno = Integer.getInteger(request.getParameter("script_sequenceno")).intValue();
		String script_sequenceno= "1";
		
		String script_category_id= "4";

		//int script_category_id = Integer.parseInt(request.getParameter("script_category_id"));
		System.out.println("AS a Integer script_sequenceno:"+script_sequenceno);
		String script_status = "active";
		String script_category_name = "cohbaines";
		String Test_Type = "Others";
		String script_package_name = "cohbainessandbox";
		String ProjectId=request.getParameter("pid");
	    
		//System.out.println("Inside script_name do get::"+script_id);
		System.out.println("Inside script_name do get::"+script_name);
		System.out.println("Inside script_inputfile do get::"+script_inputfile);
		System.out.println("Inside script_scenerio do get::"+script_scenerio);
		System.out.println("Inside script_sequenceno do get::"+script_sequenceno);
		System.out.println("Inside script_status do get::"+script_status);
		System.out.println("Inside script_category_id do get::"+script_category_id);
		System.out.println("Inside script_category_name do get::"+script_category_name);
		System.out.println("Inside Test_Type do get::"+Test_Type);
		System.out.println("Inside script_package_name do get::"+script_package_name);
		System.out.println("Inside Project id do get::"+ProjectId);

		
		
           
		    Connection connection = null;
			//Statement statement = null;
			PreparedStatement statement = null;

			try{
			connection = Database.getConnection();
			//statement = connection.createStatement();

			/*String sqlQuery = "insert into script(script_id,script_name,script_inputfile,script_scenario,script_sequenceno,script_status,script_category_id,script_category_name,Test_Type) "
			   		+ "values('" +script_id + "','" +script_name + "','" + script_inputfile + "','" + script_scenerio + "','" + script_sequenceno + "','" + script_status + "','" + script_category_id + "','" + script_category_name + "','" + Test_Type + "')";
			   		
			   		*/
			
			String sqlQuery = "insert into script(script_name,script_inputfile,script_scenario,script_sequenceno,script_status,script_category_id,script_category_name,Test_Type,script_package_name,ProjectId) "
			   		+ "values('" +script_name + "','" + script_inputfile + "','" + script_scenerio + "','" + script_sequenceno + "','" + script_status + "','" + script_category_id + "','" + script_category_name + "','" + Test_Type + "','" + script_package_name + "','" + ProjectId + "')";
		

			System.out.println("Before Insertion sqlQuery is:"+sqlQuery);
			
				Statement stmt = connection.createStatement();
				int result = stmt.executeUpdate(sqlQuery);
				System.out.println("Result of DML is:"+result);
				/*if(result==1)
				{
					boolean finalResult = movefile(request);
					System.out.println("finalResult is:"+finalResult);
				}*/
			}
			catch (Exception e) 
			{
				System.out.println("Before starting the execution :"+e.getMessage());
				System.out.println("Inside Exception Before starting the execution :"+e.getStackTrace());
			}
			
			
			
			
			
		   
			
			response.getWriter().append("Served at: ").append(request.getContextPath());
			
			response.sendRedirect("ProjectSetup.jsp"); 
		 
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
