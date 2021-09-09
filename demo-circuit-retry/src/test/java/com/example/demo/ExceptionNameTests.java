package com.example.demo;

import org.junit.jupiter.api.Test;

public class ExceptionNameTests {
    @Test
    public void getNameTest(){
        Exception ex = new Exception();
        System.out.println(ex.getClass().getSimpleName());
    }
}
