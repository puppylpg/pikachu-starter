package io.puppylpg.pokemon.controller;

import io.puppylpg.pokemon.pikachu.entity.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonController {

    /**
     * 用的是手动创建的pikachu，而不是auto config的
     */
    @Autowired
    Pokemon pokemon;

    @RequestMapping("show")
    public String pokemonSay() {
        return pokemon.show();
    }
}
