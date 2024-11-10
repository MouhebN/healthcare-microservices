package esprit.microservices.micro_rdv.repository;

import esprit.microservices.micro_rdv.entity.RDV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RDVRepository extends JpaRepository<RDV,Long> {
}
