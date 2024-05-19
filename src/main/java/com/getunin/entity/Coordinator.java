package com.getunin.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_coordinator")
public class Coordinator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coordinator_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "university_id")
    private University university;


}
