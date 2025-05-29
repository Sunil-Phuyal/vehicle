<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/contact.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/footer.css" />
<script src="https://kit.fontawesome.com/a63c128ded.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="./component/header.jsp" />
	<div class="container">
		<div class="hero">
			<h1>Contact Us</h1>
		</div>
		<c:if test="${not empty sessionScope.messageStatus}">
			<div class="popup" id="popup">
				<p>${sessionScope.messageStatus}</p>
				<button id="closePopup">Ok</button>
			</div>
			<c:remove var="messageStatus" scope="session" />
		</c:if>

		<div class="contact-content">
			<div class="first-section">
				<div class="first-one">
					<ul>
						<li><i class="fa-solid fa-phone"></i>
							<div>
								<h2>Phone</h2>
								<p>(123) 456-7890</p>
							</div></li>
						<li><i class="fa-solid fa-envelope"></i>
							<div>
								<h2>Email</h2>
								<p>support@fleetX.com</p>
							</div></li>
						<li><i class="fa-solid fa-business-time"></i>
							<div>
								<h2>Business Hours</h2>
								<p>Mon-Fri: 9am - 5pm</p>
							</div></li>
					</ul>
				</div>

				<div class="first-two">
					<h2>Get in touch</h2>
					<p class="form-desc">Have questions, suggestions, or need
						support? Drop us a message and we’ll get back to you soon!</p>
					<form action="${contextPath}/SendMessage" method="POST">
						<input type="email" name="email" placeholder="Email" required>
						<input type="text" name="subject" placeholder="Subject" required>
						<textarea name="message" placeholder="Message..." required></textarea>
						<button type="submit">Send</button>
					</form>
				</div>
			</div>
			<div class="last-section">
				<iframe
					src="https://www.google.com/maps/embed?pb=!1m17!1m12!1m3!1d3531.8387442068183!2d85.29312007492355!3d27.722264724834368!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m2!1m1!2zMjfCsDQzJzIwLjEiTiA4NcKwMTcnNDQuNSJF!5e0!3m2!1sen!2snp!4v1744859594468!5m2!1sen!2snp"
					width="600" height="450" style="border: 0;"></iframe>
			</div>
		</div>
	</div>
	<jsp:include page="./component/footer.jsp" />
</body>
<script type="text/javascript" src="${contextPath}/js/popUp.js"></script>

</html>