package com.pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class InsertScheduler
 */
@WebServlet("/InsertScheduler")
public class InsertScheduler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertScheduler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("Inside doPost of InsertScheduler:");
		String Schedule_id=request.getParameter("Schedule_id");
		String Requested_by=request.getParameter("Requested_by");
		String Status=request.getParameter("Status");
		String Start_date=request.getParameter("Start_date");

		
		System.out.println("Schedule_id:"+Schedule_id);
		System.out.println("Requested_by:"+Requested_by);
		System.out.println("Status:"+Status);
		System.out.println("Start_date:"+Start_date);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		
		System.out.println(formatter.format(date));
		
		
		java.util.Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("Start_date"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Correct Date :"+date1);
		
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try{
		connection = Database.getConnection();
		statement=connection.createStatement();

		//String sql  ="insert into schedule(Schedule_id,Requested_by,Status,Start_date)values('"+Schedule_id+"','"+Requested_by+"','"+Status+"','"+Start_date+"')" ;

		String sql  ="INSERT INTO schedule(Requested_by,STATUS,Start_date) VALUES('"+Requested_by+"','"+Status+"','"+Start_date+"')";
		System.out.println("SQL"+sql);

			int result  = statement.executeUpdate(sql);
		}
		catch (Exception e) 
		{
			System.out.println("Before starting the execution :"+e.getMessage());
		}
		
	}

}
