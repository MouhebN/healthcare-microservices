package esprit.microservices.micro_rdv.entity;
import java.io.Serializable;
import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="rendez-vous")
public class RDV implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idRDV")
    private Long idRDV;

    private String dateRDV;
    private String heureRDV;
    @Enumerated(EnumType.STRING)
    private Status status;

    private String patientId;
    private String patientFirstName;
    private String patientLastName;
    private String patientPhoneNumber;
    private String patientEmail;

    private String medecinId;
    private String medecinFirstName;
    private String medecinLastName;
}
