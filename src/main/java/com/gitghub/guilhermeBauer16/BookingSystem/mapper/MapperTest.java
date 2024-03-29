package com.gitghub.guilhermeBauer16.BookingSystem.mapper;

import com.gitghub.guilhermeBauer16.BookingSystem.model.ServicesModel;
import com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.ServicesVO;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;

public class MapperTest {



    private static final ModelMapper mapper = new ModelMapper();

    static {
        // to entity for VO
        mapper.createTypeMap(ServicesModel.class, ServicesVO.class)
                .addMapping(ServicesModel::getId, ServicesVO::setId);


        // to VO for entity

        mapper.createTypeMap(ServicesVO.class , ServicesModel.class)
                .addMapping(ServicesVO::getId, ServicesModel::setId);



    }

    public static <O,D> D parseObject(O origin, Class<D> destination){
        return mapper.map(origin,destination);
    }

    public static <O,D> List<D> parseObjectList(List<O> origin, Class<D> destination){
        List<D> destinationObjects = new ArrayList<>();

        for (O o : origin){
            destinationObjects.add(mapper.map(o,destination));
        }

        return destinationObjects;
    }
}
