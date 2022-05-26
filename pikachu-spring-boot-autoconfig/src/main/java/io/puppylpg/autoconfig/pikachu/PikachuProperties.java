package io.puppylpg.autoconfig.pikachu;

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
        /**
         * 这个是女朋友的名字啦（属性配置的时候可以看到注释）
         */
        private String name;
        private String interest;
    }
}
