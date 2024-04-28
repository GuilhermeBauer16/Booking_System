package com.gitghub.guilhermeBauer16.BookingSystem.controller.user;

import com.gitghub.guilhermeBauer16.BookingSystem.controller.Contract.ControllerDatabasesContract;
import com.gitghub.guilhermeBauer16.BookingSystem.services.security.UserServices;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.security.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/user")
public class UserController implements ControllerDatabasesContract<UserVO, UUID> {

    @Autowired
    private UserServices userServices;

    @Override
    @PostMapping("/create")
    public ResponseEntity<UserVO> create(@RequestBody UserVO userVO) throws Exception {
        UserVO createdUser = userServices.create(userVO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Page<EntityModel<UserVO>>> findAll(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<UserVO> update(UserVO userVO) throws Exception {
        return null;
    }

    @Override
    @GetMapping("/{uuid}")
    public ResponseEntity<UserVO> findById(@PathVariable(value = "uuid")UUID uuid) throws Exception {
        UserVO user = userServices.findById(uuid);

        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<?> delete(UUID uuid) throws Exception {
        return null;
    }
}
