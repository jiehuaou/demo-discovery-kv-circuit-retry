package com.example.demo.remote;

import feign.RequestLine;

public interface FeignHelloClient {
    @RequestLine("GET /id")
    String hello();
}
