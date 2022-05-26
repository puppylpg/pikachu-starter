package io.puppylpg.pokemon;

import io.puppylpg.pokemon.pikachu.entity.Pikachu;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author puppylpg on 2022/05/25
 */
@Configuration
public class Config {

    @Bean
    public Pikachu newPikachu() {
        Pikachu pikachu = new Pikachu("manual", 1);
        Pikachu.GirlFriend girlFriend = new Pikachu.GirlFriend();
        girlFriend.setName("self");
        girlFriend.setInterest("lying");
        pikachu.setGirlFriend(girlFriend);
        return pikachu;
    }
}
