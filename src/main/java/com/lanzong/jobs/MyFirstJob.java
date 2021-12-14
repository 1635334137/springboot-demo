package com.lanzong.jobs;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyFirstJob {
    public void sayHello(){
        System.out.println("MyFirstJob:sayHello!"+new Date());
    }
}
