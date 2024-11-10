package esprit.microservices.micro_rdv.Dto;

public record PatientDTO(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email
) {}

