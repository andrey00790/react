package com.example.flux;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.Map.entry;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Endpoint(id = "rates")
@RequiredArgsConstructor
public class RatesMetricsEndpoint {
    private final MetricRegistry metricRegistry;


    private static final Collector<Map.Entry<String, Object>, ?, Map<String, Object>> ENTRY_MAP_COLLECTOR = toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (o, o2) -> {
                if (o instanceof List && o2 instanceof List) {
                    ((List) o).addAll((Collection) o2);
                }
                return o;
            }
    );

    @ReadOperation
    public Map<String, Object> allrates() {
        return extractRatesFromMeters();
    }

    @ReadOperation
    public Map<String, Object> rates(@Selector String arg0) {
        Map<String, Object> rates = extractMeterRates(
                metricRegistry.getMeters().entrySet().stream()
                        .filter(stringMeterEntry -> stringMeterEntry.getKey().contains(arg0))
        );

        return rates;
    }

    private Map<String, Object> extractRatesFromMeters() {
        Map<String, Object> rates = extractMeterRates(metricRegistry.getMeters().entrySet().stream());
        return rates;
    }

    private Map<String, Object> extractMeterRates(Stream<Map.Entry<String, Meter>> stream) {
        return stream
                .map(metric -> entry(metric.getKey(), (Object) metric.getValue().getOneMinuteRate()))
                .collect(ENTRY_MAP_COLLECTOR);
    }
}
