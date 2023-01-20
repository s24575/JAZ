package com.westeros.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.westeros.data", "com.westeros.webapi"})
public class WesterosWebApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(WesterosWebApiApplication.class, args);
    }

}
