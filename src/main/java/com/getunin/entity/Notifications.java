package com.getunin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tbl_notifications")
@Data
@NoArgsConstructor
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    private String title;

    @Column(name = "subject")
    private String subject;

    @ManyToOne
    @JoinColumn(name = "user_to")
    private User userTo;

    @ManyToOne
    @JoinColumn(name = "user_to")
    private User userRecovery;

    private Boolean status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-CO", timezone = "America/Bogota")
    @Column(name = "update_date")
    private Timestamp updateDate;

}
