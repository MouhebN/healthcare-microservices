package esprit.microservices.micro_rdv.Dto;

import java.util.UUID;

public record MedecinDTO(
        UUID id,
        String firstName,
        String lastName
) {}
