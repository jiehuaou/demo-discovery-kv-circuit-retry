package com.example.demo.controller;

import com.example.demo.logic.LogicHello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignClientController {

    @Autowired
    LogicHello logic;

    @GetMapping("/find-id")
    public String findId(){
        return logic.hello();
    }
}
