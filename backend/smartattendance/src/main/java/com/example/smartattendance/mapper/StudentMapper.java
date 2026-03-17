package com.example.smartattendance.mapper;

import com.example.smartattendance.dto.response.StudentDTO;
import com.example.smartattendance.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
  public StudentDTO toDto(Student s) {
    return new StudentDTO(s.getId(), s.getRollNo(), s.getFullName());
  }
}

