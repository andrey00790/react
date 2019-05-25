package com.examples.flux.service;

import com.examples.flux.properties.FileProperties;
import com.examples.flux.web.dto.Image;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class ImageGeneratorService {

    private final FileProperties fileProperties;

    public Image generate() {
        return Image.builder()
                .value(generateRandomBase64Token(fileProperties.getSize()))
                .build();
    }

    private static String generateRandomBase64Token(int byteLength) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[byteLength];
        secureRandom.nextBytes(token);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(token);
    }

}
