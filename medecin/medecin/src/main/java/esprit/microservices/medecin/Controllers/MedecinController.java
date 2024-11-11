package esprit.microservices.medecin.Controllers;


import esprit.microservices.medecin.DTO.CreateMedecinRequest;
import esprit.microservices.medecin.DTO.CreateMedecinResponse;
import esprit.microservices.medecin.Services.MedecinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/medecins")
public class MedecinController {

    private final MedecinService medecinService;

    public MedecinController(MedecinService medecinService) {
        this.medecinService = medecinService;
    }

    @PostMapping
    public ResponseEntity<CreateMedecinResponse> createMedecin(@RequestBody CreateMedecinRequest request) {
        CreateMedecinResponse response = medecinService.createMedecin(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateMedecinResponse> getMedecinById(@PathVariable UUID id) {
        return medecinService.getMedecinById(id)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<CreateMedecinResponse>> getAllMedecins() {
        List<CreateMedecinResponse> responses = medecinService.getAllMedecins();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateMedecinResponse> updateMedecin(@PathVariable UUID id, @RequestBody CreateMedecinRequest request) {
        try {
            CreateMedecinResponse response = medecinService.updateMedecin(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
