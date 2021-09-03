package com.example.demo.other;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FindService {


    public Object getStores() {
        //do stuff that might fail
        return "";
    }

    public String defaultStores() {
        return "sample";
    }
}
