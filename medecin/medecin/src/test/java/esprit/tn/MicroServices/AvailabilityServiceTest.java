package esprit.tn.MicroServices;

import esprit.microservices.medecin.DTO.AvailabilityDTO;
import esprit.microservices.medecin.Entity.AvailabilityEntity;
import esprit.microservices.medecin.Entity.MedecinEntity;
import esprit.microservices.medecin.Entity.TimeSlotEntity;
import esprit.microservices.medecin.MedecinApplication;
import esprit.microservices.medecin.Repository.AvailabilityRepository;
import esprit.microservices.medecin.Repository.MedecinRepository;
import esprit.microservices.medecin.Mappers.AvailabilityMapper;
import esprit.microservices.medecin.Mappers.TimeSlotMapper;
import esprit.microservices.medecin.Services.AvailabilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes= MedecinApplication.class)
class AvailabilityServiceTest {

    @MockBean
    private AvailabilityRepository availabilityRepository;

    @MockBean
    private MedecinRepository medecinRepository;

    @MockBean
    private AvailabilityMapper availabilityMapper;

    @MockBean
    private TimeSlotMapper timeSlotMapper;

    @Autowired
    private AvailabilityService availabilityService;

    @BeforeEach
    void setUp() {
        // No need for MockitoAnnotations.openMocks(this); because @MockBean is managed by Spring
    }

    @Test
    void testCreateAvailability() {
        UUID medecinId = UUID.randomUUID();

        AvailabilityDTO availabilityDTO = new AvailabilityDTO(UUID.randomUUID(), LocalDate.now(), new ArrayList<>());
        AvailabilityEntity availabilityEntity = new AvailabilityEntity();
        MedecinEntity medecinEntity = new MedecinEntity();
        medecinEntity.setAvailability(new ArrayList<>());

        when(medecinRepository.findById(medecinId)).thenReturn(Optional.of(medecinEntity));
        when(availabilityMapper.toEntity(availabilityDTO)).thenReturn(availabilityEntity);
        when(availabilityRepository.save(availabilityEntity)).thenReturn(availabilityEntity);
        when(availabilityMapper.toDTO(availabilityEntity)).thenReturn(availabilityDTO);

        AvailabilityDTO result = availabilityService.createAvailability(medecinId, availabilityDTO);

        assertEquals(availabilityDTO, result);
        verify(medecinRepository, times(1)).findById(medecinId);
        verify(availabilityRepository, times(1)).save(availabilityEntity);
        verify(medecinRepository, times(1)).save(medecinEntity);
    }

    @Test
    void testGetAvailabilityForMedecin() {
        UUID medecinId = UUID.randomUUID();
        MedecinEntity medecinEntity = new MedecinEntity();
        List<AvailabilityEntity> availabilityList = new ArrayList<>();
        medecinEntity.setAvailability(availabilityList);
        when(medecinRepository.findById(medecinId)).thenReturn(Optional.of(medecinEntity));

        List<AvailabilityDTO> availabilityDTOList = new ArrayList<>();
        when(availabilityMapper.toDTO(any(AvailabilityEntity.class))).thenReturn(new AvailabilityDTO(UUID.randomUUID(), LocalDate.now(), new ArrayList<>()));

        List<AvailabilityDTO> result = availabilityService.getAvailabilityForMedecin(medecinId);

        assertEquals(availabilityDTOList.size(), result.size());
        verify(medecinRepository, times(1)).findById(medecinId);
    }

    @Test
    void testDeleteAvailability() {
        UUID availabilityId = UUID.randomUUID();
        AvailabilityEntity availabilityEntity = new AvailabilityEntity();
        when(availabilityRepository.findById(availabilityId)).thenReturn(Optional.of(availabilityEntity));

        availabilityService.deleteAvailability(availabilityId);

        verify(availabilityRepository, times(1)).delete(availabilityEntity);
    }

    @Test
    void testCheckAvailabilityForDateAndTime() {
        UUID medecinId = UUID.randomUUID();
        String dateStr = "2024-12-12";
        String timeStr = "10:00";

        MedecinEntity medecinEntity = new MedecinEntity();
        List<AvailabilityEntity> availabilityList = new ArrayList<>();
        AvailabilityEntity availability = new AvailabilityEntity();
        availability.setAvailableDate(LocalDate.parse(dateStr));

        List<TimeSlotEntity> timeSlots = new ArrayList<>();
        TimeSlotEntity timeSlot = new TimeSlotEntity();
        timeSlot.setStartTime("09:00");
        timeSlot.setEndTime("11:00");
        timeSlots.add(timeSlot);
        availability.setTimeSlots(timeSlots);

        availabilityList.add(availability);
        medecinEntity.setAvailability(availabilityList);

        when(medecinRepository.findById(medecinId)).thenReturn(Optional.of(medecinEntity));

        boolean result = availabilityService.checkAvailabilityForDateAndTime(medecinId, dateStr, timeStr);

        assertTrue(result);
        verify(medecinRepository, times(1)).findById(medecinId);
    }
}