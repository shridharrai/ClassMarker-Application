<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change password</title>
<link href="css/bootstrap.min.css" rel ="stylesheet">
</head>
<body>
<div class="container-fluid">
<h1 class="alert-info text-center">Password Change</h1>
<h1><s:property value="message"/></h1>
<br>
<s:form action="pwdchange" method="post">
<s:textfield name="userid" cssClass="form-group form-control" type="text" 
label="Enter Your UserId"/>
<s:textfield name="contact" cssClass="form-group form-control" type="text" 
label="Enter Your Contact"/>
<s:textfield name="email" cssClass="form-group form-control" type="email" 
label="Enter Your Email"/>
<s:password name="password" cssClass="form-group form-control" 
type="password" label="Enter Your Password"/>
<s:submit cssClass="form-group btn btn-primary mx-5"/>
</s:form>
</div>
</body>
</html>