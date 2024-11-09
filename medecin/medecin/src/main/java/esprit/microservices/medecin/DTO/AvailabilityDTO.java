package esprit.microservices.medecin.DTO;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

public record AvailabilityDTO(
        UUID id ,
        DayOfWeek dayOfWeek,
        List<TimeSlotDTO> timeSlots
) {}