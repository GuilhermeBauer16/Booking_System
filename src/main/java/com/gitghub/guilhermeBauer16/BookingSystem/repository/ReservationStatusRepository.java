package com.gitghub.guilhermeBauer16.BookingSystem.repository;

import com.gitghub.guilhermeBauer16.BookingSystem.model.ReservationStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationStatusRepository extends JpaRepository<ReservationStatusModel, UUID> {
}
