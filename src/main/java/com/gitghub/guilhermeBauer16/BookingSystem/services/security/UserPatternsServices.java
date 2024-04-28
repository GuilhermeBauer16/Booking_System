package com.gitghub.guilhermeBauer16.BookingSystem.services.security;

import com.gitghub.guilhermeBauer16.BookingSystem.Dto.UserDto;
import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.ExceptionMessages;
import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.PermissionNotFound;
import com.gitghub.guilhermeBauer16.BookingSystem.mapper.Mapper;
import com.gitghub.guilhermeBauer16.BookingSystem.model.security.PermissionModel;
import com.gitghub.guilhermeBauer16.BookingSystem.model.security.UserPatternsModel;
import com.gitghub.guilhermeBauer16.BookingSystem.repository.PermissionRepository;
import com.gitghub.guilhermeBauer16.BookingSystem.repository.UserPatternsRepository;
import com.gitghub.guilhermeBauer16.BookingSystem.util.CheckIfNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPatternsServices implements UserDetailsService {


    private final Mapper<UserDto, UserPatternsModel> userDtoMapper = new Mapper<>(UserDto.class, UserPatternsModel.class);
    private final Mapper<UserPatternsModel, UserDto> userDtoVOMapper = new Mapper<>(UserPatternsModel.class, UserDto.class);

    @Autowired
    private UserPatternsRepository userPatternsRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserPatternsModel user = userPatternsRepository.findByUsername(username);

        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException(ExceptionMessages.USERNAME_NOT_FOUND_MESSAGE);
        }

    }


    public UserPatternsModel create(UserDto userDto) throws Exception {

        PermissionModel description = permissionRepository.findByDescription(userDto.permission().getDescription());
        checkIfPermissionIsNull(description);
        checkIfDescriptionNull(description);
        String encode = passwordEncoder.encode(userDto.password());
        UserPatternsModel entity = CheckIfNotNull.updateIfNotNull(new UserPatternsModel(), userDto);
        entity.setPassword(encode);
        entity.setPermission(description);
        entity.setActive(true);

        return userPatternsRepository.save(entity);
    }


    public UserPatternsModel findUserByUsername(String username) throws Exception {
        UserPatternsModel user = userPatternsRepository.findByUsername(username);
        checkIfUserIsNull(user);
        checkIfUserIsActive(user);
        return user;
    }


    public void delete(String username) throws Exception {

        UserPatternsModel user = userPatternsRepository.findByUsername(username);
        checkIfUserIsNull(user);
        checkIfUserIsActive(user);
        user.setActive(false);
        userPatternsRepository.save(user);

    }

    private void checkIfUserIsNull(UserPatternsModel userPatternsModel) {
        if (userPatternsModel == null) {

            throw new UsernameNotFoundException(ExceptionMessages.USERNAME_NOT_FOUND_MESSAGE);
        }
    }

    private void checkIfUserIsActive(UserPatternsModel userPatternsModel) {
        if (!userPatternsModel.getActive()) {
            throw new UsernameNotFoundException(ExceptionMessages.USERNAME_NOT_FOUND_MESSAGE);
        }
    }

    private void checkIfPermissionIsNull(PermissionModel permission) {
        if (permission == null) {
            throw new PermissionNotFound(ExceptionMessages.PERMISSION_NOT_FOUND_MESSAGE);
        }
    }

    private void checkIfDescriptionNull(PermissionModel permission) {
        if (permission.getDescription() == null) {
            throw new PermissionNotFound(ExceptionMessages.PERMISSION_NOT_FOUND_MESSAGE);
        }
    }
}
