package com.getunin.controller;


import com.getunin.dto.CoordinatorRequest;
import com.getunin.dto.EducatorRequest;
import com.getunin.service.interfaces.CoordinatorService;
import com.getunin.service.interfaces.EducatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/educator")
public class EducatorController {

    @Autowired
    private EducatorService educatorService;


    @PostMapping("/create")
    public ResponseEntity<Object> createEducator(@Valid @RequestBody EducatorRequest request){
        return ResponseEntity.ok(educatorService.createEducator(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateEducator(@Valid @RequestBody EducatorRequest request, @PathVariable Long id){
        return ResponseEntity.ok(educatorService.updateEducator(request,id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEducatorById(@PathVariable Long id){
        return ResponseEntity.ok(educatorService.getEducatorById(id));
    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> getCoordinatorById(@PathVariable String email){
        return ResponseEntity.ok(educatorService.getEducatorByEmail(email));
    }

    @GetMapping("/all/")
    public ResponseEntity<Object> getAllEducator(){
        return ResponseEntity.ok(educatorService.getAllEducator());
    }




}
