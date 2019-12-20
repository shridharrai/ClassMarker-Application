<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
    <%@taglib prefix="cm" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teacher Landing</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<cm:header rolename='sessionScope.role' userid='sessionScope.userid'
		rights='sessionScope.rights'></cm:header>
	<div class='w-100 float-right pr-2 mt-2'>
		<h1 class='text-center btn btn-outline-success w-100 display-1'>Landing
			Page</h1>
		<s:form action="teacherlanding" namespace="/">
			<table class="table table-bordered">
				<thead class="thead-dark">
					<tr>
						<th>Test name</th>
						<th>Branch Name</th>
						<th>Group name</th>
						<th>Semester Name</th>
						<th>Subject Name</th>
						<th>Teacher Name</th>
					</tr>
				</thead>
				<s:iterator value="lands">
					<tr>
						<td><s:property value="teachername" /></td>
						<td><s:property value="subjectname" /></td>
						<td><s:property value="testname" /></td>
						<td><s:property value="branchname" /></td>
						<td><s:property value="semestername" /></td>
						<td><s:property value="groupname" /></td>
					</tr>
				</s:iterator>
			</table>
		</s:form>

	</div>
	<cm:footer></cm:footer>
</body>
</html>