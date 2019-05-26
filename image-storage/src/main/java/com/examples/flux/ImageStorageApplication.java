package com.examples.flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
public class ImageStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageStorageApplication.class, args);
    }
}
