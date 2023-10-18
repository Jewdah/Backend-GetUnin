package com.getunin.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_users_login")
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_login")
    private long id;
    @Column
    private String username;

    @JsonIgnore
    @Column(name = "password_user")
    private String password;

    @OneToOne
    @JoinColumn(name = "user_login")
    private User userLogin;

}

