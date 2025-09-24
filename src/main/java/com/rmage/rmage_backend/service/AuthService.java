package com.rmage.rmage_backend.service;

import com.rmage.rmage_backend.exception.UserAlreadyExistsException;
import com.rmage.rmage_backend.model.Landlord;
import com.rmage.rmage_backend.model.Role;
import com.rmage.rmage_backend.model.User;
import com.rmage.rmage_backend.repository.LandlordRepository;
import com.rmage.rmage_backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final LandlordRepository landlordRepository;
    private final PasswordEncoder passwordEncoder;

    // Spring will automatically inject the real beans here
    public AuthService(UserRepository userRepository, LandlordRepository landlordRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.landlordRepository = landlordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional // Ensures that if one save fails, the other is rolled back
    public void registerLandlord(String email, String password) {
        // REFACTOR: Check if a user with this email already exists
        // TODO: This logic is tested by the happy-path BDD scenario.
        //  The error-path is tracked in issue #11.
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new UserAlreadyExistsException("A user with email " + email + " already exists.");
        });

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // HASH the password
        user.setRole(Role.LANDLORD);

        Landlord landlord = new Landlord();
        landlord.setUser(user);
        // We can set other default landlord properties here in the future
        landlordRepository.save(landlord);
    }
}
