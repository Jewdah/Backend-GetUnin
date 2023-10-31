package com.getunin.service;

import com.getunin.dto.ParameterRequest;
import com.getunin.entity.Parameter;
import com.getunin.exception.listexceptions.ConflictException;
import com.getunin.modelview.ResponseMessage;
import com.getunin.repository.ParameterRepository;
import com.getunin.service.interfaces.ParameterService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class ParameterServiceImpl implements ParameterService {


    private final ParameterRepository parameterRepository;

    private final MessageSource messageSource;

    private final JwtUserDetailsService jwtUserDetailsService;

    public ParameterServiceImpl(ParameterRepository parameterRepository, MessageSource messageSource, @Lazy JwtUserDetailsService jwtUserDetailsService) {
        this.parameterRepository = parameterRepository;
        this.messageSource = messageSource;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    public Parameter createParameter(ParameterRequest request) {

        Parameter parameterSave = new Parameter();

        parameterSave.setParameterName(request.getParameterName());
        parameterSave.setParameterValue(request.getParameterValue());
        parameterSave.setParameterRelationShip(request.getParameterRelationShip());
        parameterSave.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));


        return parameterRepository.save(parameterSave);

    }
    @Override
    public Parameter getParameterById(Long id) {
        Optional<Parameter> parameterDb = parameterRepository.findById(id);

        if (parameterDb.isPresent()){
            return parameterDb.get();
        }else {
            throw new ConflictException(messageSource.getMessage("user.Not.Found.Rol", null,"", LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public List<Parameter> getAllParameterByRelationShip(Long parameterRelationShip) {
        Optional<List<Parameter>> parameterRelation = Optional.ofNullable(parameterRepository.findByParameterRelationShip_Id(parameterRelationShip));

        if (parameterRelation.isPresent()){
            return parameterRelation.get();
        }else{
            throw new ConflictException(messageSource.getMessage("parameter.Relation" , null,"", LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public Parameter updateParameter(ParameterRequest request, Long id) {
        //revisar codigo ya que no actualiza
        Optional<Parameter> parameterUp = parameterRepository.findById(id);

        if (parameterUp.isPresent()){

            Parameter parameterUpdate = parameterUp.get();

            parameterUpdate.setParameterName(request.getParameterName());
            parameterUpdate.setParameterValue(request.getParameterValue());
            parameterUpdate.setParameterRelationShip(request.getParameterRelationShip());
            parameterUpdate.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));;

            return parameterRepository.save(parameterUpdate );
        }else {
            throw new ConflictException(messageSource.getMessage("user.Not.Found.Rol", null,"", LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public ResponseMessage deleteParameterById(Long id) {
        Optional<Parameter> parameterDb = parameterRepository.findById(id);

        if (parameterDb.isPresent()){
            try {
                parameterRepository.delete(parameterDb.get());
            }catch (ConflictException e){
                throw new ConflictException(messageSource.getMessage("cant.Delete", null,"", LocaleContextHolder.getLocale()));
            }

            return new ResponseMessage(messageSource.getMessage("parameter.Delete",null,"",LocaleContextHolder.getLocale()));
        }else {
            throw new ConflictException(messageSource.getMessage("user.Not.Found.Rol", null,"", LocaleContextHolder.getLocale()));
        }
    }
}

