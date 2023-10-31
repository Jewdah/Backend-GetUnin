package com.getunin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.getunin.entity.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class ParameterRequest {

    @Size(min = 1, max = 50, message = "{size.ParameterName}")
    @Pattern(regexp = "[a-zA-Z0-9À-ÿ\\s]+", message = "{pattern.ParameterName}")
    private String parameterName;

    @NotEmpty(message = "{notEmpty.ParameterValue}")
    @Size(min = 1, max = 100, message = "{size.ParameterValue}")
    @Pattern(regexp = "[a-zA-Z0-9À-ÿ\\s]+", message = "{pattern.ParameterValue}")
    private String parameterValue;

    private Parameter parameterRelationShip;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-CO", timezone = "America/Bogota")
    @NotNull(message = "{notNull.UpdaterDateUser}")
    private Timestamp updateDate;

}

