package esprit.microservices.medecin.Repository;

import esprit.microservices.medecin.Entity.MedecinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;
@Repository
public interface MedecinRepository  extends JpaRepository<MedecinEntity, UUID>{
}



