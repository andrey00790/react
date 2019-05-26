package com.eaxmple.flux.configuration;

import com.eaxmple.flux.properties.AsyncAdjustmentProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableScheduling
@EnableAsync
@EnableConfigurationProperties(AsyncAdjustmentProperties.class)
public class AsyncAdjustmentConfiguration {

    @Bean
    @ConditionalOnMissingBean
    RejectedExecutionHandler rejectedExecutionHandler() {
        return (r, executor) -> log.info("task failed — {}", r);
    }

    @Bean
    public ThreadPoolExecutor asyncProcessorExecutor(
            AsyncAdjustmentProperties adjustmentProperties,
            RejectedExecutionHandler rejectedExecutionHandler
    ) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                adjustmentProperties.getConcurrencyLevel(),
                adjustmentProperties.getConcurrencyLevel(),
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(adjustmentProperties.getQueueSize()),
                new BasicThreadFactory.Builder()
                        .namingPattern("asyncProcessor-%d")
                        .daemon(true)
                        .priority(Thread.MAX_PRIORITY)
                        .build()
        );

        threadPoolExecutor.setRejectedExecutionHandler(rejectedExecutionHandler);

        threadPoolExecutor.prestartAllCoreThreads();
        return threadPoolExecutor;
    }
}
