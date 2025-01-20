package com.hanghe.common.exception;

import jakarta.persistence.Entity;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ErrorCodeRegistry {

    private static final Map<String, String> errorCodes = new HashMap<>();

    static {
        Reflections reflections = new Reflections("com.hanghe");
        Set<Class<?>> entities = reflections.getTypesAnnotatedWith(Entity.class);

        for (Class<?> entity : entities) {
            String entityName = entity.getSimpleName().toUpperCase();
            errorCodes.put(entityName + "_NOT_FOUND", entityName + " not found");
        }
    }

    public static String getErrorMessage(String errorCode) {
        return errorCodes.getOrDefault(errorCode, "Unknown error");
    }
}
