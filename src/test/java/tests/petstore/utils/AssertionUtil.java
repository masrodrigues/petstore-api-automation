package tests.petstore.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import tests.petstore.enums.HttpStatusEnum;

import org.assertj.core.api.Assertions;

public final class AssertionUtil {

    private AssertionUtil() {}

    public static void assertStatusCode(Response response, HttpStatusEnum status) {
        // Qualifica o Assertions para evitar qualquer ambiguidade de overload
        Assertions.assertThat(response.getStatusCode())
            .as("Status HTTP incorreto")
            .isEqualTo(status.getCode());
    }

    public static void assertFieldNotNull(Response response, String field) {
        Object value = response.jsonPath().get(field);
        Assertions.assertThat(value)
            .as("Campo '%s' não deveria ser nulo".formatted(field))
            .isNotNull();
    }

    public static void assertFieldEquals(Response response, String field, Object expected) {
        Object value = response.jsonPath().get(field);
        Assertions.assertThat(value)
            .as("Campo '%s' incorreto".formatted(field))
            .isEqualTo(expected);
    }
}