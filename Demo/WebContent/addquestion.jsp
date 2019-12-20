<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Questions</title>
<link href="css/bootstrap.min.css" rel ="stylesheet">
</head>
<body>
<div class='w-100 float-right pr-2'>
	<h1 class='text-center btn btn-outline-success w-100 display-1'>Add Questions</h1>
	<s:form name="AddQuestion" action="AddQuestionBank">
                <s:submit  value="Add from QuestionBank" cssClass="form-group btn btn-primary mx-5"/>
</s:form>
<s:form action="addquestion" namespace="/">
	
		<s:textarea rows="10" cols="30" type="text" name="question" cssClass="form-group 
		form-control" label="Question" placeholder="Enter Question"/>
		<s:textfield type="text" name="choice1" cssClass="form-group 
		form-control" label="Choice 1" placeholder="Enter first choice"/>
		<s:textfield type="text" name="choice2" cssClass="form-group 
		form-control" label="Choice 2" placeholder="Enter second choice"/>
		<s:textfield type="text" name="choice3" cssClass="form-group 
		form-control" label="Choice 3" placeholder="Enter third choice"/>
		<s:textfield type="text" name="choice4" cssClass="form-group 
		form-control" label="Choice 4" placeholder="Enter fourth choice"/>
		<s:combobox list="{1,2,3,4}" headerKey="-1" 
		headerValue="--- Select ---" name="selectedchoice" label="Correct Choice"/>
	 
	<s:submit value="Add new Question" cssClass="form-group btn btn-info mx-5"/>
	<%-- <s:submit value="Add from QuestionBank" cssClass="form-group btn btn-primary mx-5"/> --%>
</s:form>

<s:form name="submit" action="submitTest">
                <s:submit  value="Submit" cssClass="form-group btn btn-success mx-5"/>
</s:form>

</div>
</body>
</html>