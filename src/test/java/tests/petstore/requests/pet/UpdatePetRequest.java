package tests.petstore.requests.pet;

import io.restassured.response.Response;
import tests.petstore.base.BaseRequest;
import tests.petstore.domains.pet.PetDomain;
import tests.petstore.enums.HttpStatusEnum;

public class UpdatePetRequest extends BaseRequest {

    public static final String REQUEST_PATH = "/pet";

    public UpdatePetRequest() {
        super();
    }

    public Response update(final PetDomain pet) {
        return put(REQUEST_PATH, pet);
    }

    public Response updateAndValidateStatus(final PetDomain pet, final HttpStatusEnum expectedStatus) {
        return update(pet)
            .then()
            .statusCode(expectedStatus.getCode())
            .extract()
            .response();
    }

    public PetDomain updateAndExtract(final PetDomain pet) {
        return update(pet)
            .then()
            .statusCode(HttpStatusEnum.OK.getCode())
            .extract()
            .as(PetDomain.class);
    }
}
