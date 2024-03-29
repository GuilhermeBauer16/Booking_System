package com.gitghub.guilhermeBauer16.BookingSystem.services;

import com.gitghub.guilhermeBauer16.BookingSystem.controller.ServiceController;
import com.gitghub.guilhermeBauer16.BookingSystem.mapper.Mapper;
import com.gitghub.guilhermeBauer16.BookingSystem.mapper.MapperTest;
import com.gitghub.guilhermeBauer16.BookingSystem.model.ServicesModel;
import com.gitghub.guilhermeBauer16.BookingSystem.repository.ServiceRepository;
import com.gitghub.guilhermeBauer16.BookingSystem.services.contract.ServicesDatabaseContract;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.ServicesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.UUID;

@Service
public class ServiceServices implements ServicesDatabaseContract<ServicesVO> {
    @Autowired
    private ServiceRepository serviceRepository;


    private Mapper<ServicesVO, ServicesModel> serviceModelMapper = new Mapper<>(ServicesVO.class, ServicesModel.class);
    private Mapper<ServicesModel, ServicesVO> serviceVOMapper = new Mapper<>(ServicesModel.class, ServicesVO.class);

    @Override
    public ServicesVO create(ServicesVO servicesVO) throws Exception {

        if (servicesVO == null) {
            throw new RuntimeException();


        }
//        ServicesModel entity = serviceModelMapper.parseObject(servicesVO);
//        ServicesVO vo = serviceVOMapper.parseObject(entity);

        ServicesModel entity = MapperTest.parseObject(servicesVO, ServicesModel.class);
        ServicesVO vo = MapperTest.parseObject(serviceRepository.save(entity), ServicesVO.class);
        vo.add(linkTo(methodOn(ServiceController.class).findById(servicesVO.getId())).withSelfRel());
        return vo;
    }

    @Override
    public Page<EntityModel<ServicesVO>> findAll(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public ServicesVO update(ServicesVO servicesVO) throws Exception {
        return null;
    }

    @Override
    public ServicesVO findById(UUID uuid) throws Exception {

        ServicesModel serviceId = serviceRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException());

        ServicesVO vo = serviceVOMapper.parseObject(serviceId);
        vo.add(linkTo(methodOn(ServiceController.class).findById(uuid)).withSelfRel());
        return vo;
    }

    @Override
    public void delete(UUID uuid) throws Exception {

    }
}
