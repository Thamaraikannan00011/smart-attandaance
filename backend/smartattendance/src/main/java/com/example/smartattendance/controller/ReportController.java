package com.example.smartattendance.controller;

import com.example.smartattendance.dto.response.ApiResponse;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
  @GetMapping("/health")
  public ApiResponse<Map<String, String>> health() {
    return ApiResponse.ok(Map.of("status", "ok"));
  }
}

