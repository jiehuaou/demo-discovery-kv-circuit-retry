package com.example.demo.break1;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class LookupService {

    @Autowired
    RestTemplate rest;

    @Autowired
    private RetryRegistry registry;

//    @Autowired
//    CircuitBreakerRegistry circuitBreakerRegistry;

    @PostConstruct
    public void postConstruct() {
        registry
                .retry("retrySearch1")
                .getEventPublisher()
                .onRetry(System.out::println);

//        circuitBreakerRegistry.circuitBreaker("example1")
//                .getEventPublisher()
//                .onStateTransition(System.out::println);
    }

    static final String idUrl = "http://data-service-1/id";
    static final String url404 = "http://data-service-1/404";

    @CircuitBreaker(name = "example1", fallbackMethod = "circuitLookupDefault")
    public String circuitOnly(){
        System.out.println("invoke ---> " + idUrl);
        ResponseEntity<String> resp = rest.getForEntity(idUrl, String.class);
        String data = resp.getBody();
        System.out.println("return ---> " + data);
        return data;
    }

    /**
    *   retry will ignore the case in CallNotPermittedException
     */
    @Retry(name="retrySearch1", fallbackMethod = "retryLookupDefault" )
    @CircuitBreaker(name = "example1" )
    public String retryCircuit(){
        System.out.println("invoke ---> " + idUrl);
        ResponseEntity<String> resp = rest.getForEntity(idUrl, String.class);
        String data = resp.getBody();
        System.out.println("return ---> " + data);
        return data;
    }

    public String circuitLookupDefault(Exception e){
        System.out.println("call ----> CircuitBreaker fallbackMethod");
        return " CircuitBreaker, service down + " + e.getClass().getSimpleName();
    }
    public String retryLookupDefault(Exception e){
        System.out.println("call ----> Retry fallbackMethod");
        return " Retry, service down + " + e.getClass().getSimpleName();
    }

    @Retry(name="retrySearch1", fallbackMethod = "retryLookupDefault")
    public String retryOnly(){
        System.out.println("invoke ---> " + idUrl);
        ResponseEntity<String> resp = rest.getForEntity(idUrl, String.class);
        String data = resp.getBody();
        System.out.println("return ---> " + data);
        return data;
    }

    public String lookup404() {
        System.out.println("invoke ---> " + url404);
        ResponseEntity<String> resp = rest.getForEntity(url404, String.class);
        String data = resp.getBody();
        System.out.println("return ---> " + data);
        return data;
    }
}
