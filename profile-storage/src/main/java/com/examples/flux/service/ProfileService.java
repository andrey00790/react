package com.examples.flux.service;

import com.examples.flux.web.dto.Profile;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ProfileService {

    private final ProfileGeneratorService profileGeneratorService;
    private final Counter counter;

    public ProfileService(ProfileGeneratorService profileGeneratorService,
                          MeterRegistry meterRegistry) {
        this.profileGeneratorService = profileGeneratorService;
        this.counter = meterRegistry.counter("profile-storage.rps");
    }

    public Profile getProfile(){
        try{
            return profileGeneratorService.generate();
        }finally {
            counter.increment();
        }
    }
}
