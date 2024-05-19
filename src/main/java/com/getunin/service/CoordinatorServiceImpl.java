package com.getunin.service;

import com.getunin.dto.CoordinatorRequest;
import com.getunin.dto.JwtRequest;

import com.getunin.entity.*;
import com.getunin.exception.listexceptions.NotFoundException;
import com.getunin.repository.CoordinatorRepository;
import com.getunin.service.interfaces.CoordinatorService;
import com.getunin.service.interfaces.ExceptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class CoordinatorServiceImpl implements CoordinatorService {

    private final CoordinatorRepository coordinatorRepository;

    private final JwtUserDetailsService jwtUserDetailsService;

    private final ExceptionService exceptionService;

    public CoordinatorServiceImpl(CoordinatorRepository coordinatorRepository, JwtUserDetailsService jwtUserDetailsService, ExceptionService exceptionService) {
        this.coordinatorRepository = coordinatorRepository;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.exceptionService = exceptionService;
    }

    @Override
    public Coordinator createCoordinator(CoordinatorRequest request) {

        User user = createJwtRequest(request, null);

        Coordinator coordinator = new Coordinator();
        coordinator.setUser(user);
        coordinator.setUniversity(new University(1L));
        return coordinatorRepository.save(coordinator);
    }

    @Override
    public Coordinator updateCoordinator(CoordinatorRequest request,Long id) {
        Optional<Coordinator> coordinator = coordinatorRepository.findById(id);

        if(coordinator.isPresent()){
            User userUpdate = createJwtRequest(request,coordinator.get().getUser().getId() );

            Coordinator coordinatorUpdate = coordinator.get();

            coordinatorUpdate.setUser(userUpdate);
            return coordinatorRepository.save(coordinatorUpdate);

        }else {
            exceptionService.createException(0L, String.valueOf(NotFoundException.class),"updateCoordinator",request);
            throw new NotFoundException("id de coordinador no encontrado");
        }
    }

    @Override
    public Coordinator getCoordinatorById(Long id) {

        Optional<Coordinator> coordinator = coordinatorRepository.findById(id);

        if(coordinator.isPresent()){
            return coordinator.get();
        }else {
            exceptionService.createException(0L, String.valueOf(NotFoundException.class),"getCoordinatorById",null);
            throw new NotFoundException("id de coordinador no encontrado");
        }
    }

    @Override
    public Coordinator getCoordinatorByEmail(String email) {

        Optional<Coordinator> coordinator = Optional.ofNullable(coordinatorRepository.findByUser_Email(email));

        if(coordinator.isPresent()){
            return coordinator.get();
        }else {
            exceptionService.createException(0L, String.valueOf(NotFoundException.class),"getCoordinatorByEmail",null);
            throw new NotFoundException("id de coordinador no encontrado");
        }
    }

    @Override
    public List<Coordinator> getAllCoordinator() {

        List<Coordinator> coordinators = coordinatorRepository.findAll();

        if(coordinators.isEmpty()){
            exceptionService.createException(0L, String.valueOf(NotFoundException.class),"getCoordinatorByEmail",null);
            throw new NotFoundException("id de coordinador no encontrado");
        }else {
            return coordinators;
        }
    }

    private User createJwtRequest(CoordinatorRequest request, Long id){
        JwtRequest jwtRequest = new JwtRequest();

        jwtRequest.setIdentificationType(request.getIdentificationType());
        jwtRequest.setIdentification(request.getIdentification());
        jwtRequest.setFirstName(request.getFirstName());
        jwtRequest.setLastName(request.getLastName());
        jwtRequest.setPhoneNumber(request.getPhoneNumber());
        jwtRequest.setEmail(request.getEmail());
        jwtRequest.setStatus(new Parameter(10L));
        jwtRequest.setRoleId(new Parameter(13L));
        java.util.Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        jwtRequest.setUpdateDate(timestamp);
        jwtRequest.setPassword(request.getPassword());

        if (Objects.equals(id,null)) {
            return jwtUserDetailsService.createUser(jwtRequest);
        }else{
            return jwtUserDetailsService.updateUserGeneral(jwtRequest,id);
        }
    }
}

