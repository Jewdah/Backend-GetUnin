package com.getunin.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserLogin {

    private static final long serialVersionUID = 5926468583005150707L;

    @NotEmpty(message = "{notEmpty.Email}")
    @Size(min = 8, max = 50, message = "{size.Email}")
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "{pattern.Email}")
    private String email;

    @NotEmpty(message = "{notEmpty.Password}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&.])([A-Za-z\\d$@$!%*?&.]){8,15}$", message = "{pattern.Password}")
    private String password;
}
