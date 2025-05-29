//Toggle Password 
function togglePassword(inputId, buttonId) {
	const password = document.getElementById(inputId);
	const toggleIcon = document.getElementById(buttonId);
	if (password.type == "password") {
		password.type = "text";
		toggleIcon.classList.remove("fa-eye");
		toggleIcon.classList.add("fa-eye-slash");
	} else {
		password.type = "password";
		toggleIcon.classList.remove("fa-eye-slash");
		toggleIcon.classList.add("fa-eye");
	}
}