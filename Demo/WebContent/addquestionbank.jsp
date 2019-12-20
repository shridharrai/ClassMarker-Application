<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<div class="pl-1">
		<h1 class='text-center btn btn-outline-primary w-100 display-1'>Add
			From Question Bank</h1>
		<s:form action="AddQuestionFromBank" namespace="/">
			<table class="table table-bordered">
				<thead class="thead-dark">
					<tr>
						<%-- <th>Select Question</th> --%>
						<th>Questionid</th>
						<th>Question</th>
						<th>Choice1</th>
						<th>Choice2</th>
						<th>Choice3</th>
						<th>Choice4</th>
						<th>Correct Choice</th>
					</tr>
				</thead>

				<s:iterator value="questionbanklist">
					<tr>
                      <%-- <td><s:checkbox name="select"></s:checkbox></td> --%>
						<td><s:property value="questionid" /></td>
						<td><s:property value="question" /></td>
						<td><s:property value="choice1" /></td>
						<td><s:property value="choice2" /></td>
						<td><s:property value="choice3" /></td>
						<td><s:property value="choice4" /></td>
						<td><s:property value="selectedchoice" /></td>
					<tr>
				</s:iterator>
			</table>
			 <s:checkboxlist list="questionidlist" name="selectedquestions" label="Select Questions"></s:checkboxlist>
			<s:submit value="Add Question"
				cssClass="form-group btn btn-primary mx-5" />
		</s:form>
	</div>
</body>
</html>