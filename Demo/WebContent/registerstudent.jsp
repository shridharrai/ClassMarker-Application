<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Registration</title>
<link href="css/bootstrap.min.css" rel ="stylesheet">
<link href="css/style.css" rel ="stylesheet">
<script>
window.addEventListener("load", init);
function init() {
	let message = document.getElementById('alert');
	console.log("Message is ",message.innerText);
	if (message.innerText == "Invalid") {
		message.innerText = " ";
		alert("Already Registered....");
	}
}
</script>
</head>
<body>
<div class="container-fluid">
	 <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h6 id="alert" class="text-center"><s:property value="message"/></h6>
                        <h2 class="form-title">Registration</h2>
                        <form action="registerstudent" method="post">
                            <div class="form-group">
                                <label for="enrollment"></label>
                                <input type="text" name="userenrollment" 
                                id="userenrollment" placeholder="Your Enrollment Number"/>
                            </div>
                            <div class="form-group">
                                <label for="name"></label>
                                <input type="text" name="userid" 
                                id="name" placeholder="Your Name"/>
                            </div>
                            <div class="form-group">
                                <label for="contact no."></label>
                                <input type="text" name="contact" 
                                id="contact" placeholder="Your Contact No."/>
                            </div>
                            <div class="form-group">
                                <label for="email"></label>
                                <input type="email" name="email" 
                                id="email" placeholder="Your Email"/>
                            </div>
                            <div class="form-group">
                                <label for="pass"></label>
                                <input type="password" name="pwd" 
                                id="pwd" placeholder="Password"/>
                            </div>
                            <s:combobox list="branchlist" headerKey="-1" 
                            headerValue="--- Select Branch---" name="selectedbranch" label="Branch" />
                            <br> <br>
                            <s:combobox list="semesterlist" headerKey="-1" 
                            headerValue="--- Select Sem ---" name="selectedsemester" label="Semester"/>
    
                             <div class="form-group form-button">
                                <input type="submit" class="form-submit" 
                                value="Register"/>
                            </div>
                        </form>
                    </div>
                    <div class="signup-image">
                        <figure><img src="images/signup-image.jpg" 
                        alt="sign up image"></figure>
                    </div>
                </div>
            </div>
        </section>
</div>
</body>
</html>