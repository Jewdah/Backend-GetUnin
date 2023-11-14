package com.getunin.dto;

import com.getunin.entity.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class NotificationRequest {

    private Long id;

    private String title;

    private String subject;

    private User user_id;

    private Timestamp updateDate;

}
