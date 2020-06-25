<!-- Code to upload script --- by soundarya -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> File Upload</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<script type="text/javascript">
$(document).ready(function(){
	//alert('fieldName');
	//var fileName = fi.getName();
	//document.getElementById('myInput').value=fileName();
});
</script>

<body onload="initialize();">
<%
   File file ;
   File file2;
   //File file2;

   int maxFileSize = 5000 * 1024;
   int maxMemSize = 5000 * 1024;
  String filePath = "D:\\AutomationDashboard\\SigmaAutomation\\WebContent\\WEB-INF\\classes\\cohbainessandbox\\";
  String filepath2 = "D:\\AutomationDashboard\\SigmaAutomation\\src\\cohbainessandbox\\";
 
   String contentType = request.getContentType();
   Object Projectname = session.getAttribute("ProjectId");
    HttpSession session1=request.getSession();
	String proId = (String) session1.getAttribute("ProjectId");
	String proname = (String) session1.getAttribute("ProjectName");
	System.out.println("proId>>>>>>>"+proId);
	System.out.println("proname>>>>>>>"+proname);
   System.out.println("Projectname="+Projectname);
   if ((contentType.indexOf("multipart/form-data") >= 0)) {
 
      DiskFileItemFactory factory = new DiskFileItemFactory();
      factory.setSizeThreshold(maxMemSize);
      factory.setRepository(new File("c:\\temp"));
      ServletFileUpload upload = new ServletFileUpload(factory);
      upload.setSizeMax( maxFileSize );
      try{ 
         List fileItems = upload.parseRequest(request);
         Iterator i = fileItems.iterator();
         out.println("<html>");
         out.println("<body>");
         while ( i.hasNext () ) 
         {
            FileItem fi = (FileItem)i.next();
            if ( !fi.isFormField () )  
            {
                String fieldName = fi.getFieldName();
                String fileName = fi.getName();
                boolean isInMemory = fi.isInMemory();
                long sizeInBytes = fi.getSize();
                String name = new File(fi.getName()).getName();
                System.out.println("name:::"+name);
             	file = new File( filePath + File.separator +name) ;
          	 	fi.write( file ) ;
                file2 = new File( filepath2 + File.separator +name);
                fi.write(file2);
                
                
                
              //-------------------------loading class file to jvm  ------------------------------
             /*    
              try{
                	Class cls = Class.forName("AccountTestpage");
                	ClassLoader cLoader =  cls.getClassLoader();
                	Class cls2 = Class.forName("java.lang.Thread", true, cLoader);
                	System.out.println("Class = " + cls.getName());
                    System.out.println("Class = " + cls2.getName()); 
                	
                }catch(ClassNotFoundException e){
                	System.out.println(e.toString());
                } 
               */
              //create an object instance
              
              
                
                //request.getRequestDispatcher("UploadFileNew").forward(request, response);
                //out.println("Uploaded Filename: " + filePath + fileName + "<br>");
            }
            else {
                //here...
                String fieldname = fi.getFieldName();
                String fieldvalue = fi.getString();
                if (fieldname.equals("script_name"))
                {
                	System.out.println("Inside fieldname.equals(script_name):"+fieldvalue);
                	
                } else if (fieldname.equals("upload_wall_gallery")) {
                    //next logic goes here...
                }
            }
            
            
            
         }
         out.println("</body>");
         out.println("</html>");
        
         
      }catch(Exception ex) {
         System.out.println(ex);
      }
   }else{
      out.println("<html>");
      out.println("<body>");
      out.println("<p>No file uploaded</p>"); 
      out.println("</body>");
      out.println("</html>");
   }
   
%>
<div class="slds-backdrop slds-backdrop_open"></div>
<section role="dialog" tabindex="-1" aria-labelledby="modal-heading-01" aria-modal="true" aria-describedby="modal-content-id-1" class="slds-modal slds-fade-in-open">
    
    <div class="slds-modal__container">    
     
            <div class="slds-modal__content" id="modal-content-id-1">
      
<form action="UploadFileNew" method="post" >
      <header class="slds-modal__header">       
        <h2 id="modal-heading-01" class="slds-text-heading_medium slds-hyphenate">Uploaded File</h2>
      </header>
      <div class="slds-modal__content slds-p-around_medium" id="modal-content-id-1">
      
      
       <input  type="hidden" id="pid" type="text" name="pid" value="<%=session.getAttribute("ProjectId")%>">
       <!--  <input type="file" id="file" onchange="GetFileSizeNameAndType()"/><br/><br/>
        
         <div class="slds-form slds-form_horizontal">
		  <div class="slds-form-element">
			<label class="slds-form-element__label" for="input-id-01">Script id</label>
			<div class="slds-form-element__control">
			  <input type="text" id="myInput7" name="script_id"/>
			</div>
		  </div>-->

		<div class="slds-form slds-form_horizontal">
		  <div class="slds-form-element">
			<label class="slds-form-element__label" for="input-id-01">Script name</label>
			<div class="slds-form-element__control">
			  <input type="text" id="myInput" name="script_name" title="Script name should be same as project name"/>
			  </div>
		  </div>
		  
		 <!--   <div class="slds-form-element">
			<label class="slds-form-element__label" for="input-id-01">Script Inputfile</label>
			<div class="slds-form-element__control">
			  <input type="text" id="myInput1" name="script_inputfile" />
			</div>
		  </div>-->
		  
		  <div class="slds-form-element">
			<label class="slds-form-element__label" for="input-id-01">Script scenario</label>
			<div class="slds-form-element__control">
			  <input type="text" id="myInput2" name="script_scenerio"/>
			</div>
		  </div>
		  
		 <!--  <div class="slds-form-element">
			<label class="slds-form-element__label" for="input-id-01">Script sequenceno</label>
			<div class="slds-form-element__control">
			  <input type="text" id="myInput3" name="script_sequenceno"/>
			</div>
		  </div>
		  
		  <div class="slds-form-element">
			<label class="slds-form-element__label" for="input-id-01">Script status</label>
			<div class="slds-form-element__control">
			  <input type="text" id="myInput4" name="script_status"/>
			</div>
		  </div>
		  
		  <div class="slds-form-element">
			<label class="slds-form-element__label" for="input-id-01">Script categoryid</label>
			<div class="slds-form-element__control">
			  <input type="text" id="myInput5" name="script_category_id"/>
			</div>
		  </div>
		  
		  <div class="slds-form-element">
			<label class="slds-form-element__label" for="input-id-01">Script categoryname</label>
			<div class="slds-form-element__control">
			  <input type="text" id="myInput6" name="script_category_name"/>
			</div>
		  </div>
		  
		  <div class="slds-form-element">
			<label class="slds-form-element__label" for="input-id-01">Test type</label>
			<div class="slds-form-element__control">
			  <input type="text" id="myInput6" name="Test_Type"/>
			</div>
		  </div> -->
		  
		  
		</div>
    
	
      </div>
	  
      <footer class="slds-modal__footer">
       <input class="slds-button slds-button_brand" type="submit" id="submit1" value="Save" />
        <button class="slds-button slds-button_neutral" onclick="cancelUploadPopup()">Cancel</button>
        
        <!-- <button class="slds-button slds-button_brand" onclick="SaveUpload()" type="submit">Save</button> -->
       
      </footer> 
      </form>
      </div>
</body>
</html>