package com.examples.flux.com.examples.flux.service;

import com.examples.flux.properties.StorageProperties;
import com.examples.flux.web.dto.ImageDto;
import com.examples.flux.web.dto.ProfileDto;
import com.examples.flux.web.dto.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final ImageService imageService;
    private final ProfileService profileService;
    private final StorageProperties storageProperties;

    public UserProfile getUserProfile() {
        ProfileDto profileDto = profileService.getProfile();
        List<ImageDto> imageDtos = imageService.getImages(storageProperties.getBatchImagesSize());

        return UserProfile.builder()
                .fio(profileDto.getFio())
                .job(profileDto.getJob())
                .quote(profileDto.getQuote())
                .imageDtos(imageDtos)
                .build();
    }
}
