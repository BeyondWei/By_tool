package com.sztech.bytool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = "com.sztech")
public class ByToolApplication {
    public static void main(String[] args) {
        SpringApplication.run(ByToolApplication.class, args);
    }
}
