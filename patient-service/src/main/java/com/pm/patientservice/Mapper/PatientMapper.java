package com.pm.patientservice.Mapper;

import com.pm.patientservice.DTOs.PatientRequestDTO;
import com.pm.patientservice.DTOs.PatientResponseDTO;
import com.pm.patientservice.Models.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());

        return patientDTO;
    }

    public static Patient toModel(PatientRequestDTO patientDTO) {
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setAddress(patientDTO.getAddress());
        patient.setEmail(patientDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientDTO.getDateOfBirth()));

        return patient;
    }
}
