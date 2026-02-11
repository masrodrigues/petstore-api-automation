
package tests.petstore.tests.pet;

import org.junit.jupiter.api.Test;
import tests.petstore.base.BaseTest;
import tests.petstore.domains.pet.PetDomain;
import tests.petstore.enums.PetStatusEnum;
import tests.petstore.requests.pet.CreatePetRequest;
import tests.petstore.requests.pet.GetPetByIdRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostCreateTest extends BaseTest {

    private final CreatePetRequest createPetRequest = new CreatePetRequest();
    private final GetPetByIdRequest getPetByIdRequest = new GetPetByIdRequest();

    @Test
    void shouldCreatePetSuccessfully() {

        // Arrange
        PetDomain pet = PetDomain.builder()
            .id(System.currentTimeMillis())
            .name("QA Dog")
            .photoUrls(List.of("https://photo.pet/1"))
            .status(PetStatusEnum.available)
            .build();

        // Act
        Long petId = createPetRequest.createAndReturnId(pet);

        // Assert
        PetDomain createdPet = getPetByIdRequest.getByIdAndExtract(petId);

        assertThat(createdPet.getId()).isEqualTo(pet.getId());
        assertThat(createdPet.getName()).isEqualTo("QA Dog");
        assertThat(createdPet.getStatus()).isEqualTo(PetStatusEnum.available);
    }
}
