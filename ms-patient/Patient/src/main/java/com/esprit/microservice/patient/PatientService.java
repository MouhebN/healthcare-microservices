package com.esprit.microservice.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Add a new patient
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Update an existing patient
    public Patient updatePatient(Long id, Patient newPatient) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));

        existingPatient.setFirstName(newPatient.getFirstName());
        existingPatient.setLastName(newPatient.getLastName());
        existingPatient.setDateOfBirth(newPatient.getDateOfBirth());
        existingPatient.setAddress(newPatient.getAddress());
        existingPatient.setPhoneNumber(newPatient.getPhoneNumber());
        existingPatient.setEmail(newPatient.getEmail());

        return patientRepository.save(existingPatient);
    }

    // Delete a patient
    public String deletePatient(Long id) {
        if (patientRepository.findById(id).isPresent()) {
            patientRepository.deleteById(id);
            return "Patient deleted successfully";
        } else {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
    }

    // Retrieve all patients
    public List<Patient> retrieveAllPatients() {
        return patientRepository.findAll();
    }

    // Retrieve a specific patient by ID
    public Patient retrievePatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }
}
