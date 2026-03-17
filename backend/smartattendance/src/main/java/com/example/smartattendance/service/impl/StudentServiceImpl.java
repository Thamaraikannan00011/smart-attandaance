package com.example.smartattendance.service.impl;

import com.example.smartattendance.dto.request.StudentRequest;
import com.example.smartattendance.dto.response.StudentDTO;
import com.example.smartattendance.entity.Student;
import com.example.smartattendance.exception.ResourceNotFoundException;
import com.example.smartattendance.mapper.StudentMapper;
import com.example.smartattendance.repository.StudentRepository;
import com.example.smartattendance.service.StudentService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {
  private final StudentRepository repo;
  private final StudentMapper mapper;

  public StudentServiceImpl(StudentRepository repo, StudentMapper mapper) {
    this.repo = repo;
    this.mapper = mapper;
  }

  @Override
  public List<StudentDTO> list() {
    return repo.findAll().stream().map(mapper::toDto).toList();
  }

  @Override
  @Transactional
  public StudentDTO create(StudentRequest req) {
    Student s = new Student();
    s.setRollNo(req.rollNo());
    s.setFullName(req.fullName());
    return mapper.toDto(repo.save(s));
  }

  @Override
  @Transactional
  public StudentDTO update(long id, StudentRequest req) {
    Student s = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found: " + id));
    s.setRollNo(req.rollNo());
    s.setFullName(req.fullName());
    return mapper.toDto(repo.save(s));
  }

  @Override
  @Transactional
  public void delete(long id) {
    if (!repo.existsById(id)) {
      throw new ResourceNotFoundException("Student not found: " + id);
    }
    repo.deleteById(id);
  }
}

