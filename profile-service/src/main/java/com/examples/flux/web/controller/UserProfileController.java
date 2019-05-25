package com.examples.flux.web.controller;


import com.examples.flux.com.examples.flux.service.UserProfileService;
import com.examples.flux.web.dto.UserProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/profile-service", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("user-profile")
    public ResponseEntity<UserProfile> getUserProfile() {
        return ResponseEntity.ok(userProfileService.getUserProfile());
    }
}
