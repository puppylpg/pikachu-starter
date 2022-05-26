package io.puppylpg.pokemon.controller;

import io.puppylpg.pokemon.pikachu.entity.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonController {

    @Autowired
    Pokemon pokemon;

    @RequestMapping("show")
    public String pokemonSay() {
        return pokemon.show();
    }
}
