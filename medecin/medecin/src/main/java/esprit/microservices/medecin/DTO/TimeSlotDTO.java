package esprit.microservices.medecin.DTO;

import java.util.UUID;

public record TimeSlotDTO(
        UUID id ,
        String startTime,
        String endTime
) {}