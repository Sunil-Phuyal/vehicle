<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Edit Vehicle</title>
<script src="https://kit.fontawesome.com/a63c128ded.js"
	crossorigin="anonymous"></script>
</head>
<style>
/* edit-vehicle.css */
:root {
	--primary: #0d6efd;
	--primary-dark: #0a58ca;
	--white: #ffffff;
	--gray-100: #f8f9fa;
	--gray-200: #e9ecef;
	--gray-300: #dee2e6;
	--text-primary: #212529;
	--text-secondary: #6c757d;
	--radius: 8px;
	--shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #696f74;
	color: var(--text-primary);
}

.main {
	display: flex;
	width: 100%;
	padding: 2rem 1rem;
}

.vehicle-form-container {
	background-color: var(--gray-300);
	padding: 2rem;
	border-radius: var(--radius);
	max-width: 900px;
	margin: auto;
}

.form-header {
	margin-bottom: 1.5rem;
	text-align: center;
}

.form-header h3 {
	font-size: 2rem;
}

.form-body form {
	display: flex;
	flex-direction: column;
	gap: 2rem;
}

.form-section {
	background-color: var(--gray-100);
	padding: 1.5rem;
	border-radius: var(--radius);
	border: 1px solid var(--gray-200);
}

.section-title {
	font-size: 1.25rem;
	font-weight: 600;
	margin-bottom: 1rem;
	color: var(--text-secondary);
}

.form-row {
	display: flex;
	flex-wrap: wrap;
	gap: 1.5rem;
}

.form-group {
	flex: 1;
	display: flex;
	flex-direction: column;
}

.form-group.full {
	flex: 100%;
}

label {
	font-weight: 600;
	margin-bottom: 0.5rem;
	color: var(--text-secondary);
}

input[type="text"], input[type="number"], input[type="file"], select,
	textarea {
	padding: 0.65rem 1rem;
	font-size: 1rem;
	border: 1px solid var(--gray-300);
	border-radius: var(--radius);
	background-color: var(--white);
	color: var(--text-primary);
	transition: border-color 0.2s ease;
}

input:focus, select:focus, textarea:focus {
	outline: none;
	border-color: var(--primary);
}

textarea {
	resize: vertical;
	min-height: 100px;
}

small {
	margin-top: 0.5rem;
	font-size: 0.9rem;
	color: var(--text-secondary);
}

.form-footer {
	text-align: center;
}

