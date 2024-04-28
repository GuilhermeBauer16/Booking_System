package com.gitghub.guilhermeBauer16.BookingSystem.services.security;

import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.ExceptionMessages;
import com.gitghub.guilhermeBauer16.BookingSystem.exceptions.PermissionNotFound;
import com.gitghub.guilhermeBauer16.BookingSystem.mapper.Mapper;
import com.gitghub.guilhermeBauer16.BookingSystem.model.security.PermissionModel;
import com.gitghub.guilhermeBauer16.BookingSystem.repository.PermissionRepository;
import com.gitghub.guilhermeBauer16.BookingSystem.services.contract.PermissionServiceContract;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.security.PermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServices implements PermissionServiceContract<PermissionVO> {


    private final Mapper<PermissionVO, PermissionModel> permissionModelMapper = new Mapper<>(PermissionVO.class, PermissionModel.class);
    private final Mapper<PermissionModel, PermissionVO> permissionVOMapper = new Mapper<>(PermissionModel.class, PermissionVO.class);


    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public PermissionVO create(PermissionVO permissionVO) throws Exception {
        checkIfPermissionIsNull(permissionVO);
        permissionVO.setActive(true);
        permissionVO.setDescription(permissionVO.getDescription().toUpperCase());
        PermissionModel entity = permissionModelMapper.parseObject(permissionVO);
        PermissionModel save = permissionRepository.save(entity);
        return permissionVOMapper.parseObject(save);
    }

    @Override
    public PermissionVO findByDescription(PermissionVO permissionVO) throws Exception {
        checkIfPermissionIsNull(permissionVO);
        PermissionModel entity = permissionRepository.findByDescription(permissionVO.getDescription());
        checkIfPermissionIsActive(entity);

        return permissionVOMapper.parseObject(entity);
    }


    private void checkIfPermissionIsNull(PermissionVO permissionVO) {
        if (permissionVO == null) {
            throw new PermissionNotFound(ExceptionMessages.PERMISSION_NOT_FOUND_MESSAGE);
        }
    }

    private void checkIfPermissionIsActive(PermissionModel permissionModel) {
        if (!permissionModel.isActive()) {
            throw new PermissionNotFound(ExceptionMessages
                    .PERMISSION_NOT_FOUND_MESSAGE);
        }
    }
}
