package com.pm.patientservice.Controller;

import com.pm.patientservice.DTOs.PatientRequestDTO;
import com.pm.patientservice.DTOs.PatientResponseDTO;
import com.pm.patientservice.DTOs.Validators.CreatePatientValidationGroup;
import com.pm.patientservice.Service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")    //  http://localhost:4000/patients
@Tag(name="Patient", description = "Patient Management API")
public class PatientController {
    private final PatientService patientService;

    PatientController(PatientService patientService, LocalValidatorFactoryBean defaultValidator) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get all patients", description = "Retrieve a list of all patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }


    @PostMapping
    @Operation(summary = "Create a new patient", description = "Add a new patient to the system")
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class})
                                                                @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update an existing patient", description = "Modify the details of an existing patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable("id") UUID id,
                                                            @Validated({Default.class})
                                                            @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient", description = "Remove a patient from the system")
    public ResponseEntity<String> deletePatient(@PathVariable("id") UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok().body("Patient with id " + id + " deleted successfully.");
    }

}
