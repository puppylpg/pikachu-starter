package io.puppylpg.pokemon.pikachu;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = PikachuProperties.PIKACHU_PREFIX)
public class PikachuProperties {

    public static final String PIKACHU_PREFIX = "pokemon.pikachu";

    private String name;

    private int height;

    private Girl girlFriend;

    @Data
    public static class Girl {
        private String name;
        private String interest;
    }
}
