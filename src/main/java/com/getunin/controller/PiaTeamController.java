package com.getunin.controller;

import com.getunin.dto.PiaTeamRequest;
import com.getunin.dto.StudentRequest;
import com.getunin.service.interfaces.PiaTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/project")
public class PiaTeamController {

    @Autowired
    private PiaTeamService piaTeamService;

    @PostMapping("/create")
    public ResponseEntity<Object> createPiaTeam(@Valid @RequestBody PiaTeamRequest request){
        return ResponseEntity.ok(piaTeamService.createPiaTeam(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePiaTeam(@Valid @RequestBody PiaTeamRequest request, @PathVariable Long id){
        return ResponseEntity.ok(piaTeamService.updatePiaTeam(request,id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPiaTeamById(@PathVariable Long id){
        return ResponseEntity.ok(piaTeamService.getPiaTeamById(id));
    }

    @PostMapping("/invite")
    public ResponseEntity<Object> invitePiaTeam(@Valid @RequestBody PiaTeamRequest request){
        return ResponseEntity.ok(piaTeamService.inviteStudentsToProject(request.getEmailTo(),request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePiaTeamById(@PathVariable Long id){
        return ResponseEntity.ok(piaTeamService.deletePiaById(id));
    }
}
