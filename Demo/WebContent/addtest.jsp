<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Test</title>
<link href="css/bootstrap.min.css" rel ="stylesheet">
</head>
<body>
<div class='w-100 float-right pr-2'>
	<h1 class='text-center btn btn-outline-success w-100 display-1'>Add Test</h1>
	<h2>Please make group first........</h2>
<s:form action="addtest" namespace="/">
		<s:textfield type="text" name="testname" 
		cssClass="form-group form-control" label="Test Name" 
		placeholder="Enter Test Name"/>
		<s:textfield type="text" name="marksperquestion" 
		cssClass="form-group form-control" label="Marks per Question" 
		placeholder="Enter marks per ques"/>
		<s:combobox list="branchlist" headerKey="-1" 
		headerValue="--- Select ---" name="selectedbranch" 
		label="Branch"/>
		<s:combobox list="semesterlist" headerKey="-1" 
		headerValue="--- Select ---" name="selectedsemester" label="Semester"/>
		<s:combobox list="subjectlist" headerKey="-1" 
		headerValue="--- Select ---" name="selectedsubject" label="Subject"/>
		<s:checkboxlist list="grouplist" name="selectedgroup" 
		label="Group" />
	<s:submit value="Proceed" cssClass="form-group btn btn-success mx-5"/>
</s:form>

</div>
</body>
</html>