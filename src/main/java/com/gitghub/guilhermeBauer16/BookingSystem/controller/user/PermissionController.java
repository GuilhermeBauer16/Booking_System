package com.gitghub.guilhermeBauer16.BookingSystem.controller.user;

import com.gitghub.guilhermeBauer16.BookingSystem.services.security.PermissionServices;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.security.PermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionServices permissionServices;


    @PostMapping
    @Transactional
    public ResponseEntity<PermissionVO> create(@RequestBody PermissionVO permissionVO) throws Exception {

        PermissionVO createdPermission = permissionServices.create(permissionVO);
        return new ResponseEntity<>(createdPermission, HttpStatus.CREATED);
    }

    @PostMapping("/search")
    @Transactional
    public ResponseEntity<PermissionVO> findByDescription(@RequestBody PermissionVO permissionVO) throws Exception {
        PermissionVO permission = permissionServices.findByDescription(permissionVO);
        return ResponseEntity.ok(permission);
    }
}