.form-footer button {
	background-color: var(--primary);
	color: var(--white);
	padding: 0.75rem 2rem;
	font-size: 1rem;
	border: none;
	border-radius: var(--radius);
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.form-footer button:hover {
	background-color: var(--primary-dark);
}

.back {
	margin: 1rem 0 0 2rem;
}

.back a {
	background: var(--text-primary);
	color: var(--white);
	text-decoration: none;
	padding: .5rem 1rem;
	border-radius: 12px;
	font-weight: bold;
	transition: background 0.3s ease;
}

.back a:hover {
	background: var(--primary-dark);
}
</style>
<body>
	<div class="back">
		<a href="${contextPath}/Dashboard">Back to Dashboard</a>
	</div>
	<section class="main">

		<div class="vehicle-form-container">
			<div class="vehicle-form-wrapper">
				<div class="form-header">
					<h3>Edit Vehicle Information</h3>
				</div>

				<div class="form-body">
					<form action="UpdateVehicle" method="POST"
						enctype="multipart/form-data">
						<input type="hidden" name="id" value="${vehicle.id}" />

						<div class="form-section">
							<h4 class="section-title">Basic Information</h4>
							<div class="form-row">
								<div class="form-group">
									<label for="category">Category</label> <select id="category"
										name="category" required>
										<option value="">-- Select Category --</option>
										<option value="Car"
											${vehicle.category.toLowerCase() == 'car' ? 'selected' : ''}>Car</option>
										<option value="Bike"
											${vehicle.category.toLowerCase() == 'bike' ? 'selected' : ''}>Bike</option>
									</select>
								</div>
								<div class="form-group">
									<label for="brand">Brand</label> <input type="text" id="brand"
										name="brand" value="${vehicle.brand}" required>
								</div>
							</div>

							<div class="form-row">
								<div class="form-group">
									<label for="model">Model</label> <input type="text" id="model"
										name="model" value="${vehicle.model}" required>
								</div>
								<div class="form-group">
									<label for="year">Year</label> <input type="number" id="year"
										name="year" value="${vehicle.year}" required>
								</div>
							</div>
						</div>

						<div class="form-section">
							<h4 class="section-title">Registration & Pricing</h4>
							<div class="form-row">
								<div class="form-group">
									<label for="registrationNumber">Registration Number</label> <input
										type="text" id="registrationNumber" name="registrationNumber"
										value="${vehicle.registrationNumber}" required>
								</div>
								<div class="form-group">
									<label for="dailyRate">Daily Rate (Rs.)</label> <input
										type="number" id="dailyRate" name="dailyRate"
										value="${vehicle.dailyRate}" required>
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
										<option value="EV"
											${vehicle.fuelType == 'EV' ? 'selected' : ''}>EV</option>
										<option value="Petrol"
											${vehicle.fuelType == 'Petrol' ? 'selected' : ''}>Petrol</option>
										<option value="Diesel"
											${vehicle.fuelType == 'Diesel' ? 'selected' : ''}>Diesel</option>
									</select>
								</div>
								<div class="form-group">
									<label for="transmission">Transmission</label> <select
										id="transmission" name="transmission" required>
										<option value="">-- Select Transmission --</option>
										<option value="Automatic"
											${vehicle.transmission == 'Automatic' ? 'selected' : ''}>Automatic</option>
										<option value="Manual"
											${vehicle.transmission == 'Manual' ? 'selected' : ''}>Manual</option>
									</select>
								</div>
							</div>

							<div class="form-row">
								<div class="form-group">
									<label for="capacity">Capacity</label> <input type="number"
										id="capacity" name="capacity" value="${vehicle.capacity}"
										required>
								</div>
								<div class="form-group">
									<label for="status">Status</label> <select id="status"
										name="status" required>
										<option value="available"
											${vehicle.status == 'available' ? 'selected' : ''}>Available</option>
										<option value="rented"
											${vehicle.status == 'rented' ? 'selected' : ''}>rented</option>
									</select>
								</div>
							</div>
						</div>

						<div class="form-section">
							<h4 class="section-title">Location & Media</h4>
							<div class="form-row">
								<div class="form-group">
									<label for="imageUrl">Change Image (optional)</label> <input
										type="file" id="imageUrl" name="imageUrl" accept="image/*">
									<small>Current Image:${vehicle.imageUrl}</small> 
									<input type="hidden" name="existingImageUrl"
										value="${vehicle.imageUrl}">
								</div>
								<div class="form-group">
									<label for="location">Location</label> <select id="location"
										name="location" required>
										<option value="">-- Select Location --</option>
										<option value="Kathmandu"
											${vehicle.location == 'Kathmandu' ? 'selected' : ''}>Kathmandu</option>
										<option value="Lalitpur"
											${vehicle.location == 'Lalitpur' ? 'selected' : ''}>Lalitpur</option>
										<option value="Bhaktapur"
											${vehicle.location == 'Bhaktapur' ? 'selected' : ''}>Bhaktapur</option>
									</select>
								</div>
							</div>
						</div>

						<div class="form-section">
							<h4 class="section-title">Additional Details</h4>
							<div class="form-row">
								<div class="form-group full">
									<label for="description">Description</label>
									<textarea id="description" name="description">${vehicle.description}</textarea>
								</div>
							</div>

							<div class="form-row">
								<div class="form-group full">
									<label for="features">Features</label> <input type="text"
										id="features" name="features" value="${vehicle.features}">
								</div>
							</div>
						</div>

						<div class="form-footer">
							<button type="submit">Update Vehicle</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>