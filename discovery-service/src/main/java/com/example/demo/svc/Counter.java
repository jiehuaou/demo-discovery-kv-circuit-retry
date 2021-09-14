package com.example.demo.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Counter {

    private long counter = 0;


    private boolean inSuccessState = true; // failed

    @Autowired
    MyData data;

    public synchronized long getCounter() {
        return counter;
    }
    public synchronized boolean isSuccessState() {
        return inSuccessState;
    }

    public synchronized boolean countAndSuccess() {
        if(inSuccessState && counter>=data.getSuccessCount()){
            counter = 0;
            inSuccessState = false;
        }else if(!inSuccessState && counter>=data.getFailCount()){
            counter = 0;
            inSuccessState = true;
        }
        String msg;
        String state = inSuccessState? "success-state": "failed-state";
        int total = inSuccessState? data.getSuccessCount(): data.getFailCount();
        long index  = counter;
        msg = String.format("%d out of %d,  .... %s  ", index, total, state);
        System.out.println(msg);
        counter = counter + 1;
        return inSuccessState;
    }
}
