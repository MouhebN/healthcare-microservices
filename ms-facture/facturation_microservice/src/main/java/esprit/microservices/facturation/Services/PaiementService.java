package esprit.microservices.facturation.Service;

import esprit.microservices.facturation.Entity.Paiement;
import java.util.List;
import java.util.UUID;

public interface PaiementService {
    Paiement savePaiement(Paiement paiement);
    Paiement getPaiementById(Long id);
    List<Paiement> getAllPaiements();
    Paiement updatePaiement(Long id, Paiement paiement);
    void deletePaiement(Long id);
}