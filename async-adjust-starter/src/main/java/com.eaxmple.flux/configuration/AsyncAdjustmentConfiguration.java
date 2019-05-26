package com.eaxmple.flux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@Configuration
@EnableScheduling
@EnableConfigurationProperties(AsyncAdjustmentProperties.class)
public class AsyncAdjustmentConfiguration {
}
