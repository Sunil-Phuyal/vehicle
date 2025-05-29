window.addEventListener('DOMContentLoaded', () => {
	// Attach click listeners to all links with a data-target attribute
	document.querySelectorAll('a[data-target]').forEach(link => {
		link.addEventListener('click', e => {
			e.preventDefault();
			const targetId = link.dataset.target;
			activateSection(targetId, link);
		});
	});


	// Attach sidebar toggle
	const minimizeBtn = document.querySelector('.minimize');
	if (minimizeBtn) {
		minimizeBtn.addEventListener('click', toggleSidebar);
	}
});

function toggleSidebar() {
	const sidebar = document.querySelector('.sidebar');
	const icon = document.querySelector('.minimize i');
	sidebar.classList.toggle('collapsed');

	document.querySelectorAll('.main.active-main').forEach(main => {
		if (sidebar.classList.contains('collapsed')) {
			main.classList.add('expanded');
		} else {
			main.classList.remove('expanded');
		}
	});

	icon.classList.toggle('fa-circle-chevron-left');
	icon.classList.toggle('fa-circle-chevron-right');
}

function activateSection(id, link) {
	// Hide all
	document.querySelectorAll('.main').forEach(main => {
		main.classList.remove('active-main', 'expanded');
	});

	const target = document.getElementById(id);
	target.classList.add('active-main');

	if (document.querySelector('.sidebar.collapsed')) {
		target.classList.add('expanded');
	}

	document.querySelectorAll('.sidebar nav ul li a').forEach(a => a.classList.remove('active'));
	link.classList.add('active');
}
