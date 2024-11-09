package esprit.microservices.medecin.Controllers;

import esprit.microservices.medecin.DTO.AvailabilityDTO;
import esprit.microservices.medecin.Services.AvailabilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/availability/")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }
    @PostMapping("/{medecinId}")
    public ResponseEntity<AvailabilityDTO> createAvailability(
            @PathVariable UUID medecinId,
            @RequestBody AvailabilityDTO availabilityDTO) {
        AvailabilityDTO createdAvailability = availabilityService.createAvailability(medecinId, availabilityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAvailability);
    }

    @GetMapping("/{medecinId}")
    public ResponseEntity<List<AvailabilityDTO>> getAvailabilityForMedecin(@PathVariable UUID medecinId) {
        List<AvailabilityDTO> availabilityList = availabilityService.getAvailabilityForMedecin(medecinId);
        return ResponseEntity.ok(availabilityList);
    }

    @DeleteMapping("/{availabilityId}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable UUID availabilityId) {
        availabilityService.deleteAvailability(availabilityId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{availabilityId}")
    public ResponseEntity<AvailabilityDTO> updateAvailability(
            @PathVariable UUID availabilityId,
            @RequestBody AvailabilityDTO availabilityDTO) {
        AvailabilityDTO updatedAvailability = availabilityService.updateAvailability(availabilityId, availabilityDTO);
        return ResponseEntity.ok(updatedAvailability);
    }
}
