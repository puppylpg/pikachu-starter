package io.puppylpg.autoconfig.pikachu;

import io.puppylpg.pokemon.pikachu.entity.Pikachu;
import io.puppylpg.pokemon.pikachu.entity.Pokemon;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Pikachu.class)
@EnableConfigurationProperties(PikachuProperties.class)
@ConditionalOnProperty(prefix = PikachuProperties.PIKACHU_PREFIX, value = "enabled", havingValue = "true")
public class PikachuAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(Pokemon.class)
    public Pikachu pikachu(PikachuProperties properties) {
        Pikachu pikachu =  new Pikachu(properties.getName(), properties.getHeight());

        if (properties.getGirlFriend() != null) {
            Pikachu.GirlFriend girlFriend = new Pikachu.GirlFriend();
            girlFriend.setName(properties.getGirlFriend().getName());
            girlFriend.setInterest(properties.getGirlFriend().getInterest());
            pikachu.setGirlFriend(girlFriend);
        }

        return pikachu;
    }
}
