<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="true"%>
<footer>
	<div class="footer-links">
		<div class="about-company">
			<h3>About FleetX</h3>
			<p>FleetX is a leading vehicle rental service provider, offering
				a wide range of vehicles for all your travel needs.</p>
		</div>
		<div class="contact-info">
			<h3>Contact Info</h3>
			<ul class="contact-info-list">
				<li><i class="fa-solid fa-map-location-dot"></i>kathmandu,Nepal</li>
				<li><i class="fa-solid fa-phone"></i>+977 555 8822</li>
				<li><i class="fa-solid fa-envelope"></i>support@fleetX.com</li>
			</ul>
		</div>
		<div class="quick-links">
			<h3>Quick Links</h3>
			<ul class="quick-links_list">
				<li><a href="${contextPath}/">Home</a></li>
				<li><a href="${contextPath}/about">About</a></li>
				<c:choose>
					<c:when test="${not empty sessionScope.username}">
						<li><a href="${contextPath}/vehicle">Vehicle</a></li>
						<li><a href="${contextPath}/contact">Contact</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${contextPath}/login">Vehicle</a></li>
						<li><a href="${contextPath}/login">Contact</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div class="social-media">
			<h3>Follow Us</h3>
			<ul class="social-media_list">
				<li><a href="https://www.facebook.com/" target="_blank"><i
						class="fa-brands fa-facebook"></i></a></li>
				<li><a href="https://x.com/" target="_blank"><i
						class="fa-brands fa-twitter"></i></a></li>
				<li><a href="https://www.instagram.com/" target="_blank"><i
						class="fa-brands fa-instagram"></i></a></li>
				<li><a href="https://www.linkedin.com/" target="_blank"><i
						class="fa-brands fa-linkedin"></i></a></li>
			</ul>
		</div>
	</div>
	<div class="footer-bottom">
		<p>&copy; 2025 FleetX. All rights reserved.</p>
		<div class="other-links">
			<a href="${contextPath}/Terms">Terms & Conditions</a> <a
				href="${contextPath}/Privacy">Privacy Policy</a>
		</div>
	</div>
</footer>