package com.examples.flux.configuration;

import com.github.javafaker.Faker;
import com.github.javafaker.RickAndMorty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FakeDataConfiguration {

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
