package io.puppylpg.pokemon;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ***************************
 * APPLICATION FAILED TO START
 * ***************************
 *
 * Bean method 'getPikachu' in 'PikachuAutoConfig' not loaded because auto-configuration 'PikachuAutoConfig' was excluded
 *
 * 可以使用class，或者class名称（如果class不存在）
 */
//@SpringBootApplication
//@SpringBootApplication(exclude = PikachuAutoConfig.class)
@SpringBootApplication(excludeName = "io.puppylpg.pokemon.pikachu.PikachuAutoConfig")
public class PokemonForbidGo {
    public static void main(String[] args) {
        SpringApplication.run(PokemonForbidGo.class, args);
    }
}
