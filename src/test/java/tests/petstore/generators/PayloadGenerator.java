package tests.petstore.generators;

import tests.petstore.metadata.PetMetadataExtractor;
import tests.petstore.utils.RandomUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public final class PayloadGenerator {

    private PayloadGenerator() {}

    public static <T> T generateValid(Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                if (field.getType().equals(String.class)) {
                    field.set(instance, RandomUtil.randomString(10));
                }

                if (field.getType().equals(Long.class)) {
                    field.set(instance, RandomUtil.randomLong());
                }

                if (field.getType().equals(List.class)) {
                    field.set(instance, List.of("default_value"));
                }
            }

            return instance;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar payload válido", e);
        }
    }

    public static <T> T generateWithMissingRequired(Class<T> clazz) {
        T instance = generateValid(clazz);

        List<Field> requiredFields = PetMetadataExtractor.getRequiredFields(clazz);

        if (!requiredFields.isEmpty()) {
            try {
                Field fieldToNull = requiredFields.get(0);
                fieldToNull.setAccessible(true);
                fieldToNull.set(instance, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return instance;
    }

    public static <T> T generateWithExceededField(Class<T> clazz, String fieldName) {
        T instance = generateValid(clazz);

        Map<Field, Integer> fields = PetMetadataExtractor.getFieldsWithMaxSize(clazz);

        for (Map.Entry<Field, Integer> entry : fields.entrySet()) {
            if (entry.getKey().getName().equals(fieldName)) {
                try {
                    Field field = entry.getKey();
                    field.setAccessible(true);
                    field.set(instance, RandomUtil.randomString(entry.getValue() + 50));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return instance;
    }
}
