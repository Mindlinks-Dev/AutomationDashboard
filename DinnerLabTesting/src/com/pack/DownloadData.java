package com.pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadData extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String path = "D:/AutomationDashboard/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SigmaAutomation/UploadedInputFiles/";
	}
	

}
