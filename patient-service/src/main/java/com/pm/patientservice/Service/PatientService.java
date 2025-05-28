package com.pm.patientservice.Service;

import com.pm.patientservice.DTOs.PatientRequestDTO;
import com.pm.patientservice.DTOs.PatientResponseDTO;
import com.pm.patientservice.Mapper.PatientMapper;
import com.pm.patientservice.Models.Patient;
import com.pm.patientservice.Repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }

}
