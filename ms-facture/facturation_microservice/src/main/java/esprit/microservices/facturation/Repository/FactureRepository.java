
package esprit.microservices.facturation.Repository;

import esprit.microservices.facturation.Entity.Facture;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Long> {
}