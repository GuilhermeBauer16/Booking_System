package com.gitghub.guilhermeBauer16.BookingSystem.Dto;

import com.gitghub.guilhermeBauer16.BookingSystem.model.security.PermissionModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(
        @Email
        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotNull
        PermissionModel permission) {
}
