package esprit.microservices.medecin.DTO;

import java.util.UUID;

public record CreateMedecinResponse(
        UUID id,
        String firstName,
        String lastName,
        String specialty,
        String licenseNumber,
        int experienceYears
) {}