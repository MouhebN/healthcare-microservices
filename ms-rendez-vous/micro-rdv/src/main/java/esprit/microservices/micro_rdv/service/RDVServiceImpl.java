package esprit.microservices.micro_rdv.service;

import esprit.microservices.micro_rdv.repository.RDVRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import esprit.microservices.micro_rdv.entity.RDV;

import java.util.List;

@Service
@AllArgsConstructor
public class RDVServiceImpl implements IRDVService{

    RDVRepository rdvRepository;
    @Override
    public List<RDV> retrieveAllRDVs() {
        return rdvRepository.findAll();
    }

    @Override
    public RDV retrieveRDV(Long rdvId) {
        return rdvRepository.findById(rdvId).orElse(null);
    }

    @Override
    public RDV addRDV(RDV rdv) {
        return rdvRepository.save(rdv);
    }

    @Override
    public void removeRDV(Long rdvID) {
        rdvRepository.deleteById(rdvID);
    }

    @Override
    public RDV modifyRDV(RDV rdv) {
        return rdvRepository.save(rdv);
    }

    @Override
    public RDV affecterRDVsA_Patient_docteur(List<String> idPatient, List<String> idDocteur, Long rdvId) {
        return null;
    }
}
