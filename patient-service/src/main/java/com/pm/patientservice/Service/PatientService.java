package com.pm.patientservice.Service;

import com.pm.patientservice.DTOs.PatientRequestDTO;
import com.pm.patientservice.DTOs.PatientResponseDTO;
import com.pm.patientservice.Exception.EmailAlreadyExistsException;
import com.pm.patientservice.Exception.PatientNotFoundException;
import com.pm.patientservice.Mapper.PatientMapper;
import com.pm.patientservice.Models.Patient;
import com.pm.patientservice.Repository.PatientRepository;
import com.pm.patientservice.grpc.BillingServiceGrpcClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    PatientService(PatientRepository patientRepository,
                   BillingServiceGrpcClient billingServiceGrpcClient) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + patientRequestDTO.getEmail());
        }
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));

        billingServiceGrpcClient.createBillingAccount(
                newPatient.getId().toString(),
                newPatient.getName(),
                newPatient.getEmail());
        return PatientMapper.toDTO(newPatient);


    }

    public PatientResponseDTO updatePatient(UUID uuid, PatientRequestDTO patientRequestDTO) {

        Patient patient = patientRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Patient not found with id: " + uuid));
        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), uuid)) {
            throw new EmailAlreadyExistsException("Email already exists: " + patientRequestDTO.getEmail());
        }
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    public String deletePatient(UUID uuid) {
        if (!patientRepository.existsById(uuid)) {
            throw new PatientNotFoundException("Patient not found with id: " + uuid);
        }
        patientRepository.deleteById(uuid);
        return "Patient with id " + uuid + " deleted successfully.";
    }
}
