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
    private String patientId;
    private String medId;
    @Temporal (TemporalType.DATE)
    private Date dateRDV;
    @Temporal (TemporalType.DATE)
    private Date heureRDV;
    @Enumerated(EnumType.STRING)
    private Status status;
}
