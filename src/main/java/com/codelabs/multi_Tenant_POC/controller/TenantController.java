package com.codelabs.multi_Tenant_POC.controller;

import com.codelabs.multi_Tenant_POC.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenant")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping("/register")
    public ResponseEntity<String> registerTenant(@RequestParam String name) {
        tenantService.createTenantSchema(name);
        return ResponseEntity.ok("Tenant schema created: " + name);
    }
}