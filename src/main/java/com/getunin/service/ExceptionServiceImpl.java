package com.getunin.service;

import com.getunin.dto.ExceptionData;
import com.getunin.modelview.ResponseMessage;
import com.getunin.service.interfaces.ExceptionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class ExceptionServiceImpl implements ExceptionService {
    private final RestTemplate restTemplate;

    public ExceptionServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseMessage createException(Long id, String nameException, String nameMetod, Object request) {

        ExceptionData exceptionData = new ExceptionData();
        exceptionData.setUser_id(String.valueOf(id));
        exceptionData.setName(nameException);
        exceptionData.setMetodo(nameMetod);
        exceptionData.setRequest(request.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ExceptionData> entity = new HttpEntity<>(exceptionData, headers);

        // URL del servicio Python
        String url = "http://host.docker.internal:5000/exceptions/create";
        restTemplate.postForObject(url, entity, String.class);

        return new ResponseMessage("Excepción registrada");
    }
}
