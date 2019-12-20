<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="s" uri="/struts-tags" %>
     <%@taglib prefix="cm" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Group</title>
<link href="css/bootstrap.min.css" rel ="stylesheet">
</head>
<body>
<cm:header rolename='sessionScope.role' userid='sessionScope.userid'
		rights='sessionScope.rights'></cm:header>
	<div class='w-100 float-right pr-2 mt-1'>
		<div class="w-25 float-left">
			<h1 class='text-center btn btn-outline-primary w-100 display-1'>Add
				Group</h1>
			<s:form action="addgroup" method="post" namespace="/">
				<s:textfield type="text" name="groupname"
					cssClass="form-group 
	form-control" label="Group Name"
					placeholder="Enter Group Name" />
				<s:textfield type="text" name="groupdesc"
					cssClass="form-group 
	form-control" label="Group Description"
					placeholder="Enter Group Description" />
				<s:checkboxlist list="studentlist" name="selectedstudent"
					label="Students" />
				<s:submit value="Add Group" name="submit"
					cssClass="form-group btn btn-primary mx-5" />
			</s:form>
		</div>
		<div class="w-75 float-right pl-1">
			<h1 class='text-center btn btn-outline-success w-100 display-1'>Groups</h1>
			<s:form name="showgroup" action="showgroup" class="mx-auto my-2">
				<s:submit value="Fetch Records" class="btn btn-primary" />
			</s:form>

			<table class="table table-bordered">
				<thead class="thead-dark">
					<tr>
						<th>Group Name</th>
						<th>Group Description</th>
						<th>Students</th>
					</tr>
				</thead>
				<s:iterator value="grouplist">
					<tr>
						<td><s:property value="groupname" /></td>
						<td><s:property value="groupdesc" /></td>
						<td><s:property value="showstudent" /></td>
					<tr>
				</s:iterator>
			</table>
		</div>
	</div>
	<cm:footer></cm:footer>
</body>
</html>