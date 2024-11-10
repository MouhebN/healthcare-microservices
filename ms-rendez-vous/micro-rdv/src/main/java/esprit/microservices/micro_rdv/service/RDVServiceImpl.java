package esprit.microservices.micro_rdv.service;

import esprit.microservices.micro_rdv.Dto.MedecinDTO;
import esprit.microservices.micro_rdv.client.MedecinClient;
import esprit.microservices.micro_rdv.client.PatientClient;
import esprit.microservices.micro_rdv.Dto.PatientDTO;
import esprit.microservices.micro_rdv.repository.RDVRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import esprit.microservices.micro_rdv.entity.RDV;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RDVServiceImpl implements IRDVService{

    RDVRepository rdvRepository;

    private final PatientClient patientClient;
    private final MedecinClient medecinClient ;

    @Override
    public List<RDV> retrieveAllRDVs() {
        return rdvRepository.findAll();
    }

    @Override
    public RDV retrieveRDV(Long rdvId) {
        return rdvRepository.findById(rdvId).orElse(null);
    }

    public RDV addRDV(RDV rdv) {

        PatientDTO patient = patientClient.getPatientById(Long.valueOf(rdv.getPatientId()));
        rdv.setPatientFirstName(patient.firstName());
        rdv.setPatientLastName(patient.lastName());
        rdv.setPatientPhoneNumber(patient.phoneNumber());
        rdv.setPatientEmail(patient.email());

        MedecinDTO medecin = medecinClient.getMedecinById(UUID.fromString(rdv.getMedecinId()));
        rdv.setMedecinFirstName(medecin.firstName());
        rdv.setMedecinLastName(medecin.lastName());

        // Check Availability for the Medecin
        boolean isAvailable = medecinClient.checkAvailability(
                UUID.fromString(rdv.getMedecinId()), rdv.getDateRDV(), rdv.getHeureRDV()
        );

        if (!isAvailable) {
            return null;
        }
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
