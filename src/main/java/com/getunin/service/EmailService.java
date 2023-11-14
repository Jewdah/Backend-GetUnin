package com.getunin.service;


import com.getunin.configuration.MailSenderConfig;
import com.getunin.dto.EmailContent;
import com.getunin.modelview.ResponseMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class EmailService {

    private final MailSenderConfig mailSenderConfig;
    private TemplateEngine templateEngine;


    private String applicationAccess;


    private String recoveryPassword;

    public EmailService(MailSenderConfig mailSenderConfig, TemplateEngine templateEngine) {
        this.mailSenderConfig = mailSenderConfig;
        this.templateEngine = templateEngine;
    }

    public void sendEmailTemplate(EmailContent content, boolean create, String password, Long idCompany){
        JavaMailSender email = mailSenderConfig.javaMailSender(idCompany);
        MimeMessage message = email.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("userName", content.getUserName());
            String htmlText;

            if (Objects.equals(content.getSubject(),"test")){

                model.put("url", applicationAccess + content.getTokenPassword());
                context.setVariables(model);
                htmlText = templateEngine.process("test-template", context);

            }else if (create){

                model.put("url", applicationAccess + content.getTokenPassword());
                context.setVariables(model);
                htmlText = templateEngine.process("email-template", context);
            }else {
                model.put("url", recoveryPassword + content.getTokenPassword());
                model.put("password", password);
                context.setVariables(model);
                htmlText = templateEngine.process("email-template-recovery", context);
            }

            helper.setFrom(content.getMailFrom());
            helper.setTo(content.getMailTo());
            helper.setSubject(content.getSubject());
            helper.setText(htmlText, true);

            email.send(message);
        }catch (MessagingException e){
            new ResponseMessage(e.getMessage());
        }
    }

}

