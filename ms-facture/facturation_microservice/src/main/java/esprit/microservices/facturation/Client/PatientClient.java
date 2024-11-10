package esprit.microservices.facturation.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service")
public interface PatientClient {

    @GetMapping("/patients/retrieve-patient/{patientId}")
    PatientDTO getPatientById(@PathVariable("patientId") Long patientId);
}