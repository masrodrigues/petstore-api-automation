package tests.petstore.domains.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tests.petstore.utils.RandomUtil;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDomain {

    private Long id;
    private String name;

    public static CategoryDomain generateValid() {
        return CategoryDomain.builder()
            .id(RandomUtil.randomLong())
            .name("CATEGORY_" + RandomUtil.randomString(5))
            .build();
    }
}
