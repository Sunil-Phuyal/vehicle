<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/home.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/footer.css" />
<script src="https://kit.fontawesome.com/a63c128ded.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="./component/header.jsp" />
	<div class="container">
		<!-- ---slider--- -->
		<div class="hero">
			<div class="slide">
				<div class="text">
					<h2>Explore the World with FleetX</h2>
					<p>Your trusted partner in vehicle rentals</p>
					<c:choose>
						<c:when test="${not empty sessionScope.username}">
							<a href="${contextPath}/vehicle" class="slide-btn">Browse
								Vehicle</a>
						</c:when>
						
						<c:otherwise>
							<a href="${contextPath}/login" class="slide-btn">Sign Up</a>
						</c:otherwise>
					</c:choose>

				</div>
			</div>
			<div class="down">
				<i class="fa-solid fa-chevron-down"></i>
			</div>
		</div>


		<!-- ---why-choose-us--- -->
		<div class="why-choose-us">
			<div class="features">
				<div class="feature-item">
					<i class="fa-solid fa-car"></i>
					<div class="item-text">
						<h3>Wide Range of Vehicles</h3>
						<p>We offer a diverse fleet of vehicles to suit all your
							travel needs.</p>
					</div>
				</div>
				<div class="feature-item">
					<i class="fa-solid fa-clock"></i>
					<div class="item-text">
						<h3>24/7 Customer Support</h3>
						<p>Our support team is available around the clock to assist
							you.</p>
					</div>
				</div>
				<div class="feature-item">
					<i class="fa-solid fa-shield-alt"></i>
					<div class="item-text">
						<div class="item-text">
							<h3>Safety First</h3>
							<p>Your safety is our top priority. All our vehicles are
								regularly maintained.</p>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- ---search vehicle--- -->
		<div class="search-section">
			<div>
				<h2>Find Your Perfect Vehicle</h2>
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
			</div>
			<div class="how-it-works">
				<h2>How It Works</h2>
				<div class="steps">
					<div class="step-item">
						<i class="fa-solid fa-search"></i>
						<h3>Search</h3>
						<p>Find the perfect vehicle for your needs.</p>
					</div>
					<div class="step-item">
						<i class="fa-solid fa-calendar-check"></i>
						<h3>Book</h3>
						<p>Book your vehicle online in just a few clicks.</p>
					</div>
					<div class="step-item">
						<i class="fa-solid fa-car-side"></i>
						<h3>Drive</h3>
						<p>Pick up your vehicle and hit the road!</p>
					</div>
					<div class="step-item">
						<i class="fa-solid fa-check-circle"></i>
						<h3>Return</h3>
						<p>Return the vehicle at the end of your rental period.</p>
					</div>
				</div>
			</div>
		</div>

		<!-- ---Featured Vehicles--- -->
		<div class="featured-vehicle">
			<h2>Latest Vehicles</h2>
			<div class="vehicle-list">
				<c:if test="${not empty VehicleList}">
					<c:forEach items="${VehicleList}" var="vehicle">
						<div class="vehicle-item">
							<div class="vehicle-img">
								<img src="${contextPath}/assets/vehicle/${vehicle.imageUrl}"
									alt="${vehicle.brand} ${vehicle.model}" />
							</div>
							<div class="vehicle-info">
								<h3>${vehicle.model}</h3>
								<div class="other-details">
									<p>Rs. ${vehicle.dailyRate}/day</p>
									<p>
										<i class="fas fa-users"></i> ${vehicle.capacity}
									</p>
									<p>
										<i class="fas fa-car-side"></i> ${vehicle.brand}
									</p>
									<p>
										<i class="fas fa-cogs"></i> ${vehicle.transmission}
									</p>
								</div>
								<div>
									<c:choose>
										<c:when test="${not empty sessionScope.username}">
											<a
												href="${contextPath}/vehicleDetail?vehicleId=${vehicle.id}">View
												Details</a>
										</c:when>
										<c:otherwise>
											<a
												href="${contextPath}/login">View
												Details</a>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${empty VehicleList}">
					<p>No vehicles found.</p>
				</c:if>
			</div>
		</div>

		<div class="testimonials-section">
			<h2>What Our Customers Say</h2>
			<div class="testimonials">
				<div class="testimonial-item">
					<p>"FleetX made my trip unforgettable! The vehicle was in
						perfect condition and the service was top-notch."</p>
					<h3>- John Doe</h3>
				</div>
				<div class="testimonial-item">
					<p>"I had a great experience with FleetX. The booking process
						was easy and the staff was very helpful."</p>
					<h3>- Jane Smith</h3>
				</div>
				<div class="testimonial-item">
					<p>"I highly recommend FleetX for anyone looking to rent a
						vehicle. Their prices are competitive and the service is
						excellent."</p>
					<h3>- Mark Johnson</h3>
				</div>
				<div class="testimonial-item">
					<p>"FleetX made my trip unforgettable! The vehicle was in
						perfect condition and the service was top-notch."</p>
					<h3>- Sarah Brown</h3>
				</div>
			</div>
		</div>

		<!-- ---company-logo--- -->
		<div class="company-logos-section">
			<div class="company-logos">
				<img src="${contextPath}/assets/brand-logo/BYD Auto.png"
					alt="Company Logo 1" /> <img
					src="${contextPath}/assets/brand-logo/toyota.svg"
					alt="Company Logo 2" /> <img
					src="${contextPath}/assets/brand-logo/Ford-logo.svg"
					alt="Company Logo 3" /> <img
					src="${contextPath}/assets/brand-logo/honda.png"
					alt="Company Logo 4" /> <img
					src="${contextPath}/assets/brand-logo/mahindra.svg"
					alt="Company Logo 4" /> <img
					src="${contextPath}/assets/brand-logo/Tata Motors.png"
					alt="Company Logo 4" /> <img
					src="${contextPath}/assets/brand-logo/royal.png"
					alt="Company Logo 4" />
			</div>
		</div>
	</div>

	<jsp:include page="./component/footer.jsp" />
</body>
<script>
    document.querySelector('.down').addEventListener('click', (event) => {
        event.preventDefault();
        window.scrollBy({
            top: 700,
            behavior: 'smooth'
        });
    });
</script>
</html>