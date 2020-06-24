
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.pack.*" %>
<%@page import="com.dinnerLab.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="Main.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload File</title>
<style>
.output {
    font: 1rem 'Fira Sans', sans-serif;
}

legend {
    background-color: #000;
    color: #fff;
    padding: 3px 6px;
}

.control {
    margin: 1rem 0;
}

label {
    display: inline-block;
    text-align: right;
    font-size: .8rem;
    width: 20%;
}

.hours {
    font-size: .7em;
    color: #999;
}


</style>
</head>

<script type="text/javascript">  
function matchpass()
{  
	var seconds=document.getElementById("t1").value;
	var Minutes=document.getElementById("t2").value; 
	var hours=document.getElementById("t3").value; 
	/*alert("seconds"+seconds);
	alert("Minutes"+Minutes);
	*/

return;

}
	 


</script> 

<body>
<div id="logo">
<a id="logout"  class="slds-button slds-button_neutral backbutton" href="clienthome.jsp" style="">&nbsp;</a>
<a id="logout" style="" href="client.jsp" onclick="return setSessionOff();">Logout</a>
</div>
<div class="header" style="">
<h3 style="">Time Scheduler</h3>

</div>
<div id="container" class="container_executescript">

<%	

	String [] SelectedIds=request.getParameterValues("scriptlist");
	System.out.println("SelectedIds :"+SelectedIds);
%>
	  	 
</div>	
<div id="upload_container">
<fieldset>
    <legend>Schedule Time</legend>
<!-- <form method="get" action="TimerSchedule1"> -->	
    <form method="post" action="TimerSchedule1">
	    <div class="control">
			<label for="appt-time">Time:</label>
			<input type="time" name="t1" placeholder="ss" id="t1" min="9:00" max="18:00" required />
			
			<input type="time" name="t2" placeholder="mm" id="t2" min="9:00" max="18:00" required />
			
			<input placeholder="HH" type="time" name="t3" id="t3" min="9:00" max="18:00" required />
				    
	        <!--<span class="hours">Office hours: 9AM to 6PM</span>-->
			<br/>
			 
	    </div>
	
<input type="submit" value="Submit" name="b1" href="#" onclick="return matchpass();" />
</form> 

</fieldset>

</div>
<div class="footer" style=""><p style="">Mindlinks Solution &copy 2016</p></div>
</body>
</html>
