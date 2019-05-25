package com.examples.flux.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
@ConfigurationProperties("profile-service.storage")
@Data
public class StorageProperties {

    @NotNull
    private String imageStorageUrl;
    @NotNull
    private String profileStorageUrl;
    @Min(1)
    private int batchImagesSize = 1;
}
