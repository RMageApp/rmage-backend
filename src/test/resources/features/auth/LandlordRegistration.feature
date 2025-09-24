Feature: Landlord Account Registration
  As a prospective landlord, I want to create a secure account
  so that I can start using the RMage application.

  Scenario: Successful registration with a new, valid email
    Given the system is ready for registrations
    When a user submits a registration request with email "newlandlord@example.com" and password "ValidPassword123!"
    Then a new user with email "newlandlord@example.com" and role "LANDLORD" should be created
    And a new landlord profile linked to this user should exist in the system