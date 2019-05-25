package com.examples.flux.service;

import com.examples.flux.web.dto.Image;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ImageService {

    private final ImageGeneratorService imageGeneratorService;
    private final Counter counter;

    public ImageService(ImageGeneratorService userProfileGeneratorService,
                        MeterRegistry meterRegistry) {
        this.imageGeneratorService = userProfileGeneratorService;
        this.counter = meterRegistry.counter("image-storage.rps");
    }

    public Image getImage(){
        try{
            return imageGeneratorService.generate();
        }finally {
            counter.increment();
        }
    }
}
