package com.main.t1executiontimeofmethods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class T1ExecutionTimeOfMethodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(T1ExecutionTimeOfMethodsApplication.class, args);
    }

}
