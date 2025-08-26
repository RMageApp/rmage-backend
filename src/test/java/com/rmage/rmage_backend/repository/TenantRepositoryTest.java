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
        User user = new User();
        Tenant tenant = new Tenant();

        user.setEmail("test.tenant@example.com");
        user.setPassword("TestPassword123");
        user.setRole(Role.TENANT);

        tenant.setUser(user);
        tenant.setPhone("0987654321");
        tenant.setName("Test Tenant");

        //When
        Tenant savedTenant = tenantRepository.save(tenant);

        //Then
        assertThat(savedTenant).isNotNull();
        assertThat(savedTenant.getId()).isNotNull();
        assertThat(savedTenant.getUser().getEmail()).isEqualTo("test.tenant@example.com");
        assertThat(savedTenant.getName()).isEqualTo("Test Tenant");
        assertThat(savedTenant.getPhone()).isEqualTo("0987654321");
        assertThat(savedTenant.getUser().getRole()).isEqualTo(Role.TENANT);
        assertThat(savedTenant.getUser().getPassword()).isEqualTo("TestPassword123");
    }
}
