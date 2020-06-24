package com.pack;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserSignup1
 */
@WebServlet("/UserLoginPage")
public class UserLoginPage extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name= request.getParameter("name");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		String confirmpassword= request.getParameter("confirmpassword");
		
	    
		System.out.println("Inside UserLoginPage do get::"+name);
		System.out.println("Inside UserLoginPage do get::"+email);
		System.out.println("Inside UserLoginPage do get::"+password);
		System.out.println("Inside UserLoginPage do get::"+confirmpassword);
		
		
		//code to write all fields in the signup table in sample1 database
		LoginAuthenticate.validateUser(name, email, password);
		//LoginAuthenticate.validateUser(name, email, password);
		
		if(	LoginAuthenticate.validateUser(name, email, password))
        {
			//System.out.println("Welcome user" +uname);
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("password",password );

		//	response.sendRedirect("clienthome.jsp");--commented by Meghana on 01-10-2018
			
			response.sendRedirect("ProjectSetup.jsp");
        }
		
        else
        {
        	response.sendRedirect("client.jsp");
        	//response.sendRedirect("Welcome.jsp");
           //System.out.println("Username or Password incorrect");
           //RequestDispatcher rs = request.getRequestDispatcher("index.html");
           //rs.include(request, response);
        }
		

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
