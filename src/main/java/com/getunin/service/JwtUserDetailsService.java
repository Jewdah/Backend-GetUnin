package com.getunin.service;

import com.getunin.entity.UserLogin;
import com.getunin.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {


    private final UserLoginRepository userLoginRepository;
    private final MessageSource messageSource;

    private final PasswordEncoder bcryptEncoder;

    public JwtUserDetailsService(UserLoginRepository userLoginRepository, MessageSource messageSource, PasswordEncoder bcryptEncoder) {
        this.userLoginRepository = userLoginRepository;

        this.messageSource = messageSource;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserLogin user = userLoginRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

}
