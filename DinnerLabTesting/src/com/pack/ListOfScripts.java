package com.pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListOfScripts
 */
// @WebServlet("/ListOfScripts")
public class ListOfScripts extends HttpServlet {
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	String groupname=null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {

		String i = request.getParameter("checklist");
		 System.out.println("Result of check list is " + i);
		 String pname = request.getParameter("pnameList");
		 System.out.println("Result of check list is " + pname);
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(i.split(",")));
		
		System.out.println("myList==="+myList.size()); 
		  connection = Database.getConnection();
		  try { statement =connection.createStatement(); 
		  } 
		  catch (SQLException e) { 
			  
			  } 
		  String sql = "select  * from script s, project p WHERE s.ProjectId=p.ProjectId GROUP BY ProjectName"; 
		  try {
		 resultSet = statement.executeQuery(sql);
		 System.out.println("List before::" +resultSet);
	      //printRs(resultSet);
		 
		 int count = 0;
		  groupname = "(";
		 while (resultSet.next()) 
		 {
			 for(  String we:myList )
				 
			 {
				 System.out.println("soundarya need we value:::"+ we);
				 if( count==Integer.parseInt(we) )
				 {
					 String k= resultSet.getString("ProjectId");
					 System.out.println("k:::"+ k);
					 groupname += " \""+k +"\" ,";
				 }
			 }
			 count++;
		// List<String> myList1 = new ArrayList<String>(Arrays.asList(k.split(",")));
			// System.out.println("Listing the count from DB "+ myList1.size());  
			 
		  }
		 groupname +=" \"\")";
		 System.out.println("groupname::"+groupname);
	      //printRs(resultSet);
	      

		 String sql1 = "Select * from script  WHERE ProjectId in"+ groupname+" " ;
		 System.out.println( "sql1::"+ sql1);
		//int resultSet1 = statement.executeUpdate(sql1);
				 
		 //System.out.println( "cresultSet1::"+ resultSet1);
		 
		 } catch (SQLException e)
		  { // TODO Auto-generated catch block
		  e.printStackTrace(); 
		  } try {
			  connection.close();
		  }
		  catch
		  (SQLException e) 
		  { 
			  // TODO Auto-generated catch block
			  
		  e.printStackTrace(); 
		  }

		PrintWriter out = response.getWriter();

		System.out.println("Result of check list  is" + i);

		
		request.setAttribute("ProjectId",groupname);
		
		request.setAttribute("PId",i);
		request.setAttribute("Pname",pname);
		System.out.println("pname>>"+pname);
		
		request.getRequestDispatcher("clienthome.jsp").forward(request, response);
		
		//response.sendRedirect("ProjectScript.jsp");
		

	}

}
