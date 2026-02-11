package tests.petstore.requests.pet;

import io.restassured.response.Response;
import tests.petstore.base.BaseRequest;

public class PingRequest extends BaseRequest {

    public Response ping() {
        return get("/pet/findByStatus?status=available");
    }
}