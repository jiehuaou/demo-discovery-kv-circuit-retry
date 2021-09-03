package com.example.demo.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import java.net.URI;

@RestController
public class AnotherClientController {

    @Autowired
    RestTemplate rest;


    @GetMapping("/another-id")
    public String discoveryId() throws ServiceUnavailableException {
        ResponseEntity<String> resp = rest.getForEntity("http://data-service-1/id", String.class);
        System.out.println("invoke ---> " + "http://data-service-1/id");
        String data = resp.getBody();
        System.out.println("return ---> " + data);
        return data;
    }

}
