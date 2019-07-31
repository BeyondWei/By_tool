package com.sztech.datashare;

import com.sztech.szcloud.common.DemoInterface;
import org.springframework.stereotype.Component;

@Component
public class DemoInterfaceFallBack implements DemoInterfaceD {

    @Override
    public String getMember() {
        return "失败了";
    }

    @Override
    public String getS(String message) {
        return "失败了";
    }
}
