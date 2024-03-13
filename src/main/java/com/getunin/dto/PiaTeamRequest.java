package com.getunin.dto;

import com.getunin.entity.Parameter;
import com.getunin.entity.Student;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PiaTeamRequest {

    @NotEmpty(message = "el nombre del proyecto no puede estar vacuio")
    private String nameProyect;

    private String description;

    @NotNull(message = "el tipo de proyecto no puede estar vacio")
    private Parameter typeProyect;

    @NotNull(message = "el estudiante dueño del proyecto no puede ir vacio")
    private Student studentOne;

    private Student studentTwo;

    private Student studentThree;

    private String emailTo;
}
