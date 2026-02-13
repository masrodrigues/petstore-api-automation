package tests.petstore.metadata;

import tests.petstore.annotations.RequiredField;
import tests.petstore.annotations.MaxSize;

import java.lang.reflect.Field;
import java.util.*;

public final class PetMetadataExtractor {

    private PetMetadataExtractor() {}

    public static List<Field> getRequiredFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
            .filter(field -> field.isAnnotationPresent(RequiredField.class))
            .toList();
    }

    public static Map<Field, Integer> getFieldsWithMaxSize(Class<?> clazz) {

        Map<Field, Integer> result = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(MaxSize.class)) {
                int size = field.getAnnotation(MaxSize.class).value();
                result.put(field, size);
            }
        }

        return result;
    }
}