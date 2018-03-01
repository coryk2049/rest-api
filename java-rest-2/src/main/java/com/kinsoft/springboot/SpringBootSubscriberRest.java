package com.kinsoft.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.kinsoft.springboot"})
public class SpringBootSubscriberRest {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSubscriberRest.class, args);
    }
}
