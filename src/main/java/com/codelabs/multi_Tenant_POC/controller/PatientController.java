package com.codelabs.multi_Tenant_POC.controller;

import com.codelabs.multi_Tenant_POC.entity.Patient;
import com.codelabs.multi_Tenant_POC.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @GetMapping
    public List<Patient> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        return repository.save(patient);
    }
}