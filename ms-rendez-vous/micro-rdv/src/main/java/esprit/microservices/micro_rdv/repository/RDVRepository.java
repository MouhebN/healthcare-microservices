package esprit.microservices.micro_rdv.repository;

import esprit.microservices.micro_rdv.entity.RDV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface RDVRepository extends JpaRepository<RDV,Long> {
}
