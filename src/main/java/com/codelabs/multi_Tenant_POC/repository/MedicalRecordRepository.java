package com.codelabs.multi_Tenant_POC.repository;

import com.codelabs.multi_Tenant_POC.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}