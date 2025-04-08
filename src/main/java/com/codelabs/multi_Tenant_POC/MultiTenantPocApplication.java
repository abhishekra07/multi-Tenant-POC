package com.codelabs.multi_Tenant_POC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.codelabs.multi_Tenant_POC.repository")
public class MultiTenantPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiTenantPocApplication.class, args);
	}

}
