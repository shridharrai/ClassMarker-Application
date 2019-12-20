<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@attribute name="rolename" required="true" type="java.lang.String" %>
<%@attribute name="userid" required="true" type="java.lang.String" %>
<%@attribute name="rights" required="true" type="java.lang.String" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/dashboard/">
<link href="css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="css/assets/dashboard.css" rel="stylesheet">
<style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      #whcolor{
	color:white;
}
    </style>
</head>
<body>
<div class="container-fluid">
  <div class="row">
<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
  <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Class Marker</a>
  <h4 id="whcolor">Welcome <s:property value="rolename"/> <s:property value="userid"/> </h4>
  <h1>${rolename}</h1>
  <ul class="navbar-nav px-3">
  
    <li class="nav-item text-nowrap">
      <a id="whcolor" class="nav-link" href="#">Log Out</a>
    </li>
  </ul>
</nav>
<nav class="col-md-2 d-none d-md-block bg-light sidebar mt-5 ">
      <div class="sidebar-fixed">
        <ul class="nav flex-column">
        	<s:iterator value="rights">
          	<li class="nav-item">
            	<a class="nav-link" href='<s:property value="screenname"/> '>
              	<s:property value="rightname"/>
            	</a>
          	</li>
          	</s:iterator>
        </ul>

       
      </div>
</nav>
  <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4 mt-4">
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
</body>
</html>