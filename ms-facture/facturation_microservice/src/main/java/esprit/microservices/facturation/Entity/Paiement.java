package esprit.microservices.facturation.Entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paiement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPaiement")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "facture_id", referencedColumnName = "idFacture", nullable = false)
    private Facture facture;

    private double montant;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String mode;
}