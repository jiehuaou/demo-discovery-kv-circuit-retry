package com.example.demo.logic;

import feign.RequestLine;

public interface FeignHelloClient {
    @RequestLine("GET /id")
    String hello();
}
