package com.gitghub.guilhermeBauer16.BookingSystem.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @Email
        @NotBlank
        String username,
        @NotBlank
        String password
        ) {
}
