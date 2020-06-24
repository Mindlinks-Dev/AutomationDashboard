package com.pack;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserLoginAction
 */
public class UserLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String FirstName= new String("");	
	public static String LastName= new String("");	
	public static String EmailId=new String("");
	String encypass=null;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		// TODO Auto-generated method stub
		
	       //PrintWriter out = response.getWriter();
		String uname=request.getParameter("name");
		String upass=request.getParameter("pass");
		//String email =request.getParameter("EmailID");
		//EmaiService.userEmail = email;
		
		System.out.println("Entered Inside UserLoginAction:"+uname);
		System.out.println("Entered Inside UserLoginAction:"+upass);
		//System.out.println("Entered Email Inside UserLoginAction:"+email);
		//System.out.println("Entered Email Inside UserLoginAction  after assigning to class:"+EmaiService.userEmail );
		PasswordService.getPassword(upass);
		
		PasswordService ps = new PasswordService();
	    try{
		encypass = ps.encrypt(upass);
		//System.out.println("encypass is ::"+encypass);
		}
	    catch(Exception e){
	     }
		if(upass!=null || upass!="")
		{
		//System.out.println("Welcome user" +uname);
        // System.out.println("username is   "+uname+"      password   is    "+upass);
		if(Authenticate.validateUser(uname,encypass))
        {
			//System.out.println("Welcome user" +uname);
			request.getSession().setAttribute("UserName", uname);
			request.getSession().setAttribute("FirstName", FirstName);
			request.getSession().setAttribute("LastName",LastName );
			request.getSession().setAttribute("EmailAddress", EmailId);
			//System.out.println("Email Address to Send Email inside UserLoginAction java file is :"+EmailId);
			response.sendRedirect("clienthome.jsp");
			
			
           
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
	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
		
		
	
	}

}
