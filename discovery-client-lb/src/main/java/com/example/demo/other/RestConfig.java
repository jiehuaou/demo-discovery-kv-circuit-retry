package com.example.demo.other;

import com.example.demo.config.SayHelloConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


// LoadBalancerConfiguration
@LoadBalancerClient(value = "data-service-1", configuration = LoadBalancerConfiguration.class)
@Configuration
public class RestConfig {
//    @LoadBalanced
    @Bean
    public RestTemplate loadbalancedRestTemplate() {
        return new RestTemplate();
    }

    /**
     * health-Check
     */
    @Primary
    @Bean
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    /**
     * business use
     */
    @LoadBalanced
    @Bean("load-balance")
    WebClient.Builder webClientBuilder2() {
        return WebClient.builder();
    }
}



