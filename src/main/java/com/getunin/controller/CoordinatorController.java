package com.getunin.controller;


import com.getunin.dto.CoordinatorRequest;
import com.getunin.dto.StudentRequest;
import com.getunin.service.interfaces.CoordinatorService;
import com.getunin.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/coordinator")
public class CoordinatorController {

    @Autowired
    private CoordinatorService coordinatorService;


    @PostMapping("/create")
    public ResponseEntity<Object> createCoordinator(@Valid @RequestBody CoordinatorRequest request){
        return ResponseEntity.ok(coordinatorService.createCoordinator(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCoordinator(@Valid @RequestBody CoordinatorRequest request, @PathVariable Long id){
        return ResponseEntity.ok(coordinatorService.updateCoordinator(request,id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCoordinatorById(@PathVariable Long id){
        return ResponseEntity.ok(coordinatorService.getCoordinatorById(id));
    }

    @GetMapping("/all/")
    public ResponseEntity<Object> getAllCoordinator(){
        return ResponseEntity.ok(coordinatorService.getAllCoordinator());
    }


}
