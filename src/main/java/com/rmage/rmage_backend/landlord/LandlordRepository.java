package com.rmage.rmage_backend.landlord;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LandlordRepository extends JpaRepository<Landlord, UUID> {
}
