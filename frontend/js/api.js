async function request(path, options = {}) {
  const res = await fetch(path, {
    headers: {
      'Content-Type': 'application/json',
      ...(options.headers || {}),
    },
    ...options,
  });

  const text = await res.text();
  let body;
  try {
    body = text ? JSON.parse(text) : null;
  } catch {
    body = text;
  }

  if (!res.ok) {
    const message = body?.message || body?.error || `Request failed (${res.status})`;
    throw new Error(message);
  }

  return body;
}

export async function listStudents() {
  const body = await request('/api/students', { method: 'GET', headers: {} });
  return body?.data || [];
}

export async function createStudent({ rollNo, fullName }) {
  const body = await request('/api/students', {
    method: 'POST',
    body: JSON.stringify({ rollNo, fullName }),
  });
  return body?.data;
}

export async function updateStudent(id, { rollNo, fullName }) {
  const body = await request(`/api/students/${id}`, {
    method: 'PUT',
    body: JSON.stringify({ rollNo, fullName }),
  });
  return body?.data;
}

export async function deleteStudent(id) {
  await request(`/api/students/${id}`, { method: 'DELETE', headers: {} });
}

export async function markAttendance({ date, studentRollNo, status, periodId }) {
  const body = await request('/api/attendance', {
    method: 'POST',
    body: JSON.stringify({ date, studentRollNo, status, periodId }),
  });
  return body?.data;
}

export async function listAttendanceByDate(date) {
  const body = await request(`/api/attendance?date=${encodeURIComponent(date)}`, { method: 'GET', headers: {} });
  return body?.data || [];
}

export async function reportRecords({ from, to, studentRollNo, status }) {
  const params = new URLSearchParams({ from, to });
  if (studentRollNo) params.set('studentRollNo', studentRollNo);
  if (status) params.set('status', status);
  const body = await request(`/api/reports/records?${params.toString()}`, { method: 'GET', headers: {} });
  return body?.data || [];
}

