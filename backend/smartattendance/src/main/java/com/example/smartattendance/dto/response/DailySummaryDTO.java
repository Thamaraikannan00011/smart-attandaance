package com.example.smartattendance.dto.response;

import java.time.LocalDate;

public record DailySummaryDTO(
    LocalDate date,
    long totalStudents,
    long present,
    long late,
    long absent,
    int percentage
) {}

