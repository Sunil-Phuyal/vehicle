<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/register.css" />
<script src="https://kit.fontawesome.com/a63c128ded.js"
	crossorigin="anonymous"></script>
</head>
<body>

	<header>
		<a href="${contextPath}/">FleetX</a>
	</header>

	<div class="container">
		<div class="form-container">
			<h1>Create Account</h1>
			<div class="message-box">
				<c:if test="${not empty successMessage}">
					<p style="color: green;">${successMessage}</p>
				</c:if>

				<c:if test="${not empty error}">
					<p style="color: red;">${error}</p>
				</c:if>
			</div>
			<form action="register" method="POST">
				<div class="row">
					<div class="field-group">
						<label for="fname">First Name</label> <input type="text"
							id="fname" name="fname" value="${fname}" placeholder="e.g., John" />
					</div>
					<div class="field-group">
						<label for="lname">Last Name</label> <input type="text" id="lname"
							name="lname" value="${lname}" placeholder="e.g., Doe" />
					</div>
				</div>

				<div class="row">
					<div class="field-group">
						<label for="username">Username</label> <input type="text"
							id="username" name="username" value="${username}"
							placeholder="e.g., John123" />
					</div>
					<div class="field-group">
						<label for="dob">Date of Birth</label> <input type="date" id="dob"
							name="dob" value="${dob}" />
					</div>
				</div>

				<div class="row">
					<div class="field-group">
						<label for="email">Email</label> <input type="email" id="email"
							name="email" value="${email}"
							placeholder="e.g., john123@gmail.com" />
					</div>
					<div class="field-group">
						<label for="phone">Phone</label> <input type="tel" id="phone" name="phone" value="${phone}"
					placeholder="e.g., 98********" />
					</div>
				</div>

				<div class="row">
					<div class="field-group">
						<label for="password">Password</label> <input type="password"
							id="password" name="password" placeholder="Create password" /> <i
							class="fa fa-eye" id="toggleIcon"
							onclick="togglePassword('password', 'toggleIcon')"></i>
					</div>
					<div class="field-group">
						<label for="repassword">Re-enter Password</label> <input
							type="password" id="repassword" name="repassword"
							placeholder="Confirm password" /> <i class="fa fa-eye"
							id="toggleIcon1"
							onclick="togglePassword('repassword', 'toggleIcon1')"></i>
					</div>
				</div>

				<input type="submit" value="Register" />
			</form>

			<p>
				Already have an account? <a href="${contextPath}/login">Login</a>
			</p>
		</div>
	</div>
</body>
<script type="text/javascript" src="${contextPath}/js/passwordToggle.js"></script>
</html>