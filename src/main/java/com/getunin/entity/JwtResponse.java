package com.getunin.entity;

import com.getunin.modelview.UserView;



import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private final UserView user;

    public JwtResponse(String token, UserView user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserView getUser(){
        return user;
    }
}
