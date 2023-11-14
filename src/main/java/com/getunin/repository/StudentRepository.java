package com.getunin.repository;

import com.getunin.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    Student findStudentByUser_Email(String email);

}
