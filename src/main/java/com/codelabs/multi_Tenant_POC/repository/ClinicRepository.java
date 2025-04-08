package com.codelabs.multi_Tenant_POC.repository;

import com.codelabs.multi_Tenant_POC.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}