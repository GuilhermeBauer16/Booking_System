package com.gitghub.guilhermeBauer16.BookingSystem.controller;

import com.gitghub.guilhermeBauer16.BookingSystem.controller.Contract.ControllerDatabasesContract;
import com.gitghub.guilhermeBauer16.BookingSystem.services.BookingServices;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.BookingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingController implements ControllerDatabasesContract<BookingVO,UUID> {

    @Autowired
    private BookingServices bookingServices;

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingVO> create(@RequestBody BookingVO bookingVO) throws Exception {

        BookingVO createdBooking = bookingServices.create(bookingVO);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<EntityModel<BookingVO>>> findAll(
            @PageableDefault(size = 20, page = 0, sort = "availableCapacity") Pageable pageable) throws Exception {

        Page<EntityModel<BookingVO>> allBookings = bookingServices.findAll(pageable);
        return ResponseEntity.ok(allBookings);
    }

    @Override
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingVO> update(@RequestBody BookingVO bookingVO) throws Exception {
        BookingVO updatedBooking = bookingServices.update(bookingVO);
        return ResponseEntity.ok(updatedBooking);
    }

    @Override
    @GetMapping(value = "/{uuid}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingVO> findById(@PathVariable(value = "uuid") UUID uuid) throws Exception {
        BookingVO bookingId = bookingServices.findById(uuid);
        return ResponseEntity.ok(bookingId);
    }

    @Override
    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<?> delete(@PathVariable(value = "uuid") UUID uuid) throws Exception {
        bookingServices.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
