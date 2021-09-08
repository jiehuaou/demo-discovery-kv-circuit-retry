package com.example.demo.controller;

import com.example.demo.logic.LogicHello;
import com.example.demo.logic.FeignHelloClient;
import com.example.demo.svc.ServiceHello;
import feign.Feign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
public class FeignClientController {

    FeignHelloClient myFeign;

    @PostConstruct
    public void afterConstruct(){

        myFeign = Feign.builder().target(FeignHelloClient.class, "http://localhost:8080/");

    }

    @Autowired
    ServiceHello logic;

    @GetMapping("/find-id")
    public String findId(){
        return logic.hello();
    }

    @GetMapping("/feign-id")
    public String feignId(){
        return myFeign.hello();
    }

}
