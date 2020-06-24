<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="com.pack.*" %>
    <%@page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="Main.css" />
<link type="text/css" rel="stylesheet" href="Static/salesforce-lightning-design-system.min.css" /> 
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
<div id="logo">
<a id="logout"  class="slds-button slds-button_neutral backbutton" onclick="back()" style="">&nbsp;</a>
<a id="logout" class="slds-button slds-button_neutral" href="client.jsp" onclick="return setSessionOff();">Logout</a>
</div>
<div id="clienthome_container">
<div class="header" style="">
<h3 style="">File Upload</h3>

</div>
</div>


<!-- <div class="header" style="">
<h3 style="">File Upload</h3>

</div> -->
<div id="upload_container">
<!-- <h3>Select a file to upload</h3> -->
<img src="Images/software_testing.png" width="40%" height="auto"/>
<form action="UpLoadServlet" method="post" enctype="multipart/form-data"  onsubmit="return file_upload();" class="upload_form">
<input type="file" name="file" size="90" id="word" class="file_btn"/>
<input id="upload" type="submit" value="Upload File" class="upload_btn" />
</form>
</div>
<!-- <div class="footer" style=""><p style="">CRMIT &copy 2018</p></div> -->
</body>
</html>
