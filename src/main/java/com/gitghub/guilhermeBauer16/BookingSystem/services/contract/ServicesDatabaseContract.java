package com.gitghub.guilhermeBauer16.BookingSystem.services.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import java.util.UUID;

public interface ServicesDatabaseContract<T> {

    T create(T t) throws Exception;

    Page<EntityModel<T>> findAll(Pageable pageable) throws Exception;

    T update(T t) throws Exception;

    T findById(UUID uuid) throws Exception;

    void delete(UUID uuid) throws Exception;


}
