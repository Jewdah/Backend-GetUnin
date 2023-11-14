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

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "type_proyect")
    private Parameter typeProyect;

    @ManyToOne
    @JoinColumn(name = "student_one")
    private Student studentOne;

    @ManyToOne
    @JoinColumn(name = "student_two")
    private Student studentTwo;

    @ManyToOne
    @JoinColumn(name = "student_three")
    private Student studentThree;

}