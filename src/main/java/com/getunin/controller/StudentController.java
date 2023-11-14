package com.getunin.controller;

import com.getunin.dto.StudentRequest;
import com.getunin.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/create")
    public ResponseEntity<Object> creaStudent(@Valid @RequestBody StudentRequest request){
        return ResponseEntity.ok(studentService.createStudent(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> UpdateStudent(@Valid @RequestBody StudentRequest request, @PathVariable Long id){
        return ResponseEntity.ok(studentService.updateStudent(request,id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }


}
