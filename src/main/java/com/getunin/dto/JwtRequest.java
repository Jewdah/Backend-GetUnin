package com.getunin.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.getunin.entity.File;
import com.getunin.entity.Parameter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
public class JwtRequest {

    @NotNull(message = "{notNull.IdentificationType.Jwt}")
    private Parameter identificationType;

    @NotEmpty(message = "{notEmpty.Identification}")
    @Size(min = 6, max = 50, message = "{size.Identification}")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "{pattern.Identification}")
    private String identification;

    @NotEmpty(message = "{notEmpty.FirstName}")
    @Size(min = 2, max = 50, message = "{size.FirstName}")
    @Pattern(regexp = "[a-zA-Z0-9À-ÿ\\s]+", message = "{pattern.FirstName}")
    private String firstName;

    @NotEmpty(message = "{notEmpty.LastName}")
    @Size(min = 2, max = 50, message = "{size.LastName}")
    @Pattern(regexp = "[a-zA-Z0-9À-ÿ\\s]+", message = "{pattern.LastName}")
    private String lastName;

    @NotEmpty(message = "{userName.Entity.Pass.Empty}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]){8,15}$", message = "{userName.Entity.Pass.Pattern}")
    private String password;

    @NotEmpty(message = "{notEmpty.PhoneNumber}")
    @Size(min = 8, max = 50, message = "{size.PhoneNumber}")
    @Pattern(regexp = "[0-9]+", message = "{pattern.PhoneNumber}")
    private String phoneNumber;

    @NotEmpty(message = "{notEmpty.Email}")
    @Size(min = 8, max = 50, message = "{size.Email}")
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "{pattern.Email}")
    private String email;

    private Parameter status;

    @NotNull(message = "{notNull.RollId}")
    private Parameter roleId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-CO", timezone = "America/Bogota")
    @NotNull(message = "{notNull.UpdaterDateUser}")
    private Timestamp updateDate;

}
