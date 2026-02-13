package tests.petstore.base;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CustomLogFilter implements Filter {

    @Override
    public Response filter(
        FilterableRequestSpecification requestSpec,
        FilterableResponseSpecification responseSpec,
        FilterContext context) {

        boolean logEnabled = Boolean.parseBoolean(
            System.getProperty("custom.log.enabled", "true")
        );

        if (!logEnabled) {
            return context.next(requestSpec, responseSpec);
        }

        String method = requestSpec.getMethod();
        String uri = requestSpec.getURI().replace(requestSpec.getBaseUri(), "");

        log.info("📤 REQUEST");
        log.info("Http Method : {}", method);
        log.info("Endpoint    : {}", uri);

        long start = System.currentTimeMillis();

        Response response = context.next(requestSpec, responseSpec);

        long duration = System.currentTimeMillis() - start;

        String result = response.getStatusCode() >= 200 && response.getStatusCode() < 300
            ? "SUCCESS"
            : "ERROR";

        log.info("📥 RESPONSE [{}]", result);
        log.info("Status Code  : {}", response.getStatusCode());
        log.info("Response Time: {}ms", duration);
        log.info("Content-Type : {}", response.getContentType());

        return response;
    }
}

