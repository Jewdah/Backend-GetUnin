package com.getunin.controller;

import com.getunin.dto.ExceptionData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionAPIController {

    private final RestTemplate restTemplate;

    public ExceptionAPIController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @PostMapping("/create-exception")
    public String createException(@RequestBody ExceptionData data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ExceptionData> entity = new HttpEntity<>(data, headers);

        String url = "http://host.docker.internal:5000/exceptions/create";
        return restTemplate.postForObject(url, entity, String.class);
    }
}
