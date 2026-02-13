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
public class TagDomain {

    private Long id;
    private String name;

    public static TagDomain generateValid() {
        return TagDomain.builder()
            .id(RandomUtil.randomLong())
            .name("TAG_" + RandomUtil.randomString(5))
            .build();
    }
}
