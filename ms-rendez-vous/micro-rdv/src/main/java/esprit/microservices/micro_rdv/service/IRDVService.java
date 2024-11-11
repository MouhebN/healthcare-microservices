package esprit.microservices.micro_rdv.service;

import esprit.microservices.micro_rdv.entity.RDV;
import java.util.List;


public interface IRDVService {
    List<RDV> retrieveAllRDVs();

    RDV retrieveRDV(Long rdvId);

    RDV addRDV(RDV rdv);

    void removeRDV(Long rdvID);

    RDV modifyRDV(RDV rdv);
    RDV affecterRDVsA_Patient_docteur(List<String> idPatient, List<String> idDocteur, Long rdvId);
}
