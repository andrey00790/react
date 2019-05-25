package com.examples.flux.configuration;

import com.examples.flux.properties.StorageProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({StorageProperties.class})
public class ProfileServiceConfiguration {
}
