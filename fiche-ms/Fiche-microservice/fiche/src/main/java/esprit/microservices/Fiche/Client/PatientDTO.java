package esprit.microservices.Fiche.Client;

public record PatientDTO(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email
) {}

