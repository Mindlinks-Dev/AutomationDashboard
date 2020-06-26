<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Automation Dashboard Login</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700,300" rel='stylesheet' type='text/css'>
	
<link type="text/css" rel="stylesheet" href="css/styles.css" /> 
<link type="text/css" rel="stylesheet" href="Static/salesforce-lightning-design-system.min.css" />
</head>
<script type="text/javascript">  
                         function matchpass()
                         {  
               			var name=document.getElementById("name").value;
               			//var EmailID =document.getElementById("EmailID").value; 
               			var password=document.getElementById("password").value;
               			//alert("Emailid is:"+EmailID);
               			
              			if(name == "" || name==null)
              			{  
               			alert("Please enter UserName!");
                		return false;
              			}  

             			else if(password=="" || password==null)
	         			{
	         			alert("Please enter password!");
	         			return false;
	         			}
	
            			else
	        			{
	          			//alert("UserName or Password is incorrect!");
	        			return true;
	        	        }
  						 } 


</script> 
<body>

<div class="login-page">
  		
  		<form class="text-center" style="text-align:center;">
		  		<h2>Registration Successful</h2>
		  		
				<img src="Images/Success.png" class="success_img" width="300"/>
		        <p><a class="signupBtn" href =client.jsp> Click here to login</a></p>
        
    </form>
        
</div>
</body>
</html>


