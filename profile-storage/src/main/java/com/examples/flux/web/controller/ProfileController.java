package com.examples.flux.web.controller;

import com.examples.flux.web.dto.Profile;
import com.examples.flux.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/profile-storage", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("profile")
    public ResponseEntity<Profile> getProfile(){
        return ResponseEntity.ok(profileService.getProfile());
    }
}
