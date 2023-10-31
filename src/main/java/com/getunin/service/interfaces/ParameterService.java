package com.getunin.service.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.getunin.dto.ParameterRequest;
import com.getunin.entity.Parameter;
import com.getunin.modelview.ResponseMessage;

import java.util.List;

public interface ParameterService  {

    Parameter createParameter(ParameterRequest request);

    Parameter getParameterById(Long id);

    List<Parameter> getAllParameterByRelationShip(Long parameterRelationShip);

    Parameter updateParameter(ParameterRequest request, Long id);

    ResponseMessage deleteParameterById(Long id);




}
