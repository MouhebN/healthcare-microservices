package esprit.microservices.medecin.Entity;

import esprit.microservices.medecin.Entity.AvailabilityEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "medecins")
@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor
@AllArgsConstructor
public class MedecinEntity {

    @Id
    private UUID id;

    private String firstName;
    private String lastName;
    private String specialty;
    private String licenseNumber;
    private int experienceYears;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "medecin_id")
    private List<AvailabilityEntity> availability;
}
