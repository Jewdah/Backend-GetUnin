package com.getunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailContent {

    private String mailFrom;
    private String mailTo;
    private String subject;
    private String userName;
    private String nameCustomer;
    private String tokenPassword;
}