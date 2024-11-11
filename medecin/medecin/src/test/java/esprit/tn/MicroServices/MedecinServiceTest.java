package esprit.tn.MicroServices;

import esprit.microservices.medecin.DTO.CreateMedecinRequest;
import esprit.microservices.medecin.DTO.CreateMedecinResponse;
import esprit.microservices.medecin.Entity.MedecinEntity;
import esprit.microservices.medecin.Mappers.MedecinMapper;
import esprit.microservices.medecin.Repository.MedecinRepository;
import esprit.microservices.medecin.Services.MedecinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class MedecinServiceTest {

    @Mock
    private MedecinRepository medecinRepository;

    @Mock
    private MedecinMapper medecinMapper;

    @InjectMocks
    private MedecinService medecinService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMedecin() {
        CreateMedecinRequest request = new CreateMedecinRequest(
                "John",              // First name
                "Doe",               // Last name
                "Cardiology",        // Specialty
                "LN12345",           // License number
                10                   // Experience years
        );

        MedecinEntity medecinEntity = new MedecinEntity();
        CreateMedecinResponse response = new CreateMedecinResponse(
                UUID.randomUUID(),   // ID
                "John",              // First name
                "Doe",               // Last name
                "Cardiology",        // Specialty
                "LN12345",           // License number
                10                   // Experience years
        );

        when(medecinMapper.toEntity(request)).thenReturn(medecinEntity);
        when(medecinRepository.save(medecinEntity)).thenReturn(medecinEntity);
        when(medecinMapper.toResponse(medecinEntity)).thenReturn(response);

        CreateMedecinResponse result = medecinService.createMedecin(request);

        assertEquals(response, result);
        verify(medecinMapper, times(1)).toEntity(request);
        verify(medecinRepository, times(1)).save(medecinEntity);
        verify(medecinMapper, times(1)).toResponse(medecinEntity);
    }


    @Test
    void testGetMedecinById() {
        UUID id = UUID.randomUUID();
        MedecinEntity medecinEntity = new MedecinEntity();
        CreateMedecinResponse response = new CreateMedecinResponse(
                id,                     // UUID id
                "John",                 // String firstName
                "Doe",                  // String lastName
                "Cardiology",           // String specialty
                "LN12345",              // String licenseNumber
                10                      // int experienceYears
        );

        when(medecinRepository.findById(id)).thenReturn(Optional.of(medecinEntity));
        when(medecinMapper.toResponse(medecinEntity)).thenReturn(response);

        Optional<CreateMedecinResponse> result = medecinService.getMedecinById(id);

        assertTrue(result.isPresent());
        assertEquals(response, result.get());
        verify(medecinRepository, times(1)).findById(id);
        verify(medecinMapper, times(1)).toResponse(medecinEntity);
    }

    @Test
    void testGetAllMedecins() {
        MedecinEntity medecinEntity = new MedecinEntity();

        // Create a sample CreateMedecinResponse with required parameters
        CreateMedecinResponse response = new CreateMedecinResponse(
                UUID.randomUUID(),   // id
                "John",              // firstName
                "Doe",               // lastName
                "Cardiology",        // specialty
                "LN12345",           // licenseNumber
                10                   // experienceYears
        );

        List<MedecinEntity> medecins = List.of(medecinEntity);
        List<CreateMedecinResponse> responses = List.of(response);

        when(medecinRepository.findAll()).thenReturn(medecins);
        when(medecinMapper.toResponse(medecinEntity)).thenReturn(response);

        List<CreateMedecinResponse> result = medecinService.getAllMedecins();

        assertEquals(responses, result);
        verify(medecinRepository, times(1)).findAll();
        verify(medecinMapper, times(1)).toResponse(medecinEntity);
    }


    @Test
    void testUpdateMedecin() {
        UUID id = UUID.randomUUID();
        CreateMedecinRequest request = new CreateMedecinRequest(
                "Jane",              // First name
                "Smith",             // Last name
                "Neurology",         // Specialty
                "LN54321",           // License number
                15                   // Experience years
        );

        MedecinEntity medecinEntity = new MedecinEntity();
        CreateMedecinResponse response = new CreateMedecinResponse(
                id,                  // ID
                "Jane",              // First name
                "Smith",             // Last name
                "Neurology",         // Specialty
                "LN54321",           // License number
                15                   // Experience years
        );

        when(medecinRepository.existsById(id)).thenReturn(true);
        when(medecinMapper.toEntity(request)).thenReturn(medecinEntity);
        when(medecinRepository.save(medecinEntity)).thenReturn(medecinEntity);
        when(medecinMapper.toResponse(medecinEntity)).thenReturn(response);

        CreateMedecinResponse result = medecinService.updateMedecin(id, request);

        assertEquals(response, result);
        verify(medecinRepository, times(1)).existsById(id);
        verify(medecinMapper, times(1)).toEntity(request);
        verify(medecinRepository, times(1)).save(medecinEntity);
        verify(medecinMapper, times(1)).toResponse(medecinEntity);
    }

}
