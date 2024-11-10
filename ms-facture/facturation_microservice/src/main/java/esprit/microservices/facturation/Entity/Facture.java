package esprit.microservices.facturation.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Facture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idFacture")
    private Long id;

    private String patientId;
    private String patientFirstName;
    private String patientLastName;
    private String patientPhoneNumber;
    private String patientEmail;

    private String medecinId;
    private String medecinFirstName;
    private String medecinLastName;

    private double montant;

    @ElementCollection
    private List<String> services;

    private String statut;

    @Temporal(TemporalType.DATE)
    private Date dateEmission;
}
