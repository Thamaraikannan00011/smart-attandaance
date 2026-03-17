package com.example.smartattendance.repository;

import com.example.smartattendance.entity.Attendance;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
  @Query("""
      select a from Attendance a
      join fetch a.student s
      left join fetch a.period p
      where a.attendanceDate = :date
      """)
  List<Attendance> findAllByAttendanceDate(@Param("date") LocalDate date);

  @Query("""
      select a from Attendance a
      join fetch a.student s
      left join fetch a.period p
      where a.attendanceDate between :from and :to
        and (:rollNo is null or s.rollNo = :rollNo)
        and (:status is null or a.status = :status)
      order by a.attendanceDate desc, s.rollNo asc
      """)
  List<Attendance> findForReport(
      @Param("from") LocalDate from,
      @Param("to") LocalDate to,
      @Param("rollNo") String rollNo,
      @Param("status") com.example.smartattendance.entity.AttendanceStatus status
  );
}

