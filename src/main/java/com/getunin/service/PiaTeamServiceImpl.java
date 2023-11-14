package com.getunin.service;

import com.getunin.dto.PiaTeamRequest;
import com.getunin.entity.PiaTeam;
import com.getunin.entity.Student;
import com.getunin.exception.listexceptions.ConflictException;
import com.getunin.exception.listexceptions.NotFoundException;
import com.getunin.modelview.ResponseMessage;
import com.getunin.repository.PiaTeamRepository;
import com.getunin.service.interfaces.NotificationService;
import com.getunin.service.interfaces.PiaTeamService;
import com.getunin.service.interfaces.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PiaTeamServiceImpl implements PiaTeamService {

    private final PiaTeamRepository piaTeamRepository;

    private final StudentService studentService;

    private final NotificationService notificationService;

    public PiaTeamServiceImpl(PiaTeamRepository piaTeamRepository, StudentService studentService, NotificationService notificationService) {
        this.piaTeamRepository = piaTeamRepository;
        this.studentService = studentService;
        this.notificationService = notificationService;
    }


    @Override
    public PiaTeam createPiaTeam(PiaTeamRequest request) {

        Optional<PiaTeam> piaTeamOptional = Optional.ofNullable(piaTeamRepository.findPiaTeamsByNameProyect(request.getNameProyect()));

        if(piaTeamOptional.isEmpty()) {
            PiaTeam piaTeam = new PiaTeam();

            piaTeam.setDescription(request.getDescription());
            piaTeam.setNameProyect(request.getNameProyect());
            piaTeam.setTypeProyect(request.getTypeProyect());
            piaTeam.setStudentOne(request.getStudentOne());

            return piaTeamRepository.save(piaTeam);
        }else{
            throw new ConflictException("Ya existe un proyecto con este nombre");
        }
    }

    @Override
    public PiaTeam updatePiaTeam(PiaTeamRequest request, Long id) {
        Optional<PiaTeam> piaId = piaTeamRepository.findById(id);
        Optional<PiaTeam> piaTeamOptional = Optional.ofNullable(piaTeamRepository.findPiaTeamsByNameProyect(request.getNameProyect()));

        if (piaId.isPresent()){
            if(piaTeamOptional.isPresent() &&
                    Objects.equals(piaId.get().getId(),piaTeamOptional.get().getId())) {

            PiaTeam piaTeam = piaId.get();

            piaTeam.setDescription(request.getDescription());
            piaTeam.setNameProyect(request.getNameProyect());
            piaTeam.setTypeProyect(request.getTypeProyect());

            return piaTeamRepository.save(piaTeam);

            }else{
                throw new ConflictException("Ya existe un proyecto con este nombre");
            }
        }else{
            throw new NotFoundException("No se encontro ningun proyecto por este id");
        }

    }

    @Override
    public PiaTeam getPiaTeamById(Long id) {
        Optional<PiaTeam> piaId = piaTeamRepository.findById(id);

        if (piaId.isPresent()){
            return piaId.get();
        }else{
            throw new NotFoundException("No se encontro ningun proyecto por este id");
        }
    }

    @Override
    public PiaTeam getPiaTeamByName(String name) {
        Optional<PiaTeam> piaId = Optional.ofNullable(piaTeamRepository.findPiaTeamsByNameProyect(name));

        if (piaId.isPresent()){
            return piaId.get();
        }else{
            throw new NotFoundException("No se encontro ningun proyecto por este id");
        }
    }

    @Override
    public ResponseMessage deletePiaById(Long id) {
        Optional<PiaTeam> piaId = piaTeamRepository.findById(id);

        if (piaId.isPresent()){
            piaTeamRepository.delete(piaId.get());
            return new ResponseMessage("Se ha eliminado el proyecto");
        }else{
            throw new NotFoundException("No se encontro ningun proyecto por este id");
        }
    }

    @Override
    public ResponseMessage inviteStudentsToProject(String email, PiaTeam team){
        Student student = studentService.getStudentByEmail(email);

        notificationService.sendNotificationOfProject(team,student);

        return new ResponseMessage("Invitación de proyecto enviada");
    }

    @Override
    public ResponseMessage acceptInvitation(PiaTeam team, Student studentRequest){
        Student student = studentService.getStudentByEmail(email);

        notificationService.sendNotificationOfProject(team,student);

        return new ResponseMessage("Invitación de proyecto enviada");
    }


    @Override
    public List<PiaTeam> getAllPiaTeamByObject(PiaTeamRequest request, Long id) {
        return null;
    }
}
