<%@page import="com.pack.Database"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.pack.*" %>

<!-- Html  code--->
<!DOCTYPE html>
<html>
<head>
<style>
	th{font-size:14px !important;font-weight:bold !important;}
	h2{font-size:20px !important;}
	#test{font-weight:bold;}
</style>
<link type="text/css" rel="stylesheet" href="Static/salesforce-lightning-design-system.min.css" /> 
</head>
<body>
<article class="slds-card" style="height:100vh;font-size:17px;">
  <div class="slds-card__header slds-grid">
    <header class="slds-media slds-media_center slds-has-flexi-truncate">
	<img src="Images/cloud.png" width="40px"/>
      <div class="slds-media__figure">
        <span class="slds-icon_container slds-icon-standard-account" title="Project Names">
          
          <span class="slds-assistive-text">Project Names</span>
        </span>
      </div>
      <div class="slds-media__body">
        <h2 class="slds-card__header-title">
          <a href="javascript:void(0);" class="slds-card__header-link slds-truncate" title="Accounts">
            <span>Project Names</span>
          </a>
        </h2>
      </div>
      <div class="slds-no-flex" style="display:inline-flex;width:12%;">
	  		<button class="slds-button slds-button_brand slds-m-right_x-small" id="Upload" onclick="uploadPopup();">Upload</button>
		<form action="ListOfScripts" >
	    <input  type="hidden" id="checklist" type="text" name="checklist" > 
		
		<button class="slds-button slds-button_brand" id="test" type="submit" >OK</button>		
		
		</form>
      </div>
    </header>
  </div>
  <div class="slds-card__body slds-card__body_inner1">
  <table class="slds-table slds-table_cell-buffer slds-table_bordered">
  <thead>
    <tr class="slds-line-height_reset">
      <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="Project Names">Project Lists</div>
      </th>
      <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="Select">Select</div>
      </th>      
    </tr>
  </thead>
  <tbody>      
	<%


Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
try{
connection = Database.getConnection();
statement=connection.createStatement();
String sql ="select  * from script GROUP BY script_category_name";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr >
<td id="fn"><%=resultSet.getString("script_category_name") %></td>  
<%-- <td><%=resultSet.getString("script_name") %></td>
<td><%=resultSet.getString("script_scenario") %></td>
<td> <%=resultSet.getString("script_status") %></td> --%>

<td> <input type="checkbox" name=<%=resultSet.getString("script_category_name") %> value="execute"  > </td>

</tr>


<%
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
  </tbody>
</table>
  
  </div>

</article>




<!-- code to validate whether the check box is checked -->

<div id="uploadPopup" style="display:none;">
  <section role="dialog" tabindex="-1" aria-labelledby="modal-heading-01" aria-modal="true" aria-describedby="modal-content-id-1" class="slds-modal slds-fade-in-open">
    <div class="slds-modal__container">
     <form action="UploadFile" method="post">
      <header class="slds-modal__header">       
        <h2 id="modal-heading-01" class="slds-text-heading_medium slds-hyphenate">Upload File</h2>
      </header>
      <div class="slds-modal__content slds-p-around_medium" id="modal-content-id-1">
        <input type="file" id="file" onchange="GetFileSizeNameAndType()"/><br/><br/>

		<div class="slds-form slds-form_horizontal">
		  <div class="slds-form-element">
			<label class="slds-form-element__label" for="input-id-01">Label 1</label>
			<div class="slds-form-element__control">
			  <input type="text" id="myInput" name="t1"/>
			</div>
		  </div>
		  
		  <div class="slds-form-element">
			<label class="slds-form-element__label" for="input-id-01">Label 2</label>
			<div class="slds-form-element__control">
			  <input type="text" id="myInput1" name="t2"/>
			</div>
		  </div>
		  
		  <div class="slds-form-element">
			<label class="slds-form-element__label" for="input-id-01">Label 3</label>
			<div class="slds-form-element__control">
			  <input type="text" id="myInput2" name="t3"/>
			</div>
		  </div>
		  
		  <div class="slds-form-element">
			<label class="slds-form-element__label" for="input-id-01">Label 3</label>
			<div class="slds-form-element__control">
			  <input type="text" id="myInput3"/>
			</div>
		  </div>
		  
		</div>
    
	
      </div>
	  
      <footer class="slds-modal__footer">
        <button class="slds-button slds-button_neutral" onclick="cancelUploadPopup()">Cancel</button>
        <button class="slds-button slds-button_brand" type="submit">Save</button>
      </footer> 
      </form>
    </div>
  </section>
  <div class="slds-backdrop slds-backdrop_open"></div>
</div>

 <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
  <script>
function uploadPopup()
{
//alert('inside');
	 $("#uploadPopup").css("display", "block");
}
function cancelUploadPopup()
{
	$("#uploadPopup").css("display", "none");
}
function SaveUpload()
{
	$("#uploadPopup").css("display", "none");
	alert('before');
	document.getElementById("file").value = "";
	document.getElementById("myInput").value = "";
	 
	 alert('after');
}
    function GetFileSizeNameAndType()

    {

      var fi = document.getElementById('file');

      var totalFileSize = 0;

      if (fi.files.length > 0)

      {

        for (var i = 0; i <= fi.files.length - 1; i++)

        { 
          
		  document.getElementById('myInput').value=document.getElementById('file').innerHTML +     fi.files.item(i).name;

        }

      }

    }

  </script>
 <script type="text/javascript">
 var chklist = [];
	$(document).ready(function(){
		
		
		
		$('#test').click(function(){
			chklist = [];
			
			$('#checklist').val(chklist);
			
			$('table [type="checkbox"]').each(function(i, chk) {
				if (chk.checked) 
				{
					//alert("Checked!"+ i+"::"+ JSON.stringify(chk.name));
					chklist.push(i);
				}
				
				
			}
			
			
			
			
			
			);
			
			
			//alert("checklist:"+chklist);
			$('#checklist').val(chklist);
			
			
			
        });
    });
	
</script>

 


</body>
</html>

s