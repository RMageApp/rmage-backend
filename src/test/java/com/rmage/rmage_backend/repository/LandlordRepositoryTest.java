package com.rmage.rmage_backend.repository;

import com.rmage.rmage_backend.model.Landlord;
import com.rmage.rmage_backend.model.Role;
import com.rmage.rmage_backend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LandlordRepositoryTest {

    @Autowired
    private LandlordRepository landlordRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenSaveLandlord_thenCanBeFoundById() {
        Landlord landlord = new Landlord();
        User user = new User();

        user.setEmail("test@example.com");
        user.setPassword("TestPassword123");
        user.setRole(Role.LANDLORD);

        landlord.setUser(user);
        landlord.setPhone("123-456-7890");
        landlord.setName("Test Landlord");

        //When
        Landlord savedLandlord = landlordRepository.save(landlord);

        //Then
        assertThat(savedLandlord).isNotNull();
        assertThat(savedLandlord.getId()).isNotNull();
        assertThat(savedLandlord.getUser().getEmail()).isEqualTo("test@example.com");
        assertThat(savedLandlord.getName()).isEqualTo("Test Landlord");
        assertThat(savedLandlord.getPhone()).isEqualTo("123-456-7890");
        assertThat(savedLandlord.getUser().getRole()).isEqualTo(Role.LANDLORD);
        assertThat(savedLandlord.getUser().getPassword()).isEqualTo("TestPassword123");
    }
}
