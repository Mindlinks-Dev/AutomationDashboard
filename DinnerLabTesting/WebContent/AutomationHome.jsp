<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Automation Home</title>
<link type="text/css" rel="stylesheet" href="Main.css" /> 

</head>

<body>
<div id="logo">
<a id="logout" style="" href="client.jsp" onclick="return setSessionOff();">Logout</a>
</div>
<div class="header">
<h3 style="">CRMIT Automation Dashboard</h3>
</div>
<div id="container" class="Container_AutomationHome">
<div id="RegressionTesting">
<img src="Images/RegressionTesting.png"/><br/>
<a href = "clienthome.jsp?id=Regression" class="testing">Regression Testing</a>
</div> 
<div id="LoadTesting">
<img src="Images/Load Testing.png"/><br/>
<a href = "clienthome.jsp?id=Load" class="testing">Load Testing</a> 
</div>
<div id="MobileTesting">
<img src="Images/Mobiletesting.png"/><br/>
<a href = "clienthome.jsp?id=Mobile" class="testing">Mobile Testing</a> 
</div>
<div id="Salesforce_appexchange">
<img src="Images/salesforceappexchange.png"/><br/>
<a href = "clienthome.jsp?id=Salesforce_appexchange" class="testing">Salesforce Appexchange</a> 
</div>
</div>

       
<div class="footer" style=""><p>CRMIT &copy 2018</p></div>     
</body>
</html>