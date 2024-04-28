package com.gitghub.guilhermeBauer16.BookingSystem.repository;

import com.gitghub.guilhermeBauer16.BookingSystem.model.security.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressModel, UUID> {
}
