<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/login.css" />
<script src="https://kit.fontawesome.com/a63c128ded.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<header>
		<a href="./index.html">FleetX</a>
	</header>

	<div class="container">
		<div class="form-container">
			<h2>Login</h2>

			<!-- Display error message if available -->
			<c:if test="${not empty error}">
				<div class="error-message">${error}</div>
			</c:if>

			<c:if test="${not empty message}">
				<div class="success-message">${message}</div>
			</c:if>

			<form action="login" method="POST">
				<input type="text" name="username" placeholder="Enter your username" />
				<input type="password" name="password" id="loginpass"
					placeholder="Enter your password" /> <i class="fa fa-eye"
					id="toggleIcon" onclick="togglePassword('loginpass', 'toggleIcon')"></i>
				<div class="extra-links">
					<a href="#" onclick="openModal()">Forgot password?</a>
				</div>

				<input type="submit" value="Login" />
			</form>

			<div class="form-footer">
				Don’t have an account? <a href="${contextPath}/register">Register
					here</a>
			</div>

			<!-- Forgot Password Modal -->
			<div id="forgotPasswordModal" class="modal"
				style="${not empty error || not empty message ? 'display: block;' : ''}">
				<div class="modal-content">
					<span class="close" onclick="closeModal()">&times;</span>
					<h3>Reset Password</h3>
					<c:if test="${not empty error}">
						<div class="error-message">${error}</div>
					</c:if>
					<form action="${contextPath}/forgetPassword" method="POST">
						<input type="email" name="email"
							placeholder="Enter your registered email" required /> <input
							type="password" id="npassword" name="newPassword"
							placeholder="Enter new password" required /> <i
							class="fa fa-eye" id="toggleIcon1"
							onclick="togglePassword('npassword', 'toggleIcon1')"></i> <input
							type="password" id="cpassword" name="confirmPassword"
							placeholder="Confirm new password" required /> <i
							class="fa fa-eye" id="toggleIcon2"
							onclick="togglePassword('cpassword', 'toggleIcon2')"></i> <input
							type="submit" value="Change Password" />
					</form>
				</div>
			</div>


		</div>
	</div>
</body>
<script>
	function openModal() {
		document.getElementById('forgotPasswordModal').style.display = 'block';
	}

	function closeModal() {
		document.getElementById('forgotPasswordModal').style.display = 'none';
	}

	window.onclick = function(event) {
		const modal = document.getElementById('forgotPasswordModal');
		if (event.target === modal) {
			modal.style.display = 'none';
		}
	}
</script>

<script type="text/javascript" src="${contextPath}/js/passwordToggle.js"></script>
</html>
