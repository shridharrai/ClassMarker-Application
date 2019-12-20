<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
    <%@taglib prefix="cm" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Landing</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<cm:header rolename='sessionScope.role' userid='sessionScope.userid'
		rights='sessionScope.rights'></cm:header>
	<div class='w-100 float-right pr-2 mt-2'>
		<h1 class='text-center btn btn-outline-success w-100 display-1'>Landing
			Page</h1>
		<s:form action="adminlanding" namespace="/">
			<h3>
				No. of Users:
				<s:property value="usercount" />
			</h3>
			<h3>
				No. of Teachers:
				<s:property value="teachercount" />
			</h3>
			<h3>
				No. of Students:
				<s:property value="studentcount" />
			</h3>
			<h3>
				No. of Tests:
				<s:property value="testcount" />
			</h3>
			<h3>
				No. of Groups:
				<s:property value="groupcount" />
			</h3>
			<h3>
				No. of Branches:
				<s:property value="branchcount" />
			</h3>
			<h3>
				No. of Roles:
				<s:property value="rolecount" />
			</h3>
			<h3>
				No. of Subjects:
				<s:property value="subjectcount" />
			</h3>
			<h3>
				No. of Semesters:
				<s:property value="semestercount" />
			</h3>
			<h3>
				No. of Questions:
				<s:property value="questioncount" />
			</h3>
		</s:form>

	</div>
	<cm:footer></cm:footer>
</body>
</html>