<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@taglib prefix="cm" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Subject</title>
<link href="css/bootstrap.min.css" rel ="stylesheet">
<script>
window.addEventListener("load", init);
function init() {
	let message = document.getElementById('alert');
	console.log("Message is ",message.innerText);
	if (message.innerText == "Added") {
		message.innerText = " ";
		alert("Subject Added....");
	}
}
</script>
</head>
<body>
<cm:header rolename='sessionScope.role' 
userid='sessionScope.userid' 
rights='sessionScope.rights' ></cm:header>
<div class='w-100 float-right pr-2'>
<h1>Welcome<h3>${sessionScope.userid}</h3> ${sessionScope.rights }</h1>
    <h6 id="alert" class="text-center"><s:property value="message"/></h6>
    <div class="w-25 float-left">
	<h1 class='text-center btn btn-outline-primary w-100 display-1'>Add Subject</h1>
<s:form action="addsubject" namespace="/">
	<s:textfield type="text" name="subjectcode" 
	cssClass="form-group form-control" label="Subject Code" 
	placeholder="Enter Subject Code"/>
	<s:textfield type="text" name="subjectid" 
	cssClass="form-group form-control" label="Subject Id" 
	placeholder="Enter Subject Id"/>
	<s:textfield type="text" name="subjectname" 
	cssClass="form-group form-control" label="Subject Name" 
	placeholder="Enter Subject Name"/>
	<s:textfield type="text" name="subjectdesc" 
	cssClass="form-group form-control" label="Subject Description" 
	placeholder="Enter Subject Description"/>
	<s:combobox list="branchlist" headerKey="-1" 
	headerValue="--- Select ---" name="selectedbranch" label="Branch"/>
	<s:combobox list="semesterlist" headerKey="-1"
	headerValue="--- Select ---" name="selectedsemester" label="Semester"/>
	<s:submit value="Submit" cssClass="form-group btn btn-primary mx-5"/>
</s:form>
</div>
<div class="w-75 float-right pl-1">
<h1 class='text-center btn btn-outline-success w-100 display-1'>Subjects</h1>
<s:form name="showsubject" action="showsubject"  class="mx-auto my-2">
                <s:submit value="Fetch Records" class="btn btn-primary"/>
</s:form>

<table class="table table-bordered">
	<thead class="thead-dark">
		<tr>
			<th>Subject Code</th>
			<th>Subject Id</th>
			<th>Subject Name</th>
			<th>SUbject Description</th>
			<th>Branch Name</th>
			<th>Semester Name</th>
		</tr>
	</thead>
	<s:iterator value="subjectlist">
	<tr>
			<td><s:property value="subjectcode"/></td>
			<td><s:property value="subjectid"/></td>
			<td><s:property value="subjectname"/></td>
			<td><s:property value="subjectdesc"/></td>
			<td><s:property value="selectedbranch"/></td>
			<td><s:property value="selectedsemester"/></td>
	<tr>
	</s:iterator>
</table>
</div>
</div>
<cm:footer></cm:footer>
</body>
</html>