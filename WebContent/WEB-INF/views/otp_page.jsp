<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%=session.getAttribute("OTP")%>
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
		<h1>OTP Verification <%-- <%=session.getAttribute("OTP")%> --%></h1>
		<div class="content">
			<div class="top-grids">
				<div class="top-grids-right col-sm-offset-4">
					<div class="signin-form recover-password">
						<h3>Enter Your OTP</h3>
						<form action="client_verify_otp.html" class="form-horizontal" role="form" method="POST">
							<input type="text" class="form-control input-lg" id="otp" size="6" name="otp" placeholder="Enter OTP here!" required="true" oninvalid="setCustomValidity('Please enter valid OTP')" oninput="setCustomValidity('')"/>
							<input type="submit" class="send" value="Verify And Start Session">
							<div class="signin-agileits-bottom"> 
								<p><a href="dummy.html"><i class="fa fa-arrow-left" aria-hidden="true"></i> Cancel and go back to Login page</a></p>    
							</div>
						</form>
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