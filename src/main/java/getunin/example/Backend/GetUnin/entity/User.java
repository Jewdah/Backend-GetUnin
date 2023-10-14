package getunin.example.Backend.GetUnin.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Data
@Table(name = "tbl_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "identification_type")
    private Parameter identificationType;

    private String identification;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "phone_Number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "status")
    private Parameter status;

    @OneToOne
    @JoinColumn(name = "profile_picture")
    private File profilePicture;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Parameter roleId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-CO", timezone = "America/Bogota")
    @Column(name = "update_date")
    private Timestamp updateDate;

}
