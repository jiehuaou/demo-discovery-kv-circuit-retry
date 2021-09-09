package com.example.demo.break1;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MyCircuitBreakListener {

    @Bean
    public RegistryEventConsumer<CircuitBreaker> circuitBreakerEventConsumer() {
        return new RegistryEventConsumer<CircuitBreaker>() {

            @Override
            public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {
                entryAddedEvent.getAddedEntry().getEventPublisher()
                        .onFailureRateExceeded(event -> log.warn("circuit breaker {} ... failure rate {} ",
                                event.getCircuitBreakerName(), event.getFailureRate())
                        )
//                        .onSlowCallRateExceeded(event -> log.error("circuit breaker {} slow call rate {} on {}",
//                                event.getCircuitBreakerName(), event.getSlowCallRate(), event.getCreationTime())
//                        )
                        .onCallNotPermitted(event -> log.warn("circuit breaker {} ... call not permitted ",
                                event.getCircuitBreakerName())
                        )
//                        .onError(event -> log.error("circuit breaker {} error with duration {}s",
//                                event.getCircuitBreakerName(), event.getElapsedDuration().getSeconds())
//                        )
                        .onStateTransition(System.out::println);
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> entryRemoveEvent) {

            }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) {

            }
        };
    }
}
