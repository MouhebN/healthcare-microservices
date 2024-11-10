package esprit.microservices.facturation.Client;

public record PatientDTO(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email
) {}

