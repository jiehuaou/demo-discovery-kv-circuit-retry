package com.example.demo.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Log4j2
@RequestMapping("/rx")
@RestController()
public class MyReactiveController {
    private final WebClient.Builder loadBalancedWebClientBuilder;
    private final ReactorLoadBalancerExchangeFilterFunction lbFunction;

    public MyReactiveController(@Qualifier("load-balance") WebClient.Builder builder,
                                ReactorLoadBalancerExchangeFilterFunction func) {
        this.loadBalancedWebClientBuilder = builder;
        this.lbFunction = func;
    }

    @RequestMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("rx-hello");
    }


    @RequestMapping("/load-id")
    public Mono<String> load() {
        log.info("Rx client /load-id ");
        return WebClient
                .builder()
                .baseUrl("data-service-1")
                .filter(lbFunction)
                .build()
                .get()
                .uri("http://data-service-1/id")
                .retrieve().bodyToMono(String.class)
                .map(x -> String.format("/rx/%s", x));
    }

    @RequestMapping("/load2-id")
    public Mono<String> load2() {
        log.info("Rx client /load2-id");
        return loadBalancedWebClientBuilder
                .build().get()
                .uri("http://data-service-1/id")
                .retrieve()
                .bodyToMono(String.class)
                .map(x -> String.format("/rx/%s", x));
    }
}
