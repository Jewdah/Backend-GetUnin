package getunin.example.Backend.GetUnin.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Entity
@Table(name = "tbl_parameters")
@Data
@NoArgsConstructor
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_id")
    private Long id;

    @Column(name = "parameter_name")
    private String parameterName;

    @Column(name = "parameter_value")
    private String parameterValue;

    @ManyToOne
    @JoinColumn(name = "parameter_relationship")
    private Parameter parameterRelationShip;

    @OneToOne
    @JoinColumn(name = "updater")
    private  User updater;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-CO", timezone = "America/Bogota")
    @Column(name = "update_date")
    private Timestamp updateDate;

}

