package com.codelabs.multi_Tenant_POC.repository;

import com.codelabs.multi_Tenant_POC.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}