package com.example.smartattendance.controller;

import com.example.smartattendance.dto.request.AttendanceRequest;
import com.example.smartattendance.dto.response.ApiResponse;
import com.example.smartattendance.dto.response.AttendanceDTO;
import com.example.smartattendance.dto.response.DailySummaryDTO;
import com.example.smartattendance.service.AttendanceService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
  private final AttendanceService service;

  public AttendanceController(AttendanceService service) {
    this.service = service;
  }

  @PostMapping
  public ApiResponse<AttendanceDTO> mark(@Valid @RequestBody AttendanceRequest req) {
    return ApiResponse.ok(service.mark(req));
  }

  @GetMapping
  public ApiResponse<List<AttendanceDTO>> listByDate(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
  ) {
    return ApiResponse.ok(service.listByDate(date));
  }

  @GetMapping("/summary")
  public ApiResponse<DailySummaryDTO> summary(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
  ) {
    return ApiResponse.ok(service.summary(date));
  }
}

