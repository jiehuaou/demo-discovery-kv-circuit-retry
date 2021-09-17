package com.example.demo.other;


import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequestMapping("/rx")
@RestController()
public class MyReactiveController {
    private final WebClient.Builder loadBalancedWebClientBuilder;
    private final ReactorLoadBalancerExchangeFilterFunction lbFunction;

    public MyReactiveController(WebClient.Builder builder, ReactorLoadBalancerExchangeFilterFunction func) {
        this.loadBalancedWebClientBuilder = builder;
        this.lbFunction = func;
    }

    @RequestMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("rx-hello");
    }


    @RequestMapping("/load-id")
    public Mono<String> load() {
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
        return loadBalancedWebClientBuilder
                .build()
                .get()
                .uri("http://data-service-1/id")
                .retrieve().bodyToMono(String.class)
                .map(x -> String.format("/rx/%s", x));
    }
}
