package io.puppylpg.pokemon.pikachu.entity;

import lombok.Data;
import lombok.Setter;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Pikachu implements Pokemon {

    public Pikachu(String name, int height) {
        this.name = name;
        this.height = height;
    }

    private String name;

    private int height;

    @Setter
    public GirlFriend girlFriend;

    @Data
    public static class GirlFriend {
        String name;
        String interest;
    }

    @Override
    public String show() {
        String pic = "";

        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("pikachu.pic").getFile());
        InputStream inputStream = classLoader.getResourceAsStream("pikachu.pic");
//        ClassPathResource classPathResource = new ClassPathResource("pikachu.pic");
        try {
            pic = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String girl = "";
        if (girlFriend != null) {
            girl = String.format("%nI have a girl friend. Her name is %s, and she likes %s.", girlFriend.name, girlFriend.interest);
        } else {
            girl = "\nAnd I have no girlfriend...";
        }

        return String.format("Hello, I am a pikachu. %nMy name is %s, %dcm. %s %nHere is my picture:%n%s", name, height, girl, pic);
    }
}
