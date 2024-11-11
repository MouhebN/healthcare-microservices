package esprit.microservices.Fiche;

import esprit.microservices.Fiche.Client.MedecinClient;
import esprit.microservices.Fiche.Client.MedecinDTO;
import esprit.microservices.Fiche.Client.PatientClient;
import esprit.microservices.Fiche.Client.PatientDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedicalRecordService {

    private final PatientClient patientClient;
    private final MedecinClient medecinClient;
    private final MedicalRecordRepository repository;

    public MedicalRecordService(PatientClient patientClient, MedecinClient medecinClient, MedicalRecordRepository repository) {
        this.patientClient = patientClient;
        this.medecinClient = medecinClient;
        this.repository = repository;
    }

    public MedicalRecordEntity save(MedicalRecordEntity record) {
        PatientDTO patient = patientClient.getPatientById(Long.valueOf(record.getPatientId()));

        record.setPatientFirstName(patient.firstName());
        record.setPatientLastName(patient.lastName());
        record.setPatientPhoneNumber(patient.phoneNumber());
        record.setPatientEmail(patient.email());

        MedecinDTO medecin = medecinClient.getMedecinById(UUID.fromString(record.getMedecinId()));
        record.setMedecinFirstName(medecin.firstName());
        record.setMedecinLastName(medecin.lastName());

        return repository.save(record);
    }

    public List<MedicalRecordEntity> findAll() {
        return repository.findAll();
    }

    public Optional<MedicalRecordEntity> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
