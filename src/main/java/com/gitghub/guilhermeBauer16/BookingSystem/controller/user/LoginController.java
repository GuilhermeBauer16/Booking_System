package com.gitghub.guilhermeBauer16.BookingSystem.controller.user;

import com.gitghub.guilhermeBauer16.BookingSystem.Dto.LoginDto;
import com.gitghub.guilhermeBauer16.BookingSystem.Dto.UserDto;
import com.gitghub.guilhermeBauer16.BookingSystem.controller.Contract.ControllerLoginContract;
import com.gitghub.guilhermeBauer16.BookingSystem.model.security.UserPatternsModel;
import com.gitghub.guilhermeBauer16.BookingSystem.services.AuthServices;
import com.gitghub.guilhermeBauer16.BookingSystem.services.security.UserPatternsServices;
import com.gitghub.guilhermeBauer16.BookingSystem.services.security.UserServices;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.security.UserPatternsVO;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.security.UserVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController implements ControllerLoginContract<LoginDto,UserVO> {


    @Autowired
    private UserServices userServices;

    @Autowired
    private AuthServices authServices;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto){
        ResponseEntity token = authServices.logIn(loginDto);
        return token;

    }


    @PostMapping("/signIn")
    @Transactional
    public ResponseEntity signIn(@RequestBody UserVO userVO) throws Exception {

        UserVO createdUser = userServices.create(userVO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
