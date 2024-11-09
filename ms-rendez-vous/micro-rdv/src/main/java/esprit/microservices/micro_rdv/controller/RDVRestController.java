package esprit.microservices.micro_rdv.controller;

import esprit.microservices.micro_rdv.entity.RDV;
import esprit.microservices.micro_rdv.service.IRDVService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rdv")
public class RDVRestController {
    IRDVService rdvService;

    // Retrieve all RDVs
    @GetMapping("/retrieve-all-rdv")
    public List<RDV> getAllRDVs() {
        return rdvService.retrieveAllRDVs();
    }

    // Retrieve a specific RDV by ID
    @GetMapping("/retrieve-rdv/{id}")
    public RDV getRDVById(@PathVariable("id") Long rdvId) {
        return rdvService.retrieveRDV(rdvId);
    }

    // Add a new RDV
    @PostMapping("/add-rdv")
    public RDV addRDV(@RequestBody RDV rdv) {
        return rdvService.addRDV(rdv);
    }

    // Update an existing RDV
    @PutMapping("/modify-rdv")
    public RDV updateRDV(@RequestBody RDV rdv) {
        return rdvService.modifyRDV(rdv);
    }

    // Delete an RDV by ID
    @DeleteMapping("/remove-rdv/{id}")
    public void deleteRDV(@PathVariable("id") Long rdvId) {
        rdvService.removeRDV(rdvId);
    }

    // Affecter RDVs to a Patient and Doctor
    @PutMapping("/affecter/{rdvId}")
    public RDV affecterRDVs(@PathVariable("rdvId") Long rdvId,
                            @RequestParam List<String> idPatient,
                            @RequestParam List<String> idDocteur) {
        return rdvService.affecterRDVsA_Patient_docteur(idPatient, idDocteur, rdvId);
    }
}
