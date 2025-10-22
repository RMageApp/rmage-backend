package com.rmage.rmage_backend.repository;

import com.rmage.rmage_backend.model.Role;
import com.rmage.rmage_backend.model.Tenant;
import com.rmage.rmage_backend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TenantRepositoryTest {

    @Autowired
    private TenantRepository tenantRepository;

    @Test
    public void whenSaveTenant_thenCanBeFoundById() {
        Tenant tenant = new Tenant();

        tenant.setEmail("test.tenant@example.com");
        tenant.setPhone("0987654321");
        tenant.setName("Test Tenant");

        //When
        Tenant savedTenant = tenantRepository.save(tenant);
        Tenant foundTenant = tenantRepository.findById(savedTenant.getId()).orElse(null);

        //Then
        assertThat(foundTenant).isNotNull();
        assertThat(foundTenant.getId()).isNotNull();
        assertThat(foundTenant.getEmail()).isEqualTo("test.tenant@example.com");
        assertThat(foundTenant.getName()).isEqualTo("Test Tenant");
        assertThat(foundTenant.getPhone()).isEqualTo("0987654321");
    }
}
