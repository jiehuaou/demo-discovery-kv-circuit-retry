package com.example.demo.fake;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.reactive.ReactiveLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class DemoServiceInstanceListSuppler implements ServiceInstanceListSupplier {

    private final String serviceId;
//    ReactiveLoadBalancer.Factory<ServiceInstance> serviceInstanceFactory;

    DemoServiceInstanceListSuppler(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays
                .asList(new DefaultServiceInstance(serviceId + "-pod-1", serviceId, "localhost", 8081, false),
                        new DefaultServiceInstance(serviceId + "-pod-2", serviceId, "localhost", 8082, false)
                ));

    }
}