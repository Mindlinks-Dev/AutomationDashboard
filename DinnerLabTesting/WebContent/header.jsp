<%@page import="com.pack.Database"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.pack.*" %>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="fonts/ionicons/css/ionicons.min.css">
	
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700,300" rel='stylesheet' type='text/css'>
	
<link type="text/css" rel="stylesheet" href="css/styles.css" /> 

<script type="text/javascript">
function standardBack()
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
<a id="logout" class="slds-button slds-button_neutral" href="client.jsp" onclick="return setSessionOff();"><img src="Images/logged-user.png"/></a>
</div>
</header>
<nav>
<div class="profile">
<img src="Images/logged-user.png"/>
</div>
<ul>
<li class="active"><a href="#"><i class="fa fa-desktop" aria-hidden="true"></i> Dashboard</a></li>
<li><a href="#"><i class="fa fa-file-text" aria-hidden="true"></i> Project Setup</a></li>
<li><a></a></li>
<li><a></a></li>
</ul>
</nav>

<div class="welcome">
 Welcome  <%=session.getAttribute("name") %> 
</div>

<div id="clienthome_container">
<div class="header1" style="">
<h3 style="">Sigma Automation Dashboard</h3>

</div>


<div class="slds-text-align_center" id="welcome">

  <div class="slds-text-align_center welcome-text1">
  Welcome  <%=session.getAttribute("name") %> 
  </div>
  </div>
  </div>
 


</body>
</html>
