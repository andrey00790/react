package com.examples.flux.web.controller;


import com.examples.flux.service.ImageService;
import com.examples.flux.web.dto.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping(path = "/image-storage", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;


    @Async("asyncProcessorExecutor")
    @GetMapping("images/{size}")
    public CompletableFuture<ResponseEntity<List<Image>>> getProfile(@PathVariable int size) {
        return CompletableFuture.completedFuture(ResponseEntity.ok(imageService.getImages(size)));
    }
}
