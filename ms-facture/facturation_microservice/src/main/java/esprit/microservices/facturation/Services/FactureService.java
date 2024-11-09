package esprit.microservices.facturation.Service;

import esprit.microservices.facturation.Entity.Facture;
import java.util.List;
import java.util.UUID;

public interface FactureService {
    Facture saveFacture(Facture facture);
    Facture getFactureById(Long id);
    List<Facture> getAllFactures();
    Facture updateFacture(Long id, Facture facture);
    void deleteFacture(Long id);
}