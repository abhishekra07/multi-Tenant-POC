package com.codelabs.multi_Tenant_POC.config;

import com.codelabs.multi_Tenant_POC.context.TenantContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TenantFilter extends OncePerRequestFilter {

    private static final String TENANT_HEADER = "X-Tenant-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            String tenantId = request.getHeader(TENANT_HEADER);

            if (tenantId == null || tenantId.isBlank()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required tenant header: " + TENANT_HEADER);
                return;
            }

            TenantContext.setTenantId(tenantId);
            chain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }
}