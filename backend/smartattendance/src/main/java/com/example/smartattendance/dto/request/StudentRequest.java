package com.example.smartattendance.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudentRequest(
    @NotBlank @Size(max = 32) String rollNo,
    @NotBlank @Size(max = 200) String fullName
) {}

