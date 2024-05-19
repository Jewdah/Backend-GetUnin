package com.getunin.service;

import com.getunin.dto.CoordinatorRequest;
import com.getunin.dto.EducatorRequest;
import com.getunin.dto.JwtRequest;
import com.getunin.entity.*;
import com.getunin.exception.listexceptions.NotFoundException;
import com.getunin.repository.EducatorRepository;
import com.getunin.service.interfaces.EducatorService;
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
public class EducatorServiceImpl implements EducatorService {

    private final EducatorRepository educatorRepository;

    private final JwtUserDetailsService jwtUserDetailsService;

    private final ExceptionService exceptionService;

    public EducatorServiceImpl(EducatorRepository educatorRepository, JwtUserDetailsService jwtUserDetailsService, ExceptionService exceptionService) {
        this.educatorRepository = educatorRepository;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.exceptionService = exceptionService;
    }


    @Override
    public Educator createEducator(EducatorRequest request) {

        User user = createJwtRequest(request, null);

        Educator educator = new Educator();
        educator.setUser(user);
        educator.setUniversity(new University(1L));
        return educatorRepository.save(educator);
    }

    @Override
    public Educator updateEducator(EducatorRequest request,Long id) {
        Optional<Educator> educator = educatorRepository.findById(id);

        if(educator.isPresent()){
            User userUpdate = createJwtRequest(request,educator.get().getUser().getId() );

            Educator educatorUpdate = educator.get();
            educatorUpdate.setUser(userUpdate);
            return educatorRepository.save(educatorUpdate);

        }else {
            exceptionService.createException(0L, String.valueOf(NotFoundException.class),"updateEducator",request);
            throw new NotFoundException("id de profesor no encontrado");
        }
    }

    @Override
    public Educator getEducatorById(Long id) {

        Optional<Educator> educator = educatorRepository.findById(id);

        if(educator.isPresent()){
            return educator.get();
        }else {
            exceptionService.createException(0L, String.valueOf(NotFoundException.class),"getEducatorById",null);
            throw new NotFoundException("id de profesor no encontrado");
        }
    }

    @Override
    public Educator getEducatorByEmail(String email) {

        Optional<Educator> educator = Optional.ofNullable(educatorRepository.findByUser_Email(email));

        if(educator.isPresent()){
            return educator.get();
        }else {
            exceptionService.createException(0L, String.valueOf(NotFoundException.class),"getEducatorByEmail",null);
            throw new NotFoundException("id de profesor no encontrado");
        }
    }

    @Override
    public List<Educator> getAllEducator() {
        List<Educator> educator = educatorRepository.findAll();

        if(educator.isEmpty()){
            return educator;
        }else {
            exceptionService.createException(0L, String.valueOf(NotFoundException.class),"getAllEducators",null);
            throw new NotFoundException("Profesor no encontrado");
        }
    }


    private User createJwtRequest(EducatorRequest request, Long id){
        JwtRequest jwtRequest = new JwtRequest();

        jwtRequest.setIdentificationType(request.getIdentificationType());
        jwtRequest.setIdentification(request.getIdentification());
        jwtRequest.setFirstName(request.getFirstName());
        jwtRequest.setLastName(request.getLastName());
        jwtRequest.setPhoneNumber(request.getPhoneNumber());
        jwtRequest.setEmail(request.getEmail());
        jwtRequest.setStatus(new Parameter(10L));
        jwtRequest.setRoleId(new Parameter(14L));
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

