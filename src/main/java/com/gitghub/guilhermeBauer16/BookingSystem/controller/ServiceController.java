package com.gitghub.guilhermeBauer16.BookingSystem.controller;

import com.gitghub.guilhermeBauer16.BookingSystem.controller.Contract.ControllerDatabasesContract;
import com.gitghub.guilhermeBauer16.BookingSystem.services.ServiceServices;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.ServicesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.UUID;
@RestController
@RequestMapping("/api/services")
public class ServiceController implements ControllerDatabasesContract<ServicesVO> {

    @Autowired
    private ServiceServices  serviceServices;
    @Override
    @PostMapping
    @Transactional
    public ResponseEntity<ServicesVO> create(@RequestBody ServicesVO servicesVO) throws Exception {
        ServicesVO createdService = serviceServices.create(servicesVO);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Page<EntityModel<ServicesVO>>> findAll(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<ServicesVO> update(@RequestBody ServicesVO servicesVO) throws Exception {
        return null;
    }

    @Override
    @GetMapping(value = "/{uuid}")
    public ResponseEntity<ServicesVO> findById(@PathVariable(value= "uuid") UUID uuid) throws Exception {
        ServicesVO serviceId = serviceServices.findById(uuid);
        return ResponseEntity.ok(serviceId);
    }

    @Override
    public ResponseEntity<?> delete(UUID uuid) throws Exception {
        return null;
    }
}
