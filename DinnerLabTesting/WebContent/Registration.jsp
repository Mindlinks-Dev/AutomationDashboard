<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

<link href="TableCSSCode.css">

<link type="text/css" rel="stylesheet" href="Main.css" /> 

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
 <div id="logo">
<a id="logout"  class="slds-button slds-button_neutral backbutton" href="client.jsp" style="width:35px;height:35px;">&nbsp;</a>
<!-- <a id="logout" class="slds-button slds-button_neutral" href="client.jsp" onclick="return setSessionOff();">Logout</a> -->
</div>
<div id="clienthome_container">
<div class="header" style="">
<h3 style="">Sigma Automation Portal</h3>

</div>



<div id="container_logo" class="registerMain">

   <form action=UserSignup1 name="f" method="get">
  		<img src="Images/register.png" style="width:60%;margin-left:16%;"/><br/>
 		<label>Name</label><br/>
		<input type="text" name="name" id="name" class="logo_input" placeholder="Enter your Name"/><br/>
   
       
        <label>Email ID</label><br/>
		<input type="email" name="email" id="email" class="logo_input" placeholder="Enter your Email ID"/><br/>
		
		<label>Password</label><br/>
		<input type="password" name="password" id="password" class="logo_input" placeholder="Enter your Password"/><br/>
		
		<label>Confirm Password</label><br/>
		<input type="password" name="confirmpassword" id="confirmpassword" class="logo_input" placeholder="Confirm Password"/><br/>
		
        <input type="submit" id="submit" class="logo_submit" href="#" value="Signup" onclick="return matchpass();"/>
		
       
    </form>
        
</div>
   <!--     <div class="footer" style=""><p style="">Mindlinks Solution &copy 2018</p></div>     -->  
</body>
</html>