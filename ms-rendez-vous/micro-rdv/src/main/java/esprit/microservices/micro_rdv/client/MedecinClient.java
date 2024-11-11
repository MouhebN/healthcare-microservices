package esprit.microservices.micro_rdv.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "medecin-service")
public interface MedecinClient {

    @GetMapping("/v1/medecins/{id}")
    MedecinDTO getMedecinById(@PathVariable UUID id);

    @GetMapping("/v1/availability/check/{medecinId}")
    boolean checkAvailability(@PathVariable UUID medecinId,
                              @RequestParam String daterdv,
                              @RequestParam String heureRDV);
}
