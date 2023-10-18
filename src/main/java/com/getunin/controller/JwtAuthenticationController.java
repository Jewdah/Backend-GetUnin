package com.getunin.controller;

import com.getunin.configuration.JwtTokenUtil;
import com.getunin.dto.UserLogin;
import com.getunin.entity.JwtResponse;
import com.getunin.entity.User;
import com.getunin.exception.listexceptions.BadRequestException;
import com.getunin.exception.listexceptions.NotFoundException;
import com.getunin.modelview.UserView;
import com.getunin.repository.UserRepository;
import com.getunin.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Value("${INVALID_CREDENTIALS}")
    private String invalidCredentials;

    @Value("${USER_DISABLED}")
    private String userDisable;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final UserRepository userRepository;

    private final JwtUserDetailsService jwtUserDetailsService;

    private final MessageSource messageSource;

    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService, UserRepository userRepository, JwtUserDetailsService jwtUserDetailsService, MessageSource messageSource) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.messageSource = messageSource;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<Object> createAuthenticationTokenController(@Valid @RequestBody UserLogin userLogin) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
        } catch (DisabledException e) {
            throw new NotFoundException(userDisable);
        } catch (BadCredentialsException e) {
            throw new BadRequestException(invalidCredentials);
        }

        User userDB = userRepository.findByEmail(userLogin.getEmail());

        UserView userView = new UserView(userDB.getId(), userDB.getIdentificationType(), userDB.getIdentification(), userDB.getFirstName(),
                userDB.getLastName(), userDB.getEmail(), userDB.getPhoneNumber(), userDB.getRoleId(), userDB.getStatus());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userLogin.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userView));
    }
}
