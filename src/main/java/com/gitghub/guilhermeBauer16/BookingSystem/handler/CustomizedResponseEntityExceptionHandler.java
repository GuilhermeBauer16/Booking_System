package com.gitghub.guilhermeBauer16.BookingSystem.handler;

import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {
    @ExceptionHandler({ServiceNotFound.class,
            BookingNotFound.class,
            NotAvailableCapacityFound.class,
            ReservationStatusNotFound.class,
            PermissionNotFound.class,
            AddressNotFound.class})
    public final ResponseEntity<ExceptionResponse> handlerNotFoundException(
            Exception ex,
            WebRequest webRequest
    ) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getMessage(),
                webRequest.getDescription(false),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

    }
//
//    @ExceptionHandler(InvalidJwtAuthenticationException.class)
//    public final ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationExceptions(
//            Exception ex,
//            WebRequest request
//    ) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse(
//                ex.getMessage(),
//                request.getDescription(false),
//                new Date()
//        );
//
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
//
//    }
}
