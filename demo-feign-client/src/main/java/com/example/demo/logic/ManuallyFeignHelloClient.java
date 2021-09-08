package com.example.demo.logic;

import feign.RequestLine;

public interface ManuallyFeignHelloClient {
    @RequestLine("GET /id")
    String hello();
}
