package com.getunin.entity;

import lombok.Data;

@Data
public class MailProperties {

    private String host;
    private int port;
    private String userName;
    private String passWord;

}
