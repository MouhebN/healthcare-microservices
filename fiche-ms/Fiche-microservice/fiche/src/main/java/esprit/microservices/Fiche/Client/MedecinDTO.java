package esprit.microservices.Fiche.Client;

import java.util.UUID;

public record MedecinDTO(
        UUID id,
        String firstName,
        String lastName
) {}
