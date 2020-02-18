package io.puppylpg.pokemon.pikachu;

import io.puppylpg.pokemon.pikachu.entity.Pikachu;

public class PikachuTest {

    public static void main(String... args) {
        Pikachu pikachu = new Pikachu("pika", 63);
        Pikachu.GirlFriend girlFriend = new Pikachu.GirlFriend();
        girlFriend.setName("pipi");
        girlFriend.setInterest("bibi");
        pikachu.setGirlFriend(girlFriend);

        System.out.println(pikachu.show());
    }
}
