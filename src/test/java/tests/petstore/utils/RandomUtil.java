package tests.petstore.utils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtil {

    private RandomUtil() {}

    public static Long randomLong() {
        return ThreadLocalRandom.current().nextLong(1, 1_000_000);
    }

    public static String randomString(int size) {
        return UUID.randomUUID()
            .toString()
            .replace("-", "")
            .substring(0, size);
    }

    public static <T> T randomFromEnum(T[] values) {
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }

    /** Retorna uma URL única (boa para o generateMinimumValid) */
    public static String randomPhotoUrl() {
        return "https://petstore.swagger.io/images/" + randomLong() + ".jpg";
    }

    /** Retorna lista com 1 URL (mantém compatibilidade com o que você já tinha) */
    public static List<String> randomPhotoUrls() {
        return List.of(randomPhotoUrl());
    }
}
