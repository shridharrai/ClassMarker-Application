<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teacher Registration</title>
<link href="css/bootstrap.min.css" rel ="stylesheet">
<%--  <link href="css/style.css" rel ="stylesheet"> --%>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<script>
window.addEventListener("load", init);
function init() {
	let message = document.getElementById('alert');
	console.log("Message is ",message.innerText);
	if (message.innerText == "Invalid") {
		message.innerText = " ";
		alert("Already Registered....");
	}
}
</script>
</head>
<body>
<h6 id="alert" class="text-center"><s:property value="message"/></h6>
<h2 class="form-title">Registration</h2>
    <s:form action="registerteacher" namespace="/">
        <s:textfield type="text" name="userenrollment" 
        label="Enrollment" placeholder="Enter Enroll"/>
        <s:textfield type="text" name="userid" label="NAme" 
        placeholder="Enter Name"/>
        <s:textfield type="text" name="contact" label="contact" 
        placeholder="Enter contact"/>
        <s:textfield type="text" name="email" label="Email" 
        placeholder="Enter email"/>
        <s:textfield type="text" name="pwd" label="password" 
        placeholder="Enter pwd"/>
        <s:checkboxlist list="branchlist" name="selectedbranch" 
        label="branch"/>
        <s:checkboxlist list="semesterlist" name="selectedsemester" 
        label="semester"/>
        <s:checkboxlist list="subjectlist" name="selectedsubject" 
        label="subject"/>
        <s:submit value="submit"/>
      </s:form>
</body>
</html>