<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="com.pack.*" %>
    <%@page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload File</title>

</head>
<script>
function file_upload()
	{
	if(document.getElementById('word').value!="")
    {   return true;
	}
	else
	{
		alert("Please upload your file");
		//document.file.word.focus();
		return false;
	} 
 }
 
 function back()
 {
	 //alert('test');
	 history.back();
 }
</script>
<body>
<jsp:include page="header.jsp" />
<div class="container">
<h2 class="slds-card__header-title purple">
         <a href="javascript:void(0);" class="slds-card__header-link slds-truncate">
           <span><i class="fa fa-upload" aria-hidden="true"></i></i> UPLOAD INPUT FILE</span>
         </a>
</h2>
<br/>
<div class="file-upload"> 
<form action="UpLoadServlet" method="post" enctype="multipart/form-data"  onsubmit="return file_upload();">
<input type="file" name="file" size="90" id="word"/>
<input id="upload" type="submit" value="Upload File" class="slds-button slds-button_brand"/> 
</form>
</div>
</div>
</body>
</html>
