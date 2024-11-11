package esprit.tn.MicroServices;

import esprit.microservices.Fiche.Client.MedecinClient;
import esprit.microservices.Fiche.Client.MedecinDTO;
import esprit.microservices.Fiche.Client.PatientClient;
import esprit.microservices.Fiche.Client.PatientDTO;
import esprit.microservices.Fiche.MedicalRecordEntity;
import esprit.microservices.Fiche.MedicalRecordRepository;
import esprit.microservices.Fiche.MedicalRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MedicalRecordServiceTest {

    @Mock
    private PatientClient patientClient;

    @Mock
    private MedecinClient medecinClient;

    @Mock
    private MedicalRecordRepository repository;

    @InjectMocks
    private MedicalRecordService medicalRecordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        MedicalRecordEntity record = new MedicalRecordEntity();
        record.setPatientId("1");
        record.setMedecinId(UUID.randomUUID().toString());

        PatientDTO patientDTO = new PatientDTO(1L, "John", "Doe", "123456789", "johndoe@example.com");
        MedecinDTO medecinDTO = new MedecinDTO(UUID.randomUUID(), "Dr.", "Smith");

        when(patientClient.getPatientById(Long.valueOf(record.getPatientId()))).thenReturn(patientDTO);
        when(medecinClient.getMedecinById(UUID.fromString(record.getMedecinId()))).thenReturn(medecinDTO);
        when(repository.save(any(MedicalRecordEntity.class))).thenReturn(record);

        MedicalRecordEntity savedRecord = medicalRecordService.save(record);

        assertEquals("John", savedRecord.getPatientFirstName());
        assertEquals("Doe", savedRecord.getPatientLastName());
        assertEquals("123456789", savedRecord.getPatientPhoneNumber());
        assertEquals("johndoe@example.com", savedRecord.getPatientEmail());
        assertEquals("Dr.", savedRecord.getMedecinFirstName());
        assertEquals("Smith", savedRecord.getMedecinLastName());

        verify(patientClient).getPatientById(Long.valueOf(record.getPatientId()));
        verify(medecinClient).getMedecinById(UUID.fromString(record.getMedecinId()));
        verify(repository).save(record);
    }

    @Test
    void testFindAll() {
        MedicalRecordEntity record1 = new MedicalRecordEntity();
        MedicalRecordEntity record2 = new MedicalRecordEntity();

        when(repository.findAll()).thenReturn(List.of(record1, record2));

        List<MedicalRecordEntity> records = medicalRecordService.findAll();

        assertEquals(2, records.size());
        verify(repository).findAll();
    }

    @Test
    void testFindById() {
        MedicalRecordEntity record = new MedicalRecordEntity();
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.of(record));

        Optional<MedicalRecordEntity> foundRecord = medicalRecordService.findById(id);

        assertTrue(foundRecord.isPresent());
        verify(repository).findById(id);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;

        medicalRecordService.deleteById(id);

        verify(repository).deleteById(id);
    }
}