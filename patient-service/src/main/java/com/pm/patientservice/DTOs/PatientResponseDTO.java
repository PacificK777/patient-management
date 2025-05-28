package com.pm.patientservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientResponseDTO {
    private String id;
    private String name;
    private String address;
    private String dateOfBirth;
    private String email;
}
