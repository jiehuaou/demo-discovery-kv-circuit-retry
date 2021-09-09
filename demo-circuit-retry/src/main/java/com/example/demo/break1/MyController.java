package com.example.demo.break1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    LookupService lookupService;

    @GetMapping("/circuit-id")
    public String getCircuit() {
        return lookupService.circuitOnly(); // circuit-break only
    }

    @GetMapping("/retry-circuit-id")
    public String getRetryCircuit() {
        return lookupService.retryCircuit(); // circuit-break & retry
    }

    @GetMapping("/retry-id")
    public String getRetry() {
        return lookupService.retryOnly(); // retry
    }

    @GetMapping("/fetch-404")
    public String fetch404() {
        return lookupService.lookup404(); // retry
    }

}
