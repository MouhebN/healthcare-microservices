package esprit.microservices.facturation.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    private double montant;

    @ElementCollection
    private List<String> services;

    private String statut;

    @Temporal(TemporalType.DATE)
    private Date dateEmission;
}
