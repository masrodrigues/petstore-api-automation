package tests.petstore.tests.pet;

import org.junit.jupiter.api.Test;

import tests.petstore.base.BaseTest;
import tests.petstore.enums.HttpStatusEnum;
import tests.petstore.requests.pet.PingRequest;
import tests.petstore.utils.AssertionUtil;

public class PingTest extends BaseTest {

    @Test
    void shouldPingPetstore() {
        var response = new PingRequest().ping();

        AssertionUtil.assertStatusCode(response, HttpStatusEnum.OK);
    }
}