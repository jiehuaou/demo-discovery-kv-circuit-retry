package com.example.demo.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("data-service-1")
public interface LogicHello {

    @RequestMapping(method = RequestMethod.GET, value = "/id")
    String hello();
}
