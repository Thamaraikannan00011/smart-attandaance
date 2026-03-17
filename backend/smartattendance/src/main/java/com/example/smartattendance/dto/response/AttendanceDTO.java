package com.example.smartattendance.dto.response;

import com.example.smartattendance.entity.AttendanceStatus;
import java.time.LocalDate;

public record AttendanceDTO(
    Long id,
    LocalDate date,
    AttendanceStatus status,
    String studentRollNo,
    String studentName,
    String periodName
) {}

