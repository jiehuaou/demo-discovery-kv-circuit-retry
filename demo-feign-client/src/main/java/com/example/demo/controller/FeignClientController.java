package com.example.demo.controller;

import com.example.demo.logic.LogicHello;
import com.example.demo.svc.ServiceHello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FeignClientController {

    @Autowired
    ServiceHello logic;

    @GetMapping("/find-id")
    public String findId(){
        return logic.hello();
    }
}
