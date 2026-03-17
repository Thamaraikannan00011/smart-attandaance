package com.example.smartattendance.service;

import com.example.smartattendance.dto.request.AttendanceRequest;
import com.example.smartattendance.dto.response.AttendanceDTO;
import com.example.smartattendance.dto.response.DailySummaryDTO;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
  AttendanceDTO mark(AttendanceRequest req);

  List<AttendanceDTO> listByDate(LocalDate date);

  DailySummaryDTO summary(LocalDate date);
}

