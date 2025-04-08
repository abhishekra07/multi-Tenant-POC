package com.codelabs.multi_Tenant_POC.repository;

import com.codelabs.multi_Tenant_POC.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}