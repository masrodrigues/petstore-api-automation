package tests.petstore.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.path.json.config.JsonPathConfig;

@Slf4j
public class Hooks {

    private static final String BASE_URI = "https://petstore.swagger.io/v2";
    private static final String ENV = System.getProperty("env", "QA");

    private long testStartTime;

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = BASE_URI;
    }

    @BeforeEach
    void beforeEach(TestInfo testInfo) {

        testStartTime = System.currentTimeMillis();

        RestAssured.reset();
        RestAssured.baseURI = BASE_URI;

        RestAssured.config = RestAssured.config()
            .jsonConfig(
                JsonConfig.jsonConfig()
                    .numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)
            );

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        log.info("====================================================================");
        log.info("🚀 TEST START");
        log.info("Thread : {}", Thread.currentThread().getName());
        log.info("Ambiente : {}", ENV);
        log.info("Base URI : {}", BASE_URI);
        log.info("Classe   : {}", testInfo.getTestClass().map(Class::getSimpleName).orElse(""));
        log.info("Método   : {}", testInfo.getTestMethod().map(m -> m.getName()).orElse(""));
        log.info("Pacote   : {}", testInfo.getTestClass().map(c -> c.getPackageName()).orElse(""));
        log.info("Teste    : {}", testInfo.getDisplayName());
        log.info("Tags     : {}", testInfo.getTags());
        log.info("====================================================================");
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {

        long totalTime = System.currentTimeMillis() - testStartTime;

        log.info("🏁 TEST END");
        log.info("Tempo total do teste : {}ms", totalTime);
        log.info("====================================================================");
    }
}
