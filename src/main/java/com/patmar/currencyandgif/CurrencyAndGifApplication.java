package com.patmar.currencyandgif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CurrencyAndGifApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyAndGifApplication.class, args);
    }

}
