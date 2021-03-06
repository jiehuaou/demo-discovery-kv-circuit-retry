package com.example.demo;

import java.net.URI;
import java.util.List;
import java.util.Random;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DiscoveryClientController {
    Random rand = new Random(1024L);
    @Autowired
    private DiscoveryClient discoveryClient;

    public int random(int size) {
        
        return rand.nextInt(size);
    }

    public URI serviceUrl() {
        List<ServiceInstance> insList = discoveryClient.getInstances("data-service-1");
        if(insList.isEmpty()){
            return null;
        }
        
        ServiceInstance first = insList.get(0);
        return first.getUri();
    }

    @GetMapping("/find-id")
    public String discoveryId() throws ServiceUnavailableException{
        URI instance = serviceUrl();
        if(instance==null){
            throw new ServiceUnavailableException();
        }
        URI service = instance.resolve("/id");
        RestTemplate rest = new RestTemplate();
        ResponseEntity<String> resp = rest.getForEntity(service, String.class);
        System.out.println("invoke ---> " + service);
        String data = resp.getBody();
        System.out.println("return ---> " + data);
        return data;
    }
}