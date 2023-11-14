package com.getunin.configuration;


import com.getunin.entity.MailProperties;
import com.getunin.service.interfaces.ParameterService;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Component
public class MailSenderConfig {

    private final ParameterService parameterService;



    public MailSenderConfig(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public JavaMailSender javaMailSender(Long idparameter) {

        String json = parameterService.getParameterById(idparameter).getParameterValue();

        Gson gson = new Gson();
        MailProperties proof = gson.fromJson(json, MailProperties.class);

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(proof.getHost());
        sender.setPort(proof.getPort());
        sender.setUsername(proof.getUserName());

        try{
            String password = EncryptionUtils.decrypt(proof.getPassWord());

            sender.setPassword(password);

            Properties props = sender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "false");

            return sender;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



}
