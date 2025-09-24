package com.rmage.rmage_backend.service;

import com.rmage.rmage_backend.repository.LandlordRepository;
import com.rmage.rmage_backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // This enables Mockito for our test
class AuthServiceTest {

    @Mock // Mockito will create a fake instance of this repository
    private UserRepository userRepository;

    @Mock // Mockito will create a fake instance of this repository
    private LandlordRepository landlordRepository;

    @Mock // Mockito will create a fake instance of this
    private PasswordEncoder passwordEncoder;

    @InjectMocks // This creates a real AuthService but injects the MOCKS above into it
    private AuthService authService;

    @Test
    void whenRegisterLandlord_thenUserAndLandlordAreSaved() {
        // Given
        String email = "test@example.com";
        String password = "Password123!";

        // When
        authService.registerLandlord(email, password);

        // Then
        // We verify that the "save" method was called on our fake repositories.
        // This proves our service logic is working as expected.
        verify(userRepository).save(any());
        verify(landlordRepository).save(any());
    }
}