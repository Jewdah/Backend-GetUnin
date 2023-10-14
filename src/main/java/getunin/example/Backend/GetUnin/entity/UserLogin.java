package getunin.example.Backend.GetUnin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

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
