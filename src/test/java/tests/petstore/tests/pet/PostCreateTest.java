
package tests.petstore.tests.pet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import tests.petstore.base.BaseTest;
import tests.petstore.domains.pet.PetDomain;
import tests.petstore.requests.pet.CreatePetRequest;
import tests.petstore.requests.pet.GetPetByIdRequest;

public class PostCreateTest extends BaseTest {

    private final CreatePetRequest createPetRequest = new CreatePetRequest();
    private final GetPetByIdRequest getPetByIdRequest = new GetPetByIdRequest();

    @Test
    @DisplayName("Deve ser possível criar um pet com sucesso")
    void shouldCreatePetSuccessfully() {

        PetDomain pet = PetDomain.generateValid();

        PetDomain createdPet = createPetRequest.createAndExtract(pet);

        assertThat(createdPet.getId()).isEqualTo(pet.getId());
        assertThat(createdPet.getName()).isEqualTo(pet.getName());
        assertThat(createdPet.getStatus()).isEqualTo(pet.getStatus());
    }

}
