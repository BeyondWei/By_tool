package com.youfan.ditto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(scanBasePackages = {"com.sztech.szcloud.common", "com.youfan"})
public class DittoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DittoApplication.class, args);
    }

}
