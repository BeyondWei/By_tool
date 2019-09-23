package com.sztech.szcloud.file.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {


    @RequestMapping("/getMember")
    public String getMember() {
        return "i am member";
    }

    @RequestMapping("/{message}")
    public String getS( @PathVariable String message) {
        return message;
    }

}
