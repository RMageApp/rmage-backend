package com.rmage.rmage_backend.integration.keycloak;

import com.rmage.rmage_backend.config.KeycloakConfig;
import com.rmage.rmage_backend.common.dto.RegisterRequest;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final Keycloak keycloak;
    private final KeycloakConfig keycloakConfig;

    public void createUser(RegisterRequest request) {
        log.info("Creating user in Keycloak: {}", request.getEmail());

        RealmResource realmResource = keycloak.realm(keycloakConfig.getRealm());
        UsersResource usersResource = realmResource.users();

        // Check if user already exists
        List<UserRepresentation> existingUsers = usersResource.searchByEmail(request.getEmail(), true);
        if (!existingUsers.isEmpty()) {
            throw new RuntimeException("User already exists with email: " + request.getEmail());
        }

        // Create user representation
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(request.getEmail());
        user.setEmail(request.getEmail());
        user.setEmailVerified(true);

        // Create user
        Response response = usersResource.create(user);

        if (response.getStatus() != 201) {
            String error = response.readEntity(String.class);
            log.error("Failed to create user: {}", error);
            throw new RuntimeException("Failed to create user: " + error);
        }

        // Extract user ID from Location header
        String locationHeader = response.getHeaderString("Location");
        String userId = locationHeader.substring(locationHeader.lastIndexOf('/') + 1);
        log.info("User created with ID: {}", userId);

        // Set password
        setPassword(usersResource, userId, request.getPassword());

        // Assign role
        assignRole(realmResource, userId, request.getRole());

        log.info("User registration completed successfully for: {}", request.getEmail());
    }

    private void setPassword(UsersResource usersResource, String userId, String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);

        usersResource.get(userId).resetPassword(credential);
        log.info("Password set for user ID: {}", userId);
    }

    private void assignRole(RealmResource realmResource, String userId, String roleName) {
        RoleRepresentation role = realmResource.roles().get(roleName).toRepresentation();
        realmResource.users().get(userId).roles().realmLevel().add(Collections.singletonList(role));
        log.info("Role {} assigned to user ID: {}", roleName, userId);
    }
}