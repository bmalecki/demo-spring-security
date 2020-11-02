package com.cognifide.rbac.poc.demo.endpoint;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    String getSth() {
        return "STH";
    }

    @PreAuthorize("hasAuthority('ROLE_USER:'+ #scope)")
    String getSth(String scope) {
        return "Access to USER:" + scope;
    }
}
