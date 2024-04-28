package com.gitghub.guilhermeBauer16.BookingSystem.services;

import com.gitghub.guilhermeBauer16.BookingSystem.controller.BookingController;
import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.BookingNotFound;
import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.NotAvailableCapacityFound;
import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.ServiceNotFound;
import com.gitghub.guilhermeBauer16.BookingSystem.mapper.Mapper;
import com.gitghub.guilhermeBauer16.BookingSystem.model.BookingModel;
import com.gitghub.guilhermeBauer16.BookingSystem.model.ServicesModel;
import com.gitghub.guilhermeBauer16.BookingSystem.repository.BookingRepository;
import com.gitghub.guilhermeBauer16.BookingSystem.repository.ServiceRepository;
import com.gitghub.guilhermeBauer16.BookingSystem.services.contract.ServicesDatabaseContract;
import com.gitghub.guilhermeBauer16.BookingSystem.util.CheckIfNotNull;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.BookingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookingServices implements ServicesDatabaseContract<BookingVO,UUID> {

    private static final String SERVICE_NOT_FOUND_MESSAGE = "No service was found for ID!";
    private static final String BOOKING_NOT_FOUND_MESSAGE = "No booking was found for ID!";
    private static final String NOT_AVAILABLE_CAPACITY_FOUND_MESSAGE = "No available capacity was found for that service!";

    private final Mapper<BookingVO, BookingModel> bookingModelMapper = new Mapper<>(BookingVO.class, BookingModel.class);
    private final Mapper<BookingModel, BookingVO> boikingVOMapper = new Mapper<>(BookingModel.class, BookingVO.class);
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public BookingVO create(BookingVO bookingVO) throws Exception {

        checkIfBookingIsNull(bookingVO);
        ServicesModel servicesModel = serviceRepository.findById(bookingVO.getServicesModel().getId())
                .orElseThrow(() -> new ServiceNotFound(SERVICE_NOT_FOUND_MESSAGE));

        bookingVO.setServicesModel(servicesModel);
        checkIfEndTimeIsAfterThanStartTime(bookingVO);
        checkIfDayIsBeforeStartTime(bookingVO);
        checkIfActualDayIsBeforeEndTime(bookingVO);
        checkIfCapacityIsMoreThan0(bookingVO);
        bookingVO.setAvailable(true);
        BookingModel entity = bookingModelMapper.parseObject(bookingVO);
        BookingVO vo = boikingVOMapper.parseObject(bookingRepository.save(entity));
        vo.add(linkTo(methodOn(BookingController.class).findById(bookingVO.getId())).withSelfRel());
        return vo;
    }

    @Override
    public Page<EntityModel<BookingVO>> findAll(Pageable pageable) throws Exception {

        Page<BookingModel> allBooking = bookingRepository.findAll(pageable);
        List<BookingVO> bookingVOS = boikingVOMapper.parseObjectList(allBooking.getContent());
        List<EntityModel<BookingVO>> bookingEntities = new ArrayList<>();
        for (BookingVO bookingVO : bookingVOS) {
            if (bookingVO.getAvailable()) {
                Link link = linkTo(
                        methodOn(BookingController.class)
                                .findById(bookingVO.getId()))
                        .withSelfRel();
                EntityModel<BookingVO> bookingVOEntityModel = EntityModel.of(bookingVO, link);
                bookingEntities.add(bookingVOEntityModel);
            }
        }
        return new PageImpl<>(bookingEntities, pageable, allBooking.getTotalElements());
    }

    @Override
    public BookingVO update(BookingVO bookingVO) throws Exception {

        checkIfBookingIsNull(bookingVO);
        checkIfEndTimeIsAfterThanStartTime(bookingVO);
        checkIfDayIsBeforeStartTime(bookingVO);
        checkIfActualDayIsBeforeEndTime(bookingVO);
        checkIfCapacityIsMoreThan0(bookingVO);
        BookingModel bookingId = bookingRepository.findById(bookingVO.getId())
                .orElseThrow(() -> new BookingNotFound(BOOKING_NOT_FOUND_MESSAGE));
        checkIfAvailableIsFalse(bookingId);
        BookingModel updatedBooking = CheckIfNotNull.updateIfNotNull(bookingId, bookingVO);
        BookingVO vo = boikingVOMapper.parseObject(bookingRepository.save(updatedBooking));
        vo.add(linkTo(methodOn(BookingController.class).findById(bookingVO.getId())).withSelfRel());
        return vo;
    }

    @Override
    public BookingVO findById(UUID uuid) throws Exception {

        BookingModel bookingId = bookingRepository.findById(uuid)
                .orElseThrow(() -> new BookingNotFound(BOOKING_NOT_FOUND_MESSAGE));
        checkIfAvailableIsFalse(bookingId);
        BookingVO vo = boikingVOMapper.parseObject(bookingId);
        vo.add(linkTo(methodOn(BookingController.class).findById(uuid)).withSelfRel());
        return vo;
    }

    @Override
    public void delete(UUID uuid) {
        BookingModel bookingId = bookingRepository.findById(uuid)
                .orElseThrow(() -> new BookingNotFound(BOOKING_NOT_FOUND_MESSAGE));
        bookingId.setAvailable(false);
        bookingRepository.save(bookingId);
    }

    private void checkIfBookingIsNull(BookingVO bookingVO) {
        if (bookingVO == null) {
            throw new BookingNotFound(BOOKING_NOT_FOUND_MESSAGE);
        }
    }

    private void checkIfAvailableIsFalse(BookingModel bookingModel) {
        if (!bookingModel.getAvailable()) {
            throw new BookingNotFound(BOOKING_NOT_FOUND_MESSAGE);
        }
    }

    private void checkIfEndTimeIsAfterThanStartTime(BookingVO bookingVO) {
        if (bookingVO.getStartTime().isAfter(bookingVO.getEndTime())) {
            throw new BookingNotFound(BOOKING_NOT_FOUND_MESSAGE);

        }
    }

    private void checkIfDayIsBeforeStartTime(BookingVO bookingVO) {
        LocalDateTime now = LocalDateTime.now();
        if (bookingVO.getStartTime().isBefore(now.toLocalDate().atStartOfDay())) {
            System.out.println("checkIfDayIsBeforeStartTime");
            throw new BookingNotFound(BOOKING_NOT_FOUND_MESSAGE);

        }
    }

    private void checkIfActualDayIsBeforeEndTime(BookingVO bookingVO) {
        LocalDateTime now = LocalDateTime.now();
        if (bookingVO.getEndTime().isBefore(now.toLocalDate().atStartOfDay())) {
            System.out.println("checkIfActualDayIsBeforeEndTime");
            throw new BookingNotFound(BOOKING_NOT_FOUND_MESSAGE);

        }
    }

    private void checkIfCapacityIsMoreThan0(BookingVO bookingVO) {
        if (bookingVO.getAvailableCapacity() <= 0) {
            throw new NotAvailableCapacityFound(NOT_AVAILABLE_CAPACITY_FOUND_MESSAGE);
        }
        bookingVO.setAvailable(true);
    }
}
