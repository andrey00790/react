package com.examples.flux.web.controller;


import com.examples.flux.com.examples.flux.service.UserProfileService;
import com.examples.flux.web.dto.UserProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping(path = "/user-profile-service", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Async("asyncProcessorExecutor")
    @GetMapping("user-profile")
    public CompletableFuture<ResponseEntity<UserProfile>> getUserProfile() {
        return CompletableFuture.completedFuture(ResponseEntity.ok(userProfileService.getUserProfile()));
    }
}
