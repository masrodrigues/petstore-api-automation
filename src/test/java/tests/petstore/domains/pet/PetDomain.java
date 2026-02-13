package tests.petstore.domains.pet;

import lombok.*;
import tests.petstore.annotations.RequiredField;
import tests.petstore.enums.PetStatusEnum;
import tests.petstore.utils.RandomUtil;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetDomain {

    @RequiredField
    private Long id;

    private CategoryDomain category;

    @RequiredField
    private String name;

    @RequiredField
    private List<String> photoUrls;

    private List<TagDomain> tags;

    private PetStatusEnum status;

    // ============================================================
    // FACTORY METHODS
    // ============================================================

    /**
     * Gera payload mínimo válido (recomendado para testes positivos)
     * Evita 500 do Petstore público.
     */
    public static PetDomain generateMinimumValid() {

        return PetDomain.builder()
            .id(RandomUtil.randomLong())
            .name("PET_" + RandomUtil.randomString(8))
            .photoUrls(List.of(RandomUtil.randomPhotoUrl()))
            .status(PetStatusEnum.available)
            .build();
    }

    /**
     * Gera payload completo válido.
     */
    public static PetDomain generateValid() {

        return PetDomain.builder()
            .id(RandomUtil.randomLong())
            .category(CategoryDomain.generateValid())
            .name("PET_" + RandomUtil.randomString(6))
            .photoUrls(List.of(RandomUtil.randomPhotoUrl()))
            .tags(List.of(TagDomain.generateValid()))
            .status(RandomUtil.randomFromEnum(PetStatusEnum.values()))
            .build();
    }

    // ============================================================
    // NEGATIVE SCENARIOS
    // ============================================================

    public static PetDomain generateWithMissingName() {
        PetDomain pet = generateMinimumValid();
        pet.setName(null);
        return pet;
    }

    public static PetDomain generateWithMissingPhotoUrls() {
        PetDomain pet = generateMinimumValid();
        pet.setPhotoUrls(null);
        return pet;
    }

    public static PetDomain generateWithExceededNameSize() {
        PetDomain pet = generateMinimumValid();
        pet.setName(RandomUtil.randomString(300));
        return pet;
    }

    public static PetDomain generateWithNullStatus() {
        PetDomain pet = generateMinimumValid();
        pet.setStatus(null);
        return pet;
    }
}
