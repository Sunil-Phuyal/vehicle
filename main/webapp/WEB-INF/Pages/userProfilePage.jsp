<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>
<link rel="stylesheet" href="${contextPath}/css/global.css">
<link rel="stylesheet" href="${contextPath}/css/userprofile.css">
<script src="https://kit.fontawesome.com/a63c128ded.js"
	crossorigin="anonymous"></script>
</head>
<body>

	<div class="container">
		<div class="sidebar">
			<a href="${contextPath}/" class="back">Back to Home</a>
			<h3>Account</h3>
			<p onclick="showSection('profileSection')">Profile</p>
			<p onclick="showSection('passwordSection')">Password</p>
			<p onclick="showSection('historySection')">Rental History</p>
			<form method="POST" action="${contextPath}/deleteAccount"
				onsubmit="return confirm('Are you sure you want to delete your account?');">
				<input type="hidden" name="username" value="${user.uName}" />
				<button class="danger-btn">Delete Account</button>
			</form>
		</div>

		<div class="content">
			<!-- Profile Section -->
			<div id="profileSection" class="section active">
				<h2>
					Profile Info
					<button class="edit-toggle" onclick="toggleEdit('profile')">Edit</button>
				</h2>
				<form id="profileForm" method="POST"
					action="${contextPath}/userUpdate">
					<input type="hidden" name="username" value="${user.uName}" /> <input
						type="hidden" name="role" value="${user.role}" /> <label>First
						Name:</label> <input type="text" name="fname" value="${user.fname}"
						disabled required /> <label>Last Name:</label> <input type="text"
						name="lname" value="${user.lname}" disabled required /> <label>Email:</label>
					<input type="email" name="email" value="${user.email}" disabled
						required /> <label>Phone:</label> <input type="text" name="phone"
						value="${user.phone}" disabled required />
					<button type="submit" id="saveProfile" style="display: none;">Save</button>
				</form>
				<c:if test="${status == 'updated'}">
					<div id="popup" class="popup">
						User Info successfully updated!
						<button onclick="closePopup('popup')">OK</button>
					</div>
				</c:if>

				<c:if test="${not empty passwordStatus}">
					<div id="popup" class="popup">
					     ${passwordStatus}
						<button onclick="closePopup('popup')">OK</button>
					</div>
				</c:if>
			</div>

			<!-- Password Section -->
			<div id="passwordSection" class="section">
				<h2>Password</h2>

				<!-- Entire form hidden initially -->
				<form id="passwordForm" method="POST"
					action="${contextPath}/updatePassword">
					<input type="hidden" name="username" value="${user.uName}" /> <label>Old
						Password:</label>
					<div class="password-wrapper">
						<input type="password" name="oldPassword" id="oldPassword" /> <i
							class="fa fa-eye" id="toggleIcon1"
							onclick="togglePassword('oldPassword', 'toggleIcon1')"
							style="color: var(--gray-900)"></i>
					</div>

					<label>New Password:</label>
					<div class="password-wrapper">
						<input type="password" name="newPassword" id="newPassword" /> <i
							class="fa fa-eye" id="toggleIcon2"
							onclick="togglePassword('newPassword', 'toggleIcon2')"
							style="color: var(--gray-900)"></i>
					</div>

					<button type="submit" id="savePassword">Save</button>
				</form>
				<c:if test="${not empty sessionScope.passwordUpdatedAt}">
					<p class="timestamp">
						Last updated:
						<fmt:formatDate value="${sessionScope.passwordUpdatedAt}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</p>
				</c:if>

			</div>


			<!-- Rental History Section -->
			<div id="historySection" class="section">
				<h2>Rental History</h2>
				<table class="booking-table">
					<thead>
						<tr>
							<th>Booking ID</th>
							<th>Vehicle Name</th>
							<th>Amount</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
							<c:if test="${empty rentList}">
							<tr>
								<td colspan="4">Not Data Found</td>
							</tr>
						</c:if>

							<c:if test="${not empty rentList}">
								<c:forEach items="${rentList}" var="rent">
								<tr>
									<td>${rent.rentalId}</td>
									<td>${rent.vehicleName}</td>
									<td>Rs.${rent.amount}</td>
									<td>${rent.status}</td>
								</tr>
							</c:forEach>
							</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script>
    function showSection(id) {
        document.querySelectorAll('.section').forEach(sec => sec.classList.remove('active'));
        document.getElementById(id).classList.add('active');
    }
    function toggleEdit(section) {
        if (section === 'profile') {
            const inputs = document.querySelectorAll('#profileForm input:not([type=hidden])');
            const saveBtn = document.getElementById('saveProfile');
            inputs.forEach(inp => inp.disabled = false);
            saveBtn.style.display = 'inline-block';
            document.querySelector('#profileSection .edit-toggle').style.display = 'none';
        } 
    }
    
    function closePopup(id) {
        document.getElementById(id).style.display = 'none';
    }
    </script>
	<script type="text/javascript"
		src="${contextPath}/js/passwordToggle.js"></script>
</body>
</html>
