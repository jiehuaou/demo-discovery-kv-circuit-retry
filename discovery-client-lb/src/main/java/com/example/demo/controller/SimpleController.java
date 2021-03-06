package com.example.demo.controller;

import com.example.demo.data.MyData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import java.net.URI;

@Slf4j
@RestController
public class SimpleController {

    @Autowired
    MyData data ;

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/load-id")
    public String discoveryId() throws ServiceUnavailableException {

        log.info("invoke ---> {}" , "http://data-service-1/id");
        String data;
        try {
            ResponseEntity<String> resp = restTemplate.getForEntity("http://data-service-1/id", String.class);
            data = resp.getBody();
        }catch (Exception ex){
            data = ex.getMessage();
        }

        log.info("return ---> {}" , data);
        return data;
    }

    /**
     *
    void test(){
        restTemplate
                .exchange(
                        RequestEntity
                                .post("http://abbbb/id")
                                .header("Ce-type", "open-type")
                                .body("hello"),
                        String.class )
                ;
    }
    */
}
