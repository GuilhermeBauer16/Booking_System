package com.gitghub.guilhermeBauer16.BookingSystem.repository;

import com.gitghub.guilhermeBauer16.BookingSystem.model.security.PermissionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<PermissionModel, String> {

    @Query("SELECT p FROM PermissionModel p WHERE p.description = :description")
    PermissionModel findByDescription(@Param("description") String description);

}
