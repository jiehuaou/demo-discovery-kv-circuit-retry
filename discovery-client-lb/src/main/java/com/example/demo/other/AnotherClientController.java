package com.example.demo.other;

import com.example.demo.MyData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import java.net.URI;

@Slf4j
@RestController
public class AnotherClientController {

    @Autowired
    MyData data ;

    @Autowired
    RestTemplate rest;


    @GetMapping("/load-id")
    public String discoveryId() throws ServiceUnavailableException {

        log.info("invoke ---> {}" , "http://data-service-1/id");
        String data;
        try {
            ResponseEntity<String> resp = rest.getForEntity("http://data-service-1/id", String.class);
            data = resp.getBody();
        }catch (Exception ex){
            data = ex.getMessage();
        }

        log.info("return ---> {}" , data);
        return data;
    }

}
