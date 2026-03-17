package com.example.smartattendance.mapper;

import com.example.smartattendance.dto.response.AttendanceDTO;
import com.example.smartattendance.entity.Attendance;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {
  public AttendanceDTO toDto(Attendance a) {
    return new AttendanceDTO(
        a.getId(),
        a.getAttendanceDate(),
        a.getStatus(),
        a.getStudent() != null ? a.getStudent().getRollNo() : null,
        a.getStudent() != null ? a.getStudent().getFullName() : null,
        a.getPeriod() != null ? a.getPeriod().getName() : null
    );
  }
}

