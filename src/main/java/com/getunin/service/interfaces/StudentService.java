package com.getunin.service.interfaces;

import com.getunin.dto.StudentRequest;
import com.getunin.entity.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(StudentRequest request);

    Student updateStudent(StudentRequest request, Long id);

    Student getStudentById(Long id);
    Student getStudentByEmail(String email);

}
