package com.example.demo.config;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;



public class LoadBalancerConfiguration {
    @Bean
    public ServiceInstanceListSupplier supplier(
            ConfigurableApplicationContext context) {
        //
        return ServiceInstanceListSupplier.builder()
                .withDiscoveryClient()
                .withHealthChecks()
                .withCaching()
                .build(context);
    }
}
