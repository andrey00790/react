package com.examples.flux.com.examples.flux.service;

import com.examples.flux.properties.StorageProperties;
import com.examples.flux.web.dto.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final RestTemplate restTemplate;
    private final StorageProperties storageProperties;

    public ProfileDto getProfile(){
        return restTemplate.getForObject(
                storageProperties.getProfileStorageUrl(),
                ProfileDto.class
        );
    }
}
