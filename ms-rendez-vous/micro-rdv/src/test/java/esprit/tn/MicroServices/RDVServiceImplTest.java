package esprit.tn.MicroServices;

import esprit.microservices.micro_rdv.MicroRdvApplication;
import esprit.microservices.micro_rdv.client.MedecinClient;
import esprit.microservices.micro_rdv.client.MedecinDTO;
import esprit.microservices.micro_rdv.client.PatientClient;
import esprit.microservices.micro_rdv.client.PatientDTO;
import esprit.microservices.micro_rdv.entity.RDV;
import esprit.microservices.micro_rdv.repository.RDVRepository;
import esprit.microservices.micro_rdv.service.RDVServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MicroRdvApplication.class)
class RDVServiceImplTest {

    @MockBean
    private RDVRepository rdvRepository;

    @MockBean
    private PatientClient patientClient;

    @MockBean
    private MedecinClient medecinClient;

    @Autowired
    private RDVServiceImpl rdvService;

    @Test
    void testRetrieveAllRDVs() {
        RDV rdv1 = new RDV();
        RDV rdv2 = new RDV();
        List<RDV> rdvs = Arrays.asList(rdv1, rdv2);

        when(rdvRepository.findAll()).thenReturn(rdvs);

        List<RDV> result = rdvService.retrieveAllRDVs();
        assertEquals(2, result.size());
        verify(rdvRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveRDV() {
        Long rdvId = 1L;
        RDV rdv = new RDV();
        rdv.setIdRDV(rdvId);

        when(rdvRepository.findById(rdvId)).thenReturn(Optional.of(rdv));

        RDV result = rdvService.retrieveRDV(rdvId);
        assertNotNull(result);
        assertEquals(rdvId, result.getIdRDV());
        verify(rdvRepository, times(1)).findById(rdvId);
    }

    @Test
    void testAddRDV() {
        RDV rdv = new RDV();
        rdv.setPatientId("1");
        rdv.setMedecinId(UUID.randomUUID().toString());
        rdv.setDateRDV("2024-12-12");
        rdv.setHeureRDV("10:00");

        PatientDTO patientDTO = new PatientDTO(1L, "John", "Doe", "123456789", "johndoe@example.com");
        MedecinDTO medecinDTO = new MedecinDTO(UUID.randomUUID(), "Dr.", "Smith");

        when(patientClient.getPatientById(anyLong())).thenReturn(patientDTO);
        when(medecinClient.getMedecinById(any(UUID.class))).thenReturn(medecinDTO);
        when(medecinClient.checkAvailability(any(UUID.class), anyString(), anyString())).thenReturn(true);
        when(rdvRepository.save(any(RDV.class))).thenReturn(rdv);

        RDV result = rdvService.addRDV(rdv);
        assertNotNull(result);
        verify(patientClient, times(1)).getPatientById(anyLong());
        verify(medecinClient, times(1)).getMedecinById(any(UUID.class));
        verify(medecinClient, times(1)).checkAvailability(any(UUID.class), anyString(), anyString());
        verify(rdvRepository, times(1)).save(any(RDV.class));
    }


    @Test
    void testRemoveRDV() {
        Long rdvId = 1L;
        doNothing().when(rdvRepository).deleteById(rdvId);

        rdvService.removeRDV(rdvId);
        verify(rdvRepository, times(1)).deleteById(rdvId);
    }

    @Test
    void testModifyRDV() {
        RDV rdv = new RDV();
        rdv.setIdRDV(1L);
        rdv.setPatientId("1");
        rdv.setMedecinId(UUID.randomUUID().toString());

        when(rdvRepository.save(any(RDV.class))).thenReturn(rdv);

        RDV result = rdvService.modifyRDV(rdv);
        assertNotNull(result);
        assertEquals(rdv.getIdRDV(), result.getIdRDV());
        verify(rdvRepository, times(1)).save(rdv);
    }
}
