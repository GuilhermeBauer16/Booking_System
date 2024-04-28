package com.gitghub.guilhermeBauer16.BookingSystem.services;

import com.gitghub.guilhermeBauer16.BookingSystem.Dto.LoginDto;
import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.ExceptionMessages;
import com.gitghub.guilhermeBauer16.BookingSystem.model.security.UserModel;
import com.gitghub.guilhermeBauer16.BookingSystem.model.security.UserPatternsModel;
import com.gitghub.guilhermeBauer16.BookingSystem.repository.UserPatternsRepository;
import com.gitghub.guilhermeBauer16.BookingSystem.repository.UserRepository;
import com.gitghub.guilhermeBauer16.BookingSystem.security.jwt.JwtTokenProvider;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.security.TokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserPatternsRepository userPatternsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity logIn(LoginDto loginDto) {

        String username = loginDto.username();
        String password = loginDto.password();
        UserPatternsModel userPatternsModel = userPatternsRepository.findByUsername(username);
        UserModel user = userRepository.findByUserPatternsModel(userPatternsModel);

        if (user == null) {
            throw new BadCredentialsException(ExceptionMessages.BAD_CREDENTIALS_MESSAGE);

        }

        passwordEncoder.matches(password, user.getUserPatternsModel().getPassword());
        TokenVO accessToken = jwtTokenProvider.createAccessToken(username, user.getUserPatternsModel().getRoles());
        return ResponseEntity.ok(accessToken);
    }

}
