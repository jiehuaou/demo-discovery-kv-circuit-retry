package com.example.demo.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.DiscoveryClientServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

//@LoadBalancerClient(value = "data-service-1")

//@Configuration
public class CustomLoadBalancerConfiguration {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
//            DiscoveryClient discoveryClient,
            Environment environment,
            ConfigurableApplicationContext context) {


        ServiceInstanceListSupplier supplier = new DiscoveryClientServiceInstanceListSupplier(discoveryClient, environment);

        return ServiceInstanceListSupplier.builder()
                .withBase(supplier)
               // .withDiscoveryClient()
              //  .withBlockingHealthChecks()
                .withHealthChecks()
                .withCaching()
                .build(context);
    }
}
