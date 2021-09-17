package com.example.demo.other;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


//@LoadBalancerClient(value = "data-service-1")

public class LoadBalancerConfiguration {
    @Bean
    public ServiceInstanceListSupplier supplier(
            ConfigurableApplicationContext context) {
        //
        return ServiceInstanceListSupplier.builder()
                .withDiscoveryClient()
//                .withHealthChecks()
//                .withCaching()
                .build(context);
    }
}
