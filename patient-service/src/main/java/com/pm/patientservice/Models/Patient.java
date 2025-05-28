package com.pm.patientservice.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Patient {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String Name;

    @NotNull
    @Email
    @Column(unique = true)
    private String Email;

    @NotNull
    private String Address;

    @NotNull
    private LocalDate DateOfBirth;

    @NotNull
    private LocalDate RegisteredDate;
}
