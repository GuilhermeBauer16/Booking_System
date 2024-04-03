package com.gitghub.guilhermeBauer16.BookingSystem.controller;

import com.gitghub.guilhermeBauer16.BookingSystem.controller.Contract.ControllerDatabasesContract;
import com.gitghub.guilhermeBauer16.BookingSystem.services.ServiceServices;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.ServicesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/services")
public class ServiceController implements ControllerDatabasesContract<ServicesVO> {

    @Autowired
    private ServiceServices serviceServices;

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<ServicesVO> create(@RequestBody ServicesVO servicesVO) throws Exception {
        ServicesVO createdService = serviceServices.create(servicesVO);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<EntityModel<ServicesVO>>> findAll(
            @PageableDefault(size = 20, page = 0, sort = "name") Pageable pageable) throws Exception {
        Page<EntityModel<ServicesVO>> allServices = serviceServices.findAll(pageable);
        return ResponseEntity.ok(allServices);
    }

    @Override
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<ServicesVO> update(@RequestBody ServicesVO servicesVO) throws Exception {
        ServicesVO updatedService = serviceServices.update(servicesVO);
        return ResponseEntity.ok(updatedService);
    }

    @Override
    @GetMapping(value = "/{uuid}")
    public ResponseEntity<ServicesVO> findById(@PathVariable(value = "uuid") UUID uuid) throws Exception {
        ServicesVO serviceId = serviceServices.findById(uuid);
        return ResponseEntity.ok(serviceId);
    }

    @Override
    @DeleteMapping(value = "/{uuid}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "uuid") UUID uuid) throws Exception {
        serviceServices.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
