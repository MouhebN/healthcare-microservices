package esprit.microservices.facturation.ServiceImpl;

import esprit.microservices.facturation.Entity.Paiement;
import esprit.microservices.facturation.Repository.PaiementRepository;
import esprit.microservices.facturation.Service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaiementServiceImpl implements PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    @Override
    public Paiement savePaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement getPaiementById(Long id) {
        return paiementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    public Paiement updatePaiement(Long id, Paiement paiement) {
        Optional<Paiement> existingPaiement = paiementRepository.findById(id);
        if (existingPaiement.isPresent()) {
            Paiement updatedPaiement = existingPaiement.get();
            updatedPaiement.setMontant(paiement.getMontant());
            updatedPaiement.setDate(paiement.getDate());
            updatedPaiement.setMode(paiement.getMode());
            return paiementRepository.save(updatedPaiement);
        }
        return null;
    }

    @Override
    public void deletePaiement(Long id) {
        paiementRepository.deleteById(id);
    }
}