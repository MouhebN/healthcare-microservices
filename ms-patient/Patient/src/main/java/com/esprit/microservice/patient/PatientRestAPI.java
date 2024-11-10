package com.esprit.microservice.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientRestAPI {

    @Autowired
    private PatientService patientService;

    // Retrieve all patients
    @GetMapping("/retrieve-all-patients")
    public List<Patient> getAllPatients() {
        return patientService.retrieveAllPatients();
    }

    // Retrieve a specific patient by ID
    @GetMapping("/retrieve-patient/{id}")
    public Patient getPatientById(@PathVariable("id") Long patientId) {
        return patientService.retrievePatient(patientId);
    }

    // Add a new patient
    @PostMapping("/add-patient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient createdPatient = patientService.addPatient(patient);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    // Update an existing patient
    @PutMapping("/modify-patient/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Long patientId,
                                                 @RequestBody Patient patient) {
        Patient updatedPatient = patientService.updatePatient(patientId, patient);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }
    // Delete a patient by ID
    @DeleteMapping("/remove-patient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") Long patientId) {
        String result = patientService.deletePatient(patientId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
