package com.example.demo.svc;

import com.example.demo.logic.LogicHello;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServiceHello {
    @Autowired
    LogicHello logic;

    @CircuitBreaker(name = "example1", fallbackMethod = "circuitLookupDefault")
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
