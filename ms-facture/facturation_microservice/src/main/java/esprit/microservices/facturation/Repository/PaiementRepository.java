package esprit.microservices.facturation.Repository;

import esprit.microservices.facturation.Entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}