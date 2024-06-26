package com.gitghub.guilhermeBauer16.BookingSystem.controller.Contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ControllerDatabasesContract<T,I>{

    ResponseEntity<T> create(T t) throws Exception;

    ResponseEntity<Page<EntityModel<T>>> findAll(Pageable pageable) throws Exception;

    ResponseEntity<T> update(T t) throws Exception;

    ResponseEntity<T> findById(I i) throws Exception;

    ResponseEntity<?> delete(I i) throws Exception;
}
