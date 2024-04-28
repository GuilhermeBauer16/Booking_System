package com.gitghub.guilhermeBauer16.BookingSystem.controller.user;

import com.gitghub.guilhermeBauer16.BookingSystem.controller.Contract.ControllerDatabasesContract;
import com.gitghub.guilhermeBauer16.BookingSystem.services.AddressService;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.security.AddressVO;
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
@RequestMapping("/api/address")
public class AddressController implements ControllerDatabasesContract<AddressVO,UUID> {

    @Autowired
    private AddressService addressService;


    @Override
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<AddressVO> create(@RequestBody AddressVO addressVO) throws Exception {
        AddressVO createdAddress = addressService.create(addressVO);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);

    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<EntityModel<AddressVO>>> findAll(
            @PageableDefault(size = 20, page = 0, sort = "neighborhood") Pageable pageable) throws Exception {

        Page<EntityModel<AddressVO>> allAddress = addressService.findAll(pageable);
        return ResponseEntity.ok(allAddress);
    }


    @Override
    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressVO> update(@RequestBody AddressVO addressVO) throws Exception {
        AddressVO updatedAddress = addressService.update(addressVO);
        return ResponseEntity.ok(updatedAddress);
    }

    @Override
    @GetMapping(value = "/{uuid}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressVO> findById(@PathVariable(value = "uuid") UUID uuid) throws Exception {
        AddressVO addressVO = addressService.findById(uuid);
        return ResponseEntity.ok(addressVO);
    }

    @Override
    @DeleteMapping(value = "delete/{uuid}")
    public ResponseEntity<?> delete(@PathVariable(value = "uuid") UUID uuid) throws Exception {
        addressService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
