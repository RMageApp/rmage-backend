package com.rmage.rmage_backend.repository;

import com.rmage.rmage_backend.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TenantRepository extends JpaRepository<Tenant, UUID> {
}
