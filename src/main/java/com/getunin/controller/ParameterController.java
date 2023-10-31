package com.getunin.controller;


import com.getunin.dto.ParameterRequest;

import com.getunin.service.interfaces.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/parameters")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;


    @PostMapping("/create")
    public ResponseEntity<Object> createParameterController(@Valid @RequestBody ParameterRequest request){
        return ResponseEntity.ok(parameterService.createParameter(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateParameterController(@Valid @RequestBody ParameterRequest request, @PathVariable Long id){
        return ResponseEntity.ok(parameterService.updateParameter(request,id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getParameterByIdController(@PathVariable Long id){
        return ResponseEntity.ok(parameterService.getParameterById(id));
    }

    @GetMapping("/all/{parameterRelationShip}")
    public ResponseEntity<Object> getAllParameterByRelationShipController(@PathVariable Long parameterRelationShip ){
        return ResponseEntity.ok(parameterService.getAllParameterByRelationShip(parameterRelationShip));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteParameterByIdController(@PathVariable Long id){
        return ResponseEntity.ok(parameterService.deleteParameterById(id));
    }
}

