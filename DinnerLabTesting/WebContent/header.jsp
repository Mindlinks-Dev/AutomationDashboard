<%@page import="com.pack.Database"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.pack.*" %>


<!DOCTYPE html>
<html>
<head>
	
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700,300" rel='stylesheet' type='text/css'>
	
<link type="text/css" rel="stylesheet" href="css/styles.css" /> 
<link type="text/css" rel="stylesheet" href="Static/salesforce-lightning-design-system.min.css" />

<script type="text/javascript">
function back()
{
	history.back();
}
</script>
</head>
<body>
<header class="header-bg">
<div class="logo">
<a><i class="fa fa-sellsy" aria-hidden="true"></i> SIGMA</a>
</div>
<div class="logout">
<a id="logout" href="client.jsp" onclick="return setSessionOff();" title="Logout"><img src="Images/logged-user.png"/></a>
</div>
</header>
<nav>
<div class="profile">
<img src="Images/loggeduser.png"/>
<p><b><%=session.getAttribute("name") %></b>
<br/>
<span>Logged User</span></p>
</div>
<ul>
<li class="active"><a href="ProjectSetup.jsp"><i class="fa fa-desktop" aria-hidden="true"></i> Dashboard</a></li>
<li><a href="ProjectSetup.jsp"><i class="fa fa-file-text" aria-hidden="true"></i> Project Setup</a></li>
<li><a></a></li>
<li><a></a></li>
</ul>
</nav>

<div class="welcome">
<div class="slds-grid">
<div class="slds-col slds-col-1">
<a class="back" title="Back" onclick="back()"><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i></a>
</div>
<div class="slds-col slds-col-2">
<p>Welcome to Sigma</p>
 <p class="small"><b>Automation Portal</b></p> 
</div>
</div>
 
 
<!-- <img src="Images/Welcome.png"/> -->
</div>


</body>
</html>
