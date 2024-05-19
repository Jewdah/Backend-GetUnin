package com.getunin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.getunin.entity.Parameter;
import com.getunin.entity.University;
import com.getunin.entity.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
public class EducatorRequest {

    private User user;

    private University university;

    private Parameter identificationType;

    @NotEmpty(message = "{notEmpty.PIdentification}")
    @Size(min = 6, max = 50, message = "{size.Identification}")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "{pattern.Identification}")
    private String identification;

    @NotEmpty(message = "{notEmpty.FirstName}")
    @Size(min = 2, max = 50, message = "{size.FirstName}")
    @Pattern(regexp = "[a-zA-Z0-9À-ÿ\\s]+", message = "{pattern.FirstName}")
    private String firstName;

    @NotEmpty(message = "{userName.Entity.Pass.Empty}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]){8,15}$", message = "{userName.Entity.Pass.Pattern}")
    private String password;

    @NotEmpty(message = "{notEmpty.LastName}")
    @Size(min = 2, max = 50, message = "{size.LastName}")
    @Pattern(regexp = "[a-zA-Z0-9À-ÿ\\s]+", message = "{pattern.LastName}")
    private String lastName;

    @NotEmpty(message = "{notEmpty.PhoneNumberUser}")
    @Size(min = 8, max = 50, message = "{size.PhoneNumberUser}")
    @Pattern(regexp = "[0-9]+", message = "{pattern.PhoneNumberUser}")
    private String phoneNumber;

    @NotEmpty(message = "{notEmpty.Email}")
    @Size(min = 8, max = 50, message = "{size.Email}")
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "{pattern.Email}")
    private String email;


    private Parameter status;

    private Parameter roleId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-CO", timezone = "America/Bogota")
    @NotNull(message = "{notNull.UpdaterDateUser}")
    private Timestamp updateDate;
}
