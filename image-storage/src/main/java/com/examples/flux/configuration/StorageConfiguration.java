package com.examples.flux.configuration;

import com.examples.flux.properties.FileProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({FileProperties.class})
public class StorageConfiguration {
}
