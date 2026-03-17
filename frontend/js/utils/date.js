export const today = () => new Date().toISOString().slice(0, 10);

export const formatDateLong = (dateStr) =>
  new Date(dateStr).toLocaleDateString(undefined, {
    weekday: 'long',
    year: 'numeric',
    month: 'long',
    day: 'numeric',
  });

export const formatDate = (dateStr) =>
  new Date(dateStr).toLocaleDateString(undefined, { year: 'numeric', month: 'short', day: '2-digit' });

export const nowDisplay = () =>
  new Date().toLocaleDateString(undefined, { weekday: 'short', month: 'short', day: 'numeric' });

