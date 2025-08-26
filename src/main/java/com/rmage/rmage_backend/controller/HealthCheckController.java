package com.rmage.rmage_backend.controller;

import com.rmage.rmage_backend.dto.HealthStatusDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public HealthStatusDto healthCheck() {
        return new HealthStatusDto("Backend is running");
    }
}
