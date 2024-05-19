package com.getunin.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "university_id")
    private Long id;


    @Column(name = "bussines_name")
    private String bussinesName;

    @Column(name = "nit_number")
    private String nitNumber;

    private String address;

    @OneToOne
    @JoinColumn(name = "identification_type")
    private Parameter indetificationType;


    public University(Long id) {
        this.id = id;
    }

    public University() {

    }
}