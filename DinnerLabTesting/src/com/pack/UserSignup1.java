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
@WebServlet("/UserSignup1")
public class UserSignup1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignup1() {
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
		
	    
		System.out.println("Inside UserSignup1 do get::"+name);
		System.out.println("Inside UserSignup1 do get::"+email);
		System.out.println("Inside UserSignup1 do get::"+password);
		System.out.println("Inside UserSignup1 do get::"+confirmpassword);
		
		
		//code to write all fields in the signup table in sample1 database
		SignupAuthenticate.validateUser(name, email, password);
		//LoginAuthenticate.validateUser(name, email, password);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("SignupSuccessMsg.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
