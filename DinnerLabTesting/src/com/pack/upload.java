package com.pack;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public upload() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        if(ServletFileUpload.isMultipartContent(request)){
		            try {
		                List <FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		                for(FileItem item : multiparts){
		                    if(!item.isFormField()){
		                        String name = new File(item.getName()).getName();
		                        item.write( new File("D:/AutomationDashboard/SigmaAutomation/src/com/upload/external/" + File.separator + name));
		                    }
		                }
		               //File uploaded successfully
		               request.setAttribute("message", "File Uploaded Successfully");
		            } catch (Exception ex) {
		               request.setAttribute("message", "File Upload Failed due to " + ex);
		            }    
		            
		           

		    	}
		        else{
		
		            request.setAttribute("message","No File found");
		        }
		        request.getRequestDispatcher("/result.jsp").forward(request, response);
		
		    }
 
 
}
