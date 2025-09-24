package com.rmage.rmage_backend.controller;

import com.rmage.rmage_backend.dto.RegisterLandlordRequest;
import com.rmage.rmage_backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register/landlord")
    public ResponseEntity<String> registerLandlord(@RequestBody RegisterLandlordRequest request) {
        authService.registerLandlord(request.getEmail(), request.getPassword());
        return ResponseEntity.ok("Landlord registered successfully");
    }
}
