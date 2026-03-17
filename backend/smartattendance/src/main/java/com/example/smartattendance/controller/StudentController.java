package com.example.smartattendance.controller;

import com.example.smartattendance.dto.request.StudentRequest;
import com.example.smartattendance.dto.response.ApiResponse;
import com.example.smartattendance.dto.response.StudentDTO;
import com.example.smartattendance.service.StudentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {
  private final StudentService service;

  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping
  public ApiResponse<List<StudentDTO>> list() {
    return ApiResponse.ok(service.list());
  }

  @PostMapping
  public ApiResponse<StudentDTO> create(@Valid @RequestBody StudentRequest req) {
    return ApiResponse.ok(service.create(req));
  }
}

