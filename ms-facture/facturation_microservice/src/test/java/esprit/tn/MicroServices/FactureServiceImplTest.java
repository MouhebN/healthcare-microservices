package esprit.tn.MicroServices;

import esprit.microservices.facturation.Client.MedecinClient;
import esprit.microservices.facturation.Client.MedecinDTO;
import esprit.microservices.facturation.Client.PatientClient;
import esprit.microservices.facturation.Client.PatientDTO;
import esprit.microservices.facturation.Entity.Facture;
import esprit.microservices.facturation.FacturationApplication;
import esprit.microservices.facturation.Repository.FactureRepository;
import esprit.microservices.facturation.Services.FactureServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes= FacturationApplication.class)
class FactureServiceImplTest {

    @MockBean
    private PatientClient patientClient;

    @MockBean
    private MedecinClient medecinClient;

    @MockBean
    private FactureRepository factureRepository;

    @Autowired
    private FactureServiceImpl factureService;

    @BeforeEach
    void setUp() {
        // No need for MockitoAnnotations.openMocks(this); because @MockBean is managed by Spring
    }

    @Test
    void testSaveFacture() {
        Facture facture = new Facture();
        facture.setPatientId("1");
        facture.setMedecinId(UUID.randomUUID().toString());

        PatientDTO patientDTO = new PatientDTO(1L, "John", "Doe", "123456789", "johndoe@example.com");
        MedecinDTO medecinDTO = new MedecinDTO(UUID.randomUUID(), "Dr.", "Smith");
        when(patientClient.getPatientById(Long.valueOf(facture.getPatientId()))).thenReturn(patientDTO);
        when(medecinClient.getMedecinById(UUID.fromString(facture.getMedecinId()))).thenReturn(medecinDTO);
        when(factureRepository.save(facture)).thenReturn(facture);

        Facture savedFacture = factureService.saveFacture(facture);

        assertEquals("John", savedFacture.getPatientFirstName());
        assertEquals("Doe", savedFacture.getPatientLastName());
        assertEquals("123456789", savedFacture.getPatientPhoneNumber());
        assertEquals("johndoe@example.com", savedFacture.getPatientEmail());
        assertEquals("Dr.", savedFacture.getMedecinFirstName());
        assertEquals("Smith", savedFacture.getMedecinLastName());
        verify(factureRepository, times(1)).save(facture);
    }

    @Test
    void testGetFactureById() {
        Long id = 1L;
        Facture facture = new Facture();
        facture.setId(id);

        when(factureRepository.findById(id)).thenReturn(Optional.of(facture));

        Facture result = factureService.getFactureById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(factureRepository, times(1)).findById(id);
    }

    @Test
    void testGetAllFactures() {
        Facture facture1 = new Facture();
        Facture facture2 = new Facture();

        List<Facture> factures = Arrays.asList(facture1, facture2);

        when(factureRepository.findAll()).thenReturn(factures);

        List<Facture> result = factureService.getAllFactures();

        assertEquals(2, result.size());
        verify(factureRepository, times(1)).findAll();
    }

    @Test
    void testUpdateFacture_NotFound() {
        Long id = 1L;
        Facture updatedFacture = new Facture();
        updatedFacture.setMontant("200");

        when(factureRepository.findById(id)).thenReturn(Optional.empty());

        Facture result = factureService.updateFacture(id, updatedFacture);

        assertNull(result);
        verify(factureRepository, times(1)).findById(id);
        verify(factureRepository, never()).save(any(Facture.class));
    }

    @Test
    void testDeleteFacture() {
        Long id = 1L;

        factureService.deleteFacture(id);
        verify(factureRepository, times(1)).deleteById(id);
    }
}