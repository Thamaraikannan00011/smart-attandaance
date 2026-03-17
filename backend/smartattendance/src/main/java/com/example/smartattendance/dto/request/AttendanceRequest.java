package com.example.smartattendance.dto.request;

import com.example.smartattendance.entity.AttendanceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AttendanceRequest(
    @NotNull LocalDate date,
    @NotBlank String studentRollNo,
    @NotNull AttendanceStatus status,
    Long periodId
) {}

