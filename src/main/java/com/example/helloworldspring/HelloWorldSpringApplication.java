package com.example.helloworldspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class HelloWorldSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldSpringApplication.class, args);
    }

}
