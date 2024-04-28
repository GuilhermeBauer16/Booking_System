package com.gitghub.guilhermeBauer16.BookingSystem.controller;

import com.gitghub.guilhermeBauer16.BookingSystem.Dto.UserDto;
import com.gitghub.guilhermeBauer16.BookingSystem.model.security.UserPatternsModel;
import com.gitghub.guilhermeBauer16.BookingSystem.services.security.UserPatternsServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/userPatterns")
public class UserPatternsController {

    @Autowired
    private UserPatternsServices userPatternsServices;
    @PostMapping("/register")
    public ResponseEntity<UserPatternsModel> create(@RequestBody @Valid UserDto userDto) throws Exception {

        UserPatternsModel createdUser = userPatternsServices.create(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }
}
