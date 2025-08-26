package com.rmage.rmage_backend.repository;

import com.rmage.rmage_backend.model.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface LandlordRepository extends JpaRepository<Landlord, UUID> {
}
