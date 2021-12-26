package com.zhaixin.carrentalbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CarrentalbookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarrentalbookingApplication.class, args);
    }

}
