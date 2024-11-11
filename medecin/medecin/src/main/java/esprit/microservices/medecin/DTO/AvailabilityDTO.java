package esprit.microservices.medecin.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record AvailabilityDTO(
        UUID id ,
        LocalDate availableDate,
        List<TimeSlotDTO> timeSlots
) {}