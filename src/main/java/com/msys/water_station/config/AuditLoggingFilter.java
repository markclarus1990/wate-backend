package com.msys.water_station.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.msys.water_station.Model.AuditLog;
import com.msys.water_station.repo.AuditLogRepo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuditLoggingFilter extends OncePerRequestFilter {

    @Autowired
    private AuditLogRepo auditLogRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // Always continue the chain first
        filterChain.doFilter(request, response);

        // Skip auth endpoints
        if (request.getRequestURI().contains("/api/auth")) {
            return;
        }

        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null &&
                auth.isAuthenticated() &&
                !"anonymousUser".equals(auth.getPrincipal())) {

            AuditLog log = new AuditLog();
            log.setUsername(auth.getName());
            log.setRole(auth.getAuthorities().toString());
            log.setMethod(request.getMethod());
            log.setEndpoint(request.getRequestURI());
            log.setIpAddress(request.getRemoteAddr());
            log.setStatus(response.getStatus());

            auditLogRepo.save(log);
        }
    }
}
