package com.rmage.rmage_backend.steps;

import com.rmage.rmage_backend.dto.RegisterLandlordRequest;
import com.rmage.rmage_backend.model.Landlord;
import com.rmage.rmage_backend.model.Role;
import com.rmage.rmage_backend.model.User;
import com.rmage.rmage_backend.repository.LandlordRepository;
import com.rmage.rmage_backend.repository.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthSteps {

    @Autowired // This lets us make real HTTP requests to our running test application
    private TestRestTemplate restTemplate;

    @Autowired // This lets us check the real test database
    private UserRepository userRepository;

    @Autowired // This lets us check the real test database
    private LandlordRepository landlordRepository;

    private ResponseEntity<String> response; // A place to store the API response

    @Given("the system is ready for registrations")
    public void the_system_is_ready_for_registrations() {
        // For a clean test, we should clear the repositories before each scenario
        userRepository.deleteAll();
        landlordRepository.deleteAll();
    }

    @When("a user submits a registration request with email {string} and password {string}")
    public void a_user_submits_a_registration_request_with_email_and_password(String email, String password) {
        // Create the request body (DTO) for our API call
        RegisterLandlordRequest request = new RegisterLandlordRequest();
        request.setEmail(email);
        request.setPassword(password);

        // Make the actual POST request to our /auth/register/landlord endpoint
        response = restTemplate.postForEntity("/auth/register/landlord", request, String.class);
    }

    @Then("a new user with email {string} and role {string} should be created")
    public void a_new_user_with_email_and_role_should_be_created(String email, String role) {
        // First, let's check that the API call was successful
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        // Now, let's check the database to verify the user was created
        User savedUser = userRepository.findByEmail(email).orElse(null);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo(email);
        assertThat(savedUser.getRole()).isEqualTo(Role.valueOf(role));
        // We can also check that the password was hashed and is not the plain text password
        assertThat(savedUser.getPassword()).isNotEqualTo("ValidPassword123!");
    }

    @Then("a new landlord profile linked to this user should exist in the system")
    public void a_new_landlord_profile_linked_to_this_user_should_exist_in_the_system() {
        // Find the user first
        User savedUser = userRepository.findAll().stream().findFirst().orElse(null);
        assertThat(savedUser).isNotNull();

        // Now check if a landlord profile is linked to this user
        Landlord savedLandlord = landlordRepository.findByUserId(savedUser.getId()).orElse(null);

        assertThat(savedLandlord).isNotNull();
        assertThat(savedLandlord.getUser().getId()).isEqualTo(savedUser.getId());
    }
}