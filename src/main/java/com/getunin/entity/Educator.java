package com.getunin.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_educator")
public class Educator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "educator_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "university_id")
    private University university;


}
