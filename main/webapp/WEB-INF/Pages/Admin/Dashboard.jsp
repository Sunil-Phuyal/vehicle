<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Admin Dashboard</title>
<script src="https://kit.fontawesome.com/a63c128ded.js"
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="${contextPath}/css/admin/global.css" />
<link rel="stylesheet" href="${contextPath}/css/admin/dashboard.css" />
<link rel="stylesheet" href="${contextPath}/css/admin/manageVehicle.css" />
<link rel="stylesheet" href="${contextPath}/css/admin/addVehicle.css" />
<link rel="stylesheet" href="${contextPath}/css/admin/booking.css" />
<link rel="stylesheet" href="${contextPath}/css/admin/user.css" />
<link rel="stylesheet" href="${contextPath}/css/admin/message.css" />
</head>

<body>
	<header class="header">
		<h1>Admin Dashboard</h1>
		<div class="profile">
			<div>
				<img src="${contextPath}/assets/bg-img/no-profile.jpg"
					alt="Profile Pic" /> <span> <c:choose>
						<c:when test="${not empty sessionScope.username}">
							<span>${sessionScope.username}</span>
						</c:when>
						<c:otherwise>
							<span>Admin</span>
						</c:otherwise>
					</c:choose>

				</span>
			</div>
			<a href="${contextPath}/" target="_blank" class="public-link"><i
				class="fas fa-globe"></i>Public</a>
			<form action="${contextPath}/logout" method="post">
				<input type="submit" class="nav-button" value="Logout" />
			</form>

		</div>
	</header>

	<div class="container">
		<aside class="sidebar">
			<nav>
				<ul>
					<li><a href="#" class="active" data-target="dashboard"><i
							class="fas fa-chart-line"></i><span>Dashboard</span></a></li>
					<li><a href="#" data-target="manageVehicle"><i
							class="fas fa-car"></i><span>Manage Vehicles</span></a></li>
					<li><a href="#" data-target="addVehicle"><i
							class="fas fa-plus-circle"></i><span>Add Vehicle</span></a></li>
					<li><a href="#" data-target="booking"><i
							class="fas fa-calendar-check"></i><span>Bookings</span></a></li>
					<li><a href="#" data-target="user"><i class="fas fa-users"></i><span>Users</span></a></li>
					<li><a href="#" data-target="messageContent"><i
							class="fas fa-envelope"></i><span>Messages</span></a></li>
				</ul>
			</nav>
			<div class="minimize">
				<i class="fa-solid fa-circle-chevron-left"></i>
			</div>

		</aside>

		<!-- Dashboard Section -->
		<section id="dashboard" class="main active-main">
			<c:if test="${not empty sessionScope.message}">
				<div class="popup" id="popup">
					<p>${sessionScope.message}</p>
					<button id="closePopup">Ok</button>
				</div>
				<c:remove var="message" scope="session" />
			</c:if>
			<div class="welcome-section">
				<h2 class="welcome-title">Welcome, Admin!</h2>
				<p class="welcome-subtitle">Manage your vehicle fleet
					efficiently with the FleetX dashboard.</p>
			</div>

			<div class="dashboard-grid">
				<div class="stat-card">
					<div class="stat-header">
						<div class="stat-title">Total Vehicles</div>
						<div class="stat-icon blue">
							<i class="fas fa-car"></i>
						</div>
					</div>
					<div class="stat-value">${totalVehicle}</div>
				</div>
				<div class="stat-card">
					<div class="stat-header">
						<div class="stat-title">Active Bookings</div>
						<div class="stat-icon green">
							<i class="fas fa-calendar-check"></i>
						</div>
					</div>
					<div class="stat-value">${totalBooking}</div>
				</div>
				<div class="stat-card">
					<div class="stat-header">
						<div class="stat-title">Registered Users</div>
						<div class="stat-icon orange">
							<i class="fas fa-users"></i>
						</div>
					</div>
					<div class="stat-value">${totalUser}</div>

				</div>
				<div class="stat-card">
					<div class="stat-header">
						<div class="stat-title">Messages</div>
						<div class="stat-icon red">
							<i class="fas fa-envelope"></i>
						</div>
					</div>
					<div class="stat-value">${totalMessage}</div>
				</div>
			</div>

			<div class="tables-container">

				<!-- Table 1: Booking Trend -->
				<div class="dashboard-table">
					<h3>Daily Booking Trend</h3>
					<table>
						<thead>
							<tr>
								<th>Date</th>
								<th>Total Bookings</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="trend" items="${bookingTrend}">
								<tr>
									<td>${trend.bookingDate}</td>
									<td>${trend.totalBookings}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="view-all-btn">
						<a href="#" data-target="booking">View All</a>
					</div>
				</div>

				<!-- Table 2: Recent User Registrations -->
				<div class="dashboard-table">
					<h3>New User</h3>
					<table>
						<thead>
							<tr>
								<th>Name</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${Only5User}">
								<tr>
									<td>${user.fname} ${user.lname}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="view-all-btn">
						<a href="#" data-target="user">View All</a>
					</div>
				</div>

				<!-- Table 3: Recent Messages -->
				<div class="dashboard-table">
					<h3>Recent Messages from Users</h3>
					<table>
						<thead>
							<tr>
								<th>Message</th>
								<th>Received On</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="msg" items="${Only5Message}">
								<tr>
									<td>${msg.content}</td>
									<td>${msg.sentAt}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="view-all-btn">
						<a href="#" data-target="messageContent">View All</a>
					</div>
				</div>

			</div>

		</section>

		<!-- Manage Vehicles -->
		<section id="manageVehicle" class="main">

			<div class="content">
				<div class="content-header">
					<h2 class="section-title">Vehicle Fleet</h2>
				</div>

				<div class="table-container">
					<table class="data-table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Vehicle</th>
								<th>Category</th>
								<th>Registration</th>
								<th>Daily Rate</th>
								<th>Fuel Type</th>
								<th>Transmission</th>
								<th>Capacity</th>
								<th>Image</th>
								<th>Status</th>
								<th>Location</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${VehicleList}" var="vehicle">
								<tr>
									<td>V${vehicle.id}</td>
									<td>
										<div>
											<strong>${vehicle.brand} ${vehicle.model}</strong>
											<div style="font-size: 0.8rem; color: var(--text-secondary);">2020</div>
										</div>
									</td>
									<td>${vehicle.category}</td>
									<td>${vehicle.registrationNumber}</td>
									<td>Rs.${vehicle.dailyRate}/day</td>
									<td>${vehicle.fuelType}</td>
									<td>${vehicle.transmission}</td>
									<td>${vehicle.capacity}</td>
									<td><img alt="${vehicle.model}"
										src="${contextPath}/assets/vehicle/${vehicle.imageUrl}"
										width="80" height="50" style="object-fit: cover;"></td>
									<td><span class="status status-available">${vehicle.status}</span></td>
									<td>${vehicle.location}</td>
									<td>
										<div class="btn-group">
											<a href="${contextPath}/EditVehicle?vehicleId=${vehicle.id}"
												class="action-btn edit-btn" title="Edit"> <i
												class="fas fa-edit"></i>
											</a>
											<form class="deleteBtn" method="post" action="deleteVehicle">
												<input type="hidden" name="vehicleID" value="${vehicle.id}" />
												<button type="submit" class="action-btn delete-btn">
													<i class="fas fa-trash"></i>
												</button>
											</form>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</section>

		<!-- Add Vehicle -->
		<section id="addVehicle" class="main">
			<div class="vehicle-form-container">
				<div class="vehicle-form-wrapper">
					<div class="form-header">
						<h3>Vehicle Information</h3>
					</div>

					<div class="form-body">
						<form action="AddVehicle" method="POST"
							enctype="multipart/form-data">
							<div class="form-section">
								<h4 class="section-title">Basic Information</h4>
								<div class="form-row">
									<div class="form-group">
										<label for="category">Category</label> <select id="category"
											name="category" required>
											<option value="">-- Select Category --</option>
											<option value="Car">Car</option>
											<option value="Bike">Bike</option>
										</select>
									</div>
									<div class="form-group">
										<label for="brand">Brand</label> <input type="text" id="brand"
											name="brand" placeholder="e.g. Toyota, Honda" required>
									</div>
								</div>

								<div class="form-row">
									<div class="form-group">
										<label for="model">Model</label> <input type="text" id="model"
											name="model" placeholder="e.g. Camry, Civic" required>
									</div>
									<div class="form-group">
										<label for="year">Year</label> <input type="number" id="year"
											name="year" placeholder="e.g. 2023" required>
									</div>
								</div>
							</div>

							<div class="form-section">
								<h4 class="section-title">Registration & Pricing</h4>
								<div class="form-row">
									<div class="form-group">
										<label for="registrationNumber">Registration Number</label> <input
											type="text" id="registrationNumber" name="registrationNumber"
											placeholder="e.g. ABC-1234" required>
									</div>
									<div class="form-group">
										<label for="dailyRate">Daily Rate (Rs.)</label> <input
											type="number" id="dailyRate" name="dailyRate"
											placeholder="e.g. 50" required>
									</div>
								</div>
							</div>

							<div class="form-section">
								<h4 class="section-title">Technical Specifications</h4>
								<div class="form-row">
									<div class="form-group">
										<label for="fuelType">Fuel Type</label> <select id="fuelType"
											name="fuelType" required>
											<option value="">-- Select Fuel Type --</option>
											<option value="EV">EV</option>
											<option value="Petrol">Petrol</option>
											<option value="Diesel">Diesel</option>
										</select>
									</div>
									<div class="form-group">
										<label for="transmission">Transmission</label> <select
											id="transmission" name="transmission" required>
											<option value="">-- Select Transmission --</option>
											<option value="Automatic">Automatic</option>
											<option value="Manual">Manual</option>
										</select>
									</div>
								</div>

								<div class="form-row">
									<div class="form-group">
										<label for="capacity">Capacity</label> <input type="number"
											id="capacity" name="capacity" placeholder="e.g. 5" required>
									</div>
									<div class="form-group">
										<label for="status">Status</label> <select id="status"
											name="status" required>
											<option value="available">Available</option>
											<option value="rented">Rented</option>
										</select>
									</div>
								</div>
							</div>

							<div class="form-section">
								<h4 class="section-title">Location & Media</h4>
								<div class="form-row">
									<div class="form-group">
										<label for="imageUrl">Image File</label> <input type="file"
											id="imageUrl" name="imageUrl" accept="image/*" required>
									</div>
									<div class="form-group">
										<label for="location">Location</label> <select id="location"
											name="location" required>
											<option value="">-- Select Location --</option>
											<option value="Kathmandu">Kathmandu</option>
											<option value="Lalitpur">Lalitpur</option>
											<option value="Bhaktapur">Bhaktapur</option>
										</select>
									</div>
								</div>
							</div>

							<div class="form-section">
								<h4 class="section-title">Additional Details</h4>
								<div class="form-row">
									<div class="form-group full">
										<label for="description">Description</label>
										<textarea id="description" name="description"
											placeholder="Detailed description of the vehicle..."></textarea>
									</div>
								</div>

								<div class="form-row">
									<div class="form-group full">
										<label for="features">Features</label> <input type="text"
											id="features" name="features"
											placeholder="e.g. GPS, Bluetooth, Leather Seats (comma separated)">
									</div>
								</div>
							</div>

							<div class="form-footer">
								<button type="submit">Add Vehicle</button>
							</div>
						</form>
					</div>
				</div>
			</div>

		</section>

		<!-- Bookings -->
		<section id="booking" class="main">
			<div class="content">
				<div class="content-header">
					<h2 class="section-title">Current Bookings</h2>
				</div>

				<div class="table-container">
					<table class="data-table">
						<thead>
							<tr>
								<th>Booking ID</th>
								<th>Vehicle ID</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th>Pickup Location</th>
								<th>Drop Location</th>
								<th>Rental Days</th>
								<th>Total Price</th>
								<th>Customer ID</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${empty BookingList}">
								<tr>
									<td colspan="5">No rental found or data not loaded
										correctly.</td>
								</tr>
							</c:if>
							<c:forEach items="${BookingList}" var="rent">
								<c:if test="${not empty BookingList }">
									<tr>
										<td>B${rent.rentalId}</td>
										<td>R${rent.vehicleId}</td>
										<td>${rent.startDate}</td>
										<td>${rent.endDate}</td>
										<td>${rent.address}</td>
										<td>${rent.dropAddress}</td>
										<td>${rent.rentalDays}</td>
										<td>Rs.${rent.amount}</td>
										<td>U${rent.userId}</td>
										<td>${rent.status}</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</section>

		<!-- Users -->
		<section id="user" class="main">
		<div class="content-header">
					<h2 class="section-title">Users</h2>
				</div>
			<table class="data-table">
				<thead>
					<tr>
						<th>User ID</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Username</th>
						<th>Date of Birth</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Password</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty UserList}">
						<tr>
							<td colspan="5">No users found or data not loaded correctly.</td>
						</tr>
					</c:if>
					<c:forEach items="${UserList}" var="user">
						<c:if test="${not empty user}">
							<tr>
								<td>U${user.id}</td>
								<td>${user.fname}</td>
								<td>${user.lname}</td>
								<td>${user.uName}</td>
								<td>${user.dob}</td>
								<td>${user.email}</td>
								<td>${user.phone}</td>
								<td>********</td>
								<td>
									<form method="post" action="deleteUser">
										<input type="hidden" name="userId" value="${user.id}" />
										<button type="submit" class="action-btn">Delete</button>
									</form>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</section>


		<!-- Messages -->
		<section id="messageContent" class="main">
		<div class="content-header">
					<h2 class="section-title">Messages</h2>
				</div>
			<table class="data-table">
				<thead>
					<tr>
						<th>Message ID</th>
						<th>From</th>
						<th>Subject</th>
						<th>Date</th>
						<th>Message</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty MessageList}">
						<tr>
							<td colspan="5">No message found or data not loaded
								correctly.</td>
						</tr>
					</c:if>
					<c:forEach items="${MessageList}" var="message">
						<tr>
							<td>M${message.messageId}</td>
							<td>${message.email}</td>
							<td>${message.subject}</td>
							<td>${message.sentAt}</td>
							<td>${message.content}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>

	</div>

</body>
<script type="text/javascript" src="${contextPath}/js/adminSection.js"></script>
<script type="text/javascript" src="${contextPath}/js/popUp.js"></script>
</html>

