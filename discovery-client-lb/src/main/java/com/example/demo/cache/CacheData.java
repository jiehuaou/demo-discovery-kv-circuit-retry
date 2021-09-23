package com.example.demo.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * test Caffeine cache
 */

@Slf4j
@Service
@CacheConfig(cacheNames = {"fake-service-cache"})
public class CacheData implements CustomerService{

    @Override
    @Cacheable
    public String getCustomer(Long customerID) {
        long uuid = System.currentTimeMillis();
        log.info("get raw customer for id {} and uuid {} ",customerID, uuid);
        return  String.format("Customer id=%d, uuid=%d", customerID, uuid);
    }

}
