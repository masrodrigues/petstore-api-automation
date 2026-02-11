package tests.petstore.requests.pet;

import static tests.petstore.utils.ResponseUtil.FIELD_ID;

import io.restassured.response.Response;
import tests.petstore.base.BaseRequest;
import tests.petstore.domains.pet.PetDomain;
import tests.petstore.enums.HttpStatusEnum;

import java.util.Map;

public class GetPetByIdRequest extends BaseRequest {

    public static final String REQUEST_PATH = "/pet/{id}";

    public GetPetByIdRequest() {
        super();
    }

    public Response getById(final Long id) {
        return getWithPathParams(
            REQUEST_PATH,
            Map.of(FIELD_ID, id)
        );
    }

    public Response getByIdAndValidateStatus(final Long id, final int expectedStatus) {
        return getById(id)
            .then()
            .statusCode(expectedStatus)
            .extract()
            .response();
    }

    public PetDomain getByIdAndExtract(final Long id) {
        return getById(id)
            .then()
            .statusCode(HttpStatusEnum.OK.getCode())
            .extract()
            .as(PetDomain.class);
    }
}
