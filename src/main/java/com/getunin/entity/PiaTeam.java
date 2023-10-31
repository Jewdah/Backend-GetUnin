package com.getunin.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_pia_team")
public class PiaTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "name_proyect")
    private String nameProyect;

    @Column(name = "type_proyect")
    private String typeProyect;
}
