package com.example.notifymoney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotifyMoneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotifyMoneyApplication.class, args);
    }
}
