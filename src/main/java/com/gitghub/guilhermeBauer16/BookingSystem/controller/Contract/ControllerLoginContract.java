package com.gitghub.guilhermeBauer16.BookingSystem.controller.Contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface ControllerLoginContract<L,S>{

//    L - login
//    S - signIn

    ResponseEntity login(L l) throws Exception;

    ResponseEntity signIn(S s) throws Exception;

}
