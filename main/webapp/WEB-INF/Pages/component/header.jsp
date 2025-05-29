<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="true"%>

<header class="header">
	<nav class="navBar">
		<ul class="navList">
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
	</nav>

	<div class="logo">
		<span>FleetX</span>
	</div>


	<c:choose>
		<c:when test="${not empty sessionScope.username}">
			<div class="logged">
				<div class="userProfile">
				<c:choose>
				  <c:when test="${sessionScope.username == 'admin'}">
				  <a href="${contextPath}/Dashboard"> <i
						class="fa-solid fa-user"></i></a>
				  </c:when>
						<c:otherwise>
							<a href="${contextPath}/userprofile"> <i
								class="fa-solid fa-user"></i></a>
						</c:otherwise>
					</c:choose>
				</div>
				<a class="cart" href="${contextPath}/cart"> <i
					class="fa-solid fa-cart-shopping"></i><span><c:out
							value="${cartSize}" /></span>
				</a>
				<form action="${contextPath}/logout" method="post">
					<input type="submit" class="nav-button" value="Logout" />
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<div class="buttons">
				<a href="${contextPath}/login">Login</a> <a
					href="${contextPath}/register">Register</a>
			</div>
		</c:otherwise>
	</c:choose>

</header>
