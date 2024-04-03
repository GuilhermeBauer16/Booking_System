package com.gitghub.guilhermeBauer16.BookingSystem.util;

import java.lang.reflect.Field;

public class CheckIfNotNull {

    public static <T, S> T updateIfNotNull(T target, S source) {
        // Product model
        Class<?> targetClass = target.getClass();
        // ProductVO
        Class<?> sourceClass = source.getClass();
        // List<ProductVO>
        Field[] sourcesFields = sourceClass.getDeclaredFields();
        //A Field provides information about, and dynamic access to, a single field of a class or an interface.
        // The reflected field may be a class (static) field or an instance field.
        //A Field permits widening conversions to occur during a get or set access operation, but throws an IllegalArgumentException
        // if a narrowing conversion would occur.
        for (Field sourceField : sourcesFields) {
            try {
                //To change the access modifier for can access the private methods
                sourceField.setAccessible(true);
                // get the value. ex: productVO.getName();
                Object value = sourceField.get(source);

                if (value != null && !sourceField.getName().equals("serialVersionUID")) {
                    // This retrieves the corresponding field in the target object based on the name of the source field.
                    Field targetField = targetClass.getDeclaredField(sourceField.getName());
                    targetField.setAccessible(true);
                    // set the value
                    targetField.set(target, value);
                }

            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);

            }
        }

        return target;

    }



}
