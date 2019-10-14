package com.sztech.datashare;

import com.sztech.szcloud.common.dto.ResultsDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = "com.sztech")
@EnableDiscoveryClient
@EnableFeignClients
public class DatashareApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatashareApplication.class, args);
    }

    @RestController
    @RequestMapping(value = "/goods")
    class Demo {


        @Resource
        private DemoInterfaceD d;

        @RequestMapping("/hrrlo")
        public String toGetMember() {
            return d.getMember();
        }

        @RequestMapping("/{id}")
        public ResultsDto getHello(@PathVariable("id") String id) {
            return ResultsDto.success(d.getS(id));
        }

    }

}
