package com.company.taskmanager.utils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ReflectionUtils {

    public static <T> void updateFields(T source, T target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException(
                    "Source or target cannot be null");
        }

        Class<?> clazz = source.getClass();
        Field[] fields = clazz.getDeclaredFields();

        Arrays.stream(fields).forEach(field -> {
            field.setAccessible(true);
            try {
                Object value = field.get(source);
                if (value != null) {
                    field.set(target, value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

}