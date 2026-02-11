package tests.petstore.base;

import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.path.json.config.JsonPathConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * Classe base para configuração dos testes de API do Petstore.
 */
public class Hooks {

    @BeforeAll
    static void beforeAll() {
        // Define a URL base para todos os testes
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @BeforeEach
    void beforeEach() {
        // 1. Reseta as configurações para garantir um estado limpo a cada teste
        RestAssured.reset();

        // 2. Reaplica as configurações globais (necessário pois o reset() limpa a config)
        RestAssured.config = RestAssured.config()
            .jsonConfig(
                JsonConfig.jsonConfig()
                    .numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)
            );

        // 3. Habilita logs apenas se a validação falhar (reduz ruído no console)
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}