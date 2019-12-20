<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@taglib prefix="cm" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Semester</title>
<link href="css/bootstrap.min.css" rel ="stylesheet">
<script>
window.addEventListener("load", init);
function init() {
	let message = document.getElementById('alert');
	console.log("Message is ",message.innerText);
	if (message.innerText == "Added") {
		message.innerText = " ";
		alert("Semester Added....");
	}
}
</script>
</head>
<body>
<cm:header rolename='<s:property value="rolename"/>'
		userid='<s:property value="userid" />'
		rights='<s:property value="rights"/>'></cm:header>
	<div class='w-100 float-right pr-2'>
		<h6 id="alert" class="text-center">
			<s:property value="message" />
		</h6>
		<div class="w-25 float-left">
			<h1 class='text-center btn btn-outline-primary w-100 display-1'>Add
				Semester</h1>
			<s:form action="addsem" method="post">
				<s:textfield type="text" name="semestername"
					cssClass="form-group form-control" label="Semester Name"
					placeholder="Enter Semester Name" />
				<s:textfield type="text" name="semesterdesc"
					cssClass="form-group form-control" label="Semester Description"
					placeholder="Enter Semester Description" />
				<s:submit cssClass="form-group btn btn-primary mx-5" />
			</s:form>
		</div>
		<div class="w-75 float-right pl-1">
			<h1 class='text-center btn btn-outline-success w-100 display-1'>Semesters</h1>
			<s:form name="showsemester" action="showsemester"
				class="mx-auto my-2">
				<s:submit value="Fetch Records" class="btn btn-primary" />
			</s:form>

			<table class="table table-bordered">
				<thead class="thead-dark">
					<tr>
						<th>Semester Name</th>
						<th>Semester Description</th>
					</tr>
				</thead>
				<s:iterator value="semesterlist">
					<tr>
						<td><s:property value="semestername" /></td>
						<td><s:property value="semesterdesc" /></td>
					<tr>
				</s:iterator>
			</table>
		</div>
	</div>
	<cm:footer></cm:footer>
</body>
</html>