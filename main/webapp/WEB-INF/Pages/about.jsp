<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/about.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/footer.css" />
<script src="https://kit.fontawesome.com/a63c128ded.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="./component/header.jsp" />
	<div class="container">
		<div class="hero">
			<h1>About Us</h1>
		</div>
		<div class="about-content">
			<div class="why-us">
				<h2>Why Choose Us</h2>
				<p>We are a team of passionate developers committed to
					delivering high-quality web applications that exceed expectations.
					Our mission is to create efficient, scalable, and user-friendly
					software tailored to our clients' needs. With a strong belief in
					continuous learning and staying updated with the latest
					technologies, we’re here to innovate and inspire.</p>
			</div>

			<div class="team">
				<h2 class="about-title">Meet the Team</h2>
				<ul class="team-list">

					<li class="team-member"><img
						src="${contextPath}/assets/bg-img/no-profile.jpg"
						alt="profile-pic">
						<div class="personal-info">
							<p class="name">Yograj Rijal</p>
							<p class="role">Lead Developer</p>
							<p class="bio">Yograj leads our development team with
								unmatched dedication and skill. With a strong command over both
								frontend and backend technologies, he ensures quality delivery
								and smooth collaboration.</p>
							<button class="toggle-btn" aria-label="Toggle bio">
								<i class="fa-solid fa-chevron-down"></i>
							</button>
						</div></li>

					<li class="team-member"><img
						src="${contextPath}/assets/bg-img/no-profile.jpg"
						alt="profile-pic">
						<div class="personal-info">
							<p class="name">Ananta Gurung</p>
							<p class="role">UI/UX Designer</p>
							<p class="bio">Ananta brings creativity and usability
								together to craft exceptional user experiences. Her
								collaborative approach and innovative mindset help us create
								designs that connect with users.</p>
							<button class="toggle-btn" aria-label="Toggle bio">
								<i class="fa-solid fa-chevron-down"></i>
							</button>
						</div></li>

					<li class="team-member"><img
						src="${contextPath}/assets/bg-img/no-profile.jpg"
						alt="profile-pic">
						<div class="personal-info">
							<p class="name">Sunil Phuyal</p>
							<p class="role">Backend Engineer</p>
							<p class="bio">Sunil specializes in backend systems and
								ensures our applications are robust and scalable. His clean and
								secure code powers our core logic and data handling.</p>
							<button class="toggle-btn" aria-label="Toggle bio">
								<i class="fa-solid fa-chevron-down"></i>
							</button>
						</div></li>

					<li class="team-member"><img
						src="${contextPath}/assets/bg-img/no-profile.jpg"
						alt="profile-pic">
						<div class="personal-info">
							<p class="name">Arun Nagarkoti</p>
							<p class="role">UI/UX Designer</p>
							<p class="bio">Arun blends artistic vision with user-centered
								thinking to produce engaging designs. His work strikes the right
								balance between aesthetics and usability.</p>
							<button class="toggle-btn" aria-label="Toggle bio">
								<i class="fa-solid fa-chevron-down"></i>
							</button>
						</div></li>

					<li class="team-member"><img
						src="${contextPath}/assets/bg-img/no-profile.jpg"
						alt="profile-pic">
						<div class="personal-info">
							<p class="name">Pujan Pyoudel</p>
							<p class="role">Frontend Developer</p>
							<p class="bio">Pujan transforms designs into reality with
								responsive, accessible frontend code. His enthusiasm for modern
								frameworks keeps our UI modern and dynamic.</p>
							<button class="toggle-btn" aria-label="Toggle bio">
								<i class="fa-solid fa-chevron-down"></i>
							</button>
						</div></li>

				</ul>

			</div>

		</div>
	</div>
	<jsp:include page="./component/footer.jsp" />
</body>
<script>
    document.addEventListener('DOMContentLoaded', () => {
        const buttons = document.querySelectorAll('.toggle-btn');
        buttons.forEach(btn => {
            btn.addEventListener('click', () => {
                const bio = btn.parentElement.querySelector('.bio');
                const icon = btn.querySelector('i');

                bio.classList.toggle('show');
                icon.classList.toggle('fa-chevron-down');
                icon.classList.toggle('fa-chevron-up');
            });
        });
    });
</script>
</html>
