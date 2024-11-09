
package esprit.microservices.facturation.Repository;

        import esprit.microservices.facturation.Entity.Facture;
        import org.springframework.data.jpa.repository.JpaRepository;
        import java.util.UUID;

public interface FactureRepository extends JpaRepository<Facture, Long> {
}