package com.eaxmple.flux;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("async.adjustment")
public class AsyncAdjustmentProperties {
    private int queueSize;
}
