package com.gitghub.guilhermeBauer16.BookingSystem.repository;

import com.gitghub.guilhermeBauer16.BookingSystem.model.security.UserModel;
import com.gitghub.guilhermeBauer16.BookingSystem.model.security.UserPatternsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existsByUserPatternsModel(UserPatternsModel userPatternsModel);

    @Query("SELECT u FROM UserModel u WHERE u.userPatternsModel =:userPatternsModel")
    UserModel findByUserPatternsModel(@Param("userPatternsModel")UserPatternsModel userPatternsModel);

}
