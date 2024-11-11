package esprit.microservices.medecin.Repository;

import esprit.microservices.medecin.Entity.AvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AvailabilityRepository extends JpaRepository<AvailabilityEntity, UUID> {
}
