<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<title>Elitecore Internet Login Firewall</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Custom Theme files -->
<link href="Css/otp_firewall.css" rel="stylesheet" type="text/css" media="all" />
<!-- //Custom Theme files -->
<!-- font-awesome icons -->
<link href="Css/otp_font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<!-- web font -->

<!-- //web font -->
</head>
<body>
	<!-- main -->
	<div class="main-agile">
		<h1>Welcome to Elitecore Internet Firewall</h1>
		<div class="content">
			<div class="top-grids">
				<div class="top-grids-right col-sm-offset-4">
					<div class="signin-form reset-password">
						<h3>Enter Credentials and Enjoy</h3>
						<form:form modelAttribute="Clientdto" action="client_otp.html" class="form-horizontal" role="form" method="POST">
							 <input path="username" type="text" class="form-control input-lg" id="username" size="25" name="username" placeholder="Username" required="true" oninvalid="setCustomValidity('Please enter valid Username')" oninput="setCustomValidity('')"/>
							 <input type="text" path="contact" class="form-control input-lg" id="contact" name="contact" placeholder="Contact Number" required oninvalid="setCustomValidity('Please enter Contact Number')" oninput="setCustomValidity('')">
							<input type="submit" class="send" value="Click here to Login">
							<!-- <i class="fa fa-sign-in" aria-hidden="true"></i> -->
						</form:form>
					</div>
										
				</div>
				<div class="clear"> </div>
			</div>
		</div>
		<div class="copyright">
			<p> © 2017. All rights reserved | Design by <a href="" target="_blank">Sterlite Tech Elitecore Technologies Ltd.</a></p>
		</div>
	</div>	
	<!-- //main --> 


</body>
</html>