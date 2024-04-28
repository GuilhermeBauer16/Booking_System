package com.gitghub.guilhermeBauer16.BookingSystem.controller;

import com.gitghub.guilhermeBauer16.BookingSystem.controller.Contract.ControllerDatabasesContract;
import com.gitghub.guilhermeBauer16.BookingSystem.services.ReservationStatusServices;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.ReservationStatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reservationStatus")
public class ReservationStatusController implements ControllerDatabasesContract<ReservationStatusVO, UUID> {

    @Autowired
    private ReservationStatusServices reservationStatusServices;
    @Override
    @PostMapping
    public ResponseEntity<ReservationStatusVO> create(@RequestBody ReservationStatusVO reservationStatusVO) throws Exception {
        ReservationStatusVO createdReservationStatus = reservationStatusServices.create(reservationStatusVO);
        return new ResponseEntity<>(createdReservationStatus, HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<EntityModel<ReservationStatusVO>>> findAll(
            @PageableDefault(size = 20, page = 0, sort = "status") Pageable pageable) throws Exception {
        Page<EntityModel<ReservationStatusVO>> allReservationStatus = reservationStatusServices.findAll(pageable);
        return ResponseEntity.ok(allReservationStatus);
    }

    @Override
    @PutMapping
    public ResponseEntity<ReservationStatusVO> update(@RequestBody ReservationStatusVO reservationStatusVO) throws Exception {
        ReservationStatusVO updatedReservation = reservationStatusServices.update(reservationStatusVO);
        return ResponseEntity.ok(updatedReservation);
    }

    @Override
    @GetMapping(value = "/{uuid}")
    public ResponseEntity<ReservationStatusVO> findById(@PathVariable("uuid")UUID uuid) throws Exception {
        ReservationStatusVO reservationId = reservationStatusServices.findById(uuid);
        return ResponseEntity.ok(reservationId);
    }

    @Override
    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<?> delete(@PathVariable("uuid")UUID uuid) throws Exception {
        reservationStatusServices.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
