package com.example.smartattendance.service.impl;

import com.example.smartattendance.dto.request.AttendanceRequest;
import com.example.smartattendance.dto.response.AttendanceDTO;
import com.example.smartattendance.dto.response.DailySummaryDTO;
import com.example.smartattendance.entity.Attendance;
import com.example.smartattendance.entity.AttendanceStatus;
import com.example.smartattendance.entity.Period;
import com.example.smartattendance.entity.Student;
import com.example.smartattendance.exception.ResourceNotFoundException;
import com.example.smartattendance.mapper.AttendanceMapper;
import com.example.smartattendance.repository.AttendanceRepository;
import com.example.smartattendance.repository.PeriodRepository;
import com.example.smartattendance.repository.StudentRepository;
import com.example.smartattendance.service.AttendanceService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttendanceServiceImpl implements AttendanceService {
  private final AttendanceRepository attendanceRepo;
  private final StudentRepository studentRepo;
  private final PeriodRepository periodRepo;
  private final AttendanceMapper mapper;

  public AttendanceServiceImpl(
      AttendanceRepository attendanceRepo,
      StudentRepository studentRepo,
      PeriodRepository periodRepo,
      AttendanceMapper mapper
  ) {
    this.attendanceRepo = attendanceRepo;
    this.studentRepo = studentRepo;
    this.periodRepo = periodRepo;
    this.mapper = mapper;
  }

  @Override
  @Transactional
  public AttendanceDTO mark(AttendanceRequest req) {
    Student student = studentRepo.findByRollNo(req.studentRollNo())
        .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + req.studentRollNo()));

    Period period = null;
    if (req.periodId() != null) {
      period = periodRepo.findById(req.periodId())
          .orElseThrow(() -> new ResourceNotFoundException("Period not found: " + req.periodId()));
    }

    Attendance a = new Attendance();
    a.setAttendanceDate(req.date());
    a.setStatus(req.status());
    a.setStudent(student);
    a.setPeriod(period);

    return mapper.toDto(attendanceRepo.save(a));
  }

  @Override
  public List<AttendanceDTO> listByDate(LocalDate date) {
    return attendanceRepo.findAllByAttendanceDate(date).stream().map(mapper::toDto).toList();
  }

  @Override
  public DailySummaryDTO summary(LocalDate date) {
    long total = studentRepo.count();
    List<Attendance> rows = attendanceRepo.findAllByAttendanceDate(date);

    long present = rows.stream().filter(a -> a.getStatus() == AttendanceStatus.PRESENT).count();
    long late = rows.stream().filter(a -> a.getStatus() == AttendanceStatus.LATE).count();
    long absent = Math.max(0, total - present - late);
    int pct = total == 0 ? 0 : (int) Math.round((present * 100.0) / total);

    return new DailySummaryDTO(date, total, present, late, absent, pct);
  }
}

