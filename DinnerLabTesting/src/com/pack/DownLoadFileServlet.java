package com.pack;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownLoadFileServlet
 */
public class DownLoadFileServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 //String fileUrl=request.getParameter("id");
            // System.out.println("inside servlet path "+fileUrl);
      // reads input file from an absolute path
      /*Properties prop = new Properties();
		InputStream readfile = new FileInputStream("E:\\DinnerLab_WorkSpace\\DinnerLabAutomationDashboard\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DinnerLabTesting\\WEB-INF\\lib\\config.properties");
	       prop.load(readfile);
	       String mypath=prop.getProperty("INPUTFILE_DOWNLOAD_LOCATION");*/
	           //System.out.println("FIle path to Download Inside a servlet "+mypath);
	       //String filePath = mypath+"\\Data.xls";
	       
	        String fileUrl=request.getParameter("id");
	        System.out.println("INside doGet of DownLoadFileServlet fileUrl is:"+fileUrl);
	     //  String mypath=AutomateLoader.config.getProperty("INPUTFILE_DOWNLOAD_LOCATION");
	       //String mypath=AutomateLoader.config.getProperty("OUTPUT_REPORT_ARCHIVE_LOCATION");
	        
	      String mypath=AutomateLoader.config.getProperty("OUTPUT_REPORT_ARCHIVE_LOCATION");
	        System.out.println("mypath Path is :"+mypath);
	       String filePath=mypath+fileUrl;
	       //String filePath=mypath+"";
       System.out.println("Full Path is :"+filePath);
      File downloadFile = new File(filePath); 
      FileInputStream inStream = new FileInputStream(downloadFile);
       
      // if you want to use a relative path to context root:
      String relativePath = getServletContext().getRealPath("");
      //System.out.println("relativePath = " + relativePath);
       
      // obtains ServletContext
      ServletContext context = getServletContext();
       
      // gets MIME type of the file
      String mimeType = context.getMimeType(filePath);
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
  }
		
// TODO Auto-generated method stub
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		          
		         
   }


}