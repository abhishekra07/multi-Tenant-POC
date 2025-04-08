package com.codelabs.multi_Tenant_POC.repository;

import com.codelabs.multi_Tenant_POC.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}