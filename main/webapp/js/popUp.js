document.addEventListener('DOMContentLoaded', () => {
  const popup = document.getElementById('popup');
  const closeBtn = document.getElementById('closePopup');

  if(popup && closeBtn) {
    closeBtn.addEventListener('click', () => {
      popup.style.display = 'none';
    });
  }
});