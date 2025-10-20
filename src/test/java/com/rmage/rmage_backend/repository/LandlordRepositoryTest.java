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

    @Test
    public void whenSaveLandlord_thenCanBeFoundById() {
        Landlord landlord = new Landlord();

        landlord.setEmail("test@example.com");
        landlord.setPhone("123-456-7890");
        landlord.setName("Test Landlord");

        //When
        Landlord savedLandlord = landlordRepository.save(landlord);
        Landlord foundLandlord = landlordRepository.findById(savedLandlord.getId()).orElse(null);

        //Then
        assertThat(foundLandlord).isNotNull();
        assertThat(foundLandlord.getId()).isNotNull();
        assertThat(foundLandlord.getEmail()).isEqualTo("test@example.com");
        assertThat(foundLandlord.getName()).isEqualTo("Test Landlord");
        assertThat(foundLandlord.getPhone()).isEqualTo("123-456-7890");
    }
}
