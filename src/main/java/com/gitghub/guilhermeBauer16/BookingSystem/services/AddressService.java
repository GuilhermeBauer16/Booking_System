package com.gitghub.guilhermeBauer16.BookingSystem.services;

import com.gitghub.guilhermeBauer16.BookingSystem.controller.user.AddressController;
import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.AddressNotFound;
import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.ExceptionMessages;
import com.gitghub.guilhermeBauer16.BookingSystem.mapper.Mapper;
import com.gitghub.guilhermeBauer16.BookingSystem.model.security.AddressModel;
import com.gitghub.guilhermeBauer16.BookingSystem.repository.AddressRepository;
import com.gitghub.guilhermeBauer16.BookingSystem.services.contract.ServicesDatabaseContract;
import com.gitghub.guilhermeBauer16.BookingSystem.util.CheckIfNotNull;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.security.AddressVO;
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
public class AddressService implements ServicesDatabaseContract<AddressVO, UUID> {

    private final Mapper<AddressVO, AddressModel> addressModelMapper = new Mapper<>(AddressVO.class, AddressModel.class);
    private final Mapper<AddressModel, AddressVO> addressVOMapper = new Mapper<>(AddressModel.class, AddressVO.class);

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public AddressVO create(AddressVO addressVO) throws Exception {

        checkIfAddressIsNull(addressVO);
        addressVO.setActive(true);
        AddressModel entity = addressModelMapper.parseObject(addressVO);
        return addressVOMapper.parseObject(addressRepository.save(entity));

    }

    @Override
    public Page<EntityModel<AddressVO>> findAll(Pageable pageable) throws Exception {

        Page<AddressModel> allAddress = addressRepository.findAll(pageable);
        List<AddressVO> addressVOS = addressVOMapper.parseObjectList(allAddress.getContent());
        List<EntityModel<AddressVO>> addressEntities = new ArrayList<>();
        for (AddressVO addressVO : addressVOS) {
            if (addressVO.getActive()) {
                Link link = linkTo(
                        methodOn(AddressController.class)
                                .findById(addressVO.getId()))
                        .withSelfRel();
                EntityModel<AddressVO> bookingVOEntityModel = EntityModel.of(addressVO, link);
                addressEntities.add(bookingVOEntityModel);
            }
        }
        return new PageImpl<>(addressEntities, pageable, allAddress.getTotalElements());
    }

    @Override
    public AddressVO update(AddressVO addressVO) throws Exception {

        checkIfAddressIsNull(addressVO);
        AddressModel addressId = addressRepository.findById(addressVO.getId())
                .orElseThrow(() -> new AddressNotFound(ExceptionMessages.ADDRESS_NOT_FOUND_MESSAGE));
        checkIfAddressIsAvailable(addressId);
        AddressModel entity = CheckIfNotNull.updateIfNotNull(addressId, addressVO);
        return addressVOMapper.parseObject(addressRepository.save(entity));
    }

    @Override
    public AddressVO findById(UUID uuid) throws Exception {

        AddressModel entity = addressRepository.findById(uuid)
                .orElseThrow(() -> new AddressNotFound(ExceptionMessages.ADDRESS_NOT_FOUND_MESSAGE));

        checkIfAddressIsAvailable(entity);

        return addressVOMapper.parseObject(entity);
    }

    @Override
    public void delete(UUID uuid) throws Exception {

        AddressModel addressId = addressRepository.findById(uuid)
                .orElseThrow(() -> new AddressNotFound(ExceptionMessages.ADDRESS_NOT_FOUND_MESSAGE));
        checkIfAddressIsAvailable(addressId);
        addressId.setActive(false);
        addressRepository.save(addressId);

    }


    private void checkIfAddressIsNull(AddressVO addressVO) {
        if (addressVO == null) {
            throw new AddressNotFound(ExceptionMessages.ADDRESS_NOT_FOUND_MESSAGE);
        }
    }

    private void checkIfAddressIsAvailable(AddressModel addressModel) {
        if (!addressModel.getActive()) {
            throw new AddressNotFound(ExceptionMessages.ADDRESS_NOT_FOUND_MESSAGE);
        }
    }
}
