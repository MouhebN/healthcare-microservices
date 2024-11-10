package esprit.microservices.Fiche.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "medecin-service")
public interface MedecinClient {

    @GetMapping("/v1/medecins/{id}")
    MedecinDTO getMedecinById(@PathVariable UUID id);

}
