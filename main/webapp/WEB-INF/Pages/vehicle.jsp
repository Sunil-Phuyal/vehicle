<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>FleetX | Vehicles</title>
<script src="https://kit.fontawesome.com/a63c128ded.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="${contextPath}/css/global.css" />
<link rel="stylesheet" href="${contextPath}/css/vehicle.css" />
<link rel="stylesheet" href="${contextPath}/css/header.css" />
<link rel="stylesheet" href="${contextPath}/css/footer.css" />
</head>
<body>
	<jsp:include page="./component/header.jsp" />

	<div class="container">
		<div class="hero">
			<h1>Vehicles</h1>
		</div>

		<div class="vehicle-content">
			<!-- Display Success Message when Added to Cart -->
			<c:if test="${not empty sessionScope.messageStatus}">
				<div class="popup" id="popup">
					<p>${sessionScope.messageStatus}</p>
					<button id="closePopup">Ok</button>
				</div>
				<c:remove var="messageStatus" scope="session" />
			</c:if>


			<section class="one">
				<h2>Browse Your Vehicle</h2>
				<div class="filter">
					<form method="get" action="${contextPath}/vehicle" id="filterForm">
						<select name="fuel" id="fuel"
							onchange="document.getElementById('filterForm').submit();">
							<option value="" disabled selected>Fuel</option>
							<option value="ev">EV</option>
							<option value="Petrol">Petrol</option>
							<option value="Diesel">Diesel</option>
						</select> <select name="gear" id="make"
							onchange="document.getElementById('filterForm').submit();">
							<option value="" disabled selected>Gear</option>
							<option value="Manual">Manual</option>
							<option value="Automatic">Automatic</option>
						</select>
					</form>
				</div>
			</section>

			<section class="two">
				<!-- Sidebar Form -->
				<aside class="sidebar">
					<form class="search-form" method="get"
						action="${contextPath}/vehicle">
						<fieldset>
							<legend>Vehicle Type</legend>
							<select name="type" required>
								<option value="" disabled selected>Select vehicle type</option>
								<option value="car">Car</option>
								<option value="bike">Bike</option>
							</select>
						</fieldset>
						<fieldset>
							<legend>Location</legend>
							<select name="location" required>
								<option value="" disabled selected>Select location</option>
								<option value="Kathmandu">Kathmandu</option>
								<option value="Lalitpur">Lalitpur</option>
								<option value="Bhaktapur">Bhaktapur</option>
							</select>
						</fieldset>
						<button type="submit">Search</button>
					</form>
				</aside>

				<!-- Vehicle Cards -->
				<ul class="vehicle-list">
					<c:if test="${not empty vehicleList}">
						<c:forEach items="${vehicleList}" var="vehicle">
							<li class="item">
								<div class="vehicle-head">
									<div class="img">
										<img src="${contextPath}/assets/vehicle/${vehicle.imageUrl}"
											alt="${vehicle.brand} ${vehicle.model}" />
									</div>
									<h3 class="vehicle-name">${vehicle.brand}${vehicle.model}</h3>
								</div>
								<div class="vehicle-details">
									<p>
										<i class="fa-solid fa-car"></i> ${vehicle.brand}
									</p>
									<p>Rs. ${vehicle.dailyRate}/day</p>
									<p>
										<i class="fa-regular fa-calendar"></i> ${vehicle.year}
									</p>
									<p>
										<i class="fa-solid fa-gears"></i> ${vehicle.transmission}
									</p>
									<p>
										<i class="fa-solid fa-gas-pump"></i> ${vehicle.fuelType}
									</p>
									<p>
										<i class="fa-solid fa-person"></i> ${vehicle.capacity}
									</p>
								</div> <c:choose>
									<c:when test="${vehicle.status.toLowerCase() == 'available'}">
										<div class="rent-btn">
											<a
												href="${contextPath}/vehicleDetail?vehicleId=${vehicle.id}">Rent
												Now</a>
										</div>
									</c:when>
									<c:otherwise>
										<div class="not-available-box">
											<span class="not-available">Not Available</span>
										</div>
									</c:otherwise>
								</c:choose>

							</li>
						</c:forEach>
					</c:if>
					<c:if test="${empty vehicleList}">
						<p>No vehicles found.</p>
					</c:if>
				</ul>
			</section>
		</div>
	</div>

	<jsp:include page="./component/footer.jsp" />
</body>
<script type="text/javascript" src="${contextPath}/js/popUp.js"></script>
</html>
