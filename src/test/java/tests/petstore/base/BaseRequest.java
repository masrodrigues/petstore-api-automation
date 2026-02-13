package tests.petstore.base;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

/**
 * Classe abstrata que encapsula as chamadas HTTP do RestAssured.
 * Serve como base para as classes de API (Page Objects de API).
 */
public abstract class BaseRequest {

    protected final RequestSpecification spec;

    protected BaseRequest() {
        this.spec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .addFilter(new CustomLogFilter())
            .build();
    }

    // ==================================================================================
    // GET METHODS
    // ==================================================================================

    protected Response get(String path) {
        return given()
            .spec(spec)
            .when()
            .get(path);
    }

    protected Response getWithPathParams(String path, Map<String, Object> pathParams) {
        return given()
            .spec(spec)
            .pathParams(pathParams)
            .when()
            .get(path);
    }

    protected Response getWithQueryParams(String path, Map<String, Object> queryParams) {
        return given()
            .spec(spec)
            .queryParams(queryParams)
            .when()
            .get(path);
    }

    // ==================================================================================
    // POST METHODS
    // ==================================================================================

    protected Response post(String path, Object payload) {
        return given()
            .spec(spec)
            .body(payload)
            .when()
            .post(path);
    }

    // ==================================================================================
    // PUT METHODS
    // ==================================================================================

    protected Response put(String path, Object payload) {
        return given()
            .spec(spec)
            .body(payload)
            .when()
            .put(path);
    }

    protected Response putWithPathParams(String path, Map<String, Object> pathParams, Object payload) {
        return given()
            .spec(spec)
            .pathParams(pathParams)
            .body(payload)
            .when()
            .put(path);
    }

    // ==================================================================================
    // DELETE METHODS
    // ==================================================================================

    protected Response deleteWithPathParams(String path, Map<String, Object> pathParams) {
        return given()
            .spec(spec)
            .pathParams(pathParams)
            .when()
            .delete(path);
    }
}