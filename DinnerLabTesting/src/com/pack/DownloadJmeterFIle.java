package com.pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadJmeterFIle
 */
@WebServlet("/DownloadJmeterFIle")
public class DownloadJmeterFIle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadJmeterFIle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String fileUrl=request.getParameter("id");
		//System.out.println("JmeterFile PAth inside sewrvelet is :"+fileUrl);
		File downloadFile = new File(fileUrl);
	      FileInputStream inStream = new FileInputStream(downloadFile);
	       
	      // if you want to use a relative path to context root:
	      //String relativePath = getServletContext().getRealPath("");
	      //System.out.println("relativePath = " + relativePath);
	       
	      // obtains ServletContext
	      ServletContext context = getServletContext();
	       
	      // gets MIME type of the file
	      String mimeType = context.getMimeType(fileUrl);
	      if (mimeType == null) {        
	          // set to binary type if MIME mapping not found
	          mimeType = "application/octet-stream";
	      }
	      //System.out.println("MIME type: " + mimeType);
	       
	      // modifies response
	      response.setContentType(mimeType);
	      response.setContentLength((int) downloadFile.length());
	       
	      // forces download
	      String headerKey = "Content-Disposition";
	      String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
	      response.setHeader(headerKey, headerValue);
	       
	      // obtains response's output stream
	      OutputStream outStream = response.getOutputStream();
	       
	      byte[] buffer = new byte[4096];
	      int bytesRead = -1;
	       
	      while ((bytesRead = inStream.read(buffer)) != -1) {
	          outStream.write(buffer, 0, bytesRead);
	      }
	       
	      inStream.close();
	      outStream.close();     
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
