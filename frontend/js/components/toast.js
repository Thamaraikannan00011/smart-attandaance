function ensureToastRoot() {
  let root = document.getElementById('toastRoot');
  if (root) return root;
  root = document.createElement('div');
  root.id = 'toastRoot';
  root.style.position = 'fixed';
  root.style.right = '16px';
  root.style.bottom = '16px';
  root.style.zIndex = '9999';
  root.style.display = 'flex';
  root.style.flexDirection = 'column';
  root.style.gap = '8px';
  document.body.appendChild(root);
  return root;
}

function toast(message, tone = 'neutral') {
  const root = ensureToastRoot();
  const el = document.createElement('div');
  el.textContent = message;
  el.style.padding = '10px 12px';
  el.style.borderRadius = '10px';
  el.style.fontSize = '13px';
  el.style.fontWeight = '600';
  el.style.boxShadow = '0 10px 30px rgba(0,0,0,.12)';
  el.style.border = '1px solid rgba(0,0,0,.08)';
  el.style.background = '#fff';
  el.style.color = '#111';
  if (tone === 'success') el.style.borderColor = 'rgba(16,185,129,.35)';
  if (tone === 'error') el.style.borderColor = 'rgba(239,68,68,.35)';
  if (tone === 'info') el.style.borderColor = 'rgba(59,130,246,.35)';

  root.appendChild(el);
  setTimeout(() => el.remove(), 2400);
}

export const toastSuccess = (msg) => toast(msg, 'success');
export const toastError = (msg) => toast(msg, 'error');
export const toastInfo = (msg) => toast(msg, 'info');

