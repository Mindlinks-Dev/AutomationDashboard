package com.pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UpLoadServlet
 */
public class UpLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
	/*static
	{
		Properties prop = new Properties();
		InputStream readfile = null;
		
		try {
			 
			readfile = new FileInputStream("WebContent/WEB-INF/lib/config.properties");
	       prop.load(readfile);
		     
	     String inputfileUpload=prop.getProperty("INPUTFILE_UPLOAD_LOCATION"); 
	     System.out.println(" in servlet class "+prop.getProperty("INPUTFILE_UPLOAD_LOCATION")); 
	     System.out.println("after stored into the string "+inputfileUpload);
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (readfile != null) {
				try {
					readfile.close();
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		}
	}*/
   
	public UpLoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		//String destinationpath=Property.getproperty();
		//System.out.println("inside servlet "+destinationpath);
		//String foo = Property.getSetting("INPUTFILE_UPLOAD_LOCATION");
		
		
		/*recent modification-----------code :inside servlet after loading from static block from Property.java class----------------------------------
		   
		   String mypath=Property.inputfileUpload;
		  System.out.println("inside servlet after loading from static bloc from Propert.java class:"+mypath);
		  FileItem item = null;
		System.out.println("from property class:"+Property.inputfileUpload);
		*/
		 
		
		FileItem item = null;
		/*Properties prop = new Properties();
		InputStream readfile = new FileInputStream("E:\\DinnerLab_WorkSpace\\DinnerLabAutomationDashboard\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DinnerLabTesting\\WEB-INF\\lib\\config.properties");
	    prop.load(readfile);
	    String mypath=prop.getProperty("INPUTFILE_UPLOAD_LOCATION");*/
	    
	    String mypath=AutomateLoader.config.getProperty("INPUTFILE_UPLOAD_LOCATION");
	    System.out.println("Input File Path:"+mypath);
	  
		//System.out.println("destination folder to upload input file :"+mypath);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List items = upload.parseRequest(request);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    item = (FileItem) iterator.next();
                    if (!item.isFormField()) {
                        String fileName = item.getName();
                        System.out.println("filename:::"+fileName);
                        //String root = getServletContext().getRealPath("/");
                        //File path = new File(root + "/uploads");
                        File path=new File(mypath);
                        if (!path.exists()) {
                            boolean status = path.mkdirs();
                        }
                        else
                        {
                        	System.out.println("Path do exists:");
                        }
                        File uploadedFile = new File(path +"/"+fileName);
                        System.out.println("uploded filepath is:"+path);
                        System.out.println("uploadedFile is:"+uploadedFile);
                        System.out.println("uploded fileName is:"+fileName);
                        item.write(uploadedFile);
                        
              // Code to store uploaded input files in database ---> by soundarya         
                       
                        Connection connection = null;        
                        
                        PreparedStatement statement = null;
                        
                        try{
                			connection = Database.getConnection();
                        
                			String upload_file = "insert into file(file_name)"+"values('" + uploadedFile + "')";
                       
                			System.out.println("Before Insertion sqlQuery is:"+upload_file);
                			
            				Statement stmt = connection.createStatement();
            				//int result = stmt.executeUpdate(upload_file);
            				
            				//System.out.println("Result of DML is:"+result);
            				LOAD DATA INFILE 'D:/Selenium/scripts/src/com/dynamicload/Config.txt'; INTO TABLE FILE FIELDS TERMINATED BY '' LINES TERMINATED BY '\n%%\n' (test);
                        
                        }
            			catch (Exception e) 
            			{
            				System.out.println("Before starting the execution :"+e.getMessage());
            				System.out.println("Inside Exception Before starting the execution :"+e.getStackTrace());
            			}
            			
                        
                        
                        
                        
                      //  uploadedFile.
                      //  uploadedFile.
                        //upload.
                        response.sendRedirect("InputFileUploadedSuccessMsg.jsp");
                    }
                }
            } catch (FileUploadException e) {
                //e.printStackTrace();
                System.out.println("Inside FileUploadException during file upload:"+e.getStackTrace());
                
            } catch (Exception e) {
               // e.printStackTrace();
                System.out.println("Inside Exception during file upload:"+e.getStackTrace());
                System.out.println("Inside Exception during file upload message:"+e.getMessage());
                System.out.println("Inside Exception during file upload messagesss:"+e.getLocalizedMessage());
            }
          /* finally{
            	
            	if(readfile != null){
                readfile.close();
                }*/
                

            	if(item.getOutputStream() != null){
            		item.getInputStream().close();
            	item.getOutputStream().flush();
            	item.getOutputStream().close();
            	}
            	
       		 	//Reading Staging Login URL Properties File.
        	 	/*Properties prop1 = new Properties();
        	 	InputStream readFile = new FileInputStream("E:\\DinnerLab_WorkSpace\\DinnerLabAutomationDashboard\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DinnerLabTesting\\WEB-INF\\lib\\config.properties");
        	 	prop1.load(readFile);
        	    String InputExcelFilePath =prop.getProperty("INPUT_EXCEl_FILE_PATH"); 
        		  
        		System.out.println("Entering copyWorkBookActions136()"+InputExcelFilePath);
        		//	HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(TestConstants.InputExcelFilePath));
        		File file = new File(InputExcelFilePath);
        		System.out.println("File Exists:"+file.exists());*/

            }
        }
    }

