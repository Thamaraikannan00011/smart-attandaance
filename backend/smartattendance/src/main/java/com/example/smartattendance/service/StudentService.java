package com.example.smartattendance.service;

import com.example.smartattendance.dto.request.StudentRequest;
import com.example.smartattendance.dto.response.StudentDTO;
import java.util.List;

public interface StudentService {
  List<StudentDTO> list();

  StudentDTO create(StudentRequest req);

  StudentDTO update(long id, StudentRequest req);

  void delete(long id);
}

