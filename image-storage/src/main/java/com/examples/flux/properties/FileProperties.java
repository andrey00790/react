package com.examples.flux.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@Validated
@ConfigurationProperties("image-storage.file")
@Data
public class FileProperties {
    @Min(1)
    private int size;
}
