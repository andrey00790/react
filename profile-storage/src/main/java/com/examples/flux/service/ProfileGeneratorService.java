package com.examples.flux.service;

import com.examples.flux.web.dto.Profile;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class ProfileGeneratorService {

    private final Faker faker;

    public Profile generate() {
        return Profile.builder()
                .fio(faker.name().name())
                .job(faker.job().title())
                .quote(faker.yoda().quote())
                .build();
    }

    private static String generateRandomBase64Token(int byteLength) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[byteLength];
        secureRandom.nextBytes(token);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(token);
    }

}
