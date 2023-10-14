package getunin.example.Backend.GetUnin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Value("${INVALID_CREDENTIALS}")
    private String invalidCredentials;

    @Value("${USER_DISABLED}")
    private String userDisable;

}
