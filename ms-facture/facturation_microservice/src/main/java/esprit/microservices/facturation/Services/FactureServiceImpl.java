package esprit.microservices.facturation.Services;

import esprit.microservices.facturation.Client.MedecinClient;
import esprit.microservices.facturation.Client.MedecinDTO;
import esprit.microservices.facturation.Client.PatientClient;
import esprit.microservices.facturation.Client.PatientDTO;
import esprit.microservices.facturation.Entity.Facture;
import esprit.microservices.facturation.Repository.FactureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class FactureServiceImpl implements FactureService {
    private final PatientClient patientClient;
    private final MedecinClient medecinClient;

    private final FactureRepository factureRepository;

    public FactureServiceImpl(PatientClient patientClient, MedecinClient medecinClient, FactureRepository factureRepository) {
        this.patientClient = patientClient;
        this.medecinClient = medecinClient;
        this.factureRepository = factureRepository;
    }

    @Override
    public Facture saveFacture(Facture facture) {
        PatientDTO patient = patientClient.getPatientById(Long.valueOf(facture.getPatientId()));

        facture.setPatientFirstName(patient.firstName());
        facture.setPatientLastName(patient.lastName());
        facture.setPatientPhoneNumber(patient.phoneNumber());
        facture.setPatientEmail(patient.email());

        MedecinDTO medecin = medecinClient.getMedecinById(UUID.fromString(facture.getMedecinId()));
        facture.setMedecinFirstName(medecin.firstName());
        facture.setMedecinLastName(medecin.lastName());

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