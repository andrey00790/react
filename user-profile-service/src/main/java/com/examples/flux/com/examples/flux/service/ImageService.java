package com.examples.flux.com.examples.flux.service;

import com.examples.flux.properties.StorageProperties;
import com.examples.flux.web.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Min;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final RestTemplate restTemplate;
    private final StorageProperties storageProperties;

    public List<ImageDto> getImages(@Min(1) int batchImagesSize){
        return restTemplate.getForObject(
                storageProperties.getImageStorageUrl() + "/" + batchImagesSize,
                getObjectType()
        );
    }

    public Class<List<ImageDto>> getObjectType() {
        return (Class<List<ImageDto>>) ((Class)List.class);
    }
}
