package esprit.microservices.facturation.Services;

import esprit.microservices.facturation.Entity.Paiement;
import java.util.List;


public interface PaiementService {
    Paiement savePaiement(Paiement paiement);
    Paiement getPaiementById(Long id);
    List<Paiement> getAllPaiements();
    Paiement updatePaiement(Long id, Paiement paiement);
    void deletePaiement(Long id);
}