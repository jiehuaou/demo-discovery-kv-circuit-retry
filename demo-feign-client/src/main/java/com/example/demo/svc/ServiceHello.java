package com.example.demo.svc;

import com.example.demo.remote.LogicHello;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class ServiceHello {

    final static String SAMPLE_CIRCUIT = "sample1";

    @Autowired
    LogicHello logic;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @PostConstruct
    public void postConstruct() {
        circuitBreakerRegistry
                .circuitBreaker(SAMPLE_CIRCUIT)
                .getEventPublisher()
                .onStateTransition(e->{
                    System.out.println(e.toString());
                });

    }

    @CircuitBreaker(name = SAMPLE_CIRCUIT, fallbackMethod = "circuitLookupDefault")
    public String hello(){
        log.info("feign-client start");
        final String msg = logic.hello();
        log.info("feign-client end with ...... {}", msg);

        return msg;
    }

    public String circuitLookupDefault(Exception e){
        System.out.println("call ----> CircuitBreaker fallbackMethod");
        return "CircuitBreaker fallback for service down";
    }
}
