<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart - FleetX</title>
<script src="https://kit.fontawesome.com/a63c128ded.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="${contextPath}/css/header.css">
<link rel="stylesheet" href="${contextPath}/css/global.css">
<link rel="stylesheet" href="${contextPath}/css/footer.css">
<link rel="stylesheet" href="${contextPath}/css/cart.css">
</head>
<body>
	<jsp:include page="./component/header.jsp" />

	<div class="container cart-container">
		<c:choose>
			<c:when test="${empty cartItems}">
				<div class="empty-cart">
					<i class="fas fa-shopping-cart fa-4x mb-3 text-muted"></i>
					<h3>Your cart is empty</h3>
					<p>Looks like you haven't added any vehicles to your cart yet.</p>
					<a href="${contextPath}/vehicle" class="btn btn-success mt-3">Browse
						Vehicles</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="cart-left">
					<h2>Your Cart</h2>
					<c:forEach items="${cartItems}" var="item" varStatus="status">
						<div class="cart-item">
							<img src="${contextPath}/assets/vehicle/${item.imageUrl}"
								alt="${item.brand} ${item.model}" class="vehicle-image">
							<div class="vehicle-info">
								<h5>${item.brand}${item.model}</h5>
								<div class="rental-details">
									<p>
										<strong>Pickup:</strong> ${item.pickupLocation}
									</p>
									<p>
										<strong>Rental:</strong>
										<fmt:formatDate value="${item.startDate}"
											pattern="MMM dd, yyyy" />
										-
										<fmt:formatDate value="${item.endDate}" pattern="MMM dd, yyyy" />
									</p>
									<p>
										<strong>Rate:</strong> Rs.${item.dailyRate}/day
									</p>
								</div>
							</div>
							<form action="${contextPath}/cart" method="post">
								<input type="hidden" name="action" value="remove"> <input
									type="hidden" name="itemIndex" value="${status.index}">
								<button type="submit" class="remove-btn">
									<i class="fas fa-trash"></i>
								</button>
							</form>
						</div>
					</c:forEach>

					<form action="${contextPath}/cart" method="post">
						<input type="hidden" name="action" value="clear">
						<button type="submit" class="btn btn-outline-danger">
							<i class="fas fa-trash-alt"></i> Clear Cart
						</button>
					</form>
				</div>

				<div class="cart-right price-details">
					<h4>Price Summary</h4>
					<c:forEach items="${cartItems}" var="item">
						<div class="summary-row">
							<span>${item.brand} ${item.model}</span> <span>Rs.${item.totalPrice}</span>
						</div>
					</c:forEach>
				<div class="total-amount summary-row">
						<span>Total:</span> <span>Rs.${totalPrice}</span>
					</div>
					<a href="${contextPath}/Checkout" class="btn btn-success"> <i
						class="fas fa-credit-card me-2"></i> Proceed to Checkout
					</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<jsp:include page="./component/footer.jsp" />
</body>
</html>

