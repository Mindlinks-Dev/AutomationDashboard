<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700,300" rel='stylesheet' type='text/css'>
	
<link type="text/css" rel="stylesheet" href="css/styles.css" /> 
<link type="text/css" rel="stylesheet" href="Static/salesforce-lightning-design-system.min.css" />

</head>
<script type="text/javascript">  
                         function matchpass()
                         {  
               			var name=document.getElementById("name").value;
               			var email =document.getElementById("email").value; 
               			var password=document.getElementById("password").value;
               			var confirmpassword=document.getElementById("confirmpassword").value;
               			//alert("Emailid is:"+EmailID);
               			
              			if(name == "" || name==null)
              			{  
               			alert("Please enter UserName!");
                		return false;
              			}  

             			else if(password=="" || password==null)
	         			{
	         			alert("Please enter Password!");
	         			return false;
	         			}
	
             			else if(email=="" || email==null)
	         			{
	         			alert("Please enter EmailID!");
	         			return false;
	         			}
             			else if(confirmpassword=="" || confirmpassword==null)
	         			{
	         			alert("Please enter Confirm Password!");
	         			return false;
	         			}
             			else if(password!=confirmpassword)
             				{
             				alert("Entered password is not matching")
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

   <form action=UserSignup1 name="f" method="get">
  		<h1>Register</h1>
		<h2>Sigma Automation Dashboard </h2>
 		<label>Name</label><br/>
		<input type="text" name="name" id="name" class="logo_input" placeholder="Enter your Name"/><br/>
   
       
        <label>Email ID</label><br/>
		<input type="email" name="email" id="email" class="logo_input" placeholder="Enter your Email ID"/><br/>
		
		<label>Password</label><br/>
		<input type="password" name="password" id="password" class="logo_input" placeholder="Enter your Password"/><br/>
		
		<label>Confirm Password</label><br/>
		<input type="password" name="confirmpassword" id="confirmpassword" class="logo_input" placeholder="Confirm Password"/><br/>
		
        <input type="submit" id="submit" class="login-btn" href="#" value="Signup" onclick="return matchpass();"/>
		<p>Already have an account? <a class="signupBtn" href ="client.jsp"> Login</a></p>
       
    </form>
        
</div>
 
</body>
</html>