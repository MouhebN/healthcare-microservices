package esprit.microservices.medecin.DTO;

public record CreateMedecinRequest(
        String firstName,
        String lastName,
        String specialty,
        String licenseNumber,
        int experienceYears
) {}
