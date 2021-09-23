package com.example.demo.controller;

import com.example.demo.cache.CacheData;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping("/cache")
public class CacheController {
    @Autowired
    CacheData cacheData;

    @RequestMapping("/load-id/{id}")
    public Mono<String> load(@PathVariable("id") Long id) {
        log.info("Rx client /cache/load-id ");

        return Mono.just(cacheData.getCustomer(id));
    }
}
