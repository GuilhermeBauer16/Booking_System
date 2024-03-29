package com.gitghub.guilhermeBauer16.BookingSystem.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper <O,D>{

    private static final ModelMapper mapper = new ModelMapper();
    private final Class<O> sourceClass;
    private final Class<D> destinationClass;



    public Mapper(Class<O> sourceClass, Class<D> destinationClass) {
        this.sourceClass = sourceClass;
        this.destinationClass = destinationClass;
    }

    public D parseObject(O origin){
        return mapper.map(origin,destinationClass);
    }

    public List<D> parseObjectList(List<O> origin){
        List<D> destinationObjects = new ArrayList<>();
        for(O o : origin){
            destinationObjects.add(mapper.map(o, destinationClass));
        }
        return destinationObjects;
    }
}
