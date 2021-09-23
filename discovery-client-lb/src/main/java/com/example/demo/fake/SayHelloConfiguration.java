package com.example.demo.fake;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;


public class SayHelloConfiguration {
//    ReactiveLoadBalancer.Factory<ServiceInstance> serviceInstanceFactory;
    @Bean
    ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new DemoServiceInstanceListSuppler("data-service-1");
    }

}
