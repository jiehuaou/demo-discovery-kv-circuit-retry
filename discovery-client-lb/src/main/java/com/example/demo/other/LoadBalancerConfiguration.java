package com.example.demo.other;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


//@LoadBalancerClient(value = "data-service-1")

//@Configuration
public class LoadBalancerConfiguration {

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @Bean
    public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
//            DiscoveryClient discoveryClient,
//            Environment environment,
            ConfigurableApplicationContext context) {


//        ServiceInstanceListSupplier supplier = new DiscoveryClientServiceInstanceListSupplier(discoveryClient, environment);

        return ServiceInstanceListSupplier.builder()
                .withDiscoveryClient()
                .withHealthChecks()
                .withCaching()
                .build(context);
    }
}
