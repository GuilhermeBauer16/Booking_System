package com.gitghub.guilhermeBauer16.BookingSystem.services;

import com.gitghub.guilhermeBauer16.BookingSystem.controller.ServiceController;
import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.NotAvailableCapacityFound;
import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.ServiceNotFound;
import com.gitghub.guilhermeBauer16.BookingSystem.mapper.Mapper;
import com.gitghub.guilhermeBauer16.BookingSystem.model.ServicesModel;
import com.gitghub.guilhermeBauer16.BookingSystem.repository.ServiceRepository;
import com.gitghub.guilhermeBauer16.BookingSystem.services.contract.ServicesDatabaseContract;
import com.gitghub.guilhermeBauer16.BookingSystem.util.CheckIfNotNull;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.ServicesVO;
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
public class ServiceServices implements ServicesDatabaseContract<ServicesVO> {
    @Autowired
    private ServiceRepository serviceRepository;

    private static final String SERVICE_NOT_FOUND_MESSAGE = "No service was found for ID!";
    private static final String NOT_AVAILABLE_CAPACITY_FOUND_MESSAGE = "No available capacity was found for that service!";

    private final Mapper<ServicesVO, ServicesModel> serviceModelMapper = new Mapper<>(ServicesVO.class, ServicesModel.class);
    private final Mapper<ServicesModel, ServicesVO> serviceVOMapper = new Mapper<>(ServicesModel.class, ServicesVO.class);

    @Override
    public ServicesVO create(ServicesVO servicesVO) throws Exception {

        checkIfServicesIsNull(servicesVO);
        checkIfCapacityIsMoreThan0(servicesVO);
        ServicesModel entity = serviceModelMapper.parseObject(servicesVO);
        ServicesVO vo = serviceVOMapper.parseObject(serviceRepository.save(entity));
        vo.add(linkTo(methodOn(ServiceController.class).findById(servicesVO.getId())).withSelfRel());
        return vo;
    }

    @Override
    public Page<EntityModel<ServicesVO>> findAll(Pageable pageable) throws Exception {
        Page<ServicesModel> services = serviceRepository.findAll(pageable);
        List<ServicesVO> servicesVOS = serviceVOMapper.parseObjectList(services.getContent());
        List<EntityModel<ServicesVO>> servicesEntities = new ArrayList<>();
        for (ServicesVO servicesVO : servicesVOS) {
            if (servicesVO.getAvailable()) {
                Link link = linkTo(methodOn(ServiceController.class)
                        .findById(servicesVO.getId()))
                        .withSelfRel();
                EntityModel<ServicesVO> servicesVOEntityModel = EntityModel.of(servicesVO, link);
                servicesEntities.add(servicesVOEntityModel);
            }
        }
        return new PageImpl<>(servicesEntities, pageable, services.getTotalElements());
    }

    @Override
    public ServicesVO update(ServicesVO servicesVO) throws Exception {
        checkIfServicesIsNull(servicesVO);
        ServicesModel serviceId = serviceRepository.findById(servicesVO.getId())
                .orElseThrow(() -> new ServiceNotFound(SERVICE_NOT_FOUND_MESSAGE));
        ServicesModel updatedServiced = CheckIfNotNull.updateIfNotNull(serviceId, servicesVO);
        ServicesVO vo = serviceVOMapper.parseObject(serviceRepository.save(updatedServiced));
        vo.add(linkTo(methodOn(ServiceController.class).findById(servicesVO.getId())).withSelfRel());
        return vo;
    }

    @Override
    public ServicesVO findById(UUID uuid) throws Exception {

        ServicesModel serviceId = serviceRepository.findById(uuid)
                .orElseThrow(() -> new ServiceNotFound(SERVICE_NOT_FOUND_MESSAGE));
        checkIfAvailableIsFalse(serviceId);
        ServicesVO vo = serviceVOMapper.parseObject(serviceId);
        vo.add(linkTo(methodOn(ServiceController.class).findById(uuid)).withSelfRel());
        return vo;
    }

    @Override
    public void delete(UUID uuid) {

        ServicesModel serviceId = serviceRepository.findById(uuid)
                .orElseThrow(() -> new ServiceNotFound(SERVICE_NOT_FOUND_MESSAGE));
        serviceId.setAvailable(false);
        serviceRepository.save(serviceId);

    }

    private void checkIfCapacityIsMoreThan0(ServicesVO servicesVO) {
        if (servicesVO.getCapacity() <= 0) {
            throw new NotAvailableCapacityFound(NOT_AVAILABLE_CAPACITY_FOUND_MESSAGE);
        }
        servicesVO.setAvailable(true);
    }

    private void checkIfServicesIsNull(ServicesVO servicesVO) {
        if (servicesVO == null) {
            throw new ServiceNotFound(SERVICE_NOT_FOUND_MESSAGE);
        }
    }

    private void checkIfAvailableIsFalse(ServicesModel servicesModel) {
        if (!servicesModel.getAvailable()) {
            throw new ServiceNotFound(SERVICE_NOT_FOUND_MESSAGE);
        }
    }
}
