package com.getunin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CreateUserLoginRequest {
    @NotEmpty(message = "{userName.Entity.Empty}")
    @Size(min = 10, max = 50, message = "{userName.Entity.Length}")
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "{userName.Entity.Pattern}")
    private String username;
    @NotEmpty(message = "{userName.Entity.Pass.Empty}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]){8,15}$", message = "{userName.Entity.Pass.Pattern}")
    private String password;
}
