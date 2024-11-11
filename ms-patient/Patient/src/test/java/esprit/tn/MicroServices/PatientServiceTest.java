package esprit.tn.MicroServices;

import com.esprit.microservice.patient.Patient;
import com.esprit.microservice.patient.PatientApplication;
import com.esprit.microservice.patient.PatientRepository;
import com.esprit.microservice.patient.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = PatientApplication.class)
class PatientServiceTest {

    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    void testAddPatient() {
        Patient patient = new Patient("John", "Doe", new Date(), "123 Street", "123456789", "john@example.com");

        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientService.addPatient(patient);

        assertEquals("John", savedPatient.getFirstName());
        assertEquals("Doe", savedPatient.getLastName());
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void testUpdatePatient() {
        Long id = 1L;
        Patient existingPatient = new Patient("John", "Doe", new Date(), "123 Street", "123456789", "john@example.com");
        existingPatient.setId(id.intValue());

        Patient newPatient = new Patient("Jane", "Smith", new Date(), "456 Avenue", "987654321", "jane@example.com");

        when(patientRepository.findById(id)).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(existingPatient)).thenReturn(existingPatient);

        Patient updatedPatient = patientService.updatePatient(id, newPatient);

        assertEquals("Jane", updatedPatient.getFirstName());
        assertEquals("Smith", updatedPatient.getLastName());
        assertEquals("456 Avenue", updatedPatient.getAddress());
        assertEquals("987654321", updatedPatient.getPhoneNumber());
        verify(patientRepository, times(1)).findById(id);
        verify(patientRepository, times(1)).save(existingPatient);
    }

    @Test
    void testUpdatePatient_NotFound() {
        Long id = 1L;
        Patient newPatient = new Patient("Jane", "Smith", new Date(), "456 Avenue", "987654321", "jane@example.com");

        when(patientRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> patientService.updatePatient(id, newPatient));
        verify(patientRepository, times(1)).findById(id);
        verify(patientRepository, times(0)).save(any(Patient.class));
    }

    @Test
    void testDeletePatient() {
        Long id = 1L;
        Patient patient = new Patient("Jane", "Smith", new Date(), "456 Avenue", "987654321", "jane@example.com");

        // Mock findById to return the patient, enabling delete operation
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));

        String response = patientService.deletePatient(id);
        assertEquals("Patient deleted successfully", response);
        verify(patientRepository, times(1)).findById(id);
        verify(patientRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeletePatient_NotFound() {
        Long id = 1L;

        when(patientRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> patientService.deletePatient(id));
        verify(patientRepository, times(1)).findById(id);
        verify(patientRepository, times(0)).deleteById(id);
    }

    @Test
    void testRetrieveAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("John", "Doe", new Date(), "123 Street", "123456789", "john@example.com"));
        patients.add(new Patient("Jane", "Smith", new Date(), "456 Avenue", "987654321", "jane@example.com"));

        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> result = patientService.retrieveAllPatients();

        assertEquals(2, result.size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void testRetrievePatient() {
        Long id = 1L;
        Patient patient = new Patient("Jane", "Smith", new Date(), "456 Avenue", "987654321", "jane@example.com");

        // Mock findById to return the patient when requested
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));

        Patient retrievedPatient = patientService.retrievePatient(id);
        assertEquals("Jane", retrievedPatient.getFirstName());
        verify(patientRepository, times(1)).findById(id);
    }

    @Test
    void testRetrievePatient_NotFound() {
        Long id = 1L;

        when(patientRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> patientService.retrievePatient(id));
        verify(patientRepository, times(1)).findById(id);
    }
}
