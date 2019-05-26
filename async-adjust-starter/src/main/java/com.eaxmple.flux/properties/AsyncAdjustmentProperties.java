package com.eaxmple.flux.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("async.adjustment")
public class AsyncAdjustmentProperties {
    private int queueSize = 100;
    private int concurrencyLevel = Runtime.getRuntime().availableProcessors();
}
