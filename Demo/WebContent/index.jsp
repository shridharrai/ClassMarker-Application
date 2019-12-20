<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<link href="css/bootstrap.min.css" rel ="stylesheet">
<script>
window.addEventListener("load", init);
function init() {
	let message = document.getElementById('alert');
	console.log("Message is ",message.innerText);
	if (message.innerText == "Invalid") {
		message.innerText = " ";
		alert("Invalid Userid or Password....");
	}
}
</script>
</head>
<body>
<div class="container-fluid">
<h6 id="alert" class="text-center"><s:property value="message"/></h6>
<h1 class="text-center">Class Marker</h1>
<s:form action="firstlogin" method="post">
<s:textfield name="userid" cssClass="form-group form-control" type="text" 
label="Enter Your Name"/>
<s:password name="password" cssClass="form-group form-control" type="password" 
label="Enter Your Password"/>
<s:submit cssClass="btn btn-primary"/>
</s:form>
</div>
</body>
</html>