export function openModal(id) {
  const el = document.getElementById(id);
  if (!el) return;
  el.classList.add('show');
}

export function closeModal(id) {
  const el = document.getElementById(id);
  if (!el) return;
  el.classList.remove('show');
}

export function initModals() {
  document.querySelectorAll('[data-modal-close]').forEach((btn) => {
    btn.addEventListener('click', () => {
      const overlay = btn.closest('.modal-overlay');
      if (overlay?.id) closeModal(overlay.id);
    });
  });

  document.querySelectorAll('.modal-overlay').forEach((overlay) => {
    overlay.addEventListener('click', (e) => {
      if (e.target === overlay && overlay.id) closeModal(overlay.id);
    });
  });

  document.addEventListener('keydown', (e) => {
    if (e.key !== 'Escape') return;
    document.querySelectorAll('.modal-overlay.show').forEach((overlay) => {
      if (overlay.id) closeModal(overlay.id);
    });
  });
}

