package tests.petstore.base;

import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.path.json.config.JsonPathConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * Classe base para todos os testes.
 * Centraliza configurações globais do RestAssured.
 */
public abstract class BaseTest extends Hooks{

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @BeforeEach
    void beforeEach() {

        RestAssured.config = RestAssured.config()
            .jsonConfig(
                JsonConfig.jsonConfig()
                    .numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)
            );

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}