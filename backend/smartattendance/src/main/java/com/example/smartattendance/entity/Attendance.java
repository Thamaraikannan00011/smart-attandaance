package com.example.smartattendance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "attendance_date", nullable = false)
  private LocalDate attendanceDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 20)
  private AttendanceStatus status;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "student_id", nullable = false)
  private Student student;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "period_id")
  private Period period;

  public Long getId() {
    return id;
  }

  public LocalDate getAttendanceDate() {
    return attendanceDate;
  }

  public void setAttendanceDate(LocalDate attendanceDate) {
    this.attendanceDate = attendanceDate;
  }

  public AttendanceStatus getStatus() {
    return status;
  }

  public void setStatus(AttendanceStatus status) {
    this.status = status;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Period getPeriod() {
    return period;
  }

  public void setPeriod(Period period) {
    this.period = period;
  }
}

