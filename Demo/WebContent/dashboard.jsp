<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@taglib prefix="cm" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/dashboard/">
<link href="css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="css/assets/dashboard.css" rel="stylesheet">
</head>
<body>

<cm:header rolename='sessionScope.role' 
userid='sessionScope.userid' 
rights='sessionScope.rights' ></cm:header>

        <h1 class="h2">Main Dashboard</h1>
       <%--  <h2>Uniqueid is ${sessionScope.uniqueid}</h2>  --%>
        <cm:footer></cm:footer>
</body>
</html>