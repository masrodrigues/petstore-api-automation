package tests.petstore.requests.pet;

import static tests.petstore.utils.ResponseUtil.FIELD_ID;

import io.restassured.response.Response;
import tests.petstore.base.BaseRequest;
import tests.petstore.domains.pet.PetDomain;
import tests.petstore.enums.HttpStatusEnum;

public class CreatePetRequest extends BaseRequest {

    public static final String REQUEST_PATH = "/pet";

    public CreatePetRequest() {
        super();
    }

    public Response create(PetDomain pet) {
        return post(REQUEST_PATH, pet);
    }

    public Long createAndReturnId(PetDomain pet) {
        return create(pet)
            .then()
            .statusCode(HttpStatusEnum.OK.getCode())
            .extract()
            .jsonPath()
            .getLong(FIELD_ID);
    }
}