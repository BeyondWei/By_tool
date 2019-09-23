package com.sztech.datashare;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "szcloud-upload-service", fallback = DemoInterfaceFallBack.class)
public interface DemoInterfaceD {

    @RequestMapping("/getMember")
    String getMember();

    @RequestMapping("/{message}")
    String getS(@PathVariable("message") String message);
}
