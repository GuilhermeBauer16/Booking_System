package com.gitghub.guilhermeBauer16.BookingSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AddressNotFound extends RuntimeException {

    public AddressNotFound(String message) {
        super(message);
    }
}
