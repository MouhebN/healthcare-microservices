package esprit.microservices.facturation.ServiceImpl;

import esprit.microservices.facturation.Entity.Facture;
import esprit.microservices.facturation.Repository.FactureRepository;
import esprit.microservices.facturation.Service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FactureServiceImpl implements FactureService {

    @Autowired
    private FactureRepository factureRepository;

    @Override
    public Facture saveFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public Facture getFactureById(Long id) {
        return factureRepository.findById(id).orElse(null);
    }

    @Override
    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    @Override
    public Facture updateFacture(Long id, Facture facture) {
        Optional<Facture> existingFacture = factureRepository.findById(id);
        if (existingFacture.isPresent()) {
            Facture updatedFacture = existingFacture.get();
            updatedFacture.setPatientId(facture.getPatientId());
            updatedFacture.setMontant(facture.getMontant());
            updatedFacture.setServices(facture.getServices());
            updatedFacture.setStatut(facture.getStatut());
            updatedFacture.setDateEmission(facture.getDateEmission());
            return factureRepository.save(updatedFacture);
        }
        return null;
    }

    @Override
    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);
    }
}