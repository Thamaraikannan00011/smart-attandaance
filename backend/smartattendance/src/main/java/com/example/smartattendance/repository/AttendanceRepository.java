package com.example.smartattendance.repository;

import com.example.smartattendance.entity.Attendance;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
  List<Attendance> findAllByAttendanceDate(LocalDate attendanceDate);
}

