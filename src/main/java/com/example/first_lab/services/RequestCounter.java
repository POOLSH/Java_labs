package com.example.first_lab.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RequestCounter {
    private  AtomicInteger count= new AtomicInteger(0);

    public int setZero(){
        count=new AtomicInteger();
        return count.get();
    }
    public int increment(){

        return count.incrementAndGet();
    }
    public int getCount(){
        return count.get();
    }
}
