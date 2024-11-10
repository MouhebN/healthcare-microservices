package esprit.microservices.micro_rdv.client;

import java.util.UUID;

public record MedecinDTO(
        UUID id,
        String firstName,
        String lastName
) {}
