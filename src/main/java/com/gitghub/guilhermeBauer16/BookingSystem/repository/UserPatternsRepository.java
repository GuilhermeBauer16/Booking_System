package com.gitghub.guilhermeBauer16.BookingSystem.repository;

import com.gitghub.guilhermeBauer16.BookingSystem.model.security.UserPatternsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserPatternsRepository extends JpaRepository<UserPatternsModel, String> {

    @Query("SELECT u FROM UserPatternsModel u WHERE u.username =:username")
    UserPatternsModel findByUsername(@Param("username") String username);
}
