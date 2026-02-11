package tests.petstore.domains.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tests.petstore.enums.PetStatusEnum;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetDomain {

    private Long id;
    private CategoryDomain category;
    private String name;
    private List<String> photoUrls;
    private List<TagDomain> tags;
    private PetStatusEnum status;
}