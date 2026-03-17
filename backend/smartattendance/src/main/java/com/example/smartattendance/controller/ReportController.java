package com.example.smartattendance.controller;

import com.example.smartattendance.dto.response.ApiResponse;
import com.example.smartattendance.dto.response.AttendanceDTO;
import com.example.smartattendance.entity.AttendanceStatus;
import com.example.smartattendance.mapper.AttendanceMapper;
import com.example.smartattendance.repository.AttendanceRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
  private final AttendanceRepository attendanceRepo;
  private final AttendanceMapper attendanceMapper;

  public ReportController(AttendanceRepository attendanceRepo, AttendanceMapper attendanceMapper) {
    this.attendanceRepo = attendanceRepo;
    this.attendanceMapper = attendanceMapper;
  }

  @GetMapping("/health")
  public ApiResponse<Map<String, String>> health() {
    return ApiResponse.ok(Map.of("status", "ok"));
  }

  @GetMapping("/records")
  @Transactional(readOnly = true)
  public ApiResponse<List<AttendanceDTO>> records(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
      @RequestParam(required = false) String studentRollNo,
      @RequestParam(required = false) AttendanceStatus status
  ) {
    return ApiResponse.ok(
        attendanceRepo.findForReport(from, to, studentRollNo, status).stream().map(attendanceMapper::toDto).toList()
    );
  }
}

