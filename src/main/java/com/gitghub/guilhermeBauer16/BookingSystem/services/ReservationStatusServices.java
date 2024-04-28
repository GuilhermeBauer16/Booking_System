package com.gitghub.guilhermeBauer16.BookingSystem.services;

import com.gitghub.guilhermeBauer16.BookingSystem.controller.ReservationStatusController;
import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.ReservationStatusNotFound;
import com.gitghub.guilhermeBauer16.BookingSystem.mapper.Mapper;
import com.gitghub.guilhermeBauer16.BookingSystem.model.ReservationStatusModel;
import com.gitghub.guilhermeBauer16.BookingSystem.repository.ReservationStatusRepository;
import com.gitghub.guilhermeBauer16.BookingSystem.services.contract.ServicesDatabaseContract;
import com.gitghub.guilhermeBauer16.BookingSystem.util.CheckIfNotNull;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.ReservationStatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ReservationStatusServices implements ServicesDatabaseContract<ReservationStatusVO, UUID> {

    private static final String RESERVATION_NOT_FOUND_MESSAGE = "No reservation status was found for ID!";

    private final Mapper<ReservationStatusModel, ReservationStatusVO> reservationStatusVOMapper = new Mapper<>(ReservationStatusModel.class, ReservationStatusVO.class);
    private final Mapper<ReservationStatusVO, ReservationStatusModel> reservationStatusModelMapper = new Mapper<>(ReservationStatusVO.class, ReservationStatusModel.class);

    @Autowired
    private ReservationStatusRepository reservationStatusRepository;

    @Override
    public ReservationStatusVO create(ReservationStatusVO reservationStatusVO) throws Exception {
        checkIfReservationIsNull(reservationStatusVO);
        reservationStatusVO.setActive(true);
        reservationStatusVO.setStatus(reservationStatusVO.getStatus().toUpperCase());
        ReservationStatusModel entity = reservationStatusModelMapper.parseObject(reservationStatusVO);
        ReservationStatusVO vo = reservationStatusVOMapper.parseObject(reservationStatusRepository.save(entity));
        vo.add(linkTo(methodOn(ReservationStatusController.class).findById(reservationStatusVO.getId())).withSelfRel());
        return vo;
    }

    @Override
    public Page<EntityModel<ReservationStatusVO>> findAll(Pageable pageable) throws Exception {

        Page<ReservationStatusModel> reservations = reservationStatusRepository.findAll(pageable);
        List<ReservationStatusVO> reservationVOS = reservationStatusVOMapper.parseObjectList(reservations.getContent());
        List<EntityModel<ReservationStatusVO>> reservationsEntities = new ArrayList<>();
        for (ReservationStatusVO reservationStatusVO : reservationVOS) {
            if (reservationStatusVO.getActive()) {
                Link link = linkTo(methodOn(ReservationStatusController.class)
                        .findById(reservationStatusVO.getId()))
                        .withSelfRel();
                EntityModel<ReservationStatusVO> reservationStatusVOEntityModel = EntityModel.of(reservationStatusVO, link);
                reservationsEntities.add(reservationStatusVOEntityModel);
            }
        }
        return new PageImpl<>(reservationsEntities, pageable, reservations.getTotalElements());
    }

    @Override
    public ReservationStatusVO update(ReservationStatusVO reservationStatusVO) throws Exception {
        checkIfReservationIsNull(reservationStatusVO);
        ReservationStatusModel reservationId = reservationStatusRepository.findById(reservationStatusVO.getId())
                .orElseThrow(() -> new ReservationStatusNotFound(RESERVATION_NOT_FOUND_MESSAGE));
        ReservationStatusModel updatedReservation = CheckIfNotNull.updateIfNotNull(reservationId, reservationStatusVO);
        updatedReservation.setStatus(updatedReservation.getStatus().toUpperCase());
        ReservationStatusVO vo = reservationStatusVOMapper.parseObject(reservationStatusRepository.save(updatedReservation));
        vo.add(linkTo(methodOn(ReservationStatusController.class).findById(reservationStatusVO.getId())).withSelfRel());
        return vo;
    }

    @Override
    public ReservationStatusVO findById(UUID uuid) throws Exception {

        ReservationStatusModel entity = reservationStatusRepository.findById(uuid)
                .orElseThrow(() -> new ReservationStatusNotFound(RESERVATION_NOT_FOUND_MESSAGE));
        checkIfReservationIsActive(entity);
        ReservationStatusVO vo = reservationStatusVOMapper.parseObject(entity);
        vo.add(linkTo(methodOn(ReservationStatusController.class).findById(uuid)).withSelfRel());

        return vo;
    }

    @Override
    public void delete(UUID uuid) throws Exception {

        ReservationStatusModel reservationId = reservationStatusRepository.findById(uuid)
                .orElseThrow(() -> new ReservationStatusNotFound(RESERVATION_NOT_FOUND_MESSAGE));
        reservationId.setActive(false);
        reservationStatusRepository.save(reservationId);

    }

    private void checkIfReservationIsNull(ReservationStatusVO reservationStatusVO) {
        if (reservationStatusVO == null) {
            throw new ReservationStatusNotFound(RESERVATION_NOT_FOUND_MESSAGE);
        }
    }

    private void checkIfReservationIsActive(ReservationStatusModel reservationStatusModel) {
        if (!reservationStatusModel.getActive()) {
            throw new ReservationStatusNotFound(RESERVATION_NOT_FOUND_MESSAGE);
        }
    }
}
