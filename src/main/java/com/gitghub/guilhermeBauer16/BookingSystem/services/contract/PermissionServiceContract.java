package com.gitghub.guilhermeBauer16.BookingSystem.services.contract;

public interface PermissionServiceContract <T>{

    public T create(T t) throws Exception ;

    public T findByDescription(T T) throws Exception ;
}
