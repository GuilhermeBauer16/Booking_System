package com.gitghub.guilhermeBauer16.BookingSystem.services.security;

import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.AddressNotFound;
import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.ExceptionMessages;
import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.PermissionNotFound;
import com.gitghub.guilhermeBauer16.BookingSystem.mapper.Mapper;
import com.gitghub.guilhermeBauer16.BookingSystem.model.security.AddressModel;
import com.gitghub.guilhermeBauer16.BookingSystem.model.security.PermissionModel;
import com.gitghub.guilhermeBauer16.BookingSystem.model.security.UserModel;
import com.gitghub.guilhermeBauer16.BookingSystem.model.security.UserPatternsModel;
import com.gitghub.guilhermeBauer16.BookingSystem.repository.AddressRepository;
import com.gitghub.guilhermeBauer16.BookingSystem.repository.PermissionRepository;
import com.gitghub.guilhermeBauer16.BookingSystem.repository.UserPatternsRepository;
import com.gitghub.guilhermeBauer16.BookingSystem.repository.UserRepository;
import com.gitghub.guilhermeBauer16.BookingSystem.services.AddressService;
import com.gitghub.guilhermeBauer16.BookingSystem.services.contract.ServicesDatabaseContract;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.security.UserPatternsVO;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.security.UserVO;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServices implements ServicesDatabaseContract<UserVO, UUID> {

    private final Mapper<UserVO, UserModel> userModelMapper = new Mapper<>(UserVO.class, UserModel.class);
    private final Mapper<UserModel, UserVO> userVOMapper = new Mapper<>(UserModel.class, UserVO.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserPatternsRepository userPatternsRepository;



    @Override
    public UserVO create(UserVO userVO) throws Exception {

        UserPatternsModel userPatterns = userPatternsRepository.findByUsername(userVO.getUserPatternsModel().getUsername());
        if( userPatterns == null) {
            throw new UsernameNotFoundException(ExceptionMessages.USERNAME_NOT_FOUND_MESSAGE);
        }
        AddressModel address = addressRepository.findById(userVO.getAddressModel().getId())
                .orElseThrow(() -> new AddressNotFound(ExceptionMessages.ADDRESS_NOT_FOUND_MESSAGE));

        userVO.setUserPatternsModel(userPatterns);
        userVO.setAddressModel(address);
        userVO.setActive(true);

        UserModel entity = userModelMapper.parseObject(userVO);
        UserVO vo = userVOMapper.parseObject(userRepository.save(entity));
        return vo;
    }

    @Override
    public Page<EntityModel<UserVO>> findAll(Pageable pageable) throws Exception {
        return null;
    }


    @Override
    public UserVO update(UserVO userVO) throws Exception {
        return null;
    }

    @Override
    public UserVO findById(UUID id) throws Exception {
        UserModel user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("no user!"));
        UserVO vo = userVOMapper.parseObject(user);
        return vo;
    }


    @Override
    public void delete(UUID id) throws Exception {

    }

//    @Override
//    public UserPatternsVO findById(UUID uuid) throws Exception {
//
////        UserPatternsModel entity = userRepository.findById(uuid).orElseThrow(() -> new UsernameNotFoundException("HELLO"));
//        UserPatternsVO vo = userVOMapper.parseObject(entity);
//        return vo;
//    }

//    @Override
//    public void delete(UUID uuid) throws Exception {
//
//    }
}
