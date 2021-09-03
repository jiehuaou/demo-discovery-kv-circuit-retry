package com.example.demo.break1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    LookupService lookupService;

    @GetMapping("/circuit-id")
    public String getString() {
        return lookupService.lookup(); // circuit-break
    }

    @GetMapping("/retry-id")
    public String getRetryString() {
        return lookupService.retryLookup(); // retry
    }

    @GetMapping("/fetch-404")
    public String fetch404() {
        return lookupService.lookup404(); // retry
    }

}
